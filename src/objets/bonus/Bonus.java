package objets.bonus;

import jeu.Physique;
import jeu.Stats;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

public abstract class Bonus {
	

	public static Array<Bonus> list = new Array<Bonus>(30);
	public float posX, posY;
	public static final int WIDTH = (int) Stats.BONUS_WIDTH;
	public static final float HALF_WIDTH = Stats.BONUS_WIDTH / 2, WIDTH_MUL2 = WIDTH * 2;
	private static final float HITBOX = WIDTH * 2, HITBOX_MUL3 = HITBOX * 3;
	protected static final float INCREASE_FREQ = 1.35f;
	protected static final int TIME_FREQ = 5, 
			ADD_FREQ = (TIME_FREQ * 2)+1, 
			BOMB_FREQ = (int) ((TIME_FREQ * 5f)+13), 
			STOP_FREQ = (TIME_FREQ * 4)+6, 
			SHIELD_FREQ = (int) ((TIME_FREQ * 3.8f)+1);
	public static int cptBonus = 1;

	public static float DISPLAY_WIDTH = WIDTH * 1.8f;
	
	/**
	 * Affiches tous les bonus, les fait tourner et test la collision avec le joueur
	 * @param batch
	 */
	public static void drawAndMove(SpriteBatch batch) {
		for (Bonus b : list) {
			if (!b.drawMeMoveMe(batch))
				list.removeValue(b, true);
			if (Physique.vaisseauDansRectangle(b.posX - HITBOX, b.posY - HITBOX, HITBOX_MUL3, HITBOX_MUL3)) {	
				b.taken();
				list.removeValue(b, true);
			}
			if (Physique.toujoursAfficher(b.posX, b.posY, WIDTH) == false)
				list.removeValue(b, true);
		}
	}

	/**
	 * Test la collision, bouge et affiche
	 * @param batch
	 */
	abstract boolean drawMeMoveMe(SpriteBatch batch);

	/**
	 * Must also free the Bonus
	 */
	public abstract void taken();
	public abstract void free();
	
	public static void resetAll() {
		cptBonus = 1;
		for (Bonus b : list)
			b.free();
        Bonus.list.clear();
        BonusAdd.resetStats();
        BonusStop.resetStats();
        BonusTemps.resetStats();
        BonusBombe.resetStats();
        BonusBouclier.resetStats();
	}
	
	/**
	 * Might add a bonus. Always add XP
	 * @param x : The X center of the enemy
	 * @param y : The Y center
	 * @param xp
	 */
	public static void addBonus(float x, float y, int xp) {
		cptBonus += xp;
		BonusAdd.mightAppear(x, y);
		BonusStop.mightAppear(x, y);
		BonusTemps.mightAppear(x, y);
		BonusBombe.mightAppear(x, y);
		BonusBouclier.mightAppear(x, y);
		XP.pool.obtain().init(x, y, xp);
	}



}