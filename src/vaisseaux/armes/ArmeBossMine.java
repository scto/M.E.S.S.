package vaisseaux.armes;

import jeu.Physique;
import jeu.Stats;
import menu.CSG;
import assets.animation.AnimationBouleBleu;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.badlogic.gdx.utils.Pools;

/**
 * Arme de base qui fait une boule de feu
 * @author Julien
 *
 */
public class ArmeBossMine extends Armes implements Poolable{
	
	// ** ** caracteristiques g�n�rales
	public static final int LARGEUR= CSG.LARGEUR_ECRAN / 30;
	public static final int DEMI_LARGEUR = LARGEUR/2;
	public static final int HAUTEUR = (int) (LARGEUR * 1.5);
	public static final int DEMI_HAUTEUR = HAUTEUR / 2; 
	public static final float CADENCETIR = .10f;
	public static Pool<ArmeBossMine> pool = Pools.get(ArmeBossMine.class);

	@Override
	public void reset() {
		direction.x = 0;
		direction.y = -1;
	}
	
	@Override
	public boolean testCollsionAdds() {
		return Physique.testCollisionAdds(position, LARGEUR, HAUTEUR);
	}
	
	@Override
	public boolean testCollisionVaisseau() {
		return Physique.pointDansVaisseau(position, LARGEUR, HAUTEUR);
	}
	
	/**
	 * Cr�e l'objet, il faut appeler la m�thode init apr�s
	 */
	public ArmeBossMine() {
		super();
	}

	@Override
	public void afficher(SpriteBatch batch){
//		particleEffect.setPosition(position.x + DEMI_LARGEUR, position.y + DEMI_HAUTEUR);
//		particleEffect.draw(batch, Endless.delta);
		batch.draw(AnimationBouleBleu.getTexture(1), position.x, position.y, DEMI_LARGEUR, DEMI_HAUTEUR, LARGEUR, HAUTEUR, 	1,0.5f,	angle, false);
	}

	@Override
	public void afficherSansParticules(SpriteBatch batch) {
		batch.draw(AnimationBouleBleu.getTexture(1), position.x, position.y,
		// CENTRE DE LA ROTATION EN X													// CENTRE DE LA ROTATION EN Y
		DEMI_LARGEUR, DEMI_HAUTEUR,
		// LARGEUR DU RECTANGLE AFFICHE		HAUTEUR DU RECTANGLE
		LARGEUR, HAUTEUR,
		//scaleX the scale of the rectangle around originX/originY in x ET Y
		1,0.5f,
		// L'ANGLE DE ROTATION
		angle,
		//FLIP OU PAS
		false);
	}
	
	@Override
	public boolean mouvementEtVerif() {
		if (Physique.mouvementDeBase(direction, position, Stats.VITESSE_ARME_BOSS_QUAD, HAUTEUR, LARGEUR)) return true;
		pool.free(this);
		return false;
	}

	@Override
	public int getForce() {
		return 0;
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

	}
	@Override
	public void free() {
		pool.free(this);
	}
}
