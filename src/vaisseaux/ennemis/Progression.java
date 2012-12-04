package vaisseaux.ennemis;

import com.badlogic.gdx.utils.Array;

public class Progression {

	public static int frequenceApparition = 1000;
	private static int niveau = 1;
	/// Nombre de secondes au bout des quelles la difficulté augmente
	private static final int PALIER = 5;
	private static int pointsDispos = 0;
	static Array<TypesEnnemis> liste = new Array<TypesEnnemis>(30);

	public static int getPoints() {
		return (int) (niveau * 1.5f);
	}

	public static Array<TypesEnnemis> getListeEnnemis(long tempsEcoule) {
		if(tempsEcoule / PALIER > niveau)
			niveau++;
		
		liste.clear();
		//modeVague();
		modeBoss();
		return liste;
	}

	private static void modeBoss() {
		pointsDispos += niveau;
		// on parcourt les types d'ennemis possible en partant de 0, ils sont rangés du plus cher au moins cher
		for(TypesEnnemis type : TypesEnnemis.LISTE_ENNEMIS){
			// si on a assez de points pour payer l'ennemi on l'ajoute à la liste de ceux à invoquer
			while(type.COUT <= pointsDispos){
				liste.add(type);
				pointsDispos -= type.COUT;
			}
		}
	}

	@SuppressWarnings("unused")
	private static void modeVague() {
		int pointsDispos = niveau * 2;
		// tant qu'on a encore des points à dépenser
		while(pointsDispos > 0){
			// on parcourt les types d'ennemis possible en partant de 0, ils sont rangés du plus cher au moins cher
			for(TypesEnnemis type : TypesEnnemis.LISTE_ENNEMIS){
				// si on a assez de points pour payer l'ennemi on l'ajoute à la liste de ceux à invoquer
				while(type.COUT <= pointsDispos){
					liste.add(type);
					pointsDispos -= type.COUT;
				}
			}
		}
	}

	public static void reset() {
		frequenceApparition = 2000;
		niveau = 1;
		liste.clear();
	}
}
