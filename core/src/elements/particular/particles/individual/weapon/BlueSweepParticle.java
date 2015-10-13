package elements.particular.particles.individual.weapon;

import jeu.CSG;
import assets.AssetMan;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;

import elements.generic.weapons.player.BlueSweepWeapon;
import elements.generic.weapons.player.PlayerWeapon;
import elements.particular.particles.Particles;

public class BlueSweepParticle implements Poolable {
	
	private static final Pool<BlueSweepParticle> POOL = new Pool<BlueSweepParticle>(100) {
		@Override
		protected BlueSweepParticle newObject() {
			return new BlueSweepParticle();
		}
	};
	private static final float WIDTH = CSG.screenWidth / 150;
	private float x, y, angle;
	private int ttl;
	private final float color, height;
	public BlueSweepParticle() {
		height = (WIDTH + ( (CSG.R.nextFloat() / 6) * WIDTH)) * 3;
		angle = (float) (CSG.R.nextGaussian() * 360f);
		color = PlayerWeapon.COLORS[CSG.R.nextInt(PlayerWeapon.COLORS.length)];
	}

	@Override
	public void reset() {}

	public static void add(BlueSweepWeapon a) {
		final BlueSweepParticle p = POOL.obtain();
		p.x = a.pos.x + BlueSweepWeapon.DIMENSIONS.halfWidth;
		p.y = a.pos.y + BlueSweepWeapon.DIMENSIONS.halfWidth;
		p.ttl = 4;
		Particles.BLUE_SWEEP_WEAPON.add(p);
	}

	public static void act(Array<BlueSweepParticle> pArmeBalayage, SpriteBatch batch) {
		for (final BlueSweepParticle p : pArmeBalayage) {
			batch.setColor(p.color);
			batch.draw(AssetMan.debris, p.x, p.y, 0, 0, WIDTH, p.height, 1, 1, p.angle);
			p.angle += 10;
			if (0 > --p.ttl) {
				pArmeBalayage.removeValue(p, true);
				POOL.free(p);
			}
		}
		batch.setColor(AssetMan.WHITE);
	}

	public static void clear(Array<BlueSweepParticle> pArmeBalayage) {
		POOL.freeAll(pArmeBalayage);
		POOL.clear();
		pArmeBalayage.clear();
	}
}
