package elements.generic.weapons.enemies;

import assets.sprites.Animations;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.components.Dimensions;

public class OrangeBullet extends EnemyWeapon {
	
	public static final Dimensions DIMENSIONS = Dimensions.ORANGE_BULLET;
	public static final Pool<OrangeBullet> POOL = Pools.get(OrangeBullet.class);
	
	@Override	public void free() {						POOL.free(this);				}
	@Override	public Animations getAnimation() {			return Animations.BLUE_BALL;	}
	@Override	public Dimensions getDimensions() {		return DIMENSIONS;					}
	
}
