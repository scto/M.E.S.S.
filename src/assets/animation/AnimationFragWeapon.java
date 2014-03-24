package assets.animation;

import jeu.CSG;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationFragWeapon {

	public static final float TIME = .4f, TOTAL_TIME = 1.6f;
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

	    animation = new Animation(TIME, tr);
		animation.setPlayMode(Animation.LOOP_PINGPONG);
	}	
}
