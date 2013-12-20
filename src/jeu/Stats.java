package jeu;


public class Stats {
	
	public static final float U = (CSG.HAUTEUR_ECRAN + CSG.LARGEUR_ECRAN) / 100f;
	public static final float VNV3 = 1.3f;
	public static final float PVNV3 = 2;
	
	public static final float V_JOUEUR = (int) CSG.HAUTEUR_ECRAN + CSG.LARGEUR_ECRAN + ((CSG.HAUTEUR_ECRAN + CSG.LARGEUR_ECRAN)/10);
	public static final int LARGEUR_JOUEUR = (int) (4f * U);
	
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
	public static final int LARGEUR_DE_BASE = (int) (3f * U);
	public static final int HAUTEUR_DE_BASE = getHauteur(LARGEUR_DE_BASE, 1.5f);
	
	// D E   B A S E   Q U I   T I R
	public static final float V_ENN_QUI_TIR = 5 * U;
	public static final float V_ENN_MAX_QUI_TIR3 = V_ENN_QUI_TIR * VNV3;
	public static final float DERIVE_QUI_TIR = U;
	public static final int PV_QUI_TIR = 19;
	public static final int PV_QUI_TIR3 = (int) (PV_QUI_TIR * PVNV3);
	public static final int DEMI_PV_QUI_TIR = PV_QUI_TIR / 2;
	public static final int DEMI_PV_QUI_TIR3 = PV_DE_BASE_NV3 / 2;
	public static final int LARGEUR_QUI_TIR = (int) ((float)HAUTEUR_DE_BASE * 1.1f);
	public static final int HAUTEUR_QUI_TIR = getHauteur(LARGEUR_QUI_TIR, 1.5f);
	public static final int LARGEUR_QUI_TIR3 = (int) ((float)LARGEUR_QUI_TIR * 1.2f);
	public static final int HAUTEUR_QUI_TIR3 = (int) ((float)HAUTEUR_QUI_TIR * 1.2f);
	
	// Z I G   Z A G
	public static final int PV_ZIGZAG = 9;
	public static final int PV_ZIGZAG_NV3 = (int) (PV_ZIGZAG * PVNV3);
	public static final int LARGEUR_ZIG_ZAG = (int) ((float)LARGEUR_DE_BASE * 1.35f); 
	public static final int HAUTEUR_ZIG_ZAG = getHauteur(LARGEUR_ZIG_ZAG, 1.2f); 
	public static final float V_ENN_ZIGZAG = 7 * U;
	public static final float V_ENN_ZIGZAG_NV3 = V_ENN_ZIGZAG * VNV3;
	
	// B O U L E 
	public static final float V_ENN_BOULE = 9 * U;
	public static final int PV_BOULE = 30;
	public static final int PV_BOULE_QUI_SARRETE3 = (int) (PV_BOULE * PVNV3);
	public static final int LARGEUR_BOULE = (int) (((float)LARGEUR_DE_BASE / 2f) * 1.5f);
	
	// P O R T E   N E F
	public static final float V_ENN_PORTE_NEF = 2 * U;
	public static final int PV_BOSS_SAT = 115;
	public static final int DEMI_PV_PORTE_NEF = PV_BOSS_SAT/2;
	public static final int LARGEUR_BOSS_SAT = (int) ((float)LARGEUR_DE_BASE * 3);
	
	// Q U I   T O U R N E
	public static final float V_ENN_QUI_TOURNE = 13 * U;
	public static final float DEMI_V_ENN_QUI_TOURNE = V_ENN_QUI_TOURNE/2;
	public static final float V_ENN_QUI_TOURNE3 = V_ENN_QUI_TOURNE * VNV3;
	public static final float DEMI_V_ENN_QUI_TOURNE3 = V_ENN_QUI_TOURNE3 / 2;
	public static final int PV_QUI_TOURNE = 60;
	public static final int PV_QUI_TOURNE3 = (int) (PV_QUI_TOURNE * PVNV3);
	public static final int LARGEUR_QUI_TOURNE = (int) ((float)LARGEUR_DE_BASE * 1.1f);
	
	// T O U P I E
	public static final float V_ENN_TOUPIE = 10 * U;
	public static final float DEMI_V_ENN_TOUPIE = V_ENN_TOUPIE / 2;
	public static final int PV_TOUPIE = 70;
	public static final int PV_TOUPIE3 = (int) (PV_TOUPIE * PVNV3);
	public static final int LARGEUR_TOUPIE = (int) ((float)LARGEUR_DE_BASE * 1.1f);
	public static final int HAUTEUR_TOUPIE = getHauteur(LARGEUR_TOUPIE, 1.55f);
	
