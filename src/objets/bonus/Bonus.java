package objets.bonus;

import objets.joueur.VaisseauJoueur;
import jeu.Physique;
import menu.CSG;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

public abstract class Bonus {
	
public static Array<Bonus> liste = new Array<Bonus>(30);
	
	public float posX, posY;
	public static final int LARGEUR = CSG.LARGEUR_ECRAN / 16, DEMI_LARGEUR = LARGEUR/2;
	public static final float LARGEUR_COLLISION = LARGEUR * 3, DEMI_LARGEUR_COLLISION = LARGEUR_COLLISION/2;
	protected static final int DECALAGE_X_COLLISION = (int) (DEMI_LARGEUR - DEMI_LARGEUR_COLLISION);
	public static int collisionEnPlusAGauche = 0, collisionEnPlusDroite = 0;
	static int cptBonus = 0;
	protected static final float FACTEUR_AUGMENTATION = 1.35f;
	protected static final int frequenceTemps = 5, frequenceAdd = (frequenceTemps * 2)+1, frequenceBombe = (int) ((frequenceTemps * 5f)+13), frequenceStop = (frequenceTemps * 4)+6, frequenceBouclier = (int) ((frequenceTemps * 3.8f)+1);
	public static int nbBonusLacheAdd = 1, nbBonusLacheStop = 1, nbBonusLacheBombe = 1, nbBonusLacheTemps = 1, nbBonusLacheBouclier = 1;

	public static float AFFICHAGE = LARGEUR * 1.8f;
	
	/**
	 * Affiches tous les bonus, les fait tourner et test la collision avec le joueur
	 * @param batch
	 */
	public static void affichageEtMouvement(SpriteBatch batch) {
		for (Bonus b : liste) {
			b.afficherEtMvt(batch);
			if (Physique.pointDansRectangle(VaisseauJoueur.centreX, VaisseauJoueur.centreY, (b.posX + DECALAGE_X_COLLISION) - collisionEnPlusAGauche,
				b.posY - DEMI_LARGEUR_COLLISION, LARGEUR_COLLISION + collisionEnPlusDroite + collisionEnPlusAGauche, LARGEUR_COLLISION + DEMI_LARGEUR_COLLISION)){	
				b.prisEtFree();
				liste.removeValue(b, true);
			}
			if(Physique.toujoursAfficher(b.posX, b.posY, LARGEUR) == false)	liste.removeValue(b, true);
		}
	}

	/**
	 * Test la collision, bouge et affiche
	 * @param batch
	 * @return true si toujours affich�
	 */
	abstract boolean afficherEtMvt(SpriteBatch batch);

	public abstract void prisEtFree();
	
	public abstract void free();

	public static void resetTout() {
		for(Bonus b : liste) b.free();
        Bonus.liste.clear();
        cptBonus = 0;
        nbBonusLacheAdd = 1;
        nbBonusLacheStop = 1;
        nbBonusLacheBombe = 1;
        nbBonusLacheTemps = 1;
        nbBonusLacheBouclier = 1;
	}
	
	/**
	 * Se base sur le nombre de bonus temps d�j� lache et l'xp apport�e pour savoir si il doit apparaitre ou non
	 * @param x
	 * @param y
	 * @param xp
	 */
	public static void ajoutBonus(float x, float y, int xp) {
		cptBonus += xp;
		if (cptBonus > frequenceAdd * (nbBonusLacheAdd * nbBonusLacheAdd * FACTEUR_AUGMENTATION)){
			BonusAdd.pool.obtain().init(x, y);
			nbBonusLacheAdd++;
		}
		if (cptBonus > frequenceStop * (nbBonusLacheStop * nbBonusLacheStop * FACTEUR_AUGMENTATION)){
			BonusStop.pool.obtain().init(x, y);
			nbBonusLacheStop++;
		}
		if (cptBonus > frequenceTemps * (nbBonusLacheTemps * nbBonusLacheTemps * FACTEUR_AUGMENTATION)){
			BonusTemps.pool.obtain().init(x, y);
			nbBonusLacheTemps++;
		}
		if (cptBonus > frequenceBombe * (nbBonusLacheBombe * nbBonusLacheBombe * FACTEUR_AUGMENTATION)){
			BonusBombe.pool.obtain().init(x, y);
			nbBonusLacheBombe++;
		}
		if (cptBonus > frequenceBouclier * (nbBonusLacheBouclier * nbBonusLacheBouclier * FACTEUR_AUGMENTATION)){
			BonusBouclier.pool.obtain().init(x, y);
			nbBonusLacheBouclier++;
		}
		XP.pool.obtain().init(x, y, xp);
	}
}
