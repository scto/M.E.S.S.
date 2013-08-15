package vaisseaux.armes;

import jeu.Endless;
import jeu.Physique;
import jeu.Stats;
import menu.CSG;
import assets.animation.AnimationBouleBleu;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;
import com.badlogic.gdx.utils.Pool.Poolable;

/**
 * VOIR POUR LA TRANSLATION
 * @author Julien
 */
public class ArmeBouleTir extends Armes implements Poolable{
	
	// ** ** caracteristiques g�n�rales
	public static final int LARGEUR= CSG.LARGEUR_ECRAN / 26;
	public static final int DEMI_LARGEUR = LARGEUR/2; 
	private static final int FORCE = 2;
	public static Pool<ArmeBouleTir> pool = Pools.get(ArmeBouleTir.class);
	private float tpsAnim = 0;
	// ** ** variable utilitaire
	private float angle;
	
	@Override
	public void reset() {
	}

	@Override
	public boolean testCollisionVaisseau() {
		return Physique.pointDansVaisseau(position, LARGEUR);
	}
	
	@Override
	public boolean testCollsionAdds() {
		return Physique.testCollisionAdds(position, LARGEUR);
	}

	@Override
	public void afficherSansParticules(SpriteBatch batch) {
		tpsAnim += Endless.delta;
		batch.setColor(.99f, Endless.color, .3f, 1);
		batch.draw(AnimationBouleBleu.getTexture(tpsAnim), position.x, position.y,
		// CENTRE DE LA ROTATION EN X													// CENTRE DE LA ROTATION EN Y
		DEMI_LARGEUR,DEMI_LARGEUR,
		// LARGEUR DU RECTANGLE AFFICHE		HAUTEUR DU RECTANGLE
		LARGEUR, LARGEUR,
		//scaleX the scale of the rectangle around originX/originY in x ET Y
		1,1,
		// L'ANGLE DE ROTATION
		angle,
		//FLIP OU PAS
		false);
		batch.setColor(Color.WHITE);
	}
	@Override
	public boolean mouvementEtVerif() {
		if (Physique.mouvementDeBase(direction, position, Stats.VITESSE_MAX_ARME_BOULE_VERTE, LARGEUR))	return true;
		pool.free(this);
		return false;
	}

	@Override
	public int getForce() {		return FORCE;	}
	@Override
	public int getLargeur() {		return LARGEUR;	}
	@Override
	public int getHauteur() {		return LARGEUR;	}
	@Override
	public void free() {		pool.free(this);	}
}
