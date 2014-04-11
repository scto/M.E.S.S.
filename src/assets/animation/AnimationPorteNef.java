package assets.animation;

import jeu.Stats;
import assets.AssetMan;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationPorteNef {

	public static TextureRegion goodShape, badShape;

	public static void initAnimation() {
		goodShape = AssetMan.getTextureRegion("portenef1");
		badShape = AssetMan.getTextureRegion("portenef2");
	}

	public static TextureRegion getTexture(int pv) {
		if (pv < Stats.DEMI_PV_PORTE_NEF)
			return badShape;
		return goodShape;
	}
}
