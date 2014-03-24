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
import elements.generic.enemies.Progression;
import elements.particular.particles.Particles;

public class Star implements Poolable {
	
	private static final int WIDTH = CSG.screenWidth/280;
	private static final Pool<Star> POOL = new Pool<Star>(Particles.MAX_BACKGROUND) {
		@Override
		protected Star newObject() {
			return new Star();
		}
	};
	private final float w;
	private float y, x;
	private static final Random R = new Random();
	
	public Star() {
		float tmp = Math.abs((float) (R.nextGaussian() * WIDTH));
		while (tmp < Stats.U / 30)
			tmp = Math.abs((float) (R.nextGaussian() * WIDTH));
		w = tmp;
		x = (R.nextFloat() * CSG.gameZoneWidth + w) - w/2;
	}

	@Override 
	public void reset() {
		x = (R.nextFloat() * CSG.gameZoneWidth + w) - w/2;
		y = CSG.SCREEN_HEIGHT + w;
	}

	public static void initBackground(Array<Star> stars) {
		for (int i = 0; i < Particles.MAX_BACKGROUND; i++) {
			final Star p = Star.POOL.obtain();
			do {
				p.y = (float) (R.nextGaussian() * CSG.SCREEN_HEIGHT * p.w * p.w);
			} while (p.y <= 0);
			stars.add(p);
		}
	}

	public static void act(SpriteBatch batch, Array<Star> stars) {
		if (stars.size < Particles.MAX_BACKGROUND)
			stars.add(Star.POOL.obtain());
		
		for (final Star star : stars) {
			batch.draw(AssetMan.dust, star.x, star.y, star.w, star.w);
			star.y -= (star.w * EndlessMode.delta2);
			if (star.y < 0) {
				POOL.free(star);
				stars.removeValue(star, true);
			}
		}
		if (Progression.bossJustPoped) {
			for (final Star star : stars) {
				batch.draw(AssetMan.dust, star.x, star.y, star.w, star.w);
				star.y += (star.w * EndlessMode.delta2);
			}
		}
	}

	public static void clear(Array<Star> stars) {
		POOL.freeAll(stars);
		stars.clear();
	}
}

