package objets.ennemis;

/**
 * Definit les differents ennemis et leurs couts
 * @author Julien
 */
public enum CoutsEnnemis {
	DeBase(1),
	EnnemiZigZag(3),
	EnnemiQuiTir(5),
	EnnemiBouleQuiSArrete(7),
	EnnemiPorteNef(50),
	EnnemiQuiTourne(11),
	EnnemiToupie(17),
	EnnemiCylon(22),
	EnnemiKinder(31),
	
	EnnemiBossQuad(100),
	EnnemiLaserCotePetitNv2(37), 	//  2
	EnnemiQuiTir2(41),
	EnnemiAvion(47),
	EnnemiPorteRaisin(59),
	
	EnnemiBossMine(200),
	ROCHER(50),
	
	BouleTirCoteRotationNv2(61), //  2
	KINDER2(63), 		//  2
	EnnemiLaser(67), 
	EnnemiLaserCoteNv2(73), 		//  2
	EnnemiAilesDeployee(10), // Lance par porte nef
	EnnemiInsecte(83),
	
	// NIVEAU 3
	
	EnnemiDeBaseNv3(2),
	EnnemiZigZagNv3(7),
	EnnemiDeBaseQuiTirNv3(9),
	EnnemiBouleQuiSArreteNv3(13),
	EnnemiQuiTourneNv3(17),
	EnnemiToupieNv3(23),
	EnnemiCylonNv3(29),
	EnnemiKinderNv3(38),
	EnnemiQuiTir2Nv3(48),
	EnnemiAvionNv3(55),
	EnnemiPorteRaisinNv3(69),
	EnnemiLaserNv3(78),
	EnnemiInsecteNv3(95);

	public final int COUT;
	
	public final static CoutsEnnemis[] LISTE_BOSS = {EnnemiBossMine, EnnemiBossQuad, EnnemiPorteNef};
	
	private CoutsEnnemis(int coutPoints) {		COUT = coutPoints;	}
}

