package elements.generic.weapons.enemies;

import jeu.Stats;
import assets.animation.Animated;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.badlogic.gdx.utils.Pools;

public class Fireball extends EnemyWeapon implements Poolable, InvocableWeapon {
	
	public static final int WIDTH = Stats.WIDTH_FIREBALL, HALF_WIDTH = WIDTH/2, HEIGHT = (int) (WIDTH * 1.5), HALF_HEIGHT = HEIGHT / 2; 
	public static final Pool<Fireball> POOL = Pools.get(Fireball.class);
	public static final int PK = 9;
	private static final float SPEED = initSpeed(36, PK);
	private static final Animated ANIMATED = initAnimation(2, PK);
	
	@Override	public int getWidth() {					return WIDTH;	}
	@Override	public int getHeight() {				return HEIGHT;	}
	@Override	public void free() {					POOL.free(this);	}
	@Override	public int getHalfHeight() {			return HALF_HEIGHT;	}
	@Override	public int getHalfWidth() {				return HALF_WIDTH;	}
	@Override	protected float getSpeed() {			return SPEED;	}
	@Override	public TextureRegion getTexture() {		return ANIMATED.getTexture(now);	}
	@Override	public EnemyWeapon invoke() {			return POOL.obtain();	}
}
