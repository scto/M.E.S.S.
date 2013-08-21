package assets.animation;

import com.badlogic.gdx.graphics.g2d.Animation;

public class AnimationKinder extends Anim {
	
	public static final String[] frames = {"kinder3","kinder2", "kinder1"};
	public static final float TPS_ANIM = 1.5f, TPS_ANIM_TOTAL = TPS_ANIM * frames.length, TPS_OUVERT = TPS_ANIM_TOTAL - TPS_ANIM;
	public static Anim anim = new Anim();
	
	AnimationKinder() {
		anime = anim.initAnim(frames, TPS_ANIM, Animation.NORMAL);
	}
}
