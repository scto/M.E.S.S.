
package jeu.mode.extensions;

import com.badlogic.gdx.Gdx;

import jeu.CSG;
import jeu.mode.EndlessMode;
import behind.SoundMan;

public class Transition {
	
	public static final int BOMB = 1, TIME_STOP = 2, POP_OUT_SHIELD = 3;
	private static final float TRANSITION_BEGIN = 20;
	private float facteurTransition = TRANSITION_BEGIN, transitionInit;
	private final static float stepTransition = .1f;
	private boolean movementTransition = false;
	
	private void transitionVersMouvement() {
		// On ne decremente le chrono de ralentissement que quand on est au facteur de ralentissement max
		if (facteurTransition > 1) {
			facteurTransition -= stepTransition;
			SoundMan.transitionUp(((TRANSITION_BEGIN - facteurTransition)/20));
			EndlessMode.touched.setUniformf("iGlobalTime", facteurTransition / transitionInit);
			EndlessMode.timeStop.setUniformf("iGlobalTime", facteurTransition / transitionInit);
		} else {
			deactivate();
		}
		EndlessMode.delta /= facteurTransition;
		EndlessMode.majDeltas(Gdx.input.isTouched());
	}

	private void deactivate() {
		CSG.batch.setShader(EndlessMode.original);
		SoundMan.setOriginalVolume();
		movementTransition = false;
	}

	public void activate(float length, int mode) {
		facteurTransition = length;
		movementTransition = true;
		transitionInit = length;
		switch (mode) {
		case TIME_STOP:
		case BOMB:			
			CSG.batch.setShader(EndlessMode.timeStop);
			break;
		case POP_OUT_SHIELD:
			CSG.batch.setShader(EndlessMode.touched);
			break;
		}		
	}

	public void act() {
		if (movementTransition)
			transitionVersMouvement();
	}

	public void reset() {
		facteurTransition = TRANSITION_BEGIN;
		movementTransition = false;
	}

}
