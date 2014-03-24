package elements.particular.particles.individual;

import java.util.Random;

import jeu.CSG;
import jeu.EndlessMode;
import jeu.Stats;
import assets.AssetMan;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;

import elements.generic.weapons.Weapons;

public class BlueSparkles implements Poolable {
	
	private static final float WIDTH = CSG.screenWidth / 90;
	private static final float MIN_WIDTH = 1;
	private static final Pool<BlueSparkles> POOL = new Pool<BlueSparkles>(100) {
		@Override
		protected BlueSparkles newObject() {
			return new BlueSparkles();
		}
	};
	private float speedX, speedY, x, y;
	private int ttl;
	private final float color, width;
	private static final Random R = new Random();
	
	public BlueSparkles() {
		color = AssetMan.convertARGB(1, R.nextFloat()/8, (R.nextFloat() + .8f) / 1.6f, 1);
		width = Math.abs((float) ((R.nextGaussian() * WIDTH))) + MIN_WIDTH;
	}
	
	@Override
	public void reset() {	}

	public static void add(Weapons e, Array<BlueSparkles> sparkles) {
		for (int i = -EndlessMode.perf; i < 2; i++) {
			sparkles.add(POOL.obtain().init(e));
		}
	}
	

	private BlueSparkles init(Weapons e) {
		x = (e.pos.x + (e.getWidth() * R.nextFloat()) );
		y = (e.pos.y + (e.getHeight() * R.nextFloat()) );
		
		speedY = (float) ((R.nextGaussian()) * Stats.V_PARTICULE_EXPLOSION_SLOW);
		speedX = (float) ((R.nextGaussian()) * Stats.V_PARTICULE_EXPLOSION_SLOW);
		
		ttl = Math.abs((int) (R.nextFloat() * 50)) + 15;
		return this;
	}
	
	public static void act(Array<BlueSparkles> explosions, SpriteBatch batch) {
		for (final BlueSparkles p : explosions) {
			batch.setColor(p.color);
			batch.draw(AssetMan.dust, p.x, p.y, p.width, p.width);
			if (EndlessMode.triggerStop)
				continue;
			p.speedX /= EndlessMode.UnPlusDelta;
			p.speedY /= EndlessMode.UnPlusDelta;
			p.x += p.speedX * EndlessMode.delta;
			p.y += p.speedY * EndlessMode.delta;
			if (p.ttl-- < 0) {
				POOL.free(p);
				explosions.removeValue(p, true);
			}
		}
		batch.setColor(AssetMan.WHITE);
	}

	public static void clear(Array<BlueSparkles> explosions) {
		POOL.freeAll(explosions);
		explosions.clear();
	}
}
