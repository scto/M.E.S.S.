package affichage.animation;

import affichage.TexMan;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationEnnemiDeBase extends ModeleAnimation{
	
	private static final float TPS_ANIM = .1f;
	private static final int COLONNES = 5;
	private static final int LIGNES = 6;
	public static Animation animation = ModeleAnimation.initAnimation(COLONNES, LIGNES, TexMan.lesVaisseaux, 24, 28, TPS_ANIM, Animation.LOOP_PINGPONG); 
	
	@Override
	public TextureRegion getTexture(float tps) {
		return animation.getKeyFrame(tps, true);
	}

}
