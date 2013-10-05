package objets.armes.joueur;

import jeu.EndlessMode;
import jeu.Physique;
import jeu.Stats;
import menu.CSG;
import assets.AssetMan;
import assets.particules.Particules;
import assets.animation.AnimationTirTrois;

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
public class ArmeHantee extends ArmeJoueur implements Poolable{
	
	// ** ** caracteristiques g�n�rales
	public static int LARGEUR;
	public static int DEMI_LARGEUR;
	public static final float CADENCETIR = .19f;
	public static final int VITESSE_ANGLE = 5000;
	private static final int FORCE = 2;
	public static final String LABEL = "armeHantee";
	public static Pool<ArmeHantee> pool = Pools.get(ArmeHantee.class);
	private static boolean alterner = false;
	public static float[] couleurs = {
		AssetMan.convertARGB(1, 0, 84f/255f, 73f/255f),
		AssetMan.convertARGB(1, 0, 162f/255f, 140f/255f),
		AssetMan.convertARGB(1, 0, 141f/255f, 239f/255f),
		AssetMan.convertARGB(1, 0, 218f/255f, 228f/255f),
		AssetMan.convertARGB(1, 0, 89f/255f, 252f/255f),
		AssetMan.convertARGB(1, 0, 62f/255f, 254f/255f)};
	
	public static void updateDimensions() {
		LARGEUR = CSG.LARGEUR_ECRAN / 30 + (CSG.LARGEUR_ECRAN/100 * CSG.profil.NvArmeHantee);
		DEMI_LARGEUR = LARGEUR/2;
	}
	
	public void init(float posX, float posY) {
		position.x = posX;
		position.y = posY;
		liste.add(this);
		direction.x = 0;
		direction.y = 1;
		direction.y *= Stats.V_ARME_HANTEE;
		angle += 0;
	}

	@Override
	public void reset() {
		direction.x = 0;
		direction.y = 1;
		direction.y *= Stats.V_ARME_HANTEE;
		maintenant = 0;
	}

	@Override
	public void afficher(SpriteBatch batch) {
		Particules.armeHantee(this);
		batch.draw(AnimationTirTrois.getTexture(1), position.x, position.y, DEMI_LARGEUR, DEMI_LARGEUR, LARGEUR, LARGEUR, 1, 1, angle);
	}
	
	@Override
	public boolean mouvementEtVerif() {
		angle += VITESSE_ANGLE * EndlessMode.delta;
		maintenant += EndlessMode.delta;
		if (maintenant > .4f && direction.y != 0) {
			if (alterner) direction.x = -direction.y;
			else direction.x = direction.y;
			direction.y = 0;
			alterner = !alterner;
		}
		return Physique.mouvementDeBase(direction, position, LARGEUR);
	}

	@Override
	public int getForce() {		return FORCE + CSG.profil.NvArmeHantee;	}
	@Override
	public int getLargeur() {		return LARGEUR;	}
	@Override
	public int getHauteur() {		return LARGEUR;	}
	@Override
	public void free() {		pool.free(this);	}
	@Override
	public float getColor() {		return couleurs[r.nextInt(couleurs.length)];	}
	@Override
	public TextureRegion getTexture() {		return null;	}
	@Override
	public float getDemiLargeur() {		return DEMI_LARGEUR;	}
	@Override
	public float getDemiHauteur() {		return DEMI_LARGEUR;	}
}
