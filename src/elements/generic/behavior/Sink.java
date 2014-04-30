package elements.generic.behavior;

import jeu.Physic;
import jeu.mode.EndlessMode;
import elements.generic.enemies.Enemy;

public class Sink extends Behavior {

	@Override
	public void act(Enemy e) {
		Physic.mvtNoCheck(e.pos, e.dir);
		if (EndlessMode.alternate) {
			if (e.now < 15) {
				e.setRotation(e.now * EndlessMode.delta * e.getVitesse()*0.2f);
				rotate(e);
			} else {
				e.setRotation(e.getRotation()/EndlessMode.UnPlusDelta);
				rotate(e);
			}
		}
	}

	private void rotate(Enemy e) {
		if (e.toLeft()) {
			e.dir.rotate(e.getRotation());
			e.angle += e.getRotation();
		} else {
			e.dir.rotate(-e.getRotation());
			e.angle -= e.getRotation();
		}
	}

}
