package elements.generic.weapons.player;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Pool;

import elements.generic.components.Dimensions;
import elements.particular.particles.Particles;
import elements.particular.particles.individual.weapon.BlueSweepParticle;

public class BlueSweepWeapon extends PlayerWeapon {
	
	public static final Dimensions DIMENSIONS = Dimensions.BLUE_SWEEP_WEAPON;
	public static final float FIRERATETIR = 1.5f;
	public static final String LABEL = "ArmeBalayage";
	public static final Pool<BlueSweepWeapon> POOL = new Pool<BlueSweepWeapon>() {		protected BlueSweepWeapon newObject() {			return new BlueSweepWeapon();		}	};
	
	public BlueSweepWeapon() {}
	

	public void init(float posX, float posY, float x, float y) {
		pos.set(posX, posY);
		dir.set(x, y);
		PLAYER_LIST.add(this);
		Particles.shot(pos.x - DIMENSIONS.width, pos.y + DIMENSIONS.halfHeight, dir.angle());
	}

	@Override	public void displayOnScreen(SpriteBatch batch) {		BlueSweepParticle.add(this);						}
	@Override	public Dimensions getDimensions() {						return DIMENSIONS;									}
	@Override	public void free() {									POOL.free(this);									}

}
