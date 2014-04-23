package elements.generic.weapons.player;

import jeu.CSG;
import assets.AssetMan;
//import assets.animation.AnimationTirBleu;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;

import elements.particular.particles.Particles;
import elements.particular.particles.individual.weapon.BlueSweepParticle;

/**
 * Arme tirant en balyant l'�cran de gauche � droite, d'o� le nom
 * @author Julien
 *
 */

public class BlueSweepWeapon extends PlayerWeapon implements Poolable {
	
	public static int width, halfWidth;
	public static final float CADENCETIR = initCadence(.99f, 2);
	public static final String LABEL = "ArmeBalayage";
	public static final Pool<BlueSweepWeapon> POOL = new Pool<BlueSweepWeapon>() {
		@Override
		protected BlueSweepWeapon newObject() {
			return new BlueSweepWeapon();
		}
	};
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
	
	public static void updateDimensions() {
		width = (int) (MINWIDTH + (UPDATEWIDTH * CSG.profile.NvArmeBalayage));
		halfWidth = width/2;
	}

	public void init(float posX, float posY, float x, float y) {
		pos.x = posX;
		pos.y = posY;
		dir.x = x;
		dir.y = y;
		PLAYER_LIST.add(this);
	}

	@Override	public void draw(SpriteBatch batch) {		BlueSweepParticle.add(this);	}
	@Override	public int getWidth() {						return width;	}
	@Override	public int getHeight() {					return width;	}
	@Override	public void free() {						POOL.free(this);	}
	@Override	public float getColor() {					return COLORS[R.nextInt(COLORS.length)];	}
	@Override	public int getHalfWidth() {					return halfWidth;	}
	@Override	public int getHalfHeight() {				return halfWidth;	}
	public static Object getLabel() {	return "ArmeBalayage";	}
}
