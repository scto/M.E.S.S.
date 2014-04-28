package jeu;


public class Stats {
	
	// 0.507cm 20 sur s3
	public static final float U = (CSG.SCREEN_HEIGHT + CSG.screenWidth) / 115f;
	public static final float UU = U * 2;
	public static final float UUU = U * 3;
	public static final float U4 = U * 4;
	public static final float U5 = U * 5;
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
	public static final float uSur4 = u / 4;
	public static final float uSur8 = u / 8;
	public static final float IMPACT = U / 240;
	public static final float VNV3 = 1.22f;
	public static final float VNV4 = 1.41f;
	public static final float PVNV3 = 2;
	public static final float PVNV4 = 2.3f;
	public static final float SCORE = 1;
	
	public static final float V_JOUEUR = (int) CSG.SCREEN_HEIGHT + CSG.screenWidth + ((CSG.SCREEN_HEIGHT + CSG.screenWidth)/10);
	public static final int LARGEUR_JOUEUR = (int) (3.8f * U);
	
	// ***********************************************************************************
	// ******************************   B O N U S   **************************************
	// ***********************************************************************************
	
	public static final float V_BONUS = 4 * U;
	public static final float V_BONUS_XP = V_BONUS/1.5f;
	public static final float V_BONUS_BOMBE = V_BONUS/3;
	public static final float V_BONUS_STOP = V_BONUS/2.1f;
	public static final float V_BONUS_TEMPS = V_BONUS/1.4f;
	public static final float V_BONUS_BOUCLIER = (V_BONUS * 1.1f);
	public static final float BONUS_WIDTH = U * 3f, DEMI_LARGEUR = BONUS_WIDTH/2;
	public static final float V_BONUS_ADD = V_BONUS/1.8f, BONUS_ADD_ACCELERATION = 15;
	
	// ***********************************************************************************
	// **********************   E  N  N  E  M  I  S   ************************************
	// ***********************************************************************************
	
	// E N N E M I   D E   B A S E
	public static final float V_ENN_DE_BASE = 9 * U;
	public static final float V_ENN_DE_BASE_3 = V_ENN_DE_BASE * VNV3;
	public static final float V_ENN_DE_BASE_4 = (V_ENN_DE_BASE * VNV4 * 2);
	public static final int PV_DE_BASE = 5;
	public static final int PV_DE_BASE_NV3 = (int) (PV_DE_BASE * PVNV3);
	public static final int PV_DE_BASE_NV4 = (int) (PV_DE_BASE * PVNV4);
	public static final int LARGEUR_DE_BASE = (int) (2.7f * U);
	public static final int HAUTEUR_DE_BASE = getHauteur(LARGEUR_DE_BASE, 1.4f);
	
	// G R O U P
	public static final float V_GROUP = 5 * U;
	public static final int LARGEUR_GROUP = (int) (4.2f * U);
	public static final int PV_GROUP = 60;
	public static final int PV_GROUP3 = (int) (PV_GROUP * PVNV3);
	public static final int PV_GROUP4 = (int) (PV_GROUP * PVNV4);
	// D E   B A S E   Q U I   T I R
	public static final float V_ENN_QUI_TIR = 5 * U;
	public static final float V_ENN_MAX_QUI_TIR3 = V_ENN_QUI_TIR * VNV3;
	public static final float DERIVE_QUI_TIR = U;
	public static final int PV_QUI_TIR = 27;
	public static final int PV_QUI_TIR3 = (int) (PV_QUI_TIR * PVNV3);
	public static final int PV_QUI_TIR4 = (int) (PV_QUI_TIR * PVNV4);
	public static final int DEMI_PV_QUI_TIR = PV_QUI_TIR / 2;
	public static final int DEMI_PV_QUI_TIR3 = PV_DE_BASE_NV3 / 2;
	public static final int DEMI_PV_QUI_TIR4 = PV_DE_BASE_NV4 / 2;
	public static final int LARGEUR_QUI_TIR = (int) ((float)HAUTEUR_DE_BASE * 1.05f);
	public static final int HAUTEUR_QUI_TIR = getHauteur(LARGEUR_QUI_TIR, 1.5f);
	public static final int LARGEUR_QUI_TIR3 = (int) ((float)LARGEUR_QUI_TIR * 1.15f);
	public static final int HAUTEUR_QUI_TIR3 = (int) ((float)HAUTEUR_QUI_TIR * 1.15f);
	
