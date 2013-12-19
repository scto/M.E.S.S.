package objets.ennemis.particuliers.nv3;

import objets.ennemis.CoutsEnnemis;
import objets.ennemis.particuliers.nv1.DeBase;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import jeu.Stats;

public class DeBaseNv3 extends DeBase {
	
	public static final Pool<DeBaseNv3> POOL = Pools.get(DeBaseNv3.class);
	
	@Override
	protected void free() {		POOL.free(this);	}
	@Override
	protected int getPvMax() {		return Stats.PV_DE_BASE_NV3;	}
	@Override
	protected float getVitesse() {		return Stats.V_ENN_DE_BASE_3;	}
	@Override
	public int getXp() {		return CoutsEnnemis.EnnemiDeBaseNv3.COUT;	}
	@Override
	public void invoquer() {		
		LISTE.add(POOL.obtain());
		LISTE.add(POOL.obtain());
		LISTE.add(POOL.obtain());
		LISTE.add(POOL.obtain());
	}
	@Override
	protected String getLabel() {			return getClass().toString();	}
}
