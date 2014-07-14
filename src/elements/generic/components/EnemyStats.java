package elements.generic.components;

import jeu.Stats;
import jeu.mode.EndlessMode;

public enum EnemyStats {
	
	BASIC(8, 9, Stats.MULTI_SPEED[1], Stats.MULTI_SPEED[2], Stats.MULTI_SPEED[3] * 2, 1, 25),
	GROUP(60, 4, 18, 35),
	SHOOTER(27, 5, 10, 40), 
	ZIGZAG(13, 1, 3, 40),
	BALL(40, 25, 12, 35),
	BALL_SIDE_SHOT(60, 25, 73, 40),
	SAT(430, 3, 80, 80),
	DIABOLO(67, 9, 19, 25),
	ROUND_N_ROUND(80, 10, 21, 30), 
	CYLON(90, 6, 12, 35),
	KINDER(120, 10, 32, 45),
	BOSS_QUAD(510, 15, 200, 200),
	CRUSADER(320, 4, 1, 1, 1, 91, 40),
	PLANE(150, 9.5f, 45, 55),
	SHOOTER_FRAG(200, 6, 39, 50),
	BOSS_MINE(780, 24, 200, 200),
	LASER(190, 7, 32, 50),
	INSECT(380, 6, 86, 50),
	ADD_SAT(25, 7, 15, 25),
	VICIOUS(0, 2, 0, 50);
	
	public int explosion = 0;
	public int[] hps = {0, 0, 0, 0}, halfHps = {0, 0, 0, 0}, oneThirdHps = {0, 0, 0, 0}, twoThirdsHps = {0, 0, 0, 0}, xp = {0, 0, 0, 0}, bonusValue = {0, 0, 0, 0};
	public float[] speeds = {0, 0, 0, 0}, halfSpeeds = {0, 0, 0, 0};
	
	private EnemyStats(int hp, float speed, int xp, int explosion) {
		speed *= Stats.U;
		for (int i = 0; i < 4; i++) {
			hps[i] = (int) (hp * Stats.MULTI_HP[i]);
			speeds[i] = speed * Stats.MULTI_SPEED[i];
			speeds[i] = speed * Stats.MULTI_SPEED[i];
			this.xp[i] = (int) (xp * Stats.MULTI_XP[i]);
			bonusValue[i] = xp;
		}
		this.explosion = explosion;
		initHalfHps();
	}

	private EnemyStats(int hp, float speed, float speedMul2, float speedMul3, float speedMul4, int xp, int explosion) {
		speed *= Stats.U;
		for (int i = 0; i < 4; i++) {
			hps[i] = (int) (hp * Stats.MULTI_HP[i]);
			this.xp[i] = (int) (xp * Stats.MULTI_XP[i]);
			bonusValue[i] = xp;
		}
		speeds[0] = speed;
		speeds[1] = speed * speedMul2;
		speeds[2] = speed * speedMul3;
		speeds[3] = speed * speedMul4;
		this.explosion = explosion;
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

	public int getXp() {
		return xp[EndlessMode.difficulty];
	}

	public int getBonusValue() {
		return bonusValue[EndlessMode.difficulty];
	}
}
