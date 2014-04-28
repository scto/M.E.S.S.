package elements.generic.enemies.individual.lvl1;

import jeu.Stats;
import assets.SoundMan;
import assets.animation.AnimationKinder;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.Invocable;
import elements.generic.behavior.Behavior;
import elements.generic.enemies.Enemy;
import elements.generic.weapons.enemies.EnemyWeapon;
import elements.generic.weapons.enemies.InvocableWeapon;
import elements.generic.weapons.enemies.KinderWeapon;
import elements.generic.weapons.patterns.TireurAngle;
import elements.generic.weapons.patterns.Tirs;
import elements.positionning.Pos;


public class Kinder extends Enemy implements TireurAngle {

	private static final int LARGEUR = Stats.LARGEUR_KINDER, DEMI_LARGEUR = LARGEUR/2, HAUTEUR = Stats.HAUTEUR_KINDER, DEMI_HAUTEUR = HAUTEUR / 2; 
	public static final Invocable ref = new Kinder();
	public static Pool<Kinder> pool = Pools.get(Kinder.class);
	
	public static final int PK = 6;
	protected static final int BASE_XP = Enemy.initXp(32, PK);
	protected static final float CADENCE = initFirerate(0.45f, PK), INIT_NEXT_SHOT = initNextShot(AnimationKinder.TIME_OUVERT, PK), SPEED = initSpeed(10, PK);
	private static final int PV = initPv(Stats.PV_KINDER, PK), EXPLOSION = initExplosion(45, PK), XP = getXp(BASE_XP, 1);
	private static final Behavior behavior = initBehavior(PK,Behavior.KINDER);
	protected static final Tirs tir = new Tirs(CADENCE);
	protected float prochainTir = INIT_NEXT_SHOT;
	private static final InvocableWeapon weapon = initEnemyWeapon(KinderWeapon.PK, PK);
	private static final Pos positionning = initPositionnement(elements.positionning.Kinder.PK, PK);
	
	protected void init() {
		positionning.set(this);
		angle = dir.angle();
		prochainTir = INIT_NEXT_SHOT;
	}
	
	@Override
	public Vector2 getPositionDuTir(int numeroTir) {
		TMP_POS.x = (pos.x + DEMI_LARGEUR - KinderWeapon.HALF_WIDTH);
		TMP_POS.y = (pos.y + DEMI_LARGEUR - KinderWeapon.HALF_WIDTH);
		return TMP_POS;
	}
	
	@Override
	public float getDirectionX() {
		if (now < AnimationKinder.TIME_OUVERT || now > 12) 
			return dir.x;
		return 0;
	}
	
	@Override
	public Invocable invoquer() {
		Kinder l = pool.obtain();
		LIST.add(l);
		l.init();
		return l;
	}
	
	protected Tirs getTir() {								return tir;	}
	@Override	public void setProchainTir(float f) {		prochainTir = f;	}
	@Override	public Vector2 getDirectionTir() {
		TMP_DIR.x = -dir.x;
		TMP_DIR.y = -dir.y;
		return TMP_DIR;	
	}
	@Override	protected void tir() {						getTir().tirToutDroit(this, now, prochainTir);	}
	@Override	protected TextureRegion getTexture() {		return AnimationKinder.getTexture(now);	}
	@Override	public int getXp() {						return XP;	}
	@Override	public int getValeurBonus() {				return BASE_XP;	}
	@Override	protected Sound getSonExplosion() {			return SoundMan.explosion6;	}
	@Override	public EnemyWeapon getArme() {				return weapon.invoke();	}
	@Override	protected String getLabel() {				return getClass().toString();	}
	@Override	protected int getPvMax() {					return PV;	}
	@Override	public int getHalfHeight() {				return DEMI_HAUTEUR;	}
	@Override	public int getHalfWidth() {					return DEMI_LARGEUR;	}
	@Override	public float getDirectionY() {				return dir.y;	}
	@Override	public void free() {						pool.free(this);	}
	@Override	public int getHeight() {					return HAUTEUR;	}
	@Override	public int getWidth() {						return LARGEUR;	}
	@Override	public float getAngleTir() {				return angle;	}
	@Override	public float getModifVitesse() {			return -0.008f;	}
	@Override	public void setPosition(Vector2 pos) {		super.setPosition(pos);	}
	@Override	public int getExplosionCount() {			return EXPLOSION;									}
	@Override	public Behavior getBehavior() {				return behavior;	}
	@Override	public float getVitesse() {					return SPEED;	}
}

