package assets.animation;

import jeu.CSG;
import assets.AssetMan;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationCylon {
	
	private static final float TIME = .02f;
	public static Animation anim;

	public static TextureRegion getTexture(float tps) {
		return anim.getKeyFrame(tps, true);
	}

	public static void initAnimation() {
		TextureRegion[] tr = new TextureRegion[2];
		
		tr[0] = AssetMan.getTextureRegion("cylon1");
		tr[1] = AssetMan.getTextureRegion("cylon2");

	    anim = new Animation(TIME, tr);
	    anim.setPlayMode(Animation.NORMAL);
	}	
}
