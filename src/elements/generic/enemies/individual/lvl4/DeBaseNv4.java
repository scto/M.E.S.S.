package elements.generic.enemies.individual.lvl4;

import jeu.Stats;
import assets.sprites.Animations;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.components.Phase;
import elements.generic.components.behavior.Behavior;
import elements.generic.enemies.individual.lvl1.DeBase;
import elements.generic.enemies.individual.lvl3.DeBaseNv3;

public class DeBaseNv4 extends DeBaseNv3 {
	
	public static final Pool<DeBaseNv4> POOL = Pools.get(DeBaseNv4.class);
	private static final int HP = getModulatedPv(Stats.HP_DE_BASE, 4);
	private static final int XP = getXp(BASE_XP, 4);
	private static final float SPEED = DeBase.SPEED * 4;
	private static final Phase[] PHASES = {		new Phase(				Behavior.STRAIGHT_ON_DETECT,				null,				null,				Animations.BASIC_ENEMY_GREEN				)};
	
	@Override	public float getFloatFactor() {				return 1;	}
	@Override	public Phase[] getPhases() {				return PHASES;					}
	@Override	public int getColor() {						return GREEN;	}
	@Override	protected String getLabel() {				return getClass().toString();				}
	@Override	public void free() {						POOL.free(this);							}
	@Override	protected int getMaxHp() {					return HP;				}
	@Override	public float getSpeed() {					return SPEED;				}
	@Override	public int getXp() {						return XP;				}
	@Override	public int getBonusValue() {				return BASE_XP;		}
}
 