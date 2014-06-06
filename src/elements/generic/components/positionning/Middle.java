package elements.generic.components.positionning;

import jeu.CSG;

import elements.generic.enemies.Enemy;

public class Middle implements Pos{

	public static final int PK = 3;
	
	@Override
	public void set(Enemy e) {
		e.pos.x = CSG.gameZoneHalfWidth - e.getHalfWidth();
		e.pos.y = CSG.SCREEN_HEIGHT;
	}

}
