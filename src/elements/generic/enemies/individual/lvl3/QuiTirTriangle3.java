package elements.generic.enemies.individual.lvl3;

import jeu.Stats;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.Invocable;
import elements.generic.enemies.individual.lvl1.QuiTirTriangle;


public class QuiTirTriangle3 extends QuiTirTriangle {
	
	public static final Pool<QuiTirTriangle3> POOL = Pools.get(QuiTirTriangle3.class);
	public static final Invocable ref = new QuiTirTriangle3();
	private static final int LVL = 3;
	private static final int PV = getModulatedPv(Stats.PV_DE_BASE_QUI_TIR_TRIANGLE, LVL), DEMI_PV = PV / 2;
	private static final int XP = getXp(BASE_XP, LVL);
	private static final float SPEED = getModulatedSpeed(QuiTirTriangle.SPEED, LVL);
	
	@Override
	public Invocable invoquer() {
		QuiTirTriangle3 l = POOL.obtain();
		LIST.add(l);
		l.init();
		return l;
	}
	@Override	public void free() {				POOL.free(this);	}
	@Override	protected int getPvMax() {			return PV;	}
	@Override	protected int getDemiPv() {			return DEMI_PV;}
	@Override	public int getXp() {				return XP;	}
	@Override	public int getValeurBonus() {		return BASE_XP;	}
	@Override	public float getVitesse() {			return SPEED;	}
	@Override	protected String getLabel() {		return getClass().toString();	}
}
