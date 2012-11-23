package affichage.animation;
import affichage.TexMan;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationTirFeu extends ModeleAnimation{

	private static final float TPS_ANIM = .1f;
	private static final int COLONNES = 3;
	private static final int LIGNES = 0;
	public static Animation animation = ModeleAnimation.initAnimation(COLONNES, LIGNES, TexMan.balleFeu, 16, 27, TPS_ANIM, Animation.LOOP_PINGPONG); 
	
	/**
	 * La methode s'occupe de calculer la frame à afficher suivant la position en x si on va vers la gauche, la droite ou si on vient de se remettre droit
	 * @return
	 */
	public TextureRegion getTexture(float tps) {
		return animation.getKeyFrame(tps, true);
	}
}
