package menu;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Credits {
	
	private float posX = 0;
	private final String CREDIT = "                                                    DEVELOPER : JULIEN BERTOZZI,    GAME DESIGN : JULIEN BERTOZZI, CHRISTIAN BRUYERE, JEREMY DETRAUX, RUDY SCHOONENBURG   GRAPHICS : TYRIAN,    MUSIC : OLIVIER LAHAYE.    DONE WITH THE LIBGDX FRAMEWORK...         THANKS FOR PLAYING !";
	private final String PATCHNOTE = "                                                    PATCHNOTE 0.89 : NEW BACKGROUND.      ";
	private final String str;
	
	public Credits() {
		if (Math.random() > .5f) str = CREDIT;
		else str = PATCHNOTE;
	}

	public void render(SpriteBatch batch, float delta) {
		posX -= delta * 30;
		CSG.menuFontPetite.draw(batch, str, posX, CSG.menuFontPetite.getBounds(str).height + 2);
	}
}
