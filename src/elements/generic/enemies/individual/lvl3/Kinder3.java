package elements.generic.enemies.individual.lvl3;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.enemies.individual.lvl1.Kinder;
import jeu.Stats;


public class Kinder3 extends Kinder {
	
	public static final Pool<Kinder3> POOL = Pools.get(Kinder3.class);
	private static final int HP = getModulatedPv(Stats.KINDER_HP, 3), XP = getXp(BASE_XP, 3);
	private static final float SPEED = getModulatedSpeed(Kinder.SPEED, 3), PHASE_DURATION = 15;
	
	@Override	protected String getLabel() {		return getClass().toString();	}
	@Override	public void free() {				POOL.free(this);				}
	@Override	public int getBonusValue() {		return BASE_XP;					}
	@Override	public float getSpeed() {			return SPEED;					}
	@Override	protected int getMaxHp() {			return HP;						}
	@Override	public int getXp() {				return XP;						}
	@Override	public float getBulletSpeedMod() {		return -1.5f;					}
	public static float getPhaseDuration() {		return PHASE_DURATION;			}
}

