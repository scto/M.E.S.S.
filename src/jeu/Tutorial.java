package jeu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Tutorial {
	
	private static boolean xpExplained = false;
	private static boolean xpTaken = false;
	private static float xpDisplayTime = 0;
	private static final float DISPLAY_TIME = 5f;
	private static final int TOUCH_NOT_EXPLAINED = 0, TOUCH_EXPLAINED = 1, TOUCH_EXPLAINED_AND_UNDERSTOOD = 2;
	private static int touchState = TOUCH_NOT_EXPLAINED;

	public static void act(SpriteBatch batch) {
		if (!Gdx.input.isTouched() && touchState < TOUCH_EXPLAINED_AND_UNDERSTOOD && EndlessMode.now > 3) {
			touchState = TOUCH_EXPLAINED;
			displayString(batch, Strings.TUTO_TOUCH, 1);
		}
		if (Gdx.input.isTouched() && touchState == TOUCH_EXPLAINED)
			touchState = TOUCH_EXPLAINED_AND_UNDERSTOOD;
		
		if (xpTaken && ! xpExplained) {
			xpDisplayTime += EndlessMode.delta;
													// from display_time to 1
			EndlessMode.delta = ((1 - (DISPLAY_TIME - xpDisplayTime) / DISPLAY_TIME) * EndlessMode.delta) * 0.8f;
			displayString(batch, Strings.TUTO_XP, (DISPLAY_TIME - xpDisplayTime) / DISPLAY_TIME);
			System.out.println((DISPLAY_TIME - xpDisplayTime) / DISPLAY_TIME);
			if (xpDisplayTime > DISPLAY_TIME)
				xpExplained = true;
		}
	}

	private static void displayString(SpriteBatch batch, String s, float alpha) {
		CSG.menuFontBlack.drawMultiLine(batch, s, ((CSG.screenWidth / 2) - CSG.menuFontBlack.getMultiLineBounds(s).width / 2) + 3, CSG.HEIGHT_8_10 - 3);
		CSG.menuFont.setColor(.32f, .52f, 0.99f, alpha);
		CSG.menuFont.drawMultiLine(batch, s, (CSG.screenWidth / 2) - CSG.menuFontBlack.getMultiLineBounds(s).width / 2, CSG.HEIGHT_8_10);
	}

	public static void xpTaken() {
		xpTaken = true;
	}

}
