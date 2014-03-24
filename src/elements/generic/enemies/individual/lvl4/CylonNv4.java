package elements.generic.enemies.individual.lvl4;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.Invocable;
import elements.generic.enemies.individual.lvl3.CylonNv3;
import elements.generic.weapons.patterns.Tirs;
import jeu.Stats;

public class CylonNv4 extends CylonNv3 {
	
	public static final Pool<CylonNv4> POOL = Pools.get(CylonNv4.class);
	public static final Invocable ref = new CylonNv4();
	protected static final Tirs tir = new Tirs(CADENCE * 0.75f);
	private static final int PV = getModulatedPv(Stats.PV_CYLON, 4), DEMI_PV = PV / 2;
	private static final int XP = getXp(BASE_XP, 4);
	
	@Override
	public Invocable invoquer() {
		CylonNv4 l = POOL.obtain();
		LIST.add(l);
		l.init();
		return l;
	}
	@Override	public void free() {					POOL.free(this);								}
	@Override	protected int getPvMax() {				return PV;							}
	@Override	public int getXp() {					return XP;				}
	@Override	public int getValeurBonus() {			return BASE_XP;			}
	@Override	protected String getLabel() {			return getClass().toString();					}
	@Override	protected void tir() {					tir.tirToutDroit(this, now, prochainTir);		}
	@Override	protected int getDemiPv() {				return DEMI_PV;					}
}
