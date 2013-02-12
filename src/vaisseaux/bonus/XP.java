package vaisseaux.bonus;

import java.util.ArrayList;
import java.util.List;

import menu.CSG;
import affichage.TexMan;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class XP extends Bonus{
	public static final int LARGEUR = CSG.LARGEUR_ECRAN / 30;
	public static final int HAUTEUR = LARGEUR;
	private static final int DEMI_LARGEUR = LARGEUR/2;
	private static final int DEMI_HAUTEUR = HAUTEUR/2;
	public static final float HAUTEUR_COLLISION = HAUTEUR * 6;
	public static final float LARGEUR_COLLISION = LARGEUR * 6;
	public static final float DEMI_LARGEUR_COLLISION = LARGEUR_COLLISION/2;
	public static final float DEMI_HAUTEUR_COLLISION = HAUTEUR_COLLISION/2;
	private static final float VITESSE_ROTATION = 60f;
	private float angleRotation = 0;
	public int valeur;
	// voir à quelle taille l'initialiser
	public static List<XP> liste = new ArrayList<XP>(30);
	
	/**
	 * Ajoute automatiquement l'item à la liste
	 * @param x
	 * @param y
	 * @param xp
	 */
	public XP(float x, float y, int xp) {
		super(x, y);
		valeur = xp;
	}


	@Override
	void afficherEtMvt(SpriteBatch batch) {
		batch.draw(TexMan.XP, posX, posY,
				// CENTRE DE LA ROTATION EN X													// CENTRE DE LA ROTATION EN Y
				DEMI_LARGEUR,DEMI_HAUTEUR,
				// LARGEUR DU RECTANGLE AFFICHE		HAUTEUR DU RECTANGLE
				LARGEUR, HAUTEUR,
				//scaleX the scale of the rectangle around originX/originY in x ET Y
				1,1,
				// L'ANGLE DE ROTATION
				angleRotation,
				//FLIP OU PAS
				false);
		angleRotation = physique.Physique.rotation(angleRotation, VITESSE_ROTATION);
	}


	@Override
	public float getDemiLargeurColl() {
		return DEMI_LARGEUR_COLLISION;
	}


	@Override
	public float getDemiHauteurColl() {
		return DEMI_HAUTEUR_COLLISION;
	}


	@Override
	public float getLargeurColl() {
		return LARGEUR_COLLISION;
	}


	@Override
	public float getHauteurColl() {
		return HAUTEUR_COLLISION;
	}


	@Override
	public void pris() {
		CSG.profil.addXp(valeur);
	}
}
