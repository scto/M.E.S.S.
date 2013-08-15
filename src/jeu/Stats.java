package jeu;

import menu.CSG;

public class Stats {
	
	public static final int VITESSE_BONUS = (int) (CSG.HAUTEUR_ECRAN/10.666f);
	public static final int VITESSE_BONUS_BOMBE = VITESSE_BONUS/2;
	public static final int VITESSE_BONUS_BOUCLIER = (int) (VITESSE_BONUS * 1.1f);
	public static final int VITESSE_JOUEUR = (int) CSG.HAUTEUR_ECRAN + CSG.LARGEUR_ECRAN + ((CSG.HAUTEUR_ECRAN + CSG.LARGEUR_ECRAN)/10);
	
	// ***********************************************************************************
	// **********************   E  N  N  E  M  I  S   ************************************
	// ***********************************************************************************
	
	// E N N E M I   D E   B A S E
	public static final int VITESSE_MAX_DE_BASE = (int) (CSG.HAUTEUR_ECRAN/5.33f);
	public static final int PVMAX_DE_BASE = 5;
	public static final int PVMAX_DE_BASE_NV3 = 14;
	public static final int VITESSE_MAX_DE_BASE_NV3 = (int) (CSG.HAUTEUR_ECRAN/4.13f);
	
	// E N N E M I   D E   B A S E   Q U I   T I R
	public static final int VITESSE_MAX_DE_BASE_QUI_TIR = -CSG.HAUTEUR_ECRAN / 11;
	public static final int PVMAX_DE_BASE_QUI_TIR = 19;
	public static final int DEMI_PV_BASE_QUI_TIR = 9;
	public static final int DERIVE_DE_BASE_QUI_TIR = CSG.LARGEUR_ECRAN / 15;
	// -- 3
	public static final int VITESSE_MAX_DE_BASE_QUI_TIR3 = -CSG.HAUTEUR_ECRAN / 9;
	public static final int PVMAX_DE_BASE_QUI_TIR3 = 32;
	public static final int DEMI_PV_BASE_QUI_TIR3 = 18;
	
	// Z I G   Z A G
	public static final int PVMAX_ZIGZAG = 9;
	public static final int PVMAX_ZIGZAG_NV3 = 18;
	public static final int VITESSE_MAX_ZIGZAG = CSG.HAUTEUR_ECRAN / 10;
	public static final int VITESSE_MAX_ZIGZAG_NV3 = CSG.HAUTEUR_ECRAN / 10;
	
	// A I L E S    D E P L O Y E E S
	public static final int VITESSE_MAX_AILES_DEPLOYEE = CSG.HAUTEUR_ECRAN / 10;
	public static final int PVMAX_AILES_DEPLOYEE = 20;
	
	// B O U L E    Q U I    S ' A R R E T E
	public static final int VITESSE_MAX_BOULE_QUI_SARRETE = -CSG.HAUTEUR_ECRAN / 10;
	public static final int PVMAX_BOULE_QUI_SARRETE = 30;
	public static final int PVMAX_BOULE_QUI_SARRETE3 = 60;
	
	// P O R T E   N E F
	public static final int VITESSE_MAX_PORTE_NEF = CSG.LARGEUR_ECRAN/12;
	public static final int PVMAX_PORTE_NEF = 115;
	public static final int DEMI_PVMAX_PORTE_NEF = PVMAX_PORTE_NEF/2;
	
	// Q U I   T O U R N E
	public static final int VITESSE_QUI_TOURNE = (int) (CSG.HAUTEUR_ECRAN/3.33f);
	public static final int DEMI_VITESSE_QUI_TOURNE = VITESSE_QUI_TOURNE/2;
	public static final int PVMAX_QUI_TOURNE = 60;
	public static final int VITESSE_QUI_TOURNE3 = (int) (CSG.HAUTEUR_ECRAN/2.4f);
	public static final int DEMI_VITESSE_QUI_TOURNE3 = VITESSE_QUI_TOURNE/2;
	public static final int PVMAX_QUI_TOURNE3 = 85;
	
