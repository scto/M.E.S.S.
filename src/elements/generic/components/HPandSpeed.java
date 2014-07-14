package elements.generic.components;

import jeu.Stats;

public enum HPandSpeed {
	
	BASIC(5, 9, Stats.MULTI_SPEED[1], Stats.MULTI_SPEED[2], Stats.MULTI_SPEED[3] * 2),
	GROUP(60, 5),
	SHOOTER(27, 5), 
	ZIGZAG(13, 12),
	BALL(37, 25),
	SAT(430, 4),
	DIABOLO(67, 13),
	ROUND_N_ROUND(80, 10), 
	CYLON(90, 4),
	KINDER(120, 6),
	BOSS_QUAD(510, 15),
	CRUSADER(320, 5),
	PLANE(150, 9.5f),
	SHOOTER_FRAG(200, 6),
	BOSS_MINE(780, 5),
	LASER(190, 8),
	INSECT(380, 6),
	ADD_SAT(25, 5);
	
	public float[] hps = {0, 0, 0, 0}, halfHps = {0, 0, 0, 0}, speeds = {0, 0, 0, 0}, halfSpeeds = {0, 0, 0, 0};
	
	private HPandSpeed(float hp, float speed) {
		speed *= Stats.U;
		for (int i = 0; i < 4; i++) {
			hps[i] = hp * Stats.MULTI_HP[i];
			speeds[i] = speed * Stats.MULTI_SPEED[i];
			speeds[i] = speed * Stats.MULTI_SPEED[i];
		}
		initHalfHps();
	}

	private HPandSpeed(float hp, float speed, float speedMul2, float speedMul3, float speedMul4) {
		speed *= Stats.U;
		for (int i = 0; i < 4; i++)
			hps[i] = hp * Stats.MULTI_HP[i];
		speeds[0] = speed;
		speeds[1] = speed * speedMul2;
		speeds[2] = speed * speedMul3;
		speeds[3] = speed * speedMul4;
		initHalfHps();
	}

	private void initHalfHps() {
		for (int i = 0; i < 4; i++) {
			halfHps[i] = hps[i] / 2;
			halfSpeeds[i] = speeds[i] / 2;
		}
	}
}
