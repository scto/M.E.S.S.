package vaisseaux.armes;

import jeu.Endless;
import menu.CSG;
import physique.Physique;
import affichage.animation.AnimationTirBleu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;
import com.badlogic.gdx.utils.Pool.Poolable;

/**
 * Arme tirant en balyant l'écran de gauche à droite, d'où le nom
 * @author Julien
 *
 */

public class ArmesBalayage extends Armes implements Poolable{
	
	// ** ** caracteristiques générales
	public static final int LARGEUR= CSG.LARGEUR_ECRAN / 15;
	public static final int DEMI_LARGEUR = LARGEUR/2;
	private static final int HAUTEUR = LARGEUR;
	private static final int DEMI_HAUTEUR = HAUTEUR / 2; 
	private static final int VITESSE_MAX = 800;
	public static final float CADENCETIR = .1f;
	private static final int FORCE = 2;
	public static Pool<ArmesBalayage> pool = Pools.get(ArmesBalayage.class);
	// ** ** animation
	private static AnimationTirBleu anim = new AnimationTirBleu();
	private float tpsAnim = 0;
	// ** ** variable utilitaire
	private float angle;
	// ** ** test
	private ParticleEffect particleEffect = new ParticleEffect();

	/**
	 * appelé systematiquement après le pool obtain
	 * @param posX
	 * @param posY
	 * @param x
	 * @param y
	 * @param angle 
	 * @param ennemi
	 */
	public void init(float posX, float posY, float x, float y, float angle) {
		position.x = posX;
		position.y = posY;
		direction.x = x;
		direction.y = y;
		this.angle = angle;
		particleEffect.load(Gdx.files.internal("particules/ballebleu.p"), Gdx.files.internal("particules"));
		particleEffect.start();
	}
	
	@Override
	public void reset() {
//		tpsAnim = 0;
	}

	@Override
	public void afficher(SpriteBatch batch) {
		particleEffect.setPosition(position.x + DEMI_LARGEUR, position.y + DEMI_HAUTEUR);
		particleEffect.draw(batch, Endless.delta);
//		
		tpsAnim += Endless.delta;
//		batch.draw(anim.getTexture(tpsAnim), position.x, position.y,
//		// CENTRE DE LA ROTATION EN X													// CENTRE DE LA ROTATION EN Y
//		DEMI_LARGEUR,DEMI_HAUTEUR,
//		// LARGEUR DU RECTANGLE AFFICHE		HAUTEUR DU RECTANGLE
//		LARGEUR, HAUTEUR,
//		//scaleX the scale of the rectangle around originX/originY in x ET Y
//		1,1,
//		// L'ANGLE DE ROTATION
//		angle,
//		//FLIP OU PAS
//		false);
	}


	@Override
	public boolean mouvementEtVerif() {
		// 0 pour que l'effet ne disparaisse pas trop vite au lieu de HAUTEUR
		return Physique.mouvementDeBase(direction, position, VITESSE_MAX, 0, LARGEUR);
	}
	
	@Override
	public int getForce() {
		return FORCE + CSG.profil.NvArmeBalayage;
	}
	
	@Override
	public int getLargeur() {
		return LARGEUR;
	}

	@Override
	public int getHauteur() {
		return HAUTEUR;
	}

	/**
	 * A voir si il faut pas la virer
	 * @param posX
	 * @param posY
	 * @param dirX
	 * @param dirY
	 * @param ennemi
	 */
	@Override
	public void init(float posX, float posY, int dirX, int dirY, boolean ennemi) {
		
	}

	@Override
	public void free() {
		pool.free(this);
	}
}
