package elements.generic.weapons.enemies;

import jeu.Stats;
import assets.AssetMan;
import assets.animation.Animated;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.badlogic.gdx.utils.Pools;

public class KinderWeapon extends EnemyWeapon implements Poolable, InvocableWeapon {
	
	public static final int WIDTH = Stats.WIDTH_WEAPON_SMALL, HALF_WIDTH = WIDTH/2;
	public static final Pool<KinderWeapon> POOL = Pools.get(KinderWeapon.class);
	private static final float COLOR = AssetMan.convertARGB(1, 1, 1, 0.65f);
	public static final int PK = 12;
	private static final float SPEED = initSpeed(26, PK);
	private static final Animated ANIMATED = initAnimation(4, PK);

	@Override
	public void draw(SpriteBatch batch) {
		batch.setColor(COLOR);
		super.draw(batch);
		batch.setColor(AssetMan.WHITE);
	}
	@Override	public TextureRegion getTexture() {		return ANIMATED.getTexture(now);	}
	@Override	protected float getSpeed() {			return SPEED;	}
	@Override	public int getWidth() {					return WIDTH;	}
	@Override	public int getHeight() {				return WIDTH;	}
	@Override	public int getHalfHeight() {			return HALF_WIDTH;	}
	@Override	public int getHalfWidth() {				return HALF_WIDTH;	}
	@Override	public void free() {					POOL.free(this);	}
	@Override	public EnemyWeapon invoke() {			return POOL.obtain();	}
}
