package vaisseaux.ennemis;

import com.badlogic.gdx.utils.Array;

public class Progression {

	public static int frequenceApparition = 2000;
	private static int niveau = 1;
	/// Nombre de secondes au bout des quelles la difficulté augmente
	private static final int PALIER = 1;
	//static List<TypesEnnemis> liste = new ArrayList<TypesEnnemis>(30);
	static Array<TypesEnnemis> liste = new Array<TypesEnnemis>(30);

	/**
	 * Retourne la frequence d'apparition et calcul en même temps le niveau qui augmente de 1 toutes les 10 secondes. 
	 * La frequence augmente de façon exp
	 * @param tempsEcoule
	 * @return
	 */
	public static long getFrequenceApparition(long tempsEcoule) {
		if(tempsEcoule / PALIER > niveau){
			niveau++;
			frequenceApparition -= (niveau*10);	
		}
		return frequenceApparition;
	}

	public static int getPoints() {
		return (int) (niveau * 1.5f);
	}

	public static Array<TypesEnnemis> getListeEnnemis() {
		liste.clear();
		int pointsDispos = niveau * 2;
		// tant qu'on a encore des points à dépenser
		while(pointsDispos > 0){
			// on parcourt les types d'ennemis possible en partant de 0, ils sont rangés du plus cher au moins cher
			for(TypesEnnemis type : TypesEnnemis.LISTE_ENNEMIS){
				// si on a assez de points pour payer l'ennemi on l'ajoute à la liste de ceux à invoquer
				while(type.COUT <= pointsDispos){
					// fait pas mal d'allocations visiblement
//					liste.add(type);
//					liste.add(type);
//					liste.add(type);
//					liste.add(type);
//					liste.add(type);
//					liste.add(type);
//					liste.add(type);
//					liste.add(type);
//					liste.add(type);
					liste.add(type);
					pointsDispos -= type.COUT;
				}
			}
		}
		return liste;
	}
}
