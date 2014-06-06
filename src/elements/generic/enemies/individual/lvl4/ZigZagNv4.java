package elements.generic.enemies.individual.lvl4;

import jeu.Stats;
import assets.sprites.Animations;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.components.Phase;
import elements.generic.components.behavior.Behavior;
import elements.generic.enemies.individual.lvl1.ZigZag;
import elements.generic.enemies.individual.lvl3.ZigZagNv3;

public class ZigZagNv4 extends ZigZagNv3 {

	public static final Pool<ZigZagNv4> POOL = Pools.get(ZigZagNv4.class);
	private static final int LVL = 4;
	private static final int HP = getModulatedPv(Stats.HP_ZIGZAG, LVL);
	private static final int XP = getXp(BASE_XP, LVL);
	private static final float SPEED = getModulatedSpeed(ZigZag.SPEED, LVL);
	private static final Vector2 smokeVector = new Vector2(0, Stats.u);
	protected static final Phase[] PHASES = {
		new Phase(				Behavior.ZIG_ZAG,							null,				null,				Animations.ZIG_ZAG_GREEN				)	};
	
	@Override	protected Vector2 getSmokeVector() {		return smokeVector;	}
	@Override	public int getColor() {								return GREEN;	}
	@Override	public void free() {						POOL.free(this);	}
	@Override	protected int getMaxHp() {					return HP;	}
	@Override	public int getXp() {						return XP;	}
	@Override	public int getBonusValue() {				return BASE_XP;	}
	@Override	public float getSpeed() {					return SPEED;	}
	@Override	protected String getLabel() {				return getClass().toString();	}
	@Override	public Phase[] getPhases() {		return PHASES;	}
}
