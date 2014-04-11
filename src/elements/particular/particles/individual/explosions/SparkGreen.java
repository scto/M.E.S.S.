package elements.particular.particles.individual.explosions;

import java.util.Random;

import jeu.CSG;
import jeu.EndlessMode;
import jeu.Stats;
import assets.AssetMan;
import assets.SoundMan;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;

import elements.generic.enemies.Enemy;
import elements.generic.weapons.enemies.EnemyWeapon;
import elements.particular.particles.Particles;

public class SparkGreen implements Poolable {
	
	private static final float WIDTH = CSG.screenWidth / 200, HALF_WIDTH = WIDTH/2;
	private static final float MIN_WIDTH = Stats.u;
	private static final Pool<SparkGreen> POOL_STANDARD = new Pool<SparkGreen>(100) {
		@Override
		protected SparkGreen newObject() {
			return new SparkGreen();
		}
	};
	private float x, y;
	private final float speedX, speedY, angle, height, halfHeight;
	private int ttl;
	private final float color = AssetMan.convertARGB(1, CSG.R.nextFloat()/8, 1, (CSG.R.nextFloat() + .8f) / 1.6f);
	protected static final Random R = new Random();
	public static final int GAUSSIAN_FACTOR = 10;
	public static final int STANDARD_EXPLOSION = 1, BLUE_EXPLOSION = 2, GREEN_EXPLOSION = 3;
	private static final Vector2 tmpVector = new Vector2();
	
	public SparkGreen() {
		speedY = (float) ((float) ((R.nextGaussian()) * Stats.V_PARTICULE_EXPLOSION_SLOW * 2) + R.nextGaussian() * Stats.V_PARTICULE_EXPLOSION);
		speedX = (float) ((float) ((R.nextGaussian()) * Stats.V_PARTICULE_EXPLOSION_SLOW * 2) + R.nextGaussian() * Stats.V_PARTICULE_EXPLOSION);
		height = Math.abs((float) ((R.nextGaussian() * WIDTH))) + MIN_WIDTH;
		halfHeight = height / 2;
		tmpVector.x = speedX;
		tmpVector.y = speedY;
		angle = tmpVector.angle() + 90;
	}
	
	@Override
	public void reset() {	}

	public static void add(Array<SparkGreen> explosions, Enemy e) {
		for (int i = -EndlessMode.perf; i < EndlessMode.perf; i++) {
			explosions.add(POOL_STANDARD.obtain().init(e));
		}
	}

	private SparkGreen init(Enemy e) {
		x = (e.pos.x + e.getHalfWidth());
		y = (e.pos.y + e.getHalfHeight());
		ttl = (int) (8 + (CSG.R.nextFloat() * 5f));
		return this;
	}

	public static void act(Array<SparkGreen> explosions, SpriteBatch batch) {
		if (EndlessMode.alternate) {
			for (final SparkGreen p : explosions) {
				batch.setColor(p.color);
				batch.draw(AssetMan.tWeapon, p.x, p.y, HALF_WIDTH, p.halfHeight, WIDTH, p.height, 1, 1, p.angle);
			}
		} else {
			for (final SparkGreen p : explosions) {
				batch.setColor(p.color);
				batch.draw(AssetMan.tWeapon, p.x, p.y, HALF_WIDTH, p.halfHeight, WIDTH, p.height, 1, 1, p.angle);
				if (EndlessMode.triggerStop)
					continue;
				p.x += p.speedX * EndlessMode.delta2;
				p.y += p.speedY * EndlessMode.delta2;
				if (p.ttl-- < 0) {
					POOL_STANDARD.free(p);
					explosions.removeValue(p, true);
				}
			}
		}
		batch.setColor(AssetMan.WHITE);
	}

	public static void clear(Array<SparkGreen> explosions) {
		POOL_STANDARD.freeAll(explosions);
		explosions.clear();
	}


}
