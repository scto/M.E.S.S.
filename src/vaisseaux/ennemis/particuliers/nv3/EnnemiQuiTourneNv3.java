package vaisseaux.ennemis.particuliers.nv3;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import jeu.Stats;
import vaisseaux.armes.typeTir.Tirs;
import vaisseaux.ennemis.CoutsEnnemis;
import vaisseaux.ennemis.particuliers.nv1.QuiTourne;

public class EnnemiQuiTourneNv3 extends QuiTourne {
	
	public static Pool<EnnemiQuiTourneNv3> pool = Pools.get(EnnemiQuiTourneNv3.class);
	public static final float CADENCE_TIR = .07f;
	public static final Tirs TIR = new Tirs(CADENCE_TIR);
	
	@Override
	protected void free() {				pool.free(this);	}
	@Override
	public void invoquer() {			liste.add(pool.obtain());	}
	@Override
	protected void tir() {				TIR.tirEnRafale(this, 3, mort, maintenant, prochainTir);	}
	@Override
	protected int getPvMax() {			return Stats.PV_QUI_TOURNE3;	}
	@Override
	protected float getVitesse() {		return Stats.V_ENN_QUI_TOURNE3;	}
	@Override
	protected float getDemiVitesse() {	return Stats.DEMI_V_ENN_QUI_TOURNE3;	}
	@Override
	public int getXp() {				return CoutsEnnemis.EnnemiQuiTourneNv3.COUT;	}
}
