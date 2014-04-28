package elements.generic.behavior;

import jeu.CSG;
import jeu.EndlessMode;
import elements.generic.enemies.Enemy;

public class OnTheTop extends Behavior {

	@Override
	public void act(Enemy e) {
		if (e.pos.x + e.getHalfWidth() > CSG.gameZoneHalfWidth)		e.dir.x -= e.getVitesse() * EndlessMode.delta2*1.5f;
		else														e.dir.x += e.getVitesse() * EndlessMode.delta2*1.5f;
		e.pos.x += e.dir.x * EndlessMode.delta;
		if (e.pos.y >= CSG.HEIGHT_8_10 || e.dir.y != e.getVitesse()) {
			e.pos.y += (e.dir.y * EndlessMode.delta);
			e.dir.y -= (e.dir.y + e.getVitesse())*EndlessMode.delta * 6;
		}
		if (e.pos.y < CSG.HEIGHT_8_10)
			e.pos.y = CSG.HEIGHT_8_10;			
	}

}
