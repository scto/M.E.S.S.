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
import elements.generic.enemies.individual.lvl1.QuiTir;
import elements.generic.enemies.individual.lvl3.QuiTirNv3;
import elements.generic.weapons.enemies.Fireball;

public class QuiTirNv4 extends QuiTirNv3 {
	
	public static final int WIDTH = Stats.WIDTH_QUI_TIR3, HALF_WIDTH = WIDTH/2, HEIGHT = Stats.HEIGHT_QUI_TIR3, HALF_HEIGHT = HEIGHT / 2; 
	public static final Pool<QuiTirNv4> POOL = Pools.get(QuiTirNv4.class);
	private static final int LVL = 4;
	private static final int HP = getModulatedPv(Stats.HP_QUI_TIR, LVL), DEMI_HP = HP / 2;
	private static final int XP = getXp(BASE_XP, LVL);
	private static final float SPEED = getModulatedSpeed(QuiTir.SPEED, LVL);
	protected static final Phase[] PHASES = {
		new Phase(				Behavior.STRAIGHT_ON_DETECT,				Gatling.FIREBALL,				Shot.SHOT_DOWN_RAND_RAFALE,				Animations.QUI_TIR_GOOD				),
		new Phase(				Behavior.STRAIGHT_ON_DETECT,				Gatling.FIREBALL,				Shot.SHOT_DOWN_RAND_RAFALE,				Animations.QUI_TIR_BAD				)		};
	
	@Override
	public Vector2 getShotPosition(int numeroTir) {
		if (numeroTir == 1) TMP_POS.x = pos.x + HALF_WIDTH - Fireball.WIDTH;
		else TMP_POS.x = pos.x + HALF_WIDTH;
		
		TMP_POS.y = pos.y - Fireball.HEIGHT + Fireball.HALF_HEIGHT;
		return TMP_POS;
	}
	
	@Override	public float getFloatFactor() {		return 0.1f;	}
	@Override	public Phase[] getPhases() {				return PHASES;	}
	@Override	public void free() {				POOL.free(this);	}
	@Override	public float getSpeed() {			return SPEED;	} 
	@Override	public int getXp() {				return XP;	}
	@Override	public int getBonusValue() {		return BASE_XP;	}
	@Override	protected int getMaxHp() {			return HP;	}
	@Override	protected int getDemiPv() {			return DEMI_HP;	}
	@Override	protected String getLabel() {		return getClass().toString();	}
}
