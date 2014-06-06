package elements.generic.enemies.individual.lvl3;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.enemies.individual.lvl1.RoundAndRound;
import jeu.Stats;

public class RoundAndRound3 extends RoundAndRound {

	public static final Pool<RoundAndRound3> POOL = Pools.get(RoundAndRound3.class);
	private static final int LVL = 3;
	private static final int HP = getModulatedPv(Stats.ROUND_AND_ROUND_HP, LVL);
	private static final int XP = getXp(BASE_XP, LVL);
	private static final float SPEED = getModulatedSpeed(RoundAndRound.SPEED, LVL);
	
	@Override	public void free() {				POOL.free(this);	}
	@Override	protected int getMaxHp() {			return HP;	}
	@Override	public int getXp() {				return XP;	}
	@Override	public int getBonusValue() {		return BASE_XP;	}
	@Override	public float getSpeed() {			return SPEED;	}
	@Override	protected String getLabel() {		return getClass().toString();	}
	@Override	public float getBulletSpeedMod() {	return 0.009f;	}
}
