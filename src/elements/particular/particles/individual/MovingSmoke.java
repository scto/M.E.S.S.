package elements.particular.particles.individual;

import jeu.CSG;
import jeu.EndlessMode;
import jeu.Stats;
import assets.AssetMan;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;

public class MovingSmoke implements Poolable{
	
	private float x, y;//, alpha, width;
	private int index;
	private static final float INITIAL_WIDTH = ((float)Stats.LARGEUR_DE_BASE / 4), INITIAL_HALF_WIDTH = INITIAL_WIDTH / 2;
	private static final float[] colors = initAlphas();
	private static final float[] widths = initWidths();
	private static final float[] halfWidths = CSG.getHalf(widths);
	private static final float[] dirY = initDirY(widths);
	public static final Pool<MovingSmoke> POOL = new Pool<MovingSmoke>() {
		@Override
		protected MovingSmoke newObject() {
			return new MovingSmoke();
		}
	};
	public static void act(Array<MovingSmoke> smoke, SpriteBatch batch) {
		for (MovingSmoke s : smoke) {
			batch.setColor(colors[s.index]);
			batch.draw(AssetMan.dust, s.x, s.y, widths[s.index], widths[s.index]);
			if (EndlessMode.triggerStop)
				continue;
			s.x -= halfWidths[s.index];
			s.y -= halfWidths[s.index];
			s.y += dirY[s.index] * EndlessMode.delta15;
			if (++s.index >= widths.length) {
				smoke.removeValue(s, true);
				POOL.free(s);
			}
		}
		batch.setColor(AssetMan.WHITE);
	}
	
	private static float[] initDirY(float[] widths2) {
		float alpha = CSG.SCREEN_HEIGHT / 50;
		Array<Float> tmp = new Array<Float>();
		while (alpha > 0) {
			tmp.add(alpha);
			alpha -= 0.075f;
		}
		return CSG.convert(tmp);
	}

	private static float[] initWidths() {
		float f = INITIAL_WIDTH;
		Array<Float> tmp = new Array<Float>();
		for (int i = 0; i < colors.length; i++) {
			tmp.add(f);
			f += Stats.uSur4;
		}
		return CSG.convert(tmp);
	}

	private static float[] initAlphas() {
		float alpha = 1;
		Array<Float> tmp = new Array<Float>();
		while (alpha > 0) {
			tmp.add(AssetMan.convertARGB(alpha, 1, alpha, 0.05f));
			alpha -= 0.075f;
		}
		return CSG.convert(tmp);
	}
	
	@Override
	public void reset() {}
	
	public void init(float x, float y, boolean rnd) {
		if (rnd)
			this.x = (x - INITIAL_HALF_WIDTH) + ((CSG.R.nextFloat() - .5f) * INITIAL_HALF_WIDTH);
		else
			this.x = x - INITIAL_HALF_WIDTH;
		this.y = y - INITIAL_HALF_WIDTH;
		index = 0;
	}
	public static void clear(Array<MovingSmoke> smoke) {
		POOL.freeAll(smoke);
		smoke.clear();
	}
}
