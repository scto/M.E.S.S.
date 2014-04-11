package assets.animation;

import assets.AssetMan;

import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationQuiTir{
	
	public static TextureRegion goodShape, badShape;
	
	public static void initAnimation(){
		goodShape = AssetMan.getTextureRegion("fusee");
		badShape = AssetMan.getTextureRegion("fuseeamochee");
	}
}
