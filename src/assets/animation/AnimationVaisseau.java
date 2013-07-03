package assets.animation;

import menu.CSG;
import jeu.Endless;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationVaisseau{
	
	private static float tpsDroite, tpsGauche;
	private static TextureRegion[] tr = initAnimation();
	private static int etat = 2;
	private static final float TPS_ANIM = .15f; 

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
		return tr[etat];
	}

	/**
	 * A appeler quand on va vers la droite
	 */
	public static void versLaGauche() {			// Si besoin : on changerait l'orientation du background ici
		if (tpsDroite > TPS_ANIM)				// Si on va vers la droite depuis un moment 
			etat = 0;
		else {
			tpsDroite += Endless.delta;			// Sinon on commence seulement l'anim est pas la mï¿½me
			etat = 1;
			tpsGauche = 0;
		}
	}

	public static void versLaDroite() {			// Si besoin : on changerait l'orientation du background ici
		if (tpsGauche > TPS_ANIM)
			etat = 4;
		else {
			etat = 3;
			tpsGauche += Endless.delta;
			tpsDroite = 0;
		}
	}

	public static void droit() {				// Si besoin : on remettrait l'orientation du background a 0 ici
		if (etat == 2) return; 					// on est deja  droit

		if (tpsDroite > 0) {					// on allait a droite avant
			etat = 1;
			tpsDroite -= Endless.delta;
		} else {
			if (tpsGauche > 0) {				// on allait a gauche avant
				etat = 3;
				tpsGauche -= Endless.delta;
			} else 
				etat = 2;
		}
	}
}
