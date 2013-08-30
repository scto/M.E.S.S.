package vaisseaux.armes.joueur;

import jeu.Stats;
import menu.CSG;
import assets.AssetMan;
import assets.particules.Particules;

import assets.animation.AnimationTirBleu;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.badlogic.gdx.utils.Pools;

/**
 * Arme tirant en balyant l'�cran de gauche � droite, d'o� le nom
 * @author Julien
 *
 */

public class ArmesBalayage extends ArmeJoueur implements Poolable{
	
	// ** ** caracteristiques g�n�rales
	public static int LARGEUR;
	public static int DEMI_LARGEUR;
	public static final float CADENCETIR = .08f;
	private static final int FORCE = 2;
	public static Pool<ArmesBalayage> pool = Pools.get(ArmesBalayage.class);
	public static float[] couleurs = {
		AssetMan.convertARGB(1, 0f, 0f, 136f/255f),
		AssetMan.convertARGB(1, 17f/255f, 66f/255f, 1f),
		AssetMan.convertARGB(1, 57f/255f, 127f/255f, 1f),
		AssetMan.convertARGB(1, 81f/255f, 173f/255f, 1f)};
	
	public static void updateDimensions() {
		LARGEUR = CSG.LARGEUR_ECRAN / 30 + (CSG.LARGEUR_ECRAN/100 * CSG.profil.NvArmeBalayage);
		DEMI_LARGEUR = LARGEUR/2;
	}

	public void init(float posX, float posY, float x, float y, float angle) {
		position.x = posX;
		position.y = posY;
		direction.x = x;
		direction.y = y;
		direction.mul(Stats.V_ARME_BALAYAGE);
		this.angle = angle;
		liste.add(this);
	}

	@Override
	public void afficher(SpriteBatch batch) {
		Particules.ajoutArmeBalayage(this);
		super.afficher(batch);
	}
	
	@Override
	public int getForce() {				return FORCE + CSG.profil.NvArmeBalayage;	}
	@Override
	public int getLargeur() {			return LARGEUR;	}
	@Override
	public int getHauteur() {			return LARGEUR;	}
	@Override
	public void free() {				pool.free(this);	}
	@Override
	public float getColor() {			return couleurs[r.nextInt(couleurs.length)];	}
	@Override
	public TextureRegion getTexture() {	return AnimationTirBleu.getTexture(maintenant);	}
	@Override
	public float getDemiLargeur() {		return DEMI_LARGEUR;	}
	@Override
	public float getDemiHauteur() {		return DEMI_LARGEUR;	}
}
