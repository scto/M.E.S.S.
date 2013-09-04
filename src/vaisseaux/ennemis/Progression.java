package vaisseaux.ennemis;

import jeu.Endless;
import menu.CSG;
import vaisseaux.ennemis.particuliers.Rocher;
import vaisseaux.ennemis.particuliers.boss.EnnemiBossMine;
import vaisseaux.ennemis.particuliers.boss.EnnemiBossQuad;
import vaisseaux.ennemis.particuliers.boss.EnnemiPorteNef;
import vaisseaux.ennemis.particuliers.nv1.Avion;
import vaisseaux.ennemis.particuliers.nv1.BouleQuiSArrete;
import vaisseaux.ennemis.particuliers.nv1.Cylon;
import vaisseaux.ennemis.particuliers.nv1.DeBase;
import vaisseaux.ennemis.particuliers.nv1.Insecte;
import vaisseaux.ennemis.particuliers.nv1.Kinder;
import vaisseaux.ennemis.particuliers.nv1.Laser;
import vaisseaux.ennemis.particuliers.nv1.PorteRaisin;
import vaisseaux.ennemis.particuliers.nv1.QuiTir;
import vaisseaux.ennemis.particuliers.nv1.QuiTir2;
import vaisseaux.ennemis.particuliers.nv1.QuiTourne;
import vaisseaux.ennemis.particuliers.nv1.Toupie;
import vaisseaux.ennemis.particuliers.nv1.ZigZag;
import vaisseaux.ennemis.particuliers.nv2.BouleTirCote;
import vaisseaux.ennemis.particuliers.nv2.BouleTirCoteRotation;
import vaisseaux.ennemis.particuliers.nv2.Kinder2;
import vaisseaux.ennemis.particuliers.nv3.AvionNv3;
import vaisseaux.ennemis.particuliers.nv3.EnnemiBouleQuiSArreteNv3;
import vaisseaux.ennemis.particuliers.nv3.EnnemiCylonNv3;
import vaisseaux.ennemis.particuliers.nv3.EnnemiDeBaseNv3;
import vaisseaux.ennemis.particuliers.nv3.EnnemiInsecteNv3;
import vaisseaux.ennemis.particuliers.nv3.EnnemiKinderNv3;
import vaisseaux.ennemis.particuliers.nv3.EnnemiPorteRaisinNv3;
import vaisseaux.ennemis.particuliers.nv3.EnnemiQuiTir2Nv3;
import vaisseaux.ennemis.particuliers.nv3.EnnemiQuiTourneNv3;
import vaisseaux.ennemis.particuliers.nv3.EnnemiToupieNv3;
import vaisseaux.ennemis.particuliers.nv3.QuiTirNv3;
import vaisseaux.ennemis.particuliers.nv3.ZigZagNv3;

public class Progression {

	private static final int PALIER = 15, DUREE_GRACE = 20, DUREE_PHASE_NORMALE = 37, NV_DE_BASE = 2;
	private static int niveau = NV_DE_BASE;
	private static int pointsDispos = 0, alternerNbPoints = 0, nbAppels = 0, nbBoss = 0;
	private static EtatProgression etat = EtatProgression.Normal;
	public static final float FREQ_APPARATION = 1;

	private Progression() {	}

	private final static Invocable[] LISTE_LV1 = {
		Insecte.pool.obtain(),
		Laser.pool.obtain(),
		PorteRaisin.pool.obtain(),
		Avion.pool.obtain(),
		QuiTir2.pool.obtain(),
		Kinder.pool.obtain(),
		Cylon.pool.obtain(),
		Toupie.pool.obtain(),
		QuiTourne.pool.obtain(),
		BouleQuiSArrete.pool.obtain(),
		QuiTir.pool.obtain(),
		ZigZag.pool.obtain(),
		DeBase.pool.obtain(),
		};
	private final static Invocable[] LISTE_LV2 = {
		Insecte.pool.obtain(),
		Kinder2.pool.obtain(),
		BouleTirCote.pool.obtain(),
		Laser.pool.obtain(),
		Kinder2.pool.obtain(),
		BouleTirCoteRotation.pool.obtain(),
		PorteRaisin.pool.obtain(),
		Avion.pool.obtain(),
		QuiTir2.pool.obtain(), 
		Kinder.pool.obtain(), 
		Cylon.pool.obtain(), 
		Toupie.pool.obtain(), 
		QuiTourne.pool.obtain(), 
		BouleQuiSArrete.pool.obtain(),
		QuiTir.pool.obtain(),
		ZigZag.pool.obtain(),
		DeBase.pool.obtain(),
		};
	private final static Invocable[] LISTE_LV3 = {
		EnnemiInsecteNv3.pool.obtain(),
		BouleTirCote.pool.obtain(), 
		EnnemiPorteRaisinNv3.pool.obtain(), 
		AvionNv3.pool.obtain(),
		EnnemiQuiTir2Nv3.pool.obtain(), 
		EnnemiKinderNv3.pool.obtain(), 
		EnnemiCylonNv3.pool.obtain(), 
		EnnemiToupieNv3.pool.obtain(), 
		EnnemiQuiTourneNv3.pool.obtain(), 
		EnnemiBouleQuiSArreteNv3.pool.obtain(),
		QuiTirNv3.pool.obtain(),
		ZigZagNv3.pool.obtain(),
		EnnemiDeBaseNv3.pool.obtain(),
		};
	
