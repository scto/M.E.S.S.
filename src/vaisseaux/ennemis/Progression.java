package vaisseaux.ennemis;

import jeu.Endless;
import menu.CSG;
import vaisseaux.ennemis.particuliers.EnnemiBossMine;
import vaisseaux.ennemis.particuliers.EnnemiBossQuad;
import vaisseaux.ennemis.particuliers.EnnemiPorteNef;
import vaisseaux.ennemis.particuliers.Rocher;

/**
 * Les switchs sont degueux, voir comment faire autrement. 
 * Garder une reference de chaque type ? Mais ca encombre
 * Trouver comment appeler une methode de la classe en ayant le nom de la classe (for name ?)
 * @author julien
 *
 */
public class Progression {

	private static final int PALIER = 15, DUREE_GRACE = 20, DUREE_PHASE_NORMALE = 37, NV_DE_BASE = 2;
	private static int niveau = NV_DE_BASE;
	private static int pointsDispos = 0, alternerNbPoints = 0, nbAppels = 0, nbBoss = 0;
	private static EtatProgression etat = EtatProgression.Normal;
	public static final float frequenceApparition = 1;

	public static void invoqueEnnemis() {
		if (niveau < 10) // on monte rapidement de niveau au debut 
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
			switch (Endless.level) {
			case 1:				popBoss();				break;
			case 2:				popBoss2();				break;
			case 3:				popBoss3();				break;
			}
		}
	}
	
	private static void popNormal() {
		alternerNbPoints++;
		switch (alternerNbPoints) { // calcul des points. Se base sur le niveau. rien sur 3, c'est normal
		case 0:			pointsDispos = niveau * Endless.level / 10;			break;
		case 1:			pointsDispos = niveau * Endless.level / 6;			break;
		case 2:			pointsDispos = niveau * Endless.level / 3;			break;
		case 4:			pointsDispos = niveau * Endless.level / 2;			break;
		case 5:
			pointsDispos = 0;
			alternerNbPoints = 0;
			break;
		}
	
		switch (Endless.level) {
		case 1: 
			for (Invocable inv : Ennemis.LISTE_LV1) {
				if (pointsDispos >= inv.getXp()) {
					inv.invoquer();
					pointsDispos -= inv.getXp();
				}
			}
			break;
		case 2: 
			for (Invocable inv : Ennemis.LISTE_LV2) {
				if (pointsDispos >= inv.getXp()) {
					inv.invoquer();
					pointsDispos -= inv.getXp();
				}
			}
			break;
		case 3:
			for (Invocable inv : Ennemis.LISTE_LV3) {
				if (pointsDispos >= inv.getXp()) {
					inv.invoquer();
					pointsDispos -= inv.getXp();
				}
			}
			break;
		}
	}
	
	private static void popBoss() {
		if (Ennemis.liste.size > 0)
			return; // si y'a encore des ennemis en jeu

		switch (nbBoss) {
		case 0:
			Ennemis.liste.add(EnnemiPorteNef.pool.obtain());		// 1 porte nef
			break;
		case 1:														// 2 portes nefs
			Ennemis.liste.add(EnnemiPorteNef.pool.obtain());
			EnnemiPorteNef e = EnnemiPorteNef.pool.obtain();			// celui ci plus et plus tard
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
		if (Ennemis.liste.size > 1)		return; 

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
			Ennemis.liste.add(EnnemiBossMine.pool.obtain());
			break;
		}
		etat = EtatProgression.TempsDeGrace;
		nbAppels = 0;
		nbBoss++;
	}

	
	private static void popBoss3() {
		if (Ennemis.liste.size > 1)		return; 

		switch (nbBoss) {
		case 0:			Ennemis.liste.add(EnnemiBossQuad.pool.obtain());			break;
		default:		Ennemis.liste.add(EnnemiBossMine.pool.obtain());			break;
		}
		etat = EtatProgression.TempsDeGrace;
		nbAppels = 0;
		nbBoss++;
	}
	
	
	private static void grace() {
		if (nbAppels > DUREE_GRACE || Ennemis.liste.size == 0) {
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
