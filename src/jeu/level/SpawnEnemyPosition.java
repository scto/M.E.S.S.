package jeu.level;

import jeu.CSG;

import com.badlogic.gdx.math.Vector2;

import elements.generic.components.enemies.Merlin;
import elements.generic.enemies.individual.bosses.Sat;

public class SpawnEnemyPosition {
	public static final Vector2 middle = new Vector2(CSG.halfWidth, CSG.height),
	_05sur10 = new Vector2(CSG.width * 0.05f, CSG.height),
	_1sur10 = new Vector2(CSG.width * 0.1f, CSG.height),
	_2sur10 = new Vector2(CSG.width * 0.2f, CSG.height),
	_3sur10 = new Vector2(CSG.width * 0.3f, CSG.height),
	_4sur10 = new Vector2(CSG.width * 0.4f, CSG.height),
	_5sur10 = new Vector2(CSG.width * 0.5f, CSG.height),
	_6sur10 = new Vector2(CSG.width * 0.6f, CSG.height),
	_7sur10 = new Vector2(CSG.width * 0.7f, CSG.height),
	_8sur10 = new Vector2(CSG.width * 0.8f, CSG.height),
	_9sur10 = new Vector2(CSG.width * 0.9f, CSG.height),
	_95sur10 = new Vector2(CSG.width * 0.95f, CSG.height);
	private static final Vector2 _bossSatUp = new Vector2((CSG.halfWidth-1)+Sat.DIMENSIONS.halfWidth, CSG.height * 0.7f);
	private static final Vector2 _bossSatDown = new Vector2((CSG.halfWidth-1)+Sat.DIMENSIONS.halfWidth, CSG.height * 0.35f);
	
	private static final Vector2 pauseVec[] = {};
	private static final Vector2[] _1random = {null};
	private static final Vector2[] _1middle = {middle};
	private static final Vector2[] _1SatDown = {_bossSatDown};
	private static final Vector2[] _1SatUp = {_bossSatUp};
	private static final Vector2[] _1_3_5_7_9 = {_1sur10, _3sur10, _5sur10, _7sur10, _9sur10};
	private static final Vector2[] _1_2_3_7_8_9 = {_1sur10, _2sur10, _3sur10, _7sur10, _8sur10, _9sur10};
	private static final Vector2[] _3_4_6_7 = {_3sur10, _4sur10, _6sur10, _7sur10};
	private static final Vector2[] _2_3_7_8 = {_2sur10, _3sur10, _7sur10, _8sur10};
	private static final Vector2[] _1_2_8_9 = {_1sur10, _2sur10, _8sur10, _9sur10};
	private static final Vector2[] _1 = {_1sur10};
	private static final Vector2[] _2 = {_2sur10};
	private static final Vector2[] _3 = {_3sur10};
	private static final Vector2[] _4 = {_4sur10};
	private static final Vector2[] _5 = {_5sur10};
	private static final Vector2[] _6 = {_6sur10};
	private static final Vector2[] _7 = {_7sur10};
	private static final Vector2[] _8 = {_8sur10};
	private static final Vector2[] _9 = {_9sur10};
	private static final Vector2[] _4_5_6 = {_4sur10, _5sur10, _6sur10};
	private static final Vector2[] _4_6 = {_4sur10, _6sur10};
	private static final Vector2[] _3_7 = {_3sur10, _7sur10};
	private static final Vector2[] _2_8 = {_2sur10, _8sur10};
	private static final Vector2[] _2_5_8 = {_2sur10, _5sur10, _8sur10};
	private static final Vector2[] _1_9 = {_1sur10, _9sur10};
	private static final Vector2[] _0_10 = {_05sur10, _95sur10};
	public static final Merlin[] pauseInv = {};
	private static final Merlin[] 	_1deBase = {Merlin.DE_BASE},
									_2deBase = {Merlin.DE_BASE, Merlin.DE_BASE},
									_3deBase = {Merlin.DE_BASE, Merlin.DE_BASE, Merlin.DE_BASE },
									_4deBase = {Merlin.DE_BASE, Merlin.DE_BASE, Merlin.DE_BASE, Merlin.DE_BASE},
									_6deBase = {Merlin.DE_BASE, Merlin.DE_BASE, Merlin.DE_BASE, Merlin.DE_BASE, Merlin.DE_BASE, Merlin.DE_BASE},
									_5deBase = {Merlin.DE_BASE, Merlin.DE_BASE, Merlin.DE_BASE, Merlin.DE_BASE, Merlin.DE_BASE},
									_1deBase3 = {Merlin.DE_BASE3},
									_2deBase3 = {Merlin.DE_BASE3, Merlin.DE_BASE3},
									_3deBase3 = {Merlin.DE_BASE3, Merlin.DE_BASE3, Merlin.DE_BASE3},
									_4deBase3 = {Merlin.DE_BASE3, Merlin.DE_BASE3, Merlin.DE_BASE3, Merlin.DE_BASE3},
									_5deBase3 = {Merlin.DE_BASE3, Merlin.DE_BASE3, Merlin.DE_BASE3, Merlin.DE_BASE3, Merlin.DE_BASE3},
									_1deBase4 = {Merlin.DE_BASE4},
									_2deBase4 = {Merlin.DE_BASE4, Merlin.DE_BASE4},
									_3deBase4 = {Merlin.DE_BASE4, Merlin.DE_BASE4, Merlin.DE_BASE4},
									_4deBase4 = {Merlin.DE_BASE4, Merlin.DE_BASE4, Merlin.DE_BASE4, Merlin.DE_BASE4},
									_5deBase4 = {Merlin.DE_BASE4, Merlin.DE_BASE4, Merlin.DE_BASE4, Merlin.DE_BASE4, Merlin.DE_BASE4};
			
