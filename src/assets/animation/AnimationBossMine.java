package assets.animation;

import jeu.CSG;

import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationBossMine{
	
	public static AtlasRegion goodShape, moyenEtat;
	
	public static void initAnimation(){
		goodShape = CSG.getAssetMan().getAtlas().findRegion("bossmine");
		moyenEtat = CSG.getAssetMan().getAtlas().findRegion("bossminecasse");
	}
	
	public static TextureRegion getTexture(int pv, int pvPhase2) {
		if (pv < pvPhase2) return moyenEtat;
		return goodShape;
	}
}
