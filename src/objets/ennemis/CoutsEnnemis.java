package objets.ennemis;

/**
 * Definit les differents ennemis et leurs couts
 * @author Julien
 */
public enum CoutsEnnemis {
	DeBase(1),
	EnnemiZigZag(3),
	EnnemiQuiTir(5),
	BOULE(7),
	EnnemiPorteNef(50),
	EnnemiQuiTourne(11),
	EnnemiToupie(17),
	CYLON(22),
	EnnemiKinder(31),
	
	EnnemiBossQuad(100),
	EnnemiLaserCotePetitNv2(37), 	//  2
	EnnemiQuiTir2(41),
	EnnemiAvion(47),
	PORTE_RAISIN(59),
	
	EnnemiBossMine(200),
	ROCHER(50),
	
	BOULE_TIR_COTE_ROTATION_NV2(61), //  2
	KINDER2(63), 		//  2
	LASER(67), 
	EnnemiLaserCoteNv2(73), 		//  2
	EnnemiAilesDeployee(10), // Lance par porte nef
	INSECTE(83),
	
	// NIVEAU 3
	
	EnnemiDeBaseNv3(2),
	ZIG_ZAG3(7),
	DE_BASE_QUI_TIR_3(9),
	EnnemiBouleQuiSArreteNv3(13),
	EnnemiQuiTourneNv3(17),
	TOUPIE3(23),
	CYLON3(29),
	KINDER3(38),
	QUI_TIR2_NV3(48),
	AVION3(55),
	PORTE_RAISIN_3(69),
	LASER3(78),
	INSECTE3(95);

	public final int COUT;
	
	public final static CoutsEnnemis[] LISTE_BOSS = {EnnemiBossMine, EnnemiBossQuad, EnnemiPorteNef};
	
	private CoutsEnnemis(int coutPoints) {		COUT = coutPoints;	}
}

