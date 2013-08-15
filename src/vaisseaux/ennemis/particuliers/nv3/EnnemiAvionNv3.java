package vaisseaux.ennemis.particuliers.nv3;

import jeu.Endless;
import jeu.Physique;
import jeu.Stats;
import vaisseaux.ennemis.CoutsEnnemis;
import vaisseaux.ennemis.particuliers.nv1.Avion;
import assets.animation.AnimationExplosion1;

public class EnnemiAvionNv3 extends Avion {

	@Override
	public void reset() {
		super.reset();
		pv = Stats.PVMAX_AVION3;
	}

	public EnnemiAvionNv3() {
		super();
		pv = Stats.PVMAX_AVION3;
	}

	@Override
	public boolean mouvementEtVerif() {
		if( (mort && tpsAnimationExplosion > AnimationExplosion1.tpsTotalAnimationExplosion1) || Physique.toujoursAfficher(position, HAUTEUR, LARGEUR) == false){
			pool.free(this);
			return false;
		}
		position.y -= (Stats.VITESSE_AVION3 * Endless.delta);
		return true;
	}

	@Override
	public float getModifVitesse() {	return 1.3f;		}

	@Override
	public int getXp() {				return CoutsEnnemis.EnnemiAvionNv3.COUT;	}
	
}
