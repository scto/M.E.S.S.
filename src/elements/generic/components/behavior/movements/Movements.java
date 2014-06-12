package elements.generic.components.behavior.movements;

import jeu.CSG;
import jeu.Physic;
import jeu.mode.EndlessMode;

import com.badlogic.gdx.utils.Array;

import elements.generic.Player;
import elements.generic.components.PhaseUser;

public enum Movements {

	STRAIGHT(1, new Mover() {
		public void act(PhaseUser e) {
			Physic.mvtNoCheck(e.getPosition(), e.getDirection());
		}
	}), DETECT_X(2, new Mover() {
		public void act(PhaseUser e) {
			e.getPosition().x += -(EndlessMode.delta * (e.getPosition().x - Player.POS.x) * e.getFloatFactor());
		}
	}), Y_SLOW_DOWN(3, new Mover() {
		public void act(PhaseUser e) {
			e.getDirection().y /= EndlessMode.UnPlusDelta;
		}
	}), Y_SLOW_DOWN_HALF(3, new Mover() {
		public void act(PhaseUser e) {
			if (EndlessMode.alternate)
				e.getDirection().y /= EndlessMode.UnPlusDelta;
		}
	}), DRIFT_LEFT(4, new Mover() {
		public void act(PhaseUser e) {
			e.getDirection().x -= (e.getPhaseTime() * EndlessMode.delta2);
		}
	}), DRIFT_RIGHT(5, new Mover() {
		public void act(PhaseUser e) {
			e.getDirection().x += (e.getPhaseTime() * EndlessMode.delta4);
		}
	}), OSCILLATE_X(6, new Mover() {
		public void act(PhaseUser e) {
			e.getDirection().x -= (((e.getPosition().x + e.getHalfWidth()) - CSG.screenHalfWidth) * EndlessMode.delta) * e.getSpeed();
		}
	}), CENTER(6, new Mover() {
		public void act(PhaseUser e) {
			e.getDirection().x -= ((e.getPosition().x + e.getHalfWidth()) - CSG.screenHalfWidth) * e.getFloatFactor();
			e.getDirection().y -= ((e.getPosition().y + e.getHalfHeight()) - CSG.halfHeight) * e.getFloatFactor();
		}
	}), GO_AWAY(7, new Mover() {
		public void act(PhaseUser e) {
			e.getDirection().y += EndlessMode.deltaDiv3 * e.getSpeed();
		}
	}), STAY_CENTER(8, new Mover() {
		public void act(PhaseUser e) {
			if (e.getPosition().x + e.getHalfWidth() < CSG.gameZoneHalfWidth)
				e.getPosition().x += EndlessMode.delta * e.getSpeed();
			else if (e.getPosition().x + e.getHalfWidth() > CSG.gameZoneHalfWidth)
				e.getPosition().x -= EndlessMode.delta * e.getSpeed();

			if (e.getPosition().y > CSG.halfHeight - e.getHalfHeight())
				e.getPosition().y -= EndlessMode.delta * e.getSpeed();
		}
	}), STAY_TOP(8, new Mover() {
		public void act(PhaseUser e) {
			if (e.getPosition().x + e.getWidth() < CSG.gameZoneHalfWidth)
				e.getPosition().x += EndlessMode.delta * e.getSpeed();
			else if (e.getPosition().x > CSG.gameZoneHalfWidth)
				e.getPosition().x -= EndlessMode.delta * e.getSpeed();
			if (e.getPosition().y > CSG.HEIGHT_ECRAN_PALLIER_3)
				e.getPosition().y -= EndlessMode.delta * e.getSpeed();
		}
	}), CENTER_SCREEN_ATTRACTION(9, new Mover() {
		public void act(PhaseUser e) {
			e.getPosition().y += (CSG.halfHeight - e.getPosition().y) * (EndlessMode.delta / 2);
		}
	}), ROTATION_TIME(10, new Mover() {
		public void act(PhaseUser e) {
			e.getDirection().rotate(e.getPhaseTime() * EndlessMode.delta * e.getFloatFactor());
		}
	}), ROTATATION_CW(11, new Mover() {
		public void act(PhaseUser e) {
			e.getDirection().rotate(e.getFloatFactor() * EndlessMode.deltaDiv2);
			e.setAngle(e.getAngle() + (e.getFloatFactor() * EndlessMode.deltaDiv2));
		}
	}), SLOW_DOWN(12, new Mover() {
		public void act(PhaseUser e) {
			e.getDirection().scl(e.getFloatFactor());
		}
	}), DETECT(13, new Mover() {
		public void act(PhaseUser e) {
			if (EndlessMode.alternate)
				e.setAngle(Physic.setDirToPlayer(e.getDirection(), e.getPosition(), e.getSpeed(), e.getWidth(), e.getHalfWidth(), e.getFloatFactor()));
		}
	}), STAY_CENTER_X(14, new Mover() {
		public void act(PhaseUser e) {
			if (e.getPosition().x < CSG.gameZoneHalfWidth - e.getHalfWidth())
				e.getPosition().x += EndlessMode.delta * e.getSpeed();
		}
	}), U(15, new Mover() {
		public void act(PhaseUser e) {
			if (e.getPosition().y < CSG.HEIGHT_ECRAN_PALLIER_7) {
				if (!EndlessMode.alternate) {
					if (e.getWay())
						e.getDirection().rotate(EndlessMode.delta2 * e.getSpeed());
					else
						e.getDirection().rotate(EndlessMode.delta2 * -e.getSpeed());
				}
			}
		}
	}), PROTOTYPE(16, new Mover() {
		public void act(PhaseUser e) {
			movement(e, e.getDirection().len(), e.getDirection().angle(), e.getWay());
		}
	});

