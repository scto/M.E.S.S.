package elements.generic.weapons.enemies;

import assets.sprites.Animations;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.badlogic.gdx.utils.Pools;

import elements.generic.components.Dimensions;

public class LaserWeapon extends EnemyWeapon implements Poolable, InvocableWeapon{
	
	public static final Dimensions DIMENSIONS = Dimensions.LASER_WEAPON;
	public static final Pool<LaserWeapon> POOL = Pools.get(LaserWeapon.class);
	
	@Override	public Animations getAnimation() {		return Animations.BLUE_BALL;	}
	@Override	public void free() {					POOL.free(this);	}
	@Override	public EnemyWeapon invoke() {			return POOL.obtain();	}
	@Override	public Dimensions getDimensions() {		return DIMENSIONS;					}
}
