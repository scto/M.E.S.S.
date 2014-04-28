package assets.animation;

import jeu.Stats;
import assets.AssetMan;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationInsecte{
	
	public static TextureRegion goodShape, badShape;
	
	
	public static TextureRegion getTexture(int pv) {
		if (pv < Stats.PV_INSECTE) return badShape;
		return goodShape;
	}
	
	public static void initAnimation(){
		goodShape = AssetMan.getTextureRegion("insecte");
		badShape = AssetMan.getTextureRegion("insectecasse");
	}
}
