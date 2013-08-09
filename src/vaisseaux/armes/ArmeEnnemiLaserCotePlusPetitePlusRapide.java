package vaisseaux.armes;

import jeu.Endless;
import jeu.Physique;
import jeu.Stats;
import menu.CSG;
import assets.animation.AnimationBouleBleu;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;
import com.badlogic.gdx.utils.Pool.Poolable;

/**
 * VOIR POUR LA TRANSLATION
 * @author Julien
 */
public class ArmeEnnemiLaserCotePlusPetitePlusRapide extends Armes implements Poolable{
	
	// ** ** caracteristiques g�n�rales
	public static final int LARGEUR= CSG.LARGEUR_ECRAN / 32;
	public static final int DEMI_LARGEUR = LARGEUR/2; 
	public static final float CADENCETIR = .1f;
	private static final int FORCE = 2;
	public static Pool<ArmeEnnemiLaserCotePlusPetitePlusRapide> pool = Pools.get(ArmeEnnemiLaserCotePlusPetitePlusRapide.class);
	private float tpsAnim = 0;
	// ** ** variable utilitaire
	private float angle;
	public Vector2 direction = new Vector2();
	
	/**
	 * Ca tir dans le bon angle
	 * @param centreX
	 * @param translationX
	 * @param centreY
	 * @param translationY
	 * @param angle
	 */
	// Appelee par l'ennmi qui la lance apr�s le pool.obtain()
	public void init(float centreX,float centreY, float angle, boolean gauche) {
		this.angle = angle;
		direction.x = -1;
		direction.y = 0;
		if (gauche)	direction.rotate(angle+90);
		else direction.rotate(angle-90);
		Armes.listeTirsDesEnnemis.add(this);
		position.x = centreX;
		position.y = centreY;
		position.x += 20 * direction.x;
		position.y += 20 * direction.y;
	}
	
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
	public void afficher(SpriteBatch batch) {
		tpsAnim += Endless.delta;
		batch.setColor(.99f, Endless.colorRapide, .3f, 1);
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
	public void afficherSansParticules(SpriteBatch batch) {
		tpsAnim += Endless.delta;
		batch.setColor(.99f, Endless.colorRapide, .3f, 1);
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
		if (Physique.mouvementDeBase(direction, position, Stats.VITESSE_MAX_ARME_BOULE_VERTE_RAPIDE, LARGEUR))	return true;
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
	public void initGraphismes() {	}
	
	@Override
	public void free() {		pool.free(this);	}
}
