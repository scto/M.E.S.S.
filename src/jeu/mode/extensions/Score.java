package jeu.mode.extensions;

import jeu.CSG;
import jeu.CharSeq;
import jeu.Stats;
import jeu.mode.EndlessMode;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Score {

	private final static char[] mockStrScore = {'0','.','0','0','0','.','0','0','0'};
	public static CharSeq strScore = new CharSeq(mockStrScore);
	private static float rScore, nextScore;
	private static int multi, scoreToProcess, currentDigit;
	public static float score = 0;
	private static final int FONT_HEIGHT = CSG.HEIGHT_DIV10/5;
	private static final String[] STR_MULTI = {"0 :/", "X1", "X2", "X3", "X4", "X5", "X6", "X7", "X8", "X9", "X10"};
	
	// limits before reduicing : multi / 20
	//	1		2		3		4		5		6		7		8		9		10
	// Down limit
	// 	0,05	0,10	0,15	0,20	0,25	0,30	0,35	0,40	0,45	0,50
	//	rScore update for XP = 20
	//	20		13,33	6,66	2,66	0,88	0,25	0,06	0,01	0,002	0,0005

	
	public static void upScore(float valeur) {
		if (!EndlessMode.lost) {
			valeur /= 5f;
			rScore += (valeur / ((float) (multi + 10f) * 3f));
			if (rScore > 1) {
				if (multi < 10) {
					multi++;
					rScore = 0.65f;
				} else {
					rScore = 1;
				}
			}
			score += valeur * multi;
			updateStringScore();
			Tutorial.xpTaken();
		}
	}

	public static void init() {
		score = 0;
		updateStringScore();
		rScore = 0;
		multi = 1;
		nextScore = 0;
	}
	
	public static void act(float now, boolean lost, boolean triggerStop) {
		if (nextScore < now && !lost) {
			score += 5;
			updateStringScore();
			nextScore = now + 1;
		}
		if (rScore > 0 && !triggerStop) {
			rScore -= EndlessMode.deltaDiv2;
			if (rScore < (float) multi / 20f) {
				if (multi > 1) {
					multi--;
					rScore = 1;
				} else {
					rScore = 0;
				}
			}
		}
		if (rScore < 0.15f) {
			CSG.scoreFont.setColor(0, .35f, 1, 1);
		} else {
			CSG.scoreFont.setColor(0, rScore, 1, 1);
		}
		CSG.scoreFont.setScale(CSG.originalScoreFontScale + rScore/2);
//		CSG.outlineScoreFont.setScale((CSG.originalScoreFontScale + rScore) * 1.05f);
	}

	private static void updateStringScore() {
		scoreToProcess = (int) score;
		
		processDigit(1000000, 0);
		// M
		processDigit(100000, 2);
		processDigit(10000, 3);
		processDigit(1000, 4);
		// K
		processDigit(100, 6);
		processDigit(10, 7);
		processDigit(1, 8);
	}

	/**
	 * 
	 */
	protected static void processDigit(int digitFactor, int digitPlace) {
		currentDigit = scoreToProcess / digitFactor;
		strScore.charSet(digitPlace, currentDigit);
		scoreToProcess -= currentDigit * digitFactor;
	}

	public static int getRoundScore() {
		return (int)score;
	}

	public static void draw(SpriteBatch batch, boolean lost) {
//		CSG.outlineScoreFont.draw(batch, STR_MULTI[multi], Stats.U, FONT_HEIGHT + CSG.outlineScoreFont.getBounds(strMulti).height/2);
		CSG.scoreFont.draw(batch, STR_MULTI[multi], Stats.U, FONT_HEIGHT + CSG.scoreFont.getBounds(STR_MULTI[multi]).height/2);
		if (!lost) {
			// bottom score
//			CSG.outlineScoreFont.draw(batch, strScore, (EndlessMode.cam.position.x) - CSG.outlineScoreFont.getBounds(strScore).width/2, FONT_HEIGHT + CSG.outlineScoreFont.getBounds(strScore).height/2);
			CSG.scoreFont.draw(batch, strScore, CSG.screenHalfWidth - CSG.scoreFont.getBounds(strScore).width/2, FONT_HEIGHT + CSG.scoreFont.getBounds(strScore).height/2);
		}
	}

	public static void lost(boolean persist) {
//		strScore = "Score : " + (int) score;
		CSG.profile.xpDispo += score;
		CSG.profilManager.persist();
	}
}

