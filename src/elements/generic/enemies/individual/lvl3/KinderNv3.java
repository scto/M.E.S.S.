package elements.generic.enemies.individual.lvl3;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.Invocable;
import elements.generic.enemies.individual.lvl1.Kinder;
import elements.generic.weapons.patterns.Tirs;
import jeu.Stats;


public class KinderNv3 extends Kinder {
	
	public static final Pool<KinderNv3> POOL = Pools.get(KinderNv3.class);
	public static final Invocable ref = new KinderNv3();
	protected static final Tirs tir = new Tirs(CADENCE * .7f);
	private static final int PV = getModulatedPv(Stats.PV_KINDER, 3);
	private static final int XP = getXp(BASE_XP, 3);
	private static final float SPEED = getModulatedSpeed(Kinder.SPEED, 3);
	
	@Override
	public Invocable invoquer() {
		KinderNv3 l = POOL.obtain();
		l.init();
		LIST.add(l);
		return l;
	}
	@Override	public void free() {				POOL.free(this);	}
	@Override	protected int getPvMax() {			return PV;	}
	@Override	public int getXp() {				return XP;	}
	@Override	public int getValeurBonus() {		return BASE_XP;	}
	@Override	public float getVitesse() {			return SPEED;	}
	@Override	protected String getLabel() {		return getClass().toString();	}
	@Override	protected Tirs getTir() {			return tir;	}
}

