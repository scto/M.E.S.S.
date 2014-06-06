package elements.generic.weapons.enemies;

import jeu.Stats;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.badlogic.gdx.utils.Pools;

public class LaserWeapon extends EnemyWeapon implements Poolable, InvocableWeapon{
	
	public static final int WIDTH = Stats.WIDTH_WEAPON_SMALL, HALF_WIDTH = WIDTH / 2;
	public static final Pool<LaserWeapon> POOL = Pools.get(LaserWeapon.class);
	public static final int PK = 5;
	private static final float SPEED = initSpeed(18, PK);
	
	@Override	public float getSpeed() {			return SPEED;	}
	@Override	public float getWidth() {					return WIDTH;	}
	@Override	public float getHeight() {				return WIDTH;	}
	@Override	public float getHalfHeight() {			return HALF_WIDTH;	}
	@Override	public float getHalfWidth() {				return HALF_WIDTH;	}
	@Override	public void free() {					POOL.free(this);	}
	@Override	public EnemyWeapon invoke() {			return POOL.obtain();	}
}
