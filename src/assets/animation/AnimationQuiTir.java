package assets.animation;

import jeu.CSG;
import jeu.Stats;

import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationQuiTir{
	
	// Animation
	public static AtlasRegion bonEtat;
	public static AtlasRegion mauvaisEtat;
	
	public static void initAnimation(){
		bonEtat = CSG.getAssetMan().getAtlas().findRegion("fusee");
		mauvaisEtat = CSG.getAssetMan().getAtlas().findRegion("fuseeamochee");
	}
	
	public static TextureRegion getTexture(int pv) {
		if (pv < Stats.DEMI_PV_QUI_TIR) return mauvaisEtat;
		return bonEtat;
	}
}
