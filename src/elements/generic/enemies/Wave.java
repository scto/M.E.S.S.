package elements.generic.enemies;

import jeu.CSG;
import jeu.mode.EndlessMode;
import jeu.mode.extensions.Score;

public class Wave {
	
	public static final int ECART = 1000;
	
	public static final Wave CYLONS = new Wave (0, 0, false, SpawnEnemyPosition.lvl1_remplissage, 0, true, 0);
	public static final Wave CYLON3 = new Wave (0, 0, false, SpawnEnemyPosition.lvl3_remplissage, 0, true, 0);
	public static final Wave CYLON4 = new Wave (0, 0, false, SpawnEnemyPosition.lvl4_remplissage, 0, true, 0);
	public static final Wave VICIOUS = new Wave (0, 0, false, SpawnEnemyPosition.vicous, 0, true, 0);
	public static final Wave KINDER = new Wave (0, 0, false, SpawnEnemyPosition.lvl1_kinder, 0, true, 0);
	public static final Wave KINDER3 = new Wave (0, 0, false, SpawnEnemyPosition.lvl3_kinder, 0, true, 0);
	public static final Wave KINDER4 = new Wave (0, 0, false, SpawnEnemyPosition.lvl4_kinder, 0, true, 0);
	public static final Wave BASICS10 = new Wave (0, 0, false, SpawnEnemyPosition.lvl1_deBase_1_to_10, 0, true, 0);
	public static final Wave BASICS10_LVL3 = new Wave (0, 0, false, SpawnEnemyPosition.lvl3_deBase_1_to_10, 0, true, 0);
	public static final Wave BASICS10_LVL4 = new Wave (0, 0, false, SpawnEnemyPosition.lvl4_deBase_1_to_10, 0, true, 0);
	public static final Wave BASICS_N_DIABOLO = new Wave (0, 0, false, SpawnEnemyPosition.lvl1_remplissageQuiTourne, 0, true, 0);
	
	public static final Wave remplissageDeBase103 = new Wave (0, 0, false, SpawnEnemyPosition.lvl3_deBase_1_to_10, 0, true, 0);
	public static final Wave BASICS_N_DIABOLO3 = new Wave (0, 0, false, SpawnEnemyPosition.lvl3_remplissageQuiTourne, 0, true, 0);
	public static final Wave ROTATION_BALL = new Wave (0, 0, false, SpawnEnemyPosition.remplissageBouleRotation, 0, true, 0);
	public static final Wave GROUP = new Wave (0, 0, false, SpawnEnemyPosition.lvl1_group, 0, true, 0);
	public static final Wave GROUP3 = new Wave (0, 0, false, SpawnEnemyPosition.lvl3_group, 0, true, 0);
	public static final Wave GROUP4 = new Wave (0, 0, false, SpawnEnemyPosition.lvl4_group, 0, true, 0);
	public static final Wave bossMine = new Wave (0, 1000, true, SpawnEnemyPosition.lvl1_bossMine, 100, true, 100000000);
	public static final Wave bossQuad = new Wave (0, 1000, true, SpawnEnemyPosition.lvl1_bossQuad, 100, true, 100000000);
	
	public static final int waveS3 = (int) (3 * CSG.mulSCORE),
							waveS173 = (int) (173 * CSG.mulSCORE),
							wave3 = (int) (290 * CSG.mulSCORE), 
							wave4 = (int) (385 * CSG.mulSCORE),
							wave5 = (int) (545 * CSG.mulSCORE),
							wave6 = (int) (627 * CSG.mulSCORE),
							wave7 = (int) (1000 * CSG.mulSCORE),
							wave8 = (int) (1325 * CSG.mulSCORE),
							wave9 = (int) (2110 * CSG.mulSCORE),
							wave10 = (int) (2627 * CSG.mulSCORE),
							wave11 = (int) (2963 * CSG.mulSCORE),
							wave115 = (int) (3100 * CSG.mulSCORE),
							wave12 = (int) (4000 * CSG.mulSCORE),
							wave13 = (int) (7000 * CSG.mulSCORE),
							wave9k = (int) (9000 * CSG.mulSCORE),
							wave14 = (int) (10000 * CSG.mulSCORE),
							wave15 = (int) (13000 * CSG.mulSCORE);
	
