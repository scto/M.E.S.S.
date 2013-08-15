package vaisseaux.ennemis.particuliers.nv3;

import jeu.Endless;
import jeu.Physique;
import jeu.Stats;
import vaisseaux.ennemis.CoutsEnnemis;
import vaisseaux.ennemis.particuliers.nv1.DeBase;
import assets.animation.AnimationExplosion1;

public class EnnemiDeBaseNv3 extends DeBase {

	public EnnemiDeBaseNv3() {
		super();
		pv = Stats.PVMAX_DE_BASE_NV3;
	}
	
	@Override
	public boolean mouvementEtVerif() {
		if( (mort && tpsAnimationExplosion > AnimationExplosion1.tpsTotalAnimationExplosion1) || Physique.toujoursAfficher(position, HAUTEUR, LARGEUR) == false){
			pool.free(this);
			return false;
		}
		position.y -= (Stats.VITESSE_MAX_DE_BASE_NV3 * Endless.delta);
		return true;
	}

	@Override
	public void reset() {
		super.reset();
		pv = Stats.PVMAX_DE_BASE_NV3;		
	}

	@Override
	public int getXp() {		return CoutsEnnemis.EnnemiDeBaseNv3.COUT;	}
	
	@Override
	public void invoquer() {		
		liste.add(pool.obtain());
		liste.add(pool.obtain());
		liste.add(pool.obtain());
		liste.add(pool.obtain());
	}
}
