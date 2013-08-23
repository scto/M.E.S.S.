package assets.animation;

import com.badlogic.gdx.graphics.g2d.Animation;

public class AnimationEnnemiTourne extends Anim {
	
	public static final String[] frames = {"ennemitourne1","ennemitourne2", "ennemitourne3","ennemitourne4","ennemitourne5","ennemitourne6","ennemitourne7","ennemitourne8"};
	public static final float TPS_ANIM = .05f, TPS_ANIM_TOTAL = TPS_ANIM * frames.length;
	public static Anim anim = new Anim();
	
	AnimationEnnemiTourne() {
		anime = anim.initAnim(frames, TPS_ANIM, Animation.LOOP);
	}

}
