package elements.particular.particles.individual.weapon;

import jeu.EndlessMode;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;

import elements.generic.weapons.player.TWeapon;

public final class TWeaponParticles implements Poolable {
	
	private static final float WIDTH = TWeapon.width;
	private static final Pool<TWeaponParticles> POOL = new Pool<TWeaponParticles>(150) {
		@Override
		protected TWeaponParticles newObject() {
			return new TWeaponParticles();
		}
	};
	private float angle, x, y, w = WIDTH, halfWidth;
	private static boolean alternate = false;
	private static float tmp = 0;
	
	public TWeaponParticles() {}
	
	@Override
	public void reset() {
		w = WIDTH;
	}

	public static void add(Array<TWeaponParticles> pArmeHantee, TWeapon a) {
		if (pArmeHantee.size > 2048) {
			alternate = !alternate;
			if (alternate)
				return;
		}
		final TWeaponParticles p = POOL.obtain();
		p.halfWidth = p.w / 2;
		p.x = a.pos.x;
		p.y = a.pos.y;
		p.angle = a.angle;
		pArmeHantee.add(p);
	}

	public static void act(Array<TWeaponParticles> pArmeHantee, SpriteBatch batch) {
		for (final TWeaponParticles p : pArmeHantee) {
			batch.draw(TWeapon.ANIMATED.getTexture(1), p.x, p.y, p.halfWidth, p.halfWidth, p.w, p.w, 1, 1, p.angle);
			
			tmp = (p.w*28) * (EndlessMode.delta);
			p.w -= tmp;
			tmp /= 2;

			p.x += tmp;
			p.y += tmp;
			p.halfWidth = p.w / 2;
			if (p.w < 6) {
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
