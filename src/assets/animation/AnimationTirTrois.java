package assets.animation;
import menu.CSG;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationTirTrois {

	private static final float TPS_ANIM = .027f;
	private static Animation animation; 
	
	public static TextureRegion getTexture(float tps) {
		return animation.getKeyFrame(tps, true);
	}
	
	public static void initAnimation() {
		TextureRegion[] tr = new TextureRegion[4];
		
		tr[0] = CSG.getAssetMan().getAtlas().findRegion("balletrois1");
		tr[1] = CSG.getAssetMan().getAtlas().findRegion("balletrois2");
		tr[2] = CSG.getAssetMan().getAtlas().findRegion("balletrois3");
		tr[3] = CSG.getAssetMan().getAtlas().findRegion("balletrois4");
		
	    animation = new Animation(TPS_ANIM, tr);
		animation.setPlayMode(Animation.LOOP);
	}	
}
