package elements.generic.enemies.individual.bosses;

import jeu.EndlessMode;
import jeu.Physic;
import jeu.Stats;
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
import elements.generic.weapons.enemies.BlueBulletFast;
import elements.generic.weapons.enemies.EnemyWeapon;
import elements.generic.weapons.enemies.InvocableWeapon;
import elements.generic.weapons.patterns.TireurAngle;
import elements.generic.weapons.patterns.Tirs;


public class AddBossStat extends Enemy implements TireurAngle {
	
	static final float LARGEUR = Stats.LARGEUR_ADD_BOSS_SAT;
	static final float DEMI_LARGEUR = LARGEUR / 2;
	public static Pool<AddBossStat> pool = Pools.get(AddBossStat.class);
	private static final float OFFSET_TIR = DEMI_LARGEUR - BlueBulletFast.HALF_WIDTH;
	
	public static final int PK = 16;
	private static final int LVL = 1;
	protected static final float CADENCE = initFirerate(1.2f, PK);
	protected static final float INIT_NEXT_SHOT = initNextShot(0, PK);
	protected static final float SPEED = initSpeed(10, PK);
	private static final int PV = initPv(25, PK);
	private static final int EXPLOSION = initExplosion(15, PK);
	protected static final int BASE_XP = Enemy.initXp(25, PK);
	private static final int XP = getXp(BASE_XP, LVL);
	private static final Behavior behavior = initBehavior(PK, Behavior.HOMMING);
	protected float prochainTir = INIT_NEXT_SHOT;
	protected static final Tirs tir = new Tirs(CADENCE);
	private static final InvocableWeapon weapon = initEnemyWeapon(BlueBulletFast.PK, PK);
	public static final Invocable ref = new AddBossStat();

	@Override
	public void reset() {
		super.reset();
		prochainTir = INIT_NEXT_SHOT;
		pos.x = 200;
		pos.y = 200;
		dir.x = 0;
		dir.y = getVitesse();
	}
	
	@Override
	public void mouvementEtVerif() {
		if (EndlessMode.alternate) 
			Physic.mvtNoCheck(pos, dir);
		else
			angle = Physic.mvtToPlayerWithAngle(dir, pos, getVitesse(), (int)LARGEUR, (int)DEMI_LARGEUR);
		super.mouvementEtVerif();
	}

	public void lancer(float dirX, float dirY, float x, float y, float angle) {
		dir.x = dirX;
		dir.y = dirY;
		pos.x = x;
		pos.y = y;
		this.angle = angle;
		LIST.add(this);
	}

	@Override
	public Vector2 getPositionDuTir(int numeroTir) {
		TMP_POS.x = (pos.x + OFFSET_TIR) + (dir.x * 16);
		TMP_POS.y = (pos.y + OFFSET_TIR - BlueBulletFast.HALF_WIDTH)+ (dir.y * 16);
		return TMP_POS;
	}
	
	@Override
	public Invocable invoquer() {
		final AddBossStat l = pool.obtain();
		LIST.add(l);
		return l;
	}
	
	@Override	protected Sound getSonExplosion() {			return SoundMan.explosion2;	}
	@Override	public void free() {						pool.free(this);		}
	@Override	protected int getPvMax() {					return PV;	}
	@Override	protected TextureRegion getTexture() {		return AnimationEnnemiAileDeployee.getTexture(now);	}
	@Override	protected float getAngle() {				return angle + 90;	}
	@Override	protected void tir() {						tir.tirToutDroit(this, now, prochainTir);	}
	@Override	public int getXp() {						return XP;	}
	public void setAngle(int i) {							this.angle = i;	}
	@Override	public int getHeight() {					return (int)LARGEUR;	}
	@Override	public int getWidth() {						return (int)LARGEUR;	}
	@Override	public int getHalfHeight() {				return (int)DEMI_LARGEUR;	}
	@Override	public int getHalfWidth() {					return (int)DEMI_LARGEUR;	}
	@Override	public EnemyWeapon getArme() {				return weapon.invoke();	}
	@Override	public void setProchainTir(float f) {		prochainTir = f;	}
	@Override	public float getModifVitesse() {			return 1;	}
	@Override	public float getAngleTir() {				return angle;	}
	@Override	public Vector2 getDirectionTir() {			return dir;	}
	@Override	public float getDirectionY() {				return dir.y;	}
	@Override	public float getDirectionX() {				return dir.x;	}
	@Override	protected String getLabel() {				return getClass().toString();	}
	@Override	public int getValeurBonus() {				return BASE_XP;			}
	@Override	public int getExplosionCount() {			return EXPLOSION;									}
	@Override	public Behavior getBehavior() {				return behavior;	}
	@Override	public float getVitesse() {					return SPEED;	}
}
