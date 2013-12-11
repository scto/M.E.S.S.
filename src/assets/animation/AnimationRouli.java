package assets.animation;

import jeu.CSG;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationRouli {
	
	private static TextureRegion[] tr;
	
	public static void initAnimation() {
		tr = new TextureRegion[5];
		tr[0] = CSG.getAssetMan().getAtlas().findRegion("ennemizigzag1");
		tr[1] = CSG.getAssetMan().getAtlas().findRegion("ennemizigzag2");
		tr[2] = CSG.getAssetMan().getAtlas().findRegion("ennemizigzag3");
		tr[3] = CSG.getAssetMan().getAtlas().findRegion("ennemizigzag4");
		tr[4] = CSG.getAssetMan().getAtlas().findRegion("ennemizigzag5");
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
