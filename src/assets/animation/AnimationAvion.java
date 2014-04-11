package assets.animation;

import assets.AssetMan;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationAvion{
	
	public static TextureRegion goodShape, badShape;
	
	public static void initAnimation(){
		goodShape = AssetMan.getTextureRegion("avion");
		badShape = AssetMan.getTextureRegion("avioncasse");
	}
	
	public static TextureRegion getTexture(boolean goodShape2) {
		if (goodShape2) return goodShape;
		return badShape;
	}
}
