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
	private float[] colors;
	public static final Pool<MovingSmoke> POOL = new Pool<MovingSmoke>() {
		@Override
		protected MovingSmoke newObject() {
			return new MovingSmoke();
		}
	};
	public static void act(Array<MovingSmoke> smoke, SpriteBatch batch) {
		for (MovingSmoke s : smoke) {
			batch.setColor(s.colors[s.index]);
			batch.draw(AssetMan.dust, s.x, s.y, PrecalculatedParticles.widths[s.index], PrecalculatedParticles.widths[s.index]);
			if (EndlessMode.triggerStop)
				continue;
			s.x -= PrecalculatedParticles.halfWidths[s.index];
			s.y -= PrecalculatedParticles.halfWidths[s.index];
			s.y += PrecalculatedParticles.dirY[s.index] * EndlessMode.delta15;
			if (++s.index >= PrecalculatedParticles.widths.length) {
				smoke.removeValue(s, true);
				POOL.free(s);
			}
		}
		batch.setColor(AssetMan.WHITE);
	}
	
	@Override
	public void reset() {}
	
	public void init(float x, float y, boolean rnd, float[] colors) {
		if (rnd)
			this.x = (x - PrecalculatedParticles.INITIAL_HALF_WIDTH) + ((CSG.R.nextFloat() - .5f) * PrecalculatedParticles.INITIAL_HALF_WIDTH);
		else
			this.x = x - PrecalculatedParticles.INITIAL_HALF_WIDTH;
		this.y = y - PrecalculatedParticles.INITIAL_HALF_WIDTH;
		index = 0;
		this.colors = colors;
	}
	public static void clear(Array<MovingSmoke> smoke) {
		POOL.freeAll(smoke);
		smoke.clear();
	}
}
