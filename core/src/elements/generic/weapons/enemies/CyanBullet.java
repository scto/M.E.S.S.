package elements.generic.weapons.enemies;

import assets.AssetMan;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.components.Dimensions;

public class CyanBullet extends OrangeBullet {
	
	public static final Dimensions DIMENSIONS = Dimensions.FIREBALL;
	public static final Pool<CyanBullet> POOL = Pools.get(CyanBullet.class);
	
	@Override	protected void setColor(SpriteBatch batch) {		batch.setColor(ALTERNATE_COLOR);			}
	@Override	protected void removeColor(SpriteBatch batch) {		batch.setColor(AssetMan.WHITE);	}
	
	@Override	public void free() {					POOL.free(this);							}
	@Override	public Dimensions getDimensions() {		return DIMENSIONS;					}
}
