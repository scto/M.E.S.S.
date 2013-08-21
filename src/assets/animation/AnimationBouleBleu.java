package assets.animation;

import com.badlogic.gdx.graphics.g2d.Animation;

public class AnimationBouleBleu extends Anim {

	public static final String[] frames = {"boulebleu1","boulebleu2"};
	public static final float TPS_ANIM = .03f, TPS_ANIM_TOTAL = TPS_ANIM * frames.length;
	public static Anim anim = new Anim();
	
	AnimationBouleBleu() {
		anime = anim.initAnim(frames, TPS_ANIM, Animation.LOOP_PINGPONG);
	}
}
