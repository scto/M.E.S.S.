package elements.generic.enemies.individual.lvl4;

import assets.sprites.Animations;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.components.Phase;
import elements.generic.components.behavior.Behavior;
import elements.generic.components.shots.Gatling;
import elements.generic.components.shots.Shot;
import elements.generic.enemies.individual.lvl1.Laser;
import elements.generic.enemies.individual.lvl3.Laser3;
import jeu.Stats;


public class Laser4 extends Laser3 {
	
	private static final int HP = getModulatedPv(Stats.LASER_HP, 4), XP = getXp(BASE_XP, 4);
	private static final float SPEED = getModulatedSpeed(Laser.SPEED, 4), PHASE_DURATION = 20;
	public static final Pool<Laser4> POOL = Pools.get(Laser4.class);
	protected static final Phase[] PHASES = {
		new Phase(				Behavior.SINK,				Gatling.LASER,				Shot.V,				Animations.AILE_DEPLOYEES				), 
		new Phase(				Behavior.GO_AWAY,			Gatling.LASER,				Shot.V,				Animations.AILE_DEPLOYEES				)		};
	private int numeroTir;
	
	@Override
	public void init() {
		numeroTir = 3;
		super.init();
	}
	@Override	public float getFloatFactor() {				return super.getFloatFactor() * 1.2f;	}
	@Override	protected String getLabel() {				return getClass().toString();			}
	@Override	public float getPhaseDuration() {			return PHASE_DURATION;					}
	@Override	public int getShotNumber() {				return numeroTir;						}
	@Override	public void free() {						POOL.free(this);						}
	@Override	public void addShots(int i) {				numeroTir += i;							}
	@Override	public int getBonusValue() {				return BASE_XP;							}
	@Override	public Phase[] getPhases() {				return PHASES;							}
	@Override	public float getSpeed() {					return SPEED;							}
	@Override	public float getBulletSpeedMod() {			return 1f;								}
	@Override	protected int getMaxHp() {					return HP;								}
	@Override	public int getXp() {						return XP;								}
}
