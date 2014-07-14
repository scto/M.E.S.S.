package elements.generic.enemies.individual.lvl4;

import jeu.Stats;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.components.shots.AbstractShot;
import elements.generic.components.shots.Gatling;
import elements.generic.enemies.individual.lvl1.Plane;
import elements.generic.enemies.individual.lvl3.Plane3;
import elements.generic.weapons.enemies.Fireball;

public class Plane4 extends Plane3 {
	
	public static final Pool<Plane4> POOL = Pools.get(Plane4.class);
	private static final int HP = getModulatedPv(Stats.PLANE_HP, 4), DEMI_HP = HP / 2, XP = getXp(BASE_XP, 4);
	private static final float SPEED = Plane.SPEED19 * Stats.SLVL4, HALF_SPEED = SPEED / 2, FIRERATE = Plane3.FIRERATE * 0.8f * MOD_FIRERATE,  OFFSET_WEAPON_RIGHT = (int) (DIMENSIONS.width - Fireball.DIMENSIONS.halfWidth * 1.5f), OFFSET_WEAPON_LEFT = Fireball.DIMENSIONS.halfWidth / 2, OFFSET_WEAPON_Y = DIMENSIONS.halfHeight - Fireball.DIMENSIONS.height;
	
	@Override
	public float getSpeed() {
		if (isInGoodShape())
			return SPEED;
		return HALF_SPEED;
	}
	@Override
	protected void shootDouble() {
		TMP_POS.set(pos.x - OFFSET_WEAPON_LEFT, pos.y + OFFSET_WEAPON_Y);
		AbstractShot.shootDownRandom(Gatling.FIREBALL, TMP_POS, Stats.U20, 10);
		TMP_POS.set(pos.y + OFFSET_WEAPON_Y, pos.x + OFFSET_WEAPON_RIGHT);
		AbstractShot.shootDownRandom(Gatling.FIREBALL, TMP_POS, Stats.U20, 10);
	}
	@Override
	protected void shootSingle() {
		AbstractShot.shootDownRandom(Gatling.FIREBALL, TMP_POS, Stats.U20, 10);
	}
	@Override	protected int getMaxHp() {				return HP;							}
	@Override	public int getXp() {					return XP;							}
	@Override	public int getBonusValue() {			return BASE_XP;						}
	@Override	protected int getHalfHp() {				return DEMI_HP;						}
	@Override	public void free() {					POOL.free(this);					}
	@Override	public float getFirerate() {			return FIRERATE;					}
}
