package elements.particular.particles.individual;

import jeu.CSG;
import jeu.EndlessMode;
import assets.AssetMan;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;

public class Smoke implements Poolable{
	
	private float x, y;
	private int index;

	public static final Pool<Smoke> POOL = new Pool<Smoke>() {
		@Override
		protected Smoke newObject() {
			return new Smoke();
		}
	};
	public static void act(Array<Smoke> smoke, SpriteBatch batch) {
		for (Smoke s : smoke) {
			batch.setColor(PrecalculatedParticles.colorsRed[s.index]);
			batch.draw(AssetMan.dust, s.x, s.y, PrecalculatedParticles.widths[s.index], PrecalculatedParticles.widths[s.index]);
			if (EndlessMode.triggerStop)
				continue;
			s.x -= PrecalculatedParticles.halfWidths[s.index];
			s.y -= PrecalculatedParticles.halfWidths[s.index];
			if (++s.index >= PrecalculatedParticles.widths.length) {
				smoke.removeValue(s, true);
				POOL.free(s);
			}
		}
		batch.setColor(AssetMan.WHITE);
	}
	
	@Override
	public void reset() {}
	
	public void init(float x, float y, boolean rnd) {
		if (rnd)
			this.x = (x - PrecalculatedParticles.INITIAL_HALF_WIDTH) + ((CSG.R.nextFloat() - .5f) * PrecalculatedParticles.INITIAL_HALF_WIDTH);
		else
			this.x = x - PrecalculatedParticles.INITIAL_HALF_WIDTH;
		this.y = y - PrecalculatedParticles.INITIAL_HALF_WIDTH;
		index = 0;
	}
	public static void clear(Array<Smoke> smoke) {
		POOL.freeAll(smoke);
		smoke.clear();
	}
}
