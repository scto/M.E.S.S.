package elements.generic.enemies.individual.lvl1;

import jeu.Stats;
import assets.AssetMan;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.Invocable;
import elements.generic.enemies.Enemy;
import elements.generic.weapons.enemies.EnemyWeapon;
import elements.generic.weapons.enemies.FragWeapon;
import elements.generic.weapons.enemies.InvocableWeapon;
import elements.generic.weapons.patterns.Tireur;
import elements.generic.weapons.patterns.Tirs;


public class QuiTirTriangle extends QuiTir implements Tireur {
	
	private static final int LARGEUR = Stats.LARGEUR_QUI_TIR2, DEMI_LARGEUR = LARGEUR/2, HAUTEUR = Stats.HAUTEUR_QUI_TIR2, DEMI_HAUTEUR = HAUTEUR / 2; 
	public static final Pool<QuiTirTriangle> POOL = Pools.get(QuiTirTriangle.class);
	public static final Invocable ref = new QuiTirTriangle();
	private static final float COLOR = AssetMan.convertARGB(1, 1, 1, .7f);
	private static final float xOffset = DEMI_LARGEUR - FragWeapon.HALF_WIDTH;
	
	public static final int PK = 11;
	protected static final float CADENCE = initFirerate(0.7f, PK);
	protected static final float SPEED = initSpeed(12, PK);
	private static final int PV = initPv(Stats.PV_DE_BASE_QUI_TIR_TRIANGLE, PK), HALF_HP = PV/2;
	private static final int EXPLOSION = initExplosion(50, PK);
	protected static final int BASE_XP = Enemy.initXp(39, PK);
	private static final int XP = getXp(BASE_XP, 1);
	protected static final Tirs tir = new Tirs(CADENCE);
	private static final InvocableWeapon weapon = initEnemyWeapon(FragWeapon.PK, PK);

	@Override
	public void afficher(SpriteBatch batch) {
		batch.setColor(COLOR);
		super.afficher(batch);
		batch.setColor(AssetMan.WHITE);
	}
	
	@Override
	public Vector2 getPositionDuTir(int numeroTir) {
		TMP_POS.x = pos.x + xOffset;
		TMP_POS.y = pos.y - FragWeapon.WIDTH;
		return TMP_POS;
	}
	
	@Override
	public Invocable invoquer() {
		final QuiTirTriangle qt = POOL.obtain();
		LIST.add(qt);
		qt.init();
		return qt;
	}
	
	@Override	protected String getLabel() {				return getClass().toString();	}
	@Override	public void free() {						POOL.free(this);	}
	@Override	protected int getPvMax() {					return PV;	}
	@Override	protected int getDemiPv() {					return HALF_HP;}
	@Override	public float getVitesse() {					return SPEED;	}
	@Override	protected float getDerive() {				return Stats.DERIVE_DE_BASE_QUI_TIR2;	};
	@Override	protected void tir() {						tir.tirVersBas(this, now, prochainTir);	}
	@Override	public int getXp() {						return XP;	}
	@Override	public int getValeurBonus() {				return BASE_XP;	}
	@Override	public int getHeight() {					return HAUTEUR;	}
	@Override	public int getWidth() {						return LARGEUR;	}
	@Override	public int getHalfHeight() {				return DEMI_HAUTEUR;	}
	@Override	public int getHalfWidth() {					return DEMI_LARGEUR;	}
	@Override	public EnemyWeapon getArme() {				return weapon.invoke();	}
	@Override	public void setProchainTir(float f) {		prochainTir = f;	}
	@Override	public float getModifVitesse() {			return 1;	}
	@Override	public int getExplosionCount() {			return EXPLOSION;									}
}
