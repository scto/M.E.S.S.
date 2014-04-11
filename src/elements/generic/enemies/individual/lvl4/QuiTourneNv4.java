package elements.generic.enemies.individual.lvl4;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.Invocable;
import elements.generic.enemies.individual.lvl1.QuiTourne;
import elements.generic.enemies.individual.lvl3.QuiTourneNv3;
import elements.generic.weapons.patterns.Tirs;
import jeu.Stats;

public class QuiTourneNv4 extends QuiTourneNv3 {
	
	public static final Pool<QuiTourneNv4> POOL = Pools.get(QuiTourneNv4.class);
	public static final Tirs TIR = new Tirs(CADENCE * 0.05f);
	public static final Invocable ref = new QuiTourneNv4();
	private static final int LVL = 4;
	private static final int PV = getModulatedPv(Stats.PV_QUI_TOURNE, LVL);
	private static final int XP = getXp(BASE_XP, LVL);
	private static final float SPEED = getModulatedSpeed(QuiTourne.SPEED, LVL), HALF_SPEED = SPEED / 2;
	@Override
	public Invocable invoquer() {
		QuiTourneNv4 l = POOL.obtain();
		LIST.add(l);
		l.init();
		return l;
	}
	@Override	public void free() {				POOL.free(this);								}
	@Override	protected void tir() {				TIR.tirEnRafale(this, 9, now, prochainTir);		}
	@Override	protected int getPvMax() {			return PV;	}
	@Override	public float getVitesse() {			return SPEED;	}
	@Override	protected float getDemiVitesse() {	return HALF_SPEED;	}
	@Override	public int getXp() {				return XP;	}
	@Override	public int getValeurBonus() {		return BASE_XP;	}
	@Override	protected String getLabel() {		return getClass().toString();					}
}
