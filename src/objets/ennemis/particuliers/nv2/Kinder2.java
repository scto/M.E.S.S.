package objets.ennemis.particuliers.nv2;

import objets.armes.typeTir.Tirs;
import objets.ennemis.CoutsEnnemis;
import objets.ennemis.particuliers.nv1.Kinder;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import jeu.Stats;

public class Kinder2 extends Kinder {

	private static Tirs tir = new Tirs(.4f); 
	public static final Pool<Kinder2> POOL = Pools.get(Kinder2.class);
	
	@Override
	protected void free() {		POOL.free(this);	}
	@Override
	public void invoquer() {	POOL.obtain();	}
	@Override
	protected int getPvMax() {		return Stats.PV_KINDER2;	}
	@Override
	protected void tir() {		tir.tirToutDroit(this, mort, maintenant, prochainTir);	}
	@Override
	public int getXp() {		return CoutsEnnemis.KINDER2.COUT;	}
	@Override
	protected String getLabel() {			return getClass().toString();	}
}

