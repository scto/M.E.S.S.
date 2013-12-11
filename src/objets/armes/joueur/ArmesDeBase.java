package objets.armes.joueur;

import jeu.CSG;
import jeu.Stats;
import assets.AssetMan;
import assets.particules.Particules;
import assets.animation.AnimationTirFeu;

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
public class ArmesDeBase extends ArmeJoueur implements Poolable{
	
	// ** ** caracteristiques g�n�rales
	public static int LARGEUR;
	public static int DEMI_LARGEUR;
	public static int HAUTEUR;
	public static int DEMI_HAUTEUR; 
	public static final float CADENCETIR = .31f;
	private static final int FORCE = 8;
	public static final String LABEL = "ArmeDeBase";
	public static Pool<ArmesDeBase> pool = Pools.get(ArmesDeBase.class);
	
	public static float[] couleurs = {
		AssetMan.convertARGB(1, 108f/255f, 4f/255f, 0),
		AssetMan.convertARGB(1, 156f/255f, 8f/255f, 0),
		AssetMan.convertARGB(1, 184f/255f, 12f/255f, 0),
		AssetMan.convertARGB(1, 132f/255f, 4f/255f, 0),
		AssetMan.convertARGB(1, 208f/255f, 20f/255f, 0),
		AssetMan.convertARGB(1, 228f/255f, 96f/255f, 12/255f),
		AssetMan.convertARGB(1, 232f/255f, 136f/255f, 24/255f),
		AssetMan.convertARGB(1, 240f/255f, 172f/255f, 36/255f),
		AssetMan.convertARGB(1, 248f/255f, 200f/255f, 48/255f),
		AssetMan.convertARGB(1, 252f/255f, 228f/255f, 60/255f),
		AssetMan.convertARGB(1, 252f/255f, 248f/255f, 148/255f)};
	
	public static void updateDimensions() {
		LARGEUR = CSG.LARGEUR_ECRAN / 30 + (CSG.LARGEUR_ECRAN/100 * CSG.profil.NvArmeDeBase);
		DEMI_LARGEUR = LARGEUR/2;
		HAUTEUR = (int) (LARGEUR * 1.5);
		DEMI_HAUTEUR = HAUTEUR / 2;
	}
	
	/**
	 * ATTENTION ici le init s'occupe d'ajouter � la bonne liste
	 */
	public void init(float posX, float posY) {
		position.x = posX;
		position.y = posY;
		liste.add(this);
		direction.y = Stats.V_ARME_DE_BASE;
	}

	@Override
	public void afficher(SpriteBatch batch) {
		Particules.ajoutArmeDeBase(this);
		super.afficher(batch);
	}

	@Override
	public int getForce() {				return FORCE + CSG.profil.NvArmeDeBase;	}
	@Override
	public int getLargeur() {			return LARGEUR;	}
	@Override
	public int getHauteur() {			return HAUTEUR;	}
	@Override
	public void free() {				pool.free(this);	}
	@Override
	public float getColor() {			return couleurs[r.nextInt(couleurs.length)];	}
	@Override
	public TextureRegion getTexture() {	return AnimationTirFeu.getTexture(maintenant);	}
	@Override
	public float getDemiLargeur() {		return DEMI_LARGEUR;	}
	@Override
	public float getDemiHauteur() {		return DEMI_HAUTEUR;	}

	public static String getLabel() {
		return "ArmeDeBase";
	}
}
