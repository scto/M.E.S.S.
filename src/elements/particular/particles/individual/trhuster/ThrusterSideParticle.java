package elements.particular.particles.individual.trhuster;

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

public class ThrusterSideParticle implements Poolable {
	
	private static final float LARGEUR = CSG.screenWidth / 65, DEMI_LARGEUR = LARGEUR / 2, OFFSET_X = Player.DEMI_LARGEUR - LARGEUR;
	public static final Pool<ThrusterSideParticle> POOL = new Pool<ThrusterSideParticle>(Particles.MAX_THRUSTER,Particles.MAX_THRUSTER+6) {
		@Override
		protected ThrusterSideParticle newObject() {
			return new ThrusterSideParticle();
		}
	};
	private boolean toLeft;
	private float alpha, x, y;
	private final float vitesseX, vitesseY, red, green, tps;
	
	public ThrusterSideParticle() {
		vitesseX =  ((CSG.R.nextFloat()+.5f) * -Stats.THRUSTER) - CSG.QUATR_HAUTEUR;
		vitesseY = (((CSG.R.nextFloat()-.5f)/2)-.15f) * Stats.THRUSTER;
		red = CSG.R.nextFloat();
		green = CSG.R.nextFloat();
		tps = 3f + (CSG.R.nextFloat() * 10f);
	}

	public void init(float posX, float posY, boolean toLeft) {
		x = (Player.POS.x + OFFSET_X) + (CSG.R.nextFloat() * LARGEUR);
		y = Player.POS.y - (DEMI_LARGEUR + (DEMI_LARGEUR * CSG.R.nextFloat()));
		alpha = 1;
		this.toLeft = toLeft;
		Particles.nbFlammes++;
	}
	
	@Override
	public void reset() {	}
	
	public static void act(Array<ThrusterSideParticle> flammes, SpriteBatch batch) {
		for (final ThrusterSideParticle f : flammes) {
			batch.setColor(f.red, f.green, 1, f.alpha);
			batch.draw(AssetMan.dust, f.x, f.y, LARGEUR, LARGEUR);
//			batch.draw(AssetMan.debris, f.posX, f.posY, DEMI_LARGEUR, DEMI_LARGEUR, LARGEUR, LARGEUR, 1, 1, f.angle);
			f.alpha -= f.tps * EndlessMode.delta;
			if (f.toLeft)
				f.x -= (f.vitesseX * EndlessMode.delta);
			else 
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

	public static void clear(Array<ThrusterSideParticle> flammes) {
		POOL.freeAll(flammes);
		flammes.clear();
	}
}
