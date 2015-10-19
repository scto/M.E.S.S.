package elements.particular.particles.individual.weapon;

import jeu.CSG;
import assets.AssetMan;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;

import elements.generic.weapons.player.PlayerWeapon;
import elements.generic.weapons.player.SunWeapon;

public class SunParticle implements Poolable {
	
	public static final Pool<SunParticle> POOL = new Pool<SunParticle>(100) {
		@Override
		protected SunParticle newObject() {
			return new SunParticle();
		}
	};
	public static float width = SunWeapon.DIMENSIONS.width, tmp, halfWidh = width/2;
	private float x, y;
	private int ttl = 0;
	private final float color;

	public SunParticle() {
		color = PlayerWeapon.COLORS[CSG.R.nextInt(SunWeapon.COLORS.length)];
	}

	@Override
	public void reset() {}

	public void init(SunWeapon sunWeapon, Vector2 vector) {
		x = sunWeapon.pos.x;
		y = sunWeapon.pos.y;
		ttl = 0;
	}
	
	public static void act(Array<SunParticle> particles, SpriteBatch batch) {
		for (final SunParticle p : particles) {
			batch.setColor(p.color);
			batch.draw(AssetMan.dust, p.x, p.y, halfWidh, halfWidh);
			if (p.ttl++ > 3) {
				particles.removeValue(p, true);
				POOL.free(p);
			}
		}
		batch.setColor(CSG.gm.palette().white);
	}

	public static void clear(Array<SunParticle> particles) {
		POOL.freeAll(particles);
		POOL.clear();
		particles.clear();
	}
}
