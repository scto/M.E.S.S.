package vaisseaux.armes.ennemi;

import jeu.Stats;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;
import com.badlogic.gdx.utils.Pool.Poolable;

public class BouleBleueRapide extends BouleBleu implements Poolable{
	
	public static Pool<BouleBleueRapide> pool = Pools.get(BouleBleueRapide.class);

	@Override
	protected float getVitesse() {		return Stats.V_BOULE_BLEU_RAPIDE;	}
	@Override
	public void free() {				pool.free(this);	}
}
