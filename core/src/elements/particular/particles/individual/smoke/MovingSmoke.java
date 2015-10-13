package elements.particular.particles.individual.smoke;

import jeu.CSG;
import jeu.Stats;
import jeu.mode.EndlessMode;
import assets.AssetMan;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;

import elements.particular.particles.individual.PrecalculatedParticles;

public class MovingSmoke implements Poolable{
	
	private float x, y, dirX, dirY;
	private int index;
	private float[] colors;
	public static final Pool<MovingSmoke> POOL = new Pool<MovingSmoke>() {
		@Override
		protected MovingSmoke newObject() {
			return new MovingSmoke();
		}
	};
	
	public MovingSmoke init(float x, float y, boolean rnd, float[] colors) {
		return init(x, y, rnd, colors, 0, Stats.uDiv2);
	}
	
	public MovingSmoke init(float x, float y, boolean rnd, float[] colors, Vector2 dir) {
		return init(x, y, rnd, colors, dir.x, dir.y);
	}
	
	public MovingSmoke init(float x, float y, boolean rnd, float[] colors, float dirX, float dirY) {
		if (rnd)
			init(dirX, dirY, colors, (x - PrecalculatedParticles.INITIAL_HALF_WIDTH) + ((CSG.R.nextFloat() - .5f) * PrecalculatedParticles.INITIAL_HALF_WIDTH), y - PrecalculatedParticles.INITIAL_HALF_WIDTH);
		else
			init(dirX, dirY, colors, x - PrecalculatedParticles.INITIAL_HALF_WIDTH, y - PrecalculatedParticles.INITIAL_HALF_WIDTH);
		return this;
	}
	
	public MovingSmoke init(float x, float y, float[] colors, Vector2 dir) {
		init(dir.x, dir.y, colors, x - PrecalculatedParticles.INITIAL_HALF_WIDTH, y - PrecalculatedParticles.INITIAL_HALF_WIDTH);
		return this;
	}
	
	private void init(float dirX, float dirY, float[] colors, float posX, float posY) {
		index = 0;
		this.dirX = dirX;
		this.dirY = dirY;
		this.colors = colors;
		x = posX;
		y = posY;
	}
	
	public static void act(Array<MovingSmoke> smoke, SpriteBatch batch) {
		for (MovingSmoke s : smoke) {
			batch.setColor(s.colors[s.index]);
			batch.draw(AssetMan.dust, s.x, s.y, PrecalculatedParticles.widths[s.index], PrecalculatedParticles.widths[s.index]);
			if (EndlessMode.triggerStop)
				continue;
			s.x -= PrecalculatedParticles.halfWidths[s.index];
			s.y -= PrecalculatedParticles.halfWidths[s.index];
			s.x += s.dirX;
			s.y += s.dirY;
			if (++s.index >= s.colors.length) {
				smoke.removeValue(s, true);
				POOL.free(s);
			}
		}
		batch.setColor(AssetMan.WHITE);
	}
	
	@Override
	public void reset() {}
	
	public static void clear(Array<MovingSmoke> smoke) {
		POOL.freeAll(smoke);
		smoke.clear();
	}
}
