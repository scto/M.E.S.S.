package vaisseaux.ennemis;

import jeu.Endless;
import menu.CSG;
import vaisseaux.ennemis.particuliers.EnnemiBossMine;
import vaisseaux.ennemis.particuliers.EnnemiBossQuad;
import vaisseaux.ennemis.particuliers.EnnemiPorteNef;
import vaisseaux.ennemis.particuliers.Rocher;
import vaisseaux.ennemis.particuliers.nv1.EnnemiAvion;
import vaisseaux.ennemis.particuliers.nv1.EnnemiBouleQuiSArrete;
import vaisseaux.ennemis.particuliers.nv1.EnnemiCylon;
import vaisseaux.ennemis.particuliers.nv1.EnnemiDeBase;
import vaisseaux.ennemis.particuliers.nv1.EnnemiInsecte;
import vaisseaux.ennemis.particuliers.nv1.EnnemiKinder;
import vaisseaux.ennemis.particuliers.nv1.EnnemiLaser;
import vaisseaux.ennemis.particuliers.nv1.EnnemiPorteRaisin;
import vaisseaux.ennemis.particuliers.nv1.EnnemiQuiTir;
import vaisseaux.ennemis.particuliers.nv1.EnnemiQuiTir2;
import vaisseaux.ennemis.particuliers.nv1.EnnemiQuiTourne;
import vaisseaux.ennemis.particuliers.nv1.EnnemiToupie;
import vaisseaux.ennemis.particuliers.nv1.EnnemiZigZag;
import vaisseaux.ennemis.particuliers.nv2.EnnemiKinderDoubleTir;
import vaisseaux.ennemis.particuliers.nv2.EnnemiLaserCote;
import vaisseaux.ennemis.particuliers.nv2.EnnemiLaserCotePetitEtRapide;
import vaisseaux.ennemis.particuliers.nv2.EnnemiLaserCoteRotation;
import vaisseaux.ennemis.particuliers.nv3.EnnemiAvionNv3;
import vaisseaux.ennemis.particuliers.nv3.EnnemiBouleQuiSArreteNv3;
import vaisseaux.ennemis.particuliers.nv3.EnnemiCylonNv3;
import vaisseaux.ennemis.particuliers.nv3.EnnemiDeBaseNv3;
import vaisseaux.ennemis.particuliers.nv3.EnnemiInsecteNv3;
import vaisseaux.ennemis.particuliers.nv3.EnnemiKinderNv3;
import vaisseaux.ennemis.particuliers.nv3.EnnemiLaserNv3;
import vaisseaux.ennemis.particuliers.nv3.EnnemiPorteRaisinNv3;
import vaisseaux.ennemis.particuliers.nv3.EnnemiQuiTir2Nv3;
import vaisseaux.ennemis.particuliers.nv3.EnnemiQuiTirNv3;
import vaisseaux.ennemis.particuliers.nv3.EnnemiQuiTourneNv3;
import vaisseaux.ennemis.particuliers.nv3.EnnemiToupieNv3;
import vaisseaux.ennemis.particuliers.nv3.EnnemiZigZagNv3;

/**
 * Les switchs sont degueux, voir comment faire autrement. 
 * Garder une reference de chaque type ? Mais ca encombre
 * Trouver comment appeler une methode de la classe en ayant le nom de la classe (for name ?)
 * @author julien
 *
 */
public class Progression {

	private static final int NV_DE_BASE = 2;
	private static int niveau = NV_DE_BASE;
	private static final int PALIER = 15;
	private static int pointsDispos = 0;
	private static int alternerNbPoints = 0;
	public static final float frequenceApparition = 1;
	public static int nbAppels = 0;
	public static EtatProgression etat = EtatProgression.Normal;
	public static int nbBoss = 0;

	public static void reset() {
		niveau = NV_DE_BASE;
		nbAppels = 0;
		etat = EtatProgression.Normal;
		nbBoss = 0;
	}
	
	public static void listeEnnemisNv1() {
		nbAppels++; // Sert � compter si on passe en mode boss ou pas

		if (nbAppels > 25) {
			etat = EtatProgression.Boss;
			nbAppels = 0;
		}

		if (Endless.score > PALIER * niveau)
			niveau++; 

		switch (etat) {
		case Normal:			popNormal();			break;
		case Boss:				popBoss();				break;
		case TempsDeGrace:		grace();				break;
		}
	}

