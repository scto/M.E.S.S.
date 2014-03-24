package assets.animation;

import jeu.CSG;

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
		
		tr[0] = CSG.getAssetMan().getAtlas().findRegion("meteorite1");
		tr[1] = CSG.getAssetMan().getAtlas().findRegion("meteorite2");
		tr[2] = CSG.getAssetMan().getAtlas().findRegion("meteorite3");
		tr[3] = CSG.getAssetMan().getAtlas().findRegion("meteorite4");

	    animation = new Animation(TIME, tr);
		animation.setPlayMode(Animation.LOOP_PINGPONG);
	}	
}
