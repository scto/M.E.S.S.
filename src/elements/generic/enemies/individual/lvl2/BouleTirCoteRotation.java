package elements.generic.enemies.individual.lvl2;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import jeu.Stats;

public class BouleTirCoteRotation extends BouleTirCote {

	public static final Pool<BouleTirCoteRotation> POOL = Pools.get(BouleTirCoteRotation.class);
	private static final float FIRERATE = .15f, SPEED = Stats.V_BOULE_TIR_COTE * 0.75f;
	
	@Override
	protected void shoot() {
		angle += 10;
		super.shoot(angle);
	}
	
	@Override	public int getNumberOfShots() {			return 5;	}
	@Override	public float getFirerate() {			return FIRERATE;	}
	@Override	public float getSpeed() {				return SPEED;	}
	@Override	public int getXp() {					return 61;	}
	@Override	public int getBonusValue() {			return 70;	}
	@Override	public void free() {					POOL.free(this);	}
}
