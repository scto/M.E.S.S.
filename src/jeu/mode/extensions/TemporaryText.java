package jeu.mode.extensions;

import jeu.CSG;
import jeu.mode.EndlessMode;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TemporaryText {

	private static final float DISPLAY_TIME = 5f;
	private float displayTime = 0;
	private final String text;
	
	public TemporaryText(String text) {
		this.text = text;
	}

	public boolean act(SpriteBatch batch, boolean slow) {
		displayTime += EndlessMode.delta;
		// from display_time to 1
		if (slow)
			EndlessMode.delta = ((1 - (DISPLAY_TIME - displayTime) / DISPLAY_TIME) * EndlessMode.delta) * 0.8f;
		displayString(batch, text, (DISPLAY_TIME - displayTime) / DISPLAY_TIME);
		if (displayTime > DISPLAY_TIME)
			return true;
		return false;
	}
	
	public static void displayString(SpriteBatch batch, String s, float alpha) {
//		CSG.menuFontBlack.drawMultiLine(batch, s, ((CSG.screenWidth / 2) - CSG.menuFontBlack.getMultiLineBounds(s).width / 2) + 3, CSG.HEIGHT_8_10 - 3);
		CSG.menuFontSmall.setColor(.32f, .52f, 0.99f, alpha);
		CSG.menuFontSmall.drawMultiLine(batch, s, (CSG.screenWidth / 2) - CSG.menuFontSmall.getMultiLineBounds(s).width / 2, CSG.HEIGHT_8_10);
	}

	public void reset() {
		displayTime = 0;
	}
}
