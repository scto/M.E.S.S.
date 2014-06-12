package elements.generic.enemies.individual.lvl4;

import jeu.Stats;
import assets.sprites.Animations;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.components.Phase;
import elements.generic.components.behavior.Behavior;
import elements.generic.enemies.individual.lvl1.ZigZag;
import elements.generic.enemies.individual.lvl3.ZigZag3;

public class ZigZag4 extends ZigZag3 {

	public static final Pool<ZigZag4> POOL = Pools.get(ZigZag4.class);
	private static final int LVL = 4, HP = getModulatedPv(Stats.HP_ZIGZAG, LVL), XP = getXp(BASE_XP, LVL);
	private static final float SPEED = getModulatedSpeed(ZigZag.SPEED, LVL);
	private static final Vector2 smokeVector = new Vector2(0, Stats.u);
	protected static final Phase[] PHASES = {
		new Phase(				Behavior.ZIG_ZAG,							null,				null,				Animations.ZIG_ZAG_GREEN				)	};
	
	@Override	protected String getLabel() {				return getClass().toString();	}
	@Override	protected Vector2 getSmokeVector() {		return smokeVector;				}
	@Override	public void free() {						POOL.free(this);				}
	@Override	public int getBonusValue() {				return BASE_XP;					}
	@Override	public Phase[] getPhases() {				return PHASES;					}
	@Override	public float getSpeed() {					return SPEED;					}
	@Override	public int getColor() {						return GREEN;					}
	@Override	protected int getMaxHp() {					return HP;						}
	@Override	public int getXp() {						return XP;						}
}
