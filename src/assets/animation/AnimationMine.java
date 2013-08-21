package assets.animation;

import com.badlogic.gdx.graphics.g2d.Animation;

public class AnimationMine extends Anim {
	
	public static final String[] frames = {"mine1","mine2", "mine3"};
	public static final float TPS_ANIM = .1f, TPS_ANIM_TOTAL = TPS_ANIM * frames.length;
	public static Anim anim = new Anim();
	
	AnimationMine() {
		anime = anim.initAnim(frames, TPS_ANIM, Animation.LOOP_PINGPONG);
	}

}
