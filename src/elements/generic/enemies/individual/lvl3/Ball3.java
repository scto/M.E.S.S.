package elements.generic.enemies.individual.lvl3;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.components.shots.AbstractShot;
import elements.generic.enemies.individual.lvl1.Ball;
import jeu.Stats;

public class Ball3 extends Ball {
	
	public static final Pool<Ball3> POOL = Pools.get(Ball3.class);
	private static final int HP = getModulatedPv(Stats.HP_BALL, 3), XP = getXp(BASE_XP, 3);
	protected static final float SPEED = getModulatedSpeed(40, 3), FIRERATE = 0.4f;
	protected int shotNumber;
	
	@Override
	public void reset() {
		shotNumber = 0;
		super.reset();
	}

	@Override
	protected void shoot() {
		super.shoot();
		shotNumber = AbstractShot.interval(this, 3, 2, shotNumber);
	}
	
	@Override	public float getFirerate() {					return FIRERATE;						}
	@Override	public void free() {							POOL.free(this);						}
	@Override	public int getBonusValue() {					return BASE_XP;							}
	@Override	public float getSpeed() {						return SPEED;							}
	@Override	protected int getMaxHp() {						return HP;								}
	@Override	public int getXp() {							return XP;								}
}
