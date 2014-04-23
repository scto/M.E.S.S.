package elements.particular.particles.individual.explosions;

import jeu.CSG;
import jeu.EndlessMode;
import jeu.Stats;
import assets.AssetMan;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;

import elements.particular.particles.individual.MovingSmoke;
import elements.particular.particles.individual.PrecalculatedParticles;

public class ExplosionColorOverTime implements Poolable{
	
	private float x, y, speedX, speedY;
	private int index;
	private static final float INITIAL_WIDTH = ((float)Stats.LARGEUR_DE_BASE / 4), INITIAL_HALF_WIDTH = INITIAL_WIDTH / 2;
	private float[] colors;
	public static final Pool<ExplosionColorOverTime> POOL = new Pool<ExplosionColorOverTime>() {
		@Override
		protected ExplosionColorOverTime newObject() {
			return new ExplosionColorOverTime();
		}
	};
	
	public static void act(Array<ExplosionColorOverTime> explosions, SpriteBatch batch) {
		for (ExplosionColorOverTime e : explosions) {
			batch.setColor(e.colors[e.index]);
			batch.draw(AssetMan.dust, e.x, e.y, PrecalculatedParticles.widths[e.index], PrecalculatedParticles.widths[e.index]);
			if (EndlessMode.triggerStop)
				continue;
			e.x -= PrecalculatedParticles.halfWidths[e.index];
			e.y -= PrecalculatedParticles.halfWidths[e.index];
			
			if (++e.index >= PrecalculatedParticles.widths.length) {
				explosions.removeValue(e, true);
				POOL.free(e);
			}
		}
		batch.setColor(AssetMan.WHITE);
	}
	

	@Override
	public void reset() {}
	
	public void init(float x, float y, boolean rnd, float[] colors) {
		if (rnd)
			this.x = (x - INITIAL_HALF_WIDTH) + ((CSG.R.nextFloat() - .5f) * INITIAL_HALF_WIDTH);
		else
			this.x = x - INITIAL_HALF_WIDTH;
		this.y = y - INITIAL_HALF_WIDTH;
		index = 0;
		this.colors = colors;
	}
	public static void clear(Array<ExplosionColorOverTime> explosions) {
		POOL.freeAll(explosions);
		explosions.clear();
	}

}
