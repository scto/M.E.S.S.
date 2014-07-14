package elements.generic.weapons.enemies;

import assets.AssetMan;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.components.Dimensions;

public class PinkBullet extends OrangeBullet {
	
	public static final Pool<PinkBullet> POOL = Pools.get(PinkBullet.class);
	public static final Dimensions DIMENSIONS = Dimensions.PINK_BULLET;
	private static final float COLOR = AssetMan.convertARGB(1, 1, .75f, 1f);
	
	@Override	protected void setColor(SpriteBatch batch) {		batch.setColor(COLOR);			}
	@Override	protected void removeColor(SpriteBatch batch) {		batch.setColor(AssetMan.WHITE);	}
	@Override	public Dimensions getDimensions() {		return DIMENSIONS;					}
	@Override	public void free() {					POOL.free(this);							}
	
}
