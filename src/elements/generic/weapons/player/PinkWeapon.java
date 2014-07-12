package elements.generic.weapons.player;

import jeu.Stats;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;

import elements.generic.components.Dimensions;
import elements.particular.particles.Particles;

public class PinkWeapon extends PlayerWeapon implements Poolable {
	
	protected static final Dimensions DIMENSIONS = Dimensions.PINK_WEAPON;
	public static final float FIRERATETIR = .11f;
	public static final String LABEL = "armeTrois";
	public static final Pool<PinkWeapon> POOL = new Pool<PinkWeapon>(30) {		protected PinkWeapon newObject() {			return new PinkWeapon();		}	};
	
	public void init(final float x, final float y, final float dirX, final float dirY) {
		pos.set(x, y);
		dir.set(dirX, dirY).scl(Stats.SPEED_PINK_WEAPON);
		PLAYER_LIST.add(this);
		Particles.shot(pos.x - Stats.U, pos.y, dir.angle());
	}

	@Override	public void free() {								POOL.free(this);									}
	@Override	public Dimensions getDimensions() {					return DIMENSIONS;									}
	@Override	public void displayOnScreen(SpriteBatch batch) {	Particles.pinkParticle(this);						}
}
