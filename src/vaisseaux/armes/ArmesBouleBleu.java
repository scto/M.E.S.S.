package vaisseaux.armes;

import jeu.Endless;
import jeu.Physique;
import jeu.Stats;
import menu.CSG;
import assets.animation.AnimationBouleBleu;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.badlogic.gdx.utils.Pools;

public class ArmesBouleBleu extends Armes implements Poolable{
	
	// ** ** caracteristiques g�n�rales
	public static final int LARGEUR= CSG.LARGEUR_ECRAN / 30;
	public static final int DEMI_LARGEUR = LARGEUR/2;
	public static final int HAUTEUR = LARGEUR;
	public static final int DEMI_HAUTEUR = HAUTEUR / 2; 
	public static final float CADENCETIR = .1f;
	private static final int FORCE = 2;
	public static Pool<ArmesBouleBleu> pool = Pools.get(ArmesBouleBleu.class);
	private float tpsAnim = 0;
	// ** ** variable utilitaire
	/**
	 * Ca tir dans le bon angle
	 * @param centreX
	 * @param translationX
	 * @param centreY
	 * @param translationY
	 * @param angle
	 */
//	public void init(float centreX, float centreY, float angle) {
//		direction.x = 1;
//		direction.y = 0;
//		direction.rotate(angle);
//		position.x = centreX;
//		position.y = centreY;
//		// d�calage du centre :
//		position.x += direction.x * 25;
//		position.y += direction.y * 25;
//
//		Armes.listeTirsDesEnnemis.add(this);
//	}
	
	@Override
	public void reset() {		tpsAnim = 0;	}
	
	@Override
	public boolean testCollisionVaisseau() {
		return Physique.pointDansVaisseau(position, LARGEUR, HAUTEUR);
	}
	
	@Override
	public boolean testCollsionAdds() {
		return Physique.testCollisionAdds(position, LARGEUR, HAUTEUR);
	}
	
	public ArmesBouleBleu() {
	}

	@Override
	public void afficher(SpriteBatch batch) {
		tpsAnim += Endless.delta;
		batch.draw(AnimationBouleBleu.getTexture(tpsAnim), position.x, position.y,
		// CENTRE DE LA ROTATION EN X													// CENTRE DE LA ROTATION EN Y
		DEMI_LARGEUR,DEMI_HAUTEUR,
		// LARGEUR DU RECTANGLE AFFICHE		HAUTEUR DU RECTANGLE
		LARGEUR, HAUTEUR,
		//scaleX the scale of the rectangle around originX/originY in x ET Y
		1,1,
		// L'ANGLE DE ROTATION
		angle,
		//FLIP OU PAS
		false);
	}
	@Override
	public void afficherSansParticules(SpriteBatch batch) {
		tpsAnim += Endless.delta;
		batch.draw(AnimationBouleBleu.getTexture(tpsAnim), position.x, position.y,
		// CENTRE DE LA ROTATION EN X													// CENTRE DE LA ROTATION EN Y
		DEMI_LARGEUR,DEMI_HAUTEUR,
		// LARGEUR DU RECTANGLE AFFICHE		HAUTEUR DU RECTANGLE
		LARGEUR, HAUTEUR,
		//scaleX the scale of the rectangle around originX/originY in x ET Y
		1,1,
		// L'ANGLE DE ROTATION
		angle,
		//FLIP OU PAS
		false);
	}

	@Override
	public boolean mouvementEtVerif() {
		if(Physique.mouvementDeBase(direction, position, Stats.VITESSE_MAX_ARME_BOULE_BLEU, HAUTEUR, LARGEUR)) return true;
		pool.free(this);
		return false;
	}
	
	@Override
	public int getForce() {
		return FORCE + CSG.profil.NvArmeBalayage;
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
		// TODO Auto-generated method stub
		
	}
	@Override
	public void free() {
		pool.free(this);
	}
	
	@Override
	public Vector2 getDirection(){
		return direction;
	}
}
