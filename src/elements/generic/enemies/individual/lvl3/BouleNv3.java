package elements.generic.enemies.individual.lvl3;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.Invocable;
import elements.generic.enemies.individual.lvl1.Boule;
import elements.generic.weapons.player.PlayerWeapon;
import jeu.Stats;

public class BouleNv3 extends Boule {
	
	public static final Pool<BouleNv3> POOL = Pools.get(BouleNv3.class);
	public static final Invocable ref = new BouleNv3();
	private static final int PV = getModulatedPv(Stats.PV_BOULE, 3);
	private static final int XP = getXp(BASE_XP, 3);
	protected static final float SPEED = getModulatedSpeed(Boule.SPEED, 3);
	@Override
	public Invocable invoquer() {
		final BouleNv3 l = POOL.obtain();
		LIST.add(l);
		l.init();
		return l;
	}
	@Override	public void free() {				POOL.free(this);						}
	@Override	protected int getPvMax() {			return PV;					}
	@Override	protected String getLabel() {		return getClass().toString();			}
	@Override	public int getXp() {				return XP;		}
	@Override	public int getValeurBonus() {		return BASE_XP;	}
	@Override	public float getVitesse() {			return SPEED;	}
	@Override	public boolean stillAlive(PlayerWeapon a) {
		prochainTir -= 2;
		return super.stillAlive(a);					
	}
}