	// C Y L O N
	public static final int PV_CYLON = 85;
	public static final int PV_CYLON3 = (int) (PV_CYLON * PVNV3);
	public static final int DEMI_PV_CYLON = PV_CYLON / 2;
	public static final int DEMI_PV_CYLON3 = PV_CYLON3 / 2;
	public static final int LARGEUR_CYLON = (int) ((float)LARGEUR_DE_BASE * 1.8f);
	public static final float V_ENN_CYLON = 5 * U;
	public static final float V_ENN_CYLON3 = V_ENN_CYLON * VNV3;
	
	// K I N D E R
	public static final int PV_KINDER = 100;
	public static final int PV_KINDER2 = 120;
	public static final float V_ENN_KINDER = 8 * U;
	public static final int PV_KINDER3 = (int) (PV_KINDER * PVNV3);
	public static final int PV_KINDER2_3 = (int) (PV_KINDER2 * PVNV3);
	public static final int LARGEUR_KINDER = LARGEUR_DE_BASE * 2;
	
	// B O S S   Q U A D
	public static final int PV_BOSS_QUAD = 430;
	public static final int DEUXTIERS_PV_ENN_BOSS_QUAD = 280;
	public static final int PRESQUE_MORT_PV_BOSS_QUAD = 125;
	public static final int LARGEUR_BOSS_QUAD = (int) ((float)LARGEUR_DE_BASE * 3);
	public static final int HAUTEUR_BOSS_QUAD = getHauteur(LARGEUR_BOSS_QUAD, 0.5f);
	public static final float V_ENN_BOSS_QUAD = 11 * U;
	
	// P O R T E   R A I S I N
	public static final float V_ENN_PORTE_RAISIN = 1 * U;
	public static final int PV_PORTE_RAISIN = 340;
	public static final int PV_PORTE_RAISIN_AMOCHE = PV_PORTE_RAISIN / 2;
	public static final int PV_PORTE_RAISIN3 = (int) (PV_PORTE_RAISIN * PVNV3);
	public static final int LARGEUR_PORTE_RAISIN = LARGEUR_DE_BASE * 3;
	public static final int HAUTEUR_PORTE_RAISIN = getHauteur(LARGEUR_PORTE_RAISIN, 1.25f);
	
	// A V I O N
	public static final float V_ENN_AVION = 9.5f * U;
	public static final float V_ENN_AVION3 = V_ENN_AVION * VNV3;
	public static final int PV_AVION = 130;
	public static final int PVMAX_AVION_AMOCHE = PV_AVION / 2;
	public static final int PV_AVION3 = (int) (PV_AVION * PVNV3);
	public static final int LARGEUR_AVION = LARGEUR_DE_BASE * 2;
	public static final int HAUTEUR_AVION = (int) ((float)HAUTEUR_DE_BASE * 1.5f);
	
	// D E   B A S E   Q U I   T I R  2
	public static final float V_ENN_DE_BASE_QUI_TIR2 = 6 * U;
	public static final float DERIVE_DE_BASE_QUI_TIR2 = U;
	public static final int PV_DE_BASE_QUI_TIR2 = 190;
	public static final int DEMI_PV_BASE_QUI_TIR2 = PV_DE_BASE_QUI_TIR2 / 2;
	public static final int LARGEUR_QUI_TIR2 = (int) ((float)LARGEUR_QUI_TIR * 1.2f);
	public static final int HAUTEUR_QUI_TIR2 = (int) ((float)HAUTEUR_QUI_TIR * 1.2f);
	
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
	public static final int LARGEUR_LASER = Stats.LARGEUR_DE_BASE * 3;
	
	// L A S E R   C O T E 
	public static final float V_BOULE_TIR_COTE = V_ENN_BOULE + U;
	public static final float V_BOULE_TIR_COTE_PETIT = V_ENN_BOULE - U;
	public static final int PV_LASER_COTE = 240;
	public static final int PV_BOULE_COTE_PETIT = 145;
	public static final int LARGEUR_BOULE_TIR_COTE = (int) ((float)LARGEUR_BOULE * 1.2f);
	
