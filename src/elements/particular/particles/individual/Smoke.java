package elements.particular.particles.individual;

import jeu.CSG;
import jeu.EndlessMode;
import jeu.Stats;
import assets.AssetMan;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;

public class Smoke implements Poolable{
	
	private float x, y;//, alpha, width;
	private int index;
	private static final float INITIAL_WIDTH = ((float)Stats.LARGEUR_DE_BASE / 5), INITIAL_HALF_WIDTH = INITIAL_WIDTH / 2;
	public static final float[] colors = initColors();
	private static final float[] widths = initWidths();
	private static final float[] halfWidths = CSG.getHalf(widths);
	public static final Pool<Smoke> POOL = new Pool<Smoke>() {
		@Override
		protected Smoke newObject() {
			return new Smoke();
		}
	};
	public static void act(Array<Smoke> smoke, SpriteBatch batch) {
		for (Smoke s : smoke) {
			batch.setColor(colors[s.index]);
			batch.draw(AssetMan.dust, s.x, s.y, widths[s.index], widths[s.index]);
			if (EndlessMode.triggerStop)
				continue;
			s.x -= halfWidths[s.index];
			s.y -= halfWidths[s.index];
			if (++s.index >= widths.length) {
				smoke.removeValue(s, true);
				POOL.free(s);
			}
		}
		batch.setColor(AssetMan.WHITE);
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

	private static float[] initColors() {
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
	public static void clear(Array<Smoke> smoke) {
		POOL.freeAll(smoke);
		smoke.clear();
	}
}
