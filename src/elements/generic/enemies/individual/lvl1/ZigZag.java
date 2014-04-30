package elements.generic.enemies.individual.lvl1;

import jeu.Stats;
import jeu.mode.EndlessMode;
import assets.SoundMan;
import assets.animation.AnimationZigZag;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.Invocable;
import elements.generic.behavior.Behavior;
import elements.generic.enemies.Enemy;
import elements.particular.particles.Particles;
import elements.particular.particles.individual.PrecalculatedParticles;
import elements.positionning.Pos;
import elements.positionning.UpWide;

public class ZigZag extends Enemy {
	
	private static final int LARGEUR = Stats.LARGEUR_ZIG_ZAG;

	protected static final int DEMI_LARGEUR = LARGEUR/2;

	private static final int HAUTEUR = Stats.HAUTEUR_ZIG_ZAG, OFFSET_SMOKE = (int) (HAUTEUR * 0.8f);

	private static final int DEMI_HAUTEUR = HAUTEUR / 2;
	
	public static final int PK = 15;
	protected static final float SPEED = initSpeed(24, PK);
	private static final int PV = initPv(Stats.PV_ZIGZAG, PK);
	private static final int EXPLOSION = initExplosion(40, PK);
	protected static final int BASE_XP = Enemy.initXp(3, PK);

	private static final int XP = getXp(BASE_XP, 1);
	private static final Behavior behavior = initBehavior(PK, Behavior.ZIGZAG);
	
	private boolean sens = true;
	public static final Pool<ZigZag> POOL = Pools.get(ZigZag.class);
	public static final Invocable ref = new ZigZag();
	private static final Pos positionning = initPositionnement(UpWide.PK, PK);

	protected void init() {
		positionning.set(this);
		dir.x = 0;
		dir.y = -getVitesse();
		sens = true;
		angle = -90;
	}
	
	@Override
	public void mouvementEtVerif() {
		if (EndlessMode.alternate)
			Particles.smokeMoving(pos.x + DEMI_LARGEUR, pos.y + OFFSET_SMOKE, true, getColor());
		super.mouvementEtVerif();
	}
	
	@Override
	public Invocable invoquer() {
		final ZigZag z = POOL.obtain();
		LIST.add(z);
		z.init();
		return z;
	}

	protected float[] getColor() {						return PrecalculatedParticles.colorsRed;	}
	@Override	public void free() {					POOL.free(this);	}
	@Override	protected int getPvMax() {				return PV;	}
	@Override	protected TextureRegion getTexture() {	return AnimationZigZag.getTexture(pos.x + DEMI_LARGEUR);	}
	@Override	public int getHeight() {				return HAUTEUR;	}
	@Override	public int getWidth() {					return LARGEUR;	}
	@Override	public int getHalfHeight() {			return DEMI_HAUTEUR;	}
	@Override	public int getHalfWidth() {				return DEMI_LARGEUR;	}
	@Override	public float getDirectionY() {			return dir.y;	}
	@Override	public float getDirectionX() {			return dir.x;	}
	@Override	public float getVitesse() {				return SPEED;	}
	@Override	protected Sound getSonExplosion() {		return SoundMan.explosion5;	}
	@Override	protected String getLabel() {			return getClass().toString();	}
	@Override	public int getXp() {					return XP;	}
	@Override	public int getValeurBonus() {			return BASE_XP;	}
	@Override	public int getExplosionCount() {		return EXPLOSION;									}
	@Override	public Behavior getBehavior() {			return behavior;	}
	@Override	public boolean toLeft() {				return sens;	}
	@Override	public void setLeft(boolean b) {		sens = b;	}
}
