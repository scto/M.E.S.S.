package elements.generic.enemies;

import jeu.CSG;
import jeu.mode.EndlessMode;
import jeu.mode.extensions.Score;

public final class Progression {

	
	public static final float FREQ_APPARATION = 1;
	public static final int MAX = 999999999;
	private static boolean poped, bossJustPoped = false;
	private static float nextNormalWavesCheck = 0, nextBoss = 0, beginBossScore, graceTime = 0;
	private static int nbEnemiesMax = 0;
	// the minimum time between two wave activations
	private static final float KEEP_COOL = 2f;
	
	private final static Wave[] FILLING = {
		Wave.CYLON_OR_BALL1, Wave.BASICS10, Wave.KINDER, Wave.BASICS_N_DIABOLO, Wave.GROUP, Wave.VICIOUS		};
	private final static Wave[] FILLING3 = {
		Wave.CYLON_OR_BALL_LVL3, Wave.BASICS10_LVL3, Wave.KINDER3, Wave.BASICS_N_DIABOLO3, Wave.ROTATION_BALL, Wave.GROUP3, Wave.VICIOUS		};
	private final static Wave[] FILLING4 = {
		Wave.CYLON_OR_BALL_LVL4, Wave.BASICS10_LVL4, Wave.KINDER4, Wave.GROUP4, Wave.VICIOUS		};
	private final static Wave[] LVL1 = {
		Wave.LVL1_3,
		Wave.LVL1_170,
		Wave.LVL1_290,
		Wave.LVL1_385,
		Wave.LVL1_445,
		Wave.LVL1_527,
		Wave.LVL1_627,
		Wave.LVL1_1055,
		Wave.LVL1_1225,
		Wave.LVL1_1310,
		Wave.LVL1_2163,
		Wave.LVL1_4000,
		Wave.LVL1_8000,
		Wave.LVL1_12000,
		Wave.LVL1_16000
	};
	private final static Wave[] LVL2 = {
		Wave.LVL2_3,
		Wave.LVL2_170,
		Wave.LVL2_290,
		Wave.LVL2_385,
		Wave.LVL2_445,
		Wave.LVL2_527,
		Wave.LVL2_627,
		Wave.LVL2_1055,
		Wave.LVL2_1225,
		Wave.LVL2_1310,
		Wave.LVL2_2163,
		Wave.LVL2_3000,
		Wave.LVL2_4000,
		Wave.LVL2_8000,
		Wave.LVL2_12000,
		Wave.LVL2_16000
	};
	private final static Wave[] LVL3 = {
		Wave.LVL3_3,
		Wave.LVL3_170,
		Wave.LVL3_290,
		Wave.LVL3_385,
		Wave.LVL3_445,
		Wave.LVL3_527,
		Wave.LVL3_627,
		Wave.LVL3_1055,
		Wave.LVL3_1225,
		Wave.LVL3_1310,
		Wave.LVL3_2163,
		Wave.LVL3_3000,
		Wave.LVL3_4000,
		Wave.LVL3_8000,
		Wave.LVL3_12000,
		Wave.LVL3_16000
	};
	private final static Wave[] LVL4 = {
		Wave.LVL4_3,
		Wave.LVL4_170,
		Wave.LVL4_290,
		Wave.LVL4_385,
		Wave.LVL4_445,
		Wave.LVL4_527,
		Wave.LVL4_627,
		Wave.LVL4_1055,
		Wave.LVL4_1225,
		Wave.LVL4_1310,
		Wave.LVL4_2163,
		Wave.LVL4_3000,
		Wave.LVL4_4000,
		Wave.LVL4_8000,
		Wave.LVL4_12000,
		Wave.LVL4_16000
	};
	private final static Wave[] BOSSES = { Wave.MINE, Wave.QUAD	};
	
	private Progression() {
	}

	public static void reset() {
		nextNormalWavesCheck = 0;
		nextBoss = 90;

		resetWaves(LVL1);
		resetWaves(LVL2);
		resetWaves(LVL3);
		resetWaves(LVL4);
		resetWaves(FILLING);
		resetWaves(FILLING3);
		resetWaves(FILLING4);
		resetWaves(BOSSES);
		graceTime = 0;
	}

