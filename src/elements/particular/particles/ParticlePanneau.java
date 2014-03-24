package elements.particular.particles;

import java.util.Random;

import jeu.CSG;
import jeu.Stats;
import assets.AssetMan;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;

public class ParticlePanneau {
	
	private float posX, posY, largeur;
	private final float color;
	public static final float LARGEUR = CSG.screenWidth / 80, DOUBLE_LARGEUR = LARGEUR * 2, TRIPLE_LARGEUR = LARGEUR * 3;
	private static final Random rand = new Random();
	public static Pool<ParticlePanneau> pool = new Pool<ParticlePanneau>(40) {
		@Override
		protected ParticlePanneau newObject() {
			return new ParticlePanneau();
		}
	};
	
	public ParticlePanneau() {
		color = AssetMan.convertARGB(1, rand.nextFloat()/8, (rand.nextFloat()+1)/3, 1);
	}
	
	public void init(Vector2 posParticule, Vector2 baseDirection) {
		posX = posParticule.x;
		posY = posParticule.y;
		largeur = LARGEUR;
	}

	public void display(SpriteBatch batch) {
		batch.setColor(color);
		batch.draw(AssetMan.debris, posX, posY, largeur, largeur);
		batch.setColor(Color.WHITE);
	}

	public boolean mouvementEtVerif() {
		largeur -= Stats.u;
		if (largeur < 1 || largeur < Stats.uSur8)
			return false;
		return true;
	}

	public static void clear(Array<ParticlePanneau> boutons) {
		pool.freeAll(boutons);
		pool.clear();
		boutons.clear();
	}
}
