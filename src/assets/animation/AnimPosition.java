package assets.animation;

import jeu.TriggerUser;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class AnimPosition extends Anim {
	
	private Array<Float> palliers = new Array<Float>(true, 5);
	/**
	 * Les pourcentages sont passés dans l'ordre genre 0.75, 0.5, 0.1
	 * @param user
	 * @param pourcentagesLargeurEcran
	 */
	public AnimPosition(Array<Float> pourcentagesLargeurEcran) {
		for (Float f : pourcentagesLargeurEcran) {
			palliers.add(Gdx.graphics.getWidth() * f);
		}
	}

	public TextureRegion frame(TriggerUser user) {
		return keyFrames[getFrameNumber()];
	}

	private int getFrameNumber() {
		for (Float f : palliers) {
//			if (f )
		}
		return 0;
	}

	public void reset() {
		super.reset();
	}

	public void addPallier(float pallier, TriggerUser user) {
		palliers.add(user.pvMax() * pallier);
		
	}

}
