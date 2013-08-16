package vaisseaux.ennemis.particuliers.nv3;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import jeu.Stats;
import vaisseaux.ennemis.CoutsEnnemis;
import vaisseaux.ennemis.particuliers.nv1.DeBase;

public class EnnemiDeBaseNv3 extends DeBase {
	
	public static Pool<EnnemiDeBaseNv3> pool = Pools.get(EnnemiDeBaseNv3.class);
	
	@Override
	protected void free() {
		pool.free(this);
	}

	@Override
	protected int getPvMax() {
		return Stats.PVMAX_DE_BASE_NV3;
	}
	
	@Override
	protected float getVitesse() {
		return Stats.VITESSE_MAX_DE_BASE_NV3;
	}

	@Override
	public int getXp() {		return CoutsEnnemis.EnnemiDeBaseNv3.COUT;	}
	
	@Override
	public void invoquer() {		
		liste.add(pool.obtain());
		liste.add(pool.obtain());
		liste.add(pool.obtain());
		liste.add(pool.obtain());
	}
}
