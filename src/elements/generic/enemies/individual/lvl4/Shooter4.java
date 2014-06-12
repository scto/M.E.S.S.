package elements.generic.enemies.individual.lvl4;

import jeu.Stats;
import assets.sprites.Animations;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.components.Phase;
import elements.generic.components.behavior.Behavior;
import elements.generic.components.shots.Gatling;
import elements.generic.components.shots.Shot;
import elements.generic.enemies.individual.lvl1.Shooter;
import elements.generic.enemies.individual.lvl3.Shooter3;
import elements.generic.weapons.enemies.Fireball;

public class Shooter4 extends Shooter3 {
	
	public static final int WIDTH = Stats.WIDTH_QUI_TIR3, HALF_WIDTH = WIDTH/2, HEIGHT = Stats.HEIGHT_QUI_TIR3, HALF_HEIGHT = HEIGHT / 2, LVL = 4, HP = getModulatedPv(Stats.HP_QUI_TIR, LVL), DEMI_HP = HP / 2, XP = getXp(BASE_XP, LVL);
	public static final Pool<Shooter4> POOL = Pools.get(Shooter4.class);
	private static final float SPEED = getModulatedSpeed(Shooter.SPEED, LVL);
	protected static final Phase[] PHASES = {
		new Phase(				Behavior.STRAIGHT_ON_DETECT,				Gatling.FIREBALL,				Shot.SHOT_DOWN_RAND_RAFALE,				Animations.SHOOTER_GOOD				),
		new Phase(				Behavior.STRAIGHT_ON_DETECT,				Gatling.FIREBALL,				Shot.SHOT_DOWN_RAND_RAFALE,				Animations.SHOOTER_BAD				)		};
	
	@Override
	public Vector2 getShotPosition(int numeroTir) {
		if (numeroTir == 1) TMP_POS.x = pos.x + HALF_WIDTH - Fireball.WIDTH;
		else TMP_POS.x = pos.x + HALF_WIDTH;
		
		TMP_POS.y = pos.y - Fireball.HEIGHT + Fireball.HALF_HEIGHT;
		return TMP_POS;
	}
	
	@Override	public int getXp() {				return XP;						}
	@Override	protected int getMaxHp() {			return HP;						}
	@Override	public float getFloatFactor() {		return 0.1f;					}
	@Override	public float getSpeed() {			return SPEED;					}		 
	@Override	public Phase[] getPhases() {		return PHASES;					}
	@Override	public int getBonusValue() {		return BASE_XP;					}
	@Override	protected int getDemiPv() {			return DEMI_HP;					}
	@Override	public void free() {				POOL.free(this);				}
	@Override	protected String getLabel() {		return getClass().toString();	}
}
