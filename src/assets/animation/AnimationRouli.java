package assets.animation;

import jeu.CSG;
import assets.AssetMan;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationRouli {
	
	private static TextureRegion[] tr;
	
	public static void initAnimation() {
		tr = new TextureRegion[5];
		tr[0] = AssetMan.getTextureRegion("ennemizigzag1");
		tr[1] = AssetMan.getTextureRegion("ennemizigzag2");
		tr[2] = AssetMan.getTextureRegion("ennemizigzag3");
		tr[3] = AssetMan.getTextureRegion("ennemizigzag4");
		tr[4] = AssetMan.getTextureRegion("ennemizigzag5");
	}	

	/**
	 * La methode s'occupe de calculer la frame a afficher suivant la position en x si on va vers la gauche, la droite ou si on vient de se remettre droit
	 * @return
	 */
	public static TextureRegion getTexture(float x) {
		if (x < CSG.CINQUIEME_ZONE) return tr[0];
		if (x < CSG.DEUX_CINQUIEME_ZONE) return tr[1];
		if (x < CSG.TROIS_CINQUIEME_ZONE) return tr[2];
		if (x < CSG.QUATRE_CINQUIEME_ZONE) return tr[3];
		return tr[4];
	}

}
