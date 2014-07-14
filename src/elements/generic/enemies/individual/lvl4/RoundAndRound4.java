package elements.generic.enemies.individual.lvl4;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.components.behavior.Mover;
import elements.generic.enemies.individual.lvl1.RoundAndRound;
import elements.generic.enemies.individual.lvl3.RoundAndRound3;
import jeu.CSG;
import jeu.Stats;
import jeu.mode.EndlessMode;

public class RoundAndRound4 extends RoundAndRound3 {

	private static final int LVL = 4, HP = getModulatedPv(Stats.ROUND_AND_ROUND_HP, LVL), XP = getXp(BASE_XP, LVL);
	private static final float SPEED = RoundAndRound.SPEED20 * Stats.SLVL4, FIRERATE = .2f;
	public static final Pool<RoundAndRound4> POOL = Pools.get(RoundAndRound4.class);
	private static float tmp;
	
	@Override
	protected void move() {
		if (now < 6.5f)
			super.move();
		else {
			tmp = Math.min(now - 6f, 3);
			if (pos.x + DIMENSIONS.halfWidth > CSG.gameZoneHalfWidth) {
				tmp *= 90;
				Mover.rotate(this, tmp);
				angle += tmp * EndlessMode.deltaDiv2;
			} else { 
				tmp *= -90;
				Mover.rotate(this, tmp);
				angle += tmp * EndlessMode.deltaDiv2;
			}
		}
	}
	@Override	public float getFirerate() {		return FIRERATE;				}
	@Override	public void free() {				POOL.free(this);				}
	@Override	public int getBonusValue() {		return BASE_XP;					}
	@Override	public float getSpeed() {			return SPEED;					}
	@Override	public int getXp() {				return XP;						}
	@Override	protected int getMaxHp() {			return HP;						}
}
