package elements.generic.enemies.individual.lvl3;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.enemies.individual.lvl1.QuiTourne;
import jeu.Stats;

public class QuiTourneNv3 extends QuiTourne {
	
	public static final Pool<QuiTourneNv3> POOL = Pools.get(QuiTourneNv3.class);
	private static final int LVL = 3, PHASE_DURATION = 19;
	private static final int HP = getModulatedPv(Stats.HP_QUI_TOURNE, LVL);
	private static final int XP = getXp(BASE_XP, LVL);
	private static final float SPEED = getModulatedSpeed(QuiTourne.SPEED, LVL), HALF_SPEED = SPEED / 2, FIRERATE = QuiTourne.FIRERATE * 0.7f;

	@Override	public int getNumberOfShots() {			return 4;				}
	@Override	public void free() {				POOL.free(this);	}
	@Override	protected int getMaxHp() {			return HP;	}
	@Override	public float getSpeed() {			return SPEED;	}
	@Override	protected float getDemiVitesse() {	return HALF_SPEED;	}
	@Override	public int getXp() {				return XP;	}
	@Override	public int getBonusValue() {		return BASE_XP;	}
	@Override	protected String getLabel() {		return getClass().toString();	}
	@Override 	public float getFirerate() {			return FIRERATE;								}
	@Override 	protected float getPhaseDuration() {
		return PHASE_DURATION;
	}
}
