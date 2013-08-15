package vaisseaux.armes;

import jeu.Endless;
import jeu.Physique;
import jeu.Stats;
import menu.CSG;
import assets.animation.AnimationTirTrois;
import assets.particules.ParticulesArmeDeBase;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.badlogic.gdx.utils.Pools;

/**
 * Arme de base qui fait une boule de feu
 * @author Julien
 *
 */
public class ArmeAvion extends Armes implements Poolable{
	
	// ** ** caracteristiques g�n�rales
	public static final int LARGEUR= CSG.LARGEUR_ECRAN / 15;
	public static final int DEMI_LARGEUR = LARGEUR/2;
	public static final int HAUTEUR = (int) (LARGEUR * 1.5);
	public static final int DEMI_HAUTEUR = HAUTEUR / 2; 
	public static final float CADENCETIR = .1f;
	private final int FORCE = 8;
	public static Pool<ArmeAvion> pool = Pools.get(ArmeAvion.class);
	private float tpsAnimation = 0;
	// ** ** particules
	public ParticulesArmeDeBase particleEffect;
	private float dirY = -1;
	
	/**
	 * ATTENTION ici le init s'occupe d'ajouter � la bonne liste
	 */
	public void init(float posX, float posY, float modifVitesse) {
		position.x = posX;
		position.y = posY;
		listeTirsDesEnnemis.add(this);
		dirY = -1 * modifVitesse;
	}

	@Override
	public void reset() {		dirY = -1;	}
	
	@Override
	public boolean testCollsionAdds() {
		return Physique.testCollisionAdds(position, LARGEUR, HAUTEUR);
	}
	
	@Override
	public boolean testCollisionVaisseau() {
		return Physique.pointDansVaisseau(position, LARGEUR, HAUTEUR);
	}

	@Override
	public void afficherSansParticules(SpriteBatch batch) {
		tpsAnimation += Endless.delta;
		batch.draw(AnimationTirTrois.getTexture(tpsAnimation), position.x, position.y,
		// CENTRE DE LA ROTATION EN X													// CENTRE DE LA ROTATION EN Y
		DEMI_LARGEUR, DEMI_HAUTEUR,
		// LARGEUR DU RECTANGLE AFFICHE		HAUTEUR DU RECTANGLE
		LARGEUR, HAUTEUR,
		//scaleX the scale of the rectangle around originX/originY in x ET Y
		1,0.5f,
		// L'ANGLE DE ROTATION
		90,
		//FLIP OU PAS
		false);
	}
	
	@Override
	public boolean mouvementEtVerif() {
		if (Physique.mouvementDeBaseDirY(dirY, position, Stats.VITESSE_MAX_ARME_AVION, HAUTEUR, LARGEUR)) return true;
		free();
		return false;
	}

	@Override
	public int getForce() {
		return FORCE;
	}

	@Override
	public int getLargeur() {
		return LARGEUR;
	}

	@Override
	public int getHauteur() {
		return HAUTEUR;
	}

	@Override
	public void free() {
		if(particleEffect != null) particleEffect.free();
		pool.free(this);
	}
}
