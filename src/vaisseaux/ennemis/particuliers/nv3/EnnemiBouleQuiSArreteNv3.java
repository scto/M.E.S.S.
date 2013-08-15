package vaisseaux.ennemis.particuliers.nv3;

import jeu.Stats;
import vaisseaux.ennemis.CoutsEnnemis;
import vaisseaux.ennemis.particuliers.nv1.BouleQuiSArrete;

public class EnnemiBouleQuiSArreteNv3 extends BouleQuiSArrete {
	
	@Override
	public void reset() {
		super.reset();
		pv = Stats.PVMAX_BOULE_QUI_SARRETE3;
	}
	
	public EnnemiBouleQuiSArreteNv3() {
		super();
		pv = Stats.PVMAX_BOULE_QUI_SARRETE3;
	}

	@Override
	public int getXp() {		return CoutsEnnemis.EnnemiBouleQuiSArreteNv3.COUT;	}
	
}
