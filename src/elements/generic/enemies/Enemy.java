package elements.generic.enemies;

import jeu.CSG;
import jeu.EndlessMode;
import jeu.Physic;
import jeu.Stats;
import jeu.Strings;
import jeu.db.Requests;
import assets.AssetMan;
import assets.SoundMan;
import assets.animation.AnimationCylon;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool.Poolable;

import elements.generic.Element;
import elements.generic.Invocable;
import elements.generic.Player;
import elements.generic.behavior.Ball;
import elements.generic.behavior.Behavior;
import elements.generic.behavior.Homming;
import elements.generic.behavior.KinderBehavior;
import elements.generic.behavior.RoundAndRound;
import elements.generic.behavior.Sink;
import elements.generic.behavior.Slash;
import elements.generic.behavior.StraightOn;
import elements.generic.behavior.TurnAround;
import elements.generic.behavior.Umbrella;
import elements.generic.behavior.Uturn;
import elements.generic.behavior.ZigZag;
import elements.generic.weapons.Weapons;
import elements.generic.weapons.enemies.BlueBullet;
import elements.generic.weapons.enemies.BlueBulletFast;
import elements.generic.weapons.enemies.BlueBulletSlow;
import elements.generic.weapons.enemies.EnemyWeapon;
import elements.generic.weapons.enemies.Fireball;
import elements.generic.weapons.enemies.FragWeapon;
import elements.generic.weapons.enemies.HalfSizeFireball;
import elements.generic.weapons.enemies.InsectWeapon;
import elements.generic.weapons.enemies.InvocableWeapon;
import elements.generic.weapons.enemies.KinderWeapon;
import elements.generic.weapons.enemies.LaserWeapon;
import elements.generic.weapons.enemies.Meteorite;
import elements.generic.weapons.enemies.Tournante;
import elements.generic.weapons.enemies.VicousBullet;
import elements.generic.weapons.player.PlayerWeapon;
import elements.particular.bonuses.Bonus;
import elements.particular.bonuses.BonusBombe;
import elements.particular.bonuses.XP;
import elements.particular.other.WaveEffect;
import elements.particular.particles.Particles;

public abstract class Enemy extends Element implements Poolable, Invocable {

	public final static Array<Enemy> LIST = new Array<Enemy>(40);
	protected static final Vector2 TMP_POS = new Vector2(), TMP_DIR = new Vector2();
	protected static final Rectangle COLLISION = new Rectangle();
	protected int pv;
	public boolean dead = false;
	public final Vector2 dir = new Vector2();
	public float angle = 90;
//	private static final float lvl4 = AssetMan.convertARGB(1, .9f, 1, .9f);
//	private static final float PV100 = AssetMan.convertARGB(1, 0, 1, 0);
//	private static final float PV95 = AssetMan.convertARGB(1, 0, .95f, 0);
//	private static final float PV90 = AssetMan.convertARGB(1, 0, .90f, 0);
//	private static final float PV85 = AssetMan.convertARGB(1, 0, .85f, 0);
//	private static final float PV80 = AssetMan.convertARGB(1, 0, .80f, 0);
//	private static final float PV75 = AssetMan.convertARGB(1, 0, .75f, 0);
//	private static final float PV70 = AssetMan.convertARGB(1, .05f, .70f, 0);
//	private static final float PV65 = AssetMan.convertARGB(1, .10f, .65f, 0);
//	private static final float PV60 = AssetMan.convertARGB(1, .15f, .60f, 0);
//	private static final float PV55 = AssetMan.convertARGB(1, .20f, .55f, 0);
//	private static final float PV50 = AssetMan.convertARGB(1, .25f, .50f, 0);
//	private static final float PV45 = AssetMan.convertARGB(1, .30f, .45f, 0);
//	private static final float PV40 = AssetMan.convertARGB(1, .35f, .40f, 0);
//	private static final float PV35 = AssetMan.convertARGB(1, .40f, .35f, 0);
//	private static final float PV30 = AssetMan.convertARGB(1, .45f, .30f, 0);
//	private static final float PV25 = AssetMan.convertARGB(1, .50f, .25f, 0);
//	private static final float PV20 = AssetMan.convertARGB(1, .55f, .20f, 0);
//	private static final float PV15 = AssetMan.convertARGB(1, .60f, .15f, 0);
//	private static final float PV10 = AssetMan.convertARGB(1, .65f, .10f, 0);
//	private static final float PV05 = AssetMan.convertARGB(1, .70f, .05f, 0);
//	private static final float[] pvsColors = {PV100, PV95, PV90, PV85, PV80, PV75, PV70, PV65, PV60, PV55, PV50, PV45, PV40, PV35, PV30, PV25, PV20, PV15, PV10, PV05};
//	private int color = 0;

