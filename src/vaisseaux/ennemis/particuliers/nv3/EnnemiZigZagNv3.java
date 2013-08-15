package vaisseaux.ennemis.particuliers.nv3;

import jeu.Stats;
import vaisseaux.ennemis.CoutsEnnemis;
import vaisseaux.ennemis.particuliers.nv1.ZigZag;

public class EnnemiZigZagNv3 extends ZigZag {
	
	@Override
	public void reset() {
		pv = Stats.PVMAX_ZIGZAG_NV3;
		super.reset();
	}
	
	public EnnemiZigZagNv3() {
		super();
		pv = Stats.PVMAX_ZIGZAG_NV3;
	}

	@Override
	public int getXp() {
		return CoutsEnnemis.EnnemiZigZagNv3.COUT;
	}
}
