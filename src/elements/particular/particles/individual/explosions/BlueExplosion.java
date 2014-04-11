package elements.particular.particles.individual.explosions;

import jeu.CSG;
import jeu.EndlessMode;
import jeu.Stats;
import assets.AssetMan;
import assets.SoundMan;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;

import elements.generic.Player;
import elements.generic.enemies.Enemy;
import elements.generic.weapons.player.PlayerWeapon;

public class BlueExplosion implements Poolable {
	
	private static final float WIDTH = CSG.screenWidth / 50;
	private static final float MIN_WIDTH = 1;
	private static final Pool<BlueExplosion> POOL = new Pool<BlueExplosion>(100) {
		@Override
		protected BlueExplosion newObject() {
			return new BlueExplosion();
		}
	};
	private float speedX, speedY, x, y;
	private float width;
	private int ttl;
	protected final float color;
	private static int bigger = 0;
	
	public BlueExplosion() {
		color = AssetMan.convertARGB(1, CSG.R.nextFloat()/8, (CSG.R.nextFloat() + .8f) / 1.6f, .85f);
	}
	
	@Override
	public void reset() {	}

	public static void add(Array<BlueExplosion> explosions, Enemy e) {
		if (++bigger > 4 && explosions.size < 400 && EndlessMode.perf > 2) {
			bigger = 0;
			create(e.getExplosionCount()*4, explosions, e);
			SoundMan.playBruitage(SoundMan.bigExplosion);
		} else {
			create(e.getExplosionCount(), explosions, e);
		}
	}

	private static void create(int max, Array<BlueExplosion> explosions, Enemy e) {
		for (int i = -EndlessMode.fps*2; i < max; i++) 
			explosions.add(POOL.obtain().init(e));
	}
	
	private BlueExplosion init(Enemy e) {
		x = (e.pos.x + (e.getWidth() * CSG.R.nextFloat()) );
		y = (e.pos.y + (e.getHeight() * CSG.R.nextFloat()) );

		speedY = (float) (((CSG.R.nextGaussian()) * Stats.V_PARTICULE_EXPLOSION_SLOW) + e.getDirectionY() * CSG.R.nextFloat());
		speedX = (float) (((CSG.R.nextGaussian()) * Stats.V_PARTICULE_EXPLOSION_SLOW) + e.getDirectionX() * CSG.R.nextFloat());
		
		width = Math.abs((float) ((CSG.R.nextGaussian() * WIDTH))) + MIN_WIDTH;
		ttl = Math.abs((int) (CSG.R.nextGaussian() * 50)) + 15;
		return this;
	}

	private static float tmp;
	public static void act(Array<BlueExplosion> explosions, SpriteBatch batch) {
		if (EndlessMode.alternate) {
			for (final BlueExplosion p : explosions) {
				batch.setColor(p.color);
				batch.draw(AssetMan.dust, p.x, p.y, p.width, p.width);
			}
		} else {
			for (final BlueExplosion p : explosions) {
				batch.setColor(p.color);
				batch.draw(AssetMan.dust, p.x, p.y, p.width, p.width);
				if (EndlessMode.triggerStop)
					continue;
				p.speedX /= EndlessMode.UnPlusDelta;
				p.speedY /= EndlessMode.UnPlusDelta;
				tmp = p.width - (p.width / EndlessMode.UnPlusDelta);
				p.width -= tmp;
				tmp /= 2;
				p.x += tmp;
				p.y += tmp;
				p.x += p.speedX * EndlessMode.delta2;
				p.y += p.speedY * EndlessMode.delta2;
				if (p.ttl-- < 0 || p.width < Stats.u) {
					POOL.free(p);
					explosions.removeValue(p, true);
				}
			}
		}
		batch.setColor(AssetMan.WHITE);
	}

	public static void clear(Array<BlueExplosion> explosions) {
		POOL.freeAll(explosions);
		explosions.clear();
	}
	

	public static void blow(PlayerWeapon a, Array<BlueExplosion> explosions) {
		for (BlueExplosion e : explosions) {
			a.dir.x = (e.x) - Player.xCenter;
			a.dir.y = (e.y) - Player.yCenter;
			a.dir.nor();
			a.dir.scl(Explosion.BOMB_SCALE);
			e.speedX += a.dir.x;
			e.speedY += a.dir.y;
		}
	}
}
