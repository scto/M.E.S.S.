package assets.animation;

import menu.CSG;
import jeu.Stats;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationPorteNef{
	
	public static AtlasRegion bonEtat, mauvaisEtat;
	
	public static void initAnimation(){
		bonEtat = CSG.getAssetMan().getAtlas().findRegion("portenef1");
		mauvaisEtat = CSG.getAssetMan().getAtlas().findRegion("portenef2");
	}
	
	public static TextureRegion getTexture(int pv) {
		if (pv < Stats.DEMI_PVMAX_PORTE_NEF) return mauvaisEtat;
		return bonEtat;
	}
}
