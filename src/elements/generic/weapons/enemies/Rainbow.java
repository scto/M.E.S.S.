package elements.generic.weapons.enemies;

import assets.AssetMan;
import assets.sprites.Animations;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.components.Dimensions;

public class Rainbow extends EnemyWeapon {
	
	public static final Dimensions DIMENSIONS = Dimensions.RAINBOW;
	public static final Pool<Rainbow> POOL = Pools.get(Rainbow.class);
	public float color;
	private static float roueCouleur = .9f;
	private static boolean sens = false;
	
	@Override
	protected void setColor(SpriteBatch batch) {
		batch.setColor(color);
	}
	
	@Override
	protected void removeColor(SpriteBatch batch) {
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
	
	@Override	public Animations getAnimation() {			return Animations.BLUE_BALL;	}
	@Override	public void free() {						POOL.free(this);	}
	@Override	public Dimensions getDimensions() {			return DIMENSIONS;					}


}
