package affichage.animation;

import vaisseaux.Vaisseaux;
import menu.CSG;
import affichage.TexMan;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * L'animation se fait suivant la position en x, l'écran est divisé en 5
 * @author Julien
 *
 */
public class AnimationRouli {
	
	private Vaisseaux v;
	private static final TextureRegion[][] vaisseaux = TextureRegion.split(TexMan.lesVaisseaux, 24, 28);
	private static final int LIGNE = 5;
	
	public AnimationRouli() {
	}
	
	public void setV(Vaisseaux v) {
		this.v = v;
	}
	

	public void afficher(SpriteBatch batch) {
		batch.draw(getTexture(), v.position.x, v.position.y, v.getLargeur(), v.getHauteur());
	}

	/**
	 * La methode s'occupe de calculer la frame à afficher suivant la position en x si on va vers la gauche, la droite ou si on vient de se remettre droit
	 * @return
	 */
	public TextureRegion getTexture() {
		if(v.position.x < CSG.CINQUIEME_ECRAN) return vaisseaux[LIGNE][4];
		if(v.position.x < CSG.DEUX_CINQUIEME_ECRAN) return vaisseaux[LIGNE][3];
		if(v.position.x < CSG.TROIS_CINQUIEME_ECRAN) return vaisseaux[LIGNE][2];
		if(v.position.x < CSG.QUATRE_CINQUIEME_ECRAN) return vaisseaux[LIGNE][1];
		return vaisseaux[LIGNE][0];
	}

}
