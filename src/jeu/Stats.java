package jeu;


public class Stats {
	
	// 0.507cm 20 sur s3
	public static final float U = (CSG.SCREEN_HEIGHT + CSG.screenWidth) / 115f;
	public static final float UU = U * 2;
	public static final float UUU = U * 3;
	public static final float U4 = U * 4;
	public static final float U5 = U * 5;
	public static final float U6 = U * 6;
	public static final float U10 = U * 10;
	public static final float U12 = U * 12;
	public static final float U20 = U * 20;
	public static final float U50 = U * 50;
	public static final float U90 = U * 90;
	public static final float U270 = U * 270;
	public static final float u = U / 2;
	public static final float microU = U / 150;
	public static final float microUSur6 = microU / 6;
	public static final float uSur2 = u / 2;
	public static final float uDiv4 = u / 4;
	public static final float uSur8 = u / 8;
	public static final float IMPACT = U / 240;
	public static final float VNV3 = 1.22f;
	public static final float VNV4 = 1.41f;
	public static final float HPNV3 = 2;
	public static final float HPNV4 = 2.3f;
	public static final float SCORE = 1;
	public static final float SCREEN_HEIGHT_100 = CSG.SCREEN_HEIGHT / 100;
	public static final float WIDTH_DIV_10 = CSG.screenWidth / 10;
	public static final float GAME_ZONE_W_PLUS_WIDTH_DIV_10 = CSG.gameZoneWidth + WIDTH_DIV_10;
	public static final float SCREEN_HEIGHT_PLUS_MARGIN = CSG.SCREEN_HEIGHT * 1.1f;
	
	
	public static final float V_JOUEUR = (int) CSG.SCREEN_HEIGHT + CSG.screenWidth + ((CSG.SCREEN_HEIGHT + CSG.screenWidth)/10);
	public static final int WIDTH_JOUEUR = (int) (3.8f * U);
	
	// ***********************************************************************************
	// ******************************   B O N U S   **************************************
	// ***********************************************************************************
	
	public static final float V_BONUS = 4 * U;
	public static final float V_BONUS_XP = V_BONUS/1.5f;
	public static final float BOMB_SPEED = V_BONUS/3;
	public static final float V_BONUS_STOP = V_BONUS/2.1f;
	public static final float V_BONUS_TEMPS = V_BONUS/1.4f;
	public static final float V_BONUS_BOUCLIER = (V_BONUS * 1.1f);
	public static final float BONUS_WIDTH = U * 3f, HALF_WIDTH = BONUS_WIDTH/2;
	public static final float DRONE_BONUS_SPEED = V_BONUS/1.8f, BONUS_ADD_ACCELERATION = 15;
	
	// ***********************************************************************************
	// **********************   E  N  N  E  M  I  S   ************************************
	// ***********************************************************************************
	
	// E N N E M I   D E   B A S E
	public static final float V_ENN_DE_BASE = 9 * U;
	public static final float V_ENN_DE_BASE_3 = V_ENN_DE_BASE * VNV3;
	public static final float V_ENN_DE_BASE_4 = (V_ENN_DE_BASE * VNV4 * 2);
	public static final int HP_DE_BASE = 5;
	public static final int HP_DE_BASE_NV3 = (int) (HP_DE_BASE * HPNV3);
	public static final int HP_DE_BASE_NV4 = (int) (HP_DE_BASE * HPNV4);
	public static final int WIDTH_DE_BASE = (int) (2.7f * U);
	public static final int HEIGHT_DE_BASE = getHauteur(WIDTH_DE_BASE, 1.4f);
	
	// G R O U P
	public static final float V_GROUP = 5 * U;
	public static final int GROUP_WIDTH = (int) (4.2f * U);
	public static final int HP_GROUP = 60;
	public static final int HP_GROUP3 = (int) (HP_GROUP * HPNV3);
	public static final int HP_GROUP4 = (int) (HP_GROUP * HPNV4);
	// D E   B A S E   Q U I   T I R
	public static final float V_ENN_QUI_TIR = 5 * U;
	public static final float V_ENN_MAX_QUI_TIR3 = V_ENN_QUI_TIR * VNV3;
	public static final float DERIVE_QUI_TIR = U;
	public static final int HP_QUI_TIR = 27;
	public static final int HP_QUI_TIR3 = (int) (HP_QUI_TIR * HPNV3);
	public static final int HP_QUI_TIR4 = (int) (HP_QUI_TIR * HPNV4);
	public static final int DEMI_HP_QUI_TIR = HP_QUI_TIR / 2;
	public static final int DEMI_HP_QUI_TIR3 = HP_DE_BASE_NV3 / 2;
	public static final int DEMI_HP_QUI_TIR4 = HP_DE_BASE_NV4 / 2;
	public static final int WIDTH_QUI_TIR = (int) ((float)HEIGHT_DE_BASE * 1.05f);
	public static final int HEIGHT_QUI_TIR = getHauteur(WIDTH_QUI_TIR, 1.5f);
	public static final int WIDTH_QUI_TIR3 = (int) ((float)WIDTH_QUI_TIR * 1.15f);
	public static final int HEIGHT_QUI_TIR3 = (int) ((float)HEIGHT_QUI_TIR * 1.15f);
	
