package elements.generic.weapons.enemies;

import jeu.CSG;
import assets.animation.Animated;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.badlogic.gdx.utils.Pools;

public class FragmentedMeteorite extends EnemyWeapon implements Poolable{
	
	private static final int WIDTH = CSG.screenWidth / 11, HALF_WIDTH = WIDTH/2;
	public static final Pool<FragmentedMeteorite> POOL = Pools.get(FragmentedMeteorite.class);
	private static final int PK = 10;
	private static final float SPEED = initSpeed(13, PK);
	private static final Animated ANIMATED = initAnimation(3, PK);
	
	public void init(final float x, final float y, Vector2 dir) {		
		this.dir.x = dir.x;
		this.dir.y = dir.y;
		this.dir.scl(getSpeed() * ((CSG.R.nextFloat()/2)+.5f) );
		pos.x = x;
		pos.y = y;
		ENEMIES_LIST.add(this);
	}
	
	@Override	public TextureRegion getTexture() {	return ANIMATED.getTexture(1);	}
	@Override	protected float getSpeed() {		return SPEED;	}
	@Override	public int getHalfHeight() {		return HALF_WIDTH;	}
	@Override	public int getHalfWidth() {			return HALF_WIDTH;	}
	@Override	public void free() {				POOL.free(this);	}
	@Override	public int getWidth() {				return WIDTH;	}
	@Override	public int getHeight() {			return WIDTH;	}
}
