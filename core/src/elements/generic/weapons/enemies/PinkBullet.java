package elements.generic.weapons.enemies;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.components.Dimensions;
import jeu.CSG;

public class PinkBullet extends OrangeBullet {
	
	public static final Pool<PinkBullet> POOL = Pools.get(PinkBullet.class);
	public static final Dimensions DIMENSIONS = Dimensions.PINK_BULLET;
	private static final float COLOR = CSG.gm.palette().convertARGB(1, 1, .75f, 1f);
	
	@Override	protected void setColor(SpriteBatch batch) {		batch.setColor(COLOR);			}
	@Override	protected void removeColor(SpriteBatch batch) {		batch.setColor(CSG.gm.palette().white);	}
	@Override	public Dimensions getDimensions() {		return DIMENSIONS;					}
	@Override	public void free() {					POOL.free(this);							}
	
}
