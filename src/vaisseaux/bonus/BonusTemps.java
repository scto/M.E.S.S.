package vaisseaux.bonus;

import java.util.ArrayList;
import java.util.List;

import physique.Physique;

import menu.CSG;
import affichage.TexMan;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class BonusTemps extends Bonus {

	public static final int LARGEUR = CSG.LARGEUR_ECRAN / 25;
	public static final int HAUTEUR = LARGEUR;
	public static final float HAUTEUR_COLLISION = HAUTEUR * 4;
	public static final float LARGEUR_COLLISION = LARGEUR * 4;
	public static final float DEMI_LARGEUR_COLLISION = LARGEUR_COLLISION/2;
	public static final float DEMI_HAUTEUR_COLLISION = HAUTEUR_COLLISION/2;
	private static final int VITESSE = -75;
	// voir à quelle taille l'initialiser
	public static List<XP> liste = new ArrayList<XP>(30);
	
	
	public BonusTemps(float x, float y) {
		super(x,y);
	}

	/**
	 * Affiche l'xp et la fait tourner
	 * @param batch
	 * @param delta 
	 */
	public static void affichage(SpriteBatch batch, float delta) {
		for(XP xp : liste){
			batch.draw(TexMan.XP, xp.posX, xp.posY,	LARGEUR, HAUTEUR);
		}
	}

	public static void ajoutBonus(float x, float y, int xp) {
		new BonusTemps(x, y);
	}

	@Override
	void afficherEtMvt(SpriteBatch batch, float delta) {
		batch.draw(TexMan.boutonRouge, posX, posY, LARGEUR, HAUTEUR);
		posY += VITESSE * delta;
	}

	@Override
	public float getDemiLargeurColl() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getDemiHauteurColl() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getLargeurColl() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getHauteurColl() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void pris() {
		// TODO Auto-generated method stub
		
	}
}
