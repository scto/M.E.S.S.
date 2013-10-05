package objets.ennemis.particuliers.nv3;

import objets.ennemis.CoutsEnnemis;
import objets.ennemis.particuliers.nv1.Cylon;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import jeu.Stats;

public class EnnemiCylonNv3 extends Cylon {
	
	public static Pool<EnnemiCylonNv3> pool = Pools.get(EnnemiCylonNv3.class);
	
	@Override
	protected void free() {
		pool.free(this);
	}
	@Override
	public void invoquer() {
		LISTE.add(pool.obtain());
	}
	
	@Override
	protected int getPvMax() {
		return Stats.PV_CYLON3;
	}

	@Override
	public int getXp() {		return CoutsEnnemis.EnnemiCylonNv3.COUT;	}

}
