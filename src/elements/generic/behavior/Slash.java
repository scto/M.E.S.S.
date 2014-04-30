package elements.generic.behavior;

import jeu.Physic;
import jeu.mode.EndlessMode;
import elements.generic.enemies.Enemy;

public class Slash extends Behavior {

	@Override
	public void act(Enemy e) {
		e.dir.x -= (e.now * EndlessMode.delta2);
		Physic.mvtNoCheck(e.pos, e.dir);
	}
}
