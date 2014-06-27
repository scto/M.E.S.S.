package elements.generic.components.behavior;

import com.badlogic.gdx.math.Vector2;

import jeu.CSG;
import jeu.Physic;
import jeu.mode.EndlessMode;
import elements.generic.Element;
import elements.particular.Player;

public abstract class Mover {
	
	public static void straight(Element e) {
		Physic.mvtNoCheck(e.pos, e.dir);
	}
	
	public static void ball(Element e, float slowFactor) {
		if (EndlessMode.alternate) {
			straight(e);
			e.dir.y /= EndlessMode.UnPlusDelta * slowFactor;
		}
	}

	public static void goAway(Element e, float factor) {
		straight(e);
		e.dir.y += e.getSpeed() * factor;
	}

	public static void ovale(Element e, float factor) {
		e.dir.x -= ((e.pos.x + e.getDimensions().halfWidth) - CSG.screenHalfWidth) * factor;
		e.dir.y -= ((e.pos.y + e.getDimensions().halfHeight) - CSG.halfHeight) * factor;
		straight(e);
	}

	public static void coma(Element e, float factor) {
		e.dir.x -= (e.getNow() * EndlessMode.delta2 * factor);
		straight(e);
	}
	
	public static void rotateNoMove(Element e, float factor) {
		e.dir.rotate(factor * EndlessMode.deltaDiv2);
	}

	public static void rotate(Element e, float factor) {
		e.dir.rotate(factor * EndlessMode.deltaDiv2);
		straight(e);
	}

	public static void sink(Element e, float f) {
		e.dir.rotate(e.getNow() * EndlessMode.delta * f);
		straight(e);
	}

	public static void U(Element e) {
		if (e.pos.y < CSG.HEIGHT_ECRAN_PALLIER_7) {
			if (!EndlessMode.alternate) {
				if (e.getWay())
					e.dir.rotate(EndlessMode.delta2 * e.getSpeed());
				else
					e.dir.rotate(EndlessMode.delta2 * -e.getSpeed());
			}
		}
		straight(e);
	}

	public static void straightDetectX(Element e, float factor) {
		detectX(e, factor);
		straight(e);
	}

	public static void detectX(Element e, float factor) {
		e.pos.x += -(EndlessMode.delta * (e.pos.x - Player.POS.x) * factor);
	}

	public static void goToPlayer(Element e, float rotation) {
		if (EndlessMode.alternate)
			e.setAngle(Physic.setDirToPlayer(e.dir, e.pos, e.getSpeed(), e.getDimensions().width, e.getDimensions().halfWidth, rotation) + 90);
		straight(e);
	}
	
	public static void goToTarget(Element e, Vector2 target, float rotation) {
		if (EndlessMode.alternate)
			e.setAngle(Physic.setDirTo(e.dir, e.pos, e.getSpeed(), e.getDimensions().width, e.getDimensions().halfWidth, rotation, target));
		straight(e);
	}

	public static void toRight(Element e, float factor) {
		e.dir.y -= factor;
		e.dir.x -= factor;
		straight(e);
	}

	public static void insectMove(Element e, int min, int max, float factor) {
		if (e.getNow() > min && e.getNow() < max) {
			e.setAngle(e.dir.angle() + 90);
			Mover.toRight(e, - (factor * (((max - min)-e.getNow())/min) ));
		} else
			Mover.straight(e);
	}

	public static void zigZag(Element e, float factor) {
		oscillateX(e, factor);
		straight(e);
	}

	/**
	 * @param e
	 * @param factor
	 */
	public static void oscillateX(Element e, float factor) {
		e.dir.x -= (((e.pos.x + e.getDimensions().halfWidth) - CSG.screenHalfWidth) * EndlessMode.delta) * factor;
	}

	public static void yOnly(Element e) {
		e.pos.y += e.dir.y * EndlessMode.delta;
	}

	public static void ancorY(Element e, float limit) {
		if (e.pos.y > limit) {
			e.dir.y = -e.getSpeed();
		}
	}

	public static void ancorX(Element e, float force) {
		if (e.pos.x + e.getDimensions().width < CSG.gameZoneHalfWidth)
			e.dir.x += force * EndlessMode.delta;
		else if (e.pos.x > CSG.gameZoneHalfWidth)
			e.dir.x -= force * EndlessMode.delta;
	}

//	if (now > 4 && now < 12) {
//		angle = dir.angle() + 90;
//		Mover.toRight(this, - (Stats.uSur8 * ((8-now)/4) ));
//	} else
//		Mover.straight(this);
}
