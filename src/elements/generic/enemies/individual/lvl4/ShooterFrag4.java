package elements.generic.enemies.individual.lvl4;

import jeu.Stats;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.enemies.individual.lvl1.ShooterFrag;
import elements.generic.enemies.individual.lvl3.ShooterFrag3;


public class ShooterFrag4 extends ShooterFrag3 {
	
	public static final Pool<ShooterFrag4> POOL = Pools.get(ShooterFrag4.class);
	private static final int LVL = 4, HP = getModulatedPv(Stats.HP_SHOOTER_FRAG, LVL), DEMI_HP = HP / 2, XP = getXp(BASE_XP, LVL);
	private static final float SPEED = getModulatedSpeed(12, LVL);
	
	@Override	public void free() {						POOL.free(this);							}
	@Override	protected int getDemiPv() {					return DEMI_HP;								}
	@Override	public int getBonusValue() {				return BASE_XP;								}
	@Override	public float getSpeed() {					return SPEED;								}
	@Override	protected int getMaxHp() {					return HP;									}
	@Override	public int getXp() {						return XP;									}
}
