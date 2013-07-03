package assets.animation;

import menu.CSG;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationBouleBleu{

	private static final float TPS_ANIM = .03f;
	public static Animation animation; 
	
	public static TextureRegion getTexture(float tps) {
		return animation.getKeyFrame(tps, true);
	}
	
	public static void initAnimation() {
		TextureRegion[] tr = new TextureRegion[2];
		
		tr[0] = CSG.getAssetMan().getAtlas().findRegion("boulebleu1");
		tr[1] = CSG.getAssetMan().getAtlas().findRegion("boulebleu2");

	    animation = new Animation(TPS_ANIM, tr);
		animation.setPlayMode(Animation.LOOP_PINGPONG);
	}	
}
