package elements.particular.particles.individual;

import java.util.Random;

import jeu.CSG;
import jeu.EndlessMode;
import jeu.Stats;
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
	private final float vitesseX, vitesseY, red, green, tps;//, angle;
	private static final Random r = new Random();
	
	public ThrusterParticle() {
//		angle = r.nextFloat() * 360;
		vitesseY =  ((r.nextFloat()+.5f) * -Stats.THRUSTER) - CSG.QUATR_HAUTEUR;
		vitesseX = (r.nextFloat()-.5f) * Stats.THRUSTER;
		red = r.nextFloat();
		green = r.nextFloat();
		tps = 3f + (r.nextFloat() * 10f);
	}

	public void init(Player v) {
		x = (Player.POS.x + OFFSET_X) + (r.nextFloat() * LARGEUR);
		y = Player.POS.y - (DEMI_LARGEUR + (DEMI_LARGEUR * r.nextFloat()));
		alpha = 1;
		Particles.nbFlammes++;
	}
	
	@Override
	public void reset() {	}
	
	public static void act(Array<ThrusterParticle> flammes, SpriteBatch batch) {
		for (final ThrusterParticle f : flammes) {
			batch.setColor(f.red, f.green, 1, f.alpha);
			batch.draw(AssetMan.dust, f.x, f.y, LARGEUR, LARGEUR);
//			batch.draw(AssetMan.debris, f.posX, f.posY, DEMI_LARGEUR, DEMI_LARGEUR, LARGEUR, LARGEUR, 1, 1, f.angle);
			f.alpha -= f.tps * EndlessMode.delta;
			f.x += (f.vitesseX * EndlessMode.delta);
			f.y += (f.vitesseY * EndlessMode.delta);
			if (f.alpha < 0) {
				POOL.free(f);
				flammes.removeValue(f, true);
				Particles.nbFlammes--;
			}
		}
		batch.setColor(AssetMan.WHITE);
	}

	public static void clear(Array<ThrusterParticle> flammes) {
		POOL.freeAll(flammes);
		flammes.clear();
	}
}