	private static final Merlin[] 	_1zigZag = {Merlin.ZIGZAG},
									_1zigZag3 = {Merlin.ZIGZAG3},
									_2zigZag3 = {Merlin.ZIGZAG3, Merlin.ZIGZAG3},
									_1zigZag4 = {Merlin.ZIGZAG4},
									_2zigZag4 = {Merlin.ZIGZAG4, Merlin.ZIGZAG4},
									_2zigZag = {Merlin.ZIGZAG, Merlin.ZIGZAG};
	
	private static final Merlin[] _1quiTir = {Merlin.SHOOTER};
	private static final Merlin[] _1quiTir3 = {Merlin.SHOOTER3};
	private static final Merlin[] _2quiTir3 = {Merlin.SHOOTER3, Merlin.SHOOTER3};
	private static final Merlin[] _1quiTir4 = {Merlin.SHOOTER4};
	private static final Merlin[] _2quiTir4 = {Merlin.SHOOTER4, Merlin.SHOOTER4};
	private static final Merlin[] _2quiTir = {Merlin.SHOOTER, Merlin.SHOOTER};
	private static final Merlin[] _1boule = {Merlin.BALL};
	private static final Merlin[] _1boule3 = {Merlin.BALL3};
	private static final Merlin[] _1boule4 = {Merlin.BALL4};
	private static final Merlin[] _1bouleRotation = {Merlin.BALL_SIDE_SHOT_ROTATION};
	private static final Merlin[] _1bouleTirCote = {Merlin.BALL_SIDE_SHOT};
	private static final Merlin[] _2boules = {Merlin.BALL, Merlin.BALL};
	private static final Merlin[] _2boules3 = {Merlin.BALL3, Merlin.BALL3};
	private static final Merlin[] _3boules3 = {Merlin.BALL3, Merlin.BALL3, Merlin.BALL3};
	private static final Merlin[] _2boules4 = {Merlin.BALL4, Merlin.BALL4};
	private static final Merlin[] _3boules4 = {Merlin.BALL4, Merlin.BALL4, Merlin.BALL4};
	private static final Merlin[] _3boules = {Merlin.BALL, Merlin.BALL, Merlin.BALL};
	private static final Merlin[] _1toupie = {Merlin.ROUND_N_ROUND};
	private static final Merlin[] _1toupie3 = {Merlin.ROUND_N_ROUND3};
	private static final Merlin[] _1toupie4 = {Merlin.ROUND_N_ROUND4};
	private static final Merlin[] _1quiTourne3 = {Merlin.DIABOLO3};
	private static final Merlin[] _1quiTourne4 = {Merlin.DIABOLO4};
	private static final Merlin[] _1DIABOLO = {Merlin.DIABOLO};
	private static final Merlin[] _1kinder3 = {Merlin.KINDER3};
	private static final Merlin[] _1kinder4 = {Merlin.KINDER4};
	private static final Merlin[] _1kinder = {Merlin.KINDER};
	private static final Merlin[] _1cylon3 = {Merlin.CYLON3};
	private static final Merlin[] _1cylon4 = {Merlin.CYLON4};
	private static final Merlin[] _1cylon = {Merlin.CYLON};
	private static final Merlin[] _1avion3 = {Merlin.PLANE3};
	private static final Merlin[] _1avion4 = {Merlin.PLANE4};
	private static final Merlin[] _1avion = {Merlin.PLANE};
	private static final Merlin[] _2avions = {Merlin.PLANE, Merlin.PLANE};
	private static final Merlin[] _2avions4 = {Merlin.PLANE4, Merlin.PLANE4};
	private static final Merlin[] _2avions3 = {Merlin.PLANE3, Merlin.PLANE3};
	private static final Merlin[] _1bossQuad = {Merlin.BOSS_QUAD};
	private static final Merlin[] _1bossMine = {Merlin.BOSS_MINE};
	private static final Merlin[] _1vicious = {Merlin.VICIOUS};
	private static final Merlin[] _1Sat = {Merlin.BOSS_SAT};
	
	private static final Merlin[] _1laser = {Merlin.LASER};
	private static final Merlin[] _1laser3 = {Merlin.LASER3};
	private static final Merlin[] _1laser4 = {Merlin.LASER4};
	private static final Merlin[] _1quiTir2 = {Merlin.SHOOTER_FRAG};
	private static final Merlin[] _1quiTir23 = {Merlin.SHOOTER_FRAG3};
	private static final Merlin[] _1quiTir24 = {Merlin.SHOOTER_FRAG4};
	private static final Merlin[] _2quiTir2 = {Merlin.SHOOTER_FRAG, Merlin.SHOOTER_FRAG};
	private static final Merlin[] _2quiTir23 = {Merlin.SHOOTER_FRAG3, Merlin.SHOOTER_FRAG3};
	
