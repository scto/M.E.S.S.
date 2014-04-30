package elements.generic.behavior;

import jeu.Physic;
import jeu.mode.EndlessMode;
import elements.generic.enemies.Enemy;

public class TurnAround extends Behavior {

	@Override
	public void act(Enemy e) {
		if (EndlessMode.alternate)
			e.dir.rotate(EndlessMode.delta15);
		Physic.mvtNoCheck(e.pos, e.dir);
		e.dir.y /= 1 + EndlessMode.deltaDiv3;
		if (e.now > 12)
			e.dir.y += EndlessMode.deltaDiv3 * e.getVitesse();
	}

}
