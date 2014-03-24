package elements.particular.bonuses;

import jeu.CSG;
import jeu.EndlessMode;
import jeu.Physic;
import jeu.Stats;
import assets.AssetMan;
import assets.SoundMan;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import elements.generic.enemies.Enemy;

public abstract class Bonus {
	

	public static final Array<Bonus> LIST = new Array<Bonus>(40), TAKEN = new Array<Bonus>(16);
	protected final Vector2 pos = new Vector2();
	public static final int WIDTH = (int) Stats.BONUS_WIDTH;
	public static final int HALF_WIDTH = (int) (Stats.BONUS_WIDTH / 2), WIDTH_MUL2 = WIDTH * 2, DISPLAY_WIDTH = (int) (WIDTH * 1.8f);
	private static final float HITBOX = HALF_WIDTH, HITBOX_TOP_RIGHT = (HITBOX * 2) + WIDTH;
	protected static final int TIME_FREQ = 8, 
			ADD_FREQ = (TIME_FREQ * 2)+1, 
			BOMB_FREQ = (int) ((TIME_FREQ * 5.9f)+26), 
			STOP_FREQ = (int) ((TIME_FREQ * 6.7f)+50), 
			SHIELD_FREQ = (int) ((TIME_FREQ * 3.8f)+1);
	protected static final float INCREASE_FREQ = 1.35f;
	public static int cptBonus = 1;
	private static final int XPMINN = 10;
	private float timeTaken;
	private static float tmp, tmpY;
	private static final float TRANSPARENCE = AssetMan.convertARGB(0.45f, .8f, 1, 1);
	protected static boolean alternate = false;
	private static int tmpXp;
	
	/**
	 * Affiches tous les bonus, les fait tourner et test la collision avec le joueur
	 * @param batch
	 */
	public static void drawAndMove(SpriteBatch batch) {
		batch.setColor(TRANSPARENCE);
		for (final Bonus b : TAKEN) {
			b.timeTaken += EndlessMode.deltaU * b.timeTaken;
			tmp = b.timeTaken / 2;
			batch.draw(b.getTexture(), b.pos.x - tmp, b.pos.y - tmp, WIDTH + b.timeTaken, WIDTH + b.timeTaken);
			if (b.timeTaken > Stats.U5) {
				TAKEN.removeValue(b, true);
				b.free();
			}
		}
		batch.setColor(AssetMan.WHITE);
		for (final Bonus b : LIST) {
			b.drawMeMoveMe(batch);
			b.detectPris();
			if (Physic.isOnScreen(b.pos.x, b.pos.y, WIDTH) == false) {
				LIST.removeValue(b, true);
				b.free();
			}
		}
		alternate = !alternate;
	}

	private void detectPris() {
		if (Physic.isShipInRect(pos.x - HITBOX, pos.y - HITBOX, HITBOX_TOP_RIGHT, HITBOX_TOP_RIGHT)) {	
			taken();
			LIST.removeValue(this, true);
		}
	}

	/**
	 * Test la collision, bouge et affiche
	 * @param batch
	 */
	abstract void drawMeMoveMe(SpriteBatch batch);

	/**
	 * Must also free the Bonus
	 */
	public void taken() {
		SoundMan.playBruitage(SoundMan.bonusTaken);
		timeTaken = 0.05f;
		TAKEN.add(this);
	}
	public abstract void free();
	
	public static void resetAll() {
		cptBonus = 1;
		for (final Bonus b : LIST)
			b.free();
		for (final Bonus b : TAKEN)
			b.free();
		Bonus.TAKEN.clear();
        Bonus.LIST.clear();
        BonusAdd.resetStats();
        BonusStop.resetStats();
        BonusBombe.resetStats();
        BonusBouclier.resetStats();
	}
	
	/**
	 * Might add a bonus. Always add XP
	 * @param x : The X center of the enemy
	 * @param y : The Y center
	 * @param xp
	 */
	public static void addBonus(Enemy e) {
		cptBonus += e.getValeurBonus();
		tmp = e.pos.x + e.getHalfWidth();
		tmpY = e.pos.y + e.getHalfHeight();
		BonusAdd.mightAppear(tmp, tmpY);
		BonusStop.mightAppear(tmp, tmpY);
		BonusBombe.mightAppear(tmp, tmpY);
		BonusBouclier.mightAppear(tmp, tmpY);
		
		if (EndlessMode.modeDifficulte > 1) {
			addXp(e.getXp(), tmp, tmpY);
		} else { 
			EndlessMode.score += e.getXp();
		}
	}

	public static void addXp(int i, float tmp2, float tmpY2) {
		tmpXp = i;
		while (tmpXp > 0) {
			if (tmpXp > XPMINN) 	XP.POOL.obtain().init(tmp2, tmpY2, XPMINN);
			else					XP.POOL.obtain().init(tmp2, tmpY2, tmpXp);
			tmpXp -= XPMINN;
		}
	}

	protected void init(float x, float y) {
		if (y > CSG.SCREEN_HEIGHT)
			y = CSG.SCREEN_HEIGHT;
		pos.x = x - HALF_WIDTH;
		pos.y = y - HALF_WIDTH;
		LIST.add(this);
	}
	protected abstract TextureRegion getTexture();
}