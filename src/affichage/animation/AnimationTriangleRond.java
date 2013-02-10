package affichage.animation;

import affichage.TexMan;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationTriangleRond {

	private static final int COLONNES = 12;
	private static final float TPS_ANIM = .2f;
	public static Animation anim = initAnimation(TPS_ANIM, Animation.LOOP);

	public TextureRegion getTexture(float tps) {
		return anim.getKeyFrame(tps);
	}
	
	protected static Animation initAnimation(float TPS_ANIM, int mode) {
		TextureRegion[] tr = new TextureRegion[COLONNES];
		
		tr[0] = TexMan.atlas.findRegion("trianglerond1");
		tr[1] = TexMan.atlas.findRegion("trianglerond2");
		tr[2] = TexMan.atlas.findRegion("trianglerond3");
		tr[3] = TexMan.atlas.findRegion("trianglerond4");
		tr[4] = TexMan.atlas.findRegion("trianglerond5");
		tr[5] = TexMan.atlas.findRegion("trianglerond6");
		tr[6] = TexMan.atlas.findRegion("trianglerond7");
		tr[7] = TexMan.atlas.findRegion("trianglerond8");
		tr[8] = TexMan.atlas.findRegion("trianglerond9");
		tr[9] = TexMan.atlas.findRegion("trianglerond10");
		tr[10] = TexMan.atlas.findRegion("trianglerond11");
		tr[11] = TexMan.atlas.findRegion("trianglerond12");
		
	    Animation animation = new Animation(TPS_ANIM, tr);
		animation.setPlayMode(mode);
		return animation;
	}	
}
