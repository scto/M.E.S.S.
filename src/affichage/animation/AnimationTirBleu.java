package affichage.animation;

import affichage.TexMan;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationTirBleu{
	
	private static final float TPS_ANIM = .12f; //0.02
	public static Animation animation = initAnimation(TPS_ANIM, Animation.NORMAL);

	public TextureRegion getTexture(float tps) {
		return animation.getKeyFrame(tps, false);
	}
	
	protected static Animation initAnimation(float TPS_ANIM, int mode) {
		TextureRegion[] tr = new TextureRegion[18];
		
		tr[0] = TexMan.atlas.findRegion("tirbleu1");
		tr[1] = TexMan.atlas.findRegion("tirbleu2");
		tr[2] = TexMan.atlas.findRegion("tirbleu3");
		tr[3] = TexMan.atlas.findRegion("tirbleu4");
		tr[4] = TexMan.atlas.findRegion("tirbleu5");
		tr[5] = TexMan.atlas.findRegion("tirbleu6");
		tr[6] = TexMan.atlas.findRegion("tirbleu7");
		tr[7] = TexMan.atlas.findRegion("tirbleu8");
		tr[8] = TexMan.atlas.findRegion("tirbleu9");
		tr[9] = TexMan.atlas.findRegion("tirbleu10");
		tr[10] = TexMan.atlas.findRegion("tirbleu11");
		tr[11] = TexMan.atlas.findRegion("tirbleu12");
		tr[12] = TexMan.atlas.findRegion("tirbleu13");
		tr[13] = TexMan.atlas.findRegion("tirbleu14");
		tr[14] = TexMan.atlas.findRegion("tirbleu15");
		tr[15] = TexMan.atlas.findRegion("tirbleu16");
		tr[16] = TexMan.atlas.findRegion("tirbleu17");
		tr[17] = TexMan.atlas.findRegion("tirbleu18");
		
	    Animation animation = new Animation(TPS_ANIM, tr);
		animation.setPlayMode(mode);
		return animation;
	}	
}
