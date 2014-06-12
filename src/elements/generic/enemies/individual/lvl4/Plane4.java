package elements.generic.enemies.individual.lvl4;

import jeu.Stats;
import assets.sprites.Animations;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.components.Phase;
import elements.generic.components.behavior.Behavior;
import elements.generic.components.shots.Gatling;
import elements.generic.components.shots.Shot;
import elements.generic.enemies.individual.lvl1.Plane;
import elements.generic.enemies.individual.lvl3.Plane3;

public class Plane4 extends Plane3 {
	
	public static final Pool<Plane4> POOL = Pools.get(Plane4.class);
	private static final int HP = getModulatedPv(Stats.PLANE_HP, 4), DEMI_HP = HP / 2, XP = getXp(BASE_XP, 4);
	private static final float SPEED = getModulatedSpeed(Plane.SPEED, 4), HALF_SPEED = SPEED / 2, FIRERATE = Plane.FIRERATE * 0.7f;
	protected static final Phase[] PHASES = {
		new Phase(				Behavior.STRAIGHT_ON,				Gatling.SMALL_FIREBALL,				Shot.DOUBLE_SHOT_DOWN_RAND_RAFALE,				Animations.PLANE_GOOD				),
		new Phase(				Behavior.STRAIGHT_ON,				Gatling.SMALL_FIREBALL,				Shot.SHOT_DOWN_RAND_RAFALE,					Animations.PLANE_BAD				)		};
	private int shotNumber;
	
	@Override
	public void init() {
		super.init();
		shotNumber = 0;
	}
	
	@Override
	public float getSpeed() {
		if (index == 0)
			return SPEED;
		return HALF_SPEED;
	}
	/**
	 * Shot dispersion
	 */
	@Override	public float getFloatFactor() {			return 5;							}
	@Override	public int getNumberOfShots() {			return 3;							}
	@Override	protected int getMaxHp() {				return HP;							}
	@Override	public int getXp() {					return XP;							}
	@Override	public float getBulletSpeedMod() {		return 1.3f;						}
	@Override	public Phase[] getPhases() {			return PHASES;						}
	@Override	public int getBonusValue() {			return BASE_XP;						}
	@Override	protected int getHalfHp() {				return DEMI_HP;						}
	@Override	public void addShots(int i) {			shotNumber += i;					}
	@Override	public void free() {					POOL.free(this);					}
	@Override	public float getFirerate() {			return FIRERATE/2;					}
	@Override	public int getShotNumber() {			return shotNumber;					}
	@Override	protected String getLabel() {			return getClass().toString();		}
}
