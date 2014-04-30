package jeu.mode.extensions;

import jeu.Strings;
import jeu.mode.EndlessMode;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Tutorial {
	
	private static boolean xpExplained = false;
	private static boolean xpTaken = false;
	private static final int TOUCH_NOT_EXPLAINED = 0, TOUCH_EXPLAINED = 1, TOUCH_EXPLAINED_AND_UNDERSTOOD = 2;
	private static int touchState = TOUCH_NOT_EXPLAINED;
	private TemporaryText xp = new TemporaryText(Strings.TUTO_XP);

	public void act(SpriteBatch batch) {
		if (!Gdx.input.isTouched() && touchState < TOUCH_EXPLAINED_AND_UNDERSTOOD && EndlessMode.now > 3) {
			touchState = TOUCH_EXPLAINED;
			TemporaryText.displayString(batch, Strings.TUTO_TOUCH, 1);
		}
		if (Gdx.input.isTouched() && touchState == TOUCH_EXPLAINED)
			touchState = TOUCH_EXPLAINED_AND_UNDERSTOOD;
		
		if (xpTaken && !xpExplained) {
			xpExplained = xp.act(batch, true);
		}
	}

	public static void xpTaken() {
		xpTaken = true;
	}

}
