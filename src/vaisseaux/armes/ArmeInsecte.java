package vaisseaux.armes;

import jeu.Physique;
import jeu.Stats;
import menu.CSG;
import assets.animation.Anim;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.badlogic.gdx.utils.Pools;

/**
 * Arme tirant en balyant l'�cran de gauche � droite, d'o� le nom
 * C'est celle qui est verte
 * @author Julien
 *
 */

public class ArmeInsecte extends Armes implements Poolable{
	
	// ** ** caracteristiques g�n�rales
	public static final int LARGEUR = CSG.LARGEUR_ECRAN / 23;
	public static final int DEMI_LARGEUR = LARGEUR/2;
	public static final int HAUTEUR = LARGEUR * 2;
	public static final int DEMI_HAUTEUR = HAUTEUR / 2; 
	public static final float CADENCETIR = 0.12f;
	private static final int FORCE = 4;
	public static Pool<ArmeInsecte> pool = Pools.get(ArmeInsecte.class);
	// ** ** animation
	private float tpsAnim = 0;

	@Override
	public void reset() {		tpsAnim = 0;	}

	@Override
	public void afficher(SpriteBatch batch) {
		batch.setColor(Color.ORANGE);
		batch.draw(Anim.tirFeu.getTexture(tpsAnim), position.x, position.y,	DEMI_LARGEUR,DEMI_HAUTEUR, LARGEUR, HAUTEUR, 1.5f,0.5f, angle, false);
		batch.setColor(Color.WHITE);
	}

	@Override
	public boolean mouvementEtVerif() {
		// 0 pour que l'effet ne disparaisse pas trop vite au lieu de HAUTEUR
		if (Physique.mouvementDeBase(direction, position, Stats.VITESSE_ARME_INSECTE, HAUTEUR, LARGEUR))		return true;
		free();
		return false;
	}


	

	
	@Override
	public int getForce() {			return FORCE;	}
	
	@Override
	public int getLargeur() {		return LARGEUR;	}

	@Override
	public int getHauteur() {		return HAUTEUR;	}

	@Override
	public void free() {
//		
		pool.free(this);
	}

}

