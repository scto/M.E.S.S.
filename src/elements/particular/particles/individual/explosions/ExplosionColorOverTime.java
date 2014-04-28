package elements.particular.particles.individual.explosions;

import jeu.CSG;
import jeu.EndlessMode;
import jeu.Stats;
import assets.AssetMan;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;

import elements.generic.Player;
import elements.generic.enemies.Enemy;
import elements.generic.weapons.player.PlayerWeapon;
import elements.particular.particles.individual.PrecalculatedParticlesLong;

public class ExplosionColorOverTime implements Poolable {

	private float x, y, speedX, speedY, angle;
	private int index;
	private static final float INITIAL_WIDTH = ((float) Stats.LARGEUR_DE_BASE / 4), INITIAL_HALF_WIDTH = INITIAL_WIDTH / 2;
	private float[] colors;
	private final int maxIndex = PrecalculatedParticlesLong.widths.length /2;

	public static final Pool<ExplosionColorOverTime> POOL = new Pool<ExplosionColorOverTime>() {
		@Override
		protected ExplosionColorOverTime newObject() {
			return new ExplosionColorOverTime();
		}
	};
	
	@Override
	public void reset() {	}
	
	public static void act(Array<ExplosionColorOverTime> explosions, SpriteBatch batch) {
		if (EndlessMode.alternate && !EndlessMode.triggerStop) {
			for (ExplosionColorOverTime e : explosions) {
				batch.setColor(e.colors[e.index]);
				batch.draw(AssetMan.dust, e.x, e.y,
						PrecalculatedParticlesLong.widths[e.index], PrecalculatedParticlesLong.halfWidths[e.index],
						PrecalculatedParticlesLong.widths2[e.index], PrecalculatedParticlesLong.widths[e.index],
						1f, 1f,
						e.angle);
				e.x -= PrecalculatedParticlesLong.halfWidths[e.index];
				e.y -= PrecalculatedParticlesLong.halfWidths[e.index];
				e.x += e.speedX * EndlessMode.delta;
				e.y += e.speedY * EndlessMode.delta;
				
				e.speedX /= EndlessMode.UnPlusDelta;
				e.speedY /= EndlessMode.UnPlusDelta;
				if (++e.index >= PrecalculatedParticlesLong.widths.length) {
					explosions.removeValue(e, true);
					POOL.free(e);
				}
			}
		} else {
			for (ExplosionColorOverTime e : explosions) {
				batch.setColor(e.colors[e.index]);
				batch.draw(AssetMan.dust, e.x, e.y,
						PrecalculatedParticlesLong.widths[e.index], PrecalculatedParticlesLong.halfWidths[e.index],
						PrecalculatedParticlesLong.widths2[e.index], PrecalculatedParticlesLong.widths[e.index],
						1f, 1f,
						e.angle);
			}
		}
		batch.setColor(AssetMan.WHITE);
	}

	private static final Vector2 tmpVector = new Vector2();
	public ExplosionColorOverTime init(float x, float y, float[] colors, Enemy e) {
		this.x = x - INITIAL_HALF_WIDTH;
		this.y = y - INITIAL_HALF_WIDTH;
		this.colors = colors;
		index = CSG.R.nextInt(maxIndex);
		speedY = (float) (((CSG.R.nextGaussian()) * Stats.V_PARTICULE_EXPLOSION_SLOW) + e.getDirectionY() * CSG.R.nextFloat()) * 1.5f;
		speedX = (float) (((CSG.R.nextGaussian()) * Stats.V_PARTICULE_EXPLOSION_SLOW) + e.getDirectionX() * CSG.R.nextFloat()) * 1.5f;
		tmpVector.x = speedX;
		tmpVector.y = speedY;
		angle = tmpVector.angle();
		return this;
	}

	public static void clear(Array<ExplosionColorOverTime> explosions) {
		POOL.freeAll(explosions);
		explosions.clear();
	}

	public static void add(Array<ExplosionColorOverTime> explosionColorOverTime, Enemy e, float[] colors) {
		for (int i = -EndlessMode.fps; i < EndlessMode.fps; i++) {
			explosionColorOverTime.add(POOL.obtain().init(e.pos.x + e.getHalfWidth(), e.pos.y + e.getHalfHeight(), colors, e));
		}
	}

	public static void blow(PlayerWeapon a, Array<ExplosionColorOverTime> explosionColorOverTime) {
		for (ExplosionColorOverTime e : explosionColorOverTime) {
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

}
