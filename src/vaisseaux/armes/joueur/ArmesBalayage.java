package vaisseaux.armes.joueur;

import vaisseaux.armes.Armes;
import jeu.Endless;
import jeu.Physique;
import jeu.Stats;
import menu.CSG;
import assets.animation.AnimationTirBleu;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.badlogic.gdx.utils.Pools;

/**
 * Arme tirant en balyant l'�cran de gauche � droite, d'o� le nom
 * @author Julien
 *
 */

public class ArmesBalayage extends ArmeJoueur implements Poolable{
	
	// ** ** caracteristiques g�n�rales
	public static int LARGEUR;
	public static int DEMI_LARGEUR;
	public static final float CADENCETIR = .08f;
	private static final int FORCE = 2;
	public static Pool<ArmesBalayage> pool = Pools.get(ArmesBalayage.class);
	// ** ** animation
	private float tpsAnim = 0;
	// ** ** variable utilitaire
	private float angle;
	
	public static void updateDimensions() {
		LARGEUR = CSG.LARGEUR_ECRAN / 30 + (CSG.LARGEUR_ECRAN/100 * CSG.profil.NvArmeBalayage);
		DEMI_LARGEUR = LARGEUR/2;
	}

	public void init(float posX, float posY, float x, float y, float angle) {
		position.x = posX;
		position.y = posY;
		direction.x = x;
		direction.y = y;
		this.angle = angle;
	}

	@Override
	public void reset() {
		tpsAnim = 0;
	}

	@Override
	public void afficherSansParticules(SpriteBatch batch) {
		tpsAnim += Endless.delta;
		batch.draw(AnimationTirBleu.getTexture(tpsAnim), position.x, position.y, DEMI_LARGEUR,DEMI_LARGEUR,	LARGEUR, LARGEUR, 1,1, angle, false);
	}

	@Override
	public boolean mouvementEtVerif() {
		// 0 pour que l'effet ne disparaisse pas trop vite au lieu de LARGEUR
		if (Physique.mouvementDeBase(direction, position, Stats.VITESSE_MAX_ARME_BALAYAGE, 0, LARGEUR)) return true;
		free();
		return false;
	}
	
	@Override
	public int getForce() {		return FORCE + CSG.profil.NvArmeBalayage;	}
	
	@Override
	public int getLargeur() {		return LARGEUR;	}

	@Override
	public int getHauteur() {		return LARGEUR;	}

	@Override
	public void free() {
		pool.free(this);
	}

	@Override
	public boolean testCollsionAdds() {		return false;	}

	@Override
	public float getR() {
		if (numeroCouleur == 1) return 0.913f;
		return 0.223f;
	}

	@Override
	public float getG() {
		if (numeroCouleur == 1) return 1.000f;
		return 0.500f;
	}

	@Override
	public float getB() {
		if (numeroCouleur == 1) return 1.000f;
		return 1.000f;
	}
}
