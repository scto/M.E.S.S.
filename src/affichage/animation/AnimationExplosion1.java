package affichage.animation;

import affichage.TexMan;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationExplosion1{
	
	private static final float TPS_ANIM = .04f;
	private static final int COLONNES = 13;
	public static final float tpsTotalAnimationExplosion1 = TPS_ANIM*COLONNES;
    public static Animation animation = initAnimation(TPS_ANIM, Animation.NORMAL); 


	public TextureRegion getTexture(float tps) {
		return animation.getKeyFrame(tps, false);
	}

	protected static Animation initAnimation(float TPS_ANIM, int mode) {
		TextureRegion[] tr = new TextureRegion[COLONNES];
		
		tr[0] = TexMan.atlas.findRegion("explosion1");
		tr[1] = TexMan.atlas.findRegion("explosion2");
		tr[2] = TexMan.atlas.findRegion("explosion3");
		tr[3] = TexMan.atlas.findRegion("explosion4");
		tr[4] = TexMan.atlas.findRegion("explosion5");
		tr[5] = TexMan.atlas.findRegion("explosion6");
		tr[6] = TexMan.atlas.findRegion("explosion7");
		tr[7] = TexMan.atlas.findRegion("explosion8");
		tr[8] = TexMan.atlas.findRegion("explosion9");
		tr[9] = TexMan.atlas.findRegion("explosion10");
		tr[10] = TexMan.atlas.findRegion("explosion11");
		tr[11] = TexMan.atlas.findRegion("explosion12");
		tr[12] = TexMan.atlas.findRegion("explosion13");
		
	    Animation animation = new Animation(TPS_ANIM, tr);
		animation.setPlayMode(mode);
		return animation;
	}	
}
