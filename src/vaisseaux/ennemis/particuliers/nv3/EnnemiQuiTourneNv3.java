package vaisseaux.ennemis.particuliers.nv3;

import jeu.Endless;
import jeu.Physique;
import jeu.Stats;
import menu.CSG;
import vaisseaux.ennemis.CoutsEnnemis;
import vaisseaux.ennemis.particuliers.nv1.QuiTourne;
import assets.animation.AnimationExplosion1;

public class EnnemiQuiTourneNv3 extends QuiTourne {
	
	public EnnemiQuiTourneNv3() {
		super();
		pv = Stats.PVMAX_QUI_TOURNE3;
	}

	@Override
	public void reset() {
		super.reset();
		pv = Stats.PVMAX_QUI_TOURNE3;
	}
	
	@Override
	public boolean mouvementEtVerif() {
		if (mort && tpsAnimationExplosion > AnimationExplosion1.tpsTotalAnimationExplosion1 || Physique.toujoursAfficher(position, LARGEUR) == false){
			pool.free(this);
			return false;
		}
		position.y += (CSG.DEMI_HAUTEUR_ECRAN - position.y) * (Endless.delta/2);
		if (maintenant < 10) direction.rotate(Endless.delta * Stats.VITESSE_QUI_TOURNE3);
		position.x += direction.x * Stats.VITESSE_QUI_TOURNE3 * Endless.delta;
		position.y += direction.y * Stats.DEMI_VITESSE_QUI_TOURNE3 * Endless.delta;
		return true;
	}

	@Override
	public int getXp() {		return CoutsEnnemis.EnnemiQuiTourneNv3.COUT;	}
}
