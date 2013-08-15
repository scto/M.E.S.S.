package vaisseaux.ennemis.particuliers.nv3;

import jeu.Stats;
import vaisseaux.ennemis.CoutsEnnemis;
import vaisseaux.ennemis.particuliers.nv1.PorteRaisin;

public class EnnemiPorteRaisinNv3 extends PorteRaisin {

	public EnnemiPorteRaisinNv3() {
		super();
		pv = Stats.PVMAX_PORTE_RAISIN3;
	}
	
	@Override
	public void reset() {
		super.reset();
		pv = Stats.PVMAX_PORTE_RAISIN3;
	}

	@Override
	public int getXp() {
		return CoutsEnnemis.EnnemiPorteRaisinNv3.COUT;
	}
}
