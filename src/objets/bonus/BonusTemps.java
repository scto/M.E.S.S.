package objets.bonus;

import jeu.CSG;
import jeu.EndlessMode;
import jeu.Stats;
import assets.AssetMan;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;
import com.badlogic.gdx.utils.Pool.Poolable;

public class BonusTemps extends Bonus implements Poolable{

	private float tps = 0;
	public static Pool<BonusTemps> pool = Pools.get(BonusTemps.class);
	private static int alreadyDropped = 1;
	private static final float SPEED = Stats.V_BONUS_TEMPS;

	void init(float x, float y) {
		posX = x  - HALF_WIDTH;
		posY = y  - HALF_WIDTH;
		list.add(this);
	}

	@Override
	boolean drawMeMoveMe(SpriteBatch batch) {
		tps += EndlessMode.delta;
		batch.draw(AssetMan.temps, posX, posY, WIDTH, WIDTH);
		posY -= SPEED * EndlessMode.delta;
		if (posX < CSG.DEMI_LARGEUR_ZONE_JEU)
			posX -= ( (tps*tps) * EndlessMode.delta);
		else
			posX += ( (tps*tps) * EndlessMode.delta);
		return true;
	}

	@Override
	public void taken() {
		EndlessMode.ralentir(1);
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
		if (cptBonus > TIME_FREQ * (alreadyDropped * alreadyDropped * INCREASE_FREQ)){
			pool.obtain().init(x, y);
			alreadyDropped++;
		}
	}

	public static void resetStats() {
		alreadyDropped = 1;
	}
}
