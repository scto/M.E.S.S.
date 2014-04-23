package assets.animation;

import jeu.CSG;
import assets.AssetMan;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationZigZag {

	private static TextureRegion[] red, blue, green;

	public static void initAnimation() {
		red = new TextureRegion[5];
		red[0] = AssetMan.getTextureRegion("ennemizigzagred1");
		red[1] = AssetMan.getTextureRegion("ennemizigzagred2");
		red[2] = AssetMan.getTextureRegion("ennemizigzagred3");
		red[3] = AssetMan.getTextureRegion("ennemizigzagred4");
		red[4] = AssetMan.getTextureRegion("ennemizigzagred5");

		green = new TextureRegion[5];
		green[0] = AssetMan.getTextureRegion("ennemizigzaggreen1");
		green[1] = AssetMan.getTextureRegion("ennemizigzaggreen2");
		green[2] = AssetMan.getTextureRegion("ennemizigzaggreen3");
		green[3] = AssetMan.getTextureRegion("ennemizigzaggreen4");
		green[4] = AssetMan.getTextureRegion("ennemizigzaggreen5");

		blue = new TextureRegion[5];
		blue[0] = AssetMan.getTextureRegion("ennemizigzagblue1");
		blue[1] = AssetMan.getTextureRegion("ennemizigzagblue2");
		blue[2] = AssetMan.getTextureRegion("ennemizigzagblue3");
		blue[3] = AssetMan.getTextureRegion("ennemizigzagblue4");
		blue[4] = AssetMan.getTextureRegion("ennemizigzagblue5");
	}

	/**
	 * La methode s'occupe de calculer la frame a afficher suivant la position
	 * en x si on va vers la gauche, la droite ou si on vient de se remettre
	 * droit
	 * 
	 * @return
	 */
	public static TextureRegion getTexture(float x) {
		if (x < CSG.CINQUIEME_ZONE)
			return red[0];
		if (x < CSG.DEUX_CINQUIEME_ZONE)
			return red[1];
		if (x < CSG.TROIS_CINQUIEME_ZONE)
			return red[2];
		if (x < CSG.QUATRE_CINQUIEME_ZONE)
			return red[3];
		return red[4];
	}

	public static TextureRegion getTextureGreen(float x) {
		if (x < CSG.CINQUIEME_ZONE)
			return green[0];
		if (x < CSG.DEUX_CINQUIEME_ZONE)
			return green[1];
		if (x < CSG.TROIS_CINQUIEME_ZONE)
			return green[2];
		if (x < CSG.QUATRE_CINQUIEME_ZONE)
			return green[3];
		return green[4];
	}

	public static TextureRegion getTextureBlue(float x) {
		if (x < CSG.CINQUIEME_ZONE)
			return blue[0];
		if (x < CSG.DEUX_CINQUIEME_ZONE)
			return blue[1];
		if (x < CSG.TROIS_CINQUIEME_ZONE)
			return blue[2];
		if (x < CSG.QUATRE_CINQUIEME_ZONE)
			return blue[3];
		return blue[4];
	}

}
