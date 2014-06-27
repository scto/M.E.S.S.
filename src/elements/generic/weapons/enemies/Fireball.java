package elements.generic.weapons.enemies;

import assets.sprites.Animations;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.badlogic.gdx.utils.Pools;

import elements.generic.components.Dimensions;

public class Fireball extends EnemyWeapon implements Poolable, InvocableWeapon {
	
	public static final Dimensions DIMENSIONS = Dimensions.FIREBALL;
	public static final Pool<Fireball> POOL = Pools.get(Fireball.class);
	private static final float SPEED = 24;
	public static final InvocableWeapon REF = new Fireball();
	
	@Override	public float getSpeed() {				return SPEED;	}
	@Override	public void free() {					POOL.free(this);	}
	@Override	public Dimensions getDimensions() {		return DIMENSIONS;					}
	@Override	public EnemyWeapon invoke() {			return POOL.obtain();	}
	@Override	public Animations getAnimation() {		return Animations.FIREBALL;	}
}
