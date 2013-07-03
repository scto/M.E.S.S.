package vaisseaux;

import jeu.Endless;
import jeu.Physique;
import menu.CSG;
import assets.AssetMan;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.badlogic.gdx.utils.Pools;

public class Reste implements Poolable{
	
	public static int LARGEUR = CSG.LARGEUR_ECRAN/90;
	public static Pool<Reste> pool = Pools.get(Reste.class);
	public float posX, posY, vitesseX, vitesseY, temps;

	@Override
	public void reset() {	}

	public void afficher(SpriteBatch batch) {
		batch.draw(AssetMan.debris, posX, posY, LARGEUR, LARGEUR);
	}

	/**
	 * @return true si il faut le virer
	 */
	public boolean mouvementEtVerif() {
		if (Endless.maintenant > temps) return false;
		posX += vitesseX * Endless.delta;
		posY += vitesseY * Endless.delta;
		return Physique.toujoursAfficher(posX, posY, LARGEUR);
	}

	public void init(Vector2 position, Vector2 direction) {		
		posX = position.x;
		posY = position.y;
		vitesseX = (float) (direction.x * Math.random() * 512);
		vitesseY = (float) (direction.y * Math.random() * 512);
		temps = Endless.maintenant + (float) Math.random();// * 20;
	}
}

