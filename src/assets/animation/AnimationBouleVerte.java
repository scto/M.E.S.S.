package assets.animation;

import com.badlogic.gdx.graphics.g2d.Animation;

public class AnimationBouleVerte extends Anim {

	public static final String[] frames = {"bouleverte1","bouleverte2"};
	public static final float TPS_ANIM = .02f, TPS_ANIM_TOTAL = TPS_ANIM * frames.length;
	public static Anim anim = new Anim();
	
	AnimationBouleVerte() {
		anime = anim.initAnim(frames, TPS_ANIM, Animation.LOOP_PINGPONG);
	}
}
