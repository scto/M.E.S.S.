package elements.generic.enemies.individual.lvl3;

import menu.DeBaseMenu;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.Invocable;
import elements.generic.enemies.individual.lvl1.DeBase;
import jeu.Stats;

public class DeBaseNv3 extends DeBaseMenu {
	
	public static final Pool<DeBaseNv3> POOL = Pools.get(DeBaseNv3.class);
	public static final Invocable ref = new DeBaseNv3(); 
	private static final int PV = getModulatedPv(Stats.PV_DE_BASE, 4);
	private static final int XP = getXp(BASE_XP, 4);
	private static final float SPEED = DeBase.SPEED * 2;
	
	@Override
	public Invocable invoquer() {
		DeBaseNv3 l = POOL.obtain();
		LIST.add(l);
		l.init();
		return l;
	}
	
	@Override	public void free() {				POOL.free(this);	}
	@Override	protected int getPvMax() {			return PV;	}
	@Override	public float getVitesse() {			return SPEED;	}
	@Override	public int getXp() {				return XP;	}
	@Override	public int getValeurBonus() {		return BASE_XP;	}
	@Override	protected String getLabel() {		return getClass().toString();	}
}
