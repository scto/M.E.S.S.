package assets.animation;

import com.badlogic.gdx.graphics.g2d.Animation;

public class AnimationBouleBleuRouge extends Anim {

	public static final String[] frames = {"ennemiboulebleubanderouge1","ennemiboulebleubanderouge2","ennemiboulebleubanderouge3"};
	public static final float TPS_ANIM = .03f, TPS_ANIM_TOTAL = TPS_ANIM * frames.length;
	public static Anim anim = new Anim();
	
	AnimationBouleBleuRouge() {
		anime = anim.initAnim(frames, TPS_ANIM, Animation.LOOP_PINGPONG);
	}
}
