package vaisseaux;

public enum TypesArmes {
	DE_BASE,
	BALAYAGE,
	LASER,
	HANTEE;
	
	public final static TypesArmes[] typeArmePossible = {DE_BASE, BALAYAGE, LASER, HANTEE};
	
	public static TypesArmes changerArme(TypesArmes typeArme) {
		int indexCourant = typeArme.ordinal();
		if (++indexCourant < typeArmePossible.length){
			return typeArmePossible[indexCourant];
		}
		return typeArmePossible[0];
	}

	public static TypesArmes determinerArme(String arme) {
		if (arme.equals(DE_BASE.toString())) return DE_BASE;
		if (arme.equals(BALAYAGE.toString())) return BALAYAGE;
		if (arme.equals(LASER.toString())) return LASER;
		return HANTEE;
	}
}
