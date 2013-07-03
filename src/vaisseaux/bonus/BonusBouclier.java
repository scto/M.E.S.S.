package vaisseaux.bonus;

import vaisseaux.joueur.VaisseauType1;
import jeu.Endless;
import jeu.Stats;
import assets.AssetMan;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;
import com.badlogic.gdx.utils.Pool.Poolable;

public class BonusBouclier extends Bonus implements Poolable{

	private float tps = 0;
	public static Pool<BonusBouclier> pool = Pools.get(BonusBouclier.class);

	void init(float x, float y) {
		posX = x;
		posY = y;
		liste.add(this);
	}

	@Override
	boolean afficherEtMvt(SpriteBatch batch) {
		tps += Endless.delta;
		batch.draw(AssetMan.bouclier, posX, posY, LARGEUR, LARGEUR);
		posY += -(Stats.VITESSE_BONUS_BOUCLIER + (tps*15)) * Endless.delta;
		return true;
	}


	@Override
	public void prisEtFree() {
		VaisseauType1.ajoutBouclier();
		pool.free(this);
	}
	@Override
	public void reset() {
		tps = 0;
	}


	@Override
	public void free() {
		pool.free(this);
	}
}
