package elements.generic.enemies;

import jeu.CSG;
import jeu.EndlessMode;

public final class Progression {

	
	public static final float FREQ_APPARATION = 1;
	public static final int MAX = 999999999;
	private static boolean poped, bossJustPoped = false;
	private static float nextNormalWavesCheck = 0, nextBoss = 0, beginBossScore, graceTime = 0;
	private static int nbEnemiesMax = 0;
	// the minimum time between two wave activations
	private static final float graceDelay = 4f;
	
	private final static Wave[] remplissage = {
		Wave.remplissageCylon, Wave.remplissageDeBase10, Wave.remplissageKinder, Wave.remplissageQuiTourne, Wave.group, Wave.remplissageVicous		};
	private final static Wave[] remplissageLvl3 = {
		Wave.remplissageCylon3, Wave.remplissageDeBase310, Wave.remplissageKinder3, Wave.remplissageQuiTourne3, Wave.remplissageBouleRotation, Wave.group3, Wave.remplissageVicous		};
	private final static Wave[] remplissageLvl4 = {
		Wave.remplissageCylon4, Wave.remplissageDeBase410, Wave.remplissageKinder4, Wave.group4, Wave.remplissageVicous		};
	private final static Wave[] wavesLvl1 = {
		Wave.lvl1_3,
		Wave.lvl1_170,
		Wave.lvl1_290,
		Wave.lvl1_385,
		Wave.lvl1_445,
		Wave.lvl1_527,
		Wave.lvl1_627,
		Wave.lvl1_1055,
		Wave.lvl1_1225,
		Wave.lvl1_1310,
		Wave.lvl1_2163,
		Wave.lvl1_4000,
		Wave.lvl1_8000,
		Wave.lvl1_12000,
		Wave.lvl1_16000
	};
	private final static Wave[] wavesLvl2 = {
		Wave.lvl2_3,
		Wave.lvl2_170,
		Wave.lvl2_290,
		Wave.remplissageVicous,
		Wave.lvl2_385,
		Wave.lvl2_445,
		Wave.lvl2_527,
		Wave.lvl2_627,
		Wave.lvl2_1055,
		Wave.lvl2_1225,
		Wave.lvl2_1310,
		Wave.lvl2_2163,
		Wave.lvl2_3000,
		Wave.lvl2_4000,
		Wave.lvl2_8000,
		Wave.lvl2_12000,
		Wave.lvl2_16000
	};
	private final static Wave[] wavesLvl3 = {
		Wave.lvl3_3,
		Wave.lvl3_170,
		Wave.lvl3_290,
		Wave.lvl3_385,
		Wave.lvl3_445,
		Wave.lvl3_527,
		Wave.lvl3_627,
		Wave.lvl3_1055,
		Wave.lvl3_1225,
		Wave.lvl3_1310,
		Wave.lvl3_2163,
		Wave.lvl3_3000,
		Wave.lvl3_4000,
		Wave.lvl3_8000,
		Wave.lvl3_12000,
		Wave.lvl3_16000
	};
	private final static Wave[] wavesLvl4 = {
		Wave.lvl4_3,
		Wave.lvl4_170,
		Wave.lvl4_290,
		Wave.lvl4_385,
		Wave.lvl4_445,
		Wave.lvl4_527,
		Wave.lvl4_627,
		Wave.lvl4_1055,
		Wave.lvl4_1225,
		Wave.lvl4_1310,
		Wave.lvl4_2163,
		Wave.lvl4_3000,
		Wave.lvl4_4000,
		Wave.lvl4_8000,
		Wave.lvl4_12000,
		Wave.lvl4_16000
	};
	private final static Wave[] bosses = {Wave.bossMine, Wave.bossQuad	};
	
	private Progression() {
	}

	public static void reset() {
		nextNormalWavesCheck = 0;
		nextBoss = 90;

		resetWaves(wavesLvl1);
		resetWaves(wavesLvl2);
		resetWaves(wavesLvl3);
		resetWaves(wavesLvl4);
		resetWaves(remplissage);
		resetWaves(remplissageLvl3);
		resetWaves(remplissageLvl4);
		resetWaves(bosses);
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
			nbEnemiesMax = (int) (14 + (EndlessMode.score / 5000));
			if (Enemy.LIST.size < nbEnemiesMax) {
				if (bossJustPoped)
					compensateForBossTime();
				switch (EndlessMode.difficulty) {
				case 1 :	checkWaves(wavesLvl1);	break;
				case 2 :	checkWaves(wavesLvl2);	break;
				case 3 :	checkWaves(wavesLvl3);	break;
				case 4 :	checkWaves(wavesLvl4);	break;
				}
			} 
			nextNormalWavesCheck = EndlessMode.now + 0.10f;
		}
		switch (EndlessMode.difficulty) {
		case 1 :
		case 2 :	remplissage(remplissage);		break;
		case 3 :	remplissage(remplissageLvl3);	break;
		case 4 :	remplissage(remplissageLvl4);	break;
		}
	}

	private static void checkWaves(Wave[] waves) {
		for (Wave wave : waves) {
			if (wave.active)
				wave.mightSpawn();
			else if (graceTime < EndlessMode.now && wave.mightActivate())
				graceTime = EndlessMode.now + graceDelay;
		}
	}

	static int tmp = 0;
	static boolean tmpCheck = false;
	private static void remplissage(Wave[] waves) {
		if (Enemy.LIST.size < 2 + EndlessMode.score / 10000 && EndlessMode.score > 20 && !bossJustPoped) {
			switch (EndlessMode.difficulty) {
			case 1 :	tmpCheck = hasAnActiveWave(wavesLvl1);		break;
			case 2 :	tmpCheck = hasAnActiveWave(wavesLvl2);		break;
			case 3 :	tmpCheck = hasAnActiveWave(wavesLvl3);		break;
			case 4 :	tmpCheck = hasAnActiveWave(wavesLvl4);		break;
			}
			if (!tmpCheck) {
				tmp = CSG.R.nextInt(waves.length);
				waves[tmp].activate();
			}
		}
		for (Wave wave : waves)
			if (wave.active)
				wave.mightSpawn();
	}

	private static void compensateForBossTime() {
		bossJustPoped = false;
		switch(EndlessMode.difficulty) {
		case 1:		compensateBoss(EndlessMode.score - beginBossScore, wavesLvl1);		break;
		case 2:		compensateBoss(EndlessMode.score - beginBossScore, wavesLvl2);		break;
		case 3:		compensateBoss(EndlessMode.score - beginBossScore, wavesLvl3);		break;
		case 4:		compensateBoss(EndlessMode.score - beginBossScore, wavesLvl4);		break;
		}
	}

	private static void compensateBoss(final float differenceScore, Wave[] waves) {
		for (Wave wave : waves)
			wave.compensateBoss(differenceScore);
	}

	private static void spawnBoss() {
		for (Wave w : bosses)
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
			poped = bosses[CSG.R.nextInt(bosses.length)].mightActivate();
		bossJustPoped = true;
		beginBossScore = EndlessMode.score;
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