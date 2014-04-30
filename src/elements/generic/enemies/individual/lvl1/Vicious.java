package elements.generic.enemies.individual.lvl1;

import jeu.Stats;
import jeu.mode.EndlessMode;
import assets.AssetMan;
import assets.SoundMan;
import assets.animation.AnimationInsecte;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.Invocable;
import elements.generic.behavior.Behavior;
import elements.generic.enemies.Enemy;
import elements.generic.weapons.enemies.EnemyWeapon;
import elements.generic.weapons.enemies.InvocableWeapon;
import elements.generic.weapons.enemies.VicousBullet;
import elements.generic.weapons.patterns.Tireur;
import elements.generic.weapons.patterns.Tirs;
import elements.positionning.Middle;
import elements.positionning.Pos;

public class Vicious extends Enemy implements Tireur {
	
	public static final int LARGEUR = (int) (Stats.LARGEUR_INSECTE * 0.75f),  DEMI_LARGEUR = LARGEUR / 2;
	
	public static final int PK = 14;
	protected static final float CADENCE = initFirerate(2, PK);
	protected static final float INIT_NEXT_SHOT = initNextShot(1, PK);
	protected static final float SPEED = initSpeed(8, PK);
	private static final int EXPLOSION = initExplosion(50, PK);
	private static final Behavior behavior = initBehavior(PK, Behavior.STRAIGHT);
	protected float prochainTir = INIT_NEXT_SHOT;
	protected static final Tirs tir = new Tirs(CADENCE);
	
	protected static float tmp;
	public static final Pool<Vicious> POOL = Pools.get(Vicious.class);
	public static final Invocable ref = new Vicious();
	private static final InvocableWeapon weapon = initEnemyWeapon(VicousBullet.PK, PK);
	private static final Pos positionning = initPositionnement(Middle.PK, PK);
	
	private int xp, pv;
	
	@Override
	public void afficher(SpriteBatch batch) {
		batch.setColor(VicousBullet.COLOR);
		super.afficher(batch);
		batch.setColor(AssetMan.WHITE);
	}

	private void init() {
		prochainTir = INIT_NEXT_SHOT;
		angle = 90;
		dir.x = 0;
		dir.y = -getVitesse();
		positionning.set(this);
		xp = (int) (EndlessMode.score / 80f);
		pv = xp / 2 ;
	}

	@Override
	public Vector2 getPositionDuTir(int numeroTir) {
		dir.y *= 1.1f;
		TMP_POS.x = pos.x + DEMI_LARGEUR;
		TMP_POS.y = pos.y + DEMI_LARGEUR;
		return TMP_POS;
	}
	
	@Override
	public Invocable invoquer() {
		final Vicious l = POOL.obtain();
		LIST.add(l);
		l.init();
		return l;
	}

	@Override	protected void tir() {						tir.tirShotgun(this, now, prochainTir, 2, 2, 0);					}
	@Override	public void setProchainTir(float f) {		prochainTir = f;													}
	@Override	protected Sound getSonExplosion() {			return SoundMan.explosion5;											}
	@Override	protected TextureRegion getTexture() {		return AnimationInsecte.getTexture(pv);								}
	@Override	public int getXp() {						return xp;															}
	@Override	public int getValeurBonus() {				return xp;															}
	@Override	protected String getLabel() {				return getClass().toString();										}
	@Override	protected int getPvMax() {					return pv;															}
	@Override	public float getModifVitesse() {			return 1;															}
	@Override	public float getAngle() {					return angle;														}
	@Override	public void free() {						POOL.free(this);													}
	@Override	public int getHeight() {					return LARGEUR;														}
	@Override	public int getWidth() {						return LARGEUR;														}
	@Override	public int getHalfHeight() {				return DEMI_LARGEUR;												}
	@Override	public int getHalfWidth() {					return DEMI_LARGEUR;												}
	@Override	public EnemyWeapon getArme() {				return weapon.invoke();									}
	@Override	public float getVitesse() {					return SPEED;												}
	@Override	public float getDirectionY() {				return -1;															}
	@Override	public int getExplosionCount() {			return EXPLOSION;									}
	@Override	public Behavior getBehavior() {				return behavior;													}
}