	public static final Wave LVL1_3 = new Wave(			waveS3, 		ECART, 					false, 		SpawnEnemyPosition.lvl1_170, 			.25f, 	true,  800);
	public static final Wave LVL1_170 = new Wave(		waveS173, 		ECART, 					false, 		SpawnEnemyPosition.lvl1_120, 			.25f, 	true,  wave7);
	public static final Wave LVL1_290 =	new Wave(		wave3, 		(int) (ECART * 1.5f), 		false, 		SpawnEnemyPosition.lvl1_94, 			.25f, 	true,  wave7);
	public static final Wave LVL1_385 = new Wave(		wave4, 		(int) (ECART * 1.5f), 		false, 		SpawnEnemyPosition.lvl1_60, 			.25f, 	true,  1250);
	public static final Wave LVL1_445 = new Wave(		wave5, 		ECART, 					false, SpawnEnemyPosition.lvl1_83, 						.25f, 	true,  Progression.MAX);
	public static final Wave LVL1_527 = new Wave(		wave6, 		ECART, 					false, SpawnEnemyPosition.lvl1_100, 			.25f, 	true,  1400);
	public static final Wave LVL1_1055 = new Wave(		wave7, 		(int) (ECART * 1.5f), 	false, SpawnEnemyPosition.lvl1_178, 			.25f, 	true,  Progression.MAX);
	public static final Wave LVL1_1225 = new Wave(		wave8, 		(int) (ECART * 1.4f), 	false, SpawnEnemyPosition.lvl1_85, 			.25f, 	true,  2150);
	public static final Wave LVL1_1310 = new Wave(		wave9, 		ECART * 2, 				false, SpawnEnemyPosition.lvl1_353, 			.25f, 	true,  Progression.MAX);
	public static final Wave LVL1_627 = new Wave(		wave10, 		ECART, 					false, SpawnEnemyPosition.lvl1_350, 			.25f, 	true,  Progression.MAX);
	public static final Wave LVL1_2163 = new Wave(		wave11, 		ECART, 					false, SpawnEnemyPosition.lvl1_500, 			.25f, 	true,  Progression.MAX);
	public static final Wave LVL1_4000 = new Wave(		wave12, 		ECART,					false, SpawnEnemyPosition.lvl1_insecte, 		.25f, 	true,  8000);
	public static final Wave LVL1_8000 = new Wave(		wave13, 		ECART,					false, SpawnEnemyPosition.lvl1_insecte2, 	.25f, 	true,  Progression.MAX);
	public static final Wave LVL1_12000 = new Wave(		wave14, 		ECART, 					false, SpawnEnemyPosition.lvl1_500, 			.25f, 	true,  Progression.MAX);
	public static final Wave LVL1_16000 = new Wave(		wave15, 		(int) (ECART * 1.5f), 	false, SpawnEnemyPosition.satUpnDown, 		.25f, 	true,  Progression.MAX);
	
