package elements.particular.particles.individual.trhuster;

import java.util.Random;

import jeu.CSG;
import jeu.Stats;
import jeu.mode.EndlessMode;
import assets.AssetMan;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;

import elements.particular.Player;
import elements.particular.particles.Particles;

public class ThrusterParticle implements Poolable {
	
	private static final float WIDTH = CSG.screenWidth / 55, HALF_WIDTH = WIDTH / 2, OFFSET_X = Player.HALF_WIDTH - WIDTH;
	public static final Pool<ThrusterParticle> POOL = new Pool<ThrusterParticle>(Particles.MAX_THRUSTER,Particles.MAX_THRUSTER+6) {
		@Override
		protected ThrusterParticle newObject() {
			return new ThrusterParticle();
		}
	};
	
//	private int index;
	private float x, y, alpha;
	private final float vitesseX, vitesseY, red, diminish;
	private static final Random r = new Random();
	
	public ThrusterParticle() {
		vitesseY =  ((r.nextFloat()+.5f) * -Stats.THRUSTER) - CSG.QUATR_HEIGHT;
		vitesseX = (r.nextFloat()-.5f) * Stats.THRUSTER;
		red = r.nextFloat();
		diminish = 6f + (r.nextFloat() * 6f);
	}

	public ThrusterParticle init(Player v) {
		x = (Player.POS.x + OFFSET_X) + (r.nextFloat() * WIDTH);
		y = Player.POS.y - (HALF_WIDTH + (HALF_WIDTH * r.nextFloat()));
		alpha = 1;
//		Particles.nbFlammes++;
//		index = CSG.R.nextInt(PrecalculatedParticles.colorsThruster.length / 4);
		return this;
	}
	
	@Override
	public void reset() {	}
	
	public static void act(Array<ThrusterParticle> flammes, SpriteBatch batch) {
		if (EndlessMode.alternate) {
			for (final ThrusterParticle f : flammes) {
//				batch.setColor(PrecalculatedParticles.colorsThruster[f.index]);
				batch.setColor(f.alpha, f.alpha, 1, f.alpha);
				batch.draw(AssetMan.dust, f.x, f.y, WIDTH, WIDTH);
				f.alpha -= f.diminish * EndlessMode.delta;
				f.x += (f.vitesseX * EndlessMode.delta);
				f.y += (f.vitesseY * EndlessMode.delta);
				if (f.alpha < 0) {
//				if (++f.index >= PrecalculatedParticles.colorsThruster.length) {
					POOL.free(f);
					flammes.removeValue(f, true);
//					Particles.nbFlammes--;
				}
			}
		} else {
			for (final ThrusterParticle f : flammes) {
				batch.setColor(f.alpha, f.red, 1, f.alpha);
				batch.draw(AssetMan.dust, f.x, f.y, WIDTH, WIDTH);
			}
		}
		batch.setColor(AssetMan.WHITE);
	}

	public static void clear(Array<ThrusterParticle> flammes) {
		POOL.freeAll(flammes);
		flammes.clear();
	}
}
