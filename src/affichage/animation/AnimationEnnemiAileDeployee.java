package affichage.animation;

import affichage.TexMan;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationEnnemiAileDeployee extends ModeleAnimation{
	
	private static final int COLONNES = 3;
	private static final float TPS_ANIM = .3f;
	public static Animation animation = initAnimation(TPS_ANIM, Animation.NORMAL); 
	
	@Override
	public TextureRegion getTexture(float tps) {
		return animation.getKeyFrame(tps, false);
	}
	
	protected static Animation initAnimation(float TPS_ANIM, int mode) {
		TextureRegion[] tr = new TextureRegion[COLONNES];
		
		tr[0] = TexMan.atlas.findRegion("ennemiailesdeployees3");
		tr[1] = TexMan.atlas.findRegion("ennemiailesdeployees2");
		tr[2] = TexMan.atlas.findRegion("ennemiailesdeployees1");
		
	    Animation animation = new Animation(TPS_ANIM, tr);
		animation.setPlayMode(mode);
		return animation;
	}	

}
