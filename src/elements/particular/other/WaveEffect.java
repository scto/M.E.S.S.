package elements.particular.other;

import jeu.CSG;
import jeu.Stats;
import assets.AssetMan;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;

public class WaveEffect implements Poolable {

	private static final float MAX = CSG.screenHeight * 2;
	private static final Array<WaveEffect> waves = new Array<WaveEffect>();
	private float color, originX, originY, width, halfwidth;
	private static final Pool<WaveEffect> POOL = new Pool<WaveEffect>() {
		@Override
		protected WaveEffect newObject() {
			return new WaveEffect();
		}
	};
	
	public static void add(float x, float y, float color) {
		final WaveEffect w = POOL.obtain();
		w.originX = x;
		w.originY = y;
		w.color = color;
		waves.add(w);
		w.width = 0;
		w.halfwidth = 0;
	}

	public static void draw(SpriteBatch batch) {
		for (WaveEffect w : waves) {
			batch.setColor(w.color);
			w.width += Stats.U20;
			w.halfwidth += Stats.U10;
			batch.draw(AssetMan.effect, w.originX - w.halfwidth, w.originY - w.halfwidth, w.width, w.width);
			if (w.width > MAX) {
				POOL.free(w);
				waves.removeValue(w, false);
			}
		}
		batch.setColor(AssetMan.WHITE);
	}

	@Override
	public void reset() {
	}

}
