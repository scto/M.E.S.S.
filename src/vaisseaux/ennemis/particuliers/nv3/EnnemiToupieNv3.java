package vaisseaux.ennemis.particuliers.nv3;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import jeu.Stats;
import vaisseaux.armes.typeTir.Tirs;
import vaisseaux.ennemis.CoutsEnnemis;
import vaisseaux.ennemis.particuliers.nv1.Toupie;

public class EnnemiToupieNv3 extends Toupie {

	public static final float CADENCE_TIR = .28f;
	public static final Tirs TIR = new Tirs(CADENCE_TIR);

	public static Pool<EnnemiToupieNv3> pool = Pools.get(EnnemiToupieNv3.class);
	
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
		return Stats.PV_TOUPIE3;
	}
	
	@Override
	protected void tir() {		TIR.tirBalayage(this, mort, maintenant, prochainTir);	}

	@Override
	public int getXp() {		return CoutsEnnemis.EnnemiToupieNv3.COUT;	}
}
