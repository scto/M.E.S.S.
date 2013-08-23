package assets.animation;

import menu.CSG;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationCylon {
	
	private static final float TPS_ANIM = .02f;
	public static Animation anim;

	public static TextureRegion getTexture(float tps) {
		return anim.getKeyFrame(tps, true);
	}

	public static void initAnimation() {
		TextureRegion[] tr = new TextureRegion[2];
		
		tr[0] = CSG.getAssetMan().getAtlas().findRegion("cylon1");
		tr[1] = CSG.getAssetMan().getAtlas().findRegion("cylon2");

	    anim = new Animation(TPS_ANIM, tr);
	    anim.setPlayMode(Animation.NORMAL);
	}	
}
