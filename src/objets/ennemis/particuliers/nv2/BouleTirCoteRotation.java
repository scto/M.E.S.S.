package objets.ennemis.particuliers.nv2;

import objets.ennemis.CoutsEnnemis;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import jeu.EndlessMode;
import jeu.Stats;

public class BouleTirCoteRotation extends BouleTirCote {

	public static final float VITESSE_ANGLE = 100f, CADENCETIR = .04f;
	public static final Pool<BouleTirCoteRotation> POOL = Pools.get(BouleTirCoteRotation.class);
	
	@Override
	public boolean mouvementEtVerif() {
		angle += VITESSE_ANGLE * EndlessMode.delta;
		return super.mouvementEtVerif();
	}

	@Override
	public Vector2 getDirectionTir() {
		super.getDirectionTir();
		return TMP_DIR.rotate(angle);
	}
	
	public void setProchainTir(float f) {
		if (++numeroTir > 10) numeroTir = 1;
		prochainTir = f + CADENCETIR * numeroTir;
	}
	
	@Override
	protected float getVitesse() {		return  Stats.V_BOULE_TIR_COTE_PETIT;	}
	@Override
	public int getXp() {		return CoutsEnnemis.BOULE_TIR_COTE_ROTATION_NV2.COUT;	}
	@Override
	public float getModifVitesse() {		return 1.3f;	}
	@Override
	protected String getLabel() {			return getClass().toString();	}
	@Override
	protected void free() {		POOL.free(this);	}
	@Override
	public void invoquer() {	POOL.obtain();	}
}