	public static void listeEnnemisNv2() {
		nbAppels++; // Sert a compter si on passe en mode boss ou pas

		if (nbAppels > 23) {
			etat = EtatProgression.Boss;
			nbAppels = 0;
		}
		if (niveau < 20) // on monte rapidement de niveau au debut 
			niveau += 3;

		if (Endless.score > PALIER * niveau)
			niveau++; 

		switch (etat) {
		case Normal:			popNormal2();		break;
		case Boss:				popBoss2();			break;
		case TempsDeGrace:		grace();			break;
		}
	}
	
	public static void listeEnnemisNv3() {
		nbAppels++; // Sert a compter si on passe en mode boss ou pas

		if (nbAppels > 40) {
			etat = EtatProgression.Boss;
			nbAppels = 0;
		}
		if (niveau < 10) // on monte rapidement de niveau au debut 
			niveau += 3;

		if (Endless.score > PALIER * niveau)
			niveau++; 

		switch (etat) {
		case Normal:			popNormal3();		break;
		case Boss:				popBoss3();			break;
		case TempsDeGrace:		grace();			break;
		}
	}
	
	private static void popNormal() {
		switch (alternerNbPoints) { // calcul des points. Se base sur le niveau
		case 0:
			pointsDispos = niveau / 8;
			alternerNbPoints++;
			break;
		case 1:
			pointsDispos = niveau / 5;
			alternerNbPoints++;
			break;
		case 2:
			pointsDispos = niveau / 3;
			alternerNbPoints++;
			break;
		case 3:
			pointsDispos = niveau;
			alternerNbPoints = 0;
			break;
		}
		pointsDispos++;

		for (Invocable inv : Ennemis.LISTE_LV1) {
			if (pointsDispos >= inv.getXp()) {
				inv.invoquer();
				pointsDispos -= inv.getXp();
			}
		}
	}
	
	private static void popNormal2() {
		switch (alternerNbPoints) { // calcul des points. Se base sur le niveau
		case 0:
			pointsDispos = niveau / 5;
			alternerNbPoints++;
			break;
		case 1:
			pointsDispos = niveau / 3;
			alternerNbPoints++;
			break;
		case 2:
			pointsDispos = niveau;
			alternerNbPoints++;
			break;
		case 3:
			pointsDispos = (int) (niveau * 1.3f);
			alternerNbPoints = 0;
			break;
		}
		pointsDispos++;

//		try {
//			Class.forName("EnnemiDeBase");
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
		for (TypesEnnemis type : TypesEnnemis.LISTE_ENNEMIS_NV2) {
			if (pointsDispos >= type.COUT) {
				if (type == TypesEnnemis.EnnemiDeBase) {
					Ennemis.liste.add(EnnemiDeBase.pool.obtain());
					Ennemis.liste.add(EnnemiDeBase.pool.obtain());
					Ennemis.liste.add(EnnemiDeBase.pool.obtain());
					Ennemis.liste.add(EnnemiDeBase.pool.obtain());
				}
				ajoutNv2(type);
				pointsDispos -= type.COUT;
			}
		}
	}
	
