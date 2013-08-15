package vaisseaux.ennemis.particuliers.nv3;

import jeu.Stats;
import vaisseaux.ennemis.CoutsEnnemis;
import vaisseaux.ennemis.particuliers.nv1.Cylon;

public class EnnemiCylonNv3 extends Cylon {
	
	public EnnemiCylonNv3() {
		super();
		pv = Stats.PVMAX_CYLON3;
	}

	@Override
	public void reset() {
		super.reset();
		pv = Stats.PVMAX_CYLON3;
	}

	@Override
	public int getXp() {		return CoutsEnnemis.EnnemiCylonNv3.COUT;	}

}
