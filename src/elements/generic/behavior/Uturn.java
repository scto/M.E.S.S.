package elements.generic.behavior;

import jeu.CSG;
import jeu.Physic;
import jeu.mode.EndlessMode;
import elements.generic.enemies.Enemy;

public class Uturn extends Behavior {

	@Override
	public void act(Enemy e) {
		if (e.pos.y < CSG.HAUTEUR_ECRAN_PALLIER_7) {
			if (!EndlessMode.alternate) {
				if (e.toLeft()) 		e.dir.rotate(EndlessMode.delta2 * e.getVitesse());
				else 					e.dir.rotate(EndlessMode.delta2 * -e.getVitesse());
				e.angle = e.dir.angle();
			}
		}
		Physic.mvtNoCheck(e.pos, e.dir);
	}

}
