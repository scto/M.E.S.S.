package objets.ennemis.particuliers.nv2;

import objets.ennemis.CoutsEnnemis;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import jeu.EndlessMode;
import jeu.Stats;

public class BouleTirCoteRotation extends BouleTirCote {

	public static final float VITESSE_ANGLE = 100f, CADENCETIR = .04f;

	public static Pool<BouleTirCoteRotation> pool = Pools.get(BouleTirCoteRotation.class);
	@Override
	protected void free() {		pool.free(this);	}
	@Override
	public void invoquer() {	pool.obtain();	}
	
	@Override
	public boolean mouvementEtVerif() {
		angle += VITESSE_ANGLE * EndlessMode.delta;
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
	public Vector2 getDirectionTir() {
		super.getDirectionTir();
		return TMP_DIR.rotate(angle);
	}
	
	@Override
	public float getModifVitesse() {
		return 1.3f;
	}
	
	public void setProchainTir(float f) {
		if (++numeroTir > 10) numeroTir = 1;
		prochainTir = f + CADENCETIR * numeroTir;
	}
	@Override
	protected String getLabel() {			return getClass().toString();	}
}
