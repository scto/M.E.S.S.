package assets.animation;

import assets.AssetMan;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationMeteorite implements Animated{

	public static final float TIME = .8f;
	public static final float TIME_TOTAL = 3.2f;
	public static Animation animation; 
	
	public TextureRegion getTexture(float tps) {
		return animation.getKeyFrame(tps, false);
	}
	
	public static void initAnimation() {
		TextureRegion[] tr = new TextureRegion[4];
		
		tr[0] = AssetMan.getTextureRegion("meteorite1");
		tr[1] = AssetMan.getTextureRegion("meteorite2");
		tr[2] = AssetMan.getTextureRegion("meteorite3");
		tr[3] = AssetMan.getTextureRegion("meteorite4");

	    animation = new Animation(TIME, tr);
		animation.setPlayMode(Animation.LOOP_PINGPONG);
	}	
}
