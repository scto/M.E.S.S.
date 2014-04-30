package elements.generic.behavior;

import jeu.CSG;
import jeu.mode.EndlessMode;
import elements.generic.enemies.Enemy;

public class RoundAndRound extends Behavior {

	@Override
	public void act(Enemy e) {
		e.pos.y += (CSG.halfHeight - e.pos.y) * (EndlessMode.delta/2);
		if (e.now < 11 && EndlessMode.alternate) {
			e.dir.rotate(EndlessMode.delta2 * e.getVitesse());
			e.angle = e.dir.angle();
		}
		e.pos.x += e.dir.x * EndlessMode.delta;
		e.pos.y += e.dir.y * EndlessMode.delta;
	}

}
