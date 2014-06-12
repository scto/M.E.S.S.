package elements.generic.enemies.individual.lvl3;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.enemies.individual.lvl1.Diabolo;
import jeu.Stats;

public class Diabolo3 extends Diabolo {
	
	public static final Pool<Diabolo3> POOL = Pools.get(Diabolo3.class);
	private static final int LVL = 3, PHASE_DURATION = 19, HP = getModulatedPv(Stats.HP_DIABOLO, LVL), XP = getXp(BASE_XP, LVL);
	private static final float SPEED = getModulatedSpeed(Diabolo.SPEED, LVL), HALF_SPEED = SPEED / 2, FIRERATE = Diabolo.FIRERATE * 0.7f;

	@Override	protected String getLabel() {			return getClass().toString();	}
	@Override 	protected float getPhaseDuration() {	return PHASE_DURATION;			}
	@Override	protected float getDemiVitesse() {		return HALF_SPEED;				}
	@Override	public void free() {					POOL.free(this);				}
	@Override 	public float getFirerate() {			return FIRERATE;				}
	@Override	public int getBonusValue() {			return BASE_XP;					}
	@Override	public float getSpeed() {				return SPEED;					}
	@Override	protected int getMaxHp() {				return HP;						}
	@Override	public int getXp() {					return XP;						}
	@Override	public int getNumberOfShots() {			return 4;						}
}
