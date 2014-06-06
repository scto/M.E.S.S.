package elements.generic.enemies.individual.lvl3;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.enemies.individual.lvl1.Ball;
import elements.generic.weapons.player.PlayerWeapon;
import jeu.Stats;

public class BouleNv3 extends Ball {
	
	public static final Pool<BouleNv3> POOL = Pools.get(BouleNv3.class);
	private static final int HP = getModulatedPv(Stats.HP_BOULE, 3);
	private static final int XP = getXp(BASE_XP, 3);
	protected static final float SPEED = getModulatedSpeed(Ball.SPEED, 3);

	@Override	public void free() {				POOL.free(this);						}
	@Override	protected int getMaxHp() {			return HP;					}
	@Override	protected String getLabel() {		return getClass().toString();			}
	@Override	public int getXp() {				return XP;		}
	@Override	public int getBonusValue() {		return BASE_XP;	}
	@Override	public float getSpeed() {			return SPEED;	}
	@Override	public boolean stillAlive(PlayerWeapon a) {
		nextShot -= 1;
		return super.stillAlive(a);					
	}
}
