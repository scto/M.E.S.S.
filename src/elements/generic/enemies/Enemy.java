package elements.generic.enemies;

import jeu.CSG;
import jeu.Physic;
import jeu.Stats;
import jeu.Strings;
import jeu.mode.EndlessMode;
import jeu.mode.extensions.ScreenShake;
import assets.AssetMan;
import assets.SoundMan;
import assets.sprites.Animations;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool.Poolable;

import elements.generic.Element;
import elements.generic.components.Dimensions;
import elements.generic.components.EnemyStats;
import elements.generic.weapons.Weapon;
import elements.generic.weapons.enemies.EnemyWeapon;
import elements.generic.weapons.player.PlayerWeapon;
import elements.particular.Player;
import elements.particular.bonuses.Bonus;
import elements.particular.other.WaveEffect;
import elements.particular.particles.Particles;
import elements.particular.particles.individual.PrecalculatedParticles;

public abstract class Enemy extends Element implements Poolable {

	public final static Array<Enemy> LIST = new Array<Enemy>(40);
	protected static final Vector2 TMP_POS = new Vector2(), TMP_DIR = new Vector2();
	protected static final Rectangle COLLISION = new Rectangle();
	public static final int RED = 0, BLUE = 1, GREEN = 2;
	protected static final float ALERT_WIDTH = 10, ALERT_HALF_WIDTH = ALERT_WIDTH / 2, DETECT_RANGE = Stats.WIDTH_DIV_10, MOD_FIRERATE = 1.2f;
	protected static float tmpDeltaMulImpact;
	public boolean dead = false;
	public float nextShot = 1;
	protected int hp;

	protected Enemy() {
		this.hp = getMaxHp();
	}
	
	protected int getMaxHp() {
		return getEnemyStats().getHp();
	}

	public static void affichageEtMouvement(SpriteBatch batch) {
		for (final Enemy e : LIST) {
			if (e.dead)
				continue;
			e.now += EndlessMode.delta;
			e.displayWarning(batch);
			e.displayOnScreen(batch);
			e.mightShoot();
			e.move();
			if (!Physic.isOnScreenWithTolerance(e.pos, e.getDimensions().halfHeight, e.getDimensions().halfWidth))
				e.dead = true;
		}
	}

	private void mightShoot() {
		if (now > nextShot) {
			nextShot = now + getFirerate();
			shoot();
		}
	}

	protected void shoot() {
	}
	
	@Override
	public boolean isInGoodShape() {
		return false;
	}
	
	private void displayWarning(SpriteBatch batch) {
		if (Physic.isNotDisplayed(this)) {
			// left
			if ( (pos.x + getDimensions().width) < 0 && (pos.x + getDimensions().width) > -DETECT_RANGE) {
				batch.setColor(1, 0, 0.5f, 1);
				batch.draw(AssetMan.dust,
						-ALERT_HALF_WIDTH, pos.y - Stats.U3,
						// pos.x is negative
						ALERT_WIDTH, (DETECT_RANGE + (pos.x + getDimensions().width)) + Stats.U6);
				batch.setColor(AssetMan.WHITE);
				// right
			} else if (pos.x > CSG.screenWidth && pos.x < Stats.GZW_PLUS_MARGIN) { 
				batch.setColor(1, 0, 0.5f, 1);
				batch.draw(AssetMan.dust, CSG.screenWidth - ALERT_HALF_WIDTH, pos.y - Stats.U3, ALERT_WIDTH,
						(DETECT_RANGE + (CSG.gameZoneWidth - pos.x)) + Stats.U6);
				batch.setColor(AssetMan.WHITE);
			}
		}
	}

	public static float f = 0;
	public static void draw(SpriteBatch batch) {
		for (final Enemy e : LIST) {
			e.displayOnScreen(batch);
		}
	}


	/**
	 * Renvoie le rectangle de collision de l'objet
	 * 
	 * @return
	 */
	public Rectangle getRectangleCollision() {
		COLLISION.x = pos.x;
		COLLISION.y = pos.y;
		COLLISION.width = getDimensions().width;
		COLLISION.height = getDimensions().height;
		return COLLISION;
	}

	/**
	 * On decremente les pvs de la force de l'arme. Si c'est 0 ou moins on le condamne a mort. Ca ajoute les bonus eventuellement
	 * 
	 * @param a.getPower()
	 * @return return true si vivant.
	 */
	public boolean stillAlive(PlayerWeapon a) {
		Particles.addPartEnemyTouched(a, this);
		tmpDeltaMulImpact = EndlessMode.delta * Stats.IMPACT;
		hp -= a.getPower();
		if (hp <= 0) {
			die();
			return false;
		}
		pos.x += (a.dir.x * tmpDeltaMulImpact);
		pos.y += (a.dir.y * tmpDeltaMulImpact);
		if (pos.x + getDimensions().width < -Stats.WIDTH_DIV_10)
			pos.x = Stats.WIDTH_DIV_10 - getDimensions().width;
		if (pos.x > Stats.GZW_PLUS_MARGIN)
			pos.x = Stats.GZW_PLUS_MARGIN;
		if (pos.y > CSG.HEIGHT_PLUS_4 + getDimensions().height)
			pos.y = CSG.HEIGHT_PLUS_4 + getDimensions().height - 1;
		return true;
	}
	
