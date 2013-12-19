package objets.ennemis.particuliers.nv3;

import objets.ennemis.CoutsEnnemis;
import objets.ennemis.particuliers.nv1.Cylon;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import jeu.Stats;

public class CylonNv3 extends Cylon {
	
	public static final Pool<CylonNv3> POOL = Pools.get(CylonNv3.class);
	
	@Override
	protected void free() {		POOL.free(this);	}
	@Override
	public void invoquer() {		LISTE.add(POOL.obtain());	}
	@Override
	protected int getPvMax() {		return Stats.PV_CYLON3;	}
	@Override
	public int getXp() {		return CoutsEnnemis.CYLON3.COUT;	}
	@Override
	protected String getLabel() {			return getClass().toString();	}

}
