package affichage.animation;

import affichage.TexMan;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationEnnemiTourne {
	
	private static final int COLONNES = 8;
	private static final int LIGNES = 0;
	private static final float TPS_ANIM = .05f;
	public static Animation anim = ModeleAnimation.initAnimation(COLONNES, LIGNES, TexMan.ennemiTourne, 21, 21, TPS_ANIM, Animation.LOOP);

	public TextureRegion getTexture(float tps) {
		return anim.getKeyFrame(tps);
	}

}
