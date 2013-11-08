package objets.ennemis.particuliers.nv3;

import objets.armes.typeTir.Tirs;
import objets.ennemis.CoutsEnnemis;
import objets.ennemis.particuliers.nv1.Laser;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import jeu.Stats;


public class EnnemiLaserNv3 extends Laser {
	
	public static final Tirs TIR = new Tirs(0.8f);
	
	public static Pool<EnnemiLaserNv3> pool = Pools.get(EnnemiLaserNv3.class);
	
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
		return Stats.PV_LASER3;
	}
	
	@Override
	protected void tir() {		TIR.tirToutDroit(this, mort, maintenant, prochainTir);	}

	@Override
	public int getXp() {		return CoutsEnnemis.EnnemiLaserNv3.COUT;	}
	
	@Override
	protected String getLabel() {			return getClass().toString();	}
}
