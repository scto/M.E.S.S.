package elements.particular.particles.individual.explosions;

import jeu.CSG;
import jeu.Stats;
import jeu.mode.EndlessMode;
import assets.AssetMan;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;

import elements.generic.Player;
import elements.generic.enemies.Enemy;
import elements.generic.weapons.player.PlayerWeapon;
import elements.particular.particles.Particles;
import elements.particular.particles.individual.PrecalculatedParticles;

public class SparklesColorOverTime implements Poolable {

	private float x, y, speedX, speedY, angle;
	private int index;
	private static final float INITIAL_WIDTH = ((float) Stats.LARGEUR_DE_BASE / 4), INITIAL_HALF_WIDTH = INITIAL_WIDTH / 2;
	public static final float HEIGHT = Stats.U, HALF_HEIGHT = HEIGHT / 2;
	private float[] colors;
	private final int maxIndex = PrecalculatedParticles.widthsColorOverTime.length /2;
	private static final Vector2 tmpVector = new Vector2();

	public static final Pool<SparklesColorOverTime> POOL = new Pool<SparklesColorOverTime>() {
		@Override
		protected SparklesColorOverTime newObject() {
			return new SparklesColorOverTime();
		}
	};
	
	@Override
	public void reset() {	}
	
	public static void act(Array<SparklesColorOverTime> explosions, SpriteBatch batch) {
		if (EndlessMode.alternate && !EndlessMode.triggerStop) {
			for (SparklesColorOverTime e : explosions) {
				batch.setColor(e.colors[e.index]);
				batch.draw(AssetMan.dust, e.x, e.y,
						HALF_HEIGHT, Stats.uSur4,
						HEIGHT, Stats.uSur2,
						1f, 1f,
						e.angle);
				e.x += e.speedX * EndlessMode.delta;
				e.y += e.speedY * EndlessMode.delta;
				
				if (++e.index >= e.colors.length) {
					explosions.removeValue(e, true);
					POOL.free(e);
				}
			}
		} else {
			for (SparklesColorOverTime e : explosions) {
				batch.setColor(e.colors[e.index]);
				batch.draw(AssetMan.dust, e.x, e.y,
						HALF_HEIGHT, Stats.uSur4,
						HEIGHT, Stats.uSur2,
						1f, 1f,
						e.angle);
			}
		}
		batch.setColor(AssetMan.WHITE);
	}

	public SparklesColorOverTime init(float x, float y, float[] colors, Enemy e) {
		this.x = x - INITIAL_HALF_WIDTH;
		this.y = y - INITIAL_HALF_WIDTH;
		this.colors = colors;
		index = CSG.R.nextInt(maxIndex);
		speedY = (float) (((CSG.R.nextGaussian()) * Stats.V_PARTICULE_EXPLOSION_SLOW) + e.getDirectionY() * CSG.R.nextFloat()) * 3.5f;
		speedX = (float) (((CSG.R.nextGaussian()) * Stats.V_PARTICULE_EXPLOSION_SLOW) + e.getDirectionX() * CSG.R.nextFloat()) * 3.5f;
		tmpVector.x = speedX;
		tmpVector.y = speedY;
		angle = tmpVector.angle();
		return this;
	}
	
	private SparklesColorOverTime init(float x, float y, float[] colors, PlayerWeapon p) {
		this.x = x - INITIAL_HALF_WIDTH;
		this.y = y - INITIAL_HALF_WIDTH;
		this.colors = colors;
		index = CSG.R.nextInt(maxIndex);
		tmpVector.x = -p.dir.x;
		tmpVector.y = -p.dir.y;
		tmpVector.rotate((float) (CSG.R.nextGaussian() * 15));
		angle = tmpVector.angle();
		tmpVector.nor();
		tmpVector.scl(Stats.U270 / 2);
		speedX = tmpVector.x;
		speedY = tmpVector.y;
		return this;
	}

	public static void clear(Array<SparklesColorOverTime> explosions) {
		POOL.freeAll(explosions);
		explosions.clear();
	}

	public static void add(Array<SparklesColorOverTime> explosionColorOverTime, Enemy e, float[] colors) {
		for (int i = -EndlessMode.fps; i < EndlessMode.fps; i++) {
			explosionColorOverTime.add(POOL.obtain().init(e.pos.x + e.getHalfWidth(), e.pos.y + e.getHalfHeight(), colors, e));
		}
	}
	
	public static void bulletImpact(Array<SparklesColorOverTime> explosionColorOverTime, PlayerWeapon p, float[] colors) {
		explosionColorOverTime.add(
				POOL.obtain().init(p.pos.x + p.getHalfWidth(), p.pos.y + p.getHalfHeight(), colors, p)
				);
	}


	public static void blow(PlayerWeapon a, Array<SparklesColorOverTime> explosionColorOverTime) {
		for (SparklesColorOverTime e : explosionColorOverTime) {
			a.dir.x = (e.x) - Player.xCenter;
			a.dir.y = (e.y) - Player.yCenter;
			a.dir.nor();
			a.dir.scl(Explosion.BOMB_SCALE);
			e.speedX += a.dir.x;
			e.speedY += a.dir.y;
			tmpVector.x = e.speedX;
			tmpVector.y = e.speedY;
			e.angle = tmpVector.angle();
		}
	}

	public static void add(float x, float y, float angle, float[] colors) {
		final SparklesColorOverTime p = POOL.obtain();
		p.x = x;
		p.y = y;
		p.angle = angle;
		p.speedX = 0;
		p.speedY = 0;
		p.colors = colors;
		p.index = 0;
		Particles.COLOR_OVER_TIME.add(p);
	}

	public static void add(float x, float y, float angle, float[] colors, float speed) {
		final SparklesColorOverTime p = POOL.obtain();
		p.x = x;
		p.y = y;
		p.angle = angle;
		tmpVector.x = speed;
		tmpVector.y = 0;
		tmpVector.rotate(angle);
		p.speedX = tmpVector.x;
		p.speedY = tmpVector.y;
		p.colors = colors;
		p.index = 0;
		Particles.COLOR_OVER_TIME.add(p);
	}

}
