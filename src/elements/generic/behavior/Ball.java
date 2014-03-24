package elements.generic.behavior;

import jeu.EndlessMode;
import jeu.Physic;
import elements.generic.enemies.Enemy;

public class Ball extends Behavior {

	@Override
	public void act(Enemy e) {
//		if (e.now < 10) 
//			Physic.mvtHeightLimit(e.pos, -Stats.V_ENN_BOULE, e.now);
//		else
		e.dir.y /= EndlessMode.UnPlusDelta;
		Physic.mvt(e.dir, e.pos, e.getWidth());
	}

}