	public boolean stillAliveEnemyWeapon(EnemyWeapon a) {
		tmpDeltaMulImpact = EndlessMode.delta * Stats.IMPACT;
		pos.x += (a.dir.x * tmpDeltaMulImpact);
		pos.y += (a.dir.y * tmpDeltaMulImpact);
		hp -= 30;
		if (hp <= 0) {
			die();
			return false;
		}
		return true;
	}

	public void die() {
		if (dead)
			return;
		Bonus.addBonus(this);
		Particles.explosion(this);
		SoundMan.playBruitage(getExplosionSound());
		ScreenShake.screenShake(getXp());
		EndlessMode.explosions++;
		dead = true;
	}

	@Override
	public void reset() {
		hp = getEnemyStats().getHp();
		dead = false;
		nextShot = 0;
		super.reset();
	}

	public static void bombe() {
		CSG.talkToTheWorld.unlockAchievementGPGS(Strings.ACH_BOMB);
		EndlessMode.transition.activate(10);
		if (LIST.size >= 15) {
			CSG.talkToTheWorld.unlockAchievementGPGS(Strings.ACH_15_ENEMY);
		}
		WaveEffect.add(Player.xCenter, Player.yCenter, AssetMan.convertARGB(1, 1f, 	(CSG.R.nextFloat() + .8f) / 1.6f, 	CSG.R.nextFloat()/8));
		WaveEffect.add(Player.xCenter, Player.yCenter, AssetMan.convertARGB(1, 1f, 	(CSG.R.nextFloat() + .8f) / 1.6f, 	CSG.R.nextFloat()/8));
		attackAllEnemies(bomb);
	}

	public static void attackAllEnemies(PlayerWeapon a) {
		for (final Enemy e : LIST) {
			if (e.dead == false) {
				a.dir.x = ((e.pos.x + e.getDimensions().halfWidth)) - Player.xCenter;
				a.dir.y = ((e.pos.y + e.getDimensions().halfWidth)) - Player.yCenter;
				e.stillAlive(a);
				a.dir.nor();
				a.dir.scl(250);
				e.pos.x += (a.dir.x * Stats.IMPACT);
				e.pos.y += (a.dir.y * Stats.IMPACT);
			} 
		}
		EndlessMode.effetBloom();
		Particles.bombExplosion(a);
	}

	public static void clear() {
		for (final Enemy e : LIST)
			e.free();
		LIST.clear();
	}

	/**
	 * Verifie si il y a collision avec la balle.
	 * //		if (dead) //			return false; 
	 * @param a
	 * @return true = collision
	 */
	public boolean isOnPlayer() {
		return getRectangleCollision().contains(Player.xCenter, Player.yCenter);
	}
	
	public static int getPvBoss(int pvBoss) {
		return (int) (pvBoss + (pvBoss * (Player.weapon.nv() /1.3f) ));
	}
	
	public boolean isTouched(Weapon a) {
		return a.getRectangleCollision().overlaps(getRectangleCollision());
	}
	
	public void setPosition(Vector2 pos) {
		this.pos.y = pos.y;
		this.pos.x = pos.x - getDimensions().halfWidth;
	}
	
	public static void deadBodiesEverywhere() {
		for (int i = 0; i < Enemy.LIST.size; i++) {
			if (Enemy.LIST.get(i).dead) {
				Enemy.LIST.get(i).free();
				Enemy.LIST.removeIndex(i);
			}
		}
	}
	
	@Override
	public float getPvPercentage() {
		return (getEnemyStats().getHp() / hp) * 100;
	}
	
	public static final PlayerWeapon bomb = getTypicalBullet(250), superBomb = getTypicalBullet(320);

	protected static PlayerWeapon getTypicalBullet(int power) {
		return new PlayerWeapon() {
			@Override		public void free() {									}		
			@Override		public float getColor() {				return 0;		}
			@Override		public int getPower() { 				return 250;		}
			@Override		public float[] getColors() {			return PrecalculatedParticles.colorsOverTimeRed;		}
			@Override		public void setAngle(float angle) {		}
			@Override		public Animations getAnimation() {			return null;		}
			@Override		public Dimensions getDimensions() {			return Dimensions.ORANGE_BULLET;			}
		};
	}
	
	protected static int getXp(int baseXp, int lvl) {
		switch (lvl) {
		case 1:			return (int) (baseXp * CSG.mulLvl1 * CSG.mulSCORE);
		case 3:			return (int) (baseXp * CSG.mulLvl3 * CSG.mulSCORE);
		default:		return (int) (baseXp * CSG.mulLvl4 * CSG.mulSCORE);
		}
	}
	
	public void setAngle(float angle) {						this.angle = angle;							}
	public float getRotation() {							return 0;									}
	public void addAngle(float f) {							angle += f;									}
	public int getColor() {									return RED;									}
	public float getAngle() {								return angle;								}
	public boolean toLeft() {								return false;								}
	protected Sound getExplosionSound() {					return SoundMan.explosion3;					}
	public int getXp() {									return getEnemyStats().getXp();				}
	public int getExplosionCount() {						return getEnemyStats().explosion;			}
	public int getBonusValue() {							return getEnemyStats().getBonusValue();		}
	public void setLeft(boolean b) {																	}
	public void setRotation(float f) {																	}
	public void init() {																				}
	public abstract EnemyStats getEnemyStats();
	public abstract void free();

	public float getFirerate() {
		return 1;
	}

	public int getShotNumber() {
		return 0;
	}

	public int getNumberOfShots() {
		return 0;
	}

	public void addShots(int i) {
	}

	public void setNextShot(float g) {
		nextShot = g;
	}
	
}