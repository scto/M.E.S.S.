package elements.generic.enemies.individual.lvl1;

import jeu.Stats;
import assets.SoundMan;
import assets.animation.AnimationQuiTir;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.Invocable;
import elements.generic.behavior.Behavior;
import elements.generic.enemies.Enemy;
import elements.generic.weapons.enemies.EnemyWeapon;
import elements.generic.weapons.enemies.Fireball;
import elements.generic.weapons.enemies.InvocableWeapon;
import elements.generic.weapons.patterns.Tireur;
import elements.generic.weapons.patterns.Tirs;
import elements.generic.weapons.player.PlayerWeapon;
import elements.positionning.Pos;
import elements.positionning.UpWide;


public class QuiTir extends Enemy implements Tireur{
	
	private static final int LARGEUR = Stats.LARGEUR_QUI_TIR, DEMI_LARGEUR = LARGEUR/2, HAUTEUR = Stats.HAUTEUR_QUI_TIR, DEMI_HAUTEUR = HAUTEUR / 2;
	public static final Pool<QuiTir> POOL = Pools.get(QuiTir.class);
	public static final Invocable ref = POOL.obtain();
	private static final float xOffset = DEMI_LARGEUR - Fireball.HALF_WIDTH;
	
	public static final int PK = 10;
	protected static final float CADENCE = initFirerate(1.2f, PK);
	protected static final float INIT_NEXT_SHOT = initNextShot(1.5f, PK);
	protected static final float SPEED = initSpeed(10, PK);
	private static final int PV = initPv(Stats.PV_QUI_TIR, PK), HALF_HP = PV/2;
	private static final int EXPLOSION = initExplosion(40, PK);
	protected static final int BASE_XP = Enemy.initXp(10, PK);
	private static final int XP = getXp(BASE_XP, 1);
	private static final Behavior behavior = initBehavior(PK, Behavior.STRAIGHT);
	protected float prochainTir = INIT_NEXT_SHOT;
	protected static final Tirs tir = new Tirs(CADENCE);
	private static final InvocableWeapon weapon = initEnemyWeapon(Fireball.PK, PK);
	private static final Pos positionning = initPositionnement(UpWide.PK, PK);
	
	protected void init() {
		positionning.set(this);
		prochainTir = 2f;
		dir.x = 0;
		dir.y = -getVitesse();
		angle = -90;
	}
	
	@Override
	public Vector2 getPositionDuTir(int numeroTir) {
		TMP_POS.x = pos.x + xOffset;
		TMP_POS.y = pos.y - Fireball.HEIGHT;
		return TMP_POS;
	}

	@Override
	public float getDirectionX() {
		if (pv < getDemiPv()) 
			return getDerive();
		else return 0;
	}
	
	@Override
	public Invocable invoquer() {
		final QuiTir qt = POOL.obtain();
		LIST.add(qt);
		qt.init();
		return qt;
	}
	
	@Override
	public TextureRegion getTexture() {
		if (pv > getDemiPv())
			return AnimationQuiTir.goodShape;
		return AnimationQuiTir.badShape;	
	}
	
	@Override
	public boolean stillAlive(PlayerWeapon a) {
		if (pv < getDemiPv())
			dir.rotate(2);
		return super.stillAlive(a);
	}
	
	@Override	protected String getLabel() {			return getClass().toString();	}
	@Override	public void free() {					POOL.free(this);	}
	@Override	protected Sound getSonExplosion() {		return SoundMan.explosion5;	}
	@Override	public float getDirectionY() {			return getVitesse();	}
	@Override	public float getVitesse() {				return SPEED;	}
	@Override	protected int getPvMax() {				return PV;	}
	@Override	protected void tir() {					tir.tirVersBas(this, now, prochainTir);	}
	@Override	public int getXp() {					return XP;	}
	@Override	public int getValeurBonus() {			return BASE_XP;	}
	@Override	public int getHeight() {				return HAUTEUR;	}
	@Override	public int getWidth() {					return LARGEUR;	}
	@Override	public int getHalfHeight() {			return DEMI_HAUTEUR;	}
	@Override	public int getHalfWidth() {				return DEMI_LARGEUR;	}
	@Override	public EnemyWeapon getArme() {			return weapon.invoke();	}
	@Override	public void setProchainTir(float f) {	prochainTir = f;	}
	@Override	public float getModifVitesse() {		return 1;	}
	@Override	public int getExplosionCount() {		return EXPLOSION;									}
	@Override	public Behavior getBehavior() {			return behavior;	}
	protected float getDerive() {						return Stats.DERIVE_QUI_TIR;	}
	protected int getDemiPv() {							return HALF_HP;	}
}