	public static final float lvl2modif = 0.95f;
	public static final Wave LVL2_3 = new Wave(			(int) (waveS3*lvl2modif), 			ECART, 					false, SpawnEnemyPosition.lvl1_170, 			.25f*lvl2modif, 	CSG.R.nextBoolean(),  800);
	public static final Wave LVL2_170 = new Wave(		(int) (waveS173*lvl2modif), 		ECART, 					false, SpawnEnemyPosition.lvl1_120, 			.25f*lvl2modif, 	CSG.R.nextBoolean(),  wave7);
	public static final Wave LVL2_290 = new Wave(		(int) (wave3*lvl2modif), 		(int) (ECART * 1.5f), 		false, SpawnEnemyPosition.lvl1_94, 			.25f*lvl2modif, 	CSG.R.nextBoolean(),  wave7);
	public static final Wave LVL2_385 = new Wave(		(int) (wave4*lvl2modif), 		(int) (ECART * 1.5f), 		false, SpawnEnemyPosition.lvl1_60, 			.25f*lvl2modif, 	CSG.R.nextBoolean(),  1250);
	public static final Wave LVL2_445 = new Wave(		(int) (wave5*lvl2modif), 		ECART, 						false, SpawnEnemyPosition.lvl1_83, 			.25f*lvl2modif, 	CSG.R.nextBoolean(),  Progression.MAX); // 3 boules
	public static final Wave LVL2_527 = new Wave(		(int) (wave6*lvl2modif), 		ECART, 						false, SpawnEnemyPosition.lvl1_100, 			.25f*lvl2modif, 	CSG.R.nextBoolean(),  1400);
	public static final Wave LVL2_1055 = new Wave(		(int) (wave7*lvl2modif), 		(int) (ECART * 1.5f), 		false, SpawnEnemyPosition.lvl1_178, 			.25f*lvl2modif, 	CSG.R.nextBoolean(),  3000);
	public static final Wave LVL2_1225 = new Wave(		(int) (wave8*lvl2modif), 		(int) (ECART * 1.4f), 		false, SpawnEnemyPosition.lvl1_85, 			.25f*lvl2modif, 	CSG.R.nextBoolean(),  wave9);
	public static final Wave LVL2_1310 = new Wave(		(int) (wave9*lvl2modif), 		ECART * 2, 					false, SpawnEnemyPosition.lvl1_353, 			.25f*lvl2modif, 	CSG.R.nextBoolean(),  Progression.MAX);
	public static final Wave LVL2_2163 = new Wave(		(int) (wave10*lvl2modif), 		ECART, 						false, SpawnEnemyPosition.lvl1_500, 			.25f*lvl2modif, 	CSG.R.nextBoolean(),  Progression.MAX);
	public static final Wave LVL2_627 = new Wave(		(int) (wave11*lvl2modif), 		ECART, 						false, SpawnEnemyPosition.lvl1_350, 			.25f*lvl2modif, 	CSG.R.nextBoolean(),  Progression.MAX);
	public static final Wave LVL2_3000 = new Wave(		(int) (wave115*lvl2modif), 		ECART, 						false, SpawnEnemyPosition.bouleTirCote2, 	.25f*lvl2modif, 	CSG.R.nextBoolean(),  Progression.MAX);
	public static final Wave LVL2_4000 = new Wave(		(int) (wave12*lvl2modif), 		ECART,						false, SpawnEnemyPosition.lvl1_insecte, 		.25f*lvl2modif, 	CSG.R.nextBoolean(),  8000);
	public static final Wave LVL2_8000 = new Wave(		(int) (wave9k*lvl2modif), 		ECART,						false, SpawnEnemyPosition.lvl1_insecte2, 	.25f*lvl2modif, 	CSG.R.nextBoolean(),  Progression.MAX);
	public static final Wave LVL2_12000 = new Wave(		(int) (wave14*lvl2modif), 		ECART, 						false, SpawnEnemyPosition.lvl1_500, 			.25f*lvl2modif, 	CSG.R.nextBoolean(),  Progression.MAX);
	public static final Wave LVL2_16000 = new Wave(		(int) (wave15*lvl2modif), 		(int) (ECART * 1.5f), 		false, SpawnEnemyPosition.satUpnDown, 		.25f*lvl2modif, 	CSG.R.nextBoolean(),  Progression.MAX);
	