	// Z I G   Z A G
	public static final int HP_ZIGZAG = 13;
	public static final int HP_ZIGZAG_NV3 = (int) (HP_ZIGZAG * HPNV3);
	public static final int HP_ZIGZAG_NV4 = (int) (HP_ZIGZAG * HPNV4);
	public static final int WIDTH_ZIG_ZAG = (int) ((float)WIDTH_DE_BASE * 1.35f); 
	public static final int HEIGHT_ZIG_ZAG = getHauteur(WIDTH_ZIG_ZAG, 1.2f); 
	public static final float V_ENN_ZIGZAG = 12 * U;
	public static final float V_ENN_ZIGZAG_NV3 = V_ENN_ZIGZAG * VNV3;
	
	// B O U L E 
	public static final float V_ENN_BOULE = u * 25f;
	public static final int HP_BALL = 37;
	public static final int BALL_WIDTH = (int) ((float)WIDTH_DE_BASE  * 1.3f);
	
	// P O R T E   N E F
	public static final float V_ENN_PORTE_NEF = 4 * U;
	public static final int HP_BOSS_SAT = 430;
	public static final int DEMI_HP_PORTE_NEF = HP_BOSS_SAT/2;
	public static final int SAT_WIDTH = (int) ((float)WIDTH_DE_BASE * 5);
	
	// Q U I   T O U R N E
	public static final float V_ENN_QUI_TOURNE = 13 * U;
	public static final float DIABOLO_HALF_SPEED = V_ENN_QUI_TOURNE/2;
	public static final float V_ENN_QUI_TOURNE3 = V_ENN_QUI_TOURNE * VNV3;
	public static final float DEMI_V_ENN_QUI_TOURNE3 = V_ENN_QUI_TOURNE3 / 2;
	public static final int HP_DIABOLO = 67;
	public static final int HP_QUI_TOURNE3 = (int) (HP_DIABOLO * HPNV3);
	public static final int HP_QUI_TOURNE4 = (int) (HP_DIABOLO * HPNV4);
	public static final int WIDTH_DIABOLO = (int) ((float)WIDTH_DE_BASE * 1.35f);
	
	// T O U P I E
	public static final float V_ENN_TOUPIE = 10 * U;
	public static final float DEMI_V_ENN_TOUPIE = V_ENN_TOUPIE / 2;
	public static final int ROUND_AND_ROUND_HP = 80;
	public static final int HP_TOUPIE3 = (int) (ROUND_AND_ROUND_HP * HPNV3);
	public static final int HP_TOUPIE4 = (int) (ROUND_AND_ROUND_HP * HPNV4);
	public static final int ROUND_AND_ROUND_WIDTH = (int) ((float)WIDTH_DE_BASE * 1.5f);
	public static final int ROUND_AND_ROUND_HEIGHT = getHauteur(ROUND_AND_ROUND_WIDTH, 1.55f);
	
	// C Y L O N
	public static final int HP_CYLON = 90;
	public static final int HP_CYLON3 = (int) (HP_CYLON * HPNV3);
	public static final int HP_CYLON4 = (int) (HP_CYLON * HPNV4);
	public static final int DEMI_HP_CYLON = HP_CYLON / 2;
	public static final int DEMI_HP_CYLON3 = HP_CYLON3 / 2;
	public static final int DEMI_HP_CYLON4 = HP_CYLON4 / 2;
	public static final int CYLON_WIDTH = (int) ((float)WIDTH_DE_BASE * 1.8f);
	public static final float CYLON_SPEED = 4 * U;
	public static final float V_ENN_CYLON3 = CYLON_SPEED;// * VNV3;
	
