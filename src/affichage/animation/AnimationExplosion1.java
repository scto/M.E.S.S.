package affichage.animation;

import affichage.TexMan;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationExplosion1 extends ModeleAnimation{
	
	private static final float TPS_ANIM = .04f;
	private static final int COLONNES = 13;
	private static final int LIGNES = 0;
	public static final float tpsTotalAnimationExplosion1 = TPS_ANIM*COLONNES;
	public static Animation animation = ModeleAnimation.initAnimation(COLONNES, LIGNES, TexMan.explosionNv1, 12, 14, TPS_ANIM, Animation.NORMAL); 

	@Override
	public TextureRegion getTexture(float tps) {
		return animation.getKeyFrame(tps, false);
	}

}
