package elements.generic.enemies.individual.lvl1;

import jeu.CSG;
import jeu.EndlessMode;
import jeu.Stats;
import assets.AssetMan;
import assets.SoundMan;
import assets.animation.AnimationEnnemiTourne;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;

import elements.generic.Invocable;
import elements.generic.behavior.Behavior;
import elements.generic.enemies.Enemy;
import elements.generic.weapons.enemies.EnemyWeapon;
import elements.generic.weapons.enemies.InvocableWeapon;
import elements.generic.weapons.enemies.Tournante;
import elements.generic.weapons.patterns.TireurPlusieurFois;
import elements.generic.weapons.patterns.Tirs;
import elements.positionning.Pos;
import elements.positionning.UpWide;

public class Group extends Enemy implements Poolable, Invocable, TireurPlusieurFois {
	
	protected static final int LARGEUR = Stats.LARGEUR_GROUP, DEMI_LARGEUR = LARGEUR / 2;
	public static final Pool<Group> POOL = new Pool<Group>() {
		@Override
		protected Group newObject() {
			return new Group();
		}
	};
	public static final Invocable ref = new Group();
	private static final Vector2 tmp = new Vector2();
	private int numeroTir = 0;
	protected float prochainTir;
	
	public static final int PK = 4;
	protected static final float CADENCE = initFirerate(1, PK);
	protected static final float INIT_NEXT_SHOT = initNextShot(.5f, PK);
	public static final Tirs TIR = new Tirs(CADENCE);
	private static final float COLOR = AssetMan.convertARGB(1, 1f, 0.5f, 0.2f);
	protected static final float SPEED = initSpeed(8, PK);
	private static final int PV = initPv(Stats.PV_CYLON, PK);
	private static final int EXPLOSION = initExplosion(35, PK);
	protected static final int BASE_XP = Enemy.initXp(12, PK);
	private static final int XP = getXp(BASE_XP, 1);
	private static final Behavior behavior = initBehavior(PK,Behavior.SLASH);
	private static final InvocableWeapon weapon = initEnemyWeapon(Tournante.PK, PK);
	
	@Override
	public void afficher(SpriteBatch batch) {
		if (EndlessMode.alternate)
			angle = dir.angle();
		batch.setColor(COLOR);
		super.afficher(batch);
		batch.setColor(AssetMan.WHITE);
	}
	
	@Override
	public Invocable invoquer() {
		Group e = POOL.obtain();
		Group f = POOL.obtain();
		Group g = POOL.obtain();
		init(e, CSG.gameZoneWidth);
		init(f, CSG.gameZoneWidth - LARGEUR * 1.5f);
		init(g, CSG.gameZoneWidth - LARGEUR * 3);
		return e;
	}
	protected void init(Group e, float x) {
		e.now = 5;
		LIST.add(e);
		e.pos.x = x;
		e.pos.y = CSG.SCREEN_HEIGHT;
		e.dir.x = 0;
		e.dir.y = -getVitesse();
		angle = -90;
	}

	@Override
	public void reset() {
		super.reset();
		pos.y = CSG.SCREEN_HEIGHT;
		prochainTir = INIT_NEXT_SHOT;
		numeroTir = 0;
	}
	@Override
	public Vector2 getDirectionTir() {
		tmp.y = (Stats.V_ENN_DE_BASE);
		tmp.x = (now * 15);
		return tmp;
	}
	@Override	protected float getAngle() {							return tmp.angle()-90;					}
	@Override	protected Sound getSonExplosion() {						return SoundMan.explosion6;	}
	@Override	public int getXp() {									return XP;	}
	@Override	public int getValeurBonus() {							return BASE_XP;	}
	@Override	public int getHeight() {								return LARGEUR;	}
	@Override	public int getWidth() {									return LARGEUR;	}
	@Override	public int getHalfHeight() {							return DEMI_LARGEUR;	}
	@Override	public int getHalfWidth() {								return DEMI_LARGEUR;	}
	@Override	protected int getPvMax() {								return PV;	}
	@Override	protected TextureRegion getTexture() {					return AnimationEnnemiTourne.getTexture(now);	}
	@Override	public void free() {									POOL.free(this);	}
	@Override	public float getDirectionY() {							return -SPEED;	}
	@Override	protected String getLabel() {							return getClass().toString();	}
	@Override	public float getVitesse() {								return SPEED;	}
	@Override	protected void tir() {									TIR.tirEnRafale(this, 2, now, prochainTir);	}
	@Override	public float getAngleTir() {							return 0;			}
	@Override	public EnemyWeapon getArme() {							return weapon.invoke();	}
	@Override	public Vector2 getPositionDuTir(int numeroTir) {		return pos;										}
	@Override	public float getModifVitesse() {						return -0.012f;				}
	@Override	public void setProchainTir(float f) {					this.prochainTir = f;	}
	@Override	public int getNumeroTir() {								return numeroTir;		}
	@Override	public void addNombresTirs(int i) {						numeroTir += i;			}
	@Override	public int getExplosionCount() {						return EXPLOSION;									}
	@Override	public Behavior getBehavior() {							return behavior;	}
}
