package assets.animation;
import jeu.CSG;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationTirFeu {

	private static final float TPS_ANIM = .1f;
	public static Animation animation; 
	
	public static TextureRegion getTexture(float tps) {
		return animation.getKeyFrame(tps, true);
	}
	
	public static void initAnimation() {
		TextureRegion[] tr = new TextureRegion[3];
		
		tr[0] = CSG.getAssetMan().getAtlas().findRegion("boulefeu1");
		tr[1] = CSG.getAssetMan().getAtlas().findRegion("boulefeu2");
		tr[2] = CSG.getAssetMan().getAtlas().findRegion("boulefeu3");
		
	    animation = new Animation(TPS_ANIM, tr);
		animation.setPlayMode(Animation.LOOP_PINGPONG);
	}	
}
