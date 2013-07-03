package assets.animation;

import menu.CSG;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationExplosion1{
	
	private static final float TPS_ANIM = .04f;
	private static final int COLONNES = 13;
	public static final float tpsTotalAnimationExplosion1 = TPS_ANIM*COLONNES;
    public static Animation animation; 


	public static TextureRegion getTexture(float tps) {
		return animation.getKeyFrame(tps, false);
	}

	public static void initAnimation() {
		TextureRegion[] tr = new TextureRegion[COLONNES];
		
		tr[0] = CSG.getAssetMan().getAtlas().findRegion("explosion1");
		tr[1] = CSG.getAssetMan().getAtlas().findRegion("explosion2");
		tr[2] = CSG.getAssetMan().getAtlas().findRegion("explosion3");
		tr[3] = CSG.getAssetMan().getAtlas().findRegion("explosion4");
		tr[4] = CSG.getAssetMan().getAtlas().findRegion("explosion5");
		tr[5] = CSG.getAssetMan().getAtlas().findRegion("explosion6");
		tr[6] = CSG.getAssetMan().getAtlas().findRegion("explosion7");
		tr[7] = CSG.getAssetMan().getAtlas().findRegion("explosion8");
		tr[8] = CSG.getAssetMan().getAtlas().findRegion("explosion9");
		tr[9] = CSG.getAssetMan().getAtlas().findRegion("explosion10");
		tr[10] = CSG.getAssetMan().getAtlas().findRegion("explosion11");
		tr[11] = CSG.getAssetMan().getAtlas().findRegion("explosion12");
		tr[12] = CSG.getAssetMan().getAtlas().findRegion("explosion13");
		
	    animation = new Animation(TPS_ANIM, tr);
		animation.setPlayMode(Animation.NORMAL);
	}	
}
