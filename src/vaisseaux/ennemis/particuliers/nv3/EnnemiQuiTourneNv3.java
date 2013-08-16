package vaisseaux.ennemis.particuliers.nv3;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import jeu.Stats;
import vaisseaux.ennemis.CoutsEnnemis;
import vaisseaux.ennemis.particuliers.nv1.QuiTourne;

public class EnnemiQuiTourneNv3 extends QuiTourne {
	
	public static Pool<EnnemiQuiTourneNv3> pool = Pools.get(EnnemiQuiTourneNv3.class);
	
	@Override
	protected void free() {
		pool.free(this);
	}
	
	@Override
	public void invoquer() {
		liste.add(pool.obtain());
	}
	
	@Override
	protected int getPvMax() {
		return Stats.PVMAX_QUI_TOURNE3;
	}
	
	@Override
	protected float getVitesse() {
		return Stats.VITESSE_QUI_TOURNE3;
	}
	
	@Override
	protected float getDemiVitesse() {
		return Stats.DEMI_VITESSE_QUI_TOURNE3;
	}

	@Override
	public int getXp() {		return CoutsEnnemis.EnnemiQuiTourneNv3.COUT;	}
}
