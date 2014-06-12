package elements.generic.enemies.individual.lvl3;

import jeu.Stats;
import assets.sprites.Animations;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.components.Phase;
import elements.generic.components.behavior.Behavior;
import elements.generic.enemies.individual.lvl1.ZigZag;

public class ZigZag3 extends ZigZag {

	public static final Pool<ZigZag3> POOL = Pools.get(ZigZag3.class);
	public static final int PK = 18, LVL = 3, HP = getModulatedPv(Stats.HP_ZIGZAG, LVL), XP = getXp(BASE_XP, LVL);
	private static final float SPEED = getModulatedSpeed(ZigZag.SPEED * 1.2f, LVL);
	private static final Vector2 smokeVector = new Vector2(0, Stats.uSur2);
	protected static final Phase[] PHASES = {
		new Phase(				Behavior.ZIG_ZAG,				null,				null,				Animations.ZIG_ZAG_BLUE				)		};
	
	@Override	protected String getLabel() {				return getClass().toString();	}
	@Override	public void free() {						POOL.free(this);				}
	@Override	public int getBonusValue() {				return BASE_XP;					}
	@Override	public Phase[] getPhases() {				return PHASES;					}
	@Override	public float getSpeed() {					return SPEED;					}
	@Override	public int getColor() {						return BLUE;					}
	@Override	protected int getMaxHp() {					return HP;						}
	@Override	public int getXp() {						return XP;						}
	protected Vector2 getSmokeVector() {					return smokeVector;				}
}
