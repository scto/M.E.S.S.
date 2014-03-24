package elements.generic.enemies.individual.lvl1;

import jeu.CSG;
import jeu.Stats;
import assets.SoundMan;
import assets.animation.AnimationEnnemiTourne;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.Invocable;
import elements.generic.behavior.Behavior;
import elements.generic.enemies.Enemy;
import elements.generic.weapons.enemies.BlueBullet;
import elements.generic.weapons.enemies.EnemyWeapon;
import elements.generic.weapons.enemies.InvocableWeapon;
import elements.generic.weapons.patterns.TireurPlusieurFois;
import elements.generic.weapons.patterns.Tirs;
import elements.positionning.Pos;
import elements.positionning.UpWide;

public class QuiTourne extends Enemy implements TireurPlusieurFois {
	
	private static final int LARGEUR = Stats.LARGEUR_QUI_TOURNE, DEMI_LARGEUR = LARGEUR/2;
	private static final float CADENCE_TIR = .1f;
	private static final Tirs TIR = new Tirs(CADENCE_TIR);
	public static final Invocable ref = new QuiTourne();
	public static final Pool<QuiTourne> POOL = Pools.get(QuiTourne.class);
	private int numeroTir;
	
	public static final int PK = 12;
	protected static final float CADENCE = initFirerate(1.2f, PK);
	protected static final float INIT_NEXT_SHOT = initNextShot(3, PK);
	protected static final float SPEED = initSpeed(26, PK);
	private static final int PV = initPv(Stats.PV_QUI_TOURNE, PK);
	private static final int EXPLOSION = initExplosion(25, PK);
	protected static final int BASE_XP = Enemy.initXp(19, PK);
	private static final int XP = getXp(BASE_XP, 1);
	private static final Behavior behavior = initBehavior(PK, Behavior.ROUND_N_ROUND);
	protected float prochainTir = INIT_NEXT_SHOT;
	protected static final Tirs tir = new Tirs(CADENCE);
	private static final InvocableWeapon weapon = initEnemyWeapon(BlueBullet.PK, PK);
	private static final Pos positionning = initPositionnement(UpWide.PK, PK);

	protected void init() {
		positionning.set(this);
		if (pos.x > CSG.gameZoneHalfWidth)
			pos.x = CSG.gameZoneHalfWidth;
		dir.x = 0;
		dir.y = -getVitesse();
		prochainTir = INIT_NEXT_SHOT;
	}
	
	@Override
	public Vector2 getPositionDuTir(int numeroTir) {
		TMP_POS.x = (pos.x + DEMI_LARGEUR - BlueBullet.HALF_WIDTH);
		TMP_POS.y = (pos.y + DEMI_LARGEUR - BlueBullet.HALF_WIDTH);
		return TMP_POS;
	}
	
	@Override
	public Invocable invoquer() {	
		final QuiTourne q = POOL.obtain();
		LIST.add(q);
		q.init();
		return q;
	}

	@Override	public float getVitesse() {				return SPEED;					}
	@Override	protected Sound getSonExplosion() {		return SoundMan.explosion1;						}
	@Override	protected TextureRegion getTexture() {	return AnimationEnnemiTourne.getTexture(now);	}
	@Override	protected int getPvMax() {				return PV;						}
	@Override	public void free() {					POOL.free(this);								}
	@Override	protected void tir() {					TIR.tirEnRafale(this, 4, now, prochainTir);		}
	@Override	public int getXp() {					return XP;				}
	@Override	public int getValeurBonus() {			return BASE_XP;		}
	@Override	public int getHeight() {				return LARGEUR;									}
	@Override	public int getWidth() {					return LARGEUR;									}
	@Override	public int getHalfHeight() {			return DEMI_LARGEUR;							}
	@Override	public int getHalfWidth() {				return DEMI_LARGEUR;							}
	@Override	public EnemyWeapon getArme() {			return BlueBullet.POOL.obtain();				}
	@Override	public void setProchainTir(float f) {	prochainTir = f;								}
	@Override	public float getModifVitesse() {		return 0.005f;									}
	@Override	public float getAngleTir() {			return 0;										}
	@Override	public Vector2 getDirectionTir() {		return dir;										}
	@Override	public int getNumeroTir() {				return numeroTir;								}
	@Override	public void addNombresTirs(int i) {		numeroTir += i;									}
	@Override	public float getDirectionY() {			return dir.y;									}
	@Override	public float getDirectionX() {			return dir.x;									}
	@Override	protected String getLabel() {			return getClass().toString();					}
	@Override	protected float getAngle() {			return angle;									}
	protected float getDemiVitesse() {					return Stats.DEMI_V_ENN_QUI_TOURNE;				}
	@Override	public int getExplosionCount() {		return EXPLOSION;										}
	@Override	public Behavior getBehavior() {			return behavior;								}
}
