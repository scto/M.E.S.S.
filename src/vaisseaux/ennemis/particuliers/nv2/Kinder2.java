package vaisseaux.ennemis.particuliers.nv2;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import jeu.Stats;
import vaisseaux.armes.typeTir.Tirs;
import vaisseaux.ennemis.CoutsEnnemis;
import vaisseaux.ennemis.particuliers.nv1.Kinder;

public class Kinder2 extends Kinder {

	private static Tirs tir = new Tirs(.4f); 
	
	public static Pool<Kinder2> pool = Pools.get(Kinder2.class);
	
	@Override
	protected void free() {		pool.free(this);	}
	@Override
	public void invoquer() {	pool.obtain();	}

	@Override
	protected int getPvMax() {
		return Stats.PV_KINDER2;
	}
	
	@Override
	protected void tir() {
		tir.tirToutDroit(this, mort, maintenant, prochainTir);
	}

	@Override
	public int getXp() {
		return CoutsEnnemis.KINDER2.COUT;
	}
}

