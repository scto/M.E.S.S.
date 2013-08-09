package assets.animation;

import menu.CSG;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationArmeFusee{

	public static final float TPS_ANIM = .4f, TPS_ANIM_TOTAL = 1.6f;
	public static Animation animation; 
	
	public static TextureRegion getTexture(float tps) {
		return animation.getKeyFrame(tps, false);
	}
	
	public static void initAnimation() {
		TextureRegion[] tr = new TextureRegion[4];
		
		tr[0] = CSG.getAssetMan().getAtlas().findRegion("armefrag1");
		tr[1] = CSG.getAssetMan().getAtlas().findRegion("armefrag2");
		tr[2] = CSG.getAssetMan().getAtlas().findRegion("armefrag3");
		tr[3] = CSG.getAssetMan().getAtlas().findRegion("armefrag4");

	    animation = new Animation(TPS_ANIM, tr);
		animation.setPlayMode(Animation.LOOP_PINGPONG);
	}	
}
