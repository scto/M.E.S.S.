package vaisseaux.bonus;

import jeu.Endless;
import jeu.Stats;

import menu.CSG;
import assets.AssetMan;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;
import com.badlogic.gdx.utils.Pool.Poolable;

public class BonusTemps extends Bonus implements Poolable{

	private float tps = 0;
	public static Pool<BonusTemps> pool = Pools.get(BonusTemps.class);

	void init(float x, float y) {
		posX = x;
		posY = y;
		liste.add(this);
	}

	@Override
	boolean afficherEtMvt(SpriteBatch batch) {
		tps += Endless.delta;
		batch.draw(AssetMan.temps, posX, posY, LARGEUR, LARGEUR);
		// Le fait descendre
		posY += -Stats.VITESSE_BONUS * Endless.delta;
		// le fait aller � gauche ou � droite de plus en plus suivant le temps �coul�
		if (posX < CSG.DEMI_LARGEUR_ZONE_JEU)	posX -= ( (tps*tps) * Endless.delta);
		else								posX += ( (tps*tps) * Endless.delta);
		return true;
	}

	@Override
	public void prisEtFree() {
		Endless.ralentir(1);
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
