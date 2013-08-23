package vaisseaux.armes.joueur;

import jeu.Physique;
import jeu.Stats;
import menu.CSG;
import assets.AssetMan;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.badlogic.gdx.utils.Pools;

/**
 * Arme de base qui fait une boule de feu
 * @author Julien
 *
 */
public class ArmesTrois extends ArmeJoueur implements Poolable{
	
	// ** ** caracteristiques g�n�rales
	public static int LARGEUR;
	public static int DEMI_LARGEUR;
	public static final float CADENCETIR = .12f;
	private final int FORCE = 4;
	public static Pool<ArmesTrois> pool = Pools.get(ArmesTrois.class);
	private float angle = 0;
	public static float[] couleurs = {
		AssetMan.convertARGB(1, 97f, 32f, 79f),
		AssetMan.convertARGB(1, 156f, 51f, 126f),
		AssetMan.convertARGB(1, 190f, 63f, 154f),
		AssetMan.convertARGB(1, 181f, 60f, 147f),
		AssetMan.convertARGB(1, 220f, 151f, 201f)};

	public static void updateDimensions() {
		LARGEUR = CSG.LARGEUR_ECRAN / 30 + (CSG.LARGEUR_ECRAN/100 * CSG.profil.NvArmeTrois);
		DEMI_LARGEUR = LARGEUR/2;
	}
	
	public void init(float posX, float posY, float d, float e, float angle) {
		position.x = posX;
		position.y = posY;
		direction.x = d;
		direction.y = e;
		this.angle = angle;
		liste.add(this);
	}

	@Override
	public void reset() {	}

	@Override
	public void afficher(SpriteBatch batch) {
		batch.draw(AssetMan.laser , position.x, position.y, DEMI_LARGEUR, DEMI_LARGEUR,	LARGEUR, LARGEUR, 1, 1,	angle, false);
	}
	
	@Override
	public boolean mouvementEtVerif() {
		if (Physique.mouvementDeBase(direction, position, Stats.VITESSE_MAX_ARME_DE_BASE, LARGEUR)) return true;
		free();
		return false;
	}

	@Override
	public int getForce() {		return FORCE + CSG.profil.NvArmeTrois;	}

	@Override
	public int getLargeur() {		return LARGEUR;	}

	@Override
	public int getHauteur() {		return LARGEUR;	}
	
	@Override
	public void free() {
		pool.free(this);
	}

	@Override
	public boolean testCollsionAdds() {		return false;	}

	@Override
	public float getColor() {
		return couleurs[r.nextInt(couleurs.length)];
	}
}
