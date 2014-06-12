package elements.generic.enemies.individual.lvl4;

import jeu.Stats;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.enemies.individual.lvl1.Crusader;
import elements.generic.enemies.individual.lvl3.Crusader3;

public class Crusader4 extends Crusader3 {

	public static final Pool<Crusader4> POOL = Pools.get(Crusader4.class);
	private static final int LVL = 4, HP = getModulatedPv(Stats.CRUASER_HP, LVL), HALF_HP = HP / 2, XP = getXp(BASE_XP, LVL);
	private static final float SPEED = getModulatedSpeed(Crusader.SPEED, LVL);
	
	@Override	protected int getMaxHp() {				return HP;						}
	@Override	public int getXp() {					return XP;						}
	@Override	public float getSpeed() {				return SPEED;					}
	@Override	public int getBonusValue() {			return BASE_XP;					}
	@Override	protected int getPallierPv() {			return HALF_HP;					}
	@Override	public void free() {					POOL.free(this);				}
	@Override	protected String getLabel() {			return getClass().toString();	}
}
