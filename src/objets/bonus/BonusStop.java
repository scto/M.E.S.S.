package objets.bonus;

import jeu.EndlessMode;
import jeu.Stats;
import assets.AssetMan;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;
import com.badlogic.gdx.utils.Pool.Poolable;

public class BonusStop extends Bonus implements Poolable{

	public static Pool<BonusStop> pool = Pools.get(BonusStop.class);
	private static int alreadyDropped = 1;
	private static final float SPEED = Stats.V_BONUS_STOP;
	private float tps = 0;

	public void init(float x, float y) {
		posX = x - HALF_WIDTH;
		posY = y - HALF_WIDTH;
		list.add(this);
	}

	@Override
	boolean drawMeMoveMe(SpriteBatch batch) {
		tps += EndlessMode.delta;
		batch.draw(AssetMan.bonusetoile, posX, posY, WIDTH, WIDTH);
		// Le fait descendre
		posY += -(SPEED + (tps*15)) * EndlessMode.delta;
		return true;
	}

	@Override
	public void taken() {
		EndlessMode.addBonusStop();
		pool.free(this);
	}
	
	@Override
	public void reset() {
		tps = 0;
	}

	@Override
	public void free() {
		pool.free(this);
	}

	public static void mightAppear(float x, float y) {
		if (cptBonus > STOP_FREQ * (alreadyDropped * alreadyDropped * INCREASE_FREQ)) {
			pool.obtain().init(x, y);
			alreadyDropped++;
		}
	}

	public static void resetStats() {
		alreadyDropped = 1;
	}
}
