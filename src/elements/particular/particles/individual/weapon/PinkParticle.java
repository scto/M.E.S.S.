package elements.particular.particles.individual.weapon;

import jeu.Stats;
import assets.AssetMan;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;

import elements.generic.weapons.Weapons;
import elements.particular.particles.individual.PrecalculatedParticles;

public final class PinkParticle implements Poolable {
		
	public static final Pool<PinkParticle> POOL = new Pool<PinkParticle>(100) {
		@Override
		protected PinkParticle newObject() {
			return new PinkParticle();
		}
	};
	public static float width = Stats.U;
	private float x, y;
	private int index;

	@Override
	public void reset() {}

	public void init(Weapons wp) {
		x = wp.pos.x;
		y = wp.pos.y;
		index = 0;
	}

	public static void act(Array<PinkParticle> pinkParticles, SpriteBatch batch) {
		for (final PinkParticle p : pinkParticles) {
			batch.setColor(PrecalculatedParticles.colorsPinkWeapon[p.index]);
			batch.draw(AssetMan.dust, p.x, p.y, width, width);
			
			if (++p.index >= PrecalculatedParticles.colorsPinkWeapon.length) {
				pinkParticles.removeValue(p, true);
				POOL.free(p);
			}
		}
		batch.setColor(AssetMan.WHITE);
	}

	public static void clear(Array<PinkParticle> particulesLaser) {
		POOL.freeAll(particulesLaser);
		POOL.clear();
		particulesLaser.clear();
	}
}
