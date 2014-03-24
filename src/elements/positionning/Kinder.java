package elements.positionning;

import jeu.CSG;

import elements.generic.enemies.Enemy;

public class Kinder implements Pos{

	public static final int PK = 4;
	
	@Override
	public void set(Enemy e) {
		final float rand = CSG.R.nextFloat();
		if (rand > 0.5f){ // on est a gauche
			e.pos.x = -e.getWidth();
			e.pos.y = (float) (CSG.SCREEN_HEIGHT - (rand * CSG.halfHeight));
			e.dir.x = 0;
			e.dir.y = -1.1f * e.getVitesse();
			e.dir.rotate((float) ((( (rand + rand) - 1) * 55) + 55) );
		} else {
			e.pos.x = CSG.gameZoneWidth;
			e.pos.y = (float) (CSG.SCREEN_HEIGHT - ((rand+rand) * CSG.halfHeight));
			e.dir.x = 0;
			e.dir.y = -e.getVitesse();
			e.dir.rotate((float) -((( (rand + rand)) * 90) + 45) );
		}
	}
}
