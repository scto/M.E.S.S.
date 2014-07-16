package elements.generic.weapons.enemies;

import assets.sprites.Animations;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.components.Dimensions;

public class KinderWeapon extends EnemyWeapon implements InvocableWeapon {
	
	public static final Dimensions DIMENSIONS = Dimensions.KINDER_WEAPON;
	public static final Pool<KinderWeapon> POOL = Pools.get(KinderWeapon.class);
	
	@Override	protected void setColor(SpriteBatch batch) {
		angle += 40;
	}
	@Override	public Animations getAnimation() {					return Animations.T_WEAPON;	}
	@Override	public EnemyWeapon invoke() {						return POOL.obtain();	}
	@Override	public Dimensions getDimensions() {					return DIMENSIONS;					}
	@Override	public void free() {								POOL.free(this);	}
}
