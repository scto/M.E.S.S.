package elements.generic.behavior;

import elements.generic.enemies.Enemy;

public abstract class Behavior {
	public static final int BALL = 1;
	public static final int STRAIGHT = 2;
	public static final int SLASH = 3;
	public static final int TURN_AROUND = 4;
	public static final int KINDER = 5;
	public static final int SINK = 6;
	public static final int ROUND_N_ROUND = 7;
	public static final int UTURN = 8;
	public static final int ZIGZAG = 9;
	public static final int HOMMING = 10;

	public abstract void act(Enemy e);
}
