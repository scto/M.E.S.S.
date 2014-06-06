package elements.generic.weapons.player;

import java.util.Random;

import jeu.CSG;
import elements.generic.weapons.Weapon;

public abstract class PlayerWeapon extends Weapon {
	
	protected static int color = 1;
	protected static final Random R = new Random();
	protected static final int FORCE = 7;
	protected static final float MINWIDTH = CSG.screenWidth / 20, UPDATEWIDTH = CSG.screenWidth / 200;
	
	// is used to generate the debris
	public void nextColor() {
		color++;
		if (color >= 21)
			color = 0;
	}
	public abstract float getColor();
	public float getSpeed() {							return 3333;																		}	
	public int getPower() {		return FORCE;	}
	
	public abstract float[] getColors();
}

