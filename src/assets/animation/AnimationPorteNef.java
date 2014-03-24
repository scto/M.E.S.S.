package assets.animation;

import jeu.CSG;
import jeu.Stats;

import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationPorteNef {

	public static AtlasRegion goodShape, badShape;

	public static void initAnimation() {
		goodShape = CSG.getAssetMan().getAtlas().findRegion("portenef1");
		badShape = CSG.getAssetMan().getAtlas().findRegion("portenef2");
	}

	public static TextureRegion getTexture(int pv) {
		if (pv < Stats.DEMI_PV_PORTE_NEF)
			return badShape;
		return goodShape;
	}
}
