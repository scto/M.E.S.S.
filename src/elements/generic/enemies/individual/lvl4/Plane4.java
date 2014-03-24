package elements.generic.enemies.individual.lvl4;

import jeu.Stats;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.Invocable;
import elements.generic.behavior.Behavior;
import elements.generic.behavior.StraightOn;
import elements.generic.enemies.individual.lvl1.Plane;
import elements.generic.enemies.individual.lvl3.Plane3;
import elements.generic.weapons.patterns.Tirs;

public class Plane4 extends Plane3 {
	
	public static final Pool<Plane4> POOL = Pools.get(Plane4.class);
	public static final Invocable ref = new Plane4();
	private static final float dispersionTir = 10;
	protected static final Tirs tir = new Tirs(CADENCE-.3f);
	private static final Behavior behavior = new StraightOn();
	private static final int PV = getModulatedPv(Stats.PV_AVION, 4), DEMI_PV = PV / 2;
	private static final int XP = getXp(BASE_XP, 4);
	private static final float SPEED = getModulatedSpeed(Plane.SPEED, 4), HALF_SPEED = SPEED / 2;
	
	@Override
	public Invocable invoquer() {
		Plane4 l = POOL.obtain();
		LIST.add(l);
		l.init();
		l.prochainTir = .5f;
		return l;
	}
	@Override
	public float getVitesse() {
		if (goodShape)
			return SPEED;
		return HALF_SPEED;
	}
	@Override	protected int getPvMax() {				return PV;	}
	@Override	public float getModifVitesse() {		return 1.3f;		}
	@Override	public int getXp() {					return XP;	}
	@Override	public int getValeurBonus() {			return BASE_XP;	}
	@Override	protected int getDemiPv() {				return DEMI_PV;											}
	@Override	public void free() {					POOL.free(this);															}
	@Override	protected void tir() {					tir.doubleTirVersBasRandomize(this, now, prochainTir, dispersionTir);		}
	@Override	protected String getLabel() {			return getClass().toString();												}
	@Override	public Behavior getBehavior() {			return behavior;															}
}
