package elements.generic.weapons.enemies;

import jeu.Stats;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.badlogic.gdx.utils.Pools;

public class Fireball extends EnemyWeapon implements Poolable, InvocableWeapon {
	
	public static final int WIDTH = Stats.WIDTH_FIREBALL, HALF_WIDTH = WIDTH/2, HEIGHT = (int) (WIDTH * 1.5), HALF_HEIGHT = HEIGHT / 2; 
	public static final Pool<Fireball> POOL = Pools.get(Fireball.class);
	public static final int PK = 9;
	private static final float SPEED = initSpeed(24, PK);
	public static final InvocableWeapon REF = new Fireball();
	
	@Override	public float getWidth() {				return WIDTH;	}
	@Override	public float getHeight() {				return HEIGHT;	}
	@Override	public void free() {					POOL.free(this);	}
	@Override	public float getHalfHeight() {			return HALF_HEIGHT;	}
	@Override	public float getHalfWidth() {			return HALF_WIDTH;	}
	@Override	public float getSpeed() {				return SPEED;	}
	@Override	public EnemyWeapon invoke() {			return POOL.obtain();	}
}