	/*
	 * LIGNE DROITE ============ Arret quand deplace de autant en X et autant en
	 * Y
	 * 
	 * Pour ca deux floats qui comptent le deplacement effectue. Dans les
	 * ennemis
	 * 
	 * Ca permet donc de faire des deplacements d'un point A e un point B de
	 * facon relative.
	 * 
	 * Deux floats dans le mover definissent de combien on se deplace par frame
	 * 
	 * 
	 * if (e.getXMeter() < 25 * Stats.u) e.getPosition().x += e.getSpeed() *
	 * EndlessMode.delta; if (e.getYMeter() < 0 * Stats.u) e.getPosition().y +=
	 * e.getSpeed() * EndlessMode.delta; e.setXMeter(e.getXMeter() +
	 * (e.getSpeed() * EndlessMode.delta)); e.setYMeter(e.getYMeter() +
	 * (e.getSpeed() * EndlessMode.delta));
	 * 
	 * 
	 * Probleme de la COURBE =====================
	 * 
	 * Il faut une direction par phase, ou plutot initialiser la direction au
	 * debut de la phase.
	 * 
	 * La direction est initialisee avec les deux floats du mover.
	 * 
	 * Dans le cas d'un demi cercle la rotation sera de 90 e -90 par rapport au
	 * milieu du deplacement.
	 * 
	 * 
	 * 
	 * 
	 * 
	 * Acceleration ============
	 * 
	 * CSG.tmpDir.x = 25 * Stats.u; CSG.tmpDir.y = 0; CSG.tmpDir.rotate(
	 * (e.getXMeter() - (12.5f * Stats.u)) ); e.getPosition().x += CSG.tmpDir.x
	 * * EndlessMode.delta; Si on rajoute le Y on a la courbe
	 * e.setXMeter(e.getXMeter() + (CSG.tmpDir.x * EndlessMode.delta));
	 */
	public final int pk;
	public Mover mover;
	public static int LINEAR, GAUSSIAN;

	private Movements(int pk, Mover mover) {
		this.pk = pk;
		this.mover = mover;
	}

	public static Movements[] convert(Array<Movements> tmp) {
		final Movements[] array = new Movements[tmp.size];
		for (int i = 0; i < tmp.size; i++) {
			array[i] = tmp.get(i);
		}
		return array;
	}
	
	private static void movement(PhaseUser e, final float x, final float angle, boolean rotation) {
		if (e.getXMeter() < x) {
			CSG.tmpDir.x = x;
			CSG.tmpDir.y = 0;
			if (rotation)
				CSG.tmpDir.rotate(e.getXMeter() - (x/2));
			// scale : 0.6 - 0.1 - 0.6
			if (e.getShotWay())
				CSG.tmpDir.scl(
					Math.abs((e.getXMeter() - (x/2))
					/ x) + 0.1f
					);
			e.setXMeter(e.getXMeter() + (CSG.tmpDir.x * EndlessMode.delta));
			CSG.tmpDir.rotate(angle);
			e.getPosition().x += CSG.tmpDir.x * EndlessMode.delta;
			e.getPosition().y += CSG.tmpDir.y * EndlessMode.delta;
		}
	}

}
