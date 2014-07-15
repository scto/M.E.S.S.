package elements.generic.components.positionning;

import jeu.CSG;
import elements.generic.enemies.Enemy;

public class Up implements Pos {

	@Override
	public void set(Enemy e) {
		e.pos.x = CSG.R.nextFloat() * (CSG.halfWidth - e.getDimensions().width);
		e.pos.y = CSG.height;
	}

}
