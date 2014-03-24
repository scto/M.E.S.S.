package assets.animation;

import jeu.CSG;
import jeu.Stats;

import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationInsecte{
	
	public static AtlasRegion goodShape, badShape;
	
	
	public static TextureRegion getTexture(int pv) {
		if (pv < Stats.PV_INSECTE) return badShape;
		return goodShape;
	}
	
	public static void initAnimation(){
		goodShape = CSG.getAssetMan().getAtlas().findRegion("insecte");
		badShape = CSG.getAssetMan().getAtlas().findRegion("insectecasse");
	}
}
