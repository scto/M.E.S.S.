package assets.animation;

import com.badlogic.gdx.graphics.g2d.Animation;

public class AnimationEnnemiDeBase extends Anim {
	
	public static final String[] frames = {"ennemidebase1","ennemidebase2","ennemidebase3"};
	public static final float TPS_ANIM = .15f, TPS_ANIM_TOTAL = TPS_ANIM * frames.length;
	public static Anim anim = new Anim();
	
	AnimationEnnemiDeBase() {
		anime = anim.initAnim(frames, TPS_ANIM, Animation.LOOP_PINGPONG);
	}
	
}
