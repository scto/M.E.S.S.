package elements.generic.weapons.player;

import assets.AssetMan;
//import assets.animation.AnimationTirBleu;





import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;

import elements.generic.components.Dimensions;
import elements.particular.particles.individual.PrecalculatedParticles;
import elements.particular.particles.individual.weapon.BlueSweepParticle;

public class BlueSweepWeapon extends PlayerWeapon implements Poolable {
	
	public static final Dimensions DIMENSIONS = Dimensions.BLUE_SWEEP_WEAPON;
	public static final float FIRERATETIR = 1.5f;
	public static final String LABEL = "ArmeBalayage";
	public static final Pool<BlueSweepWeapon> POOL = new Pool<BlueSweepWeapon>() {		protected BlueSweepWeapon newObject() {			return new BlueSweepWeapon();		}	};
	public static final float[] COLORS = {
		AssetMan.convertARGB(1, 50f, 		50f, 		236f/255f),
		AssetMan.convertARGB(1, 37f/255f, 	66f/255f, 	1f),
		AssetMan.convertARGB(1, 57f/255f, 	127f/255f, 	1f),
		AssetMan.convertARGB(1, 67f/255f, 	127f/255f, 	1f),
		AssetMan.convertARGB(1, 57f/255f, 	137f/255f, 	1f),
		
		AssetMan.convertARGB(1, 55f, 		45f, 		236f/255f),
		AssetMan.convertARGB(1, 27f/255f, 	66f/255f, 	1f),
		AssetMan.convertARGB(1, 67f/255f, 	127f/255f, 	1f),
		AssetMan.convertARGB(1, 77f/255f, 	127f/255f, 	1f),
		AssetMan.convertARGB(1, 67f/255f, 	137f/255f, 	1f),
		
		AssetMan.convertARGB(1, 50f, 		50f, 		246f/255f),
		AssetMan.convertARGB(1, 37f/255f, 	76f/255f, 	1f),
		AssetMan.convertARGB(1, 57f/255f, 	137f/255f, 	1f),
		AssetMan.convertARGB(1, 67f/255f, 	137f/255f, 	1f),
		AssetMan.convertARGB(1, 57f/255f, 	147f/255f, 	1f),
		
		AssetMan.convertARGB(1, 60f, 		60f, 		236f/255f),
		AssetMan.convertARGB(1, 27f/255f, 	76f/255f, 	1f),
		AssetMan.convertARGB(1, 67f/255f, 	137f/255f, 	1f),
		AssetMan.convertARGB(1, 77f/255f, 	137f/255f, 	1f),
		AssetMan.convertARGB(1, 67f/255f, 	147f/255f, 	1f),
		
		AssetMan.convertARGB(1, 81f/255f, 	173f/255f, 	1f)};
	
	public BlueSweepWeapon() {}
	

	public void init(float posX, float posY, float x, float y) {
		pos.set(posX, posY);
		dir.set(x, y);
		PLAYER_LIST.add(this);
	}

	@Override	public float[] getColors() {							return PrecalculatedParticles.colorsOverTimeBlue;	}
	@Override	public float getColor() {								return COLORS[R.nextInt(COLORS.length)];			}
	@Override	public void displayOnScreen(SpriteBatch batch) {		BlueSweepParticle.add(this);						}
	@Override	public Dimensions getDimensions() {						return DIMENSIONS;									}
	@Override	public void free() {									POOL.free(this);									}

}
