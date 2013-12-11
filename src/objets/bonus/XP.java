package objets.bonus;

import jeu.CSG;
import jeu.EndlessMode;
import jeu.Stats;
import assets.AssetMan;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;
import com.badlogic.gdx.utils.Pool.Poolable;

public class XP extends Bonus implements Poolable {

	public int valeur;
	private float tps = 0;
	private boolean gauche;
	public static Pool<XP> pool = Pools.get(XP.class);
	private static final float SPEED = Stats.V_BONUS_XP;

	public void init(float x, float y, int xp) {
		posX = x;
		posY = y;
		valeur = xp;
		if (posX < CSG.DEMI_LARGEUR_ZONE_JEU)
			gauche = false;
		else
			gauche = true;
		list.add(this);
	}

	@Override
	boolean drawMeMoveMe(SpriteBatch batch) {
		if (valeur < 49)
			batch.draw(AssetMan.XP, posX, posY, WIDTH, WIDTH);
		else
			batch.draw(AssetMan.XP2, posX, posY, WIDTH, WIDTH);
		
		tps += EndlessMode.delta;
		posY -= SPEED * EndlessMode.delta;
		
		if (gauche)
			posX -= ((tps) * EndlessMode.delta);
		else
			posX += ((tps) * EndlessMode.delta);

		return true;
	}

	@Override
	public void taken() {
		EndlessMode.score += valeur;
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
