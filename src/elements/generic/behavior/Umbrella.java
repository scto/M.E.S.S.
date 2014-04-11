package elements.generic.behavior;

import jeu.Physic;
import elements.generic.enemies.Enemy;

public class Umbrella extends Behavior {

	@Override
	public void act(Enemy e) {
		e.angle = Physic.mvtOmbrelle(e, e.dir);
	}

}
