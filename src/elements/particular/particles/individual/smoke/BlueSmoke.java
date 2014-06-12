package elements.particular.particles.individual.smoke;

import jeu.CSG;
import jeu.Stats;
import jeu.mode.EndlessMode;
import assets.AssetMan;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;

public class BlueSmoke implements Poolable{
	
	private float x, y;//, alpha, width;
	private int index;
	private static final float INITIAL_WIDTH = ((float)Stats.WIDTH_DE_BASE / 3), INITIAL_HALF_WIDTH = INITIAL_WIDTH / 2;
	private static final float[] colors = initAlphas();
	private static final float[] widths = initWidths();
	private static final float[] halfWidths = CSG.getDifferences(widths);
	public static final Pool<BlueSmoke> POOL = new Pool<BlueSmoke>() {
		@Override
		protected BlueSmoke newObject() {
			return new BlueSmoke();
		}
	};
	public static void act(Array<BlueSmoke> smoke, SpriteBatch batch) {
		for (BlueSmoke s : smoke) {
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
			f += Stats.uDiv4;
		}
		return CSG.convert(tmp);
	}

	private static float[] initAlphas() {
		float alpha = 1;
		Array<Float> tmp = new Array<Float>();
		while (alpha > 0) {
			tmp.add(AssetMan.convertARGB(alpha, alpha, 1, .1f));
			alpha -= 0.05f;
		}
		return CSG.convert(tmp);
	}
	
	@Override
	public void reset() {}
	
	public void init(float x, float y) {
		this.x = (x - INITIAL_HALF_WIDTH) + ((CSG.R.nextFloat() - .5f) * INITIAL_HALF_WIDTH);
		this.y = y;
		index = 0;
	}
	public static void clear(Array<BlueSmoke> smoke) {
		POOL.freeAll(smoke);
		smoke.clear();
	}
}
