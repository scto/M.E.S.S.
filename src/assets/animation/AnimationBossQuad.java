package assets.animation;

import assets.AssetMan;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationBossQuad{
	
	public static TextureRegion goodShape, moyenEtat, badShape;
	
	public static void initAnimation(){
		goodShape = AssetMan.getTextureRegion("bossquad1");
		moyenEtat = AssetMan.getTextureRegion("bossquad2");
		badShape = AssetMan.getTextureRegion("bossquad3");
	}
	
	public static TextureRegion getTexture(int phase) {
		if (phase == 3)	return badShape;
		if (phase == 2)	return moyenEtat;
		return goodShape;
	}
}
