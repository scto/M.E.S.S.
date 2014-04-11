package assets.animation;

import assets.AssetMan;

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
		
		tr[0] = AssetMan.getTextureRegion("ennemitourne1");
		tr[1] = AssetMan.getTextureRegion("ennemitourne2");
		tr[2] = AssetMan.getTextureRegion("ennemitourne3");
		tr[3] = AssetMan.getTextureRegion("ennemitourne4");
		tr[4] = AssetMan.getTextureRegion("ennemitourne5");
		tr[5] = AssetMan.getTextureRegion("ennemitourne6");
		tr[6] = AssetMan.getTextureRegion("ennemitourne7");
		tr[7] = AssetMan.getTextureRegion("ennemitourne8");
		
	    anim = new Animation(TIME, tr);
	    anim.setPlayMode(Animation.NORMAL);
	}	
}
