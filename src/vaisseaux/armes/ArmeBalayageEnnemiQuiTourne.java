package vaisseaux.armes;

import jeu.Endless;
import jeu.Physique;
import jeu.Stats;
import menu.CSG;
import assets.animation.AnimationBouleBleu;
import assets.particules.ParticulesArmeBalleBleuStatic;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

/**
 * Arme tirant en balyant l'�cran de gauche � droite, d'o� le nom
 * @author Julien
 *
 */

public class ArmeBalayageEnnemiQuiTourne extends Armes {
	
	// ** ** caracteristiques g�n�rales
	public static final int LARGEUR= CSG.LARGEUR_ECRAN / 30;
	public static final int DEMI_LARGEUR = LARGEUR/2;
	public static final float CADENCETIR = .1f;
	public static final int FORCE = 2;
	public static Pool<ArmeBalayageEnnemiQuiTourne> pool = Pools.get(ArmeBalayageEnnemiQuiTourne.class);
	// ** ** animation
	private float tpsAnim = 0;
	private ParticulesArmeBalleBleuStatic particleEffect;

	@Override
	public void reset() {
		tpsAnim = 0;
	}

	@Override
	public void afficher(SpriteBatch batch) {
		particleEffect.setPosition(position.x + DEMI_LARGEUR, position.y + DEMI_LARGEUR);
		particleEffect.draw(batch, Endless.delta);
	}

	@Override
	public void afficherSansParticules(SpriteBatch batch) {
		tpsAnim += Endless.delta;
		batch.setColor(Color.ORANGE);
		batch.draw(AnimationBouleBleu.getTexture(tpsAnim), position.x, position.y, DEMI_LARGEUR, DEMI_LARGEUR, LARGEUR, LARGEUR, 1, 1, angle,	false);
		batch.setColor(Color.WHITE);
	}

	
	@Override
	public boolean testCollisionVaisseau() {
		return Physique.pointDansVaisseau(position, LARGEUR);
	}
	
	@Override
	public boolean testCollsionAdds() {
		return Physique.testCollisionAdds(position, LARGEUR, LARGEUR);
	}
	
	@Override
	public boolean mouvementEtVerif() {
		if (Physique.mouvementDeBase(direction, position, Stats.VITESSE_MAX_ARME_BALAYAGE_ENNEMI_QUI_TOURNE, LARGEUR) == false){
			free();
			return false;
		}
		return true;
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
		return LARGEUR;
	}

	@Override
	public void initGraphismes() {
		if (CSG.profil.particules) {
			particleEffect = ParticulesArmeBalleBleuStatic.pool.obtain();
			particleEffect.start();
		}
	}

	@Override
	public void free() {
		if (particleEffect != null) particleEffect.free();
		pool.free(this);
	}

}
