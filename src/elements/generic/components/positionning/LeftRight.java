package elements.generic.components.positionning;

import jeu.CSG;

import elements.generic.enemies.Enemy;

public class LeftRight implements Pos {

	@Override
	public void set(Enemy e) {
		if (CSG.R.nextBoolean()) {
			e.pos.x = CSG.WIDTH_ZONE_MOINS_WIDTH_BORD - e.getDimensions().halfWidth;
		}
		e.pos.x = e.getDimensions().halfWidth + CSG.borderWidth;
		e.pos.y = CSG.screenHeight + e.getDimensions().height;
	}

}
