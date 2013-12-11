package assets.animation;

import jeu.CSG;
import jeu.Stats;

import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationAvion{
	
	public static AtlasRegion bonEtat, mauvaisEtat;
	
	public static void initAnimation(){
		bonEtat = CSG.getAssetMan().getAtlas().findRegion("avion");
		mauvaisEtat = CSG.getAssetMan().getAtlas().findRegion("avioncasse");
	}
	
	public static TextureRegion getTexture(int pv) {
		if (pv < Stats.PVMAX_AVION_AMOCHE) return mauvaisEtat;
		return bonEtat;
	}
}
