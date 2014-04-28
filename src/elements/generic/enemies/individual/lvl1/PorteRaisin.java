package elements.generic.enemies.individual.lvl1;

import jeu.EndlessMode;
import jeu.Stats;
import assets.AssetMan;
import assets.SoundMan;

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
import elements.generic.weapons.patterns.TireurPlusieurFois;
import elements.generic.weapons.patterns.Tirs;
import elements.generic.weapons.player.PlayerWeapon;
import elements.particular.particles.Particles;
import elements.positionning.Pos;
import elements.positionning.UpWide;

public class PorteRaisin extends Enemy implements TireurPlusieurFois {
	
	private static final int LARGEUR = Stats.LARGEUR_PORTE_RAISIN, DEMI_LARGEUR = LARGEUR/2, HAUTEUR = Stats.HAUTEUR_PORTE_RAISIN, DEMI_HAUTEUR = HAUTEUR / 2; 
	public static final Pool<PorteRaisin> POOL = Pools.get(PorteRaisin.class);
	public static final int PK = 9;
	protected static final float CADENCE = initFirerate(0.4f, PK);
	protected static final float INIT_NEXT_SHOT = initNextShot(3, PK);
	protected static final float SPEED = initSpeed(4, PK);
	private static final int PV = initPv(Stats.PV_PORTE_RAISIN, PK), HALF_HP = PV/2;
	private static final int EXPLOSION = initExplosion(40, PK);
	protected static final int BASE_XP = Enemy.initXp(91, PK);
	private static final int XP = getXp(BASE_XP, 1);
	private static final Behavior behavior = initBehavior(PK, Behavior.STRAIGHT);
	protected float prochainTir = INIT_NEXT_SHOT;
	protected static final Tirs tir = new Tirs(CADENCE);
	protected final Tirs TIR = new Tirs(CADENCE);
	public static final Invocable ref = new PorteRaisin();
	private static final float ROTATION_BETWEEN_SHOTS = 4;
	private float angleTir = 65;
	private int numeroTir = 3;
	private boolean goodShape;
	private static final InvocableWeapon weapon = initEnemyWeapon(KinderWeapon.PK, PK);
	private static final Pos positionning = initPositionnement(UpWide.PK, PK);

	protected void init() {
		positionning.set(this);
		prochainTir = INIT_NEXT_SHOT;
		angleTir = 180;
		goodShape = true;
		TIR.cadence = CADENCE;
		dir.x = 0;
		dir.y = -getVitesse();
		angle = -90;
	}

	@Override
	protected TextureRegion getTexture() {
		if (goodShape)	return AssetMan.blueDestroyer;
		return AssetMan.blueDestroyerBroken;
	}
	
	@Override
	public Vector2 getDirectionTir() {
		TMP_DIR.x = 0;
		TMP_DIR.y = -1;
		TMP_DIR.rotate(angleTir++);
		return TMP_DIR;
	}
	
	@Override
	public Vector2 getPositionDuTir(int numeroTir) {
		TMP_POS.x = (pos.x + DEMI_LARGEUR - KinderWeapon.HALF_WIDTH) - (TMP_DIR.x * Stats.U4);
		TMP_POS.y = (pos.y + DEMI_LARGEUR - KinderWeapon.HALF_WIDTH) - (TMP_DIR.y * Stats.U4);
		return TMP_POS;
	}
	
	@Override
	public void setProchainTir(float f) {
		angleTir += ROTATION_BETWEEN_SHOTS;
		prochainTir = f;
	}

	@Override
	public Invocable invoquer() {
		PorteRaisin pr = POOL.obtain();
		LIST.add(pr);
		pr.init();
		return pr;
	}
	
	@Override
	public boolean stillAlive(PlayerWeapon p) {
		if (pv <= HALF_HP && goodShape) {
			goodShape = false;
			getTir().cadence *= 0.65f;
		}
		return super.stillAlive(p);
	}
	
	@Override	protected void explode() {
		Particles.explosionBlue(this);
		Particles.explosionGreen(this);
	}
	@Override	public float getModifVitesse() {		return 1;	}
	@Override	public float getAngleTir() {			return angleTir;	}
	@Override	public int getNumeroTir() {				return numeroTir;	}
	@Override	public void addNombresTirs(int i) {		numeroTir += i;	}
	@Override	public float getDirectionY() {			return -SPEED;	}
	@Override	protected String getLabel() {			return getClass().toString();	}
	@Override	protected int getPvMax() {				return PV;	}
	@Override	protected Sound getSonExplosion() {		return SoundMan.explosion6;	}
	@Override	public void free() {					POOL.free(this);	}
	@Override	protected void tir() {					TIR.tirEnRafaleGaucheEtDroite(this, 3 + EndlessMode.R.nextInt(3), now, prochainTir);	}
	@Override	public int getXp() {					return XP;	}
	@Override	public int getValeurBonus() {			return BASE_XP;	}
	@Override	public int getHeight() {				return HAUTEUR;	}
	@Override	public int getWidth() {					return LARGEUR;	}
	@Override	public int getHalfHeight() {			return DEMI_HAUTEUR;	}
	@Override	public int getHalfWidth() {				return DEMI_LARGEUR;	}
	@Override	public EnemyWeapon getArme() {			return weapon.invoke();	}
	@Override	public float getVitesse() {				return SPEED;	}
	@Override	public int getExplosionCount() {		return EXPLOSION;									}
	@Override	public Behavior getBehavior() {			return behavior;		}
	protected Tirs getTir() {							return TIR;								}
	protected int getPallierPv() {						return HALF_HP;	}
}
