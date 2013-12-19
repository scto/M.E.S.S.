package jeu;


public class Stats {
	
	public static final float U = (CSG.HAUTEUR_ECRAN + CSG.LARGEUR_ECRAN) / 100f;
	public static final float VNV3 = 1.3f;
	public static final float PVNV3 = 2;
	
	public static final float V_JOUEUR = (int) CSG.HAUTEUR_ECRAN + CSG.LARGEUR_ECRAN + ((CSG.HAUTEUR_ECRAN + CSG.LARGEUR_ECRAN)/10);
	
	// ***********************************************************************************
	// ******************************   B O N U S   **************************************
	// ***********************************************************************************
	
	public static final float V_BONUS = 4 * U;
	public static final float V_BONUS_XP = V_BONUS/1.5f;
	public static final float V_BONUS_BOMBE = V_BONUS/2;
	public static final float V_BONUS_STOP = V_BONUS/1.6f;
	public static final float V_BONUS_TEMPS = V_BONUS/1.4f;
	public static final float V_BONUS_BOUCLIER = (V_BONUS * 1.1f);
	public static final float BONUS_WIDTH = U * 2, DEMI_LARGEUR = BONUS_WIDTH/2;
	public static final float V_BONUS_ADD = V_BONUS/1.8f, BONUS_ADD_ACCELERATION = 15;
	
	// ***********************************************************************************
	// **********************   E  N  N  E  M  I  S   ************************************
	// ***********************************************************************************
	
	// E N N E M I   D E   B A S E
	public static final float V_ENN_DE_BASE = 9 * U;
	public static final float V_ENN_DE_BASE_3 = V_ENN_DE_BASE * VNV3;
	public static final int PV_DE_BASE = 5;
	public static final int PV_DE_BASE_NV3 = (int) (PV_DE_BASE * PVNV3);
	
	// E N N E M I   D E   B A S E   Q U I   T I R
	public static final float V_ENN_QUI_TIR = 5 * U;
	public static final float V_ENN_MAX_QUI_TIR3 = V_ENN_QUI_TIR * VNV3;
	public static final float DERIVE_QUI_TIR = U;
	public static final int PV_QUI_TIR = 19;
	public static final int PV_QUI_TIR3 = (int) (PV_QUI_TIR * PVNV3);
	public static final int DEMI_PV_QUI_TIR = PV_QUI_TIR / 2;
	public static final int DEMI_PV_QUI_TIR3 = PV_DE_BASE_NV3 / 2;
	
	// Z I G   Z A G
	public static final int PV_ZIGZAG = 9;
	public static final int PV_ZIGZAG_NV3 = (int) (PV_ZIGZAG * PVNV3);
	public static final float V_ENN_ZIGZAG = 7 * U;
	public static final float V_ENN_ZIGZAG_NV3 = V_ENN_ZIGZAG * VNV3;
	
	// B O U L E    Q U I    S ' A R R E T E
	public static final float V_ENN_BOULE_QUI_SARRETE = 9 * U;
	public static final int PV_BOULE_QUI_SARRETE = 30;
	public static final int PV_BOULE_QUI_SARRETE3 = (int) (PV_BOULE_QUI_SARRETE * PVNV3);
	
	// P O R T E   N E F
	public static final float V_ENN_PORTE_NEF = 2 * U;
	public static final int PV_BOSS_SAT = 115;
	public static final int DEMI_PV_PORTE_NEF = PV_BOSS_SAT/2;
	public static final int LARGEUR_BOSS_SAT = (int) (U * 8);
	
	// Q U I   T O U R N E
	public static final float V_ENN_QUI_TOURNE = 13 * U;
	public static final float DEMI_V_ENN_QUI_TOURNE = V_ENN_QUI_TOURNE/2;
	public static final float V_ENN_QUI_TOURNE3 = V_ENN_QUI_TOURNE * VNV3;
	public static final float DEMI_V_ENN_QUI_TOURNE3 = V_ENN_QUI_TOURNE3 / 2;
	public static final int PV_QUI_TOURNE = 60;
	public static final int PV_QUI_TOURNE3 = (int) (PV_QUI_TOURNE * PVNV3);
	
	// T O U P I E
	public static final float V_ENN_TOUPIE = 10 * U;
	public static final float DEMI_V_ENN_TOUPIE = V_ENN_TOUPIE / 2;
	public static final int PV_TOUPIE = 70;
	public static final int PV_TOUPIE3 = (int) (PV_TOUPIE * PVNV3);
	
	// C Y L O N
	public static final int PV_CYLON = 85;
	public static final int PV_CYLON3 = (int) (PV_CYLON * PVNV3);
	public static final int DEMI_PV_CYLON = PV_CYLON / 2;
	public static final int DEMI_PV_CYLON3 = PV_CYLON3 / 2;
	public static final float V_ENN_CYLON = 5 * U;
	public static final float V_ENN_CYLON3 = V_ENN_CYLON * VNV3;
	
	// K I N D E R
	public static final int PV_KINDER = 100;
	public static final int PV_KINDER2 = 120;
	public static final float V_ENN_KINDER = 8 * U;
	public static final int PV_KINDER3 = (int) (PV_KINDER * PVNV3);
	public static final int PV_KINDER2_3 = (int) (PV_KINDER2 * PVNV3);
	
