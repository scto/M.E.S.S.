package elements.generic.enemies.individual.lvl4;

import assets.sprites.Animations;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.components.Phase;
import elements.generic.components.behavior.Behavior;
import elements.generic.components.shots.Gatling;
import elements.generic.components.shots.Shot;
import elements.generic.enemies.individual.lvl1.Kinder;
import elements.generic.enemies.individual.lvl3.KinderNv3;
import jeu.Stats;


public class KinderNv4 extends KinderNv3 {
	
	public static final Pool<KinderNv4> POOL = Pools.get(KinderNv4.class);
	private static final int HP = getModulatedPv(Stats.KINDER_HP, 4);
	private static final int XP = getXp(BASE_XP, 4);
	private static final float SPEED = getModulatedSpeed(Kinder.SPEED, 4);
	private static final float PHASE_DURATION = 18;
	private static final Phase[] PHASES = {
		new Phase(				Behavior.STRAIGHT_ON,				Gatling.KINDER_WEAPON,				null,						Animations.KINDER_OPENING				),
		new Phase(				Behavior.ROTATE,					Gatling.KINDER_WEAPON,				Shot.V,						Animations.KINDER_OPEN					),
		new Phase(				Behavior.GO_AWAY,					Gatling.KINDER_WEAPON,				Shot.V,						Animations.KINDER_OPEN					),		};
	
	@Override	public float getBulletSpeedMod() {		return -1.5f;	}
	@Override	public Phase[] getPhases() {			return PHASES;	}
	@Override	public void free() {					POOL.free(this);	}
	@Override	protected int getMaxHp() {				return HP;	}
	@Override	public int getXp() {					return XP;	}
	@Override	public int getBonusValue() {			return BASE_XP;	}
	@Override	public float getSpeed() {				return SPEED;	}
	@Override	protected String getLabel() {			return getClass().toString();	}
	public static float getPhaseDuration() {
		return PHASE_DURATION;
	}
}

