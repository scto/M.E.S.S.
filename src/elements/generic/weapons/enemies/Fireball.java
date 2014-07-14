package elements.generic.weapons.enemies;

import assets.sprites.Animations;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.badlogic.gdx.utils.Pools;

import elements.generic.components.Dimensions;

public class Fireball extends EnemyWeapon implements Poolable, InvocableWeapon {
	
	public static final Dimensions DIMENSIONS = Dimensions.FIREBALL;
	public static final Pool<Fireball> POOL = Pools.get(Fireball.class);
	public static final InvocableWeapon REF = new Fireball();
	
	@Override	public void free() {					POOL.free(this);	}
	@Override	public Dimensions getDimensions() {		return DIMENSIONS;					}
	@Override	public EnemyWeapon invoke() {			return POOL.obtain();	}
	@Override	public Animations getAnimation() {		return Animations.FIREBALL;	}
}
