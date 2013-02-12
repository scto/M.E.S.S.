package affichage.animation;

import affichage.TexMan;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationEnnemiTourne {
	
	private static final float TPS_ANIM = .05f;
	public static Animation anim = initAnimation(TPS_ANIM);//ModeleAnimation.initAnimation(COLONNES, LIGNES, TexMan.ennemiTourne, 21, 21, TPS_ANIM, Animation.LOOP);

	public TextureRegion getTexture(float tps) {
		return anim.getKeyFrame(tps);
	}

	protected static Animation initAnimation(float TPS_ANIM) {
		TextureRegion[] tr = new TextureRegion[8];
		
		tr[0] = TexMan.atlas.findRegion("ennemitourne1");
		tr[1] = TexMan.atlas.findRegion("ennemitourne2");
		tr[2] = TexMan.atlas.findRegion("ennemitourne3");
		tr[3] = TexMan.atlas.findRegion("ennemitourne4");
		tr[4] = TexMan.atlas.findRegion("ennemitourne5");
		tr[5] = TexMan.atlas.findRegion("ennemitourne6");
		tr[6] = TexMan.atlas.findRegion("ennemitourne7");
		tr[7] = TexMan.atlas.findRegion("ennemitourne8");
		
	    Animation animation = new Animation(TPS_ANIM, tr);
	    animation.setPlayMode(Animation.LOOP_PINGPONG);
		return animation;
	}	
}
