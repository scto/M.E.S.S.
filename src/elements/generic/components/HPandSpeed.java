package elements.generic.components;

import jeu.Stats;
import jeu.mode.EndlessMode;

public enum HPandSpeed {
	
	BASIC(8, 9, Stats.MULTI_SPEED[1], Stats.MULTI_SPEED[2], Stats.MULTI_SPEED[3] * 2),
	GROUP(60, 4),
	SHOOTER(27, 5), 
	ZIGZAG(13, 1),
	BALL(40, 25),
	BALL_SIDE_SHOT(60, 25),
	SAT(430, 3),
	DIABOLO(67, 9),
	ROUND_N_ROUND(80, 10), 
	CYLON(90, 6),
	KINDER(120, 10),
	BOSS_QUAD(510, 15),
	CRUSADER(320, 4, 1, 1, 1),
	PLANE(150, 9.5f),
	SHOOTER_FRAG(200, 6),
	BOSS_MINE(780, 24),
	LASER(190, 7),
	INSECT(380, 6),
	ADD_SAT(25, 7),
	VICIOUS(0, 2);
	
	public int[] hps = {0, 0, 0, 0}, halfHps = {0, 0, 0, 0}, oneThirdHps = {0, 0, 0, 0}, twoThirdsHps = {0, 0, 0, 0};
	public float[] speeds = {0, 0, 0, 0}, halfSpeeds = {0, 0, 0, 0};
	
	private HPandSpeed(int hp, float speed) {
		speed *= Stats.U;
		for (int i = 0; i < 4; i++) {
			hps[i] = (int) (hp * Stats.MULTI_HP[i]);
			speeds[i] = speed * Stats.MULTI_SPEED[i];
			speeds[i] = speed * Stats.MULTI_SPEED[i];
		}
		initHalfHps();
	}

	private HPandSpeed(int hp, float speed, float speedMul2, float speedMul3, float speedMul4) {
		speed *= Stats.U;
		for (int i = 0; i < 4; i++)
			hps[i] = (int) (hp * Stats.MULTI_HP[i]);
		speeds[0] = speed;
		speeds[1] = speed * speedMul2;
		speeds[2] = speed * speedMul3;
		speeds[3] = speed * speedMul4;
		initHalfHps();
	}

	private void initHalfHps() {
		for (int i = 0; i < 4; i++) {
			halfHps[i] = hps[i] / 2;
			oneThirdHps[i] = hps[i] / 3;
			twoThirdsHps[i] = (int) (hps[i] * 0.666f);
			halfSpeeds[i] = speeds[i] / 2;
		}
	}

	public float getSpeed() {
		return speeds[EndlessMode.difficulty];
	}

	public int getHalfHp() {
		return halfHps[EndlessMode.difficulty];
	}

	public int getTwoThirdsHps() {
		return twoThirdsHps[EndlessMode.difficulty];
	}
	
	public int getOneThirdHps() {
		return oneThirdHps[EndlessMode.difficulty];
	}

	public int getHp() {
		return hps[EndlessMode.difficulty];
	}

	public float getHalfSpeed() {
		return halfSpeeds[EndlessMode.difficulty];
	}
}
