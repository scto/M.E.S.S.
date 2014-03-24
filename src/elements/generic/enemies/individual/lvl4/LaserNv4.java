package elements.generic.enemies.individual.lvl4;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.Invocable;
import elements.generic.enemies.individual.lvl1.Laser;
import elements.generic.enemies.individual.lvl3.LaserNv3;
import elements.generic.weapons.patterns.TireurPlusieurFois;
import elements.generic.weapons.patterns.Tirs;
import jeu.Stats;


public class LaserNv4 extends LaserNv3 implements TireurPlusieurFois{
	
	public static final Tirs TIR = new Tirs(CADENCE * 0.4f);
	private static final int PV = getModulatedPv(Stats.PV_LASER, 4);
	private static final int XP = getXp(BASE_XP, 4);
	private static final float SPEED = getModulatedSpeed(Laser.SPEED, 4);
	public static final Pool<LaserNv4> POOL = Pools.get(LaserNv4.class);
	public static final Invocable ref = new LaserNv4(); 
	private int numeroTir;
	
	
	@Override
	public Invocable invoquer() {
		LaserNv4 l = POOL.obtain();
		LIST.add(l);
		l.init();
		l.rotation = 3;
		return l;
	}
	@Override
	protected void init() {
		numeroTir = 3;
		super.init();
	}
	@Override	public void free() {						POOL.free(this);	}
	@Override	protected void tir() {						TIR.tirEnRafale(this, 3, now, prochainTir);	}
	@Override	public float getVitesse() {					return SPEED;	}
	@Override	protected int getPvMax() {					return PV;	}
	@Override	public int getXp() {						return XP;	}
	@Override	public int getValeurBonus() {				return BASE_XP;	}
	@Override	protected String getLabel() {				return getClass().toString();	}
	@Override	public int getNumeroTir() {					return numeroTir;	}
	@Override	public void addNombresTirs(int i) {			numeroTir += i;	}
}
