package objets.ennemis.particuliers.nv3;

import objets.ennemis.CoutsEnnemis;
import objets.ennemis.particuliers.nv1.Kinder;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import jeu.Stats;


public class EnnemiKinderNv3 extends Kinder {
	
	public static Pool<EnnemiKinderNv3> pool = Pools.get(EnnemiKinderNv3.class);
	
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
		return Stats.PV_KINDER3;
	}

	@Override
	public int getXp() {
		return CoutsEnnemis.EnnemiKinderNv3.COUT;
	}
}

