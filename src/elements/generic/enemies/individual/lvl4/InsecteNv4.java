package elements.generic.enemies.individual.lvl4;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.enemies.individual.lvl1.Insect;
import jeu.Stats;


public class InsecteNv4 extends Insect {
	
	public static final Pool<InsecteNv4> POOL = Pools.get(InsecteNv4.class);
	private static final int HP = getModulatedPv(Stats.INSECT_HP, 4);
	private static final int XP = getXp(BASE_XP, 4);
	private static final float SPEED = getModulatedSpeed(Insect.SPEED, 4);
	
	/*
	 * Used for the number of shots rnd
	 */
	@Override	public int getIntFactor() {					return 7;	}
	/**
	 * Used for the minimal number of bullets
	 */
	@Override	public int getNumberOfShots() {				return 7;	}
	@Override	public void free() {				POOL.free(this);												}
	@Override	protected String getLabel() {		return getClass().toString();									}
	@Override	protected int getMaxHp() {			return HP;	}
	@Override	public int getXp() {				return XP;	}
	@Override	public int getBonusValue() {		return BASE_XP;	}
	@Override	public float getSpeed() {			return SPEED;												}
}