	private static void ajoutNv2(TypesEnnemis type) {
		switch (type) {
			case EnnemiLaser:				Ennemis.liste.add(EnnemiLaser.pool.obtain());					break;
			case ROCHER:					Ennemis.liste.add(Rocher.pool.obtain());						break;
			case EnnemiBossMine:			Ennemis.liste.add(EnnemiBossMine.pool.obtain());				break;
			case EnnemiQuiTir2:				Ennemis.liste.add(EnnemiQuiTir2.pool.obtain());					break;
			case EnnemiAvion:				Ennemis.liste.add(EnnemiAvion.pool.obtain());					break;
			case EnnemiPorteRaisin:			Ennemis.liste.add(EnnemiPorteRaisin.pool.obtain());				break;
			case EnnemiBossQuad:			Ennemis.liste.add(EnnemiBossQuad.pool.obtain());				break;
			case EnnemiKinder:				Ennemis.liste.add(EnnemiKinder.pool.obtain());					break;
			case EnnemiCylon:				Ennemis.liste.add(EnnemiCylon.pool.obtain());					break;
			case EnnemiToupie:				Ennemis.liste.add(EnnemiToupie.pool.obtain());					break;
			case EnnemiQuiTourne:			Ennemis.liste.add(EnnemiQuiTourne.pool.obtain());				break;
			case EnnemiPorteNef:			Ennemis.liste.add(EnnemiPorteNef.pool.obtain());				break;
			case EnnemiBouleQuiSArrete:		Ennemis.liste.add(EnnemiBouleQuiSArrete.pool.obtain());			break;
			case EnnemiQuiTir:		Ennemis.liste.add(EnnemiQuiTir.pool.obtain());					break;
			case EnnemiZigZag:				Ennemis.liste.add(EnnemiZigZag.pool.obtain());					break;
			case EnnemiKinderDoubleNv2:		Ennemis.liste.add(EnnemiKinderDoubleTir.pool.obtain());			break;
			case EnnemiInsecte:				Ennemis.liste.add(EnnemiInsecte.pool.obtain());					break;
			case EnnemiLaserCoteRotationNv2:Ennemis.liste.add(EnnemiLaserCoteRotation.pool.obtain());		break;
			case EnnemiLaserCoteNv2:		Ennemis.liste.add(EnnemiLaserCote.pool.obtain());				break;
			case EnnemiLaserCotePetitNv2:	Ennemis.liste.add(EnnemiLaserCotePetitEtRapide.pool.obtain());	break;
			case EnnemiDeBase:				
				Ennemis.liste.add(EnnemiDeBase.pool.obtain());
				Ennemis.liste.add(EnnemiDeBase.pool.obtain());
				Ennemis.liste.add(EnnemiDeBase.pool.obtain());
				Ennemis.liste.add(EnnemiDeBase.pool.obtain());
				Ennemis.liste.add(EnnemiDeBase.pool.obtain());
				break;
		}
	}

	private static void popNormal3() {
		switch (alternerNbPoints) { // calcul des points. Se base sur le niveau
		case 0:
			pointsDispos = niveau / 4;
			alternerNbPoints++;
			break;
		case 1:
			pointsDispos = niveau / 2;
			alternerNbPoints++;
			break;
		case 2:
			pointsDispos = (int) (niveau * 1.3f);
			alternerNbPoints++;
			break;
		case 3:
			pointsDispos = niveau * 2;
			alternerNbPoints = 0;
			break;
		}
		pointsDispos++;

		for (TypesEnnemis type : TypesEnnemis.LISTE_ENNEMIS_NV3) {
			if (pointsDispos >= type.COUT) {
				ajoutNv3(type);
//				EnnemiDeBase.class.
				
				pointsDispos -= type.COUT;
			}
		}
	}
	
	private static void ajoutNv3(TypesEnnemis type) {
		switch(type) {
			case EnnemiBouleQuiSArreteNv3:	Ennemis.liste.add(EnnemiBouleQuiSArreteNv3.pool.obtain());		break;
			case EnnemiZigZagNv3:			Ennemis.liste.add(EnnemiZigZagNv3.pool.obtain());				break;
			case EnnemiDeBaseQuiTirNv3:		Ennemis.liste.add(EnnemiQuiTirNv3.pool.obtain());				break;
			case EnnemiQuiTourneNv3:		Ennemis.liste.add(EnnemiQuiTourneNv3.pool.obtain());			break;
			case EnnemiToupieNv3:			Ennemis.liste.add(EnnemiToupieNv3.pool.obtain());				break;
			case EnnemiCylonNv3:			Ennemis.liste.add(EnnemiCylonNv3.pool.obtain());				break;
			case EnnemiKinderNv3:			Ennemis.liste.add(EnnemiKinderNv3.pool.obtain());				break;
			case EnnemiQuiTir2Nv3:			Ennemis.liste.add(EnnemiQuiTir2Nv3.pool.obtain());				break;
			case EnnemiAvionNv3:			Ennemis.liste.add(EnnemiAvionNv3.pool.obtain());				break;
			case EnnemiPorteRaisinNv3:		Ennemis.liste.add(EnnemiPorteRaisinNv3.pool.obtain());			break;
			case EnnemiLaserNv3:			Ennemis.liste.add(EnnemiLaserNv3.pool.obtain());				break;
			case EnnemiInsecteNv3:			Ennemis.liste.add(EnnemiInsecteNv3.pool.obtain());				break;
			case EnnemiDeBaseNv3:			
				Ennemis.liste.add(EnnemiDeBaseNv3.pool.obtain());
				Ennemis.liste.add(EnnemiDeBaseNv3.pool.obtain());
				Ennemis.liste.add(EnnemiDeBaseNv3.pool.obtain());
				Ennemis.liste.add(EnnemiDeBaseNv3.pool.obtain());
				Ennemis.liste.add(EnnemiDeBaseNv3.pool.obtain());
				Ennemis.liste.add(EnnemiDeBaseNv3.pool.obtain());
				break;
		}
	}

