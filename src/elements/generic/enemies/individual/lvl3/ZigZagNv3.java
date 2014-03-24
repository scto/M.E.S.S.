package elements.generic.enemies.individual.lvl3;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.Invocable;
import elements.generic.enemies.individual.lvl1.ZigZag;
import jeu.Stats;

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
	@Override	public void free() {			POOL.free(this);	}
	@Override	protected int getPvMax() {		return PV;	}
	@Override	public int getXp() {			return XP;	}
	@Override	public int getValeurBonus() {	return BASE_XP;	}
	@Override	public float getVitesse() {		return SPEED;	}
	@Override	protected String getLabel() {	return getClass().toString();	}
}
