package vaisseaux.ennemis.particuliers.nv3;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import jeu.Stats;
import vaisseaux.ennemis.CoutsEnnemis;
import vaisseaux.ennemis.particuliers.nv1.Avion;

public class AvionNv3 extends Avion {
	
	public static Pool<AvionNv3> pool = Pools.get(AvionNv3.class);
	
	@Override
	protected void free() {
		pool.free(this);
	}
	@Override
	public void invoquer() {
		liste.add(pool.obtain());
	}
	
	@Override
	protected int getPvMax() {			return Stats.PVMAX_AVION3;	}
	@Override
	protected float getVitesse() {		return Stats.VITESSE_AVION3;	}
	@Override
	public float getModifVitesse() {	return 1.3f;		}
	@Override
	public int getXp() {				return CoutsEnnemis.EnnemiAvionNv3.COUT;	}
	
}
