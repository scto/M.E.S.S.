package elements.generic.enemies.individual.lvl4;

import jeu.Stats;
import assets.sprites.Animations;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.components.Phase;
import elements.generic.components.behavior.Behavior;
import elements.generic.enemies.individual.lvl1.Basic;
import elements.generic.enemies.individual.lvl3.Basic3;

public class Basic4 extends Basic3 {
	
	public static final Pool<Basic4> POOL = Pools.get(Basic4.class);
	private static final int HP = getModulatedPv(Stats.HP_DE_BASE, 4), XP = getXp(BASE_XP, 4);
	private static final float SPEED = Basic.SPEED * 4;
	private static final Phase[] PHASES = {		new Phase(				Behavior.STRAIGHT_ON_DETECT,				null,				null,				Animations.BASIC_ENEMY_GREEN				)};
	
	@Override	protected String getLabel() {				return getClass().toString();		}
	@Override	public void free() {						POOL.free(this);					}
	@Override	public int getBonusValue() {				return BASE_XP;						}
	@Override	public Phase[] getPhases() {				return PHASES;						}
	@Override	public int getColor() {						return GREEN;						}
	@Override	public float getSpeed() {					return SPEED;						}
	@Override	protected int getMaxHp() {					return HP;							}
	@Override	public int getXp() {						return XP;							}
	@Override	public float getFloatFactor() {				return 1;							}
}
 