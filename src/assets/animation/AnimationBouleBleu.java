package assets.animation;

import jeu.CSG;
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
		
		tr[0] = CSG.getAssetMan().getAtlas().findRegion("boulebleu1");
		tr[1] = CSG.getAssetMan().getAtlas().findRegion("boulebleu2");
	    animation = new Animation(TIME, tr);
		animation.setPlayMode(Animation.LOOP_PINGPONG);
	}	
}
