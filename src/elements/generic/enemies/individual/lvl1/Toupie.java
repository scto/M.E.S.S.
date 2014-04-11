package elements.generic.enemies.individual.lvl1;

import jeu.CSG;
import jeu.Stats;
import assets.SoundMan;
import assets.animation.AnimationEnnemiToupie;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.Invocable;
import elements.generic.behavior.Behavior;
import elements.generic.enemies.Enemy;
import elements.generic.weapons.enemies.BlueBulletFast;
import elements.generic.weapons.enemies.EnemyWeapon;
import elements.generic.weapons.enemies.InvocableWeapon;
import elements.generic.weapons.patterns.TireurBalayage;
import elements.generic.weapons.patterns.Tirs;
import elements.particular.particles.Particles;
import elements.positionning.Pos;
import elements.positionning.UpWide;

public class Toupie extends Enemy implements TireurBalayage {
	
	private static final int LARGEUR = Stats.LARGEUR_TOUPIE, DEMI_LARGEUR = LARGEUR/2, HAUTEUR = Stats.HAUTEUR_TOUPIE, DEMI_HAUTEUR = HAUTEUR / 2;
	
	public static final int PK = 13;

	private static final int LVL = 1;
	protected static final float CADENCE = initFirerate(.45f, PK);
	protected static final float INIT_NEXT_SHOT = initNextShot(0, PK);
	protected static final float SPEED = initSpeed(20, PK);
	private static final int PV = initPv(Stats.PV_TOUPIE, PK);
	private static final int EXPLOSION = initExplosion(30, PK);
	protected static final int BASE_XP = Enemy.initXp(21, PK);
	private static final int XP = getXp(BASE_XP, LVL);
	private static final Behavior behavior = initBehavior(PK, Behavior.UTURN);
	protected float prochainTir = INIT_NEXT_SHOT;
	protected static final Tirs tir = new Tirs(CADENCE);
	
	private static final float OFFSET_TIR = DEMI_LARGEUR - BlueBulletFast.HALF_WIDTH;
	private static int ecartTirs = 10;
	public static final Pool<Toupie> POOL = Pools.get(Toupie.class);
	public static final Invocable ref = new Toupie();
	private boolean tirVersDroite = true, aGaucheEcran;
	private int numeroTir = 0;
	private static final InvocableWeapon weapon = initEnemyWeapon(BlueBulletFast.PK, PK);
	private static final Pos positionning = initPositionnement(UpWide.PK, PK);
	
	private void determinerCote() {
		if (pos.x + DEMI_LARGEUR < CSG.gameZoneHalfWidth) aGaucheEcran = true;
		else aGaucheEcran = false;
	}

	protected void init() {
		angle = -90;
		dir.x = 0;
		dir.y = -getVitesse();
		positionning.set(this);
		prochainTir = INIT_NEXT_SHOT;
		now = 1;
	}

	@Override
	public Vector2 getPositionDuTir(int numeroTir) {
		if (now < 1.5f)
			determinerCote();
		TMP_POS.x = (pos.x + OFFSET_TIR);
		TMP_POS.y = (pos.y + OFFSET_TIR);
		return TMP_POS;
	}

	@Override
	public Invocable invoquer() {
		final Toupie t = POOL.obtain();
		LIST.add(t);
		t.init();
		return t;
	}
	
	@Override	protected void explode() {					Particles.explosionGreen(this);	}
	@Override	public float getVitesse() {					return SPEED;	}
	@Override	protected Sound getSonExplosion() {			return SoundMan.explosion6;	}
	@Override	protected TextureRegion getTexture() {		return AnimationEnnemiToupie.getTexture(now);	}
	@Override	protected void tir() {						tir.tirBalayage(this, now, prochainTir);	}
	@Override	public int getXp() {						return XP;	}
	@Override	public int getValeurBonus() {				return BASE_XP;	}
	@Override	public int getHeight() {					return HAUTEUR;	}
	@Override	public int getWidth() {						return LARGEUR;	}
	@Override	public int getHalfHeight() {				return DEMI_HAUTEUR;	}
	@Override	public int getHalfWidth() {					return DEMI_LARGEUR;	}
	@Override	public EnemyWeapon getArme() {				return weapon.invoke();	}
	@Override	public void setProchainTir(float f) {		prochainTir = f;	}
	@Override	public float getModifVitesse() {			return 0.007f;	}
	@Override	public float getAngleTir() {				return 0;	}
	@Override	public Vector2 getDirectionTir() {			return dir;	}
	@Override	public int getNumeroTir() {					return numeroTir;	}
	@Override	public int getNombreTirsAvantChangement() {	return 5;	}
	@Override	public float getEcartTirs() {				return ecartTirs;	}
	@Override	public void addNombresTirs(int i) {			numeroTir += i;	}
	@Override	public boolean getSens() {					return tirVersDroite;	}
	@Override	public void setSens(boolean b) {			tirVersDroite = b;	}
	@Override	protected int getPvMax() {					return PV;	}
	@Override	public float getDirectionY() {				return dir.y;	}
	@Override	public void free() {						POOL.free(this);	}
	@Override	public float getDirectionX() {				return dir.x;	}
	@Override	protected String getLabel() {				return getClass().toString();	}
	@Override	public int getExplosionCount() {			return EXPLOSION;									}
	@Override	public Behavior getBehavior() {				return behavior;		}
	@Override	public boolean toLeft() {					return aGaucheEcran;	}
}
