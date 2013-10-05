package objets.ennemis.particuliers.nv3;

import objets.ennemis.CoutsEnnemis;
import objets.ennemis.particuliers.nv1.QuiTir2;
import jeu.Stats;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;


public class EnnemiQuiTir2Nv3 extends QuiTir2 {
	
	public static Pool<EnnemiQuiTir2Nv3> pool = Pools.get(EnnemiQuiTir2Nv3.class);
	
	@Override
	protected void free() {
		pool.free(this);
	}
	
	@Override
	public void invoquer() {
		LISTE.add(pool.obtain());	
	}
	
	@Override
	protected int getPvMax() {
		return Stats.PV_DE_BASE_QUI_TIR2;
	}
	
	@Override
	public int getXp() {
		return CoutsEnnemis.EnnemiQuiTir2Nv3.COUT;
	}
	
}
