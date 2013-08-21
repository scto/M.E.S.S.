package assets.animation;
import com.badlogic.gdx.graphics.g2d.Animation;

public class AnimationTirTrois extends Anim{

	public static final String[] frames = {"balletrois1","balletrois2","balletrois3","balletrois4"};
	public static final float TPS_ANIM = .027f, TPS_ANIM_TOTAL = TPS_ANIM * frames.length;
	public static Anim anim = new Anim();
	
	AnimationTirTrois() {
		anime = anim.initAnim(frames, TPS_ANIM, Animation.LOOP_PINGPONG);
	}
	
}