	public static void invoqueEnnemis() {
		// on monte rapidement de niveau au debut
		if (niveau < 10)  
			niveau += Endless.level;

		if (nbAppels++ > DUREE_PHASE_NORMALE) {
			etat = EtatProgression.Boss;
			nbAppels = 0;
		}
		
		if (Endless.score > PALIER * niveau)
			niveau++; 

		switch (etat) {
		case Normal:			popNormal();			break;
		case TempsDeGrace:		grace();				break;
		case Boss:
			if (Ennemis.LISTE.size > 0) return;
			switch (Endless.level) {
			case 1:				popBoss();				break;
			case 2:				popBoss2();				break;
			case 3:				popBoss3();				break;
			}
		}
	}
	
	private static void popNormal() {
		calculPoints();
	
		switch (Endless.level) {
		case 1: 
			for (Invocable inv : LISTE_LV1) {
				if (pointsDispos >= inv.getXp()) {
					inv.invoquer();
					pointsDispos -= inv.getXp();
				}
			}
			break;
		case 2: 
			for (Invocable inv : LISTE_LV2) {
				if (pointsDispos >= inv.getXp()) {
					inv.invoquer();
					pointsDispos -= inv.getXp();
				}
			}
			break;
		case 3:
			for (Invocable inv : LISTE_LV3) {
				if (pointsDispos >= inv.getXp()) {
					inv.invoquer();
					pointsDispos -= inv.getXp();
				}
			}
			break;
		}
	}
	
	private static void calculPoints() {
		// calcul des points. Se base sur le niveau. rien sur 3, c'est normal
		alternerNbPoints++;
		switch (alternerNbPoints) { 
		case 0:			pointsDispos = niveau * Endless.level / 10;			break;
		case 1:			pointsDispos = niveau * Endless.level / 6;			break;
		case 2:			pointsDispos = niveau * Endless.level / 3;			break;
		case 4:			pointsDispos = niveau * Endless.level / 2;			break;
		case 5:
			pointsDispos = 0;
			alternerNbPoints = 0;
			break;
		}
	}
	
	private static void popBoss() {
		switch (nbBoss) {
		case 0:
			Ennemis.LISTE.add(EnnemiPorteNef.pool.obtain());		// 1 porte nef
			break;
		case 1:														// 2 portes nefs
			Ennemis.LISTE.add(EnnemiPorteNef.pool.obtain());
			EnnemiPorteNef e = EnnemiPorteNef.pool.obtain();			// celui ci plus et plus tard
			e.position.x += EnnemiPorteNef.LARGEUR;
			e.position.y += EnnemiPorteNef.DEMI_LARGEUR;
			Ennemis.LISTE.add(e);
			break;
		case 2:
			Ennemis.LISTE.add(EnnemiBossQuad.pool.obtain());
			break;
		default:
			Ennemis.LISTE.add(EnnemiBossMine.pool.obtain());
			break;
		}
		etat = EtatProgression.TempsDeGrace;
		nbAppels = 0;
		nbBoss++;
	}

	private static void popBoss2() {
		switch (nbBoss) {
		case 0:
			Ennemis.LISTE.add(EnnemiPorteNef.pool.obtain());
			EnnemiPorteNef e = EnnemiPorteNef.pool.obtain();
			e.position.x += EnnemiPorteNef.LARGEUR;
			e.position.y += EnnemiPorteNef.DEMI_LARGEUR;
			Ennemis.LISTE.add(e);

			Rocher rocher = Rocher.pool.obtain();
			rocher.position.x = CSG.DEMI_LARGEUR_ECRAN;

			Rocher rocher2 = Rocher.pool.obtain();
			rocher2.position.x = CSG.LARGEUR_BORD;

			Rocher.pool.obtain();
			Rocher.pool.obtain();
			break;
		case 1:
			Ennemis.LISTE.add(EnnemiBossQuad.pool.obtain());
			Rocher.pool.obtain();
			Rocher.pool.obtain();
			break;
		default:
			Ennemis.LISTE.add(EnnemiBossMine.pool.obtain());
			break;
		}
		etat = EtatProgression.TempsDeGrace;
		nbAppels = 0;
		nbBoss++;
	}

	
	private static void popBoss3() {
		switch (nbBoss) {
		case 0:			Ennemis.LISTE.add(EnnemiBossQuad.pool.obtain());			break;
		default:		Ennemis.LISTE.add(EnnemiBossMine.pool.obtain());			break;
		}
		etat = EtatProgression.TempsDeGrace;
		nbAppels = 0;
		nbBoss++;
	}
	
	
	private static void grace() {
		if (nbAppels > DUREE_GRACE || Ennemis.LISTE.size == 0) {
			nbAppels = 0;
			etat = EtatProgression.Normal;
		}
	}
	
	
	public static void reset() {
		niveau = NV_DE_BASE;
		nbAppels = 0;
		etat = EtatProgression.Normal;
		nbBoss = 0;
	}

}
