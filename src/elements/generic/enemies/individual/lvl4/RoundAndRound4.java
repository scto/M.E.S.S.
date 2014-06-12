package elements.generic.enemies.individual.lvl4;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.enemies.individual.lvl1.RoundAndRound;
import elements.generic.enemies.individual.lvl3.RoundAndRound3;
import jeu.Stats;

public class RoundAndRound4 extends RoundAndRound3 {

	private static final int LVL = 4, HP = getModulatedPv(Stats.ROUND_AND_ROUND_HP, LVL), XP = getXp(BASE_XP, LVL);
	private static final float SPEED = getModulatedSpeed(RoundAndRound.SPEED, LVL), FIRERATE = .2f;
	public static final Pool<RoundAndRound4> POOL = Pools.get(RoundAndRound4.class);
	
	@Override	protected String getLabel() {		return getClass().toString();	}
	@Override	public float getFirerate() {		return FIRERATE;				}
	@Override	public void free() {				POOL.free(this);				}
	@Override	public int getBonusValue() {		return BASE_XP;					}
	@Override	public float getBulletSpeedMod() {	return 0.010f;					}
	@Override	public float getSpeed() {			return SPEED;					}
	@Override	public int getXp() {				return XP;						}
	@Override	protected int getMaxHp() {			return HP;						}
}
