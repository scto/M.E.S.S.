package elements.generic.components.positionning;

import jeu.CSG;

import elements.generic.enemies.Enemy;

public class LeftRight implements Pos {

	@Override
	public void set(Enemy e) {
		if (CSG.R.nextBoolean())
			e.pos.x = CSG.widthMinusBorder - e.getDimensions().halfWidth;
		else
			e.pos.x = e.getDimensions().halfWidth + CSG.borderWidth;
		e.pos.y = CSG.height + e.getDimensions().height;
	}

}
