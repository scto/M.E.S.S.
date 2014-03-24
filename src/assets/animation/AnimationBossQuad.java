package assets.animation;

import jeu.CSG;

import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationBossQuad{
	
	public static AtlasRegion goodShape, moyenEtat, badShape;
	
	public static void initAnimation(){
		goodShape = CSG.getAssetMan().getAtlas().findRegion("bossquad1");
		moyenEtat = CSG.getAssetMan().getAtlas().findRegion("bossquad2");
		badShape = CSG.getAssetMan().getAtlas().findRegion("bossquad3");
	}
	
	public static TextureRegion getTexture(int phase) {
		if (phase == 3)	return badShape;
		if (phase == 2)	return moyenEtat;
		return goodShape;
	}
}
