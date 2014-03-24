package elements.particular.particles.individual;

import java.util.Random;

import jeu.CSG;
import jeu.EndlessMode;
import jeu.Stats;
import assets.AssetMan;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;

import elements.generic.weapons.player.PlayerWeapon;

public class Debris implements Poolable{
	
	private static final Pool<Debris> POOL = new Pool<Debris>(30) {
		@Override
		protected Debris newObject() {
			return new Debris();
		}
	};
	private float speedX, speedY, angle, x, y, color, trigger;
	private final float width, rotation;
	private static final float WIDTH = CSG.screenWidth / 90;
	private static final Random R = new Random();
	
	public Debris() {
		rotation = (R.nextFloat() -.5f) * 5000f;
		width = ((R.nextFloat()+1) * WIDTH) + 1;
	}
	
	@Override
	public void reset() {}

	public void init(PlayerWeapon a) {
		x = a.pos.x + a.getHalfWidth();
		y = a.pos.y + a.getHalfHeight();
		
		speedX = (a.dir.x + ((R.nextFloat()-.5f) * Stats.SPEED_DEBRIS));
		speedY = (a.dir.y + ((R.nextFloat()-.5f) * Stats.SPEED_DEBRIS));
		
		angle = a.dir.angle();
		color = a.getColor();
		trigger = (EndlessMode.now + R.nextFloat() / 10) + .15f;
	}

	public static void add(PlayerWeapon a, Array<Debris> debris) {
		a.nextColor();
		for (int i = -EndlessMode.perf; i < EndlessMode.perf; i++) {
			final Debris r = POOL.obtain();
			r.init(a);
			debris.add(r);
		}
	}

	public static void act(Array<Debris> debris, SpriteBatch batch) {
		for (final Debris d : debris) {
			batch.setColor(d.color);
			batch.draw(AssetMan.dust, d.x, d.y, 0, 0, d.width, d.width, 1, 1, d.angle);
			
			d.speedX /= 1.045f;
			d.speedY /= 1.045f;
			d.x += d.speedX * EndlessMode.delta;
			d.y += d.speedY * EndlessMode.delta;
			d.angle += d.rotation * EndlessMode.delta;
			if (EndlessMode.now > d.trigger) {
				POOL.free(d);
				debris.removeValue(d, true);
			}
		}
		batch.setColor(AssetMan.WHITE);
	}

	public static void clear(Array<Debris> debris) {
		POOL.freeAll(debris);
		debris.clear();
	}
}

