package elements.generic.enemies.individual.lvl3;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.enemies.individual.lvl1.Laser;
import jeu.Stats;


public class LaserNv3 extends Laser {
	
	public static final Pool<LaserNv3> POOL = Pools.get(LaserNv3.class);
	private static final int HP = getModulatedPv(Stats.LASER_HP, 3);
	private static final int XP = getXp(BASE_XP, 3);
	private static final float SPEED = getModulatedSpeed(Laser.SPEED, 3);
	private static final float PHASE_DURATION = 16;
	
	@Override	public void free() {					POOL.free(this);	}
	@Override	public float getSpeed() {				return SPEED;	}
	@Override	protected int getMaxHp() {				return HP;	}
	@Override	public int getXp() {					return XP;	}
	@Override	public int getBonusValue() {			return BASE_XP;	}
	@Override	protected String getLabel() {			return getClass().toString();	}
	@Override	public float getFloatFactor() {			return super.getFloatFactor() * 1.25f;		}
	@Override	public float getPhaseDuration() {		return PHASE_DURATION;	}
}
