package elements.generic.enemies.individual.lvl3;

import jeu.Stats;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.enemies.individual.lvl1.ShooterFrag;


public class ShooterFrag3 extends ShooterFrag {
	
	public static final Pool<ShooterFrag3> POOL = Pools.get(ShooterFrag3.class);
	private static final int LVL = 3, HP = getModulatedPv(Stats.HP_SHOOTER_FRAG, LVL), DEMI_HP = HP / 2, XP = getXp(BASE_XP, LVL);
	private static final float SPEED = getModulatedSpeed(12, LVL), FIRERATE = 1f;
	
	@Override 	public float getFirerate() {		return FIRERATE;				}
	@Override	public void free() {				POOL.free(this);				}
	@Override	protected int getDemiPv() {			return DEMI_HP;					}
	@Override	public int getBonusValue() {		return BASE_XP;					}
	@Override	public float getSpeed() {			return SPEED;					}
	@Override	protected int getMaxHp() {			return HP;						}
	@Override	public int getXp() {				return XP;						}
}