	// B O S S   Q U A D
	public static final int PV_BOSS_QUAD = 430;
	public static final int DEUXTIERS_PV_ENN_BOSS_QUAD = 280;
	public static final int PRESQUE_MORT_PV_BOSS_QUAD = 125;
	public static final int LARGEUR_BOSS_QUAD = (int) (11 * U);
	public static final int HAUTEUR_BOSS_QUAD = (int) ((float)LARGEUR_BOSS_QUAD * CSG.RATIO * 1.25f);
	public static final float V_ENN_BOSS_QUAD = 11 * U;
	
	// P O R T E   R A I S I N
	public static final float V_ENN_PORTE_RAISIN = 1 * U;
	public static final int PV_PORTE_RAISIN = 340;
	public static final int PV_PORTE_RAISIN_AMOCHE = PV_PORTE_RAISIN / 2;
	public static final int PV_PORTE_RAISIN3 = (int) (PV_PORTE_RAISIN * PVNV3);
	
	// A V I O N
	public static final float V_ENN_AVION = 9.5f * U;
	public static final float V_ENN_AVION3 = V_ENN_AVION * VNV3;
	public static final int PV_AVION = 130;
	public static final int PVMAX_AVION_AMOCHE = PV_AVION / 2;
	public static final int PV_AVION3 = (int) (PV_AVION * PVNV3);
	
	// E N N E M I   D E   B A S E   Q U I   T I R 2
	public static final float V_ENN_DE_BASE_QUI_TIR2 = 6 * U;
	public static final float DERIVE_DE_BASE_QUI_TIR2 = U;
	public static final int PV_DE_BASE_QUI_TIR2 = 190;
	public static final int DEMI_PV_BASE_QUI_TIR2 = PV_DE_BASE_QUI_TIR2 / 2;
	
	// B O S S   M  I  N  E
	public static final int PV_BOSS_MINE = 730;
	public static final int DEUXTIERS_PV_BOSS_MINE = (PV_BOSS_MINE / 3) * 2;
	public static final int PRESQUE_MORT_PV_BOSS_MINE = (PV_BOSS_MINE / 3);
	public static final int LARGEUR_BOOS_MINE = (int) (U * 4);
	public static final int HAUTEUR_BOOS_MINE = (int) (LARGEUR_BOOS_MINE * LARGEUR_BOOS_MINE * CSG.RATIO * 4);
	public static final float V_ENN_BOSS_MINE = 5 * U;
	
	// L A S E R   
	public static final float V_ENN_LASER = 8 * U;
	public static final int PV_LASER = 190;
	public static final float V_ENN_LASER3 = V_ENN_LASER * VNV3;
	public static final int PV_LASER3 = (int) (PV_LASER * PVNV3);
	
	// L A S E R   C O T E 
	public static final float V_BOULE_TIR_COTE = V_ENN_BOULE_QUI_SARRETE + U;
	public static final float V_BOULE_TIR_COTE_PETIT = V_ENN_BOULE_QUI_SARRETE - U;
	public static final int PV_LASER_COTE = 240;
	public static final int PV_BOULE_COTE_PETIT = 145;
	
	// E N N E M I   I N S E C T E 
	public static final float V_ENN_INSECTE = 4 * U;
	public static final int PV_INSECTE = 380;
	public static final int PV_INSECTE_DEMI = PV_INSECTE / 2;
	public static final float V_ENNEMI_INSECTE3 = V_ENN_INSECTE * VNV3;
	public static final int PV_INSECTE3 = (int) (PV_INSECTE * PVNV3);
	
	// O M B R E L L E
	public static final float V_ENN_OMBRELLE = 26 * U;
	public static final int PV_OMBRELLE = 250;
	
	// A D D   B O S S   S A T
	public static final float LARGEUR_ADD_BOSS_SAT = U * 2;
	public static final float V_ADD_BOSS_SAT = 9 * U;
	public static final int PV_ADD_BOSS_SAT = 20;
	
	// ***********************************************************************************
	// ***********************   A   R   M   E   S   *************************************
	// ***********************************************************************************
	
	// F R A G
	public static final float V_ARME_FRAG = 11 * U;
	public static final float V_ARME_FRAGMENTEE = V_ARME_FRAG / 2;
	// A R M E   B O U L E   B L E U E
	public static final float V_ARME_BOULE_BLEU = 10 * U;
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
	public static final float V_ARME_LASER = 13 * U;
	// A R M E   I N S E C T E
	public static final float V_INSECTE = 15 * U;
	// =======================  J  O  U  E  U  R  ==============================
	// A R M E   H  A N T E E
	public static final float V_ARME_HANTEE = 75 * U;
	// A R M E   B A L A Y A G E
	public static final float V_ARME_BALAYAGE = 45 * U;
	// A R M E   D E   B A S E
	public static final float V_ARME_DE_BASE = 55 * U;
	// A R M E   T R O I S
	public static final float V_ARME_TROIS = 50 * U;
	// A R M E   A D D  
	public static final float V_ARME_ADD = 50 * U;
	
	// ***********************************************************************************
	// ***************   P   A   R   T   I   C   U   L   E   S   *************************
	// ***********************************************************************************
	
	// E X P L O S I O N 
	public static final float V_PARTICULE_EXPLOSION = 5 * U;
	// D E B R I S
	public static final float V_PARTICULE_DEBRIS = 2 * U;
}
