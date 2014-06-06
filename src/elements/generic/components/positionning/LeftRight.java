package elements.generic.components.positionning;

import jeu.CSG;

import elements.generic.enemies.Enemy;

public class LeftRight implements Pos {

	public static final int PK = 5;
	
	@Override
	public void set(Enemy e) {
		if (CSG.R.nextBoolean()) {
			e.pos.x = CSG.WIDTH_ZONE_MOINS_WIDTH_BORD - e.getHalfWidth();
		}
		e.pos.x = e.getHalfWidth() + CSG.borderWidth;
		e.pos.y = CSG.SCREEN_HEIGHT + e.getHeight();
	}

}
