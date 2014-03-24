package elements.generic.enemies.individual.lvl4;

import assets.animation.AnimationKinder;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.Invocable;
import elements.generic.enemies.individual.lvl1.Kinder;
import elements.generic.enemies.individual.lvl3.KinderNv3;
import elements.generic.weapons.patterns.Tirs;
import jeu.Stats;


public class KinderNv4 extends KinderNv3 {
	
	public static final Pool<KinderNv4> POOL = Pools.get(KinderNv4.class);
	public static final Invocable ref = new KinderNv4();
	protected static final Tirs tir = new Tirs(CADENCE * 0.5f);
	private static final int PV = getModulatedPv(Stats.PV_KINDER, 4);
	private static final int XP = getXp(BASE_XP, 4);
	private static final float SPEED = getModulatedSpeed(Kinder.SPEED, 4);
	
	@Override
	public Invocable invoquer() {
		final KinderNv4 l = POOL.obtain();
		LIST.add(l);
		l.init();
		return l;
	}
	
	@Override
	protected void tir() {
		if (now > AnimationKinder.TIME_OUVERT)
			getTir().tirToutDroit(this, now, prochainTir);
	}
	
	@Override	public void free() {					POOL.free(this);	}
	@Override	protected int getPvMax() {				return PV;	}
	@Override	public int getXp() {					return XP;	}
	@Override	public int getValeurBonus() {			return BASE_XP;	}
	@Override	public float getVitesse() {				return SPEED;	}
	@Override	protected String getLabel() {			return getClass().toString();	}
	@Override	protected Tirs getTir() {				return tir;	}
}

