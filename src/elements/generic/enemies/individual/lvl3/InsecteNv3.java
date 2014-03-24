package elements.generic.enemies.individual.lvl3;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.Invocable;
import elements.generic.enemies.individual.lvl1.Insecte;
import jeu.Stats;


public class InsecteNv3 extends Insecte {
	
	public static final Pool<InsecteNv3> POOL = Pools.get(InsecteNv3.class);
	public static final Invocable ref = new InsecteNv3();
	private static final int PV = getModulatedPv(Stats.PV_INSECTE, 3);
	private static final int XP = getXp(BASE_XP, 3);
	private static final float SPEED = getModulatedSpeed(Insecte.SPEED, 3);
	
	@Override
	public Invocable invoquer() {
		final InsecteNv3 l = POOL.obtain();
		LIST.add(l);
		l.init();
		return l;
	}
	@Override	public void free() {			POOL.free(this);	}
	@Override	protected int getPvMax() {		return PV;	}
	@Override	public int getXp() {			return XP;	}
	@Override	public int getValeurBonus() {	return BASE_XP;	}
	@Override	public float getVitesse() {		return SPEED;												}
	@Override	protected String getLabel() {	return getClass().toString();	}
}
