package vaisseaux.armes;

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
public class ArmeBossQuad extends Armes implements Poolable{
	
	// ** ** caracteristiques g�n�rales
	public static final int LARGEUR= CSG.LARGEUR_ECRAN / 15;
	public static final int DEMI_LARGEUR = LARGEUR/2;
	public static final int HAUTEUR = (int) (LARGEUR * 1.5);
	public static final int DEMI_HAUTEUR = HAUTEUR / 2; 
	public static final float CADENCETIR = .60f;
	private final int FORCE = 8;
	public static Pool<ArmeBossQuad> pool = Pools.get(ArmeBossQuad.class);
	private float tpsAnimation = 0;
	
	public void reset() {
	}

	@Override
	public void afficherSansParticules(SpriteBatch batch) {
		tpsAnimation += Endless.delta;
		batch.draw(Anim.tirFeu.getTexture(tpsAnimation), position.x, position.y,
		// CENTRE DE LA ROTATION EN X													// CENTRE DE LA ROTATION EN Y
		DEMI_LARGEUR, DEMI_HAUTEUR,
		// LARGEUR DU RECTANGLE AFFICHE		HAUTEUR DU RECTANGLE
		LARGEUR, HAUTEUR,
		//scaleX the scale of the rectangle around originX/originY in x ET Y
		1,0.5f,
		// L'ANGLE DE ROTATION
		90,
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
	public void free() {
		
		pool.free(this);
	}
}
