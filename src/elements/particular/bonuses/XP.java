package elements.particular.bonuses;

import jeu.CSG;
import jeu.EndlessMode;
import jeu.Physic;
import jeu.Stats;
import assets.AssetMan;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;

public class XP extends Bonus implements Poolable {

	public int valeur = 2;
	public static final Pool<XP> POOL = new Pool<XP>(30) {
		@Override
		protected XP newObject() {
			return new XP();
		}
	};
	public static final int WIDTH = (int) ((int) Stats.BONUS_WIDTH / 1.65f), HALF = WIDTH/2;
	public final Vector2 direction = new Vector2();
	private boolean active = false;

	public void init(float x, float y, int xp) {
		x -= HALF;
		y -= HALF;
		valeur = xp;
		active = false;
		direction.x = (float) (EndlessMode.R.nextFloat() * xp);
		direction.y = (float) (EndlessMode.R.nextGaussian() * xp);
		super.init(x, y);
		pos.x = x;
		pos.y = y;
		limites();
	}
	
	private void limites() {
		if (pos.x <= WIDTH) {
			pos.x = WIDTH;
			direction.x = Math.abs(direction.x); 
		} else if (pos.x > CSG.gameZoneWidth) {
			pos.x = CSG.gameZoneWidth;
			direction.x = -Math.abs(direction.x);
		}
		if (pos.y > CSG.SCREEN_HEIGHT) {
			pos.y = CSG.SCREEN_HEIGHT;
			direction.y = -Math.abs(direction.y);
		}
	}

	@Override
	void drawMeMoveMe(SpriteBatch batch) {
		batch.draw(AssetMan.xp, pos.x, pos.y, WIDTH, WIDTH);
		
		if (alternate && Physic.distanceFromPlayer(pos) <= Stats.LARGEUR_INSECTE) {
			active = true;
		}
		if (active) {
			Physic.mvtToPlayer(direction, pos, Stats.V_ADD_BOSS_SAT + valeur, WIDTH, HALF);
		} else {
			pos.x += direction.x * EndlessMode.delta15;
			pos.y += direction.y * EndlessMode.delta15;
			direction.x /= EndlessMode.UnPlusDelta;
			direction.y -= EndlessMode.deltaMicroU;
			limites();
		}
	}

	@Override
	public void taken() {
		EndlessMode.score += valeur;
		super.taken();
	}
	@Override
	public void reset() {									}
	@Override
	public void free() {							POOL.free(this);	}

	public void activate() {						active = true;		}
	@Override
	protected TextureRegion getTexture() {			return AssetMan.xp;	}

}