	private static final Merlin[] _2porteRaisin3 = {Merlin.CRUSADER3, Merlin.CRUSADER3};
	private static final Merlin[] _2porteRaisin4 = {Merlin.CRUSADER4, Merlin.CRUSADER4};
	private static final Merlin[] _2porteRaisin = {Merlin.CRUSADER, Merlin.CRUSADER};
	private static final Merlin[] _1insecte = {Merlin.INSECT};
	private static final Merlin[] _1insecte3 = {Merlin.INSECT3};
	private static final Merlin[] _1insecte4 = {Merlin.INSECT4};
	private static final Merlin[] _1group = {Merlin.GROUP},_1group3 = {Merlin.GROUP3}, _1group4 = {Merlin.GROUP4};
	public static final SpawnEnemyPosition pause = new SpawnEnemyPosition(pauseInv, pauseVec);
	private static final SpawnEnemyPosition 	deBaseCentre = new SpawnEnemyPosition(_1deBase, _1middle),
								deBase_4_6 = new SpawnEnemyPosition(_2deBase, _4_6),
								deBase_3_7 = new SpawnEnemyPosition(_2deBase, _3_7), 
								deBase_2_8 = new SpawnEnemyPosition(_2deBase, _2_8), 
								deBase_1_9 = new SpawnEnemyPosition(_2deBase, _1_9),
								deBase_4_5_7 = new SpawnEnemyPosition(_3deBase, _4_5_6),
								deBase_3_4_6_7 = new SpawnEnemyPosition(_4deBase, _3_4_6_7),
								deBase_2_3_7_8 = new SpawnEnemyPosition(_4deBase, _2_3_7_8),
								deBase_1_2_8_9 = new SpawnEnemyPosition(_4deBase, _1_2_8_9),
								deBase_1_3_5_7_9 = new SpawnEnemyPosition(_5deBase, _1_3_5_7_9),
								deBase3_1_3_5_7_9 = new SpawnEnemyPosition(_5deBase3, _1_3_5_7_9),
								deBase3Centre = new SpawnEnemyPosition(_1deBase3, _1middle),
								deBase3_4_6 = new SpawnEnemyPosition(_2deBase3, _4_6),
								deBase3_3_7 = new SpawnEnemyPosition(_2deBase3, _3_7), 
								deBase3_2_8 = new SpawnEnemyPosition(_2deBase3, _2_8), 
								deBase3_1_9 = new SpawnEnemyPosition(_2deBase3, _1_9),
								deBase3_4_5_7 = new SpawnEnemyPosition(_3deBase3, _4_5_6),
								deBase3_1_2_8_9 = new SpawnEnemyPosition(_4deBase3, _1_2_8_9),
								deBase4_1_3_5_7_9 = new SpawnEnemyPosition(_5deBase4, _1_3_5_7_9),
								deBase4Centre = new SpawnEnemyPosition(_1deBase4, _1middle),
								deBase4_4_6 = new SpawnEnemyPosition(_2deBase4, _4_6),
								deBase4_3_7 = new SpawnEnemyPosition(_2deBase4, _3_7), 
								deBase4_2_8 = new SpawnEnemyPosition(_2deBase4, _2_8), 
								deBase4_1_9 = new SpawnEnemyPosition(_2deBase4, _1_9),
								deBase4_4_5_7 = new SpawnEnemyPosition(_3deBase4, _4_5_6),
								deBase4_1_2_8_9 = new SpawnEnemyPosition(_4deBase4, _1_2_8_9),
								deBase_1_2_3_7_8_9 = new SpawnEnemyPosition(_6deBase, _1_2_3_7_8_9);
	private static final SpawnEnemyPosition 	bouleRnd = new SpawnEnemyPosition(_1boule, _1random),
								boule3Rnd = new SpawnEnemyPosition(_1boule3, _1random),
								boule4Rnd = new SpawnEnemyPosition(_1boule4, _1random),
								bouleMid = new SpawnEnemyPosition(_1boule, _1middle),
								boule4Mid = new SpawnEnemyPosition(_1boule4, _1middle),
								boule1_9 = new SpawnEnemyPosition(_2boules, _1_9),
								boule31_9 = new SpawnEnemyPosition(_2boules3, _1_9),
								boule32_5_8 = new SpawnEnemyPosition(_3boules3, _2_5_8),
								boule41_9 = new SpawnEnemyPosition(_2boules4, _1_9),
								boule42_5_8 = new SpawnEnemyPosition(_3boules4, _2_5_8),
								boule2_5_8 = new SpawnEnemyPosition(_3boules, _2_5_8),
								boule3_7 = new SpawnEnemyPosition(_2boules, _3_7),
								boule44_6 = new SpawnEnemyPosition(_2boules4, _4_6),
								boule34_6 = new SpawnEnemyPosition(_2boules3, _4_6);
	private static final SpawnEnemyPosition 	_toupieRnd = new SpawnEnemyPosition(_1toupie, _1random),
								_toupie3Rnd = new SpawnEnemyPosition(_1toupie3, _1random),
								_toupie4Rnd = new SpawnEnemyPosition(_1toupie4, _1random),
								_toupie8 = new SpawnEnemyPosition(_1toupie, _8);
	private static final SpawnEnemyPosition 
								zigZag3Rnd = new SpawnEnemyPosition(_1zigZag3, _1random),
								zigZag4Rnd = new SpawnEnemyPosition(_1zigZag4, _1random),
								zigZagRight = new SpawnEnemyPosition(_1zigZag, _9),
								zigZagLeft = new SpawnEnemyPosition(_1zigZag, _1),
								zigZag3Right = new SpawnEnemyPosition(_1zigZag3, _9),
								zigZag3Left = new SpawnEnemyPosition(_1zigZag3, _1),
								zigZag4Right = new SpawnEnemyPosition(_1zigZag4, _9),
								zigZag4Left = new SpawnEnemyPosition(_1zigZag4, _1),
								zigZag2_8 = new SpawnEnemyPosition(_2zigZag, _2_8),
								zigZag32_8 = new SpawnEnemyPosition(_2zigZag3, _2_8),
								zigZag42_8 = new SpawnEnemyPosition(_2zigZag4, _2_8),
								zigZag1sur10 = new SpawnEnemyPosition(_1zigZag, _1),
								zigZag41sur10 = new SpawnEnemyPosition(_1zigZag4, _1),
								zigZag2sur10 = new SpawnEnemyPosition(_1zigZag, _2),
								zigZag42sur10 = new SpawnEnemyPosition(_1zigZag4, _2),
								zigZag3sur10 = new SpawnEnemyPosition(_1zigZag, _3),
								zigZag43sur10 = new SpawnEnemyPosition(_1zigZag4, _3),
								zigZag4sur10 = new SpawnEnemyPosition(_1zigZag, _4),
								zigZag5sur10 = new SpawnEnemyPosition(_1zigZag, _5),
								zigZag6sur10 = new SpawnEnemyPosition(_1zigZag, _6),
								zigZag7sur10 = new SpawnEnemyPosition(_1zigZag, _7),
								zigZag47sur10 = new SpawnEnemyPosition(_1zigZag4, _7),
								zigZag8sur10 = new SpawnEnemyPosition(_1zigZag, _8),
								zigZag48sur10 = new SpawnEnemyPosition(_1zigZag4, _8),
								zigZag9sur10 = new SpawnEnemyPosition(_1zigZag, _9),
								zigZag49sur10 = new SpawnEnemyPosition(_1zigZag4, _9);
	private static final SpawnEnemyPosition 	quiTirRnd = new SpawnEnemyPosition(_1quiTir, _1random),
								quiTir3Rnd = new SpawnEnemyPosition(_1quiTir3, _1random),
								quiTir4Rnd = new SpawnEnemyPosition(_1quiTir4, _1random),
								quiTir2_8 = new SpawnEnemyPosition(_2quiTir, _2_8),
								quiTir1_9 = new SpawnEnemyPosition(_2quiTir, _1_9),
								quiTir31_9 = new SpawnEnemyPosition(_2quiTir3, _1_9),
								quiTir41_9 = new SpawnEnemyPosition(_2quiTir4, _1_9),
								quiTir0_10 = new SpawnEnemyPosition(_2quiTir, _0_10),
								quiTir_1 = new SpawnEnemyPosition(_1quiTir, _1),
								quiTir3_1 = new SpawnEnemyPosition(_1quiTir3, _1),
								quiTir4_1 = new SpawnEnemyPosition(_1quiTir4, _1),
								quiTir_2 = new SpawnEnemyPosition(_1quiTir, _2),
								quiTir3_2 = new SpawnEnemyPosition(_1quiTir3, _2),
								quiTir4_2 = new SpawnEnemyPosition(_1quiTir4, _2),
								quiTir_3 = new SpawnEnemyPosition(_1quiTir, _3),
								quiTir_4 = new SpawnEnemyPosition(_1quiTir, _4),
								quiTir3_4 = new SpawnEnemyPosition(_1quiTir3, _4),
								quiTir4_4 = new SpawnEnemyPosition(_1quiTir4, _4),
								quiTir_5 = new SpawnEnemyPosition(_1quiTir, _5),
								quiTir_6 = new SpawnEnemyPosition(_1quiTir, _6),
								quiTir3_6 = new SpawnEnemyPosition(_1quiTir3, _6),
								quiTir4_6 = new SpawnEnemyPosition(_1quiTir4, _6),
								quiTir_7 = new SpawnEnemyPosition(_1quiTir, _7),
								quiTir_8 = new SpawnEnemyPosition(_1quiTir, _8),
								quiTir3_8 = new SpawnEnemyPosition(_1quiTir3, _8),
								quiTir4_8 = new SpawnEnemyPosition(_1quiTir4, _8),
								quiTir_9 = new SpawnEnemyPosition(_1quiTir, _9),
								quiTir3_9 = new SpawnEnemyPosition(_1quiTir3, _9),
								quiTir4_9 = new SpawnEnemyPosition(_1quiTir4, _9),
								quiTir_3_7 = new SpawnEnemyPosition(_2quiTir, _3_7),
								quiTir4_3_7 = new SpawnEnemyPosition(_2quiTir4, _3_7),
								quiTir3_3_7 = new SpawnEnemyPosition(_2quiTir3, _3_7);
	private static final SpawnEnemyPosition 	cylonRnd = new SpawnEnemyPosition(_1cylon, _1random),
								cylon3Rnd = new SpawnEnemyPosition(_1cylon3, _1random),
								cylon4Rnd = new SpawnEnemyPosition(_1cylon4, _1random);
	private static final SpawnEnemyPosition 	avionRnd = new SpawnEnemyPosition(_1avion, _1random),
								avion3Rnd = new SpawnEnemyPosition(_1avion3, _1random),
								avion4Rnd = new SpawnEnemyPosition(_1avion4, _1random),
								avion2_8 = new SpawnEnemyPosition(_2avions, _2_8),
								avion32_8 = new SpawnEnemyPosition(_2avions3, _2_8),
								avion42_8 = new SpawnEnemyPosition(_2avions4, _2_8),
								avionMid = new SpawnEnemyPosition(_1avion, _1middle),
								avion3Mid = new SpawnEnemyPosition(_1avion3, _1middle),
								avion4Mid = new SpawnEnemyPosition(_1avion4, _1middle);
	private static final SpawnEnemyPosition  quiTir23Mid = new SpawnEnemyPosition(_1quiTir23, _1middle),
								quiTir24Mid = new SpawnEnemyPosition(_1quiTir24, _1middle),
								quiTir2Mid = new SpawnEnemyPosition(_1quiTir2, _1middle),
								quiTir2_2_8 = new SpawnEnemyPosition(_2quiTir2, _2_8),
								quiTir23_2_8 = new SpawnEnemyPosition(_2quiTir23, _2_8);
	private static final SpawnEnemyPosition 	quiTourneMid = new SpawnEnemyPosition(_1DIABOLO, _1middle),
								quiTourne3Mid = new SpawnEnemyPosition(_1quiTourne3, _1middle),
								quiTourne4Mid = new SpawnEnemyPosition(_1quiTourne4, _1middle);
	private static final SpawnEnemyPosition 	laserMid = new SpawnEnemyPosition(_1laser, _1middle),
								laser3Mid = new SpawnEnemyPosition(_1laser3, _1middle),
								laser4Mid = new SpawnEnemyPosition(_1laser4, _1middle);
	private static final SpawnEnemyPosition porteRaisin_2_8 = new SpawnEnemyPosition(_2porteRaisin, _2_8),
								porteRaisin3_2_8 = new SpawnEnemyPosition(_2porteRaisin3, _2_8),
								porteRaisin4_2_8 = new SpawnEnemyPosition(_2porteRaisin4, _2_8);
	private static final SpawnEnemyPosition 	kinderRnd = new SpawnEnemyPosition(_1kinder, _1random),
								kinder3Rnd = new SpawnEnemyPosition(_1kinder3, _1random),
								kinder4Rnd = new SpawnEnemyPosition(_1kinder4, _1random);
	private static final SpawnEnemyPosition 	insecteRnd = new SpawnEnemyPosition(_1insecte, _1random),
								insecte_3 = new SpawnEnemyPosition(_1insecte, _3),
								insecte_7 = new SpawnEnemyPosition(_1insecte, _7),
								insecte3Rnd = new SpawnEnemyPosition(_1insecte3, _1random),
								insecte3Right = new SpawnEnemyPosition(_1insecte3, _7),
								insecte4Right = new SpawnEnemyPosition(_1insecte3, _7),
								insecte4Rnd = new SpawnEnemyPosition(_1insecte4, _1random);
	private static final SpawnEnemyPosition bouleTirCoteRnd = new SpawnEnemyPosition(_1bouleTirCote, _1random);
	private static final SpawnEnemyPosition bouleRotationRnd = new SpawnEnemyPosition(_1bouleRotation, _1random);
	private static final SpawnEnemyPosition 	satDown = new SpawnEnemyPosition(_1Sat, _1SatDown),
								satUp = new SpawnEnemyPosition(_1Sat, _1SatUp);
	private static final SpawnEnemyPosition bossMine = new SpawnEnemyPosition(_1bossMine, _1random);
	private static final SpawnEnemyPosition bossQuad = new SpawnEnemyPosition(_1bossQuad, _1random);
	private static final SpawnEnemyPosition vicousRnd = new SpawnEnemyPosition(_1vicious, _1random);
	private static final SpawnEnemyPosition group = new SpawnEnemyPosition(_1group, _1random), group3 = new SpawnEnemyPosition(_1group3, _1random), group4 = new SpawnEnemyPosition(_1group4, _1random);
	
