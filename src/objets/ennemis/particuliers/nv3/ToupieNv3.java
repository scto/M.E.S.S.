package objets.ennemis.particuliers.nv3;

import objets.armes.typeTir.Tirs;
import objets.ennemis.CoutsEnnemis;
import objets.ennemis.particuliers.nv1.Toupie;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import jeu.Stats;

public class ToupieNv3 extends Toupie {

	public static final float CADENCE_TIR = .28f;
	public static final Tirs TIR = new Tirs(CADENCE_TIR);
	public static final Pool<ToupieNv3> POOL = Pools.get(ToupieNv3.class);
	
	@Override
	protected void free() {		POOL.free(this);	}
	@Override
	public void invoquer() {		LISTE.add(POOL.obtain());	}
	@Override
	protected int getPvMax() {		return Stats.PV_TOUPIE3;	}
	@Override
	protected void tir() {		TIR.tirBalayage(this, mort, maintenant, prochainTir);	}
	@Override
	public int getXp() {		return CoutsEnnemis.TOUPIE3.COUT;	}
	@Override
	protected String getLabel() {			return getClass().toString();	}
}
