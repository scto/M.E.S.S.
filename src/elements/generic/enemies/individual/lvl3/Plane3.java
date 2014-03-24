package elements.generic.enemies.individual.lvl3;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.Invocable;
import elements.generic.enemies.individual.lvl1.Plane;
import jeu.Stats;

public class Plane3 extends Plane {
	
	public static final Pool<Plane3> POOL = Pools.get(Plane3.class);
	public static final Invocable ref = new Plane3();
	private static final int PV = getModulatedPv(Stats.PV_AVION, 3), DEMI_PV = PV / 2;
	private static final int XP = getXp(BASE_XP, 3);
	private static final float SPEED = getModulatedSpeed(Plane.SPEED, 3), HALF_SPEED = SPEED / 2;
	
	@Override
	public Invocable invoquer() {
		Plane3 l = POOL.obtain();
		l.init();
		LIST.add(l);
		return l;
	}
	@Override
	public float getVitesse() {
		if (goodShape)
			return SPEED;
		return HALF_SPEED;
	}
	@Override	protected int getPvMax() {				return PV;	}
	@Override	public float getModifVitesse() {		return 1.3f;		}
	@Override	public int getXp() {					return XP;	}
	@Override	public int getValeurBonus() {			return BASE_XP;	}
	@Override	protected int getDemiPv() {				return DEMI_PV;											}
	@Override	public void free() {					POOL.free(this);	}
	@Override	protected String getLabel() {			return getClass().toString();	}
}
