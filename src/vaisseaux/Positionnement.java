package vaisseaux;

import menu.CSG;

public class Positionnement {
	
	/**
	 * 
	 * @param demiLargeur
	 * @return
	 */
	public static float getEmplacementX(int demiLargeur) {
		return (float) (Math.random() * (CSG.LARGEUR_ZONE_JEU - (demiLargeur+demiLargeur)));
	}

	public static void setPosX(PatternHorizontalPositionnable php) {
		if (php.getNbEnnemisAvant() == 0) {
			php.setPosXInitiale(getEmplacementX(php.getDemiLargeur()));
			php.getPosition().x = php.getPosXInitiale();
		}
		else
			if (php.getPosXInitiale() + php.getDemiLargeur() > CSG.DEMI_LARGEUR_ZONE_JEU)	php.getPosition().x = php.getPosXInitiale() - php.getLargeur() * php.getNbEnnemisAvant();
			else															php.getPosition().x = php.getPosXInitiale() + php.getLargeur() * php.getNbEnnemisAvant();
		php.incNbEnnemisAvant();
	}

	public static float getEmplacementXVersMilieu(int largeur) {
		float x = (float) (Math.random() * (CSG.LARGEUR_ZONE_JEU - largeur));
		x = (x / 2) + (x / 4);
		return x;
	}
}
