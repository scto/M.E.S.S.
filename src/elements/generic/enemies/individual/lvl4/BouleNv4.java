package elements.generic.enemies.individual.lvl4;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.Invocable;
import elements.generic.enemies.individual.lvl1.Boule;
import elements.generic.enemies.individual.lvl3.BouleNv3;
import elements.generic.weapons.player.PlayerWeapon;
import jeu.Stats;

public class BouleNv4 extends BouleNv3 {
	
	public static final Pool<BouleNv4> POOL = Pools.get(BouleNv4.class);
	public static final Invocable ref = new BouleNv4();
	private static final int PV = getModulatedPv(Stats.PV_BOULE, 4);
	private static final int XP = getXp(BASE_XP, 4);
	protected static final float SPEED = getModulatedSpeed(Boule.SPEED, 4);
	
	@Override
	public Invocable invoquer() {
		BouleNv4 l = POOL.obtain();
		LIST.add(l);
		l.init();
		return l;
	}
	@Override	public void free() {							POOL.free(this);							}
	@Override	protected int getPvMax() {						return PV;						}
	@Override	public int getXp() {							return XP;				}
	@Override	public int getValeurBonus() {					return BASE_XP;		}
	@Override	protected String getLabel() {					return getClass().toString();				}
	@Override	public float getVitesse() {			return SPEED;	}
	@Override	public boolean stillAlive(PlayerWeapon a) {
		prochainTir -= 6;
		return super.stillAlive(a);					
	}
}
