package affichage.animation;

import affichage.TexMan;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationEnnemiAileDeployee extends ModeleAnimation{
	
	private static final int COLONNES = 3;
	private static final int LIGNES = 0;
	private static final float TPS_ANIM = .3f;
	public static Animation animation = ModeleAnimation.initAnimation(COLONNES, LIGNES, TexMan.vaisseauxAileDeployee, 21, 21, TPS_ANIM, Animation.REVERSED); 
	
	@Override
	public TextureRegion getTexture(float tps) {
		return animation.getKeyFrame(tps, false);
	}

}
