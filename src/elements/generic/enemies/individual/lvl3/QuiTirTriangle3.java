package elements.generic.enemies.individual.lvl3;

import jeu.Stats;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.enemies.individual.lvl1.QuiTirTriangle;


public class QuiTirTriangle3 extends QuiTirTriangle {
	
	public static final Pool<QuiTirTriangle3> POOL = Pools.get(QuiTirTriangle3.class);
	private static final int LVL = 3;
	private static final int HP = getModulatedPv(Stats.HP_DE_BASE_QUI_TIR_TRIANGLE, LVL), DEMI_HP = HP / 2;
	private static final int XP = getXp(BASE_XP, LVL);
	private static final float SPEED = getModulatedSpeed(QuiTirTriangle.SPEED, LVL);
	
	@Override	public void free() {				POOL.free(this);	}
	@Override	protected int getMaxHp() {			return HP;	}
	@Override	protected int getDemiPv() {			return DEMI_HP;}
	@Override	public int getXp() {				return XP;	}
	@Override	public int getBonusValue() {		return BASE_XP;	}
	@Override	public float getSpeed() {			return SPEED;	}
	@Override	protected String getLabel() {		return getClass().toString();	}
}