	private static void popBoss() {
		if (Ennemis.liste.size > 0)
			return; // si y'a encore des ennemis en jeu

		switch (nbBoss) {
		case 0:
			Ennemis.liste.add(EnnemiPorteNef.pool.obtain());
			break;
		case 1:
			Ennemis.liste.add(EnnemiPorteNef.pool.obtain());
			EnnemiPorteNef e = EnnemiPorteNef.pool.obtain();
			e.position.x += EnnemiPorteNef.LARGEUR;
			e.position.y += EnnemiPorteNef.DEMI_LARGEUR;
			Ennemis.liste.add(e);
			break;
		case 2:
			Ennemis.liste.add(EnnemiBossQuad.pool.obtain());
			break;
		default:
			Ennemis.liste.add(EnnemiBossMine.pool.obtain());
			break;
		}
		etat = EtatProgression.TempsDeGrace;
		nbAppels = 0;
		nbBoss++;
	}


	private static void popBoss2() {
		if (Ennemis.liste.size > 0)		return; 

		switch (nbBoss) {
		case 0:
			Ennemis.liste.add(EnnemiPorteNef.pool.obtain());
			EnnemiPorteNef e = EnnemiPorteNef.pool.obtain();
			e.position.x += EnnemiPorteNef.LARGEUR;
			e.position.y += EnnemiPorteNef.DEMI_LARGEUR;
			Ennemis.liste.add(e);

			Rocher rocher = Rocher.pool.obtain();
			rocher.position.x = CSG.DEMI_LARGEUR_ECRAN;

			Rocher rocher2 = Rocher.pool.obtain();
			rocher2.position.x = CSG.LARGEUR_BORD;

			Rocher.pool.obtain();
			Rocher.pool.obtain();
			break;
		case 1:
			Ennemis.liste.add(EnnemiBossQuad.pool.obtain());
			Rocher.pool.obtain();
			Rocher.pool.obtain();
			break;
		default:
			Ennemis.liste.add(EnnemiBossQuad.pool.obtain());
			Rocher.pool.obtain();
			Rocher.pool.obtain();
			Rocher.pool.obtain();
			Rocher.pool.obtain();
			break;
		}
		etat = EtatProgression.TempsDeGrace;
		nbAppels = 0;
		nbBoss++;
	}

