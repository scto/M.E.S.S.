package affichage.animation;

import jeu.Endless;
import affichage.TexMan;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationVaisseau{
	
	private static float tpsDroite;
	private static float tpsGauche;
	private static TextureRegion[] tr = initAnimation();
	private static int etat = 0;
	private static final float TPS_ANIM = .15f; 

	protected static TextureRegion[] initAnimation() {
		tr = new TextureRegion[5];
		tr[0] = TexMan.atlas.findRegion("joueur1");
		tr[1] = TexMan.atlas.findRegion("joueur2");
		tr[2] = TexMan.atlas.findRegion("joueur3");
		tr[3] = TexMan.atlas.findRegion("joueur4");
		tr[4] = TexMan.atlas.findRegion("joueur5");
		return tr;
	}

	public static TextureRegion getTexture() {
		return tr[etat];
	}

	/**
	 * A appeler quand on va vers la droite
	 * @param delta
	 */
	public static void versDroite() {
		if (tpsDroite > TPS_ANIM)		// Si on va vers la droite depuis un moment 
			etat = 0;
		else {
			tpsDroite += Endless.delta;			// Sinon on commence seulement l'anim est pas la même
			etat = 1;
			tpsGauche = 0;
		}
	}

	public static void versGauche() {
		if (tpsGauche > TPS_ANIM)
			etat = 4;
		else {
			etat = 3;
			tpsGauche += Endless.delta;
			tpsDroite = 0;
		}
	}

	public static void droit() {
		if (tpsDroite > 0) {		// si on allait à droite avant
			etat = 1;
			tpsDroite -= Endless.delta;
		} else {
			if (tpsGauche > 0) {	// si on allait à gauche avat
				etat = 3;
				tpsGauche -= Endless.delta;
			} else {
				etat = 2;
			}
		}
	}
}