	// Z I G   Z A G
	public static final int PV_ZIGZAG = 13;
	public static final int PV_ZIGZAG_NV3 = (int) (PV_ZIGZAG * PVNV3);
	public static final int PV_ZIGZAG_NV4 = (int) (PV_ZIGZAG * PVNV4);
	public static final int LARGEUR_ZIG_ZAG = (int) ((float)LARGEUR_DE_BASE * 1.35f); 
	public static final int HAUTEUR_ZIG_ZAG = getHauteur(LARGEUR_ZIG_ZAG, 1.2f); 
	public static final float V_ENN_ZIGZAG = 12 * U;
	public static final float V_ENN_ZIGZAG_NV3 = V_ENN_ZIGZAG * VNV3;
	
	// B O U L E 
	public static final float V_ENN_BOULE = u * 25f;
	public static final int PV_BOULE = 37;
	public static final int LARGEUR_BOULE = (int) ((float)LARGEUR_DE_BASE  * 1.3f);
	
	// P O R T E   N E F
	public static final float V_ENN_PORTE_NEF = 4 * U;
	public static final int PV_BOSS_SAT = 430;
	public static final int DEMI_PV_PORTE_NEF = PV_BOSS_SAT/2;
	public static final int LARGEUR_BOSS_SAT = (int) ((float)LARGEUR_DE_BASE * 5);
	
	// Q U I   T O U R N E
	public static final float V_ENN_QUI_TOURNE = 13 * U;
	public static final float DEMI_V_ENN_QUI_TOURNE = V_ENN_QUI_TOURNE/2;
	public static final float V_ENN_QUI_TOURNE3 = V_ENN_QUI_TOURNE * VNV3;
	public static final float DEMI_V_ENN_QUI_TOURNE3 = V_ENN_QUI_TOURNE3 / 2;
	public static final int PV_QUI_TOURNE = 67;
	public static final int PV_QUI_TOURNE3 = (int) (PV_QUI_TOURNE * PVNV3);
	public static final int PV_QUI_TOURNE4 = (int) (PV_QUI_TOURNE * PVNV4);
	public static final int LARGEUR_QUI_TOURNE = (int) ((float)LARGEUR_DE_BASE * 1.35f);
	
	// T O U P I E
	public static final float V_ENN_TOUPIE = 10 * U;
	public static final float DEMI_V_ENN_TOUPIE = V_ENN_TOUPIE / 2;
	public static final int PV_TOUPIE = 80;
	public static final int PV_TOUPIE3 = (int) (PV_TOUPIE * PVNV3);
	public static final int PV_TOUPIE4 = (int) (PV_TOUPIE * PVNV4);
	public static final int LARGEUR_TOUPIE = (int) ((float)LARGEUR_DE_BASE * 1.5f);
	public static final int HAUTEUR_TOUPIE = getHauteur(LARGEUR_TOUPIE, 1.55f);
	
	// C Y L O N
	public static final int PV_CYLON = 90;
	public static final int PV_CYLON3 = (int) (PV_CYLON * PVNV3);
	public static final int PV_CYLON4 = (int) (PV_CYLON * PVNV4);
	public static final int DEMI_PV_CYLON = PV_CYLON / 2;
	public static final int DEMI_PV_CYLON3 = PV_CYLON3 / 2;
	public static final int DEMI_PV_CYLON4 = PV_CYLON4 / 2;
	public static final int LARGEUR_CYLON = (int) ((float)LARGEUR_DE_BASE * 1.8f);
	public static final float V_ENN_CYLON = 4 * U;
	public static final float V_ENN_CYLON3 = V_ENN_CYLON;// * VNV3;
	
