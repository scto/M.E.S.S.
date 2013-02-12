package affichage.animation;
import affichage.TexMan;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationTirFeu {

	private static final float TPS_ANIM = .1f;
	public static Animation animation = initAnimation(TPS_ANIM, Animation.LOOP_PINGPONG); 
	
	/**
	 * La methode s'occupe de calculer la frame à afficher suivant la position en x si on va vers la gauche, la droite ou si on vient de se remettre droit
	 * @return
	 */
	public TextureRegion getTexture(float tps) {
		return animation.getKeyFrame(tps, true);
	}
	
	protected static Animation initAnimation(float TPS_ANIM, int mode) {
		TextureRegion[] tr = new TextureRegion[3];
		
		tr[0] = TexMan.atlas.findRegion("boulefeu1");
		tr[1] = TexMan.atlas.findRegion("boulefeu2");
		tr[2] = TexMan.atlas.findRegion("boulefeu3");
		
	    Animation animation = new Animation(TPS_ANIM, tr);
		animation.setPlayMode(mode);
		return animation;
	}	
}
