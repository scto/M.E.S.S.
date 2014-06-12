package elements.generic.weapons.enemies;

import jeu.Stats;
import assets.AssetMan;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

public class BlueBulletSlow extends BlueBullet {
	
	public static final Pool<BlueBulletSlow> POOL = Pools.get(BlueBulletSlow.class);
	public static final int WIDTH = Stats.WIDTH_WEAPON_BIG, HALF_WIDTH = WIDTH/2;
	private static final float COLOR = AssetMan.convertARGB(1, 1, .75f, 1f);
	public static final int PK = 8;
	private static final float SPEED = initSpeed(16, PK);
	
	@Override
	public void draw(SpriteBatch batch) {
		batch.setColor(COLOR);
		super.draw(batch);
		batch.setColor(AssetMan.WHITE);
	}
	
	@Override	public float getWidth() {				return WIDTH;								}
	@Override	public float getHeight() {				return WIDTH;								}
	@Override	public void free() {					POOL.free(this);							}
	@Override	public float getHalfWidth() {			return HALF_WIDTH;							}
	@Override	public float getHalfHeight() {			return HALF_WIDTH;							}
	@Override	public float getSpeed() {				return SPEED;		}
}