	// K I N D E R
	public static final int KINDER_HP = 110;
	public static final int HP_KINDER2 = 120;
	public static final float V_ENN_KINDER = 6 * U;
	public static final int HP_KINDER3 = (int) (KINDER_HP * HPNV3);
	public static final int HP_KINDER4 = (int) (KINDER_HP * HPNV4);
	public static final int HP_KINDER2_3 = (int) (HP_KINDER2 * HPNV3);
	public static final int KINDER_WIDTH = (int) (WIDTH_DE_BASE * 1.8f);
	public static final int KINDER_HEIGHT = getHauteur(KINDER_WIDTH, 1.5f);
	
	// B O S S   Q U A D
	public static final int QUAD_HP = 510;
	public static final int QUAD_WIDTH = (int) ((float)WIDTH_DE_BASE * 7);
	public static final int QUAD_HEIGHT = getHauteur(QUAD_WIDTH, 0.5f);
	public static final float QUAD_SPEED = 15 * U;
	
	// P O R T E   R A I S I N
	public static final float V_ENN_PORTE_RAISIN = 2 * U;
	public static final int CRUASER_HP = 320;
	public static final int HP_PORTE_RAISIN_AMOCHE = CRUASER_HP / 2;
	public static final int HP_PORTE_RAISIN3 = (int) (CRUASER_HP * HPNV3);
	public static final int HP_PORTE_RAISIN4 = (int) (CRUASER_HP * HPNV4);
	public static final int CRUSADER_WIDTH = (int) ((float)WIDTH_DE_BASE * 2.5f);
	public static final int CRUSADER_HEIGHT = getHauteur(CRUSADER_WIDTH, 1.25f);
	
	// A V I O N
	public static final float V_ENN_AVION = 9.5f * U;
	public static final float V_ENN_AVION3 = V_ENN_AVION * (VNV3-0.15f);
	public static final float V_ENN_AVION_BAD_SHAPE = V_ENN_AVION * 0.7f;
	public static final float V_ENN_AVION_BAD_SHAPE3 = V_ENN_AVION3 * 0.7f;
	public static final int PLANE_HP = 150;
	public static final int HP_AVION3 = (int) (PLANE_HP * HPNV3);
	public static final int HP_AVION4 = (int) (PLANE_HP * HPNV4);
	public static final int HPMAX_AVION_AMOCHE = PLANE_HP / 2;
	public static final int HPMAX_AVION_AMOCHE3 = HP_AVION3 / 2;
	public static final int HPMAX_AVION_AMOCHE4 = HP_AVION4 / 2;
	public static final int PLANE_WIDTH = (int) ((float)WIDTH_DE_BASE * 3.0f);
	public static final int PLANE_HEIGHT = getHauteur(PLANE_WIDTH, 1.5f);
	
	// D E   B A S E   Q U I   T I R  2
	public static final float V_ENN_DE_BASE_QUI_TIR2 = 6 * U;
	public static final float DERIVE_DE_BASE_QUI_TIR2 = U;
	public static final int HP_SHOOTER_FRAG = 199;
	public static final int HP_DE_BASE_QUI_TIR23 = (int) (HP_SHOOTER_FRAG * HPNV3);
	public static final int HP_DE_BASE_QUI_TIR24 = (int) (HP_SHOOTER_FRAG * HPNV4);
	public static final int DEMI_HP_BASE_QUI_TIR2 = HP_SHOOTER_FRAG / 2;
	public static final int DEMI_HP_BASE_QUI_TIR23 = HP_DE_BASE_QUI_TIR23 / 2;
	public static final int DEMI_HP_BASE_QUI_TIR24 = HP_DE_BASE_QUI_TIR24 / 2;
	public static final int WIDTH_QUI_TIR2 = (int) ((float)WIDTH_QUI_TIR * 1.2f);
	public static final int HEIGHT_QUI_TIR2 = (int) ((float)HEIGHT_QUI_TIR * 1.2f);
	
	// B O S S   M  I  N  E
	public static final int HP_BOSS_MINE = 780;
	public static final int WIDTH_BOOS_MINE = (int) (U * 4);
	public static final int HEIGHT_BOOS_MINE = getHauteur(WIDTH_BOOS_MINE, 4);
	public static final float V_ENN_BOSS_MINE = 5 * U;
	
	// L A S E R   
	public static final float V_ENN_LASER = 8 * U;
	public static final int LASER_HP = 190;
	public static final float V_ENN_LASER3 = V_ENN_LASER * VNV3;
	public static final int HP_LASER3 = (int) (LASER_HP * HPNV3);
	public static final int HP_LASER4 = (int) (LASER_HP * HPNV4);
	public static final int LASER_WIDTH = Stats.WIDTH_DE_BASE * 3;
	
