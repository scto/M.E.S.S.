package assets.animation;

import com.badlogic.gdx.graphics.g2d.Animation;

public class AnimationEnnemiAileDeployee extends Anim{
	
	public static final String[] frames = {"ennemiailesdeployees3","ennemiailesdeployees2", "ennemiailesdeployees1"};
	public static final float TPS_ANIM = .3f, TPS_ANIM_TOTAL = TPS_ANIM * frames.length;
	public static Anim anim = new Anim();
	
	AnimationEnnemiAileDeployee() {
		anime = anim.initAnim(frames, TPS_ANIM, Animation.NORMAL);
	}

}
