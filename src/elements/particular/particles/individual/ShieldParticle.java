package elements.particular.particles.individual;

import jeu.EndlessMode;
import jeu.Stats;
import assets.AssetMan;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.badlogic.gdx.utils.Pools;

import elements.generic.Player;
import elements.particular.particles.Particles;

public class ShieldParticle implements Poolable {

	private float x, y, time;
	private final static float WIDTH = Stats.u;
	public static final float HALF_WIDTH = WIDTH / 2;
	private static final Pool<ShieldParticle> POOL = Pools.get(ShieldParticle.class);

	public static void add(float x, float y, float time) {
		ShieldParticle p = POOL.obtain();
		p.x = x;
		p.y = y;
		p.time = EndlessMode.now + time;
		Particles.SHIELD.add(p);
	}

	@Override
	public void reset() {
	}

	public static void act(SpriteBatch batch, Array<ShieldParticle> particles) {
		batch.setColor(.9f, .9f - Player.alphaShield, 1, 1);
		for (final ShieldParticle p : particles) {
			batch.draw(AssetMan.dust, p.x, p.y, WIDTH, WIDTH);
			if (p.time < EndlessMode.now) {
				particles.removeValue(p, true);
				POOL.free(p);
			}
		}
		batch.setColor(AssetMan.WHITE);
	}

	public static void clear(Array<ShieldParticle> particles) {
		POOL.freeAll(particles);
		particles.clear();
	}

}
