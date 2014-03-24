package assets.animation;

import jeu.CSG;
import jeu.EndlessMode;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import elements.particular.particles.Particles;

public class AnimPlayer{
	
	private static float tpsDroite, tpsGauche;
	public static TextureRegion[] tr = initAnimation();
	public static int etat = 2;
	private static final float TIME = .15f; 

	public static TextureRegion[] initAnimation() {
		tr = new TextureRegion[5];

		tr[0] = CSG.getAssetMan().getAtlas().findRegion("joueur1");
		tr[1] = CSG.getAssetMan().getAtlas().findRegion("joueur2");
		tr[2] = CSG.getAssetMan().getAtlas().findRegion("joueur3");
		tr[3] = CSG.getAssetMan().getAtlas().findRegion("joueur4");
		tr[4] = CSG.getAssetMan().getAtlas().findRegion("joueur5");
		return tr;
	}

	public static TextureRegion getTexture() {
		if (EndlessMode.triggerStop)
			Particles.addGhost(etat);
		return tr[etat];
	}

	/**
	 * A appeler quand on va vers la droite
	 */
	public static void versLaGauche() {			// Si besoin : on changerait l'orientation du background ici
		if (tpsDroite > TIME)				// Si on va vers la droite depuis un moment 
			etat = 0;
		else {
			tpsDroite += EndlessMode.delta;			// Sinon on commence seulement l'anim est pas la mï¿½me
			etat = 1;
			tpsGauche = 0;
		}
	}

	public static void versLaDroite() {			// Si besoin : on changerait l'orientation du background ici
		if (tpsGauche > TIME)
			etat = 4;
		else {
			etat = 3;
			tpsGauche += EndlessMode.delta;
			tpsDroite = 0;
		}
	}

	public static void droit() {				// Si besoin : on remettrait l'orientation du background a 0 ici
		if (etat == 2) return; 					// on est deja  droit

		if (tpsDroite > 0) {					// on allait a droite avant
			etat = 1;
			tpsDroite -= EndlessMode.delta;
		} else {
			if (tpsGauche > 0) {				// on allait a gauche avant
				etat = 3;
				tpsGauche -= EndlessMode.delta;
			} else 
				etat = 2;
		}
	}
	
}
