package elements.particular.bonuses;

import jeu.Stats;
import assets.AssetMan;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;
import com.badlogic.gdx.utils.Pool.Poolable;

import elements.generic.Player;

public class BonusAdd extends BonusTimeMvt implements Poolable {

	private static int alreadyDropped = 1;
	public static final Pool<BonusAdd> POOL = Pools.get(BonusAdd.class);
	private static final float SPEED = Stats.V_BONUS_ADD;

	@Override
	public void taken() {
		Player.rajoutAdd();
		super.taken();
	}
	
	@Override
	public void free() {
		POOL.free(this);
	}

	public static void mightAppear(float x, float y) {
		if (cptBonus > ADD_FREQ * (alreadyDropped * alreadyDropped * INCREASE_FREQ)) {
			POOL.obtain().init(x, y);
			alreadyDropped++;
		}
	}

	public static void resetStats() {
		alreadyDropped = 1;
	}

	@Override
	protected TextureRegion getTexture() {
		return AssetMan.add;
	}

	@Override
	protected float getMvt() {
		return (-SPEED + (tps));
	}
}
