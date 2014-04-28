package elements.particular.particles.individual.weapon;

import assets.AssetMan;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;

import elements.generic.weapons.player.Fireball;
import elements.particular.particles.individual.PrecalculatedParticles;

public class FireballParticle implements Poolable {
	
	private static final Pool<FireballParticle> POOL = new Pool<FireballParticle>(100) {
		@Override
		protected FireballParticle newObject() {
			return new FireballParticle();
		}
	};
	private float x, y;
	private int index;
	
	@Override
	public void reset() {}

	public static void add(Fireball wp, Array<FireballParticle> fireballs) {
		final FireballParticle p = FireballParticle.POOL.obtain();
		p.x = wp.pos.x;
		p.y = wp.pos.y;
		p.index = 0;
		fireballs.add(p);
	}

	public static void act(Array<FireballParticle> fireballs, SpriteBatch batch) {
		for (final FireballParticle p : fireballs) {
			batch.setColor(PrecalculatedParticles.colorsPinkWeapon[p.index]);
			p.x -= PrecalculatedParticles.halfWidthsFireballParticules[p.index];
			batch.draw(AssetMan.dust, p.x, p.y, PrecalculatedParticles.widthsFireballParticules[p.index], PrecalculatedParticles.widthsFireballParticules[p.index]);
			
			if (++p.index >= PrecalculatedParticles.widthsFireballParticules.length) {
				fireballs.removeValue(p, true);
				POOL.free(p);
			}
		}
		batch.setColor(AssetMan.WHITE);
	}

	public static void clear(Array<FireballParticle> fireballs) {
		POOL.freeAll(fireballs);
		POOL.clear();
		fireballs.clear();
	}
}