	private static void resetWaves(Wave[] waves) {
		for (Wave wave : waves)
			wave.reset();
	}

	public static void invoqueBaseOnScore() {
		if (nextBoss < EndlessMode.now) {
			if (bossJustPoped) {
				spawnBoss();
			} else if (Enemy.LIST.size < 5) {
				activateRandomBoss();
			}
		} else if (nextNormalWavesCheck < EndlessMode.now) {
			nbEnemiesMax = (int) (14 + (Score.score / 5000));
			if (Enemy.LIST.size < nbEnemiesMax) {
				if (bossJustPoped)
					compensateForBossTime();
				switch (EndlessMode.difficulty) {
				case 1 :	checkWaves(LVL1);	break;
				case 2 :	checkWaves(LVL2);	break;
				case 3 :	checkWaves(LVL3);	break;
				case 4 :	checkWaves(LVL4);	break;
				}
			} 
			nextNormalWavesCheck = EndlessMode.now + 0.10f;
		}
		switch (EndlessMode.difficulty) {
		case 1 :
		case 2 :	remplissage(FILLING);		break;
		case 3 :	remplissage(FILLING3);	break;
		case 4 :	remplissage(FILLING4);	break;
		}
	}

	private static void checkWaves(Wave[] waves) {
		for (Wave wave : waves) {
			if (wave.active)
				wave.mightSpawn();
			else if (graceTime < EndlessMode.now && wave.mightActivate())
				graceTime = EndlessMode.now + KEEP_COOL;
		}
	}

	static boolean tmpCheck = false;
	private static void remplissage(Wave[] waves) {
		if (Enemy.LIST.size < 2 + Score.score / 10000 &&
				Score.score > 20 &&
				!bossJustPoped &&
				graceTime < EndlessMode.now) {
			switch (EndlessMode.difficulty) {
			case 1 :	tmpCheck = hasAnActiveWave(LVL1);		break;
			case 2 :	tmpCheck = hasAnActiveWave(LVL2);		break;
			case 3 :	tmpCheck = hasAnActiveWave(LVL3);		break;
			case 4 :	tmpCheck = hasAnActiveWave(LVL4);		break;
			}
			if (!tmpCheck) {
				waves[CSG.R.nextInt(waves.length)].activate();
			}
		}
		for (Wave wave : waves)
			if (wave.active)
				wave.mightSpawn();
	}

	private static void compensateForBossTime() {
		bossJustPoped = false;
		switch(EndlessMode.difficulty) {
		case 1:		compensateBoss(Score.score - beginBossScore, LVL1);		break;
		case 2:		compensateBoss(Score.score - beginBossScore, LVL2);		break;
		case 3:		compensateBoss(Score.score - beginBossScore, LVL3);		break;
		case 4:		compensateBoss(Score.score - beginBossScore, LVL4);		break;
		}
	}

	private static void compensateBoss(final float differenceScore, Wave[] waves) {
		for (Wave wave : waves)
			wave.compensateBoss(differenceScore);
	}

	private static void spawnBoss() {
		for (Wave w : BOSSES)
			if (w.active)
				w.mightSpawn();
	}

	private static boolean hasAnActiveWave(Wave[] waves) {
		for (Wave w : waves)
			if (w.active)
				return true;
		return false;
	}

	private static void activateRandomBoss() {
		Enemy.attackAllEnemies(Enemy.superBomb);
		poped = false;
		while (!poped)
			poped = BOSSES[CSG.R.nextInt(BOSSES.length)].mightActivate();
		bossJustPoped = true;
		beginBossScore = Score.score;
	}

	public static void incrementNextBoss(float time) {
		nextBoss = EndlessMode.now + time;
	}

	public static void incrementWavesCheck(float f) {
		nextNormalWavesCheck = EndlessMode.now + f;
	}

	public static void bossDied() {
		nextNormalWavesCheck = EndlessMode.now;
		bossJustPoped = false;
	}
}