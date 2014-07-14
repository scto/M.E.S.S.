package elements.generic.weapons.enemies;

import jeu.CSG;
import jeu.mode.EndlessMode;
import assets.AssetMan;
import assets.sprites.Animations;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.components.Dimensions;

public class Mine extends EnemyWeapon {
	
	public static final Pool<Mine> POOL = Pools.get(Mine.class);
	private final float vitesseAngulaire = (float) CSG.R.nextGaussian() * 80;
	public static final Dimensions DIMENSIONS = Dimensions.MINE;
	public final float color = AssetMan.convertARGB(1, (CSG.R.nextFloat() /2) + 0.49f , (CSG.R.nextFloat() /2) + 0.49f, (CSG.R.nextFloat() /2) + 0.49f);

	@Override
	protected void setColor(SpriteBatch batch) {
		batch.setColor(color);
	}
	@Override
	protected void removeColor(SpriteBatch batch) {
		batch.setColor(AssetMan.WHITE);
	}
	@Override
	public Animations getAnimation() {
		return Animations.MINE;
	}
	@Override
	public float getAngle() {
		angle += vitesseAngulaire * EndlessMode.delta;
		return super.getAngle();
	}
	@Override	public void free() {					POOL.free(this);	}
	@Override	public Dimensions getDimensions() {		return DIMENSIONS;					}
}
