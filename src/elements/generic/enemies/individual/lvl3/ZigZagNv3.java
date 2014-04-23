package elements.generic.enemies.individual.lvl3;

import jeu.Stats;
import assets.animation.AnimationZigZag;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.Invocable;
import elements.generic.enemies.individual.lvl1.ZigZag;
import elements.particular.particles.Particles;
import elements.particular.particles.individual.PrecalculatedParticles;

public class ZigZagNv3 extends ZigZag {

	public static final Pool<ZigZagNv3> POOL = Pools.get(ZigZagNv3.class);
	public static final Invocable ref = new ZigZagNv3();
	private static final int LVL = 3;
	private static final int PV = getModulatedPv(Stats.PV_ZIGZAG, LVL);
	private static final int XP = getXp(BASE_XP, LVL);
	private static final float SPEED = getModulatedSpeed(ZigZag.SPEED, LVL);
	
	@Override
	public Invocable invoquer() {
		ZigZagNv3 l = POOL.obtain();
		LIST.add(l);
		l.init();
		return l;
	}
	@Override	protected TextureRegion getTexture() {	return AnimationZigZag.getTextureBlue(pos.x + DEMI_LARGEUR);	}
	@Override	protected void explode() {				Particles.explosionBlue(this);	}
	@Override	protected float[] getColor() {			return PrecalculatedParticles.colorsBlue;	}
	@Override	public void free() {					POOL.free(this);	}
	@Override	protected int getPvMax() {				return PV;	}
	@Override	public int getXp() {					return XP;	}
	@Override	public int getValeurBonus() {			return BASE_XP;	}
	@Override	public float getVitesse() {				return SPEED;	}
	@Override	protected String getLabel() {			return getClass().toString();	}
}