	public static final float lvl3modif = 0.90f;
	public static final Wave LVL3_3 =
			new Wave(			(int) (waveS3*lvl3modif), 		(int) (ECART*lvl3modif), 	false, .25f*lvl3modif, 	true,  800, 
			SpawnEnemyPosition.deBase3_12_89_b_b_deBase3_12_89,
			SpawnEnemyPosition.break1,SpawnEnemyPosition.break1, SpawnEnemyPosition.break1, SpawnEnemyPosition.break1,  
			SpawnEnemyPosition.zigZag3_sweep, 
			SpawnEnemyPosition.break1, SpawnEnemyPosition.break1, SpawnEnemyPosition.break1, SpawnEnemyPosition.break1,
			SpawnEnemyPosition.deBase3_Triangle,
			SpawnEnemyPosition.break1, SpawnEnemyPosition.break1, SpawnEnemyPosition.break1, SpawnEnemyPosition.break1
			);
//	public static final Wave lvl3_3 = new Wave(			(int) (waveS3*lvl3modif), 		(int) (ECART*lvl3modif), 	false, SpawnEnemyPosition.lvl3_170, 			.25f*lvl3modif, 	true,  800);
	public static final Wave LVL3_170 = new Wave(		(int) (waveS173*lvl3modif), 	(int) (ECART*lvl3modif), 	false, SpawnEnemyPosition.lvl3_120, 			.25f*lvl3modif, 	true,  wave7);
	public static final Wave LVL3_290 = new Wave(		(int) (wave3*lvl3modif), 		(int) (ECART * 1.4f), 		false, SpawnEnemyPosition.lvl3_94, 			.25f*lvl3modif, 	true,  wave7);
	public static final Wave LVL3_385 = new Wave(		(int) (wave4*lvl3modif), 		(int) (ECART * 1.4f), 		false, SpawnEnemyPosition.lvl3_60, 			.25f*lvl3modif, 	true,  1250);
	public static final Wave LVL3_445 = new Wave(		(int) (wave5*lvl3modif), 		(int) (ECART*lvl3modif), 	false, SpawnEnemyPosition.lvl3_83, 			.25f*lvl3modif, 	true,  Progression.MAX); // 3 boules
	public static final Wave LVL3_527 = new Wave(		(int) (wave6*lvl3modif), 		(int) (ECART*lvl3modif), 	false, SpawnEnemyPosition.lvl3_100, 			.25f*lvl3modif, 	true,  1400);
	public static final Wave LVL3_1055 = new Wave(		(int) (wave7*lvl3modif), 		(int) (ECART * 1.4f), 		false, SpawnEnemyPosition.lvl3_178, 			.25f*lvl3modif, 	true,  3000);
	public static final Wave LVL3_1225 = new Wave(		(int) (wave8*lvl3modif), 		(int) (ECART * 1.3f), 		false, SpawnEnemyPosition.lvl3_85, 			.25f*lvl3modif, 	true,  wave9);
	public static final Wave LVL3_1310 = new Wave(		(int) (wave9*lvl3modif), 		(int) (ECART * 1.5f), 		false, SpawnEnemyPosition.lvl3_353, 			.25f*lvl3modif, 	true,  Progression.MAX);
	public static final Wave LVL3_2163 = new Wave(		(int) (wave10*lvl3modif), 		(int) (ECART*lvl3modif), 	false, SpawnEnemyPosition.lvl3_500, 			.25f*lvl3modif, 	true,  Progression.MAX);
	public static final Wave LVL3_627 = new Wave(		(int) (wave11*lvl3modif), 		(int) (ECART*lvl3modif), 	false, SpawnEnemyPosition.lvl3_350, 			.25f*lvl3modif, 	true,  Progression.MAX);
	public static final Wave LVL3_3000 = new Wave(		(int) (wave115*lvl3modif), 		(int) (ECART*lvl3modif), 	false, SpawnEnemyPosition.bouleTirCote2, 	.25f*lvl3modif, 	true,  Progression.MAX);
	public static final Wave LVL3_4000 = new Wave(		(int) (wave12*lvl3modif), 		(int) (ECART*lvl3modif),	false, SpawnEnemyPosition.lvl3_insecte, 		.25f*lvl3modif, 	true,  8000);
	public static final Wave LVL3_8000 = new Wave(		(int) (wave13*lvl3modif), 		(int) (ECART*lvl3modif),	false, SpawnEnemyPosition.lvl3_insecte2, 	.25f*lvl3modif, 	true,  Progression.MAX);
	public static final Wave LVL3_12000 = new Wave(		(int) (wave14*lvl3modif), 		(int) (ECART*lvl3modif), 	false, SpawnEnemyPosition.lvl3_500, 			.25f*lvl3modif, 	true,  Progression.MAX);
	public static final Wave LVL3_16000 = new Wave(		(int) (wave15*lvl3modif), 		(int) (ECART * 1.4f), 		false, SpawnEnemyPosition.satUpnDown, 		.25f*lvl3modif, 	true,  Progression.MAX);
	
