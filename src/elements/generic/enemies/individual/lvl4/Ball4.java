package elements.generic.enemies.individual.lvl4;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.enemies.individual.lvl1.Ball;
import elements.generic.enemies.individual.lvl3.Ball3;
import elements.generic.weapons.player.PlayerWeapon;
import jeu.Stats;

public class Ball4 extends Ball3 {
	
	public static final Pool<Ball4> POOL = Pools.get(Ball4.class);
	private static final int HP = getModulatedPv(Stats.HP_BALL, 4), XP = getXp(BASE_XP, 4);
	protected static final float SPEED = getModulatedSpeed(Ball.SPEED, 4);
	
	@Override	protected String getLabel() {					return getClass().toString();				}
	@Override	public void free() {							POOL.free(this);							}
	@Override	public int getBonusValue() {					return BASE_XP;								}
	@Override	public float getSpeed() {						return SPEED;								}
	@Override	protected int getMaxHp() {						return HP;									}
	@Override	public int getXp() {							return XP;									}
	@Override	public boolean stillAlive(PlayerWeapon a) {
		nextShot -= 1.5f;
		return super.stillAlive(a);					
	}
}
