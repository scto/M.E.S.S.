package objets.ennemis.particuliers.nv3;

import objets.ennemis.CoutsEnnemis;
import objets.ennemis.particuliers.nv1.Insecte;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import jeu.Stats;


public class InsecteNv3 extends Insecte {
	
	public static final Pool<InsecteNv3> POOL = Pools.get(InsecteNv3.class);
	
	@Override
	protected void free() {			POOL.free(this);	}
	@Override
	public void invoquer() {		LISTE.add(POOL.obtain());	}
	@Override
	protected int getPvMax() {		return Stats.PV_INSECTE3;	}
	@Override
	public int getXp() {			return CoutsEnnemis.INSECTE3.COUT;	}
	@Override
	protected String getLabel() {	return getClass().toString();	}
}
