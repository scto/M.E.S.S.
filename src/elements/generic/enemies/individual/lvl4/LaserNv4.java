package elements.generic.enemies.individual.lvl4;

import assets.sprites.Animations;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.components.Phase;
import elements.generic.components.behavior.Behavior;
import elements.generic.components.shots.Gatling;
import elements.generic.components.shots.Shot;
import elements.generic.enemies.individual.lvl1.Laser;
import elements.generic.enemies.individual.lvl3.LaserNv3;
import jeu.Stats;


public class LaserNv4 extends LaserNv3 {
	
	private static final int HP = getModulatedPv(Stats.LASER_HP, 4);
	private static final int XP = getXp(BASE_XP, 4);
	private static final float SPEED = getModulatedSpeed(Laser.SPEED, 4);
	public static final Pool<LaserNv4> POOL = Pools.get(LaserNv4.class);
	protected static final Phase[] PHASES = {
		new Phase(				Behavior.SINK,				Gatling.LASER,				Shot.V,				Animations.AILE_DEPLOYEES				), 
		new Phase(				Behavior.GO_AWAY,			Gatling.LASER,				Shot.V,				Animations.AILE_DEPLOYEES				)		};
	private int numeroTir;
	private static final float PHASE_DURATION = 20;
	
	
	@Override
	public void init() {
		numeroTir = 3;
		super.init();
	}
	@Override	public float getBulletSpeedMod() {			return 1f;	}
	@Override	public void free() {						POOL.free(this);	}
	@Override	public float getSpeed() {					return SPEED;	}
	@Override	protected int getMaxHp() {					return HP;	}
	@Override	public int getXp() {						return XP;	}
	@Override	public int getBonusValue() {				return BASE_XP;	}
	@Override	protected String getLabel() {				return getClass().toString();	}
	@Override	public int getShotNumber() {				return numeroTir;	}
	@Override	public void addShots(int i) {				numeroTir += i;	}
	@Override	public Phase[] getPhases() {				return PHASES;						}
	@Override	public float getPhaseDuration() {			return PHASE_DURATION;	}
}
