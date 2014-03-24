package elements.generic.behavior;

import jeu.CSG;
import jeu.Physic;
import elements.generic.enemies.Enemy;

public class ZigZag extends Behavior {

	private static final float AMPLITUDE_HORIZONTALE = CSG.gameZoneWidth / 2;
	
	@Override
	public void act(Enemy e) {
		e.setLeft(Physic.goToZigZagCentre(e.pos, e.dir, e.getHalfWidth(), e.toLeft(), AMPLITUDE_HORIZONTALE, e.getHeight(), e.getWidth()));
	}

}
