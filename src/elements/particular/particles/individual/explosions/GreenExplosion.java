package elements.particular.particles.individual.explosions;

import jeu.CSG;
import jeu.Stats;
import jeu.mode.EndlessMode;
import assets.AssetMan;
import assets.SoundMan;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;

import elements.generic.Player;
import elements.generic.enemies.Enemy;
import elements.generic.weapons.player.PlayerWeapon;

public class GreenExplosion implements Poolable {
	
	private static final float WIDTH = CSG.screenWidth / 50;
	private static final float MIN_WIDTH = 1;
	private static final Pool<GreenExplosion> POOL = new Pool<GreenExplosion>(50) {
		@Override
		protected GreenExplosion newObject() {
			return new GreenExplosion();
		}
	};
	private float speedX, speedY, x, y;
	private float width;
	private int ttl;
//	private final float color = AssetMan.convertARGB(1, CSG.R.nextFloat()/8, 1, (CSG.R.nextFloat() + .8f) / 1.6f);
	private final float color = getColor();
	private static int bigger = 0;
	private static final Vector2 tmpVector = new Vector2();
	
	
	@Override
	public void reset() {	}

	private float getColor() {
		if (CSG.R.nextBoolean())
			return AssetMan.convertARGB(1, 	CSG.R.nextFloat()/8, 				1, 		(CSG.R.nextFloat() + .8f) / 1.6f);
		return AssetMan.convertARGB(1,		(CSG.R.nextFloat() + .8f) / 1.6f, 	1, 		CSG.R.nextFloat()/8);
	}

	public static void add(int max, Array<GreenExplosion> explosionsGreens, float posX, float posY) {
		for (int i = -EndlessMode.fps; i < max; i++) {
			explosionsGreens.add(POOL.obtain().init(posX, posY));
		}
	}
	

	public static void add(Array<GreenExplosion> explosions, Enemy e) {
		if (++bigger > 4 && explosions.size < Explosion.LIMIT_BIGGER && EndlessMode.perf > 2) {
			bigger = 0;
			create(e.getExplosionCount()*4, explosions, e);
			SoundMan.playBruitage(SoundMan.bigExplosion);
		} else {
			create(e.getExplosionCount(), explosions, e);
		}
	}
	
	private static void create(int max, Array<GreenExplosion> explosions, Enemy e) {
		for (int i = -EndlessMode.fps*2; i < max; i++) 
			explosions.add(POOL.obtain().init(e));
	}

	private GreenExplosion init(Enemy e) {
		x = (e.pos.x + (e.getWidth() * CSG.R.nextFloat()) );
		y = (e.pos.y + (e.getHeight() * CSG.R.nextFloat()) );

		speedY = (float) (((CSG.R.nextGaussian()) * Stats.V_PARTICLE_EXPLOSION_SLOW) + e.getDirectionY() * CSG.R.nextFloat());
		speedX = (float) (((CSG.R.nextGaussian()) * Stats.V_PARTICLE_EXPLOSION_SLOW) + e.getDirectionX() * CSG.R.nextFloat());
		
		width = Math.abs((float) ((CSG.R.nextGaussian() * WIDTH))) + MIN_WIDTH;
		ttl = Math.abs((int) (CSG.R.nextGaussian() * 50)) + 8;
		return this;
	}

	private GreenExplosion init(float x, float y) {
		this.x = (x + (Player.WIDTH_ADD * CSG.R.nextFloat()) );
		this.y = (y + (Player.WIDTH_ADD * CSG.R.nextFloat()) );
		
		speedY = (float) ((CSG.R.nextGaussian()) * Stats.V_PARTICLE_EXPLOSION_SLOW);
		speedX = (float) ((CSG.R.nextGaussian()) * Stats.V_PARTICLE_EXPLOSION_SLOW);
		
		width = Math.abs((float) ((CSG.R.nextGaussian() * WIDTH))) + MIN_WIDTH;
		ttl = Math.abs((int) (CSG.R.nextFloat() * 25)) + 8;
		return this;
	}
	
	private static float tmp;
	public static void act(Array<GreenExplosion> explosions, SpriteBatch batch) {
		if (EndlessMode.alternate || EndlessMode.triggerStop) {
			for (final GreenExplosion p : explosions) {
				batch.setColor(p.color);
				batch.draw(AssetMan.dust, p.x, p.y, p.width, p.width);
			}
		} else {
			for (final GreenExplosion p : explosions) {
				batch.setColor(p.color);
				batch.draw(AssetMan.dust, p.x, p.y, p.width, p.width);
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

	public static void clear(Array<GreenExplosion> explosions) {
		POOL.freeAll(explosions);
		explosions.clear();
	}
	

	public static void blow(PlayerWeapon a, Array<GreenExplosion> explosions) {
		for (GreenExplosion e : explosions) {
			tmpVector.x = a.dir.x;
			tmpVector.y = a.dir.y;
			tmpVector.x = (e.x) - Player.xCenter;
			tmpVector.y = (e.y) - Player.yCenter;
			tmpVector.nor();
			tmpVector.scl(Explosion.BOMB_SCALE);
		}
	}

}