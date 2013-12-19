package objets.ennemis.particuliers.nv3;

import objets.ennemis.CoutsEnnemis;
import objets.ennemis.particuliers.nv1.PorteRaisin;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import jeu.Stats;

public class PorteRaisinNv3 extends PorteRaisin {

	public static final Pool<PorteRaisinNv3> POOL = Pools.get(PorteRaisinNv3.class);
	
	@Override
	protected void free() {		POOL.free(this);	}
	@Override
	public void invoquer() {		LISTE.add(POOL.obtain());	}
	@Override
	protected int getPvMax() {		return Stats.PV_PORTE_RAISIN3;	}
	@Override
	public int getXp() {		return CoutsEnnemis.PORTE_RAISIN_3.COUT;	}
	@Override
	protected String getLabel() {			return getClass().toString();	}
}
