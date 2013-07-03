package assets.animation;

import menu.CSG;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationBouleBleuRouge {

	private static final float TPS_ANIM = .03f;
	public static Animation animation; 
	
	/**
	 * La methode s'occupe de calculer la frame � afficher suivant la position en x si on va vers la gauche, la droite ou si on vient de se remettre droit
	 * @return
	 */
	public static TextureRegion getTexture(float tps) {
		return animation.getKeyFrame(tps, true);
	}
	
	public static void initAnimation() {
		TextureRegion[] tr = new TextureRegion[3];
		
		tr[0] = CSG.getAssetMan().getAtlas().findRegion("ennemiboulebleubanderouge1");
		tr[1] = CSG.getAssetMan().getAtlas().findRegion("ennemiboulebleubanderouge2");
		tr[2] = CSG.getAssetMan().getAtlas().findRegion("ennemiboulebleubanderouge3");
		
	    animation = new Animation(TPS_ANIM, tr);
		animation.setPlayMode(Animation.LOOP_PINGPONG);
	}	
}
