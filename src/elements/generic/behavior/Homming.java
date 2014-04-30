package elements.generic.behavior;

import jeu.Physic;
import jeu.mode.EndlessMode;
import elements.generic.enemies.Enemy;

public class Homming extends Behavior {

	@Override
	public void act(Enemy e) {
//		if (e.now < 10) 
//			Physic.mvtHeightLimit(e.pos, -Stats.V_ENN_BOULE, e.now);
//		else
		e.dir.y /= EndlessMode.UnPlusDelta;
		Physic.mvt(e.dir, e.pos, e.getWidth());
	}

}
