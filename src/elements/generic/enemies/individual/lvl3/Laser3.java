package elements.generic.enemies.individual.lvl3;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.enemies.individual.lvl1.Laser;
import jeu.Stats;


public class Laser3 extends Laser {
	
	public static final Pool<Laser3> POOL = Pools.get(Laser3.class);
	private static final int HP = getModulatedPv(Stats.LASER_HP, 3), XP = getXp(BASE_XP, 3);
	private static final float SPEED = getModulatedSpeed(Laser.SPEED, 3), PHASE_DURATION = 16;
	
	
	@Override	public float getFloatFactor() {			return super.getFloatFactor() * 1.35f;		}
	@Override	protected String getLabel() {			return getClass().toString();				}
	@Override	public float getPhaseDuration() {		return PHASE_DURATION;						}
	@Override	public void free() {					POOL.free(this);							}
	@Override	public int getBonusValue() {			return BASE_XP;								}
	@Override	public float getSpeed() {				return SPEED;								}
	@Override	protected int getMaxHp() {				return HP;									}
	@Override	public int getXp() {					return XP;									}
}
