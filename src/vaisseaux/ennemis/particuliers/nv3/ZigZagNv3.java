package vaisseaux.ennemis.particuliers.nv3;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import jeu.Stats;
import vaisseaux.ennemis.CoutsEnnemis;
import vaisseaux.ennemis.particuliers.nv1.ZigZag;

public class ZigZagNv3 extends ZigZag {

	public static Pool<ZigZagNv3> pool = Pools.get(ZigZagNv3.class);
	
	@Override
	protected void free() {			pool.free(this);	}
	@Override
	public void invoquer() {		liste.add(pool.obtain());	}
	@Override
	protected int getPvMax() {		return Stats.PV_ZIGZAG_NV3;	}
	@Override
	public int getXp() {			return CoutsEnnemis.EnnemiZigZagNv3.COUT;	}
	@Override
	protected float getVitesse() {	return Stats.V_ENN_ZIGZAG_NV3;	}
}