	// K I N D E R
	public static final int PV_KINDER = 110;
	public static final int PV_KINDER2 = 120;
	public static final float V_ENN_KINDER = 6 * U;
	public static final int PV_KINDER3 = (int) (PV_KINDER * PVNV3);
	public static final int PV_KINDER4 = (int) (PV_KINDER * PVNV4);
	public static final int PV_KINDER2_3 = (int) (PV_KINDER2 * PVNV3);
	public static final int LARGEUR_KINDER = (int) (LARGEUR_DE_BASE * 1.8f);
	public static final int HAUTEUR_KINDER = getHauteur(LARGEUR_KINDER, 1.5f);
	
	// B O S S   Q U A D
	public static final int PV_BOSS_QUAD = 510;
	public static final int LARGEUR_BOSS_QUAD = (int) ((float)LARGEUR_DE_BASE * 7);
	public static final int HAUTEUR_BOSS_QUAD = getHauteur(LARGEUR_BOSS_QUAD, 0.5f);
	public static final float V_ENN_BOSS_QUAD = 15 * U;
	
	// P O R T E   R A I S I N
	public static final float V_ENN_PORTE_RAISIN = 2 * U;
	public static final int PV_PORTE_RAISIN = 320;
	public static final int PV_PORTE_RAISIN_AMOCHE = PV_PORTE_RAISIN / 2;
	public static final int PV_PORTE_RAISIN3 = (int) (PV_PORTE_RAISIN * PVNV3);
	public static final int PV_PORTE_RAISIN4 = (int) (PV_PORTE_RAISIN * PVNV4);
	public static final int LARGEUR_PORTE_RAISIN = (int) ((float)LARGEUR_DE_BASE * 2.5f);
	public static final int HAUTEUR_PORTE_RAISIN = getHauteur(LARGEUR_PORTE_RAISIN, 1.25f);
	
	// A V I O N
	public static final float V_ENN_AVION = 9.5f * U;
	public static final float V_ENN_AVION3 = V_ENN_AVION * (VNV3-0.15f);
	public static final float V_ENN_AVION_BAD_SHAPE = V_ENN_AVION * 0.7f;
	public static final float V_ENN_AVION_BAD_SHAPE3 = V_ENN_AVION3 * 0.7f;
	public static final int PV_AVION = 150;
	public static final int PV_AVION3 = (int) (PV_AVION * PVNV3);
	public static final int PV_AVION4 = (int) (PV_AVION * PVNV4);
	public static final int PVMAX_AVION_AMOCHE = PV_AVION / 2;
	public static final int PVMAX_AVION_AMOCHE3 = PV_AVION3 / 2;
	public static final int PVMAX_AVION_AMOCHE4 = PV_AVION4 / 2;
	public static final int PLANE_WIDTH = (int) ((float)LARGEUR_DE_BASE * 3.0f);
	public static final int PLANE_HEIGHT = getHauteur(PLANE_WIDTH, 1.5f);
	
	// D E   B A S E   Q U I   T I R  2
	public static final float V_ENN_DE_BASE_QUI_TIR2 = 6 * U;
	public static final float DERIVE_DE_BASE_QUI_TIR2 = U;
	public static final int PV_DE_BASE_QUI_TIR_TRIANGLE = 199;
	public static final int PV_DE_BASE_QUI_TIR23 = (int) (PV_DE_BASE_QUI_TIR_TRIANGLE * PVNV3);
	public static final int PV_DE_BASE_QUI_TIR24 = (int) (PV_DE_BASE_QUI_TIR_TRIANGLE * PVNV4);
	public static final int DEMI_PV_BASE_QUI_TIR2 = PV_DE_BASE_QUI_TIR_TRIANGLE / 2;
	public static final int DEMI_PV_BASE_QUI_TIR23 = PV_DE_BASE_QUI_TIR23 / 2;
	public static final int DEMI_PV_BASE_QUI_TIR24 = PV_DE_BASE_QUI_TIR24 / 2;
	public static final int LARGEUR_QUI_TIR2 = (int) ((float)LARGEUR_QUI_TIR * 1.2f);
	public static final int HAUTEUR_QUI_TIR2 = (int) ((float)HAUTEUR_QUI_TIR * 1.2f);
	
