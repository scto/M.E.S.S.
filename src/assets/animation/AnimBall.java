package assets.animation;

import assets.AssetMan;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimBall {

	private static final float TIME = .03f;
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
		
		tr[0] = AssetMan.getTextureRegion("ennemiboulebleubanderouge1");
		tr[1] = AssetMan.getTextureRegion("ennemiboulebleubanderouge2");
		tr[2] = AssetMan.getTextureRegion("ennemiboulebleubanderouge3");
		
	    animation = new Animation(TIME, tr);
		animation.setPlayMode(Animation.LOOP_PINGPONG);
	}	
}