	public static final float lvl4modif = 1f;
	public static final Wave LVL4_3 = new Wave(			(int) (waveS3*lvl4modif), 		(int) (ECART*lvl4modif), 	false, SpawnEnemyPosition.lvl4_170, 			.25f*lvl4modif, 	true,  800);
	public static final Wave LVL4_170 = new Wave(		(int) (waveS173*lvl4modif), 		(int) (ECART*lvl4modif), 	false, SpawnEnemyPosition.lvl4_120, 			.25f*lvl4modif, 	true,  wave7);
	public static final Wave LVL4_290 = new Wave(		(int) (wave3*lvl4modif), 		(int) (ECART * 1.4f), 		false, SpawnEnemyPosition.lvl4_94, 			.25f*lvl4modif, 	true,  wave7);
	public static final Wave LVL4_385 = new Wave(		(int) (wave4*lvl4modif), 		(int) (ECART * 1.4f), 		false, SpawnEnemyPosition.lvl4_60, 			.25f*lvl4modif, 	true,  1250);
	public static final Wave LVL4_445 = new Wave(		(int) (wave5*lvl4modif), 		(int) (ECART*lvl4modif), 	false, SpawnEnemyPosition.lvl4_83, 			.25f*lvl4modif, 	true,  Progression.MAX); // 3 boules
	public static final Wave LVL4_527 = new Wave(		(int) (wave6*lvl4modif), 		(int) (ECART*lvl4modif), 	false, SpawnEnemyPosition.lvl4_100, 			.25f*lvl4modif, 	true,  1400);
	public static final Wave LVL4_1055 = new Wave(		(int) (wave7*lvl4modif), 		(int) (ECART * 1.4f), 		false, SpawnEnemyPosition.lvl4_178, 			.25f*lvl4modif, 	true,  3000);
	public static final Wave LVL4_1225 = new Wave(		(int) (wave8*lvl4modif), 		(int) (ECART * 1.3f), 		false, SpawnEnemyPosition.lvl4_85, 			.25f*lvl4modif, 	true,  wave9);
	public static final Wave LVL4_1310 = new Wave(		(int) (wave9*lvl4modif), 		(int) (ECART * 1.5f), 		false, SpawnEnemyPosition.lvl4_353, 			.25f*lvl4modif, 	true,  Progression.MAX);
	public static final Wave LVL4_2163 = new Wave(		(int) (wave10*lvl4modif), 		(int) (ECART*lvl4modif), 	false, SpawnEnemyPosition.lvl4_500, 			.25f*lvl4modif, 	true,  Progression.MAX);
	public static final Wave LVL4_627 = new Wave(		(int) (wave11*lvl4modif), 		(int) (ECART*lvl4modif), 	false, SpawnEnemyPosition.lvl4_350, 			.25f*lvl4modif, 	true,  Progression.MAX);
	public static final Wave LVL4_3000 = new Wave(		(int) (wave115*lvl4modif), 		(int) (ECART*lvl4modif), 	false, SpawnEnemyPosition.bouleTirCote2, 	.25f*lvl4modif, 	true,  Progression.MAX);
	public static final Wave LVL4_4000 = new Wave(		(int) (wave12*lvl4modif), 		(int) (ECART*lvl4modif),	false, SpawnEnemyPosition.lvl4_insecte, 		.25f*lvl4modif, 	true,  8000);
	public static final Wave LVL4_8000 = new Wave(		(int) (wave13*lvl4modif), 		(int) (ECART*lvl4modif),	false, SpawnEnemyPosition.lvl4_insecte2, 	.25f*lvl4modif, 	true,  Progression.MAX);
	public static final Wave LVL4_12000 = new Wave(		(int) (wave14*lvl4modif), 		(int) (ECART*lvl4modif), 	false, SpawnEnemyPosition.lvl4_500, 			.25f*lvl4modif, 	true,  Progression.MAX);
	public static final Wave LVL4_16000 = new Wave(		(int) (wave15*lvl4modif), 		(int) (ECART * 1.4f), 		false, SpawnEnemyPosition.satUpnDown, 		.25f*lvl4modif, 	true,  Progression.MAX);

