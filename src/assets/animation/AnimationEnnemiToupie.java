package assets.animation;

import menu.CSG;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationEnnemiToupie {
	
<<<<<<< HEAD
	public static final String[] frames = {"ennemirotation1","ennemirotation2", "ennemirotation3","ennemirotation4","ennemirotation5","ennemirotation6","ennemirotation7","ennemirotation8"};
	public static final float TPS_ANIM = .04f, TPS_ANIM_TOTAL = TPS_ANIM * frames.length;
	public static Anim anim = new Anim();
	
	AnimationEnnemiToupie() {
		anime = anim.initAnim(frames, TPS_ANIM, Animation.LOOP);
=======
	private static final float TPS_ANIM = .04f;
	public static Animation anim;

	public static TextureRegion getTexture(float tps) {
		return anim.getKeyFrame(tps, true);
>>>>>>> parent of a593e8e... refact animation
	}

	public static void initAnimation() {
		TextureRegion[] tr = new TextureRegion[8];
		
		tr[0] = CSG.getAssetMan().getAtlas().findRegion("ennemirotation1");
		tr[1] = CSG.getAssetMan().getAtlas().findRegion("ennemirotation2");
		tr[2] = CSG.getAssetMan().getAtlas().findRegion("ennemirotation3");
		tr[3] = CSG.getAssetMan().getAtlas().findRegion("ennemirotation4");
		tr[4] = CSG.getAssetMan().getAtlas().findRegion("ennemirotation5");
		tr[5] = CSG.getAssetMan().getAtlas().findRegion("ennemirotation6");
		tr[6] = CSG.getAssetMan().getAtlas().findRegion("ennemirotation7");
		tr[7] = CSG.getAssetMan().getAtlas().findRegion("ennemirotation8");
		
	    anim = new Animation(TPS_ANIM, tr);
	    anim.setPlayMode(Animation.NORMAL);
	}	
}
