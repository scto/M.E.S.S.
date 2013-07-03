package vaisseaux.armes;

import jeu.Physique;
import jeu.Stats;
import menu.CSG;
import assets.AssetMan;
import assets.particules.ParticulesArmeBalayage;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.badlogic.gdx.utils.Pools;

/**
 * Arme de base qui fait une boule de feu
 * @author Julien
 *
 */
public class ArmeBouleEnergie extends Armes implements Poolable{
	
	// ** ** caracteristiques g�n�rales
	public static final int LARGEUR= CSG.LARGEUR_ECRAN / 23;
	public static final int DEMI_LARGEUR = LARGEUR/2;
	public static final int HAUTEUR = LARGEUR + DEMI_LARGEUR;
	public static final int DEMI_HAUTEUR = HAUTEUR / 2; 
	public static final float CADENCETIR = 1.2f;
	private final int FORCE = 4;
	public static Pool<ArmeBouleEnergie> pool = Pools.get(ArmeBouleEnergie.class);
	public ParticulesArmeBalayage particleEffect;
	
	@Override
	public void reset() {	}
	
	@Override
	public boolean testCollisionVaisseau() {
		return Physique.pointDansVaisseau(position, LARGEUR, HAUTEUR);
	}
	
	@Override
	public boolean testCollsionAdds() {
		return Physique.testCollisionAdds(position, LARGEUR, HAUTEUR);
	}

	@Override
	public void afficher(SpriteBatch batch){
		batch.draw(AssetMan.boulenergiebleu , position.x, position.y, DEMI_LARGEUR,DEMI_HAUTEUR,
		// LARGEUR DU RECTANGLE AFFICHE		HAUTEUR DU RECTANGLE
		LARGEUR, HAUTEUR,
		//scaleX the scale of the rectangle around originX/originY in x ET Y
		1,1,
		// L'ANGLE DE ROTATION
		angle ,
		//FLIP OU PAS
		false);
	}

	@Override
	public void afficherSansParticules(SpriteBatch batch) {
		batch.draw(AssetMan.boulenergiebleu , position.x, position.y, DEMI_LARGEUR,DEMI_HAUTEUR,
		// LARGEUR DU RECTANGLE AFFICHE		HAUTEUR DU RECTANGLE
		LARGEUR, HAUTEUR,
		//scaleX the scale of the rectangle around originX/originY in x ET Y
		1,1,
		// L'ANGLE DE ROTATION
		angle ,
		//FLIP OU PAS
		false);
	}
	
	@Override
	public boolean mouvementEtVerif() {
		if (Physique.mouvementDeBase(direction, position, Stats.VITESSE_MAX_ARME_RAISIN, HAUTEUR, LARGEUR)) return true;
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
	public void initGraphismes() {
		if (CSG.profil.particules) {
			particleEffect = ParticulesArmeBalayage.pool.obtain();
			particleEffect.start();
		}
	}
	@Override
	public void free() {
		if (particleEffect != null) particleEffect.free();
		pool.free(this);
	}
}
