package elements.particular.bonuses;

import jeu.EndlessMode;
import jeu.Stats;
import assets.AssetMan;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;
import com.badlogic.gdx.utils.Pool.Poolable;

import elements.particular.particles.Particles;

public class BonusBombe extends BonusTimeMvt implements Poolable{

	public static final Pool<BonusBombe> POOL = Pools.get(BonusBombe.class);
	private static int alreadyDropped = 1;
	private static final float SPEED = Stats.V_BONUS_BOMBE;
	
	
	@Override
	public void taken() {
		Particles.bomb(this);
		EndlessMode.ajoutBombe();
		super.taken();
	}
	
	@Override
	public void free() {
		POOL.free(this);
	}
	
	public static void mightAppear(float x, float y) {
		if (cptBonus > BOMB_FREQ * (alreadyDropped * alreadyDropped * INCREASE_FREQ)){
			POOL.obtain().init(x, y);
			alreadyDropped++;
		}
	}

	public static void resetStats() {
		alreadyDropped = 1;
	}

	@Override
	protected float getMvt() {
		return -(SPEED + (tps*tps));
	}

	@Override
	protected TextureRegion getTexture() {
		return AssetMan.bomb;
	}
}
