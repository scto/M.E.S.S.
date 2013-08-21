package vaisseaux.armes.joueur;

import jeu.Endless;
import jeu.Physique;
import jeu.Stats;
import menu.CSG;
import assets.animation.Anim;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.badlogic.gdx.utils.Pools;

/**
 * Arme de base qui fait une boule de feu
 * @author Julien
 *
 */
public class ArmeHantee extends ArmeJoueur implements Poolable{
	
	// ** ** caracteristiques g�n�rales
	public static int LARGEUR;
	public static int DEMI_LARGEUR;
	public static final float CADENCETIR = .05f;
	private final int FORCE = 2;
	public static Pool<ArmeHantee> pool = Pools.get(ArmeHantee.class);
	private float tpsAnimation = 0;
	private static boolean alterner = false;
	
	public static void updateDimensions() {
		LARGEUR = CSG.LARGEUR_ECRAN / 30 + (CSG.LARGEUR_ECRAN/100 * CSG.profil.NvArmeHantee);
		DEMI_LARGEUR = LARGEUR/2;
	}
	
	public void init(float posX, float posY) {
		position.x = posX;
		position.y = posY;
		liste.add(this);
		direction.x = 0;
		direction.y = 1;
		tpsAnimation = 0;
	}

	@Override
	public void reset() {	}

	@Override
	public void afficherSansParticules(SpriteBatch batch) {
		batch.draw(Anim.tirTrois.getTexture(tpsAnimation) , position.x, position.y, LARGEUR, LARGEUR);
	}
	
	@Override
	public boolean mouvementEtVerif() {
		tpsAnimation += Endless.delta;
		if (tpsAnimation > .4f & direction.y != 0) {
			if (alterner) direction.x = -1;
			else direction.x = 1;
			direction.y = 0;
			alterner = !alterner;
		}
		if (Physique.mouvementDeBase(direction, position, Stats.VITESSE_MAX_ARME_HANTEE, LARGEUR)) return true;
		free();
		return false;
	}

	@Override
	public int getForce() {		return FORCE + CSG.profil.NvArmeHantee;	}
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
		if (numeroCouleur == 1) return 0.000f;
		return 0.000f;
	}

	@Override
	public float getG() {
		if (numeroCouleur == 1) return 0.854f;
		return 0.725f;
	}

	@Override
	public float getB() {
		if (numeroCouleur == 1) return 0.894f;
		return 0.925f;
	}
}
