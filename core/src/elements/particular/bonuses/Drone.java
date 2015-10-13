package elements.particular.bonuses;

import jeu.Stats;
import assets.AssetMan;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;
import com.badlogic.gdx.utils.Pool.Poolable;

import elements.particular.Player;

public class Drone extends BonusTimeMvt implements Poolable {

	private static int alreadyDropped = 1;
	public static final Pool<Drone> POOL = Pools.get(Drone.class);
	private static final float SPEED = Stats.DRONE_BONUS_SPEED;

	@Override
	public void taken() {
		Player.addDrone();
		super.taken();
	}
	

	public static void mightAppear(float x, float y) {
		if (cptBonus > ADD_FREQ * (alreadyDropped * alreadyDropped * INCREASE_FREQ)) {
			POOL.obtain().init(x, y);
			alreadyDropped++;
		}
	}

	public static void resetStats() {						alreadyDropped = 1;			}
	@Override	public void free() {						POOL.free(this);			}
	@Override	protected TextureRegion getTexture() {		return AssetMan.add;		}
	@Override	protected float getMvt() {					return (-SPEED + (tps));	}
}
