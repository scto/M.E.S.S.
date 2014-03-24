package elements.generic.enemies.individual.lvl1;

import jeu.Stats;
import assets.SoundMan;
import assets.animation.AnimationInsecte;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.Invocable;
import elements.generic.behavior.Behavior;
import elements.generic.enemies.Enemy;
import elements.generic.weapons.enemies.EnemyWeapon;
import elements.generic.weapons.enemies.InsectWeapon;
import elements.generic.weapons.enemies.InvocableWeapon;
import elements.generic.weapons.patterns.Tireur;
import elements.generic.weapons.patterns.Tirs;
import elements.positionning.Middle;
import elements.positionning.Pos;

public class Insecte extends Enemy implements Tireur {
	
	public static final int LARGEUR = Stats.LARGEUR_INSECTE,  DEMI_LARGEUR = LARGEUR / 2;
	private static final float VITESSE = Stats.V_ENN_INSECTE;
	public static final Pool<Insecte> POOL = Pools.get(Insecte.class);
	public static final Invocable ref = new Insecte();
	protected static float tmp;
	
	public static final int PK = 5;
	protected static final float CADENCE = initFirerate(2, PK);
	protected static final Tirs tir = new Tirs(CADENCE);
	protected static final float INIT_NEXT_SHOT = initNextShot(1, PK);
	protected float prochainTir = INIT_NEXT_SHOT;
	protected static final float SPEED = initSpeed(6, PK);
	private static final int PV = initPv(Stats.PV_INSECTE, PK);
	private static final int EXPLOSION = initExplosion(50, PK);
	protected static final int BASE_XP = Enemy.initXp(86, PK);
	private static final int XP = getXp(BASE_XP, 1);
	private static final Behavior behavior = initBehavior(PK,Behavior.TURN_AROUND);
	private static final InvocableWeapon weapon = initEnemyWeapon(InsectWeapon.PK, PK);
	private static final Pos positionning = initPositionnement(Middle.PK, PK);
	
	protected void init() {
		prochainTir = INIT_NEXT_SHOT;
		angle = 90;
		dir.x = 0;
		dir.y = -VITESSE;
		dir.rotate(-20);
		positionning.set(this);
	}

	@Override
	public void mouvementEtVerif() {
		super.mouvementEtVerif();
		angle = dir.angle();
	}

	@Override
	public Vector2 getPositionDuTir(int numeroTir) {
		TMP_POS.x = pos.x + DEMI_LARGEUR;
		TMP_POS.y = pos.y + DEMI_LARGEUR;
		return TMP_POS;
	}
	
	@Override
	public Invocable invoquer() {
		Insecte l = POOL.obtain();
		LIST.add(l);
		l.init();
		return l;
	}

	@Override	public void setProchainTir(float f) {		prochainTir = f;	}
	@Override	protected void tir() {						tir.tirShotgun(this, now, prochainTir, 5, 5, angle-90);				}
	@Override	protected Sound getSonExplosion() {			return SoundMan.explosion5;											}
	@Override	protected TextureRegion getTexture() {		return AnimationInsecte.getTexture(pv);								}
	@Override	public int getXp() {						return XP;									}
	@Override	public int getValeurBonus() {				return BASE_XP;	}
	@Override	protected String getLabel() {				return getClass().toString();										}
	@Override	protected int getPvMax() {					return PV;											}
	@Override	public float getDirectionY() {				return dir.y;													}
	@Override	public float getDirectionX() {				return dir.x;													}
	@Override	public float getModifVitesse() {			return 1;																}
	@Override	public float getAngle() {					return angle;															}
	@Override	public void free() {						POOL.free(this);														}
	@Override	public int getHeight() {					return LARGEUR;														}
	@Override	public int getWidth() {						return LARGEUR;														}
	@Override	public int getHalfHeight() {				return DEMI_LARGEUR;													}
	@Override	public int getHalfWidth() {					return DEMI_LARGEUR;													}
	@Override	public EnemyWeapon getArme() {				return weapon.invoke();									}
	@Override	public float getVitesse() {					return SPEED;												}
	@Override	public int getExplosionCount() {			return EXPLOSION;									}
	@Override	public Behavior getBehavior() {				return behavior;	}
}
