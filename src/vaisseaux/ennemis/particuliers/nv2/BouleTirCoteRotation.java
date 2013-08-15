package vaisseaux.ennemis.particuliers.nv2;

import jeu.Endless;
import jeu.Physique;
import jeu.Stats;
import vaisseaux.ennemis.CoutsEnnemis;
import assets.animation.AnimationExplosion1;

public class BouleTirCoteRotation extends BouleTirCote {

	public static final float VITESSE_ANGLE = 100f, CADENCETIR = .09f;

	@Override
	public boolean mouvementEtVerif() {
		angle += VITESSE_ANGLE * Endless.delta;
		if( (mort && tpsAnimationExplosion > AnimationExplosion1.tpsTotalAnimationExplosion1)
				|| Physique.mouvementDeBase(direction, position, Stats.VITESSE_BOULE_TIR_COTE_PETIT, LARGEUR) == false){
			pool.free(this);
			return false;
		}
		return true;
	}

	@Override
	public int getXp() {
		return CoutsEnnemis.BouleTirCoteRotationNv2.COUT;
	}
	
	@Override
	public void setProchainTir(float f) {
		if (++numeroTir > 10) numeroTir = 1;
		prochainTir = f + CADENCETIR * numeroTir;
	}
	
	@Override
	public float getModifVitesse() {
		return 1.3f;
	}
}
