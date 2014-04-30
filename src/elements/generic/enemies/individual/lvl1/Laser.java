package elements.generic.enemies.individual.lvl1;

import jeu.CSG;
import jeu.Stats;
import jeu.mode.EndlessMode;
import assets.SoundMan;
import assets.animation.AnimationEnnemiAileDeployee;

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
import elements.generic.weapons.enemies.LaserWeapon;
import elements.generic.weapons.patterns.TireurAngle;
import elements.generic.weapons.patterns.Tirs;


public class Laser extends Enemy implements TireurAngle {
	
	private static final int LARGEUR = Stats.LARGEUR_LASER, DEMI_LARGEUR = LARGEUR/2;
	public static final Pool<Laser> POOL = Pools.get(Laser.class);
	protected float rotation = 0;
	private boolean versGauche;
	public static final  Invocable ref = new Laser();
	public static final int PK = 7;
	protected static final float CADENCE = initFirerate(0.5f, PK);
	protected static final float INIT_NEXT_SHOT = initNextShot(1, PK);
	protected static final float SPEED = initSpeed(16, PK);
	private static final int PV = initPv(Stats.PV_LASER, PK);
	private static final int EXPLOSION = initExplosion(45, PK);
	protected static final int BASE_XP = Enemy.initXp(32, PK);
	private static final int XP = getXp(BASE_XP, 1);
	private static final Behavior behavior = initBehavior(PK,Behavior.SINK);
	protected static final Tirs tir = new Tirs(CADENCE);
	protected float prochainTir = INIT_NEXT_SHOT;
	private static final InvocableWeapon weapon = initEnemyWeapon(LaserWeapon.PK, PK);
	
	protected void init() {
		if (EndlessMode.alternate)
			pos.x = (CSG.gameZoneHalfWidth - DEMI_LARGEUR) - LARGEUR;
		else
			pos.x = (CSG.gameZoneHalfWidth - DEMI_LARGEUR) + LARGEUR;
		pos.y = CSG.SCREEN_HEIGHT;
		prochainTir = 1f;
		
		if (pos.x + DEMI_LARGEUR > CSG.gameZoneHalfWidth) {
			versGauche = true;
			dir.x = -1;
			angle = -135;
		} else {
			dir.x = 1;
			versGauche = false;
			angle = -45;
		}
		dir.y = -1;
		dir.scl(getVitesse());
	}
	
	@Override
	public Vector2 getPositionDuTir(int numeroTir) {
		TMP_POS.x = (pos.x + DEMI_LARGEUR - LaserWeapon.HALF_WIDTH) + (dir.x / 3);
		TMP_POS.y = (pos.y + DEMI_LARGEUR - LaserWeapon.HALF_WIDTH) + (dir.y / 3);
		return TMP_POS;
	}
	
	@Override
	public Invocable invoquer() {
		Laser l = POOL.obtain();
		LIST.add(l);
		l.init();
		return l;
	}
	
	@Override	protected TextureRegion getTexture() {	return AnimationEnnemiAileDeployee.getTexture(3);	}
	@Override	public float getAngle() {				return angle+90;	}
	@Override	protected void tir() {					tir.tirToutDroit(this, now, prochainTir);	}
	@Override	public int getXp() {					return XP;	}
	@Override	public int getValeurBonus() {			return BASE_XP;	}
	@Override	public int getHeight() {				return LARGEUR;	}
	@Override	protected int getPvMax() {				return PV;	}
	@Override	public int getWidth() {					return LARGEUR;	}
	@Override	public int getHalfHeight() {			return DEMI_LARGEUR;	}
	@Override	public float getVitesse() {				return SPEED;	}
	@Override	public int getHalfWidth() {				return DEMI_LARGEUR;	}
	@Override	public EnemyWeapon getArme() {			return weapon.invoke();	}
	@Override	public void setProchainTir(float f) {	prochainTir = f;	}
	@Override	public float getModifVitesse() {		return 0.010f;	}
	@Override	public float getAngleTir() {			return angle+90;	}
	@Override	public Vector2 getDirectionTir() {		return dir;	}
	@Override	public float getDirectionY() {			return dir.y;	}
	@Override	public float getDirectionX() {			return dir.x;	}
	@Override	protected Sound getSonExplosion() {		return SoundMan.explosion5;	}
	@Override	public void free() {					POOL.free(this);	}
	@Override	protected String getLabel() {			return getClass().toString();	}
	@Override	public int getExplosionCount() {		return EXPLOSION;									}
	@Override	public Behavior getBehavior() {			return behavior;	}
	@Override	public boolean toLeft() {				return versGauche;	}
	@Override	public float getRotation() {			return rotation;	}
	@Override	public void setRotation(float f) {		rotation = f;	}
}
