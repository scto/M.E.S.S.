package assets.animation;

import assets.AssetMan;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationMine implements Animated {
	
	private static final float TIME = .1f;
	public static Animation anim;

	public TextureRegion getTexture(float tps) {
		return anim.getKeyFrame(tps, true);
	}

	public static void initAnimation() {
		TextureRegion[] tr = new TextureRegion[3];
		
		tr[0] = AssetMan.getTextureRegion("mine1");
		tr[1] = AssetMan.getTextureRegion("mine2");
		tr[2] = AssetMan.getTextureRegion("mine3");

	    anim = new Animation(TIME, tr);
	    anim.setPlayMode(Animation.LOOP_PINGPONG);
	}	
}
