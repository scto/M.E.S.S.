package vaisseaux.ennemis.particuliers.nv3;

import jeu.Stats;
import vaisseaux.armes.typeTir.Tirs;
import vaisseaux.ennemis.CoutsEnnemis;
import vaisseaux.ennemis.particuliers.nv1.Toupie;

public class EnnemiToupieNv3 extends Toupie {

	public static final float CADENCE_TIR = .28f;
	public static final Tirs TIR = new Tirs(CADENCE_TIR);

	public EnnemiToupieNv3() {
		super();
		pv = Stats.PVMAX_TOUPIE3;
	}
	
	@Override
	public void reset() {
		super.reset();
		pv = Stats.PVMAX_TOUPIE3;
	}

	@Override
	protected void tir() {		TIR.tirBalayage(this, mort, maintenant, prochainTir);	}

	@Override
	public int getXp() {		return CoutsEnnemis.EnnemiToupieNv3.COUT;	}
}
