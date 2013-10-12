package assets.particules;

import java.util.Random;

import jeu.EndlessMode;
import menu.CSG;
import assets.AssetMan;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class Particule {
	
	public static final float HAUTEUR = CSG.LARGEUR_ECRAN / 280;
	public float posX, posY, temps;
	protected static float diminution = 0;
	protected static Random r = new Random();
	
	/**
	 * Par defaut retourne LARGEUR ECRAN / 280
	 * @return
	 */
	protected float getLargeur() {
		return HAUTEUR;
	}
	
	public abstract void display(SpriteBatch batch);
	

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



	protected boolean trainee() {
		if (temps < 4) return false;
		
		diminution = temps * (EndlessMode.delta15);
		temps -= diminution;
		diminution /= 2;

		posX += diminution;
		posY += diminution;
		
		return true;
	}

	public abstract void free();

	public abstract boolean mouvementEtVerif();
}
