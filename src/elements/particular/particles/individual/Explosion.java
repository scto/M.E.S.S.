package elements.particular.particles.individual;

import java.util.Random;

import jeu.CSG;
import jeu.EndlessMode;
import jeu.Stats;
import assets.AssetMan;
import assets.SoundMan;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;

import elements.generic.enemies.Enemy;
import elements.generic.weapons.enemies.EnemyWeapon;
import elements.generic.weapons.player.PlayerWeapon;
import elements.particular.particles.Particles;

public class Explosion implements Poolable {
	
	private static final float WIDTH_SMALL = CSG.screenWidth / 60;
	private static final float WIDTH = CSG.screenWidth / 30;
	private static final float MIN_WIDTH = 1;
	private static final Pool<Explosion> POOL = new Pool<Explosion>(100) {
		@Override
		protected Explosion newObject() {
			return new Explosion();
		}
	};
	private float speedX, speedY, x, y;
	private float width;
	private int ttl;
	protected final float color;
	protected static final Random R = new Random();
	private static int bigger = 0;
	
	public Explosion() {
		color = AssetMan.convertARGB(1, 1f, (R.nextFloat() + .8f) / 1.6f, R.nextFloat()/8);
	}
	
	@Override
	public void reset() {	}

	public static void add(Array<Explosion> explosions, Enemy e) {
		if (++bigger > 4 && explosions.size < 200 && EndlessMode.perf > 2) {
			bigger = 0;
			create(e.getExplosionCount()*4, explosions, e);
			SoundMan.playBruitage(SoundMan.bigExplosion);
		} else {
			create(e.getExplosionCount(), explosions, e);
		}
	}

	private static void create(int max, Array<Explosion> explosions, Enemy e) {
		for (int i = -EndlessMode.fps; i < max; i++) 
			explosions.add(POOL.obtain().init(e));
	}
	
	public static void addSmall(Array<Explosion> explosions, PlayerWeapon e, Enemy enemy) {
		for (int i = -EndlessMode.perf; i <= EndlessMode.perf; i++) 
			explosions.add(POOL.obtain().bulletImpact(e, enemy));
	}
	
	public static void add(int max, EnemyWeapon e) {
		for (int i = -EndlessMode.fps; i < max; i++) 
			Particles.EXPLOSIONS.add(POOL.obtain().init(e));
	}
	
	
	private Explosion bulletImpact(PlayerWeapon e, Enemy enemy) {
		speedY = (float) ((R.nextGaussian()) * Stats.V_PARTICULE_EXPLOSION_SLOW) + (e.getDirection().y/10);
		speedX = (float) ((R.nextGaussian()) * Stats.V_PARTICULE_EXPLOSION_SLOW) + (e.getDirection().x/10);
		
		width = Math.abs((float) ((R.nextGaussian() * WIDTH_SMALL))) + MIN_WIDTH;
		x = (e.pos.x - width/2) + (e.getWidth()*CSG.R.nextFloat());
		y = (e.pos.y - width/2) + (e.getHeight()*CSG.R.nextFloat());
		ttl = (int) (R.nextFloat() * 35) + 15;
		return this;
	}
	private Explosion init(EnemyWeapon e) {
		x = (e.pos.x + (e.getWidth() * R.nextFloat()) );
		y = (e.pos.y + (e.getHeight() * R.nextFloat()) );
		
		speedY = (float) ((R.nextGaussian()) * Stats.V_PARTICULE_EXPLOSION_SLOW);
		speedX = (float) ((R.nextGaussian()) * Stats.V_PARTICULE_EXPLOSION_SLOW);
		
		width = Math.abs((float) ((R.nextGaussian() * WIDTH))) + MIN_WIDTH;
		ttl = Math.abs((int) (R.nextGaussian() * 100)) + 15;
		return this;
	}
	
	private Explosion init(Enemy e) {
		x = (e.pos.x + (e.getWidth() * R.nextFloat()) );
		y = (e.pos.y + (e.getHeight() * R.nextFloat()) );

		speedY = (float) (((R.nextGaussian()) * Stats.V_PARTICULE_EXPLOSION) + e.getDirectionY() * R.nextFloat());
		speedX = (float) (((R.nextGaussian()) * Stats.V_PARTICULE_EXPLOSION) + e.getDirectionX() * R.nextFloat());
		
		width = Math.abs((float) ((R.nextGaussian() * WIDTH))) + MIN_WIDTH;
		ttl = Math.abs((int) (R.nextGaussian() * 100)) + 15;
		return this;
	}

	private static float tmp;
	public static void act(Array<Explosion> explosions, SpriteBatch batch) {
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
			p.x += p.speedX * EndlessMode.delta;
			p.y += p.speedY * EndlessMode.delta;
			if (p.ttl-- < 0) {
				POOL.free(p);
				explosions.removeValue(p, true);
			}
		}
		batch.setColor(AssetMan.WHITE);
	}

	public static void clear(Array<Explosion> explosions) {
		POOL.freeAll(explosions);
		explosions.clear();
	}
}
