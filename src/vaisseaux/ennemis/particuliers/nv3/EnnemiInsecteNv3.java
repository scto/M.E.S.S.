package vaisseaux.ennemis.particuliers.nv3;

import jeu.Stats;
import vaisseaux.ennemis.CoutsEnnemis;
import vaisseaux.ennemis.particuliers.nv1.Insecte;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class EnnemiInsecteNv3 extends Insecte {
	
	public static TextureRegion getTexture(int pv) {
		if (pv < Stats.PVMAX_INSECTE_DEMI3)	return Insecte.mauvaisEtat;
		return Insecte.bonEtat;
	}
	
	@Override
	public void reset() {
		super.reset();
		pv = Stats.PVMAX_INSECTE3;
	}

	public EnnemiInsecteNv3() {
		super();
		pv = Stats.PVMAX_INSECTE3;
	}

	@Override
	public int getXp() {
		return CoutsEnnemis.EnnemiInsecteNv3.COUT;
	}
}
