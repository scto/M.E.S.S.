package elements.particular.particles.individual.weapon;

import java.util.Random;

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
	private static final int WIDTH = ArmeAdd.WIDTH;
	private float w, x, y;
	private static final Random R = new Random();
	private final float angle;
	public static float COLOR = ArmeAdd.COLORS[R.nextInt(ArmeAdd.COLORS.length)];
	private static float tmp;
	
	public GreenAddParticle() {
		angle = R.nextFloat() * 360;
	}

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
		for (final GreenAddParticle p : GreenAddParticles) {
			tmp = p.w / 2;
			batch.draw(AssetMan.debris, p.x, p.y, tmp, tmp, p.w, p.w, 1, 1, p.angle);
			
			p.w -= 2;
			p.x++;
			p.y++;
			
			if (p.w < 6) {
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
