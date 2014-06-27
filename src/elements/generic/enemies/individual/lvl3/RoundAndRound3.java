package elements.generic.enemies.individual.lvl3;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.components.shots.AbstractShot;
import elements.generic.enemies.individual.lvl1.RoundAndRound;
import jeu.Stats;

public class RoundAndRound3 extends RoundAndRound {

	public static final Pool<RoundAndRound3> POOL = Pools.get(RoundAndRound3.class);
	private static final int LVL = 3, HP = getModulatedPv(Stats.ROUND_AND_ROUND_HP, LVL), XP = getXp(BASE_XP, LVL);
	private static final float SPEED = RoundAndRound.SPEED20 * Stats.VNV3, FIRERATE = RoundAndRound.FIRERATE * 0.7f;
	
	@Override
	protected void shoot() {
		super.shoot();
		shoot(-1);
	}
	protected void interval() {
		shotInterval = AbstractShot.interval(this, 12, 1.5f, shotInterval);
	}
	@Override	public float getFirerate() {		return FIRERATE;	}
	@Override	public void free() {				POOL.free(this);				}
	@Override	public int getBonusValue() {		return BASE_XP;					}
	@Override	public float getSpeed() {			return SPEED;					}
	@Override	protected int getMaxHp() {			return HP;						}
	@Override	public int getXp() {				return XP;						}
}
