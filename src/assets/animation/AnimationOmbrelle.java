package assets.animation;

import jeu.CSG;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

public class AnimationOmbrelle {
	
	public static AtlasRegion goodShape;
	
	public static void initAnimation(){
		goodShape = CSG.getAssetMan().getAtlas().findRegion("ombrelleboss");
	}
	
	public static TextureRegion getTexture(int pv) {
		return goodShape;
	}

}
