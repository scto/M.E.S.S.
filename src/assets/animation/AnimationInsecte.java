package assets.animation;

import menu.CSG;
import jeu.Stats;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationInsecte{
	
	public static AtlasRegion bonEtat, mauvaisEtat;
	
	
	public static TextureRegion getTexture(int pv) {
		if (pv < Stats.PV_INSECTE) return mauvaisEtat;
		return bonEtat;
	}
	
	public static void initAnimation(){
		bonEtat = CSG.getAssetMan().getAtlas().findRegion("insecte");
		mauvaisEtat = CSG.getAssetMan().getAtlas().findRegion("insectecasse");
	}
}
