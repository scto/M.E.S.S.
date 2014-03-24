package assets.animation;

import jeu.CSG;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationCylonCasse {
	
	private static final float TIME = .02f;
	public static Animation anim;

	public static TextureRegion getTexture(float tps) {
		return anim.getKeyFrame(tps, true);
	}

	public static void initAnimation() {
		TextureRegion[] tr = new TextureRegion[2];
		
		tr[0] = CSG.getAssetMan().getAtlas().findRegion("cyloncasse1");
		tr[1] = CSG.getAssetMan().getAtlas().findRegion("cyloncasse2");

	    anim = new Animation(TIME, tr);
	    anim.setPlayMode(Animation.NORMAL);
	}	
}
