package assets.animation;

import jeu.CSG;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationEnnemiTourne {
	

	private static final float TIME = .05f;
	public static Animation anim;

	public static TextureRegion getTexture(float tps) {
		return anim.getKeyFrame(tps, true);
	}

	public static void initAnimation() {
		TextureRegion[] tr = new TextureRegion[8];
		
		tr[0] = CSG.getAssetMan().getAtlas().findRegion("ennemitourne1");
		tr[1] = CSG.getAssetMan().getAtlas().findRegion("ennemitourne2");
		tr[2] = CSG.getAssetMan().getAtlas().findRegion("ennemitourne3");
		tr[3] = CSG.getAssetMan().getAtlas().findRegion("ennemitourne4");
		tr[4] = CSG.getAssetMan().getAtlas().findRegion("ennemitourne5");
		tr[5] = CSG.getAssetMan().getAtlas().findRegion("ennemitourne6");
		tr[6] = CSG.getAssetMan().getAtlas().findRegion("ennemitourne7");
		tr[7] = CSG.getAssetMan().getAtlas().findRegion("ennemitourne8");
		
	    anim = new Animation(TIME, tr);
	    anim.setPlayMode(Animation.NORMAL);
	}	
}
