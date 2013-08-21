package assets.animation;

import com.badlogic.gdx.graphics.g2d.Animation;

public class AnimationMeteorite extends Anim {
	
	public static final String[] frames = {"meteorite1","meteorite2", "meteorite3","meteorite4"};
	public static final float TPS_ANIM = .8f, TPS_ANIM_TOTAL = TPS_ANIM * frames.length;
	public static Anim anim = new Anim();
	
	AnimationMeteorite() {
		anime = anim.initAnim(frames, TPS_ANIM, Animation.LOOP_PINGPONG);
	}

}