	private static void popBoss3() {
		if (Ennemis.liste.size > 0)		return; 

		switch (nbBoss) {
		case 0:			Ennemis.liste.add(EnnemiBossQuad.pool.obtain());			break;
		default:		Ennemis.liste.add(EnnemiBossMine.pool.obtain());			break;
		}
		etat = EtatProgression.TempsDeGrace;
		nbAppels = 0;
		nbBoss++;
	}
	
//	private static Pools[] test;
	
//	private static void ajout(EnnemisPool ennemis) { //TypesEnnemis type) {
//		Ennemis.liste.add(ennemis.obtain());
//		switch (type) {
//		case EnnemiLaser:				Ennemis.liste.add((Ennemis) test[0].obtain());					break;
//		case ROCHER:					Ennemis.liste.add(Rocher.pool.obtain());						break;
//		case EnnemiBossMine:			Ennemis.liste.add(EnnemiBossMine.pool.obtain());				break;
//		case EnnemiQuiTir2:				Ennemis.liste.add(EnnemiQuiTir2.pool.obtain());					break;
//		case EnnemiAvion:				Ennemis.liste.add(EnnemiAvion.pool.obtain());					break;
//		case EnnemiPorteRaisin:			Ennemis.liste.add(EnnemiPorteRaisin.pool.obtain());				break;
//		case EnnemiBossQuad:			Ennemis.liste.add(EnnemiBossQuad.pool.obtain());				break;
//		case EnnemiKinder:				Ennemis.liste.add(EnnemiKinder.pool.obtain());					break;
//		case EnnemiCylon:				Ennemis.liste.add(EnnemiCylon.pool.obtain());					break;
//		case EnnemiToupie:				Ennemis.liste.add(EnnemiToupie.pool.obtain());					break;
//		case EnnemiQuiTourne:			Ennemis.liste.add(EnnemiQuiTourne.pool.obtain());				break;
//		case EnnemiPorteNef:			Ennemis.liste.add(EnnemiPorteNef.pool.obtain());				break;
//		case EnnemiBouleQuiSArrete:		Ennemis.liste.add(EnnemiBouleQuiSArrete.pool.obtain());			break;
//		case EnnemiDeBaseQuiTir:		Ennemis.liste.add(EnnemiQuiTir.pool.obtain());					break;
//		case EnnemiZigZag:				Ennemis.liste.add(EnnemiZigZag.pool.obtain());					break;
//		case EnnemiDeBase:				Ennemis.liste.add(EnnemiDeBase.pool.obtain());					break;
//		case EnnemiKinderDoubleNv2:		Ennemis.liste.add(EnnemiKinderDoubleTir.pool.obtain());			break;
//		case EnnemiInsecte:				Ennemis.liste.add(EnnemiInsecte.pool.obtain());					break;
//		case EnnemiLaserCoteRotationNv2:Ennemis.liste.add(EnnemiLaserCoteRotation.pool.obtain());		break;
//		case EnnemiLaserCoteNv2:		Ennemis.liste.add(EnnemiLaserCote.pool.obtain());				break;
//		case EnnemiLaserCotePetitNv2:	Ennemis.liste.add(EnnemiLaserCotePetitEtRapide.pool.obtain());	break;
//		case EnnemiBouleQuiSArreteNv3:	Ennemis.liste.add(EnnemiBouleQuiSArreteNv3.pool.obtain());		break;
//		case EnnemiZigZagNv3:			Ennemis.liste.add(EnnemiZigZagNv3.pool.obtain());				break;
//		case EnnemiDeBaseQuiTirNv3:		Ennemis.liste.add(EnnemiQuiTirNv3.pool.obtain());				break;
//		case EnnemiQuiTourneNv3:		Ennemis.liste.add(EnnemiQuiTourneNv3.pool.obtain());			break;
//		case EnnemiToupieNv3:			Ennemis.liste.add(EnnemiToupieNv3.pool.obtain());				break;
//		case EnnemiCylonNv3:			Ennemis.liste.add(EnnemiCylonNv3.pool.obtain());				break;
//		case EnnemiKinderNv3:			Ennemis.liste.add(EnnemiKinderNv3.pool.obtain());				break;
//		case EnnemiQuiTir2Nv3:			Ennemis.liste.add(EnnemiQuiTir2Nv3.pool.obtain());				break;
//		case EnnemiAvionNv3:			Ennemis.liste.add(EnnemiAvionNv3.pool.obtain());				break;
//		case EnnemiPorteRaisinNv3:		Ennemis.liste.add(EnnemiPorteRaisinNv3.pool.obtain());			break;
//		case EnnemiLaserNv3:			Ennemis.liste.add(EnnemiLaserNv3.pool.obtain());				break;
//		case EnnemiDeBaseNv3:			
//			Ennemis.liste.add(EnnemiDeBaseNv3.pool.obtain());
//			Ennemis.liste.add(EnnemiDeBaseNv3.pool.obtain());
//			Ennemis.liste.add(EnnemiDeBaseNv3.pool.obtain());
//			Ennemis.liste.add(EnnemiDeBaseNv3.pool.obtain());
//			Ennemis.liste.add(EnnemiDeBaseNv3.pool.obtain());
//			Ennemis.liste.add(EnnemiDeBaseNv3.pool.obtain());
//			break;
//		}
//	}

	private static void grace() {
		if (nbAppels > 15 || Ennemis.liste.size == 0) {
			nbAppels = 0;
			etat = EtatProgression.Normal;
		}
	}

	



}
