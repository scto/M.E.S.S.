package elements.generic.weapons.player;

import jeu.Stats;
import assets.AssetMan;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;

import elements.particular.particles.Particles;
import elements.particular.particles.individual.weapon.FireballParticle;

/**
 * Arme de base qui fait une boule de feu
 * @author Julien
 *
 */
public class Fireball extends PlayerWeapon implements Poolable{
	
	public static int width, halfWidth, width066, width033, width05, width10, width15, width20, width25, width30, width35, width40;
	public static final float CADENCETIR = initCadence(.13f, 1);
	public static final String LABEL = "ArmeDeBase";
	public static final Pool<Fireball> POOL = new Pool<Fireball>(10) {
		@Override
		protected Fireball newObject() {
			return new Fireball();
		}
	};
	
	public static float[] couleurs = {
		AssetMan.convertARGB(1, 108f/255f, 24f/255f, 	0),
		AssetMan.convertARGB(1, 128f/255f, 24f/255f, 	0),
		AssetMan.convertARGB(1, 146f/255f, 28f/255f, 	0),
		AssetMan.convertARGB(1, 152f/255f, 24f/255f, 	0),
		AssetMan.convertARGB(1, 156f/255f, 28f/255f, 	0),
		
		AssetMan.convertARGB(1, 174f/255f, 22f/255f,	0),
		AssetMan.convertARGB(1, 184f/255f, 22f/255f, 	0),
		AssetMan.convertARGB(1, 132f/255f, 40f/255f, 	0),
		AssetMan.convertARGB(1, 208f/255f, 20f/255f, 	0),
		AssetMan.convertARGB(1, 218f/255f, 20f/255f, 	0),
		
		AssetMan.convertARGB(1, 228f/255f, 96f/255f, 	12/255f),
		AssetMan.convertARGB(1, 232f/255f, 136f/255f, 	24/255f),
		AssetMan.convertARGB(1, 238f/255f, 96f/255f, 	12/255f),
		AssetMan.convertARGB(1, 240f/255f, 172f/255f, 	36/255f),
		AssetMan.convertARGB(1, 245f/255f, 122f/255f, 	36/255f),
		
		AssetMan.convertARGB(1, 245f/255f, 136f/255f, 	24/255f),
		AssetMan.convertARGB(1, 248f/255f, 150f/255f, 	48/255f),
		AssetMan.convertARGB(1, 248f/255f, 200f/255f, 	48/255f),
		AssetMan.convertARGB(1, 252f/255f, 208f/255f, 	60/255f),
		AssetMan.convertARGB(1, 252f/255f, 228f/255f, 	60/255f),
		
		AssetMan.convertARGB(1, 252f/255f, 248f/255f, 148/255f)};
	
	public static void updateDimensions() {
//		width = (int) (MINWIDTH + (UPDATEWIDTH * CSG.profile.NvArmeDeBase));
		width = (int) (MINWIDTH + (UPDATEWIDTH * 3)); 
		halfWidth = width/2;
		width033 = (int) (width * .33f);
		width066 = (int) (width * .66f);
		width05 = (int) (width*.03f);
		width10 = (int) (width*.06f);
		width15 = (int) (width*.09f);
		width20 = (int) (width*.12f);
		width25 = (int) (width*.15f);
		width30 = (int) (width*.18f);
		width35 = (int) (width*.21f);
		width40 = (int) (width*.24f);
		
		FireballParticle.width = width;
	}

	/**
	 * ATTENTION ici le init s'occupe d'ajouter � la bonne liste
	 */
	public void init(float posX, float posY) {
		pos.x = posX;
		pos.y = posY;
		PLAYER_LIST.add(this);
		dir.y = Stats.V_ARME_DE_BASE;
	}

	@Override	public void draw(SpriteBatch batch) {		Particles.ajoutArmeDeBase(this);	}
	@Override	public int getWidth() {						return width;	}	
	@Override	public int getHeight() {					return width;	}
	@Override	public void free() {						POOL.free(this);	}
	@Override	public float getColor() {					return couleurs[R.nextInt(couleurs.length)];	}
	@Override	public int getHalfWidth() {					return halfWidth;	}
	@Override	public int getHalfHeight() {				return halfWidth;	}

	public static String getLabel() {	return "ArmeDeBase";	}
}