	public int nextActivation;
	public final int espaceActivation;
	public boolean active;
	public final boolean boss;
	public final SpawnEnemyPosition[] lignes;
	public final int maxScore, scoreMin;
	public float freqSpawn, nextSpawn;
	public final boolean ordered;
	private int cptPhase;

	
	public Wave(int scoreMin, int espaceActivation, boolean boos, SpawnEnemyPosition[] phases, float freq, boolean ordered, int maxScore) {
		super();
		this.nextActivation = scoreMin;
		this.boss = boos;
		this.freqSpawn = freq;
		this.espaceActivation = espaceActivation;
		this.ordered = CSG.R.nextBoolean();
		this.maxScore = maxScore;
		this.scoreMin = scoreMin;
		this.lignes = phases;
		initCptPhase();
	}
	
	public Wave(int scoreMin, int espaceActivation, boolean boos, float freq, boolean ordered, int maxScore, SpawnEnemyPosition[]... phases) {
		super();
		this.nextActivation = scoreMin;
		this.boss = boos;
		this.freqSpawn = freq;
		this.espaceActivation = espaceActivation;
		this.ordered = CSG.R.nextBoolean();
		this.maxScore = maxScore;
		this.scoreMin = scoreMin;
		this.lignes = convert(phases);
		initCptPhase();
	}

	private SpawnEnemyPosition[] convert(SpawnEnemyPosition[]... phases) {
		int size = 0;
		for (SpawnEnemyPosition[] spawnEnemyPositions : phases) {
			size += spawnEnemyPositions.length;
		}
		SpawnEnemyPosition[] rep = new SpawnEnemyPosition[size];
		int i = 0;
		for (SpawnEnemyPosition[] spawnEnemyPosition : phases) {
			for (SpawnEnemyPosition sep : spawnEnemyPosition) {
				rep[i++] = sep;
			}
		}
		return rep;
	}

	public void mightSpawn() {
		if (nextSpawn < EndlessMode.now) {
			for (int i = 0; i < lignes[cptPhase].merlin.length; i++) {
				final Enemy inv = lignes[cptPhase].merlin[i].incantation.invoke();
				if (lignes[cptPhase].positions[i] != null) 
					inv.setPosition(lignes[cptPhase].positions[i]);
			}
			
			if (ordered)
				cptPhase++;
			else
				cptPhase--;
			nextSpawn = EndlessMode.now + freqSpawn;
			
			if (cptPhase >= lignes.length)
				finish();
			if (cptPhase < 0)
				finish();
		}
	}

	private void finish() {
		active = false;
		nextActivation = (int) (Score.score + espaceActivation);
		if (maxScore < Score.score)
			nextActivation = Progression.MAX;
		if (boss) {
			Progression.incrementNextBoss(120f);
			Progression.incrementWavesCheck(25f);
		}
	}
	
	public void boss (int espaceSupplementaire) {
		nextActivation += espaceSupplementaire;
	}
	

	public boolean mightActivate() {
		if (nextActivation < Score.score) {
			activation();
			return true;
		}
		return false;
	}

	private void activation() {
		active = true;
		nextSpawn = 0;
		initCptPhase();
	}

	private void initCptPhase() {
		// A VIRER
		// A VIRER
		// A VIRER
		// A VIRER
		// A VIRER
		// A VIRER
		// A VIRER
		// A VIRER
		// A VIRER
		// A VIRER
		// A VIRER
		// A VIRER
		// A VIRER
		// A VIRER
		// A VIRER
		// A VIRER
		// A VIRER
		// A VIRER
		// A VIRER
		// A VIRER
		if (lignes == null)
			return;
		if (!ordered) 	cptPhase = lignes.length - 1;
		else			cptPhase = 0;
	}

	public void reset() {
		active = false;
		initCptPhase();
		nextActivation = scoreMin;
		nextSpawn = 0;
	}

	public void compensateBoss(float differenceScore) {
		nextActivation += differenceScore;
	}

	public void activate() {
		activation();
	}
}
