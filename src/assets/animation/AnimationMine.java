package assets.animation;

import jeu.CSG;

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
		
		tr[0] = CSG.getAssetMan().getAtlas().findRegion("mine1");
		tr[1] = CSG.getAssetMan().getAtlas().findRegion("mine2");
		tr[2] = CSG.getAssetMan().getAtlas().findRegion("mine3");

	    anim = new Animation(TIME, tr);
	    anim.setPlayMode(Animation.LOOP_PINGPONG);
	}	
}
