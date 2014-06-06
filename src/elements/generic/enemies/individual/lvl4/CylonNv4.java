package elements.generic.enemies.individual.lvl4;

import assets.sprites.Animations;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.components.Phase;
import elements.generic.components.behavior.Behavior;
import elements.generic.components.shots.Gatling;
import elements.generic.components.shots.Shot;
import elements.generic.enemies.individual.lvl3.CylonNv3;
import jeu.Stats;

public class CylonNv4 extends CylonNv3 {
	
	public static final Pool<CylonNv4> POOL = Pools.get(CylonNv4.class);
	private static final int HP = getModulatedPv(Stats.HP_CYLON, 4);
	private static final int HP_BAD = (int) (HP * 0.66f), HP_WORST = (int) (HP * 0.33f);
	private static final int XP = getXp(BASE_XP, 4);
	private static final Phase[] PHASES = {
		new Phase(				Behavior.STRAIGHT_ON,				Gatling.METEORITE,				Shot.TIR_TOUT_DROIT,			Animations.CYLON_GREEN_GOOD				),
		new Phase(				Behavior.STRAIGHT_ON,				Gatling.METEORITE,				Shot.TIR_TOUT_DROIT,			Animations.CYLON_GREEN_BAD				),
		new Phase(				Behavior.STRAIGHT_ON,				Gatling.METEORITE,				Shot.TIR_TOUT_DROIT,			Animations.CYLON_GREEN_WORST				)		};
	
	@Override	public void free() {					POOL.free(this);								}
	@Override	protected int getMaxHp() {				return HP;							}
	@Override	public int getXp() {					return XP;				}
	@Override	public int getBonusValue() {			return BASE_XP;			}
	@Override	protected String getLabel() {			return getClass().toString();					}
	@Override   protected int getPvWorst() {			return HP_WORST;						}
	@Override 	protected int getPvBad() {				return HP_BAD;							}
	@Override	public int getColor() {					return GREEN;					}
	@Override	public Phase[] getPhases() {			return PHASES;							}
}