	protected Enemy() {
		this.pv = getPvMax();
	}
	
	public static void affichageEtMouvement(SpriteBatch batch) {
//		if (EndlessMode.modeDifficulte == 4)
//			batch.setColor(lvl4);
		for (final Enemy e : LIST) {
			e.now += EndlessMode.delta;
			e.afficher(batch);
			e.tir();
			e.mouvementEtVerif();
		}
//		batch.setColor(AssetMan.WHITE);
	}

	public void mouvementEtVerif() {
		getBehavior().act(this);
		if (Physic.isOnScreen(pos, getHeight(), getWidth()) == false)
			dead = true;
	}

	public void afficher(SpriteBatch batch) {
//		batch.setColor(pvsColors[color]);
		batch.draw(getTexture(), pos.x, pos.y, getHalfWidth(), getHalfHeight(), getWidth(), getHeight(), 1, 1, angle+90);
	}

	public static float f = 0;
	public static void draw(SpriteBatch batch) {
//		if (EndlessMode.modeDifficulte == 4)
//			batch.setColor(lvl4);
		for (final Enemy e : LIST) 
			e.afficher(batch);
//		if (EndlessMode.triggerStop) {
//			batch.setColor(0, 1, 0, 1);
//			for (final Enemy e : LIST) { 
////				batch.draw(AssetMan.star, xp.pos.x, xp.pos.y, xp.getHalfWidth(), xp.getHalfHeight(), xp.getWidth(), xp.getHeight(), 1, 1, xp.angle);
//				batch.draw(AssetMan.star, e.pos.x						, e.pos.y											, XP.HALF, XP.HALF, XP.WIDTH, XP.WIDTH_INF, 1, 1, f);
//				batch.draw(AssetMan.star, e.pos.x + e.getHalfWidth()	, e.pos.y - e.getHalfHeight()						, XP.HALF, XP.HALF, XP.WIDTH, XP.WIDTH_INF, 1, 1, f);
//				batch.draw(AssetMan.star, e.pos.x + e.getHalfWidth()	, e.pos.y + e.getHeight() + e.getHalfHeight()		, XP.HALF, XP.HALF, XP.WIDTH, XP.WIDTH_INF, 1, 1, f);
//				batch.draw(AssetMan.star, e.pos.x + e.getWidth()		, e.pos.y											, XP.HALF, XP.HALF, XP.WIDTH, XP.WIDTH_INF, 1, 1, f);
//				batch.draw(AssetMan.star, e.pos.x + e.getWidth()		, e.pos.y + e.getHeight()	, XP.HALF, XP.HALF, XP.WIDTH, XP.WIDTH_INF, 1, 1, f);
//				batch.draw(AssetMan.star, e.pos.x						, e.pos.y + e.getHeight()	, XP.HALF, XP.HALF, XP.WIDTH, XP.WIDTH_INF, 1, 1, f);
//				
//				batch.draw(AssetMan.star, e.pos.x						, e.pos.y											, XP.HALF, XP.HALF, XP.WIDTH, XP.WIDTH_INF, 1, 1, -f);
//				batch.draw(AssetMan.star, e.pos.x + e.getHalfWidth()	, e.pos.y - e.getHalfHeight()						, XP.HALF, XP.HALF, XP.WIDTH, XP.WIDTH_INF, 1, 1, -f);
//				batch.draw(AssetMan.star, e.pos.x + e.getHalfWidth()	, e.pos.y + e.getHeight() + e.getHalfHeight()		, XP.HALF, XP.HALF, XP.WIDTH, XP.WIDTH_INF, 1, 1, -f);
//				batch.draw(AssetMan.star, e.pos.x + e.getWidth()		, e.pos.y											, XP.HALF, XP.HALF, XP.WIDTH, XP.WIDTH_INF, 1, 1, -f);
//				batch.draw(AssetMan.star, e.pos.x + e.getWidth()		, e.pos.y + e.getHeight()	, XP.HALF, XP.HALF, XP.WIDTH, XP.WIDTH_INF, 1, 1, -f);
//				batch.draw(AssetMan.star, e.pos.x						, e.pos.y + e.getHeight()	, XP.HALF, XP.HALF, XP.WIDTH, XP.WIDTH_INF, 1, 1, -f);
//			}
//			f++;
//		} 
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
//	private static float pourcentage;
	private static float deltaMulImpact;
	public boolean stillAlive(PlayerWeapon a) {
		deltaMulImpact = EndlessMode.delta * Stats.IMPACT;
		pos.x += (a.dir.x * deltaMulImpact);
		pos.y += (a.dir.y * deltaMulImpact);
		Particles.addPartEnemyTouched(a, this);
		pv -= a.getPower();
//		pourcentage = getPvMax() / pv;
//		color = (int) (pvsColors.length / pourcentage);
		if (pv <= 0) {
			die();
			return false;
		}
		return true;
	}
	public boolean stillAliveEnemyWeapon(EnemyWeapon a) {
		deltaMulImpact = EndlessMode.delta * Stats.IMPACT;
		pos.x += (a.dir.x * deltaMulImpact);
		pos.y += (a.dir.y * deltaMulImpact);
		pv -= 30;
//		pourcentage = getPvMax() / pv;
//		color = (int) (pvsColors.length / pourcentage);
		if (pv <= 0) {
			die();
			return false;
		}
		return true;
	}

	public void die() {
		if (dead)
			return;
		Bonus.addBonus(this);
		explode();
		SoundMan.playBruitage(getSonExplosion());
//		SoundMan.playBruitage(getSonExplosion(), (pos.x + getHalfWidth()) - Player.xCenter);
		EndlessMode.screenShake(getXp());
		EndlessMode.explosions++;
		dead = true;
	}

	protected void explode() {
		Particles.explosion(this);
	}
	
	@Override
	public void reset() {
//		color = 0;
		pv = getPvMax();
		dead = false;
		angle = 90;
		super.reset();
	}

	public static void bombe() {
		CSG.google.unlockAchievementGPGS(Strings.ACH_BOMB);
		if (LIST.size >= 15) {
			CSG.google.unlockAchievementGPGS(Strings.ACH_15_ENEMY);
		}
		WaveEffect.add(Player.xCenter, Player.yCenter, AssetMan.convertARGB(1, 1f, 	(CSG.R.nextFloat() + .8f) / 1.6f, 	CSG.R.nextFloat()/8));
		WaveEffect.add(Player.xCenter, Player.yCenter, AssetMan.convertARGB(1, 1f, 	(CSG.R.nextFloat() + .8f) / 1.6f, 	CSG.R.nextFloat()/8));
		attackAllEnemies(bomb);
	}

	private static final BonusBombe mockBomb = new BonusBombe();
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
		for (XP xp : Bonus.XP_LIST)
			xp.state = XP.HOMMING;
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
	
	public static short getPvBoss(int pvBossMine) {		return (short) (pvBossMine + (pvBossMine *  (Player.weapon.nv() / 3) ));		}
	
	public boolean isTouched(Weapons a) {
		return a.getRectangleCollision().overlaps(getRectangleCollision());
	}
	
	@Override
	public void setPosition(Vector2 pos) {
		this.pos.y = pos.y;
		this.pos.x = pos.x - getHalfWidth();
	}
	
	protected TextureRegion getTexture() {				return AnimationCylon.getTexture(now);											}
	protected Sound getSonExplosion() {					return null;																		}
	public float getVitesse() {							return 3333;																		}
	public Vector2 getPosition() {						return pos;																			}
	public float getDirectionX() {						return 0;																			}
	protected float getAngle() {						return angle;																			}
	public boolean toLeft() {							return false;	}
	public void setLeft(boolean b) {							}
	public float getRotation() {		return 0;	}
	public void setRotation(float f) {			}
	protected void tir() {																													}
	public abstract int getXp();
	public abstract void free();
	protected abstract int getPvMax();
	protected abstract String getLabel();
	public abstract int getValeurBonus();
	public abstract float getDirectionY();
	public abstract int getExplosionCount();
	public abstract Behavior getBehavior();

	public static void deadBodiesEverywhere() {
		for (int i = 0; i < Enemy.LIST.size; i++) {
			if (Enemy.LIST.get(i).dead) {
				Enemy.LIST.get(i).free();
				Enemy.LIST.removeIndex(i);
			}
		}
	}
	
	public static final PlayerWeapon bomb = new PlayerWeapon() {
		@Override
		public int getWidth() {					return 0;		}
		@Override
		protected TextureRegion getTexture() {	return AssetMan.add;	}
		@Override
		public int getHeight() {				return 0;		}
		@Override
		public int getHalfWidth() {				return 0;		}
		@Override
		public int getHalfHeight() {			return 0;		}
		@Override
		public void free() {		}		
		@Override
		public float getColor() {				return 0;		}
		@Override
		public int getPower() { 				return 250;		}
	};
	
	public static final PlayerWeapon superBomb = new PlayerWeapon() {
		@Override
		public int getWidth() {					return 0;		}
		@Override
		protected TextureRegion getTexture() {	return AssetMan.add;	}
		@Override
		public int getHeight() {				return 0;		}
		@Override
		public int getHalfWidth() {				return 0;		}
		@Override
		public int getHalfHeight() {			return 0;		}
		@Override
		public void free() {		}		
		@Override
		public float getColor() {				return 0;		}
		@Override
		public int getPower() { 				return 380;		}
	};

	public void addAngle(float f) {
		angle += f;
	}

	public int getPhase() {
		return 0;
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
	
	protected static int initPv(int def, int pk) {
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
	
	protected static Behavior initBehavior(int pk, int def) {
		if (CSG.updateNeeded)
			return detectBehavior(Requests.getBehavior(pk, def));
		return detectBehavior(def);
	}

	private static Behavior detectBehavior(int b) {
		switch (b) {
		case Behavior.BALL : 			return new Ball();
		case Behavior.STRAIGHT : 		return new StraightOn();
		case Behavior.SLASH : 			return new Slash();
		case Behavior.TURN_AROUND :		return new TurnAround();
		case Behavior.KINDER :			return new KinderBehavior();
		case Behavior.SINK :			return new Sink();
		case Behavior.ROUND_N_ROUND :	return new RoundAndRound();
		case Behavior.UTURN :			return new Uturn();
		case Behavior.ZIGZAG :			return new ZigZag();
		case Behavior.HOMMING :			return new Homming();
		case Behavior.UMBRELLA :		return new Umbrella();
		}
		return new StraightOn();
	}
	protected static int getModulatedPv(int pv, int lvl) {
		switch (lvl) {
		case 3: return (int) (pv * Stats.PVNV3);
		case 4: return (int) (pv * Stats.PVNV4);
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
		switch (b) {
		case BlueBulletSlow.PK : 			return BlueBulletSlow.POOL.obtain();
		case Meteorite.PK : 				return Meteorite.POOL.obtain();
		case Tournante.PK : 				return Tournante.POOL.obtain();
		case InsectWeapon.PK : 				return InsectWeapon.POOL.obtain();
		case KinderWeapon.PK : 				return KinderWeapon.POOL.obtain();
		case LaserWeapon.PK : 				return LaserWeapon.POOL.obtain();
		case HalfSizeFireball.PK : 			return HalfSizeFireball.POOL.obtain();
		case Fireball.PK : 					return Fireball.POOL.obtain();
		case FragWeapon.PK : 				return FragWeapon.POOL.obtain();
		case BlueBullet.PK : 				return BlueBullet.POOL.obtain();
		case BlueBulletFast.PK : 			return BlueBulletFast.POOL.obtain();
		case VicousBullet.PK : 				return VicousBullet.POOL.obtain();
		}
		return null;
	}
}