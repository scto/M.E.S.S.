package elements.generic.enemies.individual.lvl3;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.enemies.individual.lvl1.ShooterFrag;


public class ShooterFrag3 extends ShooterFrag {
	
	public static final Pool<ShooterFrag3> POOL = Pools.get(ShooterFrag3.class);
	private static final float FIRERATE = 1f * MOD_FIRERATE;
	
	@Override 	public float getFirerate() {		return FIRERATE;				}
	@Override	public void free() {				POOL.free(this);				}
}
