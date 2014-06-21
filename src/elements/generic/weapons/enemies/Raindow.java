package elements.generic.weapons.enemies;

import jeu.CSG;
import assets.AssetMan;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

public class Raindow extends EnemyWeapon {
	
	public static final int WIDTH = CSG.screenWidth / 30, HALF_WIDTH = WIDTH / 2, HEIGHT = (int) (WIDTH * 1.5), HALF_HEIGHT = HEIGHT / 2; 
	public static final Pool<Raindow> POOL = Pools.get(Raindow.class);
	public float color;
	private static float roueCouleur = .9f;
	private static boolean sens = false;
	private static final int PK = 1;
	private static final float SPEED = initSpeed(36, PK);
	
	@Override
	public void draw(SpriteBatch batch) {
		batch.setColor(color);
		super.draw(batch);
		batch.setColor(AssetMan.WHITE);
	}
	
	public void init(Vector2 position, float modifVitesse, Vector2 direction, boolean boss) {
		super.init(position, modifVitesse, direction, boss);
		if (sens)		roueCouleur += 0.03f;
		else			roueCouleur -= 0.03f;
		
		if (roueCouleur > 0.98f)			sens = false;
		else if (roueCouleur < 0.40f)		sens = true;
		color = AssetMan.convertARGB(1, roueCouleur, 1 - roueCouleur, 1);
	}
	
	@Override	public float getWidth() {					return WIDTH;	}
	@Override	public float getHeight() {					return HEIGHT;	}
	@Override	public void free() {						POOL.free(this);	}
	@Override	public float getHalfWidth() {				return HALF_WIDTH;	}
	@Override	public float getHalfHeight() {				return HALF_HEIGHT;	}
	@Override	public float getSpeed() {					return SPEED;	}


}
