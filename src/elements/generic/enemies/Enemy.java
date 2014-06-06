package elements.generic.enemies;

import jeu.CSG;
import jeu.Physic;
import jeu.Stats;
import jeu.Strings;
import jeu.db.Requests;
import jeu.mode.EndlessMode;
import jeu.mode.extensions.ScreenShake;
import assets.AssetMan;
import assets.SoundMan;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool.Poolable;

import elements.generic.Element;
import elements.generic.Player;
import elements.generic.components.Phase;
import elements.generic.components.behavior.Behavior;
import elements.generic.weapons.Weapon;
import elements.generic.weapons.enemies.EnemyWeapon;
import elements.generic.weapons.enemies.InvocableWeapon;
import elements.generic.weapons.player.PlayerWeapon;
import elements.particular.bonuses.Bonus;
import elements.particular.other.WaveEffect;
import elements.particular.particles.Particles;
import elements.particular.particles.individual.PrecalculatedParticles;

public abstract class Enemy extends Element implements Poolable {

	public final static Array<Enemy> LIST = new Array<Enemy>(40);
	protected static final Vector2 TMP_POS = new Vector2(), TMP_DIR = new Vector2();
	protected static final Rectangle COLLISION = new Rectangle();
	public static final int RED = 0, BLUE = 1, GREEN = 2;
	private static final float ALERT_WIDTH = 10, ALERT_HALF_WIDTH = ALERT_WIDTH / 2;
	private static final float DETECT_RANGE = Stats.WIDTH_DIV_10;
	public boolean dead = false;
	public float angle = 0, nextShot = 1;
	protected int hp;

	protected Enemy() {
		this.hp = getMaxHp();
	}
	
	public static void affichageEtMouvement(SpriteBatch batch) {
		for (final Enemy e : LIST) {
			e.now += EndlessMode.delta;
			e.displayWarning(batch);
			e.setColor(batch);
			e.getPhase().draw(e, batch);
			e.getPhase().shoot(e);
			e.dead = e.getPhases()[e.index].move(e);
			e.removeColor(batch);
			e.isMoving();
		}
	}

	protected void removeColor(SpriteBatch batch) {
	}

	protected void setColor(SpriteBatch batch) {
	}

	protected void isMoving() {	}

	private void displayWarning(SpriteBatch batch) {
		if (Physic.isNotDisplayed(this)) {
			// left
			if ( (pos.x + getWidth()) < 0 && (pos.x + getWidth()) > -DETECT_RANGE) {
				batch.setColor(1, 0, 0.5f, 1);
				batch.draw(AssetMan.dust,
						-ALERT_HALF_WIDTH, pos.y - Stats.UUU,
						// pos.x is negative
						ALERT_WIDTH, (DETECT_RANGE + (pos.x + getWidth())) + Stats.U6);
				batch.setColor(AssetMan.WHITE);
			} else if (pos.x > CSG.screenWidth && pos.x < Stats.GAME_ZONE_W_PLUS_WIDTH_DIV_10) { // right
				batch.setColor(1, 0, 0.5f, 1);
				batch.draw(AssetMan.dust, CSG.screenWidth - ALERT_HALF_WIDTH, pos.y - Stats.UUU, ALERT_WIDTH,
						(DETECT_RANGE + (CSG.gameZoneWidth - pos.x)) + Stats.U6);
				batch.setColor(AssetMan.WHITE);
			}
		}
	}

	public static float f = 0;
	public static void draw(SpriteBatch batch) {
		for (final Enemy e : LIST) 
			e.getPhases()[e.index].draw(e, batch);
	}


	/**
	 * Renvoie le rectangle de collision de l'objet
	 * 
	 * @return
	 */
	public Rectangle getRectangleCollision() {
		COLLISION.x = pos.x;
		COLLISION.y = pos.y;
		COLLISION.width = getWidth();
		COLLISION.height = getHeight();
		return COLLISION;
	}

