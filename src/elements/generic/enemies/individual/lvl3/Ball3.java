package elements.generic.enemies.individual.lvl3;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.enemies.individual.lvl1.Ball;
import elements.generic.weapons.player.PlayerWeapon;
import jeu.Stats;

public class Ball3 extends Ball {
	
	public static final Pool<Ball3> POOL = Pools.get(Ball3.class);
	private static final int HP = getModulatedPv(Stats.HP_BALL, 3), XP = getXp(BASE_XP, 3);
	protected static final float SPEED = getModulatedSpeed(Ball.SPEED, 3);

	@Override	protected String getLabel() {					return getClass().toString();			}
	@Override	public void free() {							POOL.free(this);						}
	@Override	public int getBonusValue() {					return BASE_XP;							}
	@Override	public float getSpeed() {						return SPEED;							}
	@Override	protected int getMaxHp() {						return HP;								}
	@Override	public int getXp() {							return XP;								}
	@Override	public boolean stillAlive(PlayerWeapon a) {
		nextShot -= 1;
		return super.stillAlive(a);					
	}
}
