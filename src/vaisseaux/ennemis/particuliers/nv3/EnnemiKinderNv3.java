package vaisseaux.ennemis.particuliers.nv3;

import jeu.Stats;
import vaisseaux.ennemis.CoutsEnnemis;
import vaisseaux.ennemis.particuliers.nv1.Kinder;


public class EnnemiKinderNv3 extends Kinder {

	public EnnemiKinderNv3() {
		super();
		pv = Stats.PVMAX_KINDER3;
	}
	
	@Override
	public void reset() {
		super.reset();
		pv = Stats.PVMAX_KINDER3;
	}

	@Override
	public int getXp() {
		return CoutsEnnemis.EnnemiKinderNv3.COUT;
	}
}

