package elements.generic.weapons.player;

import jeu.Stats;
import assets.AssetMan;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;

import elements.particular.particles.Particles;

public class PinkWeapon extends PlayerWeapon implements Poolable {
	
	public static int width = (int) Stats.U, halfWidth = (int) Stats.u;
	public static final float CADENCETIR = initCadence(.11f, 3);
	public static final String LABEL = "armeTrois";
	public static final Pool<PinkWeapon> POOL = new Pool<PinkWeapon>(30) {
		@Override
		protected PinkWeapon newObject() {
			return new PinkWeapon();
		}
	};
	public static final float[] COLORS = {
		AssetMan.convertARGB(1, 255f/255f, 20f/255f, 199f/255f),
		AssetMan.convertARGB(1, 255f/255f, 20f/255f, 196f/255f),
		AssetMan.convertARGB(1, 255f/255f, 20f/255f, 194f/255f),
		AssetMan.convertARGB(1, 255f/255f, 20f/255f, 197f/255f),
		AssetMan.convertARGB(1, 255f/255f, 20f/255f, 197f/255f),
		
		AssetMan.convertARGB(1, 255f/255f, 15f/255f, 209f/255f),
		AssetMan.convertARGB(1, 255f/255f, 15f/255f, 196f/255f),
		AssetMan.convertARGB(1, 255f/255f, 15f/255f, 194f/255f),
		AssetMan.convertARGB(1, 255f/255f, 15f/255f, 197f/255f),
		AssetMan.convertARGB(1, 255f/255f, 15f/255f, 197f/255f),
		
		AssetMan.convertARGB(1, 255f/255f, 30f/255f, 209f/255f),
		AssetMan.convertARGB(1, 255f/255f, 30f/255f, 196f/255f),
		AssetMan.convertARGB(1, 255f/255f, 30f/255f, 201f/255f),
		AssetMan.convertARGB(1, 255f/255f, 30f/255f, 197f/255f),
		AssetMan.convertARGB(1, 255f/255f, 30f/255f, 197f/255f),
		
		AssetMan.convertARGB(1, 255f/255f, 35f/255f, 219f/255f),
		AssetMan.convertARGB(1, 255f/255f, 35f/255f, 196f/255f),
		AssetMan.convertARGB(1, 255f/255f, 35f/255f, 194f/255f),
		AssetMan.convertARGB(1, 255f/255f, 35f/255f, 197f/255f),
		AssetMan.convertARGB(1, 255f/255f, 35f/255f, 197f/255f),
		
		AssetMan.convertARGB(1, 255f/255f, 30f/255f, 201f/255f)};
	
	public void init(final float x, final float y, final float dirX, final float dirY) {
		pos.x = x - halfWidth;
		pos.y = y;
		dir.x = dirX;
		dir.y = dirY;
		dir.scl(Stats.SPEED_PINK_WEAPON);
		PLAYER_LIST.add(this);
	}

	@Override	public void draw(SpriteBatch batch) {Particles.pinkParticle(this);	}
	@Override	public int getWidth() {				return width;	}
	@Override	public int getHeight() {			return width;	}
	@Override	public void free() {				POOL.free(this);	}
	@Override	public int getHalfWidth() {			return halfWidth;	}
	@Override	public int getHalfHeight() {		return halfWidth;	}
	@Override	public float getColor() {			return COLORS[color];	}
}
