package elements.generic.weapons.player;

import java.util.Random;

import assets.AssetMan;
import assets.sprites.Animations;
import jeu.CSG;
import elements.generic.weapons.Weapon;
import elements.particular.particles.individual.PrecalculatedParticles;

public abstract class PlayerWeapon extends Weapon {
	
	protected static int color = 1;
	protected static final Random R = new Random();
	protected static final int FORCE = 7;
	protected static final float MINWIDTH = CSG.screenWidth / 20, UPDATEWIDTH = CSG.screenWidth / 200;
	public static final float[] COLORS = {
		AssetMan.convertARGB(1, 0, 84f  / 255f, 73f  / 255f),
		AssetMan.convertARGB(1, 0, 162f / 255f, 140f / 255f),
		AssetMan.convertARGB(1, 0, 141f / 255f, 239f / 255f),
		AssetMan.convertARGB(1, 0, 218f / 255f, 228f / 255f),
		AssetMan.convertARGB(1, 0, 89f  / 255f, 252f / 255f),
		
		AssetMan.convertARGB(1, 0, 94f  / 255f, 73f  / 255f),
		AssetMan.convertARGB(1, 0, 172f / 255f, 140f / 255f),
		AssetMan.convertARGB(1, 0, 151f / 255f, 239f / 255f),
		AssetMan.convertARGB(1, 0, 228f / 255f, 228f / 255f),
		AssetMan.convertARGB(1, 0, 99f  / 255f, 252f / 255f),
		
		AssetMan.convertARGB(1, 0, 84f  / 255f, 83f  / 255f),
		AssetMan.convertARGB(1, 0, 162f / 255f, 150f / 255f),
		AssetMan.convertARGB(1, 0, 141f / 255f, 249f / 255f),
		AssetMan.convertARGB(1, 0, 218f / 255f, 238f / 255f),
		AssetMan.convertARGB(1, 0, 99f  / 255f, 255f / 255f),
		
		AssetMan.convertARGB(1, 0, 94f  / 255f, 83f  / 255f),
		AssetMan.convertARGB(1, 0, 172f / 255f, 150f / 255f),
		AssetMan.convertARGB(1, 0, 151f / 255f, 249f / 255f),
		AssetMan.convertARGB(1, 0, 228f / 255f, 238f / 255f),
		AssetMan.convertARGB(1, 0, 109f / 255f, 255f / 255f),
		
		AssetMan.convertARGB(1, 0, 62f  / 255f, 254f / 255f)};
	
	// is used to generate the debris
	public void nextColor() {
		color++;
		if (color >= 21)
			color = 0;
	}
	public float getColor() {					return COLORS[R.nextInt(COLORS.length)];			}
	public float[] getColors() {				return PrecalculatedParticles.colorsOverTimeBlue;	}
	public float getSpeed() {					return 3333;		}	
	public int getPower() {						return FORCE;		}
	@Override
	public Animations getAnimation() {
		return null;
	}
}

