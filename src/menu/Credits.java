package menu;

import jeu.CSG;
import jeu.Stats;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Credits {
	
	private float posX = 0;
	private final static String BEER = "WE GOT BEERS FROM BRAZIL, POLAND, US, KOREA, JAPAN AND BELGIUM !";
	private final static String ADV = "                                 IF YOU LIKE THIS GAME, PLEASE RATE IT, HELP ME MAKING IT BETTER AND TALK ABOUT IT ! THANKS FOR YOUR DONATIONS, " + BEER;
	private final static String END = "                             THANKS FOR YOUR DONATIONS. " + BEER + " AND THANKS FOR PLAYING, SERIOUSLY YOU ARE AWESOME, I AM REALLY HAPPY THAT YOU LIKE IT !" +
	ADV;
	private final static String CREDIT = "                DEVELOPER : JULIEN BERTOZZI,     GAME DESIGN : JULIEN BERTOZZI, CHRISTIAN BRUYERE, THE COMMUNITY     GRAPHICS : TYRIAN, ELSIE STARBROOK, JULIEN BERTOZZI, A LOT OF PARTICLES     MUSIC : OLIVIER LAHAYE.     DONE WITH THE LIBGDX FRAMEWORK." +
											END;
	private final static String PATCHNOTE = 
"                                                    "
+ "PATCHNOTE 0.926 :  Better explosions and your bomb is more powerful ! Everything is a little sharper. New sprites !!! They actually are the first from our artist Emma Payne ! And sorry for the delay of this new version, I've been sick and basically lost a week. Sorry, I had to reset all leaderboards" + 
									 		END;
	private final String str;
	
	public Credits() {
		final double d = Math.random();
		if (d < .3)	str = CREDIT;
		else if (d < .6)	str = ADV; 
		else 				str = PATCHNOTE;
	}
	
	public Credits(String s) {
		str = s;
	}

	public void render(SpriteBatch batch, float delta) {
		posX -= delta * Stats.UUU;
		CSG.menuFontPetite.draw(batch, str, posX, CSG.menuFontPetite.getBounds(str).height + 2);
	}
}