	// B O S S   M  I  N  E
	public static final int PV_BOSS_MINE = 780;
	public static final int LARGEUR_BOOS_MINE = (int) (U * 4);
	public static final int HAUTEUR_BOOS_MINE = getHauteur(LARGEUR_BOOS_MINE, 4);
	public static final float V_ENN_BOSS_MINE = 5 * U;
	
	// L A S E R   
	public static final float V_ENN_LASER = 8 * U;
	public static final int PV_LASER = 190;
	public static final float V_ENN_LASER3 = V_ENN_LASER * VNV3;
	public static final int PV_LASER3 = (int) (PV_LASER * PVNV3);
	public static final int PV_LASER4 = (int) (PV_LASER * PVNV4);
	public static final int LARGEUR_LASER = Stats.LARGEUR_DE_BASE * 3;
	
	// L A S E R   C O T E 
	public static final float V_BOULE_TIR_COTE = (V_ENN_BOULE + u) / 2;
	public static final float V_BOULE_TIR_COTE_PETIT = V_ENN_BOULE - U;
	public static final int PV_LASER_COTE = 240;
	public static final int PV_BOULE_COTE_PETIT = 145;
	public static final int LARGEUR_BOULE_TIR_COTE = (int) (Stats.U * 4.5f);
	
	// I N S E C T E 
	public static final int LARGEUR_INSECTE = (int) (LARGEUR_DE_BASE * 4f);
	public static final int PV_INSECTE = 380;
	public static final int PV_INSECTE_DEMI = PV_INSECTE / 2;
	public static final int PV_INSECTE3 = (int) (PV_INSECTE * PVNV3);
	public static final int PV_INSECTE4 = (int) (PV_INSECTE * PVNV4);
	public static final float V_ENN_INSECTE = 8 * U;
	public static final float V_ENNEMI_INSECTE3 = V_ENN_INSECTE * VNV3;
	
	// O M B R E L L E
	public static final float V_ENN_OMBRELLE = 26 * U;
	public static final int PV_OMBRELLE = 250;
	
	// A D D   B O S S   S A T
	public static final float LARGEUR_ADD_BOSS_SAT = U * 2.5f;
	public static final float V_ADD_BOSS_SAT = 5 * U;
	public static final int PV_ADD_BOSS_SAT = 25;
	
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
	public static final float V_ARME_SUN = 75 * U;
	public static final float V_ARME_SPACE_INVADER = 22 * U;
	
	// ***********************************************************************************
	// ***************   P   A   R   T   I   C   U   L   E   S   *************************
	// ***********************************************************************************
	
	public static final float V_PARTICULE_EXPLOSION = 9 * U;
	public static final float V_PARTICULE_EXPLOSION_SLOW = 4 * U;
	public static final float SPEED_DEBRIS = 2 * U;
	
	public static final int WIDTH_WEAPON_SMALL = CSG.screenWidth / 20;
	public static final int WIDTH_WEAPON_MINUS_NORMAL = CSG.screenWidth / 17;
	public static final int WIDTH_WEAPON_NORMAL = CSG.screenWidth / 15;
	public static final int WIDTH_WEAPON_BIG = CSG.screenWidth / 13;
	public static final int WIDTH_FIREBALL = CSG.screenWidth / 15;
	public static final int THRUSTER = (int) (U * 14);
	
	
	private static int getHauteur(float largeur, float f) {
		return (int) (largeur * f);
	}
}
