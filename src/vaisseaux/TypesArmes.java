package vaisseaux;

/** défini les types d'armes, utilisée par la classe xp. (Pourquoi ?) et par la classe joueur
 * @author Julien
 */
public enum TypesArmes {
	ArmeDeBase(true),
	ArmeTriple(true),
	ArmeBalayage(true);
	
	public final boolean JOUEUR;
	
	private TypesArmes(boolean joueur) {
		JOUEUR = joueur;
	}
	
	public static final TypesArmes[] LISTE_ARME_JOUEUR = {ArmeDeBase, ArmeBalayage};

	public static TypesArmes changerArme(TypesArmes[] typeArmePossible, TypesArmes typeArme) {
		int indexCourant = typeArme.ordinal();
		if(++indexCourant < typeArmePossible.length){
			return typeArmePossible[indexCourant];
		}
		return typeArmePossible[0];
	}

	public static TypesArmes determinerArme(String arme) {
		if(arme.equals(ArmeDeBase.toString())) return ArmeDeBase;
		if(arme.equals(ArmeBalayage.toString())) return ArmeBalayage;
		return null;
	}
}
