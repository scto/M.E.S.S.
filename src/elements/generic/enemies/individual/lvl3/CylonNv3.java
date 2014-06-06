package elements.generic.enemies.individual.lvl3;

import assets.sprites.Animations;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.components.Phase;
import elements.generic.components.behavior.Behavior;
import elements.generic.components.shots.Gatling;
import elements.generic.components.shots.Shot;
import elements.generic.enemies.individual.lvl1.Cylon;
import jeu.Stats;

public class CylonNv3 extends Cylon {
	
	public static final Pool<CylonNv3> POOL = Pools.get(CylonNv3.class);
	private static final int HP = getModulatedPv(Stats.HP_CYLON, 3);
	private static final int HP_BAD = (int) (HP * 0.66f), HP_WORST = (int) (HP * 0.33f);
	private static final int XP = getXp(BASE_XP, 3);
	private static final Phase[] PHASES = {
		new Phase(				Behavior.STRAIGHT_ON,				Gatling.METEORITE,				Shot.TIR_TOUT_DROIT,			Animations.CYLON_BLUE_GOOD				),
		new Phase(				Behavior.STRAIGHT_ON,				Gatling.METEORITE,				Shot.TIR_TOUT_DROIT,			Animations.CYLON_BLUE_BAD				),
		new Phase(				Behavior.STRAIGHT_ON,				Gatling.METEORITE,				Shot.TIR_TOUT_DROIT,			Animations.CYLON_BLUE_WORST				)		};
	
	@Override	public void free() {					POOL.free(this);						}
	@Override	protected int getMaxHp() {				return HP;					}
	@Override	public int getXp() {					return XP;		}
	@Override	public int getBonusValue() {			return BASE_XP;	}
	@Override	protected String getLabel() {			return getClass().toString();			}
	@Override   protected int getPvWorst() {			return HP_WORST;						}
	@Override 	protected int getPvBad() {				return HP_BAD;							}
	@Override	public int getColor() {					return BLUE;							}
	@Override	public Phase[] getPhases() {			return PHASES;							}
}
