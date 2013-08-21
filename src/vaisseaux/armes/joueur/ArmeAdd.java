package vaisseaux.armes.joueur;

import vaisseaux.armes.Armes;
import jeu.Physique;
import jeu.Stats;
import menu.CSG;
import assets.animation.AnimationTirAdd;
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

public class ArmeAdd extends ArmeJoueur implements Poolable{
	
	// ** ** caracteristiques g�n�rales
	public static final int LARGEUR= CSG.LARGEUR_ECRAN / 27, DEMI_LARGEUR = LARGEUR/2, HAUTEUR = LARGEUR * 2, DEMI_HAUTEUR = HAUTEUR / 2; 
	public static float CADENCETIR;
	private static final int FORCE = 5;
	public static Pool<ArmeAdd> pool = Pools.get(ArmeAdd.class);
	// ** ** animation
	private float angle;
	
	public static void determinerCadenceTir() {	CADENCETIR = 0.4f / CSG.profil.cadenceAdd;	}

	public void init(float x, float y, float angle) {
		position.x = x;
		position.y = y;
		direction.x = 0;
		direction.y = 1;
		direction.rotate(angle + 90);
		position.x += direction.x * 25;
		position.y += direction.y * 25;
		this.angle = angle;
		Armes.liste.add(this);
	}

	@Override
	public void reset() {			}

	@Override
	public void afficherSansParticules(SpriteBatch batch) {
		batch.draw(AnimationTirAdd.getTexture(), position.x, position.y,	DEMI_LARGEUR,DEMI_HAUTEUR, LARGEUR, HAUTEUR, 1.5f,0.5f, angle, false);
	}

	@Override
	public boolean mouvementEtVerif() {
		if (Physique.mouvementDeBase(direction, position, Stats.VITESSE_MAX_ARME_ADD, HAUTEUR, LARGEUR))		return true;
		free();
		return false;
	}
	
	@Override
	public int getForce() {		return FORCE;	}
	
	@Override
	public int getLargeur() {		return LARGEUR;	}

	@Override
	public int getHauteur() {		return HAUTEUR;	}

	@Override
	public void free() {
		pool.free(this);
	}

	@Override
	public boolean testCollsionAdds() {		return false;	}

	@Override
	public float getR() {
		if (numeroCouleur == 1) return 0.813f;
		return 0.207f;
	}

	@Override
	public float getG() {
		if (numeroCouleur == 1) return 0.988f;
		return 0.976f;
	}

	@Override
	public float getB() {
		if (numeroCouleur == 1) return 0.872f;
		return 0f;
	}
}

