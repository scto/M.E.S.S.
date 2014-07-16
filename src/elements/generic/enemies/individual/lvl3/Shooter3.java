package elements.generic.enemies.individual.lvl3;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.enemies.individual.lvl1.Shooter;

public class Shooter3 extends Shooter {
	
	public static final Pool<Shooter3> POOL = Pools.get(Shooter3.class);
	private static final float FIRERATE = Shooter.FIRERATE * 0.4f  * MOD_FIRERATE;
	private int shotNumber = 0;
	
	@Override
	protected void shoot() {
		super.shoot();
		interval(0);
	}
	protected void interval(int init) {
		if (++shotNumber > 2) {
			shotNumber = init;
			nextShot += FIRERATE * 2;
		}
	}
	@Override	public void free() {				POOL.free(this);				}
	@Override	public float getFirerate() {		return FIRERATE;				}
}