	// L A S E R   C O T E 
	public static final float V_BOULE_TIR_COTE = (V_ENN_BOULE + u) / 2;
	public static final float V_BOULE_TIR_COTE_PETIT = V_ENN_BOULE - U;
	public static final int HP_LASER_COTE = 240;
	public static final int HP_BOULE_COTE_PETIT = 145;
	public static final int WIDTH_BOULE_TIR_COTE = (int) (Stats.U * 4.5f);
	
	// I N S E C T E 
	public static final int INSECT_WIDTH = (int) (WIDTH_DE_BASE * 4f);
	public static final int INSECT_HP = 380;
	public static final int HP_INSECTE_DEMI = INSECT_HP / 2;
	public static final int HP_INSECTE3 = (int) (INSECT_HP * HPNV3);
	public static final int HP_INSECTE4 = (int) (INSECT_HP * HPNV4);
	public static final float INSECT_SPEED = 1 * U;
	public static final float V_ENNEMI_INSECTE3 = INSECT_SPEED * VNV3;
	
	// O M B R E L L E
	public static final float V_ENN_OMBRELLE = 26 * U;
	public static final int HP_OMBRELLE = 250;
	
	// A D D   B O S S   S A T
	public static final float WIDTH_ADD_BOSS_SAT = U * 2.5f;
	public static final float V_ADD_BOSS_SAT = 5 * U;
	public static final int HP_ADD_BOSS_SAT = 25;
	
	// ***********************************************************************************
	// ***********************   A   R   M   E   S   *************************************
	// ***********************************************************************************
	public static final float V_ARME_VICOUS = 20 * U;
	
	// F R A G
	public static final float V_ARME_FRAG = 13 * U;
	public static final float SPEED_FRAGMENTED_WEAPON_AND_MINE = V_ARME_FRAG / 2;
	// A R M E   B O U L E   B L E U E
	public static final float V_ARME_BOULE_BLEU = 10 * U;
	public static final float V_ARME_BOULE_BLEU_LENTE = V_ARME_BOULE_BLEU * 0.8f;
	public static final float V_BOULE_BLEU_RAPIDE = V_ARME_BOULE_BLEU * 1.2f;
	public static final float V_ARME_BOULE_VERTE_RAPIDE = 12 * U;
	public static final float V_ARME_BOULE_VERTE_ROTATION = V_ARME_BOULE_VERTE_RAPIDE / 1.5f;
	// A R M E   K I N D E R
	public static final float V_ARME_KINDER = 13 * U;
	// A R M E   B O S S   Q U A D 
	public static final float V_ARME_BOSS_QUAD = 18 * U;
	// A R M E   S H U R I K E N
	public static final float V_ARME_RAISIN = 19 * U;
	// A R M E   A V I O N
	public static final float V_ARME_AVION = 24 * U;
	// A R M E   E N N E M I   L A S E R
	public static final float V_ARME_LASER = 9 * U;
	// A R M E   I N S E C T E
	public static final float V_INSECTE = 3 * U;
	// =======================  J  O  U  E  U  R  ==============================
	// A R M E   H  A N T E E
	public static final float V_ARME_HANTEE = 120 * U;
	// A R M E   B A L A Y A G E
	public static final float V_ARME_BALAYAGE = 45 * U;
	// A R M E   D E   B A S E
	public static final float V_ARME_DE_BASE = 65 * U;
	// A R M E   T R O I S
	public static final float SPEED_PINK_WEAPON = 90 * U;
	// A R M E   A D D  
	public static final float V_ARME_ADD = 50 * U;
	public static final float SUN_SPEED = 75 * U;
	public static final float V_ARME_SPACE_INVADER = 22 * U;
	
	// ***********************************************************************************
	// ***************   P   A   R   T   I   C   U   L   E   S   *************************
	// ***********************************************************************************
	
	public static final float V_PARTICULE_EXPLOSION = 9 * U;
	public static final float V_PARTICLE_EXPLOSION_SLOW = 4 * U;
	public static final float SPEED_DEBRIS = 2 * U;
	
	public static final int WIDTH_WEAPON_SMALL = CSG.screenWidth / 20;
	public static final int WIDTH_WEAPON_MINUS_NORMAL = CSG.screenWidth / 17;
	public static final int WIDTH_WEAPON_NORMAL = CSG.screenWidth / 15;
	public static final int WIDTH_WEAPON_BIG = CSG.screenWidth / 13;
	public static final int WIDTH_FIREBALL = CSG.screenWidth / 20;
	public static final int THRUSTER = (int) (U * 14);
	
	
	private static int getHauteur(float largeur, float f) {
		return (int) (largeur * f);
	}
}
