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
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;

import elements.generic.Player;

public class XP extends Bonus implements Poolable {

	public int valeur = 2;
	public static final Pool<XP> POOL = new Pool<XP>(30) {
		@Override
		protected XP newObject() {
			return new XP();
		}
	};
	public static final int WIDTH = (int) ((int) Stats.BONUS_WIDTH / 2.00f), HALF = WIDTH/2, WIDTH_INF = (int) (WIDTH + Stats.u), HALF_INF_WIDTH = WIDTH_INF / 2, WIDTH2 = WIDTH * 2;
	public final Vector2 direction = new Vector2();
	private float color, angle, inflate;
	private static final int WANDERER = 1;
	public static final int HOMMING = 2;
	public static final int INFLATE = 3;
	private static final int SHOOTING_STAR = 4;
	public int state;

	public void init(float x, float y, int xp) {
		x -= HALF;
		y -= HALF;
		x += CSG.R.nextGaussian() * Stats.u;
		y += CSG.R.nextGaussian() * Stats.u;
		valeur = xp;
		direction.x = (float) (EndlessMode.R.nextFloat() * Stats.u);
		direction.y = (float) (EndlessMode.R.nextGaussian() * Stats.u);
		pos.x = x - HALF_WIDTH;
		pos.y = y - HALF_WIDTH;
		pos.x = x;
		pos.y = y;
		limites();
		color = getColor(xp);
		state = WANDERER;
		inflate = 0;
		Bonus.XP_LIST.add(this);
		angle = CSG.R.nextFloat() * 360;
	}
	
	private float getColor(float xp) {
		return AssetMan.convertARGB(1, 0, xp / 50f, 1);
	}

	private void limites() {
		if (pos.x <= WIDTH) {
			pos.x = WIDTH;
			direction.x = Math.abs(direction.x); 
		} else if (pos.x > CSG.gameZoneWidth) {
			pos.x = CSG.gameZoneWidth;
			direction.x = -Math.abs(direction.x);
		}
		if (pos.y > CSG.SCREEN_HEIGHT) {
			pos.y = CSG.SCREEN_HEIGHT;
			direction.y = -Math.abs(direction.y);
		}
	}

	@Override
	public void reset() {									}
	@Override
	public void free() {							POOL.free(this);	}

	private static final Vector2 tmp = new Vector2();
	private static float tmpFloat;
	public static void act(Array<XP> xpList, SpriteBatch batch) {
		for (XP xp : xpList) {
			if (EndlessMode.alternate) {
				switch (xp.state) {
				case WANDERER:
					analyzeDistance(xp, HOMMING, Stats.LARGEUR_BOSS_SAT);
					xp.direction.x /= EndlessMode.UnPlusDelta;
					xp.direction.y -= EndlessMode.deltaMicroU;
					xp.limites();
					drawStandard(batch, xp, xp.angle);
					break;
				case HOMMING:
					xp.angle += xp.valeur;
					Physic.mvtToPlayer(xp.direction, xp.pos, Stats.U20 + xp.valeur*2, WIDTH, HALF);
					analyzeDistance(xp, INFLATE, Player.HEIGHT);
					drawStandard(batch, xp, xp.angle);
					break;
				case INFLATE:
					xp.angle += xp.valeur;
					xp.inflate += EndlessMode.delta * Stats.U20;
					tmpFloat = xp.inflate/2;
					drawMultipleTimes(batch, xp, xp.angle);
					if (xp.inflate >= WIDTH_INF) {
						xp.color = AssetMan.setAlpha(xp.color, 0.5f);
						xp.state = SHOOTING_STAR;
						xp.direction.x = CSG.screenHalfWidth - xp.pos.x;
						xp.direction.y = -xp.pos.y;
						xp.direction.nor();
						xp.direction.scl(Stats.UUU);
					}
					break;
				case SHOOTING_STAR:
					xp.angle += xp.valeur;
					drawStandard(batch, xp, xp.angle);
					break;
				}
			} else {
				switch (xp.state) {
				case WANDERER:					drawStandard(batch, xp, -xp.angle);					break;
				case HOMMING:					drawStandard(batch, xp, -xp.angle);					break;
				case INFLATE:
					tmpFloat = xp.inflate/2;
					drawMultipleTimes(batch, xp, xp.angle);
					break;
				case SHOOTING_STAR:
					drawAndMove(batch, xp, -xp.angle);
					drawAndMove(batch, xp, xp.angle);
					drawAndMove(batch, xp, -xp.angle);
					drawAndMove(batch, xp, xp.angle);
					drawAndMove(batch, xp, -xp.angle);
					drawAndMove(batch, xp, xp.angle);
					if (Math.abs(CSG.screenHalfWidth - xp.pos.x) < Stats.U && xp.pos.y < CSG.HEIGHT_DIV20) {
						taken(xp);
					}
					break;
				}
			}
			if (xp.pos.y + WIDTH_INF < 0) {
				XP_LIST.removeValue(xp, false);
				POOL.free(xp);
			}
		}
		batch.setColor(AssetMan.WHITE);
	}

