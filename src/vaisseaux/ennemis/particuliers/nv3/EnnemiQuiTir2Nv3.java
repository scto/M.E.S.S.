package vaisseaux.ennemis.particuliers.nv3;

import jeu.Stats;
import vaisseaux.ennemis.CoutsEnnemis;
import vaisseaux.ennemis.particuliers.nv1.QuiTir2;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class EnnemiQuiTir2Nv3 extends QuiTir2 {
	
	public static TextureRegion getTexture(int pv) {
		if (pv < Stats.DEMI_PV_BASE_QUI_TIR2)
			return QuiTir2.mauvaisEtat;
		return QuiTir2.bonEtat;
	}
	
	@Override
	public void reset() {
		super.reset();
		pv = Stats.PVMAX_DE_BASE_QUI_TIR2;
	}

	public EnnemiQuiTir2Nv3() {
		super();
		pv = Stats.PVMAX_DE_BASE_QUI_TIR2;
	}

	@Override
	public int getXp() {
		return CoutsEnnemis.EnnemiQuiTir2Nv3.COUT;
	}
	
}
