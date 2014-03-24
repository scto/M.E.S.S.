package elements.generic.weapons.enemies;

import jeu.Stats;
import assets.animation.Animated;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.badlogic.gdx.utils.Pools;

public class InsectWeapon extends EnemyWeapon implements Poolable, InvocableWeapon{
	
	public static final int WIDTH = Stats.WIDTH_WEAPON_SMALL, HALF_WIDTH = WIDTH/2; 
	public static final Pool<InsectWeapon> POOL = Pools.get(InsectWeapon.class);
	public static final int PK = 4;
	private static final float SPEED = initSpeed(6, PK);
	private static final Animated ANIMATED = initAnimation(1, PK);

	@Override	public int getWidth() {					return WIDTH;	}
	@Override	public int getHeight() {				return WIDTH;									}
	@Override	public void free() {					POOL.free(this);		}
	@Override	public int getHalfWidth() {				return HALF_WIDTH;	}
	@Override	public int getHalfHeight() {			return HALF_WIDTH;	}
	@Override	protected float getSpeed() {			return SPEED;	}
	@Override	public TextureRegion getTexture() {		return ANIMATED.getTexture(now);	}
	@Override	public EnemyWeapon invoke() {			return POOL.obtain();	}
}

