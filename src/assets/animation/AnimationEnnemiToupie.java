package assets.animation;

import assets.AssetMan;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationEnnemiToupie {
	
	private static final float TIME = .04f;
	public static Animation anim;

	public static TextureRegion getTexture(float tps) {
		return anim.getKeyFrame(tps, true);
	}

	public static void initAnimation() {
		TextureRegion[] tr = new TextureRegion[8];
		
		tr[0] = AssetMan.getTextureRegion("ennemirotation1");
		tr[1] = AssetMan.getTextureRegion("ennemirotation2");
		tr[2] = AssetMan.getTextureRegion("ennemirotation3");
		tr[3] = AssetMan.getTextureRegion("ennemirotation4");
		tr[4] = AssetMan.getTextureRegion("ennemirotation5");
		tr[5] = AssetMan.getTextureRegion("ennemirotation6");
		tr[6] = AssetMan.getTextureRegion("ennemirotation7");
		tr[7] = AssetMan.getTextureRegion("ennemirotation8");
		
	    anim = new Animation(TIME, tr);
	    anim.setPlayMode(Animation.NORMAL);
	}	
}