	// T O U P I E
	public static final int VITESSE_TOUPIE = (int) (CSG.HAUTEUR_ECRAN/8.88f);
	public static final int DEMI_VITESSE_TOUPIE = VITESSE_TOUPIE/2;
	public static final int PVMAX_TOUPIE = 70;
	public static final int PVMAX_TOUPIE3 = 100;
	
	// C Y L O N
	public static final int PVMAX_CYLON = 85;
	public static final int DEMI_PV_CYLON = 45;
	public static final int VITESSE_CYLON = CSG.HAUTEUR_ECRAN/10;
	// -- 3
	public static final int PVMAX_CYLON3 = 145;
	public static final int DEMI_PV_CYLON3 = 85;
	public static final int VITESSE_CYLON3 = CSG.HAUTEUR_ECRAN/8;
	
	// K I N D E R
	public static final int PVMAX_KINDER = 100;
	public static final int PV_KINDER2 = 170;
	public static final int VITESSE_KINDER = (int) (CSG.HAUTEUR_ECRAN/12f);
	public static final int PVMAX_KINDER3 = 200;
	public static final int PVMAX_KINDER_DOUBLE3 = 340;
	
	// B O S S   Q U A D
	public static final int PVMAX_BOSS_QUAD = 430;
	public static final int DEUXTIERS_PVMAX_BOSS_QUAD = 280;
	public static final int PRESQUE_MORT_PV_BOSS_QUAD = 125;
	public static final int VITESSE_BOSS_QUAD = (int) (CSG.HAUTEUR_ECRAN/7f);
	
	// P O R T E   R A I S I N
	public static final int VITESSE_PORTE_RAISIN = CSG.HAUTEUR_ECRAN / 30;
	public static final int PVMAX_PORTE_RAISIN = 340;
	public static final int PVMAX_PORTE_RAISIN_AMOCHE = 150;
	public static final int PVMAX_PORTE_RAISIN3 = 420;
	
	// A V I O N
	public static final int VITESSE_AVION = CSG.HAUTEUR_ECRAN / 8;
	public static final int PVMAX_AVION = 130;
	public static final int PVMAX_AVION_AMOCHE = 80;
	// -- 
	public static final int VITESSE_AVION3 = CSG.HAUTEUR_ECRAN / 6;
	public static final int PVMAX_AVION3 = 190;
	
	// E N N E M I   D E   B A S E   Q U I   T I R 2
	public static final int VITESSE_MAX_DE_BASE_QUI_TIR2 = -CSG.HAUTEUR_ECRAN / 13;
	public static final int DERIVE_DE_BASE_QUI_TIR2 = CSG.LARGEUR_ECRAN / 20;
	public static final int PVMAX_DE_BASE_QUI_TIR2 = 190;
	public static final int DEMI_PV_BASE_QUI_TIR2 = 90;
	
	// B O S S   M  I  N  E
	public static final int PVMAX_BOSS_MINE = 730;
	public static final int DEUXTIERS_PVMAX_BOSS_MINE = 390;
	public static final int PRESQUE_MORT_PV_BOSS_MINE = 135;
	public static final int VITESSE_BOSS_MINE = (int) (CSG.HAUTEUR_ECRAN/6f);
	
	// L A S E R   
	public static final int VITESSE_ENNEMI_LASER = (CSG.HAUTEUR_ECRAN / 12);
	public static final int PVMAX_LASER = 190;
	// -- 3
	public static final int VITESSE_ENNEMI_LASER3 = (CSG.HAUTEUR_ECRAN / 9);
	public static final int PVMAX_LASER3 = 290;
	
	// L A S E R   C O T E 
	public static final int VITESSE_BOULE_TIR_COTE = (CSG.HAUTEUR_ECRAN / 14);
	public static final int VITESSE_BOULE_TIR_COTE_PETIT = (CSG.HAUTEUR_ECRAN / 8);
	public static final int PVMAX_LASER_COTE = 240;
	public static final int PV_BOULE_COTE_PETIT = 145;
	
