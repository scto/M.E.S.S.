package objets.ennemis.particuliers.nv3;

import objets.ennemis.CoutsEnnemis;
import objets.ennemis.particuliers.nv1.PorteRaisin;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import jeu.Stats;

public class EnnemiPorteRaisinNv3 extends PorteRaisin {

	public static Pool<EnnemiPorteRaisinNv3> pool = Pools.get(EnnemiPorteRaisinNv3.class);
	
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
		return Stats.PV_PORTE_RAISIN3;
	}

	@Override
	public int getXp() {
		return CoutsEnnemis.EnnemiPorteRaisinNv3.COUT;
	}
	
	@Override
	protected String getLabel() {			return getClass().toString();	}
}
