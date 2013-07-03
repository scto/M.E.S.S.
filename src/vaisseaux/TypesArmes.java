package vaisseaux;

/** défini les types d'armes, utilisée par la classe xp. (Pourquoi ?) et par la classe joueur
 * @author Julien
 */
public enum TypesArmes {
	ArmeDeBase,
	ArmeBalayage,
	ArmeTrois,
	ArmeHantee;
	
	
	public static TypesArmes[] typeArmePossible = {ArmeDeBase, ArmeBalayage, ArmeTrois, ArmeHantee};
	
	public static TypesArmes changerArme(TypesArmes typeArme) {
		int indexCourant = typeArme.ordinal();
		if(++indexCourant < typeArmePossible.length){
			return typeArmePossible[indexCourant];
		}
		return typeArmePossible[0];
	}

	public static TypesArmes determinerArme(String arme) {
		if (arme.equals(ArmeDeBase.toString())) return ArmeDeBase;
		if (arme.equals(ArmeBalayage.toString())) return ArmeBalayage;
		if (arme.equals(ArmeTrois.toString())) return ArmeTrois;
		return ArmeHantee;
	}
}
