package assets.animation;

import jeu.CSG;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationKinder {
	
	private static final float TIME = 1.5f;
	public static final float TIME_OUVERT = TIME * 2;
	public static Animation anim;

	public static TextureRegion getTexture(float tps) {
		return anim.getKeyFrame(tps, false);
	}

	public static void initAnimation() {
		TextureRegion[] tr = new TextureRegion[3];
		
		tr[0] = CSG.getAssetMan().getAtlas().findRegion("kinder3");
		tr[1] = CSG.getAssetMan().getAtlas().findRegion("kinder2");
		tr[2] = CSG.getAssetMan().getAtlas().findRegion("kinder1");

	    anim = new Animation(TIME, tr);
	    anim.setPlayMode(Animation.NORMAL);
	}	
}
