package assets.animation;

import jeu.CSG;

import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationAvion{
	
	public static AtlasRegion goodShape, badShape;
	
	public static void initAnimation(){
		goodShape = CSG.getAssetMan().getAtlas().findRegion("avion");
		badShape = CSG.getAssetMan().getAtlas().findRegion("avioncasse");
	}
	
	public static TextureRegion getTexture(boolean goodShape2) {
		if (goodShape2) return goodShape;
		return badShape;
	}
}
