package elements.generic.enemies.individual.lvl3;

import jeu.Stats;
import assets.sprites.Animations;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.components.Phase;
import elements.generic.components.behavior.Behavior;
import elements.generic.enemies.individual.lvl1.ZigZag;

public class ZigZagNv3 extends ZigZag {

	public static final Pool<ZigZagNv3> POOL = Pools.get(ZigZagNv3.class);
	private static final int LVL = 3;
	private static final int HP = getModulatedPv(Stats.HP_ZIGZAG, LVL);
	private static final int XP = getXp(BASE_XP, LVL);
	private static final float SPEED = getModulatedSpeed(ZigZag.SPEED, LVL);
	private static final Vector2 smokeVector = new Vector2(0, Stats.uSur2);
	protected static final Phase[] PHASES = {
		new Phase(				Behavior.ZIG_ZAG,				null,				null,				Animations.ZIG_ZAG_BLUE				)		};
	
	protected Vector2 getSmokeVector() {				return smokeVector;		}
	@Override	public int getColor() {								return BLUE;	}
	@Override	public void free() {						POOL.free(this);	}
	@Override	protected int getMaxHp() {					return HP;	}
	@Override	public int getXp() {						return XP;	}
	@Override	public int getBonusValue() {				return BASE_XP;	}
	@Override	public float getSpeed() {					return SPEED;	}
	@Override	protected String getLabel() {				return getClass().toString();	}
	@Override	public Phase[] getPhases() {		return PHASES;	}
}
