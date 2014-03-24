package elements.generic.enemies.individual.lvl3;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.Invocable;
import elements.generic.enemies.individual.lvl1.Laser;
import elements.generic.weapons.patterns.Tirs;
import jeu.Stats;


public class LaserNv3 extends Laser {
	
	public static final Pool<LaserNv3> POOL = Pools.get(LaserNv3.class);
	public static final Invocable ref = new LaserNv3(); 
	public static final Tirs TIR = new Tirs(CADENCE * 0.8f);
	private static final int PV = getModulatedPv(Stats.PV_LASER, 3);
	private static final int XP = getXp(BASE_XP, 3);
	private static final float SPEED = getModulatedSpeed(Laser.SPEED, 3);
	
	
	@Override
	public Invocable invoquer() {
		LaserNv3 l = POOL.obtain();
		LIST.add(l);
		l.init();
		l.now = 0;
		return l;
	}
	@Override	public void free() {					POOL.free(this);	}
	@Override	protected void tir() {					TIR.tirToutDroit(this, now, prochainTir);	}
	@Override	public float getVitesse() {				return SPEED;	}
	@Override	protected int getPvMax() {				return PV;	}
	@Override	public int getXp() {					return XP;	}
	@Override	public int getValeurBonus() {			return BASE_XP;	}
	@Override	protected String getLabel() {			return getClass().toString();	}
}
