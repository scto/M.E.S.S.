package elements.generic.weapons.enemies;

import jeu.Stats;
import assets.animation.Animated;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;
import com.badlogic.gdx.utils.Pool.Poolable;

public class BlueBulletFast extends BlueBullet implements Poolable, InvocableWeapon{
	
	public static final Pool<BlueBulletFast> POOL = Pools.get(BlueBulletFast.class);
	public static final int WIDTH = Stats.WIDTH_WEAPON_SMALL, HALF_WIDTH = WIDTH/2;
	public static final int PK = 7;
	private static final float SPEED = initSpeed(24, PK);
	private static final Animated ANIMATED = initAnimation(1, PK);
	
	@Override	public void free() {				POOL.free(this);						}
	@Override	public int getWidth() {				return WIDTH;							}
	@Override	public int getHeight() {			return WIDTH;							}
	@Override	public int getHalfWidth() {			return HALF_WIDTH;					}
	@Override	public int getHalfHeight() {		return HALF_WIDTH;					}
	@Override	protected float getSpeed() {		return SPEED;	}
	@Override	public EnemyWeapon invoke() {		return POOL.obtain();	}
}
