package elements.generic.components.behavior;

import jeu.CSG;

import com.badlogic.gdx.utils.Array;

import elements.generic.components.PhaseUser;
import elements.generic.components.behavior.movements.Movements;

public enum Behavior {
	
	BALL(					1, initMovements(Movements.STRAIGHT, Movements.Y_SLOW_DOWN)),
	STRAIGHT_ON(			2, initMovements(Movements.STRAIGHT)), 
	COMMA(					3, initMovements(Movements.STRAIGHT, Movements.DRIFT_LEFT)),
	COMMA_RIGHT_MUL2(		4, initMovements(Movements.STRAIGHT, Movements.DRIFT_RIGHT)),
	ZIG_ZAG(				5, initMovements(Movements.STRAIGHT, Movements.OSCILLATE_X)),
	U_TURN(					6, initMovements(Movements.STRAIGHT, Movements.U)),
	UMBRELLA(				7, initMovements(Movements.STRAIGHT, Movements.Y_SLOW_DOWN, Movements.DETECT_X)),
	TURN_AROUND(			8, initMovements(Movements.STRAIGHT, Movements.Y_SLOW_DOWN_HALF, Movements.ROTATATION_CW, Movements.GO_AWAY)),
	ON_TOP(					9, initMovements(Movements.STRAIGHT, Movements.OSCILLATE_X, Movements.Y_SLOW_DOWN)),
	ROUND_N_ROUND(			10, initMovements(Movements.STRAIGHT, Movements.ROTATATION_CW, Movements.CENTER_SCREEN_ATTRACTION)),
	SINK(					11, initMovements(Movements.STRAIGHT, Movements.ROTATION_TIME)),
	ROTATE(					12, initMovements(Movements.ROTATATION_CW)),
	GO_AWAY(				13, initMovements(Movements.STRAIGHT, Movements.GO_AWAY)),
	NOTHING(				14, initMovements()),
	STRAIGHT_ON_DETECT(		15, initMovements(Movements.STRAIGHT, Movements.DETECT_X)),
	OVALE(					16, initMovements(Movements.STRAIGHT, Movements.CENTER)),
	GO_ON_PLAYER(			17, initMovements(Movements.STRAIGHT, Movements.DETECT)),
	CENTER_X(				18, initMovements(Movements.STAY_CENTER_X)),
	STAY_TOP(				19, initMovements(Movements.STAY_TOP)),
	ROTATE_STAY_TOP(		20, initMovements(Movements.STAY_TOP, Movements.ROTATATION_CW)),
	OSCILLATE_X(			21, initMovements(Movements.STRAIGHT, Movements.OSCILLATE_X, Movements.Y_SLOW_DOWN)),
	SLOW(					22, initMovements(Movements.STRAIGHT, Movements.SLOW_DOWN));
	
	public final int pk;
	private Movements[] movements;

	private Behavior(int pk, Movements[] movements) {
		this.pk = pk;
		this.movements = movements;
	}
	
	public void act(PhaseUser u) {
		for (Movements m : movements) 
			m.mover.act(u);
	}
	
	protected static Movements[] initMovements(Movements... movements) {
		if (CSG.updateNeeded) {
			Array<Movements> tmp = new Array<Movements>();
			return Movements.convert(tmp);
		} else {
			return movements;
		}
	}

}
