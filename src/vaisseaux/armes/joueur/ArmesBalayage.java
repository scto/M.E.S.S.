package vaisseaux.armes.joueur;

import vaisseaux.armes.Armes;
import jeu.Endless;
import jeu.Physique;
import jeu.Stats;
import menu.CSG;
<<<<<<< HEAD
import assets.AssetMan;
import assets.animation.Anim;
import assets.particules.Particules;

=======
import assets.animation.AnimationTirBleu;
>>>>>>> parent of a593e8e... refact animation
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
	public float angle;
	public static float[] couleurs = {
		AssetMan.convertARGB(1, 0f, 0f, 136f/255f),
		AssetMan.convertARGB(1, 17f/255f, 66f/255f, 1f),
		AssetMan.convertARGB(1, 57f/255f, 127f/255f, 1f),
		AssetMan.convertARGB(1, 81f/255f, 173f/255f, 1f)};
	
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
	public void afficher(SpriteBatch batch) {
		tpsAnim += Endless.delta;
<<<<<<< HEAD
		Particules.ajoutArmeBalayage(this);
		batch.draw(Anim.tirBleu.getTexture(tpsAnim), position.x, position.y, DEMI_LARGEUR,DEMI_LARGEUR,	LARGEUR, LARGEUR, 1,1, angle, false);
=======
		batch.draw(AnimationTirBleu.getTexture(tpsAnim), position.x, position.y, DEMI_LARGEUR,DEMI_LARGEUR,	LARGEUR, LARGEUR, 1,1, angle, false);
>>>>>>> parent of a593e8e... refact animation
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
	public float getColor() {
		return couleurs[r.nextInt(couleurs.length)];
	}
}
