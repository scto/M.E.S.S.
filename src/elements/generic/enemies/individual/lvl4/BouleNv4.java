package elements.generic.enemies.individual.lvl4;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.enemies.individual.lvl1.Ball;
import elements.generic.enemies.individual.lvl3.BouleNv3;
import elements.generic.weapons.player.PlayerWeapon;
import jeu.Stats;

public class BouleNv4 extends BouleNv3 {
	
	public static final Pool<BouleNv4> POOL = Pools.get(BouleNv4.class);
	private static final int HP = getModulatedPv(Stats.HP_BOULE, 4);
	private static final int XP = getXp(BASE_XP, 4);
	protected static final float SPEED = getModulatedSpeed(Ball.SPEED, 4);
	
	@Override	public void free() {							POOL.free(this);							}
	@Override	protected int getMaxHp() {						return HP;						}
	@Override	public int getXp() {							return XP;				}
	@Override	public int getBonusValue() {					return BASE_XP;		}
	@Override	protected String getLabel() {					return getClass().toString();				}
	@Override	public float getSpeed() {						return SPEED;	}
	@Override	public boolean stillAlive(PlayerWeapon a) {
		nextShot -= 1.5f;
		return super.stillAlive(a);					
	}
}
