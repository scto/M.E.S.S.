package objets.ennemis.particuliers.nv3;

import objets.ennemis.CoutsEnnemis;
import objets.ennemis.particuliers.nv1.Boule;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import jeu.Stats;

public class BouleQuiSArreteNv3 extends Boule {
	
	public static final Pool<BouleQuiSArreteNv3> POOL = Pools.get(BouleQuiSArreteNv3.class);
	
	@Override
	protected void free() {		POOL.free(this);	}
	@Override
	public void invoquer() {		LISTE.add(POOL.obtain());	}
	@Override
	protected int getPvMax() {	return Stats.PV_BOULE_QUI_SARRETE3;	}
	@Override
	public int getXp() {		return CoutsEnnemis.EnnemiBouleQuiSArreteNv3.COUT;	}
	@Override
	protected String getLabel() {			return getClass().toString();	}
	
}
