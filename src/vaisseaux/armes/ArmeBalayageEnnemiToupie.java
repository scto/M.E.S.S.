package vaisseaux.armes;

import jeu.Endless;
import jeu.Physique;
import jeu.Stats;
import menu.CSG;
import assets.animation.AnimationTirFeu;
import assets.particules.ParticulesArmeTraitVert;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.badlogic.gdx.utils.Pools;

/**
 * Arme tirant en balyant l'�cran de gauche � droite, d'o� le nom
 * C'est celle qui est verte
 * @author Julien
 *
 */

public class ArmeBalayageEnnemiToupie extends Armes implements Poolable{
	
	// ** ** caracteristiques g�n�rales
	public static final int LARGEUR= CSG.LARGEUR_ECRAN / 15;
	public static final int DEMI_LARGEUR = LARGEUR/2;
	private static final int HAUTEUR = LARGEUR/2;
	public static final int DEMI_HAUTEUR = HAUTEUR / 2; 
	public static final float CADENCETIR = .11f;
	private static final int FORCE = 2;
	public static Pool<ArmeBalayageEnnemiToupie> pool = Pools.get(ArmeBalayageEnnemiToupie.class);
	// ** ** animation
	private float tpsAnim = 0;
	// ** ** variable utilitaire
	private ParticulesArmeTraitVert particleEffect;

	@Override
	public void reset() {
		tpsAnim = 0;
	}

	@Override
	public void afficher(SpriteBatch batch) {
		particleEffect.setPosition(position.x + DEMI_LARGEUR, position.y + DEMI_HAUTEUR);
		particleEffect.draw(batch, Endless.delta);
	}

	@Override
	public void afficherSansParticules(SpriteBatch batch) {
		tpsAnim += Endless.delta;
		batch.setColor(Color.GREEN);
		batch.draw(AnimationTirFeu.getTexture(tpsAnim), position.x, position.y, DEMI_LARGEUR,DEMI_HAUTEUR, LARGEUR, HAUTEUR, 1,1, angle+90, false);
		batch.setColor(Color.WHITE);
	}

	@Override
	public boolean mouvementEtVerif() {
		// 0 pour que l'effet ne disparaisse pas trop vite au lieu de HAUTEUR
		if(Physique.mouvementDeBase(direction, position, Stats.VITESSE_MAX_ARME_BALAYAGE_ENNEMIS_TOUPIE, LARGEUR, HAUTEUR))		return true;
		free();
		return false;
	}

	@Override
	public boolean testCollisionVaisseau() {
		return Physique.pointDansVaisseau(position, LARGEUR, HAUTEUR);
	}
	
	@Override
	public boolean testCollsionAdds() {
		return Physique.testCollisionAdds(position, LARGEUR, HAUTEUR);
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
			particleEffect = ParticulesArmeTraitVert.pool.obtain();
			particleEffect.start();
		}
	}

	@Override
	public void free() {
		if (particleEffect != null) particleEffect.free();
		pool.free(this);
	}
}