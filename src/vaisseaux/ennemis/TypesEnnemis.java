package vaisseaux.ennemis;

import vaisseaux.ennemis.particuliers.EnnemiDeBase;

/**
 * Definit les differents ennemis et leurs couts
 * @author Julien
 */
public enum TypesEnnemis {
	EnnemiDeBase(1),
	EnnemiZigZag(3),
	EnnemiDeBaseQuiTir(5),
	EnnemiBouleQuiSArrete(7),
	EnnemiPorteNef(50),
	EnnemiQuiTourne(11),
	EnnemiToupie(17),
	EnnemiCylon(23),
	EnnemiKinder(31),
	
	EnnemiBossQuad(100),
	EnnemiLaserCotePetitNv2(37), 	//  2
	EnnemiQuiTir2(41),
	EnnemiAvion(47),
	EnnemiPorteRaisin(59),
	
	EnnemiBossMine(200),
	ROCHER(50),
	
	EnnemiLaserCoteRotationNv2(61), //  2
	EnnemiKinderDoubleNv2(63), 		//  2
	EnnemiLaser(67), 
	EnnemiLaserCoteNv2(73), 		//  2
	EnnemiAilesDeployee(10), // Lance par porte nef
	EnnemiInsecte(83),
	
	// NIVEAU 3
	
	EnnemiDeBaseNv3(2),
	EnnemiZigZagNv3(5),
	EnnemiDeBaseQuiTirNv3(7),
	EnnemiBouleQuiSArreteNv3(10),
	EnnemiQuiTourneNv3(14),
	EnnemiToupieNv3(20),
	EnnemiCylonNv3(26),
	EnnemiKinderNv3(34),
	EnnemiQuiTir2Nv3(45),
	EnnemiAvionNv3(51),
	EnnemiPorteRaisinNv3(63),
	EnnemiLaserNv3(71),
	EnnemiInsecteNv3(87);

	public final int COUT;
	
	public final static TypesEnnemis[] LISTE_ENNEMIS_NV1 = {EnnemiInsecte, EnnemiLaser, EnnemiPorteRaisin, EnnemiAvion, EnnemiQuiTir2, EnnemiKinder, EnnemiCylon,
		EnnemiToupie, EnnemiQuiTourne, EnnemiBouleQuiSArrete, EnnemiDeBaseQuiTir, EnnemiZigZag, EnnemiDeBase};
	
	public final static TypesEnnemis[] LISTE_ENNEMIS_NV2 = {EnnemiInsecte, EnnemiLaserCoteNv2, EnnemiLaser, EnnemiKinderDoubleNv2, EnnemiLaserCoteRotationNv2, EnnemiPorteRaisin,
		EnnemiAvion, EnnemiQuiTir2, EnnemiLaserCotePetitNv2, EnnemiKinder, EnnemiCylon, EnnemiToupie, EnnemiQuiTourne, EnnemiBouleQuiSArrete, EnnemiDeBaseQuiTir, EnnemiZigZag,
		EnnemiDeBase};
	
	public final static TypesEnnemis[] LISTE_ENNEMIS_NV3 = {EnnemiInsecteNv3, EnnemiLaserNv3, EnnemiPorteRaisinNv3, EnnemiAvionNv3, EnnemiQuiTir2Nv3, EnnemiKinderNv3, EnnemiCylonNv3, EnnemiToupieNv3, EnnemiQuiTourneNv3, EnnemiBouleQuiSArreteNv3,
		EnnemiDeBaseQuiTirNv3, EnnemiZigZagNv3, EnnemiDeBaseNv3};
	
	public final static TypesEnnemis[] LISTE_BOSS = {EnnemiBossMine, EnnemiBossQuad, EnnemiPorteNef};
	
	private TypesEnnemis(int coutPoints) {
		COUT = coutPoints;
	}
}

