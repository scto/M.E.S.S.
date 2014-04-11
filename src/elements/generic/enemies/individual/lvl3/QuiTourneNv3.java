package elements.generic.enemies.individual.lvl3;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.Invocable;
import elements.generic.enemies.individual.lvl1.QuiTourne;
import elements.generic.weapons.patterns.Tirs;
import jeu.Stats;

public class QuiTourneNv3 extends QuiTourne {
	
	public static final Pool<QuiTourneNv3> POOL = Pools.get(QuiTourneNv3.class);
	public static final Tirs TIR = new Tirs(CADENCE * 0.15f);
	public static final Invocable ref = new QuiTourneNv3();
	private static final int LVL = 3;
	private static final int PV = getModulatedPv(Stats.PV_QUI_TOURNE, LVL);
	private static final int XP = getXp(BASE_XP, LVL);
	private static final float SPEED = getModulatedSpeed(QuiTourne.SPEED, LVL), HALF_SPEED = SPEED / 2;
	
	@Override
	public Invocable invoquer() {
		QuiTourneNv3 l = POOL.obtain();
		LIST.add(l);
		l.init();
		return l;
	}
	@Override	public void free() {				POOL.free(this);	}
	@Override	protected void tir() {				TIR.tirEnRafale(this, 5, now, prochainTir);	}
	@Override	protected int getPvMax() {			return PV;	}
	@Override	public float getVitesse() {			return SPEED;	}
	@Override	protected float getDemiVitesse() {	return HALF_SPEED;	}
	@Override	public int getXp() {				return XP;	}
	@Override	public int getValeurBonus() {		return BASE_XP;	}
	@Override	protected String getLabel() {		return getClass().toString();	}
}
