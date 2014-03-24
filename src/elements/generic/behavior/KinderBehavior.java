package elements.generic.behavior;

import jeu.EndlessMode;
import jeu.Physic;
import assets.animation.AnimationKinder;
import elements.generic.enemies.Enemy;

public class KinderBehavior extends Behavior {

	@Override
	public void act(Enemy e) {
		if (e.now < AnimationKinder.TIME_OUVERT || e.now > 12) {
			Physic.mvtNoCheck(e.pos, e.dir);
		} else {
//			e.addAngle((e.getVitesse() * EndlessMode.delta)/100);
			e.dir.rotate(EndlessMode.delta * e.getVitesse());
			e.angle = e.dir.angle();
		}
	}

}
