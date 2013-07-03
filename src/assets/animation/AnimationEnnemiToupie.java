package assets.animation;

import menu.CSG;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationEnnemiToupie {
	
	private static final float TPS_ANIM = .04f;
	public static Animation anim;

	public static TextureRegion getTexture(float tps) {
		return anim.getKeyFrame(tps, true);
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
