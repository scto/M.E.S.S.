package vaisseaux;

import java.util.ArrayList;
import java.util.List;

import affichage.TexMan;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import menu.CSG;

public class XP {
	public static final int LARGEUR = CSG.LARGEUR_ECRAN / 30;
	public static final int HAUTEUR = LARGEUR;
	private static final int DEMI_LARGEUR = LARGEUR/2;
	private static final int DEMI_HAUTEUR = HAUTEUR/2;
	public static final float HAUTEUR_COLLISION = HAUTEUR * 6;
	public static final float LARGEUR_COLLISION = LARGEUR * 6;
	public static final float DEMI_LARGEUR_COLLISION = LARGEUR_COLLISION/2;
	public static final float DEMI_HAUTEUR_COLLISION = HAUTEUR_COLLISION/2;
	private static final float VITESSE_ROTATION = 60f;
	public float posX;
	public float posY;
	private float angleRotation = 0;
	public int valeur;
	// voir à quelle taille l'initialiser
	public static List<XP> liste = new ArrayList<XP>(30);
	
	
	public XP(Vector2 position2, int xp) {
		posX = position2.x;
		posY = position2.y;
		valeur = xp;
	}

	public static void ajoutXp(Vector2 position, int xp) {
		liste.add(new XP(position, xp));
	}

	/**
	 * Affiche l'xp et la fait tourner
	 * @param batch
	 */
	public static void affichage(SpriteBatch batch) {
		for(XP xp : liste){
			batch.draw(TexMan.XP, xp.posX, xp.posY,
					// CENTRE DE LA ROTATION EN X													// CENTRE DE LA ROTATION EN Y
					DEMI_LARGEUR,DEMI_HAUTEUR,
					// LARGEUR DU RECTANGLE AFFICHE		HAUTEUR DU RECTANGLE
					LARGEUR, HAUTEUR,
					//scaleX the scale of the rectangle around originX/originY in x ET Y
					1,1,
					// L'ANGLE DE ROTATION
					xp.angleRotation,
					//FLIP OU PAS
					false);
			xp.angleRotation = physique.Physique.rotation(xp.angleRotation, VITESSE_ROTATION);
		}
	}
}
