package elements.generic.enemies.individual.lvl3;

import jeu.Stats;
import assets.sprites.Animations;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.components.Phase;
import elements.generic.components.behavior.Behavior;
import elements.generic.enemies.individual.lvl1.DeBase;

public class DeBaseNv3 extends DeBase {
	
	public static final Pool<DeBaseNv3> POOL = Pools.get(DeBaseNv3.class);
	private static final int HP = getModulatedPv(Stats.HP_DE_BASE, 4);
	private static final int XP = getXp(BASE_XP, 4);
	private static final float SPEED = DeBase.SPEED * 2;
	private static final Phase[] PHASES = {		new Phase(				Behavior.STRAIGHT_ON,				null,				null,				Animations.BASIC_ENEMY_BLUE				)};
	
	@Override	protected String getLabel() {				return getClass().toString();	}
	@Override	public void free() {						POOL.free(this);				}
	@Override	public int getBonusValue() {				return BASE_XP;					}
	@Override	public Phase[] getPhases() {				return PHASES;					}
	@Override	public float getSpeed() {					return SPEED;					}
	@Override	public int getColor() {						return BLUE;					}
	@Override	protected int getMaxHp() {					return HP;						}
	@Override	public int getXp() {						return XP;						}
}
