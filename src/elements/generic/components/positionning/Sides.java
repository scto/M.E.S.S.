package elements.generic.components.positionning;

import jeu.CSG;
import jeu.Stats;
import elements.generic.enemies.Enemy;

public class Sides implements Pos{

	public boolean left = false;
	public static final int PK = 4;
	
	@Override
	public void set(Enemy e) {
		if (left) {
			e.pos.x = -e.getWidth() - Stats.WIDTH_DIV_10;
			getConst(e);
			// 45 to 125
			e.dir.rotate(45 + (CSG.R.nextFloat() * 80));
		} else {
			e.pos.x = Stats.GAME_ZONE_W_PLUS_WIDTH_DIV_10;
			getConst(e);
			// -45 to - 125
			e.dir.rotate(-45 - (CSG.R.nextFloat() * 80));
		}
		left = !left;
	}

	private void getConst(Enemy e) {
		e.dir.x = 0;
		e.dir.y = -e.getSpeed();
		e.pos.y = CSG.halfHeight + e.getHeight();
	}
}
