package elements.generic.weapons.enemies;

import jeu.CSG;
import jeu.EndlessMode;
import assets.animation.Animated;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.badlogic.gdx.utils.Pools;

public class Mine extends EnemyWeapon implements Poolable {
	
	public static final Pool<Mine> POOL = Pools.get(Mine.class);
	public static final int WIDTH = CSG.screenWidth / 10, HALF_WIDTH = WIDTH/2;
	private float angle = EndlessMode.R.nextFloat() * 360;
	private final float vitesseAngulaire = (float) EndlessMode.R.nextGaussian() * 80;
	private static final int PK = 13;
	private static final float SPEED = initSpeed(13, PK);
	private static final Animated ANIMATED = initAnimation(Animated.MINE, PK);


	public void draw(SpriteBatch batch) {
		angle += vitesseAngulaire * EndlessMode.delta;
		batch.draw(getTexture(), pos.x, pos.y, HALF_WIDTH, HALF_WIDTH, WIDTH, WIDTH, 1, 1, angle);
	}
	
	@Override	public void free() {					POOL.free(this);	}
	@Override	public int getWidth() {					return WIDTH;	}
	@Override	public int getHeight() {				return WIDTH;	}
	@Override	public int getHalfWidth() {				return HALF_WIDTH;	}
	@Override	public int getHalfHeight() {			return HALF_WIDTH;	}
	@Override	protected float getSpeed() {			return SPEED;	}
	@Override	public TextureRegion getTexture() {		return ANIMATED.getTexture(now);	}
}
