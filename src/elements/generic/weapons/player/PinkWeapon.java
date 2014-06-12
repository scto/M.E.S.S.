package elements.generic.weapons.player;

import jeu.Stats;
import assets.AssetMan;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;

import elements.particular.particles.Particles;
import elements.particular.particles.individual.PrecalculatedParticles;

public class PinkWeapon extends PlayerWeapon implements Poolable {
	
	public static int width = (int) Stats.U, halfWidth = (int) Stats.u;
	public static final float FIRERATETIR = initCadence(.11f, 3);
	public static final String LABEL = "armeTrois";
	public static final Pool<PinkWeapon> POOL = new Pool<PinkWeapon>(30) {		protected PinkWeapon newObject() {			return new PinkWeapon();		}	};
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

	@Override	public float getWidth() {				return width;										}
	@Override	public float getHeight() {				return width;										}
	@Override	public void free() {					POOL.free(this);									}
	@Override	public float getHalfWidth() {			return halfWidth;									}
	@Override	public float getHalfHeight() {			return halfWidth;									}
	@Override	public float getColor() {				return COLORS[color];								}
	@Override	public void draw(SpriteBatch batch) {	Particles.pinkParticle(this);						}
	@Override	public float[] getColors() {			return PrecalculatedParticles.colorsPinkWeapon;		}
}
