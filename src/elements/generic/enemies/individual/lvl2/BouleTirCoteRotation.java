package elements.generic.enemies.individual.lvl2;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

public class BouleTirCoteRotation extends BouleTirCote {

	public static final Pool<BouleTirCoteRotation> POOL = Pools.get(BouleTirCoteRotation.class);
	
	@Override
	protected void shoot() {
		angle += 10;
		super.shoot(angle);
	}
	
	@Override	public int getNumberOfShots() {			return 5;	}
	@Override	public void free() {					POOL.free(this);	}
}