	// E N N E M I   I N S E C T E 
	public static final int VITESSE_ENNEMI_INSECTE = (CSG.HAUTEUR_ECRAN / 20);
	public static final int PVMAX_INSECTE = 380;
	public static final int PVMAX_INSECTE_DEMI = 200;
	// -- --
	public static final int VITESSE_ENNEMI_INSECTE3 = (CSG.HAUTEUR_ECRAN / 15);
	public static final int PVMAX_INSECTE3 = 520;
	public static final int PVMAX_INSECTE_DEMI3 = 300;
	
	// ***********************************************************************************
	// ***********************   A   R   M   E   S   *************************************
	// ***********************************************************************************
	
	// F R A G
	public static final int VITESSE_MAX_ARME_FRAG = CSG.HAUTEUR_ECRAN / 6;
	public static final int VITESSE_MAX_ARME_FRAGMENTEE = CSG.HAUTEUR_ECRAN / 6;
	// B A L A Y A G E   E N N E M I S
	public static final int VITESSE_MAX_ARME_BALAYAGE_ENNEMI_QUI_TOURNE = CSG.HAUTEUR_ECRAN / 11;
	// B A L A Y A G E   E N N E M I S  2
	public static final int VITESSE_MAX_ARME_BALAYAGE_ENNEMIS_TOUPIE = (int) (CSG.HAUTEUR_ECRAN/3.93f);
	// A R M E   B A L A Y A G E
	public static final int VITESSE_MAX_ARME_BALAYAGE = CSG.HAUTEUR_ECRAN;
	// A R M E   B O U L E   B L E U E
	public static final int VITESSE_MAX_ARME_BOULE_BLEU = CSG.HAUTEUR_ECRAN/8;
	// A R M E   B O U L E   V E R T E
	public static final int VITESSE_MAX_ARME_BOULE_VERTE = (int) (CSG.HAUTEUR_ECRAN/5.7f);
	public static final int VITESSE_MAX_ARME_BOULE_VERTE_RAPIDE = (int) (CSG.HAUTEUR_ECRAN/4.133f);
	public static final int VITESSE_MAX_ARME_BOULE_VERTE_ROTATION = (int) (CSG.HAUTEUR_ECRAN/6.333f);
	// A R M E   D E   B A S E
	public static final int VITESSE_MAX_ARME_DE_BASE = (int) (CSG.HAUTEUR_ECRAN/1.233f);
	// A R M E   K I N D E R
	public static final int VITESSE_MAX_ARME_KINDER = (int) (CSG.HAUTEUR_ECRAN/5.333f);
	// A R M E   B O S S   Q U A D 
	public static final int VITESSE_ARME_BOSS_QUAD = (int) (CSG.HAUTEUR_ECRAN/4.133f);
	// A R M E  A D D  
	public static final int VITESSE_MAX_ARME_ADD = (int) (CSG.HAUTEUR_ECRAN/1.333f);
	// A R M E   S H U R I K E N
	public static final int VITESSE_MAX_ARME_RAISIN = (int) (CSG.HAUTEUR_ECRAN/4.433f);
	// A R M E   A V I O N
	public static final int VITESSE_MAX_ARME_AVION = (int) (CSG.HAUTEUR_ECRAN/1.6f);
	// A R M E   H  A N T E E
	public static final int VITESSE_MAX_ARME_HANTEE = (int) (CSG.HAUTEUR_ECRAN/0.8f);
	// A R M E   E N N E M I   L A S E R
	public static final int VITESSE_MAX_ARME_LASER = (int) (CSG.HAUTEUR_ECRAN/6.8f);
	// A R M E   I N S E C T E
	public static final int VITESSE_ARME_INSECTE = (int) (CSG.HAUTEUR_ECRAN/4.8f);
}
