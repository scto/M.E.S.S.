package elements.generic.enemies.individual.lvl3;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.components.shots.AbstractShot;
import elements.generic.enemies.individual.lvl1.Laser;
import jeu.Stats;


public class Laser3 extends Laser {
	
	public static final Pool<Laser3> POOL = Pools.get(Laser3.class);
	private static final int HP = getModulatedPv(Stats.LASER_HP, 3), XP = getXp(BASE_XP, 3);
	private static final float SPEED = SPEED14 * Stats.SLVL3, FIRERATE = 0.25f * MOD_FIRERATE;
	private int shotNumber = 0;
	
	@Override
	protected void shoot() {
		super.shoot();
		interval();
	}

	protected void interval() {
		shotNumber = AbstractShot.interval(this, 2, 1, shotNumber);
	}
	
	@Override	public float getFirerate() {			return FIRERATE;							}
	@Override	public void free() {					POOL.free(this);							}
	@Override	public int getBonusValue() {			return BASE_XP;								}
	@Override	public float getSpeed() {				return SPEED;								}
	@Override	protected int getMaxHp() {				return HP;									}
	@Override	public int getXp() {					return XP;									}
	@Override	protected float getRotateTime() {		return 16.2f;									}
	
}
