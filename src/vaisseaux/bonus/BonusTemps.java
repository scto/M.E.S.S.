package vaisseaux.bonus;

import java.util.ArrayList;
import java.util.List;

import jeu.Endless;

import physique.Physique;

import menu.CSG;
import affichage.TexMan;
import affichage.animation.AnimationTriangleRond;

import com.badlogic.gdx.Gdx;
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
	public static AnimationTriangleRond anim = new AnimationTriangleRond();
	// voir à quelle taille l'initialiser	
	public static List<BonusTemps> liste = new ArrayList<BonusTemps>(30);
	private float tps = 0;
	private static int cptBonus = 0;
	private static int nbBonusLache = 0;
	
	
	public BonusTemps(float x, float y) {
		super(x,y);
	}

	/**
	 * Affiche l'xp et la fait tourner
	 * @param batch
	 * @param delta 
	 */
	public static void affichage(SpriteBatch batch, float delta) {
		for(BonusTemps b : liste){
			b.tps += delta;
			batch.draw(anim.getTexture(b.tps), b.posX, b.posY,	LARGEUR, HAUTEUR);
		}
	}

	/**
	 * Se base sur le nombre de bonus temps déjà lache et l'xp apportée pour savoir si il doit apparaitre ou non
	 * @param x
	 * @param y
	 * @param xp
	 */
	public static void ajoutBonus(float x, float y, int xp) {
		cptBonus += xp;
		if(cptBonus > 5 * nbBonusLache){
			new BonusTemps(x, y);
			nbBonusLache++;
			cptBonus = 0;
		}
	}

	@Override
	void afficherEtMvt(SpriteBatch batch, float delta) {
		tps += delta;
		batch.draw(anim.getTexture(tps), posX, posY, LARGEUR, HAUTEUR);
		// Le fait descendre
		posY += VITESSE * delta;
		// le fait aller à gauche ou à droite de plus en plus suivant le temps écoulé
		if(posX < CSG.DEMI_LARGEUR_ECRAN)
			posX -= ( (tps*tps) * delta);
		else
			posX += ( (tps*tps) * delta);
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
		Endless.ralentir(1);
	}
}