	/**
	 * On decremente les pvs de la force de l'arme. Si c'est 0 ou moins on le condamne a mort. Ca ajoute les bonus eventuellement
	 * 
	 * @param a.getPower()
	 * @return return true si vivant.
	 */
	protected static float deltaMulImpact;
	public boolean stillAlive(PlayerWeapon a) {
		Particles.addPartEnemyTouched(a, this);
		deltaMulImpact = EndlessMode.delta * Stats.IMPACT;
		hp -= a.getPower();
		if (hp <= 0) {
			die();
			return false;
		}
		pos.x += (a.dir.x * deltaMulImpact);
		pos.y += (a.dir.y * deltaMulImpact);
		if (pos.x + getWidth() < -Stats.WIDTH_DIV_10)
			pos.x = Stats.WIDTH_DIV_10 - getWidth();
		if (pos.x > Stats.GAME_ZONE_W_PLUS_WIDTH_DIV_10)
			pos.x = Stats.GAME_ZONE_W_PLUS_WIDTH_DIV_10;
		if (pos.y > CSG.HEIGHT_PLUS_4 + getHeight())
			pos.y = CSG.HEIGHT_PLUS_4 + getHeight() - 1;
		return true;
	}
	
	protected void changePhase(int index, float firerate) {
		this.index = index;
		phaseTime = 0;
		nextShot = firerate;
	}
	
	protected void changePhase() {
		super.changePhase();
		nextShot = getFirerate();
	}
	
	public boolean stillAliveEnemyWeapon(EnemyWeapon a) {
		deltaMulImpact = EndlessMode.delta * Stats.IMPACT;
		pos.x += (a.dir.x * deltaMulImpact);
		pos.y += (a.dir.y * deltaMulImpact);
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
		hp = getMaxHp();
		dead = false;
		angle = 0;
		index = 0;
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
//		for (XP xp : Bonus.XP_LIST)
//			xp.state = XP.HOMMING;
	}

