package elements.generic.enemies.individual.lvl4;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.Invocable;
import elements.generic.behavior.Behavior;
import elements.generic.behavior.StraightOnDetect;
import elements.generic.enemies.individual.lvl1.DeBase;
import elements.generic.enemies.individual.lvl3.DeBaseNv3;
import jeu.Stats;

public class DeBaseNv4 extends DeBaseNv3 {
	
	public static final Pool<DeBaseNv4> POOL = Pools.get(DeBaseNv4.class);
	public static final Invocable ref = new DeBaseNv4();
	private static final Behavior behavior = new StraightOnDetect();
	private static final int PV = getModulatedPv(Stats.PV_DE_BASE, 4);
	private static final int XP = getXp(BASE_XP, 4);
	private static final float SPEED = DeBase.SPEED * 4;
	
	@Override
	public Invocable invoquer() {
		DeBaseNv4 l = POOL.obtain();
		LIST.add(l);
		l.init();
		return l;
	}
	@Override	protected String getLabel() {			return getClass().toString();				}
	@Override	public void free() {					POOL.free(this);							}
	@Override	protected int getPvMax() {				return PV;				}
	@Override	public float getVitesse() {				return SPEED;				}
	@Override	public int getXp() {					return XP;				}
	@Override	public int getValeurBonus() {			return BASE_XP;		}
	@Override	public Behavior getBehavior() {			return behavior;	}
}
