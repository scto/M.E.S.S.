package elements.particular.particles.individual.background;

import java.util.Random;

import jeu.CSG;
import jeu.EndlessMode;
import jeu.Stats;
import assets.AssetMan;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;

import elements.particular.particles.Particles;

public class Dust implements Poolable {

	private static final int WIDTH = CSG.screenWidth / 260, MINWIDTH = (int) (WIDTH / 2.5f);
	private static final Pool<Dust> POOL = new Pool<Dust>(10) {
		@Override
		protected Dust newObject() {
			return new Dust();
		}
	};
	private final float w, h, speed, color;
	private float y, x;
	private static final Random R = new Random();
	public static float next = 0;

	public Dust() {
		float tmp = Math.abs((float) (R.nextFloat() * WIDTH));
		while (tmp < MINWIDTH)
			tmp = Math.abs((float) (R.nextFloat() * WIDTH));
		w = tmp;
		x = (R.nextFloat() * CSG.gameZoneWidth + w) - w / 2;
		final float f = (R.nextFloat() / 2) + 0.35f;
		color = AssetMan.convertARGB(1, f, f, f);
		speed = (R.nextFloat() / 2) * 14 * CSG.SCREEN_HEIGHT;
		h = speed / 20;
	}

	private float[] initWidths(float w) {
		Array<Float> widths = new Array<Float>();
		float tmp = w;
		while (tmp > Stats.uSur8) {
			widths.add(tmp);
			tmp -= (w / 10);
		}
		return CSG.convert(widths);
	}

	@Override
	public void reset() {
		x = (R.nextFloat() * CSG.gameZoneWidth + w) - w / 2;
		y = CSG.SCREEN_HEIGHT + w;
	}

	public static void act(SpriteBatch batch, Array<Dust> dust) {
		if (dust.size < Particles.MAX_DUST && next < EndlessMode.now) {
			next = EndlessMode.now + .5f;
			dust.add(POOL.obtain());
		}

		if (EndlessMode.triggerStop) {
			for (final Dust d : dust) {
				batch.setColor(d.color);
				batch.draw(AssetMan.shootingStar, d.x, d.y, d.w, d.h);
			}
		} else {
			for (final Dust d : dust) {
				batch.setColor(d.color);
				batch.draw(AssetMan.shootingStar, d.x, d.y, d.w, d.h);
				d.y -= (d.speed * EndlessMode.delta);
				if (d.y < -d.h) {
					POOL.free(d);
					dust.removeValue(d, true);
				}
			}
		}
		batch.setColor(CSG.assetMan.WHITE);
	}

	public static void clear(Array<Dust> stars) {
		POOL.freeAll(stars);
		stars.clear();
	}

}
