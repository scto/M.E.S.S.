package vaisseaux.ennemis.particuliers.nv3;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import jeu.Stats;
import vaisseaux.ennemis.CoutsEnnemis;
import vaisseaux.ennemis.particuliers.nv1.Insecte;


public class EnnemiInsecteNv3 extends Insecte {
	
	public static Pool<EnnemiInsecteNv3> pool = Pools.get(EnnemiInsecteNv3.class);
	
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
		return Stats.PV_INSECTE3;
	}

	@Override
	public int getXp() {
		return CoutsEnnemis.EnnemiInsecteNv3.COUT;
	}
}
