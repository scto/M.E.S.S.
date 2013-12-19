package objets.ennemis.particuliers.nv3;

import objets.ennemis.CoutsEnnemis;
import objets.ennemis.particuliers.nv1.Avion;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import jeu.Stats;

public class AvionNv3 extends Avion {
	
	public static final Pool<AvionNv3> POOL = Pools.get(AvionNv3.class);
	
	@Override
	protected void free() {		POOL.free(this);	}
	@Override
	public void invoquer() {		LISTE.add(POOL.obtain());	}
	@Override
	protected int getPvMax() {			return Stats.PV_AVION3;	}
	@Override
	protected float getVitesse() {		return Stats.V_ENN_AVION3;	}
	@Override
	public float getModifVitesse() {	return 1.3f;		}
	@Override
	public int getXp() {				return CoutsEnnemis.AVION3.COUT;	}
	@Override
	protected String getLabel() {			return getClass().toString();	}
	
}
