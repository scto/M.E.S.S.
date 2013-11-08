package objets.ennemis.particuliers.nv3;

import objets.ennemis.CoutsEnnemis;
import objets.ennemis.particuliers.nv1.BouleQuiSArrete;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import jeu.Stats;

public class EnnemiBouleQuiSArreteNv3 extends BouleQuiSArrete {
	
	public static Pool<EnnemiBouleQuiSArreteNv3> pool = Pools.get(EnnemiBouleQuiSArreteNv3.class);
	
	@Override
	protected void free() {
		pool.free(this);
	}
	@Override
	public void invoquer() {
		LISTE.add(pool.obtain());
	}
	
	@Override
	protected int getPvMax() {	return Stats.PV_BOULE_QUI_SARRETE3;	}
	@Override
	public int getXp() {		return CoutsEnnemis.EnnemiBouleQuiSArreteNv3.COUT;	}
	@Override
	protected String getLabel() {			return getClass().toString();	}
	
}
