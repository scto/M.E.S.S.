package elements.generic.enemies.individual.lvl4;

import jeu.Stats;
import jeu.mode.EndlessMode;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.Invocable;
import elements.generic.Player;
import elements.generic.enemies.individual.lvl1.QuiTirTriangle;
import elements.generic.enemies.individual.lvl3.QuiTirTriangle3;
import elements.generic.weapons.patterns.Tirs;


public class QuiTirTriangle4 extends QuiTirTriangle3 {
	
	public static final Pool<QuiTirTriangle4> POOL = Pools.get(QuiTirTriangle4.class);
	public static final Invocable ref = new QuiTirTriangle4();
	public static final Tirs TIR = new Tirs(CADENCE * 0.7f);
	private static float tmp;
	private static final int LVL = 4;
	private static final int PV = getModulatedPv(Stats.PV_DE_BASE_QUI_TIR_TRIANGLE, LVL), DEMI_PV = PV / 2;
	private static final int XP = getXp(BASE_XP, LVL);
	private static final float SPEED = getModulatedSpeed(QuiTirTriangle.SPEED, LVL);
	
	@Override
	public Invocable invoquer() {
		QuiTirTriangle4 l = POOL.obtain();
		LIST.add(l);
		l.init();
		return l;
	}
	@Override
	protected void tir() {	
		if (EndlessMode.alternate) {
			tmp = pos.x - Player.POS.x;
			pos.x -= EndlessMode.deltaDiv3 * tmp;
		}
		TIR.tirVersBas(this, now, prochainTir);	
	}
	@Override	protected int getPvMax() {					return PV;	}
	@Override	protected int getDemiPv() {					return DEMI_PV;}
	@Override	public int getXp() {						return XP;	}
	@Override	public int getValeurBonus() {				return BASE_XP;	}
	@Override	public float getVitesse() {					return SPEED;	}
	@Override	protected String getLabel() {				return getClass().toString();								}
	@Override	public void free() {						POOL.free(this);												}
}
