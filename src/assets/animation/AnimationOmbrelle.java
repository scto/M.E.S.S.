package assets.animation;

import menu.CSG;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

public class AnimationOmbrelle {
	
	public static AtlasRegion bonEtat;
	
	public static void initAnimation(){
		bonEtat = CSG.getAssetMan().getAtlas().findRegion("ombrelleBoss");
	}
	
	public static TextureRegion getTexture(int pv) {
		return bonEtat;
	}

}
