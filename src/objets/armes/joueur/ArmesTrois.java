package objets.armes.joueur;

import jeu.Stats;
import menu.CSG;
import assets.AssetMan;
import assets.particules.Particules;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
	public static final float CADENCETIR = .40f;
	private static final int FORCE = 4;
	public static final String LABEL = "armeTrois";
	public static Pool<ArmesTrois> pool = Pools.get(ArmesTrois.class);
	public static float[] couleurs = {
		AssetMan.convertARGB(1, 97f/255f, 32f/255f, 79f/255f),
		AssetMan.convertARGB(1, 156f/255f, 51f/255f, 126f/255f),
		AssetMan.convertARGB(1, 190f/255f, 63f/255f, 154f/255f),
		AssetMan.convertARGB(1, 181f/255f, 60f/255f, 147f/255f),
		AssetMan.convertARGB(1, 220f/255f, 151f/255f, 201f/255f)};

	public static void updateDimensions() {
		LARGEUR = CSG.LARGEUR_ECRAN / 30 + (CSG.LARGEUR_ECRAN/100 * CSG.profil.NvArmeTrois);
		DEMI_LARGEUR = LARGEUR/2;
	}
	
	public void init(float posX, float posY, float d, float e, float angle) {
		position.x = posX;
		position.y = posY;
		direction.x = d;
		direction.y = e;
		direction.mul(Stats.V_ARME_TROIS);
		this.angle = angle;
		liste.add(this);
	}

	@Override
	public void afficher(SpriteBatch batch) {
		Particules.armeTrois(this);
		batch.draw(AssetMan.laser , position.x, position.y, DEMI_LARGEUR, DEMI_LARGEUR,	LARGEUR, LARGEUR, 1, 1,	angle, false);
	}

	@Override
	public int getForce() {		return FORCE + CSG.profil.NvArmeTrois;	}
	@Override
	public int getLargeur() {		return LARGEUR;	}
	@Override
	public int getHauteur() {		return LARGEUR;	}
	@Override
	public void free() {			pool.free(this);	}
	@Override
	public float getColor() {		return couleurs[r.nextInt(couleurs.length)];	}
	@Override
	public TextureRegion getTexture() {	return AssetMan.laser;	}
	@Override
	public float getDemiLargeur() {		return DEMI_LARGEUR;	}
	@Override
	public float getDemiHauteur() {		return DEMI_LARGEUR;	}
}