	// I N S E C T E 
	public static final int LARGEUR_INSECTE = LARGEUR_DE_BASE * 2;
	public static final int PV_INSECTE = 380;
	public static final int PV_INSECTE_DEMI = PV_INSECTE / 2;
	public static final int PV_INSECTE3 = (int) (PV_INSECTE * PVNV3);
	public static final float V_ENN_INSECTE = 4 * U;
	public static final float V_ENNEMI_INSECTE3 = V_ENN_INSECTE * VNV3;
	
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
	
	private static int getHauteur(float largeur, float f) {
		return (int) (largeur * f);
	}

//	private static void init() {
//		U = (CSG.HAUTEUR_ECRAN + CSG.LARGEUR_ECRAN) / 100f;
//		VNV3 = 1.3f;
//		PVNV3 = 2;
//		
//		V_JOUEUR = (int) CSG.HAUTEUR_ECRAN + CSG.LARGEUR_ECRAN + ((CSG.HAUTEUR_ECRAN + CSG.LARGEUR_ECRAN)/10);
//		
//		// ***********************************************************************************
//		// ******************************   B O N U S   **************************************
//		// ***********************************************************************************
//		
//		V_BONUS = 4 * U;
//		V_BONUS_XP = V_BONUS/1.5f;
//		V_BONUS_BOMBE = V_BONUS/2;
//		V_BONUS_STOP = V_BONUS/1.6f;
//		V_BONUS_TEMPS = V_BONUS/1.4f;
//		V_BONUS_BOUCLIER = (V_BONUS * 1.1f);
//		BONUS_WIDTH = U * 2;
//		DEMI_LARGEUR = BONUS_WIDTH/2;
//		V_BONUS_ADD = V_BONUS/1.8f;
//		BONUS_ADD_ACCELERATION = 15;
//		
//		// ***********************************************************************************
//		// **********************   E  N  N  E  M  I  S   ************************************
//		// ***********************************************************************************
//		
//		// E N N E M I   D E   B A S E
//		V_ENN_DE_BASE = 9 * U;
//		V_ENN_DE_BASE_3 = V_ENN_DE_BASE * VNV3;
//		PV_DE_BASE = 5;
//		PV_DE_BASE_NV3 = (int) (PV_DE_BASE * PVNV3);
//		LARGEUR_DE_BASE = (int) (40 * U);
//		HAUTEUR_DE_BASE = getHauteur(LARGEUR_DE_BASE, 1.5f);
//		
//		// E N N E M I   D E   B A S E   Q U I   T I R
//		V_ENN_QUI_TIR = 5 * U;
//		V_ENN_MAX_QUI_TIR3 = V_ENN_QUI_TIR * VNV3;
//		DERIVE_QUI_TIR = U;
//		PV_QUI_TIR = 19;
//		PV_QUI_TIR3 = (int) (PV_QUI_TIR * PVNV3);
//		DEMI_PV_QUI_TIR = PV_QUI_TIR / 2;
//		DEMI_PV_QUI_TIR3 = PV_DE_BASE_NV3 / 2;
//		LARGEUR_QUI_TIR = (int) ((float)HAUTEUR_DE_BASE * 1.2f);
//		HAUTEUR_QUI_TIR = getHauteur(LARGEUR_QUI_TIR, 1.5f);
//		
//		// Z I G   Z A G
//		PV_ZIGZAG = 9;
//		PV_ZIGZAG_NV3 = (int) (PV_ZIGZAG * PVNV3);
//		LARGEUR_ZIG_ZAG = (int) ((float)LARGEUR_DE_BASE * 1.25f); 
//		HAUTEUR_ZIG_ZAG = getHauteur(LARGEUR_ZIG_ZAG, 1.2f); 
//		V_ENN_ZIGZAG = 7 * U;
//		V_ENN_ZIGZAG_NV3 = V_ENN_ZIGZAG * VNV3;
//		
//		// B O U L E 
//		V_ENN_BOULE = 9 * U;
//		PV_BOULE = 30;
//		PV_BOULE_QUI_SARRETE3 = (int) (PV_BOULE * PVNV3);
//		LARGEUR_BOULE = (int) (((float)LARGEUR_DE_BASE / 2f) * 1.5f);
//		
//		// P O R T E   N E F
//		V_ENN_PORTE_NEF = 2 * U;
//		PV_BOSS_SAT = 115;
//		DEMI_PV_PORTE_NEF = PV_BOSS_SAT/2;
//		LARGEUR_BOSS_SAT = (int) (U * 8);
//		
//		// Q U I   T O U R N E
//		V_ENN_QUI_TOURNE = 13 * U;
//		DEMI_V_ENN_QUI_TOURNE = V_ENN_QUI_TOURNE/2;
//		V_ENN_QUI_TOURNE3 = V_ENN_QUI_TOURNE * VNV3;
//		DEMI_V_ENN_QUI_TOURNE3 = V_ENN_QUI_TOURNE3 / 2;
//		PV_QUI_TOURNE = 60;
//		PV_QUI_TOURNE3 = (int) (PV_QUI_TOURNE * PVNV3);
//		LARGEUR_QUI_TOURNE = (int) ((float)LARGEUR_DE_BASE * 1.1f);
//		HAUTEUR_QUI_TOURNE = getHauteur(LARGEUR_QUI_TOURNE);
//		
//		// T O U P I E
//		V_ENN_TOUPIE = 10 * U;
//		DEMI_V_ENN_TOUPIE = V_ENN_TOUPIE / 2;
//		PV_TOUPIE = 70;
//		PV_TOUPIE3 = (int) (PV_TOUPIE * PVNV3);
//		LARGEUR_TOUPIE = (int) ((float)LARGEUR_DE_BASE * 1.1f);
//		HAUTEUR_TOUPIE = getHauteur(LARGEUR_TOUPIE, 1.55f);
//		
//		// C Y L O N
//		PV_CYLON = 85;
//		PV_CYLON3 = (int) (PV_CYLON * PVNV3);
//		DEMI_PV_CYLON = PV_CYLON / 2;
//		DEMI_PV_CYLON3 = PV_CYLON3 / 2;
//		LARGEUR_CYLON = (int) ((float)LARGEUR_DE_BASE * 1.8f);
//		V_ENN_CYLON = 5 * U;
//		V_ENN_CYLON3 = V_ENN_CYLON * VNV3;
//		
//		// K I N D E R
//		PV_KINDER = 100;
//		PV_KINDER2 = 120;
//		V_ENN_KINDER = 8 * U;
//		PV_KINDER3 = (int) (PV_KINDER * PVNV3);
//		PV_KINDER2_3 = (int) (PV_KINDER2 * PVNV3);
//		LARGEUR_KINDER = LARGEUR_DE_BASE * 2;
//		HAUTEUR_KINDER = (int) ((float)getHauteur(LARGEUR_KINDER) * 1.5f);
//		
//		// B O S S   Q U A D
//		PV_BOSS_QUAD = 430;
//		DEUXTIERS_PV_ENN_BOSS_QUAD = 280;
//		PRESQUE_MORT_PV_BOSS_QUAD = 125;
//		LARGEUR_BOSS_QUAD = (int) (11 * U);
//		HAUTEUR_BOSS_QUAD = (int) ((float)LARGEUR_BOSS_QUAD * CSG.RATIO * 1.25f);
//		V_ENN_BOSS_QUAD = 11 * U;
//		
//		// P O R T E   R A I S I N
//		V_ENN_PORTE_RAISIN = 1 * U;
//		PV_PORTE_RAISIN = 340;
//		PV_PORTE_RAISIN_AMOCHE = PV_PORTE_RAISIN / 2;
//		PV_PORTE_RAISIN3 = (int) (PV_PORTE_RAISIN * PVNV3);
//		LARGEUR_PORTE_RAISIN = LARGEUR_DE_BASE * 3;
//		HAUTEUR_PORTE_RAISIN = getHauteur(LARGEUR_PORTE_RAISIN, 1.25f);
//		
//		// A V I O N
//		V_ENN_AVION = 9.5f * U;
//		V_ENN_AVION3 = V_ENN_AVION * VNV3;
//		PV_AVION = 130;
//		PVMAX_AVION_AMOCHE = PV_AVION / 2;
//		PV_AVION3 = (int) (PV_AVION * PVNV3);
//		LARGEUR_AVION = LARGEUR_DE_BASE * 2;
//		HAUTEUR_AVION = (int) ((float)HAUTEUR_DE_BASE * 1.5f);
//		
//		// D E   B A S E   Q U I   T I R  2
//		V_ENN_DE_BASE_QUI_TIR2 = 6 * U;
//		DERIVE_DE_BASE_QUI_TIR2 = U;
//		PV_DE_BASE_QUI_TIR2 = 190;
//		DEMI_PV_BASE_QUI_TIR2 = PV_DE_BASE_QUI_TIR2 / 2;
//		LARGEUR_QUI_TIR2 = (int) ((float)LARGEUR_QUI_TIR * 1.2f);
//		HAUTEUR_QUI_TIR2 = (int) ((float)HAUTEUR_QUI_TIR * 1.2f);
//		
//		// B O S S   M  I  N  E
//		PV_BOSS_MINE = 730;
//		DEUXTIERS_PV_BOSS_MINE = (PV_BOSS_MINE / 3) * 2;
//		PRESQUE_MORT_PV_BOSS_MINE = (PV_BOSS_MINE / 3);
//		LARGEUR_BOOS_MINE = (int) (U * 4);
//		HAUTEUR_BOOS_MINE = (int) (LARGEUR_BOOS_MINE * LARGEUR_BOOS_MINE * CSG.RATIO * 4);
//		V_ENN_BOSS_MINE = 5 * U;
//		
//		// L A S E R   
//		V_ENN_LASER = 8 * U;
//		PV_LASER = 190;
//		V_ENN_LASER3 = V_ENN_LASER * VNV3;
//		PV_LASER3 = (int) (PV_LASER * PVNV3);
//		LARGEUR_LASER = Stats.LARGEUR_DE_BASE * 3;
//		HAUTEUR_LASER = getHauteur(LARGEUR_LASER);
//		
//		// L A S E R   C O T E 
//		V_BOULE_TIR_COTE = V_ENN_BOULE + U;
//		V_BOULE_TIR_COTE_PETIT = V_ENN_BOULE - U;
//		PV_LASER_COTE = 240;
//		PV_BOULE_COTE_PETIT = 145;
//		
//		// I N S E C T E 
//		LARGEUR_INSECTE = LARGEUR_DE_BASE * 2;
//		HAUTEUR_INSECTE = getHauteur(LARGEUR_INSECTE);
//		PV_INSECTE = 380;
//		PV_INSECTE_DEMI = PV_INSECTE / 2;
//		PV_INSECTE3 = (int) (PV_INSECTE * PVNV3);
//		V_ENN_INSECTE = 4 * U;
//		V_ENNEMI_INSECTE3 = V_ENN_INSECTE * VNV3;
//		
//		// O M B R E L L E
//		V_ENN_OMBRELLE = 26 * U;
//		PV_OMBRELLE = 250;
//		
//		// A D D   B O S S   S A T
//		LARGEUR_ADD_BOSS_SAT = U * 2;
//		V_ADD_BOSS_SAT = 9 * U;
//		PV_ADD_BOSS_SAT = 20;
//		
//		// ***********************************************************************************
//		// ***********************   A   R   M   E   S   *************************************
//		// ***********************************************************************************
//		
//		// F R A G
//		V_ARME_FRAG = 11 * U;
//		V_ARME_FRAGMENTEE = V_ARME_FRAG / 2;
//		// A R M E   B O U L E   B L E U E
//		V_ARME_BOULE_BLEU = 10 * U;
//		V_BOULE_BLEU_RAPIDE = V_ARME_BOULE_BLEU * 1.2f;
//		V_ARME_BOULE_VERTE_RAPIDE = 12 * U;
//		V_ARME_BOULE_VERTE_ROTATION = V_ARME_BOULE_VERTE_RAPIDE / 1.5f;
//		// A R M E   K I N D E R
//		V_ARME_KINDER = 13 * U;
//		// A R M E   B O S S   Q U A D 
//		V_ARME_BOSS_QUAD = 18 * U;
//		// A R M E   S H U R I K E N
//		V_ARME_RAISIN = 19 * U;
//		// A R M E   A V I O N
//		V_ARME_AVION = 24 * U;
//		// A R M E   E N N E M I   L A S E R
//		V_ARME_LASER = 13 * U;
//		// A R M E   I N S E C T E
//		V_INSECTE = 15 * U;
//		// =======================  J  O  U  E  U  R  ==============================
//		// A R M E   H  A N T E E
//		V_ARME_HANTEE = 75 * U;
//		// A R M E   B A L A Y A G E
//		V_ARME_BALAYAGE = 45 * U;
//		// A R M E   D E   B A S E
//		V_ARME_DE_BASE = 55 * U;
//		// A R M E   T R O I S
//		V_ARME_TROIS = 50 * U;
//		// A R M E   A D D  
//		V_ARME_ADD = 50 * U;
//		
//		// ***********************************************************************************
//		// ***************   P   A   R   T   I   C   U   L   E   S   *************************
//		// ***********************************************************************************
//		
//		// E X P L O S I O N 
//		V_PARTICULE_EXPLOSION = 5 * U;
//		// D E B R I S
//		V_PARTICULE_DEBRIS = 2 * U;
//	}
}
