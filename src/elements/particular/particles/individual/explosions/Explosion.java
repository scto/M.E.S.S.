package elements.particular.particles.individual.explosions;

import jeu.CSG;
import jeu.Stats;
import jeu.mode.EndlessMode;
import assets.AssetMan;
import assets.SoundMan;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;

import elements.generic.Player;
import elements.generic.enemies.Enemy;
import elements.generic.weapons.enemies.EnemyWeapon;
import elements.generic.weapons.player.PlayerWeapon;
import elements.particular.particles.Particles;

public class Explosion implements Poolable {
	
	private static final float WIDTH = CSG.screenWidth / 50;
	private static final float MIN_WIDTH = 1;
	private static final Pool<Explosion> POOL_STANDARD = new Pool<Explosion>(100) {
		@Override
		protected Explosion newObject() {
			return new Explosion();
		}
	};
	private float speedX, speedY, x, y;
	private float width;
	private int ttl;
	protected final float color = AssetMan.convertARGB(1, 1f, 					(CSG.R.nextFloat() + .8f) / 1.6f, 			CSG.R.nextFloat()/8);
	private static int bigger = 0;
	public static final int GAUSSIAN_FACTOR = 50;
	public static final int STANDARD_EXPLOSION = 1, BLUE_EXPLOSION = 2, GREEN_EXPLOSION = 3;
	public static final float BOMB_SCALE = Stats.U * 15;
	public static final int LIMIT_BIGGER = 600;
	
	@Override
	public void reset() {	}

	public static void add(Array<Explosion> explosions, Enemy e) {
		if (++bigger > 4 && explosions.size < LIMIT_BIGGER && EndlessMode.perf > 2) {
			bigger = 0;
			create(e.getExplosionCount()*4, explosions, e);
			SoundMan.playBruitage(SoundMan.bigExplosion);
		} else {
			create(e.getExplosionCount(), explosions, e);
		}
	}

	private static void create(int max, Array<Explosion> explosions, Enemy e) {
		for (int i = -EndlessMode.fps*2; i < max; i++) {
			explosions.add(POOL_STANDARD.obtain().init(e));
		}
	}
	
	public static void add(int max, EnemyWeapon e) {
		for (int i = -EndlessMode.fps; i < max; i++) 
			Particles.EXPLOSIONS.add(POOL_STANDARD.obtain().init(e));
	}
	
	
	private Explosion init(EnemyWeapon e) {
		x = (e.pos.x + (e.getWidth() * CSG.R.nextFloat()) );
		y = (e.pos.y + (e.getHeight() * CSG.R.nextFloat()) );
		
		speedY = (float) ((CSG.R.nextGaussian()) * Stats.V_PARTICULE_EXPLOSION_SLOW);
		speedX = (float) ((CSG.R.nextGaussian()) * Stats.V_PARTICULE_EXPLOSION_SLOW);
		
		width = Math.abs((float) ((CSG.R.nextGaussian() * WIDTH))) + MIN_WIDTH;
		ttl = (int) (CSG.R.nextFloat() * 15) + 10;
		return this;
	}
	
	private Explosion init(Enemy e) {
		x = (e.pos.x + (e.getWidth() * CSG.R.nextFloat()) );
		y = (e.pos.y + (e.getHeight() * CSG.R.nextFloat()) );

		speedY = (float) (((CSG.R.nextGaussian()) * Stats.V_PARTICULE_EXPLOSION_SLOW) + e.getDirectionY() * CSG.R.nextFloat());
		speedX = (float) (((CSG.R.nextGaussian()) * Stats.V_PARTICULE_EXPLOSION_SLOW) + e.getDirectionX() * CSG.R.nextFloat());
		
		width = Math.abs((float) ((CSG.R.nextGaussian() * WIDTH))) + MIN_WIDTH;
		ttl = Math.abs((int) (CSG.R.nextGaussian() * GAUSSIAN_FACTOR)) + 15;
		return this;
	}

	private static float tmp;
	public static void act(Array<Explosion> explosions, SpriteBatch batch) {
		if (EndlessMode.alternate) {
			for (final Explosion p : explosions) {
				batch.setColor(p.color);
				batch.draw(AssetMan.dust, p.x, p.y, p.width, p.width);
			}
		} else {
			for (final Explosion p : explosions) {
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
					POOL_STANDARD.free(p);
					explosions.removeValue(p, true);
				}
			}
		}
		batch.setColor(AssetMan.WHITE);
	}

	public static void clear(Array<Explosion> explosions) {
		POOL_STANDARD.freeAll(explosions);
		explosions.clear();
	}

	public static void blow(PlayerWeapon a, Array<Explosion> explosions) {
		for (Explosion e : explosions) {
			a.dir.x = (e.x) - Player.xCenter;
			a.dir.y = (e.y) - Player.yCenter;
			a.dir.nor();
			a.dir.scl(BOMB_SCALE);
			e.speedX += a.dir.x;
			e.speedY += a.dir.y;
		}
	}

}
