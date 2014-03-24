package elements.generic.enemies.individual.lvl4;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.Invocable;
import elements.generic.enemies.individual.lvl1.Insecte;
import jeu.Stats;


public class InsecteNv4 extends Insecte {
	
	public static final Pool<InsecteNv4> POOL = Pools.get(InsecteNv4.class);
	public static final Invocable ref = new InsecteNv4();
	private static final int PV = getModulatedPv(Stats.PV_INSECTE, 4);
	private static final int XP = getXp(BASE_XP, 4);
	private static final float SPEED = getModulatedSpeed(Insecte.SPEED, 4);
	
	@Override
	public Invocable invoquer() {
		final InsecteNv4 l = POOL.obtain();
		LIST.add(l);
		l.init();
		return l;
	}
	@Override	public void free() {				POOL.free(this);												}
	@Override	protected String getLabel() {		return getClass().toString();									}
	@Override	protected int getPvMax() {			return PV;	}
	@Override	public int getXp() {				return XP;	}
	@Override	public int getValeurBonus() {		return BASE_XP;	}
	@Override	public float getVitesse() {			return SPEED;												}
	@Override	protected void tir() {				tir.tirShotgun(this, now, prochainTir, 14, 7, angle -90);		}
}
