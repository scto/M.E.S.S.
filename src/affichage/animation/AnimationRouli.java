package affichage.animation;

import menu.CSG;
import affichage.TexMan;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * L'animation se fait suivant la position en x, l'écran est divisé en 5
 * @author Julien
 *
 */
public class AnimationRouli {
	
	private static TextureRegion[] tr = initAnimation();
	
	protected static TextureRegion[] initAnimation() {
		tr = new TextureRegion[5];
		tr[0] = TexMan.atlas.findRegion("ennemizigzag1");
		tr[1] = TexMan.atlas.findRegion("ennemizigzag2");
		tr[2] = TexMan.atlas.findRegion("ennemizigzag3");
		tr[3] = TexMan.atlas.findRegion("ennemizigzag4");
		tr[4] = TexMan.atlas.findRegion("ennemizigzag5");
		return tr;
	}	

	/**
	 * La methode s'occupe de calculer la frame à afficher suivant la position en x si on va vers la gauche, la droite ou si on vient de se remettre droit
	 * @return
	 */
	public static TextureRegion getTexture(float x) {
		if(x < CSG.CINQUIEME_ECRAN) return tr[0];
		if(x < CSG.DEUX_CINQUIEME_ECRAN) return tr[1];
		if(x < CSG.TROIS_CINQUIEME_ECRAN) return tr[2];
		if(x < CSG.QUATRE_CINQUIEME_ECRAN) return tr[3];
		return tr[4];
	}

}
