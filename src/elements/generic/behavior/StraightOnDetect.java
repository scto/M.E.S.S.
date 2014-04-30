package elements.generic.behavior;

import jeu.Physic;
import jeu.mode.EndlessMode;
import elements.generic.Player;
import elements.generic.enemies.Enemy;

public class StraightOnDetect extends Behavior {

	@Override
	public void act(Enemy e) {
		Physic.mvtNoCheck(e.pos, e.dir);
		e.pos.x += -(EndlessMode.delta * (e.pos.x - Player.POS.x));
	}

}
