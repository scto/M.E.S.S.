package assets.animation;

import assets.AssetMan;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationBossMine{
	
	public static TextureRegion moyenEtat, goodShape;
	
	public static void initAnimation(){
		goodShape =	AssetMan.getTextureRegion("bossmine");
		moyenEtat = AssetMan.getTextureRegion("bossminecasse");
	}
	
	public static TextureRegion getTexture(int pv, int pvPhase2) {
		if (pv < pvPhase2) return moyenEtat;
		return goodShape;
	}
}
