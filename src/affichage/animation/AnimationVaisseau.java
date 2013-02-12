package affichage.animation;

import vaisseaux.joueur.VaisseauType1;
import affichage.TexMan;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationVaisseau{
	
//	private final VaisseauType1 v;
//	private static final float TPS_ANIM = .15f;
//	private Animation centreVersGauche = initAnimationCentreVersGauche();
//	private Animation centreVersDroite = initAnimationCentreVersDroite();
	private static float tpsDroite;
	private static float tpsGauche;
//	private static final TextureRegion[][] vaisseaux = TextureRegion.split(TexMan.lesVaisseaux, 24, 28);
//	private boolean onAllaitVersDroite = false, remettreDroit = false, onAllaitToutDroit = true;
//	private static boolean versDroite = false, versGauche = false, toutDroit = true;
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
	public static void versDroite(float delta) {
		if (tpsDroite > TPS_ANIM)		// Si on va vers la droite depuis un moment 
			etat = 0;
		else {
			tpsDroite += delta;			// Sinon on commence seulement l'anim est pas la même
			etat = 1;
			tpsGauche = 0;
		}
	}

	public static void versGauche(float delta) {
		if (tpsGauche > TPS_ANIM)
			etat = 4;
		else {
			etat = 3;
			tpsGauche += delta;
			tpsDroite = 0;
		}
	}

	public static void droit(float delta) {
		if (tpsDroite > 0) {		// si on allait à droite avant
			etat = 1;
			tpsDroite -= delta;
		} else {
			if (tpsGauche > 0) {	// si on allait à gauche avat
				etat = 3;
				tpsGauche -= delta;
			} else {
				etat = 2;
			}
		}
	}
}
