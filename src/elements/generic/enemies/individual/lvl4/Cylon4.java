package elements.generic.enemies.individual.lvl4;

import assets.sprites.Animations;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.components.Phase;
import elements.generic.components.behavior.Behavior;
import elements.generic.components.shots.Gatling;
import elements.generic.components.shots.Shot;
import elements.generic.enemies.individual.lvl3.Cylon3;
import jeu.Stats;

public class Cylon4 extends Cylon3 {
	
	public static final Pool<Cylon4> POOL = Pools.get(Cylon4.class);
	private static final int HP = getModulatedPv(Stats.HP_CYLON, 4), HP_BAD = (int) (HP * 0.66f), HP_WORST = (int) (HP * 0.33f), XP = getXp(BASE_XP, 4);
	private static final Phase[] PHASES = {
		new Phase(				Behavior.STRAIGHT_ON,				Gatling.METEORITE,				Shot.TIR_TOUT_DROIT,			Animations.CYLON_GREEN_GOOD				),
		new Phase(				Behavior.STRAIGHT_ON,				Gatling.METEORITE,				Shot.TIR_TOUT_DROIT,			Animations.CYLON_GREEN_BAD				),
		new Phase(				Behavior.STRAIGHT_ON,				Gatling.METEORITE,				Shot.TIR_TOUT_DROIT,			Animations.CYLON_GREEN_WORST				)		};
	
	@Override	protected String getLabel() {			return getClass().toString();					}
	@Override	public void free() {					POOL.free(this);								}
	@Override   protected int getPvWorst() {			return HP_WORST;								}
	@Override	public int getBonusValue() {			return BASE_XP;									}
	@Override 	protected int getPvBad() {				return HP_BAD;									}
	@Override	public Phase[] getPhases() {			return PHASES;									}
	@Override	public int getColor() {					return GREEN;									}
	@Override	protected int getMaxHp() {				return HP;										}
	@Override	public int getXp() {					return XP;										}
}
