package elements.particular.particles.individual;

import jeu.Stats;
import jeu.mode.EndlessMode;
import assets.AssetMan;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;

public class SparklesColorOverTimeWide implements Poolable {

	private float x, y, angle;
	private int index;
	private static final float WIDTH = Stats.u * 0.75f, HALF_WIDTH = WIDTH / 2;
	public static final float HEIGHT = Stats.U3, HALF_HEIGHT = HEIGHT / 2;
	private float[] colors;

	public static final Pool<SparklesColorOverTimeWide> POOL = new Pool<SparklesColorOverTimeWide>() {
		@Override
		protected SparklesColorOverTimeWide newObject() {
			return new SparklesColorOverTimeWide();
		}
	};
	
	@Override
	public void reset() {	}
	
	public static void act(Array<SparklesColorOverTimeWide> explosions, SpriteBatch batch) {
		if (EndlessMode.alternate && !EndlessMode.triggerStop) {
			for (SparklesColorOverTimeWide e : explosions) {
				batch.setColor(e.colors[e.index]);
				batch.draw(AssetMan.dust, e.x, e.y, HALF_HEIGHT, HALF_WIDTH, HEIGHT, WIDTH, 1f, 1f, e.angle);
				
				if (++e.index >= e.colors.length) {
					explosions.removeValue(e, true);
					POOL.free(e);
				}
			}
		} else {
			for (SparklesColorOverTimeWide e : explosions) {
				batch.setColor(e.colors[e.index]);
				batch.draw(AssetMan.dust, e.x, e.y, HALF_HEIGHT, HALF_WIDTH, HEIGHT, WIDTH, 1f, 1f, e.angle);
			}
		}
		batch.setColor(AssetMan.WHITE);
	}
	
	public static void clear(Array<SparklesColorOverTimeWide> explosions) {
		POOL.freeAll(explosions);
		explosions.clear();
	}

	public static void add(float x, float y, float angle, float[] colors, Array<SparklesColorOverTimeWide> array, int index) {
		final SparklesColorOverTimeWide p = POOL.obtain();
		p.x = x;
		p.y = y;
		p.angle = angle;
		p.colors = colors;
		p.index = index;
		array.add(p);
	}

}
