package elements.generic.enemies.individual.lvl3;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.Invocable;
import elements.generic.enemies.individual.lvl1.Toupie;
import elements.generic.weapons.patterns.Tirs;
import jeu.Stats;

public class ToupieNv3 extends Toupie {

	public static final Tirs TIR = new Tirs(CADENCE * 0.7f);
	public static final Pool<ToupieNv3> POOL = Pools.get(ToupieNv3.class);
	public static final Invocable ref = new ToupieNv3();
	private static final int LVL = 3;
	private static final int PV = getModulatedPv(Stats.PV_TOUPIE, LVL);
	private static final int XP = getXp(BASE_XP, LVL);
	private static final float SPEED = getModulatedSpeed(Toupie.SPEED, LVL);
	
	@Override
	public Invocable invoquer() {
		ToupieNv3 l = POOL.obtain();
		LIST.add(l);
		l.init();
		return l;
	}
	@Override	public void free() {				POOL.free(this);	}
	@Override	protected void tir() {				TIR.tirBalayage(this, now, prochainTir);	}
	@Override	protected int getPvMax() {			return PV;	}
	@Override	public int getXp() {				return XP;	}
	@Override	public int getValeurBonus() {		return BASE_XP;	}
	@Override	public float getVitesse() {			return SPEED;	}
	@Override	protected String getLabel() {		return getClass().toString();	}
	@Override	public float getModifVitesse() {	return 0.009f;	}
}
