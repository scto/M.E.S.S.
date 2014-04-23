package elements.particular.particles.individual.explosions;

import jeu.CSG;
import jeu.EndlessMode;
import jeu.Stats;
import assets.AssetMan;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;

import elements.generic.enemies.Enemy;
import elements.particular.particles.individual.PrecalculatedParticles;

public class ExplosionColorOverTime implements Poolable {

	private float x, y, speedX, speedY;
	private int index;
	private static final float INITIAL_WIDTH = ((float) Stats.LARGEUR_DE_BASE / 4), INITIAL_HALF_WIDTH = INITIAL_WIDTH / 2;
	private float[] colors;
	private final int maxIndex = PrecalculatedParticles.widths.length /2;

	public static final Pool<ExplosionColorOverTime> POOL = new Pool<ExplosionColorOverTime>() {
		@Override
		protected ExplosionColorOverTime newObject() {
			return new ExplosionColorOverTime();
		}
	};
	
	@Override
	public void reset() {	}
	
	public static void act(Array<ExplosionColorOverTime> explosions, SpriteBatch batch) {
		for (ExplosionColorOverTime e : explosions) {
			batch.setColor(e.colors[e.index]);
			batch.draw(AssetMan.dust, e.x, e.y, PrecalculatedParticles.widths[e.index], PrecalculatedParticles.widths[e.index]);
			if (EndlessMode.triggerStop)
				continue;
			e.x -= PrecalculatedParticles.halfWidths[e.index];
			e.y -= PrecalculatedParticles.halfWidths[e.index];

			e.x += e.speedX * EndlessMode.delta;
			e.y += e.speedY * EndlessMode.delta;

			e.speedX /= EndlessMode.UnPlusDelta;
			e.speedY /= EndlessMode.UnPlusDelta;
			if (++e.index >= PrecalculatedParticles.widths.length) {
				explosions.removeValue(e, true);
				POOL.free(e);
			}
		}
		batch.setColor(AssetMan.WHITE);
	}

	public ExplosionColorOverTime init(float x, float y, float[] colors) {
		this.x = x - INITIAL_HALF_WIDTH;
		this.y = y - INITIAL_HALF_WIDTH;
		this.colors = colors;
		index = CSG.R.nextInt(maxIndex);

		speedY = (float) ((CSG.R.nextGaussian()) * Stats.V_PARTICULE_EXPLOSION_SLOW);
		speedX = (float) ((CSG.R.nextGaussian()) * Stats.V_PARTICULE_EXPLOSION_SLOW);
		return this;
	}

	public static void clear(Array<ExplosionColorOverTime> explosions) {
		POOL.freeAll(explosions);
		explosions.clear();
	}

	public static void add(Array<ExplosionColorOverTime> explosionColorOverTime, Enemy e, float[] colors) {
		for (int i = 0; i < EndlessMode.fps; i++) {
			explosionColorOverTime.add(POOL.obtain().init(e.pos.x + e.getHalfWidth(), e.pos.y + e.getHalfHeight(), colors));
		}
	}

}
