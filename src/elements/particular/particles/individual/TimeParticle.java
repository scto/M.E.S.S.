package elements.particular.particles.individual;

import jeu.CSG;
import jeu.mode.EndlessMode;
import assets.AssetMan;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;

import elements.generic.Element;
import elements.generic.enemies.Enemy;

public class TimeParticle implements Poolable {

	private static final float WIDTH = CSG.screenWidth / 55, HALF_WIDTH = WIDTH / 2;
	public static final Pool<TimeParticle> POOL = new Pool<TimeParticle>() {
		@Override
		protected TimeParticle newObject() {
			return new TimeParticle();
		}
	};
	private float x, y, time;
	private final float color = AssetMan.convertARGB(1, 0, 1, CSG.R.nextFloat());

	public TimeParticle init(Element e) {
		x = (e.pos.x + e.getHalfWidth()) - HALF_WIDTH;
		y = (e.pos.y + e.getHalfHeight()) - HALF_WIDTH;
		x += (CSG.R.nextGaussian() / 2) * e.getHalfWidth();
		y += (CSG.R.nextGaussian() / 2) * e.getHalfHeight();
		time = .5f + (EndlessMode.STOP - .5f) * CSG.R.nextFloat();
		return this;
	}

	public static void act(Array<TimeParticle> time, SpriteBatch batch) {
		for (TimeParticle t : time) {
			batch.setColor(t.color);
			batch.draw(AssetMan.dust, t.x, t.y, WIDTH, WIDTH);
			t.time -= EndlessMode.delta;
			if (t.time < 0) {
				time.removeValue(t, false);
				POOL.free(t);
			}
		}
		batch.setColor(AssetMan.WHITE);
	}

	public static void clear(Array<TimeParticle> time) {
		POOL.freeAll(time);
		time.clear();
	}

	@Override
	public void reset() {
	}

	public static void generate(Enemy e, Array<TimeParticle> time) {
		for (int i = 0; i < EndlessMode.fps; i++)
			time.add(POOL.obtain().init(e));
	}

}
