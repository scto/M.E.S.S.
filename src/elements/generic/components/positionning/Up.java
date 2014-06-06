package elements.generic.components.positionning;

import jeu.CSG;
import elements.generic.enemies.Enemy;

public class Up implements Pos {

	public static final int PK = 1;

	@Override
	public void set(Enemy e) {
		e.pos.x = CSG.R.nextFloat() * (CSG.gameZoneWidth - e.getWidth());
	}

}
