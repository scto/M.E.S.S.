package elements.positionning;

import jeu.CSG;

import elements.generic.enemies.Enemy;

public class UpWide implements Pos{

	public static final int PK = 2;
	
	@Override
	public void set(Enemy e) {
		e.pos.x = CSG.R.nextFloat() * (CSG.gameZoneWidth - e.getWidth());
		e.pos.y = CSG.SCREEN_HEIGHT + e.getHeight();
	}

}
