package elements.generic.enemies.individual.lvl4;

import assets.animation.AnimationZigZag;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.Invocable;
import elements.generic.enemies.individual.lvl1.ZigZag;
import elements.generic.enemies.individual.lvl3.ZigZagNv3;
import elements.particular.particles.Particles;
import elements.particular.particles.individual.MovingSmoke;
import jeu.Stats;

public class ZigZagNv4 extends ZigZagNv3 {

	public static final Pool<ZigZagNv4> POOL = Pools.get(ZigZagNv4.class);
	public static final Invocable ref = new ZigZagNv4();
	private static final int LVL = 4;
	private static final int PV = getModulatedPv(Stats.PV_ZIGZAG, LVL);
	private static final int XP = getXp(BASE_XP, LVL);
	private static final float SPEED = getModulatedSpeed(ZigZag.SPEED, LVL);
	
	@Override
	public Invocable invoquer() {
		ZigZagNv4 l = POOL.obtain();
		LIST.add(l);
		l.init();
		return l;
	}
	@Override	protected TextureRegion getTexture() {	return AnimationZigZag.getTextureGreen(pos.x + DEMI_LARGEUR);	}
	@Override	protected void explode() {				Particles.explosionGreen(this);	}
	@Override	protected float[] getColor() {			return MovingSmoke.colorsGreen;	}
	@Override	public void free() {					POOL.free(this);	}
	@Override	protected int getPvMax() {				return PV;	}
	@Override	public int getXp() {					return XP;	}
	@Override	public int getValeurBonus() {			return BASE_XP;	}
	@Override	public float getVitesse() {				return SPEED;	}
	@Override	protected String getLabel() {			return getClass().toString();	}
}
