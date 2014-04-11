package assets.animation;

import assets.AssetMan;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationOmbrelle {
	
	public static TextureRegion goodShape;
	
	public static void initAnimation(){
		goodShape = AssetMan.getTextureRegion("ombrelleboss");
	}
	
	public static TextureRegion getTexture(int pv) {
		return goodShape;
	}

}
