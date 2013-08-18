package assets.particules;

import jeu.Endless;
import menu.CSG;
import assets.AssetMan;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class Particule {
	
	public static final float HAUTEUR = CSG.LARGEUR_ECRAN / 280;
	public float posX, posY, r, g, b, angle, vitesseAngle, vitesseY, vitesseX, temps;
	
	protected void flammes() {
		b = 0;
		r = 1;
		g = (float) ((Math.random() + .8) / 1.6);
		
		angle = g;
		vitesseAngle = g * 100000;
	}

	public void afficher(SpriteBatch batch) {
		batch.setColor(r, g, b, 1);
		batch.draw(getTexture(), posX, posY, 0, 0, getLargeur(), getHauteur(), 1, 1, angle);
		batch.setColor(Color.WHITE);
	}
	
	/**
	 * Par defaut retourne LARGEUR ECRAN / 280
	 * @return
	 */
	protected float getLargeur() {
		return HAUTEUR;
	}

	/**
	 * Par defaut retourne LARGEUR ECRAN / 280
	 * @return
	 */
	protected float getHauteur() {
		return HAUTEUR;
	}

	/**
	 * Par defaut retourne un carre blanc
	 * @return
	 */
	protected TextureRegion getTexture() {
		return AssetMan.debris;
	}

	public boolean mouvementEtVerif() {
		angle += vitesseAngle  * Endless.delta;
		
		if (Endless.maintenant > temps) return false;
		posX += vitesseX * Endless.delta;
		posY += vitesseY * Endless.delta;
		// Je pense qu'on peut se permettre de ne pas verifier si il est tjrs à l'écran vu son court temps de vie
		return true;
	}

	public abstract void free();
}
