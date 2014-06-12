package elements.generic.weapons.enemies;

import jeu.CSG;
import jeu.mode.EndlessMode;
import assets.sprites.Animations;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

public class Mine extends EnemyWeapon {
	
	public static final Pool<Mine> POOL = Pools.get(Mine.class);
	public static final int WIDTH = CSG.screenWidth / 10, HALF_WIDTH = WIDTH/2;
	private float angle = CSG.R.nextFloat() * 360;
	private final float vitesseAngulaire = (float) CSG.R.nextGaussian() * 80;
	private static final int PK = 13;
	private static final float SPEED = initSpeed(13, PK);


	public void draw(SpriteBatch batch) {
		angle += vitesseAngulaire * EndlessMode.delta;
		batch.draw(Animations.MINE.anim.getTexture(now), pos.x, pos.y, HALF_WIDTH, HALF_WIDTH, WIDTH, WIDTH, 1, 1, angle);
	}
	
	@Override	public void free() {					POOL.free(this);	}
	@Override	public float getWidth() {				return WIDTH;	}
	@Override	public float getHeight() {				return WIDTH;	}
	@Override	public float getHalfWidth() {			return HALF_WIDTH;	}
	@Override	public float getHalfHeight() {			return HALF_WIDTH;	}
	@Override	public float getSpeed() {				return SPEED;	}
}
