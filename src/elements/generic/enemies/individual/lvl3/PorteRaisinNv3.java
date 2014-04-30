package elements.generic.enemies.individual.lvl3;

import jeu.CSG;
import jeu.Stats;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.Invocable;
import elements.generic.enemies.individual.lvl1.PorteRaisin;
import elements.generic.weapons.patterns.Tirs;

public class PorteRaisinNv3 extends PorteRaisin {

	public static final Pool<PorteRaisinNv3> POOL = Pools.get(PorteRaisinNv3.class);
	public static final Invocable ref = new PorteRaisinNv3();
	private static final int PV = getModulatedPv(Stats.PV_PORTE_RAISIN, 3), DEMI_PV = PV / 2;
	private static final int XP = getXp(BASE_XP, 3);
	private static final float SPEED = getModulatedSpeed(PorteRaisin.SPEED, 3);
	
	@Override
	public Invocable invoquer() {
		final PorteRaisinNv3 l = POOL.obtain();
		LIST.add(l);
		l.init();
		return l;
	}
	@Override	public void free() {				POOL.free(this);	}
	@Override	protected int getPvMax() {			return PV;	}
	@Override	public int getXp() {				return XP;	}
	@Override	public int getValeurBonus() {		return BASE_XP;	}
	@Override	protected int getPallierPv() {		return DEMI_PV;	}
	@Override	protected String getLabel() {		return getClass().toString();	}
	@Override	protected void tir() {				TIR.tirEnRafaleGaucheEtDroite(this, 3 + CSG.R.nextInt(6), now, prochainTir);	}
	@Override	protected Tirs getTir() {			return TIR;								}
}
