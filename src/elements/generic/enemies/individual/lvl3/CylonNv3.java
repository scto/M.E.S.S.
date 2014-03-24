package elements.generic.enemies.individual.lvl3;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.Invocable;
import elements.generic.enemies.individual.lvl1.Cylon;
import jeu.Stats;

public class CylonNv3 extends Cylon {
	
	public static final Pool<CylonNv3> POOL = Pools.get(CylonNv3.class);
	public static final Invocable ref = new CylonNv3();
	private static final int PV = getModulatedPv(Stats.PV_CYLON, 3), DEMI_PV = PV / 2;
	private static final int XP = getXp(BASE_XP, 3);
	@Override
	public Invocable invoquer() {
		CylonNv3 l = POOL.obtain();
		LIST.add(l);
		l.init();
		return l;
	}
	@Override	public void free() {					POOL.free(this);						}
	@Override	protected int getPvMax() {				return PV;					}
	@Override	public int getXp() {					return XP;		}
	@Override	public int getValeurBonus() {			return BASE_XP;	}
	@Override	protected String getLabel() {			return getClass().toString();			}
	@Override	protected int getDemiPv() {				return DEMI_PV;			}
}
