package elements.positionning;

import jeu.CSG;

import elements.generic.enemies.Enemy;

public class LeftRight implements Pos{

	public static final int PK = 5;
	
	@Override
	public void set(Enemy e) {
		if (CSG.R.nextBoolean()) {
			e.pos.x = CSG.LARGEUR_ZONE_MOINS_LARGEUR_BORD - e.getHalfWidth();
		}
		e.pos.x = e.getHalfWidth() + CSG.borderWidth;
		e.pos.y = CSG.SCREEN_HEIGHT + e.getHeight();
	}

}
