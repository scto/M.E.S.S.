package vaisseaux.ennemis.particuliers.nv3;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import jeu.Stats;
import vaisseaux.ennemis.CoutsEnnemis;
import vaisseaux.ennemis.particuliers.nv1.Kinder;


public class EnnemiKinderNv3 extends Kinder {
	
	public static Pool<EnnemiKinderNv3> pool = Pools.get(EnnemiKinderNv3.class);
	
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
		return Stats.PV_KINDER3;
	}

	@Override
	public int getXp() {
		return CoutsEnnemis.EnnemiKinderNv3.COUT;
	}
}

