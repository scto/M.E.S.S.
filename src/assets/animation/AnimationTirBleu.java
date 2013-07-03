package assets.animation;

import menu.CSG;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationTirBleu{
	
	private static final float TPS_ANIM = 0.02f;
	public static Animation animation;

	public static TextureRegion getTexture(float tps) {
		return animation.getKeyFrame(tps, false);
	}
	
	public static void initAnimation() {
		TextureRegion[] tr = new TextureRegion[18];
		
		tr[0] = CSG.getAssetMan().getAtlas().findRegion("tirbleu1");
		tr[1] = CSG.getAssetMan().getAtlas().findRegion("tirbleu2");
		tr[2] = CSG.getAssetMan().getAtlas().findRegion("tirbleu3");
		tr[3] = CSG.getAssetMan().getAtlas().findRegion("tirbleu4");
		tr[4] = CSG.getAssetMan().getAtlas().findRegion("tirbleu5");
		tr[5] = CSG.getAssetMan().getAtlas().findRegion("tirbleu6");
		tr[6] = CSG.getAssetMan().getAtlas().findRegion("tirbleu7");
		tr[7] = CSG.getAssetMan().getAtlas().findRegion("tirbleu8");
		tr[8] = CSG.getAssetMan().getAtlas().findRegion("tirbleu9");
		tr[9] = CSG.getAssetMan().getAtlas().findRegion("tirbleu10");
		tr[10] = CSG.getAssetMan().getAtlas().findRegion("tirbleu11");
		tr[11] = CSG.getAssetMan().getAtlas().findRegion("tirbleu12");
		tr[12] = CSG.getAssetMan().getAtlas().findRegion("tirbleu13");
		tr[13] = CSG.getAssetMan().getAtlas().findRegion("tirbleu14");
		tr[14] = CSG.getAssetMan().getAtlas().findRegion("tirbleu15");
		tr[15] = CSG.getAssetMan().getAtlas().findRegion("tirbleu16");
		tr[16] = CSG.getAssetMan().getAtlas().findRegion("tirbleu17");
		tr[17] = CSG.getAssetMan().getAtlas().findRegion("tirbleu18");
		
	    animation = new Animation(TPS_ANIM, tr);
		animation.setPlayMode(Animation.NORMAL);
	}	
}
