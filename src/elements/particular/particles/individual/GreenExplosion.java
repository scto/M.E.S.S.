package elements.particular.particles.individual;

import jeu.CSG;
import jeu.EndlessMode;
import jeu.Stats;
import assets.AssetMan;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;

import elements.generic.Player;

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
	private final float color;
	
	public GreenExplosion() {
		color = AssetMan.convertARGB(1, CSG.R.nextFloat()/8, 1, (CSG.R.nextFloat() + .8f) / 1.6f);
	}
	
	@Override
	public void reset() {	}

	public static void add(int max, Array<GreenExplosion> explosionsGreens, float posX, float posY) {
		for (int i = -EndlessMode.fps; i < max; i++) {
			explosionsGreens.add(POOL.obtain().init(posX, posY));
		}
	}

	private GreenExplosion init(float x, float y) {
		this.x = (x + (Player.LARGEUR_ADD * CSG.R.nextFloat()) );
		this.y = (y + (Player.LARGEUR_ADD * CSG.R.nextFloat()) );
		
		speedY = (float) ((CSG.R.nextGaussian()) * Stats.V_PARTICULE_EXPLOSION_SLOW);
		speedX = (float) ((CSG.R.nextGaussian()) * Stats.V_PARTICULE_EXPLOSION_SLOW);
		
		width = Math.abs((float) ((CSG.R.nextGaussian() * WIDTH))) + MIN_WIDTH;
		ttl = Math.abs((int) (CSG.R.nextFloat() * 50)) + 15;
		return this;
	}
	
	private static float tmp;
	public static void act(Array<GreenExplosion> explosions, SpriteBatch batch) {
		for (final GreenExplosion p : explosions) {
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

	public static void clear(Array<GreenExplosion> explosions) {
		POOL.freeAll(explosions);
		explosions.clear();
	}

}
