package affichage.animation;

import affichage.TexMan;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationTirBleu extends ModeleAnimation{
	
	private static final float TPS_ANIM = .1f;
	private static final int COLONNES = 13;
	private static final int LIGNES = 0;
	public static Animation animation = ModeleAnimation.initAnimation(COLONNES, LIGNES, TexMan.balleDeBase, 12, 13, TPS_ANIM, Animation.LOOP_PINGPONG);

	
	public AnimationTirBleu() {
		super();
	}


	@Override
	public TextureRegion getTexture(float tps) {
		return animation.getKeyFrame(tps, true);
	}
}
