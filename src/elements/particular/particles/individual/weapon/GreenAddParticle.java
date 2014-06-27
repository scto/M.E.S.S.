package elements.particular.particles.individual.weapon;

import jeu.CSG;
import jeu.Stats;
import assets.AssetMan;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;

import elements.generic.weapons.player.ArmeAdd;

public class GreenAddParticle implements Poolable {
	
	public static final Pool<GreenAddParticle> POOL = new Pool<GreenAddParticle>(50) {
		@Override
		protected GreenAddParticle newObject() {
			return new GreenAddParticle();
		}
	};
	private static final int WIDTH = (int) ArmeAdd.DIMENSIONS.width;
	private float w, x, y;
	public static float COLOR = ArmeAdd.COLORS[CSG.R.nextInt(ArmeAdd.COLORS.length)];
	private static float tmp, half;
	
	@Override
	public void reset() {}

	public static void add(ArmeAdd wp, Array<GreenAddParticle> GreenAddParticles) {
		final GreenAddParticle p = POOL.obtain();
		p.x = wp.pos.x;
		p.y = wp.pos.y;
		p.w = WIDTH;
		GreenAddParticles.add(p);
	}

	public static void act(Array<GreenAddParticle> GreenAddParticles, SpriteBatch batch) {
		batch.setColor(COLOR);
		tmp = Stats.uSur2;
		half = tmp/2;
		for (final GreenAddParticle p : GreenAddParticles) {
			p.w -= tmp;
			p.x += half;
			p.y += half;
			batch.draw(AssetMan.dust, p.x, p.y, p.w, p.w);
			if (p.w < Stats.u) {
				GreenAddParticles.removeValue(p, true);
				POOL.free(p);
			}
		}
		batch.setColor(AssetMan.WHITE);
	}

	public static void clear(Array<GreenAddParticle> pArmeAdd) {
		POOL.freeAll(pArmeAdd);
		POOL.clear();
		pArmeAdd.clear();
	}
}