	public static final SpawnEnemyPosition[] lvl1_170 = {		//6								10							13
		deBaseCentre, pause, pause, pause, pause, deBase_4_6, pause, pause, pause, deBase_3_7, pause, pause, deBase_2_8, pause, deBase_1_9, // Ca fait un V
		pause, pause, pause, pause, pause, pause,
		// 22
		deBase_1_9, pause, deBase_1_9, pause, deBase_1_9,
		pause, pause, pause, pause, pause, pause,
		// 34								38							41				43				44
		zigZagRight, pause, pause, pause, zigZagRight, pause, pause, zigZagRight, pause, zigZagRight, zigZagRight,
		// 45						47					49							51					53
		deBase_1_2_8_9, pause, deBase_2_3_7_8, pause, deBase_3_4_6_7, pause, deBase_2_3_7_8, pause, deBase_1_2_8_9,
		pause, pause, pause, pause,
		// 58
		bouleMid,
		pause, pause, pause,
		// 62					64					66					68						70					72						74				76						78
		zigZag1sur10, pause, zigZag2sur10, pause, zigZag3sur10, pause, zigZag4sur10, pause, zigZag5sur10, pause, zigZag6sur10, pause, zigZag7sur10, pause, zigZag8sur10, pause, zigZag9sur10 
		};
	public static final SpawnEnemyPosition[] lvl3_170 = {
//		deBase3_1_9, deBase3_2_8, deBase3_4_6, deBase3_3_7, deBase3Centre,
//		pause, pause, pause, pause, pause, pause, pause, pause,
//		zigZag3Rnd, pause, pause, zigZag3Rnd, pause, pause, zigZag3Rnd, pause, pause, zigZag3Rnd, pause, pause, zigZag3Rnd,
//		pause, pause, pause, pause, pause, pause,
//		deBase3_1_2_8_9, pause, pause, deBase3_1_2_8_9,
//		pause, pause, pause, pause, pause, pause,
//		boule3Mid,
//		pause, pause, pause, pause, pause,
//		zigZag31sur10, pause, zigZag39sur10, pause, zigZag32sur10, pause, zigZag38sur10, pause, zigZag33sur10, pause, zigZag37sur10,
//		pause, pause, pause, pause, pause,
	};
	public static final SpawnEnemyPosition[] deBase3_Triangle = {
		deBase3_1_9, deBase3_2_8, deBase3_4_6, deBase3_3_7, deBase3Centre
	};
	public static final SpawnEnemyPosition[] zigZag3_sweep = {
		zigZag3Rnd, pause, pause, zigZag3Rnd, pause, pause, zigZag3Rnd, pause, pause, zigZag3Rnd, pause, pause, zigZag3Rnd
	};
	public static final SpawnEnemyPosition[] deBase3_12_89_b_b_deBase3_12_89 = {
		deBase3_1_2_8_9, pause, pause, deBase3_1_2_8_9
	};
	public static final SpawnEnemyPosition[] break1 = {pause};
	
