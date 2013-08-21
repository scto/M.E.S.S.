package assets.animation;

import com.badlogic.gdx.graphics.g2d.Animation;

public class AnimationCylon extends Anim{
	

	public static final String[] frames = {"cylon1","cylon2"};
	public static final float TPS_ANIM = .02f, TPS_ANIM_TOTAL = TPS_ANIM * frames.length;
	public static Anim anim = new Anim();
	
	AnimationCylon() {
		anime = anim.initAnim(frames, TPS_ANIM, Animation.NORMAL);
	}
}
