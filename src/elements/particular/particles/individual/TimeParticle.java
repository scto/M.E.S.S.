package elements.particular.particles.individual;

import jeu.CSG;
import jeu.EndlessMode;
import assets.AssetMan;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;

import elements.generic.Element;
import elements.generic.Player;
import elements.generic.enemies.Enemy;

public class TimeParticle implements Poolable {
	
	private static final float LARGEUR = CSG.screenWidth / 55, DEMI_LARGEUR = LARGEUR / 2, OFFSET_X = Player.DEMI_LARGEUR - LARGEUR;
	public static final Pool<TimeParticle> POOL = new Pool<TimeParticle>() {
		@Override
		protected TimeParticle newObject() {
			return new TimeParticle();
		}
	};
	private float x, y, time;
	private final float angle = CSG.R.nextFloat() * 360, color = AssetMan.convertARGB(1, 0, 1, CSG.R.nextFloat());
	
	private static final Vector2 tmp = new Vector2();
	public TimeParticle init(Element e) {
		x = (e.pos.x + e.getHalfWidth()) - DEMI_LARGEUR;
		y = (e.pos.y + e.getHalfHeight()) - DEMI_LARGEUR;
		x += (CSG.R.nextGaussian()/2) * e.getHalfWidth(); 
		y += (CSG.R.nextGaussian()/2) * e.getHalfHeight(); 
		time = .5f + (EndlessMode.STOP-.5f) * CSG.R.nextFloat();
		return this;
	}

	public static void act(Array<TimeParticle> time, SpriteBatch batch) {
		for (TimeParticle t : time) {
			batch.setColor(t.color);
			batch.draw(AssetMan.dust, t.x, t.y, LARGEUR, LARGEUR);
			t.time -= EndlessMode.delta;
			if (t.time < 0) {
				time.removeValue(t, false);
				POOL.free(t);
			}
		}
		batch.setColor(AssetMan.WHITE);
	}

	public static void clear(Array<TimeParticle> time) {
		POOL.freeAll(time);
		time.clear();
	}

	@Override
	public void reset() {
	}

	public static void generate(Enemy e, Array<TimeParticle> time) {
		for (int i = 0; i < EndlessMode.fps; i++) {
			time.add(POOL.obtain().init(e));
		}
	}

}
