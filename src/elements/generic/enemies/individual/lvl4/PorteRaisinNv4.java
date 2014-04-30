package elements.generic.enemies.individual.lvl4;

import jeu.CSG;
import jeu.Stats;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.Invocable;
import elements.generic.enemies.individual.lvl1.PorteRaisin;
import elements.generic.enemies.individual.lvl3.PorteRaisinNv3;
import elements.generic.weapons.patterns.Tirs;

public class PorteRaisinNv4 extends PorteRaisinNv3 {

	public static final Pool<PorteRaisinNv4> POOL = Pools.get(PorteRaisinNv4.class);
	public static final Invocable ref = new PorteRaisinNv4();
	private static final int LVL = 4;
	private static final int PV = getModulatedPv(Stats.PV_PORTE_RAISIN, LVL), DEMI_PV = PV / 2;
	private static final int XP = getXp(BASE_XP, LVL);
	private static final float SPEED = getModulatedSpeed(PorteRaisin.SPEED, LVL);
	
	@Override
	public Invocable invoquer() {
		PorteRaisinNv4 l = POOL.obtain();
		LIST.add(l);
		l.init();
		return l;
	}
	@Override	public void free() {					POOL.free(this);	}
	@Override	protected int getPvMax() {				return PV;	}
	@Override	public int getXp() {					return XP;	}
	@Override	public int getValeurBonus() {			return BASE_XP;	}
	@Override	protected int getPallierPv() {			return DEMI_PV;	}
	@Override	protected String getLabel() {			return getClass().toString();	}
	@Override	protected void tir() {					TIR.tirEnRafaleGaucheEtDroite(this, 4 + CSG.R.nextInt(10), now, prochainTir);	}
	@Override	protected Tirs getTir() {				return TIR;								}
}
