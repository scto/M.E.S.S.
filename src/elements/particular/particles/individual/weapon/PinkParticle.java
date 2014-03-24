package elements.particular.particles.individual.weapon;


import jeu.CSG;
import jeu.Stats;
import assets.AssetMan;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;

import elements.generic.weapons.Weapons;

public final class PinkParticle implements Poolable {
		
	public static final Pool<PinkParticle> POOL = new Pool<PinkParticle>(100) {
		@Override
		protected PinkParticle newObject() {
			return new PinkParticle();
		}
	};
	public static float width = Stats.U;
	private float x, y;
	public static final float[] colors = initColors();
	private int index;
	private static final int nbFrames = 7;

	@Override
	public void reset() {}

	public void init(Weapons wp) {
		x = wp.pos.x;
		y = wp.pos.y;
		index = 0;
	}

	public static void act(Array<PinkParticle> pinkParticles, SpriteBatch batch) {
		for (final PinkParticle p : pinkParticles) {
			batch.setColor(colors[p.index]);
			batch.draw(AssetMan.dust, p.x, p.y, width, width);
			
			if (++p.index >= colors.length) {
				pinkParticles.removeValue(p, true);
				POOL.free(p);
			}
		}
		batch.setColor(AssetMan.WHITE);
	}

	private static float[] initColors() {
		int cpt = 0;
		float g = 1f;
		final float step = 0.95f / nbFrames;
		Array<Float> tmp = new Array<Float>();
		while (cpt < nbFrames) {
			tmp.add(AssetMan.convertARGB(
					1,
					1,
					(g/4),
					g));
			g -= step;
			cpt++;
		}
		return CSG.convert(tmp);
	}

	public static void clear(Array<PinkParticle> particulesLaser) {
		POOL.freeAll(particulesLaser);
		POOL.clear();
		particulesLaser.clear();
	}
}
