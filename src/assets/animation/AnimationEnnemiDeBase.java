package assets.animation;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationEnnemiDeBase {
	
	private static final float TIME = .15f;
	public static Animation animation; 
	
	public static TextureRegion getTexture(float tps) {
		return animation.getKeyFrame(tps, true);
	}

	public static void initAnimation() {
//		TextureRegion[] tr = new TextureRegion[3];
//		
//		tr[0] = AssetMan.getTextureRegion("ennemidebase1");
//		tr[1] = AssetMan.getTextureRegion("ennemidebase2");
//		tr[2] = AssetMan.getTextureRegion("ennemidebase3");
//		
//	    animation = new Animation(TIME, tr);
//	    animation.setPlayMode(Animation.LOOP_PINGPONG);
	}	
}
