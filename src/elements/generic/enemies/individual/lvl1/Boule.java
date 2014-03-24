package elements.generic.enemies.individual.lvl1;

import jeu.EndlessMode;
import jeu.Physic;
import jeu.Stats;
import assets.SoundMan;
import assets.animation.AnimBall;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.Invocable;
import elements.generic.behavior.Behavior;
import elements.generic.enemies.Enemy;
import elements.generic.weapons.enemies.BlueBulletFast;
import elements.generic.weapons.enemies.BlueBulletSlow;
import elements.generic.weapons.enemies.EnemyWeapon;
import elements.generic.weapons.enemies.InvocableWeapon;
import elements.generic.weapons.patterns.Tireur;
import elements.generic.weapons.patterns.Tirs;
import elements.generic.weapons.player.PlayerWeapon;
import elements.positionning.Pos;
import elements.positionning.UpWide;

public class Boule extends Enemy implements Tireur {
	
	private static final int LARGEUR = Stats.LARGEUR_BOULE, DEMI_LARGEUR = LARGEUR/2;
	
	public static final int PK = 1;
	protected static final float CADENCETIR = initFirerate(5.6f, PK);
	protected static final float INIT_NEXT_SHOT = initNextShot(.1f, PK);
	protected static final float SPEED = initSpeed(40, PK);
	private static final int PV = initPv(Stats.PV_BOULE, PK);
	private static final int EXPLOSION = initExplosion(35, PK);
	protected static final int BASE_XP = Enemy.initXp(12, PK);
	private static final int XP = getXp(BASE_XP, PK);
	private static final Behavior behavior = initBehavior(PK, Behavior.BALL);
	protected static final Tirs tir = new Tirs(CADENCETIR);
	protected float prochainTir = INIT_NEXT_SHOT;
	public static final Pool<Boule> POOL = Pools.get(Boule.class);
	public static final InvocableWeapon weapon = initEnemyWeapon(BlueBulletSlow.PK, PK);
	public static final Invocable ref = new Boule();
	private static final Pos positionning = initPositionnement(UpWide.PK, PK);
	@Override
	public Vector2 getPositionDuTir(int numeroTir) {
		TMP_POS.x = (pos.x + DEMI_LARGEUR - BlueBulletFast.HALF_WIDTH);
		TMP_POS.y = (pos.y + DEMI_LARGEUR - BlueBulletFast.HALF_WIDTH);
		return TMP_POS;
	}

	@Override
	public Invocable invoquer() {
		final Boule l = POOL.obtain();
		LIST.add(l);
		l.init();
		return l;
	}
	protected void init() {
		prochainTir = .2f;
		positionning.set(this);
		dir.x = 0;
		dir.y = -getVitesse();
	}

	@Override
	protected float getAngle() {
		if (!EndlessMode.alternate && !EndlessMode.triggerStop)
			angle = Physic.getAngleWithPlayer(pos, DEMI_LARGEUR, DEMI_LARGEUR) + 90;
		return angle;
	}
	
	@Override	public float getDirectionY() {				return dir.y;	}
	@Override	public float getDirectionX() {				return dir.x;	}
	@Override	public void free() {						POOL.free(this);	}
	@Override	protected TextureRegion getTexture() {		return AnimBall.getTexture(now);	}
	@Override	protected void tir() {						tir.tirVersJoueur(this, now, prochainTir);	}
	@Override	protected Sound getSonExplosion() {			return SoundMan.explosion3;	}
	@Override	protected int getPvMax() {					return PV;	}
	@Override	public int getXp() {						return XP;	}
	@Override	public int getValeurBonus() {				return BASE_XP;	}
	@Override	public int getHeight() {					return LARGEUR;	}
	@Override	public int getWidth() {						return LARGEUR;	}
	@Override	public int getHalfHeight() {				return DEMI_LARGEUR;	}
	@Override	public int getHalfWidth() {					return DEMI_LARGEUR;	}
	@Override	public float getVitesse() {					return SPEED;	}
	@Override	public EnemyWeapon getArme() {				return weapon.invoke();	}
	@Override	public void setProchainTir(float f) {		prochainTir = f;	}
	@Override	public float getModifVitesse() {			return 1;	}
	@Override	protected String getLabel() {				return getClass().toString();	}
	@Override	public int getExplosionCount() {			return EXPLOSION;									}
	@Override	public Behavior getBehavior() {				return behavior;							}
	@Override	public boolean stillAlive(PlayerWeapon a) {
		prochainTir -= 1;
		return super.stillAlive(a);					
	}
}
