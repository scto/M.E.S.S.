package elements.generic.behavior;

import jeu.Physic;
import elements.generic.enemies.Enemy;

public class StraightOn extends Behavior {

	@Override
	public void act(Enemy e) {
		Physic.mvtNoCheck(e.pos, e.dir);
	}

}
