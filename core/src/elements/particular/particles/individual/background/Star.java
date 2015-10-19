package elements.particular.particles.individual.background;

import jeu.CSG;
import jeu.mode.EndlessMode;
import assets.AssetMan;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;

import elements.particular.particles.Particles;

public class Star implements Poolable {
	
	private static final int WIDTH = CSG.screenWidth/130, MINWIDTH = (int) (WIDTH / 2.5f);
	private static final Pool<Star> POOL = new Pool<Star>(Particles.MAX_BACKGROUND) {
		@Override
		protected Star newObject() {
			return new Star();
		}
	};
	private final float w, speed, color;
	private float y, x;
	
	public Star() {
		float tmp = Math.abs((float) (CSG.R.nextFloat() * WIDTH));
		while (tmp < MINWIDTH)
			tmp = Math.abs((float) (CSG.R.nextFloat() * WIDTH));
		w = tmp;
		x = (CSG.R.nextFloat() * CSG.screenWidth + w) - w/2;
		final float f = CSG.R.nextFloat();
		if (f > .99f) {
			color = CSG.gm.palette().white;
		} else {
			if (f > .9f)
				color = CSG.gm.palette().convertARGB(1, 1, .7f + CSG.R.nextFloat() / 4, 1);
			else if (f > .7f) {
				color = CSG.gm.palette().convertARGB(1, .5f, .5f + CSG.R.nextFloat() / 4, 1);
			} else {
				color = CSG.gm.palette().white;
			}
		}
		speed = (w * w) * 0.5f;
	}

	@Override 
	public void reset() {
		x = (CSG.R.nextFloat() * CSG.screenWidth + w) - w/2;
		y = CSG.height + w;
	}

	public static void initBackground(Array<Star> stars) {
		while (stars.size < Particles.MAX_BACKGROUND) {
			final Star p = Star.POOL.obtain();
			do {
				p.y = (float) (CSG.R.nextFloat() * CSG.height);
			} while (p.y <= 0);
			stars.add(p);
		}
	}

	public static void act(SpriteBatch batch, Array<Star> stars) {
		while (stars.size < Particles.MAX_BACKGROUND)
			stars.add(Star.POOL.obtain());

		if (EndlessMode.triggerStop) {
			for (final Star star : stars) {
				batch.setColor(star.color);
				batch.draw(AssetMan.dust, star.x, star.y, star.w, star.w);
			}
		} else {
			for (final Star star : stars) {
				batch.setColor(star.color);
				batch.draw(AssetMan.dust, star.x, star.y, star.w, star.w);
				star.y -= (star.speed * EndlessMode.delta2);
				if (star.y < 0) {
					POOL.free(star);
					stars.removeValue(star, true);
				}
			}
		}
		batch.setColor(CSG.gm.palette().white);
	}

	public static void clear(Array<Star> stars) {
		POOL.freeAll(stars);
		stars.clear();
	}
}

