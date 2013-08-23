package assets.animation;

import com.badlogic.gdx.graphics.g2d.Animation;

public class AnimationEnnemiToupie extends Anim {
	
	public static final String[] frames = {"ennemirotation1","ennemirotation2", "ennemirotation3","ennemirotation4","ennemirotation5","ennemirotation6","ennemirotation7","ennemirotation8"};
	public static final float TPS_ANIM = .04f, TPS_ANIM_TOTAL = TPS_ANIM * frames.length;
	public static Anim anim = new Anim();
	
	AnimationEnnemiToupie() {
		anime = anim.initAnim(frames, TPS_ANIM, Animation.LOOP);
	}

}
