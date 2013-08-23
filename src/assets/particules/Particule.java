package assets.particules;

import java.util.Random;

import vaisseaux.armes.joueur.ArmeHantee;
import jeu.Endless;
import menu.CSG;
import assets.AssetMan;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class Particule {
	
	public static final float HAUTEUR = CSG.LARGEUR_ECRAN / 280;
	public float posX, posY, angle, vitesseAngle, temps;
	protected static float diminution = 0;
	protected static Random r = new Random();
	
	public void afficher(SpriteBatch batch) {
		batch.draw(getTexture(), posX, posY, 0, 0, getLargeur(), getHauteur(), 1, 1, angle);
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
		// Je pense qu'on peut se permettre de ne pas verifier si il est tjrs à l'écran vu son court temps de vie
		return true;
	}
	

	protected boolean trainee() {
		if (temps < 4) return false;
		
		diminution = temps * (Endless.delta15);
		temps -= diminution;
		diminution /= 2;

		posX += diminution;
		posY += diminution;
		
		return true;
	}

	public abstract void free();
}
