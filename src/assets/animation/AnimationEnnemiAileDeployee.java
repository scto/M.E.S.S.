package assets.animation;

import assets.AssetMan;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationEnnemiAileDeployee {
	
	private static final float TIME = .3f;
	public static Animation animation; 
	
	public static TextureRegion getTexture(float tps) {
		return animation.getKeyFrame(tps, false);
	}
	
	public static void initAnimation() {
		TextureRegion[] tr = new TextureRegion[3];
		
		tr[0] = AssetMan.getTextureRegion("ennemiailesdeployees3");
		tr[1] = AssetMan.getTextureRegion("ennemiailesdeployees2");
		tr[2] = AssetMan.getTextureRegion("ennemiailesdeployees1");
		
	    animation = new Animation(TIME, tr);
		animation.setPlayMode(Animation.NORMAL);
	}
}
