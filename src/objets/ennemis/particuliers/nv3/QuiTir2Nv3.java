package objets.ennemis.particuliers.nv3;

import objets.ennemis.CoutsEnnemis;
import objets.ennemis.particuliers.nv1.QuiTir2;
import jeu.Stats;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;


public class QuiTir2Nv3 extends QuiTir2 {
	
	public static final Pool<QuiTir2Nv3> POOL = Pools.get(QuiTir2Nv3.class);
	
	@Override
	protected void free() {		POOL.free(this);	}
	@Override
	public void invoquer() {		LISTE.add(POOL.obtain());	}
	@Override
	protected int getPvMax() {		return Stats.PV_DE_BASE_QUI_TIR2;	}
	@Override
	public int getXp() {		return CoutsEnnemis.QUI_TIR2_NV3.COUT;	}
	@Override
	protected String getLabel() {			return getClass().toString();	}
}
