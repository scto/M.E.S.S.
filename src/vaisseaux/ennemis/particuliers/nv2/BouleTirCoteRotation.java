package vaisseaux.ennemis.particuliers.nv2;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import jeu.Endless;
import jeu.Stats;
import vaisseaux.ennemis.CoutsEnnemis;

public class BouleTirCoteRotation extends BouleTirCote {

	public static final float VITESSE_ANGLE = 100f, CADENCETIR = .09f;

	public static Pool<BouleTirCoteRotation> pool = Pools.get(BouleTirCoteRotation.class);
	
	@Override
	protected void free() {		pool.free(this);	}
	@Override
	public void invoquer() {	pool.obtain();	}
	
	@Override
	public boolean mouvementEtVerif() {
		angle += VITESSE_ANGLE * Endless.delta;
		return super.mouvementEtVerif();
	}
	
	@Override
	protected float getVitesse() {
		return  Stats.V_BOULE_TIR_COTE_PETIT;
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
