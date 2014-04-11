package assets.animation;

import assets.AssetMan;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationBouleBleu implements Animated {

	private static final float TIME = .03f;
	public static Animation animation;
	
	public TextureRegion getTexture(float tps) {
		return animation.getKeyFrame(tps, true);
	}
	
	public static void initAnimation() {
		TextureRegion[] tr = new TextureRegion[2];
		
		tr[0] = AssetMan.getTextureRegion("boulebleu1");
		tr[1] = AssetMan.getTextureRegion("boulebleu2");
	    animation = new Animation(TIME, tr);
		animation.setPlayMode(Animation.LOOP_PINGPONG);
	}	
}
