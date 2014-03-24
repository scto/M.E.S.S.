package elements.particular.bonuses;

import jeu.EndlessMode;
import jeu.Stats;
import assets.AssetMan;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;
import com.badlogic.gdx.utils.Pool.Poolable;

public class BonusStop extends BonusTimeMvt implements Poolable{

	public static final Pool<BonusStop> POOL = Pools.get(BonusStop.class);
	private static int alreadyDropped = 1;
	private static final float SPEED = Stats.V_BONUS_STOP;

	@Override
	public void taken() {
		EndlessMode.addBonusStop();
		super.taken();
	}
	
	@Override
	public void free() {
		POOL.free(this);
	}

	public static void mightAppear(float x, float y) {
		if (cptBonus > STOP_FREQ * (alreadyDropped * alreadyDropped * INCREASE_FREQ)) {
			POOL.obtain().init(x, y);
			alreadyDropped++;
		}
	}

	public static void resetStats() {
		alreadyDropped = 1;
	}

	@Override
	protected float getMvt() {
		return -(SPEED * tps);
	}

	@Override
	protected TextureRegion getTexture() {
		return AssetMan.stopBonus;
	}
}
