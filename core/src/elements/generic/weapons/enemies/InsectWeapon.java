package elements.generic.weapons.enemies;

import assets.sprites.Animations;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.components.Dimensions;

public class InsectWeapon extends EnemyWeapon implements InvocableWeapon {
	
	protected static final Dimensions DIMENSIONS = Dimensions.INSECT_WEAPON;
	public static final Pool<InsectWeapon> POOL = Pools.get(InsectWeapon.class);

	@Override	public void free() {					POOL.free(this);				}
	@Override	public EnemyWeapon invoke() {			return POOL.obtain();			}
	@Override	public Dimensions getDimensions() {		return DIMENSIONS;					}
	@Override	public Animations getAnimation() {		return Animations.BLUE_BALL;	}
}

