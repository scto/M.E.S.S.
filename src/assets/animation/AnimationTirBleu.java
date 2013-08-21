package assets.animation;

import com.badlogic.gdx.graphics.g2d.Animation;

public class AnimationTirBleu extends Anim {
	
	public static final String[] frames = {"tirbleu1","tirbleu2","tirbleu3","tirbleu4","tirbleu5","tirbleu6","tirbleu7","tirbleu8",
		"tirbleu9","tirbleu10","tirbleu11","tirbleu12","tirbleu13","tirbleu14","tirbleu15","tirbleu16","tirbleu17","tirbleu18"};
	public static final float TPS_ANIM = .019f, TPS_ANIM_TOTAL = TPS_ANIM * frames.length;
	public static Anim anim = new Anim();
	
	AnimationTirBleu() {
		anime = anim.initAnim(frames, TPS_ANIM, Animation.NORMAL);
	}
}
