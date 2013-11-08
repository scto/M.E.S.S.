package menu;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Credits {
	
	private float posX = 0;
	private final String CREDIT = "                                                    DEVELOPER : JULIEN BERTOZZI,    GAME DESIGN : JULIEN BERTOZZI, CHRISTIAN BRUYERE       GRAPHICS : TYRIAN,    MUSIC : OLIVIER LAHAYE.    DONE WITH THE LIBGDX FRAMEWORK...         THANKS FOR PLAYING !";
	private final String PATCHNOTE = "                                                    PATCHNOTE 0.903 : BUG FIXES.     YOU GAIN MORE EXPERIENCE WITH THE HARDEST DIFFICULTY.    THERE IS A NEW LEADERBOARD, WAY BETTER THAN THE OLD ONE I HOPE, I'M SORRY I COULD NOT IMPORT THE OLD SCORES SO IT IS A FRESH START AND A GREAT OCCASION TO GET YOUR NAME ON THE LIST ! I WILL CERTAINLY TRY MY BEST....     I WANT TO THANK ALL YOU ALL FOR TESTING AND PLAYING, IT IS GREATLY APPRECIATED      ";
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
