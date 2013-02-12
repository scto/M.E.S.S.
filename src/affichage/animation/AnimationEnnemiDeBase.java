package affichage.animation;

import affichage.TexMan;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationEnnemiDeBase{
	
	private static final float TPS_ANIM = .15f;
	public static Animation animation = initAnimation(TPS_ANIM); 
	
	public TextureRegion getTexture(float tps) {
		return animation.getKeyFrame(tps, true);
	}

	protected static Animation initAnimation(float TPS_ANIM) {
		TextureRegion[] tr = new TextureRegion[3];
		
		tr[0] = TexMan.atlas.findRegion("ennemidebase1");
		tr[1] = TexMan.atlas.findRegion("ennemidebase2");
		tr[2] = TexMan.atlas.findRegion("ennemidebase3");
		
	    Animation animation = new Animation(TPS_ANIM, tr);
	    animation.setPlayMode(Animation.LOOP_PINGPONG);
		return animation;
	}	
}
