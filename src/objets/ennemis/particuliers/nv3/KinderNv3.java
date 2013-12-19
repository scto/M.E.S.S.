package objets.ennemis.particuliers.nv3;

import objets.ennemis.CoutsEnnemis;
import objets.ennemis.particuliers.nv1.Kinder;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import jeu.Stats;


public class KinderNv3 extends Kinder {
	
	public static final Pool<KinderNv3> POOL = Pools.get(KinderNv3.class);
	
	@Override
	protected void free() {		POOL.free(this);	}
	@Override
	public void invoquer() {		LISTE.add(POOL.obtain());	}
	@Override
	protected int getPvMax() {		return Stats.PV_KINDER3;	}
	@Override
	public int getXp() {		return CoutsEnnemis.KINDER3.COUT;	}
	@Override
	protected String getLabel() {			return getClass().toString();	}
}

