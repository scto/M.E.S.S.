package vaisseaux;

import com.badlogic.gdx.math.Vector2;

import vaisseaux.ennemis.Ennemis;
import menu.CSG;

public class Positionnement {
	
	/**
	 * 
	 * @param demiLargeur
	 * @return
	 */
	public static float getEmplacementX(Ennemis e) {
		return (float) (Math.random() * (CSG.LARGEUR_ZONE_JEU - e.getLargeur()));
	}

	public static void setPosX(PatternHorizontalPositionnable php) {
		if (php.getNbEnnemisAvant() == 0) {
			php.setPosXInitiale((float) (Math.random() * (CSG.LARGEUR_ZONE_JEU - php.getLargeur())));
			php.getPosition().x = php.getPosXInitiale();
		}
		else
			if (php.getPosXInitiale() + php.getDemiLargeur() > CSG.DEMI_LARGEUR_ZONE_JEU)	php.getPosition().x = php.getPosXInitiale() - php.getLargeur() * php.getNbEnnemisAvant();
			else															php.getPosition().x = php.getPosXInitiale() + php.getLargeur() * php.getNbEnnemisAvant();
		php.incNbEnnemisAvant();
		php.getPosition().y = CSG.HAUTEUR_ECRAN + php.getHauteur();
	}

	public static float getEmplacementXVersMilieu(int largeur) {
		float x = (float) (Math.random() * (CSG.LARGEUR_ZONE_JEU - largeur));
		x = (x / 2) + (x / 4);
		return x;
	}
	
	public static void hautLarge(Vector2 position, int largeur, int hauteur) {
		position.x = (float) (Math.random() * (CSG.LARGEUR_ZONE_JEU - largeur));
		position.y = CSG.HAUTEUR_ECRAN + hauteur;
	}
	
	public static void hautMoyen(Ennemis e) {
		float x = (float) (Math.random() * (CSG.LARGEUR_ZONE_JEU - e.getLargeur()));
		e.position.x = (x / 2) + (x / 4);
		e.position.y = CSG.HAUTEUR_ECRAN + e.getHauteur();
	}
	
	/**
	 * Ca positionne genre ennemi insecte
	 * @param e
	 * @param vitesse
	 * @param direction
	 */
	public static void coteVersInterieur(Vector2 position, float vitesse, Vector2 direction, int largeur) {
		position.y = CSG.HAUTEUR_ECRAN_PALLIER_3;
		if (Math.random() > .5) {
			direction.x = -vitesse;
			position.x = CSG.LARGEUR_ZONE_JEU;
		} else {
			direction.x = vitesse;
			position.x = -largeur;
		}
		direction.y = 0;
	}
	
	/**
	 * return true si a gauche
	 * @param e
	 * @param vitesse
	 * @param direction
	 * @return
	 */
	public static boolean coteVersInterieurKinder(Ennemis e, float vitesse, Vector2 direction) {
		double rand = Math.random();
		if (rand > 0.5){ // on est a gauche
			e.position.x = -e.getLargeur();
			e.position.y = (float) (CSG.HAUTEUR_ECRAN - (rand * CSG.DEMI_HAUTEUR_ECRAN));
			direction.x = 0;
			direction.y = -1.1f * vitesse;
			direction.rotate((float) ((( (rand + rand) - 1) * 55) + 55) );
			return false;
		} else {
			e.position.x = CSG.LARGEUR_ZONE_JEU;
			e.position.y = (float) (CSG.HAUTEUR_ECRAN - ((rand+rand) * CSG.DEMI_HAUTEUR_ECRAN));
			direction.x = 0;
			direction.y = -vitesse;
			direction.rotate((float) -((( (rand + rand)) * 90) + 45) );
			return true;
		}
	}

	public static void milieu(Ennemis e) {
		e.position.x = CSG.DEMI_LARGEUR_ZONE_JEU - e.getDemiLargeur();
		e.position.y = CSG.HAUTEUR_ECRAN;
	}
}
