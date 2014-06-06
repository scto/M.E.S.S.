package elements.generic.enemies.individual.lvl3;

import jeu.CSG;
import jeu.Stats;
import assets.sprites.Animations;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.components.Phase;
import elements.generic.components.behavior.Behavior;
import elements.generic.components.shots.Gatling;
import elements.generic.components.shots.Shot;
import elements.generic.enemies.individual.lvl1.QuiTir;

public class QuiTirNv3 extends QuiTir {
	
	public static final int WIDTH = Stats.WIDTH_QUI_TIR3, HALF_WIDTH = WIDTH/2, HEIGHT = Stats.HEIGHT_QUI_TIR3, HALF_HEIGHT = HEIGHT / 2; 
	public static final Pool<QuiTirNv3> POOL = Pools.get(QuiTirNv3.class);
	private static final int LVL = 3;
	private static final int HP = getModulatedPv(Stats.HP_QUI_TIR, LVL), DEMI_HP = HP / 2;
	private static final int XP = getXp(BASE_XP, LVL);
	private static final float SPEED = getModulatedSpeed(QuiTir.SPEED, LVL);
	private int shotNumber = 0;
	protected static final Phase[] PHASES = {
		new Phase(				Behavior.STRAIGHT_ON,				Gatling.FIREBALL,				Shot.SHOT_DOWN_RAND_RAFALE,				Animations.QUI_TIR_GOOD				),
		new Phase(				Behavior.STRAIGHT_ON,				Gatling.FIREBALL,				Shot.SHOT_DOWN_RAND_RAFALE,				Animations.QUI_TIR_BAD				)		};
	protected static final float FIRERATE = QuiTir.FIRERATE * 0.5f;
	
	@Override	public Phase[] getPhases() {				return PHASES;	}
	@Override	public void free() {				POOL.free(this);	}
	@Override	public float getSpeed() {			return SPEED;	} 
	@Override	public int getXp() {				return XP;	}
	@Override	public int getBonusValue() {		return BASE_XP;	}
	@Override	protected int getMaxHp() {			return HP;	}
	@Override	protected int getDemiPv() {			return DEMI_HP;	}
	@Override	public float getHeight() {			return HEIGHT;	}
	@Override	public float getWidth() {				return WIDTH;	}
	@Override	public float getHalfHeight() {		return HALF_HEIGHT;	}
	@Override	public float getHalfWidth() {			return HALF_WIDTH;	}
	@Override	protected String getLabel() {		return getClass().toString();	}
	@Override	public int getShotNumber() {			return shotNumber;	}
	@Override	public void addShots(int i) {			shotNumber += i;	}
	@Override	public int getNumberOfShots() {			return CSG.R.nextInt(4);	}
	@Override	public float getFirerate() {		return FIRERATE;	}
}
