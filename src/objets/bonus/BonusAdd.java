package objets.bonus;

import objets.joueur.VaisseauJoueur;
import jeu.EndlessMode;
import jeu.Stats;
import assets.AssetMan;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;
import com.badlogic.gdx.utils.Pool.Poolable;

public class BonusAdd extends Bonus implements Poolable{

	private float tps = 0;
	public static Pool<BonusAdd> pool = Pools.get(BonusAdd.class);

	public void init(float x, float y) {
		posX = x;
		posY = y;
		liste.add(this);
	}

	@Override
	boolean afficherEtMvt(SpriteBatch batch) {
		tps += EndlessMode.delta;
		batch.draw(AssetMan.add, posX, posY, LARGEUR, LARGEUR);
		// Le fait descendre
		posY += -(Stats.V_BONUS_BOMBE+(tps*40)) * EndlessMode.delta;
		return true;
	}

	@Override
	public void prisEtFree() {
		VaisseauJoueur.rajoutAdd();
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
