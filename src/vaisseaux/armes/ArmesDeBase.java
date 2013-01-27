package vaisseaux.armes;

import menu.CSG;
import physique.Physique;
import affichage.animation.AnimationTirFeu;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.badlogic.gdx.utils.Pools;

/**
 * Arme de base qui fait une boule de feu
 * @author Julien
 *
 */
public class ArmesDeBase extends Armes implements Poolable{
	
	// ** ** caracteristiques générales
	public static final int LARGEUR= CSG.LARGEUR_ECRAN / 20;
	public static final int DEMI_LARGEUR = LARGEUR/2;
	public static final int HAUTEUR = (int) (LARGEUR * 1.5);
	private static final int DEMI_HAUTEUR = HAUTEUR / 2; 
	private static final int VITESSE_MAX = 300;
	public static final float CADENCETIR = .22f;
	private final int FORCE = 8;
	public static Pool<ArmesDeBase> pool = Pools.get(ArmesDeBase.class);
	//private AnimationDeBase animation;
	private static AnimationTirFeu animation = new AnimationTirFeu();
	private float tpsAnimation = 0;
	// ** ** caracteristiques variables. 

	
	@Override
	public void reset() {
	}
	
	/**
	 * Crée l'objet, il faut appeler la méthode init après
	 */
	public ArmesDeBase() {
		super();
	}

	@Override
	public void afficher(SpriteBatch batch, float delta){
		tpsAnimation += delta;
		batch.draw(animation.getTexture(tpsAnimation) , position.x, position.y, LARGEUR, HAUTEUR);
	}

	
	@Override
	public boolean mouvementEtVerif(float delta) {
		if (Physique.mouvementDeBase(direction, position, VITESSE_MAX, HAUTEUR, LARGEUR, delta) == false){
			pool.free(this);
			return false;
		}
		return true;
	}
	@Override
	public Rectangle getRectangleCollision() {
		collision.set(position.x, position.y, LARGEUR, HAUTEUR);
		return collision;
	}

	@Override
	public int getForce() {
		return FORCE + CSG.profil.NvArmeDeBase;
	}

	@Override
	public int getLargeur() {
		return LARGEUR;
	}

	@Override
	public int getHauteur() {
		return HAUTEUR;
	}


	@Override
	public void free() {
		pool.free(this);
	}
	
	
	public void init(float posX, float posY, int dirX, int dirY, boolean ennemi) {
		position.x = posX;
		position.y = posY;
		direction.x = dirX;
		direction.y = dirY;
		if(ennemi) listeTirsDesEnnemis.add(this);
		else liste.add(this);
	}

}
