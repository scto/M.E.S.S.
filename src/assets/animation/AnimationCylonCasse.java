package assets.animation;

import com.badlogic.gdx.graphics.g2d.Animation;

public class AnimationCylonCasse extends Anim {
	
	public static final String[] frames = {"cyloncasse1","cyloncasse2"};
	public static final float TPS_ANIM = .02f, TPS_ANIM_TOTAL = TPS_ANIM * frames.length;
	public static Anim anim = new Anim();
	
	AnimationCylonCasse() {
		anime = anim.initAnim(frames, TPS_ANIM, Animation.NORMAL);
	}
}
