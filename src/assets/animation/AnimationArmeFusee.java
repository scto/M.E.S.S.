package assets.animation;

import com.badlogic.gdx.graphics.g2d.Animation;

public class AnimationArmeFusee extends Anim {

	public static final String[] frames = {"armefrag1","armefrag2","armefrag3","armefrag4"};
	public static final float TPS_ANIM = .4f, TPS_ANIM_TOTAL = TPS_ANIM * frames.length;
	public static final Anim anim = new Anim();
	
	public static void initAnimation() {
		anim.initAnim(frames, TPS_ANIM, Animation.LOOP_PINGPONG);
	}
	
}
