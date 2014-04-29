package elements.generic.weapons.enemies;

import jeu.EndlessMode;
import assets.AssetMan;
import assets.animation.Animated;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.badlogic.gdx.utils.Pools;

public class Tournante extends LaserWeapon implements Poolable, InvocableWeapon{
	
	public static final Pool<Tournante> POOL = Pools.get(Tournante.class);
	public static final float COLOR = AssetMan.convertARGB(1, 1, 1, .85f);
	public static final int PK = 14;
	private static final float SPEED = initSpeed(18, PK);
	private static final Animated ANIMATED = initAnimation(1, PK);
	
	@Override
	public void draw(SpriteBatch batch) {
		batch.setColor(COLOR);
		super.draw(batch);
		batch.setColor(AssetMan.WHITE);
	}

	@Override
	public boolean mouvementEtVerif() {
		EndlessMode.rotate(dir, EndlessMode.delta25);
		return super.mouvementEtVerif();
	}
	
	@Override	public void free() {					POOL.free(this);	}
	@Override	public EnemyWeapon invoke() {			return POOL.obtain();	}
	@Override	public TextureRegion getTexture() {		return ANIMATED.getTexture(now);	}
	@Override	protected float getSpeed() {			return SPEED;	}
}
