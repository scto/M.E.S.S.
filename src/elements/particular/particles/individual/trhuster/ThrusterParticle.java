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

import elements.generic.Player;
import elements.particular.particles.Particles;

public class ThrusterParticle implements Poolable {
	
	private static final float LARGEUR = CSG.screenWidth / 55, DEMI_LARGEUR = LARGEUR / 2, OFFSET_X = Player.DEMI_LARGEUR - LARGEUR;
	public static final Pool<ThrusterParticle> POOL = new Pool<ThrusterParticle>(Particles.MAX_THRUSTER,Particles.MAX_THRUSTER+6) {
		@Override
		protected ThrusterParticle newObject() {
			return new ThrusterParticle();
		}
	};
	private float alpha, x, y;
	private final float vitesseX, vitesseY, red, diminish;
	private static final Random r = new Random();
	
	public ThrusterParticle() {
		vitesseY =  ((r.nextFloat()+.5f) * -Stats.THRUSTER) - CSG.QUATR_HAUTEUR;
		vitesseX = (r.nextFloat()-.5f) * Stats.THRUSTER;
		red = r.nextFloat();
		diminish = 6f + (r.nextFloat() * 6f);
	}

	public ThrusterParticle init(Player v) {
		x = (Player.POS.x + OFFSET_X) + (r.nextFloat() * LARGEUR);
		y = Player.POS.y - (DEMI_LARGEUR + (DEMI_LARGEUR * r.nextFloat()));
		alpha = 1;
		Particles.nbFlammes++;
		return this;
	}
	
	@Override
	public void reset() {	}
	
	public static void act(Array<ThrusterParticle> flammes, SpriteBatch batch) {
		if (EndlessMode.alternate) {
			for (final ThrusterParticle f : flammes) {
				batch.setColor(f.alpha, f.alpha, 1, f.alpha);
				batch.draw(AssetMan.dust, f.x, f.y, LARGEUR, LARGEUR);
				f.alpha -= f.diminish * EndlessMode.delta;
				f.x += (f.vitesseX * EndlessMode.delta);
				f.y += (f.vitesseY * EndlessMode.delta);
				if (f.alpha < 0) {
					POOL.free(f);
					flammes.removeValue(f, true);
					Particles.nbFlammes--;
				}
			}
		} else {
			for (final ThrusterParticle f : flammes) {
				batch.setColor(f.alpha, f.red, 1, f.alpha);
				batch.draw(AssetMan.dust, f.x, f.y, LARGEUR, LARGEUR);
			}
		}
		batch.setColor(AssetMan.WHITE);
	}

	public static void clear(Array<ThrusterParticle> flammes) {
		POOL.freeAll(flammes);
		flammes.clear();
	}
}
