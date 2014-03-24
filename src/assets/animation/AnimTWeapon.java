package assets.animation;
import jeu.CSG;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimTWeapon implements Animated {

	private static final float TIME = .027f;
	private static Animation animation; 
	
	public TextureRegion getTexture(float tps) {
		return animation.getKeyFrame(tps, true);
	}
	
	public static void initAnimation() {
		TextureRegion[] tr = new TextureRegion[4];
		
		tr[0] = CSG.getAssetMan().getAtlas().findRegion("balletrois1");
		tr[1] = CSG.getAssetMan().getAtlas().findRegion("balletrois2");
		tr[2] = CSG.getAssetMan().getAtlas().findRegion("balletrois3");
		tr[3] = CSG.getAssetMan().getAtlas().findRegion("balletrois4");
		
	    animation = new Animation(TIME, tr);
		animation.setPlayMode(Animation.LOOP);
	}	
}
