package objets.armes.joueur;

import objets.armes.Armes;
import jeu.Physique;
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
 * Arme tirant en balyant l'�cran de gauche � droite, d'o� le nom
 * C'est celle qui est verte
 * @author Julien
 *
 */

public class ArmeAdd extends ArmeJoueur implements Poolable{
	
	// ** ** caracteristiques g�n�rales
	public static final int LARGEUR= CSG.LARGEUR_ECRAN / 27, DEMI_LARGEUR = LARGEUR/2, HAUTEUR = LARGEUR * 2, DEMI_HAUTEUR = HAUTEUR / 2; 
	public static float CADENCETIR;
	private static final int FORCE = 5;
	public static Pool<ArmeAdd> pool = Pools.get(ArmeAdd.class);
	public static float[] couleurs = {
		AssetMan.convertARGB(1, 27f/255f, 124f/255f, 0),
		AssetMan.convertARGB(1, 38f/255f, 178f/255f, 0),
		AssetMan.convertARGB(1, 32f/255f, 151f/255f, 0),
		AssetMan.convertARGB(1, 45f/255f, 210f/255f, 0),
		AssetMan.convertARGB(1, 0, 255f/255f, 103f/255f),
		AssetMan.convertARGB(1, 0, 254f/255f, 191f/255f)};
	
	public static void determinerCadenceTir() {	CADENCETIR = 1.6f / CSG.profil.cadenceAdd;	}

	public void init(float x, float y, float angle) {
		position.x = x;
		position.y = y;
		direction.x = 0;
		direction.y = 1;
		direction.rotate(angle + 90);
		position.x += direction.x * 25;
		position.y += direction.y * 25;
		direction.mul(Stats.V_ARME_ADD);
		this.angle = angle;
		Armes.liste.add(this);
	}

	@Override
	public void reset() {			}

	@Override
	public void afficher(SpriteBatch batch) {	}

	@Override
	public boolean mouvementEtVerif() {
		Particules.ajoutAdd(this);
		return Physique.mouvementDeBase(HAUTEUR, LARGEUR, direction, position);
	}
	
	@Override
	public int getForce() {		return FORCE;	}
	@Override
	public int getLargeur() {	return LARGEUR;	}
	@Override
	public int getHauteur() {	return HAUTEUR;	}
	@Override
	public void free() {		pool.free(this);	}
	@Override
	public float getColor() {	return couleurs[r.nextInt(couleurs.length)];	}
	@Override
	public TextureRegion getTexture() {		return null;	}
	@Override
	public float getDemiLargeur() {		return DEMI_LARGEUR;	}
	@Override
	public float getDemiHauteur() {		return DEMI_HAUTEUR;	}

	public static void add(float x, float y, float angle) {
		ArmeAdd a = pool.obtain();
		a.init(x, y, angle);
	}
}

