package assets.background;

import jeu.Endless;
import menu.CSG;
import assets.AssetMan;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.badlogic.gdx.utils.Pools;

public class Poussiere implements Poolable{
	
	public static int LARGEURMIN = CSG.LARGEUR_ECRAN/100;
	public static final int VITESSE = CSG.HAUTEUR_ECRAN / 90;
	public static Pool<Poussiere> pool = Pools.get(Poussiere.class);
	public float posX, posY, largeur;

	public void init(float x) {
		largeur = (float) (LARGEURMIN + (Math.random() * LARGEURMIN / 1.5));
		posX = x;
		posY = CSG.HAUTEUR_ECRAN + largeur;
	}

	@Override
	public void reset() {	}

	public void afficher(SpriteBatch batch) {
		batch.draw(AssetMan.poussiere, posX, posY, largeur, largeur);
	}

	/**
	 * @return true si il faut le virer
	 */
	public boolean mouvementEtVerif() {
		posY -= (VITESSE * largeur * Endless.delta); // Ne tient pas en compte la largeur pour corriger le bug bizarre des poussières qui restes bloquées dans le fond si le ralentit est activé
		if (posY < 1) {
			pool.free(this);
			return true;
		}
		return false;
	}
}

