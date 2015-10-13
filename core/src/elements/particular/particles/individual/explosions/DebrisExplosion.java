package elements.particular.particles.individual.explosions;

import java.util.Random;

import jeu.CSG;
import jeu.Stats;
import jeu.mode.EndlessMode;
import assets.AssetMan;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;

import elements.generic.Element;
import elements.generic.weapons.player.PlayerWeapon;
import elements.particular.bonuses.Bonus;
import elements.particular.bonuses.Bomb;

public class DebrisExplosion implements Poolable {
	
	private static final float WIDTH_BOMB = CSG.screenWidth / 2;
	private static final float WIDTH_SMALL = CSG.screenWidth / 15;
	private static final float MIN_WIDTH = Stats.u / 10;
	
	private static final Pool<DebrisExplosion> POOL = new Pool<DebrisExplosion>(100) {
		@Override
		protected DebrisExplosion newObject() {
			return new DebrisExplosion();
		}
	};
	private float x, y, angle, width, height;
	private int ttl;
	protected final float color;
	protected static final Random R = new Random();
	
	public DebrisExplosion() {
		final float tmp = ((CSG.R.nextFloat()/3f) * 2f) + 0.15f;
		color = AssetMan.convertARGB(1, tmp, tmp, tmp);
	}
	
	@Override
	public void reset() {	}

	public static void add(Array<DebrisExplosion> explosions, Element e) {
		for (int i = 0; i < EndlessMode.perf; i++) {
			explosions.add(POOL.obtain().init(e));
		}
	}

	private DebrisExplosion init(Element e) {
		x = e.pos.x + e.getDimensions().halfWidth;
		y = e.pos.y + e.getDimensions().halfHeight;
		angle = CSG.R.nextFloat() * 360;
		
		width = MIN_WIDTH + (MIN_WIDTH * CSG.R.nextFloat()); 
		height = Math.abs((float) ((R.nextGaussian() * WIDTH_SMALL))) + e.getDimensions().halfWidth + e.getDimensions().halfHeight;
		ttl = Math.abs((int) (R.nextGaussian() * 2)) + 3;
		return this;
	}
	
	private DebrisExplosion initImpact(float x, float y) {
		this.x = x;
		this.y = y;
		angle = CSG.R.nextFloat() * 360;
		
		width = MIN_WIDTH + (MIN_WIDTH * CSG.R.nextFloat()); 
		height = Math.abs((float) ((R.nextGaussian() * WIDTH_SMALL)));
		ttl = Math.abs((int) (R.nextGaussian() * 2)) + 3;
		return this;
	}
	
	private DebrisExplosion init(float x, float y) {
		this.x = x;
		this.y = y;
		angle = CSG.R.nextFloat() * 360;
		
		width = MIN_WIDTH + (MIN_WIDTH * CSG.R.nextFloat()); 
		height = Math.abs((float) ((R.nextGaussian() * WIDTH_BOMB))) + WIDTH_SMALL;
		ttl = Math.abs((int) (R.nextGaussian() * 2)) + 3;
		return this;
	}
	public static int tmpDecrase;
	public static void act(Array<DebrisExplosion> explosions, SpriteBatch batch) {
		if (EndlessMode.alternate) {
			for (final DebrisExplosion p : explosions) {
				batch.setColor(p.color);
				batch.draw(AssetMan.dust, p.x, p.y, 0, 0, p.width, p.height, 1, 1, p.angle);
				if (EndlessMode.triggerStop)
					continue;
				if (p.ttl-- < 0 || p.width < Stats.u) {
					POOL.free(p);
					explosions.removeValue(p, true);
				}
			}
		} else {
			for (final DebrisExplosion p : explosions) {
				batch.setColor(p.color);
				batch.draw(AssetMan.dust, p.x, p.y, 0, 0, p.width, p.height, 1, 1, p.angle);
			}
		}
		batch.setColor(AssetMan.WHITE);
	}

	public static void clear(Array<DebrisExplosion> explosions) {
		POOL.freeAll(explosions);
		explosions.clear();
	}

	public static void bomb(Bomb bonusBombe, Array<DebrisExplosion> debrisExplosions) {
		final float x = bonusBombe.pos.x + Bonus.HALF_WIDTH;
		final float y = bonusBombe.pos.y + Bonus.HALF_WIDTH;
		for (int i = -EndlessMode.fps; i < 100; i++) {
			debrisExplosions.add(POOL.obtain().init(x, y));
		}
	}

	public static void addEnemyTouched(Array<DebrisExplosion> explosions, PlayerWeapon a) {
		explosions.add(POOL.obtain().initImpact(a.pos.x + a.getDimensions().halfWidth + (a.dir.x * EndlessMode.delta), a.pos.y + a.getDimensions().halfHeight + (a.dir.y * EndlessMode.delta)));
	}
}
