package elements.generic.enemies.individual.lvl4;

import jeu.Stats;
import assets.sprites.Animations;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.components.Phase;
import elements.generic.components.behavior.Behavior;
import elements.generic.components.shots.Gatling;
import elements.generic.components.shots.Shot;
import elements.generic.enemies.individual.lvl1.QuiTirTriangle;
import elements.generic.enemies.individual.lvl3.QuiTirTriangle3;


public class QuiTirTriangle4 extends QuiTirTriangle3 {
	
	public static final Pool<QuiTirTriangle4> POOL = Pools.get(QuiTirTriangle4.class);
	private static final int LVL = 4;
	private static final int HP = getModulatedPv(Stats.HP_DE_BASE_QUI_TIR_TRIANGLE, LVL), DEMI_HP = HP / 2;
	private static final int XP = getXp(BASE_XP, LVL);
	private static final float SPEED = getModulatedSpeed(QuiTirTriangle.SPEED, LVL);
	protected static final Phase[] PHASES = {
		new Phase(				Behavior.STRAIGHT_ON_DETECT,				Gatling.FRAG,				Shot.SHOT_DOWN,				Animations.QUI_TIR_GOOD				),
		new Phase(				Behavior.STRAIGHT_ON_DETECT,				Gatling.FRAG,				Shot.SHOT_DOWN,				Animations.QUI_TIR_BAD				),
		};
	
	@Override	public float getFloatFactor() {				return 0.1f;	}
	@Override	protected int getMaxHp() {					return HP;	}
	@Override	protected int getDemiPv() {					return DEMI_HP;}
	@Override	public int getXp() {						return XP;	}
	@Override	public int getBonusValue() {				return BASE_XP;	}
	@Override	public float getSpeed() {					return SPEED;	}
	@Override	protected String getLabel() {				return getClass().toString();								}
	@Override	public void free() {						POOL.free(this);												}
	@Override	public Phase[] getPhases() {				return PHASES;	}
}
