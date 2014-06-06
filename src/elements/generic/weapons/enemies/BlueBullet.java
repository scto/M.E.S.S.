package elements.generic.weapons.enemies;

import jeu.Stats;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

public class BlueBullet extends EnemyWeapon {
	
	public static final Pool<BlueBullet> POOL = Pools.get(BlueBullet.class);
	public static final int WIDTH = Stats.WIDTH_WEAPON_NORMAL, HALF_WIDTH = WIDTH / 2;
	public static final int PK = 2;
	private static final float SPEED = initSpeed(20, PK);
	
	@Override	public float getWidth() {					return WIDTH;	}
	@Override	public float getHeight() {					return WIDTH;	}
	@Override	public void free() {						POOL.free(this);	}
	@Override	public float getHalfHeight() {				return HALF_WIDTH;	}
	@Override	public float getHalfWidth() {				return HALF_WIDTH;	}
	@Override	public float getSpeed() {					return SPEED;	}
	@Override	public EnemyWeapon invoke() {				return POOL.obtain();	}
	
}
