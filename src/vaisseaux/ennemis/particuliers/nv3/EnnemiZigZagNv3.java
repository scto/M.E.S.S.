package vaisseaux.ennemis.particuliers.nv3;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import jeu.Stats;
import vaisseaux.ennemis.CoutsEnnemis;
import vaisseaux.ennemis.particuliers.nv1.ZigZag;

public class EnnemiZigZagNv3 extends ZigZag {

	public static Pool<EnnemiZigZagNv3> pool = Pools.get(EnnemiZigZagNv3.class);
	
	@Override
	protected void free() {
		pool.free(this);
	}
	
	@Override
	public void invoquer() {
		liste.add(pool.obtain());
	}
	
	@Override
	protected int getPvMax() {
		return Stats.PVMAX_ZIGZAG_NV3;
	}
	@Override
	public int getXp() {
		return CoutsEnnemis.EnnemiZigZagNv3.COUT;
	}
}
