package assets.animation;

import menu.CSG;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationEnnemiAileDeployee {
	
	private static final float TPS_ANIM = .3f;
	public static Animation animation; 
	
	public static TextureRegion getTexture(float tps) {
		return animation.getKeyFrame(tps, false);
	}
	
	public static void initAnimation() {
		TextureRegion[] tr = new TextureRegion[3];
		
		tr[0] = CSG.getAssetMan().getAtlas().findRegion("ennemiailesdeployees3");
		tr[1] = CSG.getAssetMan().getAtlas().findRegion("ennemiailesdeployees2");
		tr[2] = CSG.getAssetMan().getAtlas().findRegion("ennemiailesdeployees1");
		
	    animation = new Animation(TPS_ANIM, tr);
		animation.setPlayMode(Animation.NORMAL);
	}
}
