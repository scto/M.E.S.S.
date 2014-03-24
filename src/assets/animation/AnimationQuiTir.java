package assets.animation;

import jeu.CSG;

import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

public class AnimationQuiTir{
	
	// Animation
	public static AtlasRegion goodShape;
	public static AtlasRegion badShape;
	
	public static void initAnimation(){
		goodShape = CSG.getAssetMan().getAtlas().findRegion("fusee");
		badShape = CSG.getAssetMan().getAtlas().findRegion("fuseeamochee");
	}
}
