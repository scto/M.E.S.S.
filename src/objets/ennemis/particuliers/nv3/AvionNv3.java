package objets.ennemis.particuliers.nv3;

import objets.ennemis.CoutsEnnemis;
import objets.ennemis.particuliers.nv1.Avion;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import jeu.Stats;

public class AvionNv3 extends Avion {
	
	public static Pool<AvionNv3> pool = Pools.get(AvionNv3.class);
	
	@Override
	protected void free() {
		pool.free(this);
	}
	@Override
	public void invoquer() {
		LISTE.add(pool.obtain());
	}
	
	@Override
	protected int getPvMax() {			return Stats.PV_AVION3;	}
	@Override
	protected float getVitesse() {		return Stats.V_ENN_AVION3;	}
	@Override
	public float getModifVitesse() {	return 1.3f;		}
	@Override
	public int getXp() {				return CoutsEnnemis.EnnemiAvionNv3.COUT;	}
	
}
