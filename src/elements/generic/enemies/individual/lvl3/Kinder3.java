package elements.generic.enemies.individual.lvl3;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.components.shots.AbstractShot;
import elements.generic.components.shots.Gatling;
import elements.generic.enemies.individual.lvl1.Kinder;
import elements.generic.weapons.enemies.KinderWeapon;
import jeu.Stats;


public class Kinder3 extends Kinder {
	
	public static final Pool<Kinder3> POOL = Pools.get(Kinder3.class);
	private static final int XP = getXp(BASE_XP, 3);
	private static final float PHASE_DURATION = 20, FIRERATE = 0.14f * MOD_FIRERATE;
	private boolean alternateShot = false;
	
	@Override
	protected void shoot() {
		if (index == 1) {
			TMP_POS.x = (pos.x + DIMENSIONS.halfWidth - KinderWeapon.DIMENSIONS.halfWidth);
			TMP_POS.y = (pos.y + DIMENSIONS.halfHeight - KinderWeapon.DIMENSIONS.halfWidth);
			TMP_DIR.x = -dir.x;
			TMP_DIR.y = -dir.y;
			
			if (alternateShot)
				TMP_DIR.rotate(10);
			else
				TMP_DIR.rotate(-10);
			alternateShot = !alternateShot;
			AbstractShot.straight(Gatling.KINDER_WEAPON, TMP_POS, TMP_DIR, -1.5f);
			
			interval(-3);
		}
	}
	@Override	public float getFirerate() {		return FIRERATE;				}
	@Override	public void free() {				POOL.free(this);				}
	@Override	public int getBonusValue() {		return BASE_XP;					}
	@Override	public int getXp() {				return XP;						}
	@Override   public float getPhaseDuration() {	return PHASE_DURATION;			}
}

