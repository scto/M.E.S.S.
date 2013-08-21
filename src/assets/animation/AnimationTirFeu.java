package assets.animation;
import com.badlogic.gdx.graphics.g2d.Animation;

public class AnimationTirFeu extends Anim {

	public static final String[] frames = {"boulefeu1","boulefeu2","boulefeu3"};
	public static final float TPS_ANIM = .1f, TPS_ANIM_TOTAL = TPS_ANIM * frames.length;
	public static Anim anim = new Anim();
	
	AnimationTirFeu() {
		anime = anim.initAnim(frames, TPS_ANIM, Animation.LOOP_PINGPONG);
	}

}