	public static void attackAllEnemies(PlayerWeapon a) {
		for (final Enemy e : LIST) {
			a.dir.x = ((e.pos.x + e.getHalfWidth())) - Player.xCenter;
			a.dir.y = ((e.pos.y + e.getHalfWidth())) - Player.yCenter;
			if (e.dead == false) {
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
		this.pos.x = pos.x - getHalfWidth();
	}
	
	public static void deadBodiesEverywhere() {
		for (int i = 0; i < Enemy.LIST.size; i++) {
			if (Enemy.LIST.get(i).dead) {
				Enemy.LIST.get(i).free();
				Enemy.LIST.removeIndex(i);
			}
		}
	}
	
	@Override	public void setAngle(float angle) {			this.angle = angle;}
	@Override	public float getNextShot() {				return nextShot;								}
	@Override	public void setNextShot(float f) {			nextShot = f;									}
	
	public static final PlayerWeapon bomb = new PlayerWeapon() {
		@Override		public float getWidth() {					return 0;		}
		@Override		public float getHeight() {				return 0;		}
		@Override		public float getHalfWidth() {				return 0;		}
		@Override		public float getHalfHeight() {			return 0;		}
		@Override		public void free() {									}		
		@Override		public float getColor() {				return 0;		}
		@Override		public int getPower() { 				return 250;		}
		@Override		public float[] getColors() {			return PrecalculatedParticles.colorsOverTimeRed;		}
		@Override		public Phase[] getPhases() {			return null;		}
		@Override		public void setAngle(float angle) {		}
		@Override		public void setShootingAngle(float shootingAngle) {		}
	};
	
	public static final PlayerWeapon superBomb = new PlayerWeapon() {
		@Override		public float getWidth() {					return 0;		}
		@Override		public float getHeight() {				return 0;		}
		@Override		public float getHalfWidth() {				return 0;		}
		@Override		public float getHalfHeight() {			return 0;		}
		@Override		public void free() {		}		
		@Override		public float getColor() {				return 0;		}
		@Override		public int getPower() { 				return 380;		}
		@Override		public float[] getColors() {			return PrecalculatedParticles.colorsOverTimeRed;		}
		@Override		public Phase[] getPhases() {			return null;		}
		@Override		public void setAngle(float angle) {		}
		@Override		public void setShootingAngle(float shootingAngle) {		}
	};

	@Override		public void setShootingAngle(float shootingAngle) {		}
	public void addAngle(float f) {
		angle += f;
	}

	protected static float initFirerate(float def, int pk) {
		if (CSG.updateNeeded)
			return Requests.getFireRateEnemy(pk, def);
		return def;
	}
	
	protected static float initNextShot(float def, int pk) {
		if (CSG.updateNeeded)
			return Requests.getNextShot(pk, def);
		return def;
	}
	
	protected static float initSpeed(float def, int pk) {
		if (CSG.updateNeeded)
			return Requests.getSpeed(pk, def) * Stats.u;
		return def * Stats.u;
	}
	
	protected static int initHp(int def, int pk) {
		if (CSG.updateNeeded)
			return Requests.getPvEnemy(pk, def);
		return def;
	}
	
	protected static int initExplosion(int def, int pk) {
		if (CSG.updateNeeded)
			return Requests.getExplosionEnemy(pk, def);
		return def;
	}
	
	protected static int initXp(int def, int pk) {
		if (CSG.updateNeeded)
			return Requests.getXpEnemy(pk, def);
		return def;
	}

	protected static int getXp(int baseXp, int lvl) {
		switch (lvl) {
		case 1:
			return (int) (baseXp * CSG.mulLvl1 * CSG.mulSCORE);
		case 3:
			return (int) (baseXp * CSG.mulLvl3 * CSG.mulSCORE);
		default:
			return (int) (baseXp * CSG.mulLvl4 * CSG.mulSCORE);
		}
	}
	
//	protected static Behavior initBehavior(int pk, int def) {
//		if (CSG.updateNeeded)
//			return detectBehavior(Requests.getBehavior(pk, def));
//		return detectBehavior(def);
//	}
	
	protected static Behavior initBehaviors(int pk, Behavior straightOn) {
//		if (CSG.updateNeeded)
//			return detectBehavior(Requests.getBehavior(pk, straightOn));
		return straightOn;
	}

//	private static Behavior detectBehavior(int b) {
//		switch (b) {
//		case Behavior.SLASH : 			return new Slash();
//		case Behavior.TURN_AROUND :		return new TurnAround();
//		case Behavior.KINDER :			return new KinderBehavior();
//		case Behavior.SINK :			return new Sink();
//		case Behavior.ROUND_N_ROUND :	return new RoundAndRound();
//		case Behavior.UTURN :			return new Uturn();
//		case Behavior.ZIGZAG :			return new ZigZag();
//		case Behavior.HOMMING :			return new Homming();
//		case Behavior.UMBRELLA :		return new Umbrella();
//		}
//		return new Slash();
//	}
	protected static int getModulatedPv(int pv, int lvl) {
		switch (lvl) {
		case 3: return (int) (pv * Stats.HPNV3);
		case 4: return (int) (pv * Stats.HPNV4);
		}
		return pv;
	}
	
	protected static float getModulatedSpeed(float speed, int lvl) {
		switch (lvl) {
		case 3: return (int) (speed * Stats.VNV3);
		case 4: return (int) (speed * Stats.VNV4);
		}
		return speed;
	}
	

	protected static InvocableWeapon initEnemyWeapon(int def, int pk) {
		if (CSG.updateNeeded)
			return detectWeapon(Requests.getEnemyWeapon(pk, def));
		return detectWeapon(def);
	}

	private static InvocableWeapon detectWeapon(int b) {
//		switch (b) {
//		case BlueBulletSlow.PK : 			return BlueBulletSlow.POOL.obtain();
//		case Meteorite.PK : 				return Meteorite.POOL.obtain();
//		case Tournante.PK : 				return Tournante.POOL.obtain();
//		case InsectWeapon.PK : 				return InsectWeapon.POOL.obtain();
//		case KinderWeapon.PK : 				return KinderWeapon.POOL.obtain();
//		case LaserWeapon.PK : 				return LaserWeapon.POOL.obtain();
//		case SmallFireball.PK : 			return SmallFireball.POOL.obtain();
//		case Fireball.PK : 					return Fireball.POOL.obtain();
//		case FragWeapon.PK : 				return FragWeapon.POOL.obtain();
//		case BlueBullet.PK : 				return BlueBullet.POOL.obtain();
//		case BlueBulletFast.PK : 			return BlueBulletFast.POOL.obtain();
//		case VicousBullet.PK : 				return VicousBullet.POOL.obtain();
//		case SmallMine.PK : 				return SmallMine.POOL.obtain();
//		}
		return null;
	}
	
//	@Override
//	public Invocable invoke() {
//		return this;
//	}
	
	public int getColor() {		return RED;	}
	protected Sound getExplosionSound() {				return SoundMan.explosion3;																		}
	public float getSpeed() {							return 3333;																		}
	public float getDirectionX() {						return 0;																			}
	public float getAngle() {							return angle;																			}
	public boolean toLeft() {							return false;	}
	public void setLeft(boolean b) {							}
	public float getRotation() {		return 0;	}
	public void setRotation(float f) {			}
	public abstract int getXp();
	public abstract void free();
	protected abstract int getMaxHp();
	protected abstract String getLabel();
	public abstract int getBonusValue();
	public abstract float getDirectionY();
	public abstract int getExplosionCount();
	
}