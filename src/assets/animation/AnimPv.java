package assets.animation;

import jeu.TriggerUser;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class AnimPv extends Anim {
	
	private static Array<Float> palliers = new Array<Float>();
	private int currentPallier = 0;

	/**
	 * Les pourcentages sont passés dans l'ordre genre 0.75, 0.5, 0.1
	 * @param user
	 * @param pourcentages
	 */
	public AnimPv(TriggerUser user, Array<Float> pourcentages) {
		for (Float f : pourcentages) {
			palliers.add(user.pvMax() * f);
		}
	}

	public TextureRegion frame(TriggerUser user) {
		if (next(user) && frameNumber + 1 < keyFrames.length)
			frameNumber++;
		return keyFrames[frameNumber];
	}
	
	private boolean next(TriggerUser user) {
		if (user.pv() < palliers.get(currentPallier)) {
			currentPallier++;
			if (currentPallier > palliers.size - 1)
				currentPallier = palliers.size - 1;
			return true;
		}
		return false;
	}

	public void reset() {
		super.reset();
		currentPallier = 0;
	}

}