	private static void taken(XP xp) {
		SoundMan.playBruitage(SoundMan.xp);
		EndlessMode.upScore(xp.valeur);
		XP_LIST.removeValue(xp, false);
		POOL.free(xp);
	}

	private static void drawAndMove(SpriteBatch batch, XP xp, float angle) {
		batch.setColor(xp.color);
		batch.draw(AssetMan.star, xp.pos.x, xp.pos.y, HALF_INF_WIDTH, HALF_INF_WIDTH, WIDTH_INF, WIDTH_INF, 1, 1, angle);
		move(xp);
	}

	private static void drawMultipleTimes(SpriteBatch batch, XP xp, float angle) {
//		batch.setColor(AssetMan.BLACK);
//		batch.draw(AssetMan.star, xp.pos.x - HALF, xp.pos.y - HALF, WIDTH, WIDTH, WIDTH2, WIDTH2, 1, 1, angle);
//		batch.draw(AssetMan.star, xp.pos.x - HALF, xp.pos.y - HALF, WIDTH, WIDTH, WIDTH2, WIDTH2, 1, 1, -angle);
		batch.setColor(xp.color);
		batch.draw(AssetMan.star, xp.pos.x - tmpFloat, xp.pos.y - tmpFloat, HALF + tmpFloat, HALF + tmpFloat, WIDTH + xp.inflate, WIDTH + xp.inflate, 1, 1, angle);
		batch.draw(AssetMan.star, xp.pos.x - tmpFloat, xp.pos.y - tmpFloat, HALF + tmpFloat, HALF + tmpFloat, WIDTH + xp.inflate, WIDTH + xp.inflate, 1, 1, -angle);
	}

	private static void drawStandard(SpriteBatch batch, XP xp, float angle) {
//		batch.setColor(AssetMan.BLACK);
//		batch.draw(AssetMan.star, xp.pos.x - HALF, xp.pos.y - HALF, WIDTH, WIDTH, WIDTH2, WIDTH2, 1, 1, angle);
		batch.setColor(xp.color);
		batch.draw(AssetMan.star, xp.pos.x, xp.pos.y, HALF, HALF, WIDTH, WIDTH, 1, 1, angle);
	}

	private static void move(XP xp) {
		xp.pos.x += xp.direction.x * EndlessMode.delta15;
		xp.pos.y += xp.direction.y * EndlessMode.delta15;
	}

	private static void analyzeDistance(XP xp, int state, int width) {
		tmp.x = xp.pos.x + HALF_WIDTH;
		tmp.y = xp.pos.y + HALF_WIDTH;
		if (Physic.distanceFromPlayer(tmp) < width)
			xp.state = state;
	}

	@Override
	void drawMeMoveMe(SpriteBatch batch) {
	}

	@Override
	protected TextureRegion getTexture() {
		return null;
	}

}
