package elements.particular.particles.individual.weapon;

import java.util.Random;

import jeu.CSG;
import jeu.EndlessMode;
import jeu.Stats;
import assets.AssetMan;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;

import elements.generic.weapons.player.Fireball;

public class FireballParticle implements Poolable {
	
	private static final Pool<FireballParticle> POOL = new Pool<FireballParticle>(100) {
		@Override
		protected FireballParticle newObject() {
			return new FireballParticle();
		}
	};
	public static float width = Stats.UU, tmp;
	private float x, y;
	private int index;
	private final float speedX, speedY;
	private static final Random R = new Random();
	private final static float[] widths = initWidths();
	private final static float[] halfWidths = CSG.getHalf(widths);
	public static final float[] colors = initColors();
	
	public FireballParticle() {
		speedX = ((R.nextFloat() / 4f) - .125f) * 350f;
		speedY = ((R.nextFloat() / 4f) - .125f) * 280f;
	}

	private static float[] initWidths() {
		Array<Float> tmp = new Array<Float>();
		float w = width;
		while (w >= Stats.u) {
			tmp.add(w);
			w *= 0.75f;
			System.out.println(w);
		}
		System.out.println("width fireball particles size : " + tmp.size);
		return CSG.convert(tmp);
	}
	
	private static float[] initColors() {
		int cpt = 0;
		float g = .9f;
		final float step = 0.95f / widths.length;
		Array<Float> tmp = new Array<Float>();
		while (cpt < widths.length) {
			tmp.add(AssetMan.convertARGB(1, 1, g, 0));
			g -= step;
			cpt++;
		}
		System.out.println("colors fireball particles size : " + tmp.size);
		return CSG.convert(tmp);
	}

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
			batch.setColor(colors[p.index]);
			p.x -= halfWidths[p.index];
			batch.draw(AssetMan.dust, p.x, p.y, widths[p.index], widths[p.index]);
			p.x += p.speedX * EndlessMode.delta;
			p.y += p.speedY * EndlessMode.delta;
			
			if (++p.index >= widths.length) {
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
