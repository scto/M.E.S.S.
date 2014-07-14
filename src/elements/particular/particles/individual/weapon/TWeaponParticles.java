package elements.particular.particles.individual.weapon;

import jeu.Stats;
import jeu.mode.EndlessMode;
import assets.AssetMan;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;

import elements.generic.weapons.player.TWeapon;

public final class TWeaponParticles implements Poolable {
	
	private static final Pool<TWeaponParticles> POOL = new Pool<TWeaponParticles>(150) {
		@Override
		protected TWeaponParticles newObject() {
			return new TWeaponParticles();
		}
	};
	private float angle, x, y, w, halfWidth, h = TWeapon.DIMENSIONS.height, halfHeight;
	private static float tmp = 0;
	
	public TWeaponParticles() {}
	
	@Override
	public void reset() {}

	public static void add(Array<TWeaponParticles> pArmeHantee, TWeapon a) {
		final TWeaponParticles p = POOL.obtain();
		p.w = TWeapon.DIMENSIONS.width;
		p.h = TWeapon.DIMENSIONS.height;
		p.halfWidth = TWeapon.DIMENSIONS.halfWidth;
		p.halfHeight = TWeapon.DIMENSIONS.halfHeight;
		p.x = a.pos.x;
		p.y = a.pos.y;
		p.angle = a.angle;
		pArmeHantee.add(p);
	}
	private static float tmpDecrease = 100;
	public static void act(Array<TWeaponParticles> pArmeHantee, SpriteBatch batch) {
		tmp = EndlessMode.delta * Stats.U90;
		if (tmp < Stats.uDiv75)
			tmp = Stats.uDiv75;
		tmpDecrease = tmp / 2;
		for (final TWeaponParticles p : pArmeHantee) {
			batch.draw(AssetMan.tWeapon, p.x, p.y, p.halfWidth, p.halfHeight, p.w, p.h, 1, 1, p.angle);
			p.w -= tmp;
			p.h -= tmp;
			p.x += tmpDecrease;
			p.y += tmpDecrease;
			p.halfWidth -= tmpDecrease;
			p.halfHeight -= tmpDecrease;
			if (p.w < Stats.u) {
				POOL.free(p);
				pArmeHantee.removeValue(p, true);
			}
		}
	}

	public static void clear(Array<TWeaponParticles> pArmeHantee) {
		POOL.clear();
		POOL.freeAll(pArmeHantee);
		pArmeHantee.clear();
	}
}
