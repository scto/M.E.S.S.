package elements.positionning;

import jeu.CSG;
import elements.generic.enemies.Enemy;

public class Kinder implements Pos{

	public boolean left = false;
	public static final int PK = 4;
	
	@Override
	public void set(Enemy e) {
		if (left) {
			e.pos.x = -e.getWidth();
			getConst(e);
			// 45 to 125
			e.dir.rotate(45 + (CSG.R.nextFloat() * 80));
		} else {
			e.pos.x = CSG.gameZoneWidth;
			getConst(e);
			// -45 to - 125
			e.dir.rotate(-45 - (CSG.R.nextFloat() * 80));
		}
		left = !left;
	}

	private void getConst(Enemy e) {
		e.dir.x = 0;
		e.dir.y = -e.getVitesse();
		e.pos.y = CSG.halfHeight + e.getHeight();
	}
}
