package vaisseaux.ennemis.particuliers.nv3;

import jeu.Stats;
import vaisseaux.armes.typeTir.Tirs;
import vaisseaux.ennemis.CoutsEnnemis;
import vaisseaux.ennemis.particuliers.nv1.Laser;


public class EnnemiLaserNv3 extends Laser {
	
	public static final Tirs TIR = new Tirs(0.8f);
		
	@Override
	public void reset() {
		super.reset();
		pv = Stats.PVMAX_LASER3;
	}

	public EnnemiLaserNv3() {
		super();
		pv = Stats.PVMAX_LASER3;
	}
	
	@Override
	protected void tir() {		TIR.tirToutDroit(this, mort, maintenant, prochainTir);	}

	@Override
	public int getXp() {		return CoutsEnnemis.EnnemiLaserNv3.COUT;	}
}