	public static final SpawnEnemyPosition[] lvl4_170 = {
		deBase4_1_9, deBase4_2_8, deBase4_4_6, deBase4_3_7, deBase4Centre,
		pause, pause, pause, pause, pause, pause,
		zigZag4Rnd, pause, pause, zigZag4Rnd, pause, pause, zigZag4Rnd, pause, pause, zigZag4Rnd, pause, pause, zigZag4Rnd,
		deBase4_1_2_8_9, pause, pause, deBase4_1_2_8_9,
		pause, pause, pause, pause,
		boule4Mid,
		pause, pause, pause,
		zigZag41sur10, pause, zigZag49sur10, pause, zigZag42sur10, pause, zigZag48sur10, pause, zigZag43sur10, pause, zigZag47sur10
	};
	public static final SpawnEnemyPosition[] lvl1_120 = {
		// 1
		boule1_9, pause, pause, pause, pause, pause,
		// 6		7
		zigZagLeft, zigZagLeft, pause, pause, 
		// 10
		_toupieRnd, pause, pause, pause, pause, pause, pause,
		// 17			18
		zigZagRight, zigZagRight, pause, pause,
		// 21
		quiTirRnd, pause, pause,
		// 24
		boule3_7, pause, pause, pause, pause, pause,
		// 30		31
		zigZagLeft, zigZagLeft, pause, pause, 
		// 34
		_toupie8
	};
	public static final SpawnEnemyPosition[] lvl3_120 = {
		// 1
		boule34_6, pause, pause, pause, pause, pause,
		// 6
		boule31_9, pause, pause, pause, pause, pause,
		// 7			8
		zigZag3Left, zigZag3Left, pause, pause, 
		// 11 			12
		zigZag3Right, zigZag3Right, pause, pause, 
		// 15
		_toupie3Rnd, pause, pause, pause, pause, pause, pause,
		// 22						25
		quiTir3Rnd, pause, pause, quiTir3Rnd, pause, pause,
		// 28			29
		zigZag3Left, zigZag3Left, pause, pause, 
		// 32
		_toupie3Rnd
	};
	public static final SpawnEnemyPosition[] lvl4_120 = {
		boule44_6, pause, pause, pause, pause, pause,
		boule41_9, pause, pause, pause, pause, pause,
		zigZag4Left, zigZag4Left, pause, pause, 
		zigZag4Right, zigZag4Right, pause, pause, 
		_toupie4Rnd, pause, pause, pause, pause, pause, pause,
		quiTir4Rnd, pause, pause, quiTir4Rnd, pause, pause,
		zigZag4Left, zigZag4Left, pause, pause, 
		_toupie4Rnd
	};
	public static final SpawnEnemyPosition[] lvl1_94 = {
		quiTir_1, pause,
		quiTir_9, pause, pause, 
		deBase_4_6, pause,
		quiTir_2, pause, quiTir_4, pause, quiTir_6, pause, quiTir_8, pause, pause, pause, pause,
		quiTir_3, pause, quiTir_5, pause, quiTir_7, pause, quiTir_9
	};
	public static final SpawnEnemyPosition[] lvl3_94 = {
		quiTir3_1, pause,
		quiTir3_9, pause, pause, pause, pause, 
		quiTir3_2, pause, quiTir3_4, pause, quiTir3_6, pause, quiTir3_8
	};
	public static final SpawnEnemyPosition[] lvl4_94 = {
		quiTir4_1, pause,
		quiTir4_9, pause, pause, pause, pause, 
		quiTir4_2, pause, quiTir4_4, pause, quiTir4_6, pause, quiTir4_8
	};
	public static final SpawnEnemyPosition[] bouleTirCote2 = {
		bouleTirCoteRnd, pause, pause, pause, bouleTirCoteRnd
	};
	public static final SpawnEnemyPosition[] lvl1_60 = {
		quiTir2_8, pause, pause,
		cylonRnd, pause, pause,
		deBase_4_6
	};
	public static final SpawnEnemyPosition[] lvl3_60 = {
		quiTir3Rnd, pause, pause,
		cylon3Rnd, pause, pause,
		deBase3_4_6
	};
	public static final SpawnEnemyPosition[] lvl4_60 = {
		quiTir4Rnd, pause, pause,
		cylon4Rnd, pause, pause,
		deBase4_4_6
	};
	public static final SpawnEnemyPosition[] lvl1_83 = {
		boule2_5_8, pause, pause,
		quiTir1_9, pause, pause,
		quiTir0_10
	};
	public static final SpawnEnemyPosition[] lvl3_83 = {
		boule32_5_8, pause, pause,
		quiTir31_9
	};
	public static final SpawnEnemyPosition[] lvl4_83 = {
		boule42_5_8, pause, pause,
		quiTir41_9
	};
	public static final SpawnEnemyPosition[] lvl1_100 = {
		avionRnd, pause, pause, pause, pause, pause,
		deBaseCentre, deBase_4_6, deBase_3_7, deBase_2_8, deBase_1_9,
		pause, pause, pause,
		bouleRnd
	};
	public static final SpawnEnemyPosition[] lvl3_100 = {
		avion3Rnd, pause, pause, pause, pause, pause,
		deBase3Centre, pause, deBase3_3_7, pause, deBase3_4_6, pause, deBase3_1_9, pause, deBase3_2_8,
		pause, pause, pause,
		boule3Rnd
	};
	public static final SpawnEnemyPosition[] lvl4_100 = {
		avion4Rnd, pause, pause, pause, pause, pause,pause, 
		deBase4Centre, pause, pause, pause, pause, deBase4_3_7, pause, pause, pause, pause, deBase4_4_6, pause, pause, pause, pause, deBase4_1_9, pause, pause, pause, pause, deBase4_2_8,
		pause, pause, pause,pause, pause, pause, 
		boule4Rnd
	};
	public static final SpawnEnemyPosition[] lvl1_350 = {
		quiTir1_9, pause, pause, pause, pause,
		avion2_8,
		pause, pause, pause,
		zigZag2_8, pause, zigZag2_8, pause, zigZag2_8, 
		pause, pause, pause,
		avionMid
	};
	public static final SpawnEnemyPosition[] lvl3_350 = {
		avion32_8,
		pause, pause, pause,
		quiTir31_9, pause, pause, pause, pause,
		zigZag32_8, pause, pause, zigZag32_8, pause, pause, zigZag32_8, 
		pause, pause, pause,
		avion3Mid
	};
	public static final SpawnEnemyPosition[] lvl4_350 = {
		avion42_8,
		pause, pause, pause,
		quiTir41_9, pause, pause, pause, pause,
		zigZag42_8, pause, pause, zigZag42_8, pause, pause, zigZag42_8, 
		pause, pause, pause,
		avion4Mid
	};
	public static final SpawnEnemyPosition[] lvl1_178 = {
		quiTir2Mid,
		pause, pause,
		deBase_1_2_3_7_8_9,
		pause, pause, pause,
		bouleRnd,
		pause, pause, pause,
		bouleRnd,
		pause, pause, pause,
		quiTir2_2_8
	};
	public static final SpawnEnemyPosition[] lvl3_178 = {
		quiTir23Mid,
		pause, pause, pause,
		boule3Rnd,
		pause, pause, pause,
		boule3Rnd,
		pause, pause, pause,
		quiTir23_2_8
	};
	public static final SpawnEnemyPosition[] lvl4_178 = {
		boule4Rnd,
		pause, pause, pause,
		quiTir24Mid,
		pause, pause, pause,
		boule4Rnd
	};
	public static final SpawnEnemyPosition[] lvl1_85 = {
		quiTourneMid, pause, pause,
		deBase_1_9, pause, deBase_2_8, pause, deBase_1_9, deBase_2_8, pause, pause, pause,
		quiTir_3_7, pause, pause, pause,
		quiTourneMid
	};
	public static final SpawnEnemyPosition[] lvl3_85 = {
		quiTourne3Mid, pause, pause,
		deBase3_1_9, deBase3_2_8, pause, pause, pause, pause, pause,
		quiTir3_3_7, pause, pause, pause, pause,
		quiTourne3Mid
	};
	public static final SpawnEnemyPosition[] lvl4_85 = {
		quiTourne4Mid, pause, pause,
		deBase4_1_9, deBase4_2_8, pause, pause, pause, pause, pause,
		quiTir4_3_7, pause, pause, pause, pause,
		quiTourne4Mid
	};
	public static final SpawnEnemyPosition[] lvl1_353 = {
		laserMid, pause, pause, laserMid, pause, pause, laserMid,
		pause, pause, pause,
		deBase_4_5_7
	};
	public static final SpawnEnemyPosition[] lvl3_353 = {
		deBase3_4_5_7,
		pause, pause, pause,
		laser3Mid, pause, laser3Mid, pause, laser3Mid
	};
	public static final SpawnEnemyPosition[] lvl4_353 = {
		deBase4_4_5_7,
		pause, pause, pause,
		laser4Mid, pause, laser4Mid, pause, laser4Mid
	};
	public static final SpawnEnemyPosition[] lvl1_500 = {
		quiTir_3_7, 
		pause,
		quiTir2Mid,
		pause, pause,
		porteRaisin_2_8
	};
	public static final SpawnEnemyPosition[] lvl3_500 = {
		quiTir3_3_7, 
		pause,pause,pause,
		porteRaisin3_2_8,
		pause, pause,pause,pause,pause,
		quiTir23Mid
	};
	public static final SpawnEnemyPosition[] lvl4_500 = {
		quiTir4_3_7, 
		pause,pause,pause,
		porteRaisin4_2_8,
		pause, pause,pause,pause,pause,
		quiTir24Mid
	};
	public static final SpawnEnemyPosition[] lvl1_insecte = {insecteRnd};
	public static final SpawnEnemyPosition[] lvl3_insecte = {insecte3Rnd};
	public static final SpawnEnemyPosition[] lvl4_insecte = {insecte4Rnd};
	public static final SpawnEnemyPosition[] lvl1_insecte2 = {insecte_3, pause, pause, pause, pause, insecte_7};
	public static final SpawnEnemyPosition[] lvl3_insecte2 = {insecte3Rnd, pause, pause, pause, pause, insecte3Right};
	public static final SpawnEnemyPosition[] lvl4_insecte2 = {insecte4Rnd, pause, pause, pause, pause, insecte4Right};
	public static final SpawnEnemyPosition[] KINDER_LVL4 = {	kinderRnd, pause, kinderRnd	};
	public static final SpawnEnemyPosition[] KINDER_LVL3 = {	kinder3Rnd, pause, kinder3Rnd	};
	public static final SpawnEnemyPosition[] lvl4_kinder = {	kinder4Rnd, pause, kinder4Rnd	};
	public static final SpawnEnemyPosition[] CYLON_OR_BALL_LVL1 = { cylonRnd, bouleRnd	};
	public static final SpawnEnemyPosition[] CYLON_OR_BALL_LVL3 = { cylon3Rnd, boule3Rnd	};
	public static final SpawnEnemyPosition[] CYLON_OR_BALL_LVL4 = { cylon4Rnd, boule4Rnd	};
	public static final SpawnEnemyPosition[] BASICS_LVL1 = {	deBase_1_3_5_7_9 };
	public static final SpawnEnemyPosition[] BASICS_LVL3 = {	deBase3_1_3_5_7_9 };
	public static final SpawnEnemyPosition[] BASICS_LVL4 = {	deBase4_1_3_5_7_9 };
	public static final SpawnEnemyPosition[] lvl1_bossQuad = {bossQuad};
	public static final SpawnEnemyPosition[] lvl1_bossMine = {bossMine};
	public static final SpawnEnemyPosition[] satUpnDown = {satDown, pause, pause, pause, pause, satUp};
	public static final SpawnEnemyPosition[] BASICS_AND_DIABOLO_LVL1 = {
		deBase_2_8, pause, deBase_1_9, pause, pause, pause,
		quiTourneMid
	};
	public static final SpawnEnemyPosition[] BASICS_AND_DIABOLO_LVL3 = {
		deBase3_2_8, pause, deBase3_1_9, pause, pause, pause,
		quiTourne3Mid
	};
	public static final SpawnEnemyPosition[] lvl4_remplissageQuiTourne = {
		deBase4_2_8, pause, deBase4_1_9, pause, pause, pause,
		quiTourne4Mid
	};
	public static final SpawnEnemyPosition[] ROTATION_BALL = {bouleRotationRnd};
	public static final SpawnEnemyPosition[] VICIOUS = {vicousRnd, pause, pause, pause, vicousRnd};
	public static final SpawnEnemyPosition[] GROUP_LVL1 = {group};
	public static final SpawnEnemyPosition[] GROUP_LVL3 = {group3};
	public static final SpawnEnemyPosition[] GROUP_LVL4 = {group4};
	public final Merlin[] merlin;
	public final Vector2[] positions;
	
	public SpawnEnemyPosition(Merlin[] enemies, Vector2[] positions) {
		super();
		this.merlin = enemies;
		this.positions = positions;
		if (positions.length != enemies.length)
			throw new RuntimeException("Spawns lenght != enemies length");
	}

//	public Spawn(Array<Invocable> enemies, Array<Vector2> positions) {
//		System.out.println("enemies : " + enemies.size);
//		if (enemies.size > 0)
//			this.enemies = enemies.toArray();
//		if (positions.size > 0)
//			this.spawns = positions.toArray();
//		if (positions.size != enemies.size)
//			throw new RuntimeException("Spawns lenght != enemies length");
//	}

}
