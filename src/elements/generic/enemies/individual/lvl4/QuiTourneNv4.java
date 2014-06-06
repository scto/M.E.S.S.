package elements.generic.enemies.individual.lvl4;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.enemies.individual.lvl1.QuiTourne;
import elements.generic.enemies.individual.lvl3.QuiTourneNv3;
import jeu.Stats;

public class QuiTourneNv4 extends QuiTourneNv3 {
	
	public static final Pool<QuiTourneNv4> POOL = Pools.get(QuiTourneNv4.class);
	private static final int LVL = 4, PHASE_DURATION = 24;
	private static final int HP = getModulatedPv(Stats.HP_QUI_TOURNE, LVL);
	private static final int XP = getXp(BASE_XP, LVL);
	private static final float SPEED = getModulatedSpeed(QuiTourne.SPEED, LVL), HALF_SPEED = SPEED / 2, FIRERATE = QuiTourne.FIRERATE * 0.4f;

	@Override	public int getNumberOfShots() {			return 6;				}
	@Override	public void free() {				POOL.free(this);								}
	@Override	protected int getMaxHp() {			return HP;	}
	@Override	public float getSpeed() {			return SPEED;	}
	@Override	protected float getDemiVitesse() {	return HALF_SPEED;	}
	@Override	public int getXp() {				return XP;	}
	@Override	public int getBonusValue() {		return BASE_XP;	}
	@Override	protected String getLabel() {		return getClass().toString();					}
	@Override 	public float getFirerate() {			return FIRERATE;								}
	@Override 	protected float getPhaseDuration() {
		return PHASE_DURATION;
	}
}
