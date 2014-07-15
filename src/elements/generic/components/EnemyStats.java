package elements.generic.components;

import jeu.Stats;
import jeu.mode.EndlessMode;

import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.Element;
import elements.generic.enemies.Enemy;
import elements.generic.enemies.individual.lvl1.Basic;
import elements.generic.enemies.individual.lvl1.Insect;

public enum EnemyStats {
	
	BASIC(
			8,
			9, Stats.MULTI_SPEED[1], Stats.MULTI_SPEED[2], Stats.MULTI_SPEED[3] * 2,
			1,
			25),
	GROUP(
			60,
			4,
			18,
			35),
	SHOOTER(
			27,
			5,
			10,
			40), 
	ZIGZAG(
			13,
			1,
			3,
			40),
	BALL(
			40,
			25,
			12,
			35),
	BALL_SIDE_SHOT(
			60,
			25,
			73,
			40),
	SAT(
			430,
			3,
			80,
			80),
	DIABOLO(
			67,
			9,
			19,
			25),
	ROUND_N_ROUND(
			80,
			10,
			21,
			30), 
	CYLON(
			90,
			6,
			12,
			35),
	KINDER(
			120,
			10,
			32,
			45),
	BOSS_QUAD(
			510,
			15,
			200,
			200),
	CRUSADER(
			320,
			4, 1, 1, 1,
			91,
			40),
	PLANE(
			150,
			9.5f,
			45,
			55),
	SHOOTER_FRAG(
			200,
			6,
			39,
			50),
	BOSS_MINE(
			780,
			24,
			200,
			200),
	LASER(
			190,
			7,
			32,
			50),
	INSECT(
			380,
			6,
			86,
			50),
	ADD_SAT(
			25,
			7,
			15,
			25),
	VICIOUS(
			0,
			2,
			0,
			50);
	
	public int explosion = 0;
	public int[] hps = {0, 0, 0, 0}, halfHps = {0, 0, 0, 0}, oneThirdHps = {0, 0, 0, 0}, twoThirdsHps = {0, 0, 0, 0}, xp = {0, 0, 0, 0}, bonusValue = {0, 0, 0, 0};
	public float[] speeds = {0, 0, 0, 0}, halfSpeeds = {0, 0, 0, 0};
	
	private EnemyStats(int hp, float speed, int xp, int explosion) {
		this(hp, xp, explosion);
		speed *= Stats.U;
		for (int i = 0; i < 4; i++) {
			speeds[i] = speed * Stats.MULTI_SPEED[i];
			speeds[i] = speed * Stats.MULTI_SPEED[i];
		}
		initHalfSpeeds();
	}
	
	private void initHalfSpeeds() {
		for (int i = 0; i < 4; i++)
			halfSpeeds[i] = speeds[i] / 2;
	}

	private EnemyStats(int hp, int xp, int explosion) {
		for (int i = 0; i < 4; i++) {
			this.xp[i] = (int) (xp * Stats.MULTI_XP[i]);
			bonusValue[i] = xp;
			
			hps[i] = (int) (hp * Stats.MULTI_HP[i]);
			halfHps[i] = hps[i] / 2;
			oneThirdHps[i] = hps[i] / 3;
			twoThirdsHps[i] = (int) (hps[i] * 0.666f);
		}
		this.explosion = explosion;
	}

	private EnemyStats(int hp, float speed, float speedMul2, float speedMul3, float speedMul4, int xp, int explosion) {
		this(hp, xp, explosion);
		speed *= Stats.U;
		speeds[0] = speed;
		speeds[1] = speed * speedMul2;
		speeds[2] = speed * speedMul3;
		speeds[3] = speed * speedMul4;
		initHalfSpeeds();
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
