package vaisseaux.ennemis.particuliers;

import physique.Physique;
import vaisseaux.ennemis.Ennemis;
import vaisseaux.ennemis.TypesEnnemis;
import menu.CSG;
import affichage.animation.AnimationEnnemiDeBase;
import affichage.animation.AnimationExplosion1;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

/**
 * Ennemi de base, gère son pool, va tout droit et ni tire pas.
 * @author Julien
 *
 */
public class EnnemiDeBase extends Ennemis{
	
	// ** ** caracteristiques générales
	public static final int LARGEUR= CSG.LARGEUR_ECRAN / 12;
	public static final int DEMI_LARGEUR = LARGEUR/2;
	public static final int HAUTEUR = LARGEUR;
	private static final int DEMI_HAUTEUR = HAUTEUR / 2; 
	private static final int VITESSE_MAX = 150;
	public static final int PVMAX = 6;
	static final double chancePowerUp = 0.01;
	public static Pool<EnnemiDeBase> pool = Pools.get(EnnemiDeBase.class);
	// ** ** animations
	protected AnimationEnnemiDeBase animation; 
	protected AnimationExplosion1 animationExplosion;
	protected float tpsAnimation;
	protected float tpsAnimationExplosion;
	// ** ** caracteristiques variables.

	/** 
	 * Appelé par les sous classes
	 * @param posX
	 * @param posY
	 * @param dirX
	 * @param dirY
	 * @param pv
	 */
	public EnnemiDeBase(float posX, float posY, float dirX, float dirY, int pv) {
		super(posX, posY, dirX, dirY, pv);
		init();
	}

	
	/**
	 * Contructeur sans argument, appelé par le pool
	 */
	public EnnemiDeBase() {
		super((float) (Math.random() * CSG.LARGEUR_ECRAN - DEMI_LARGEUR),	CSG.HAUTEUR_ECRAN + HAUTEUR, 0, -1, PVMAX);
		init();
	}
	
	/**
	 * Initialise l'ennemi
	 */
	private void init() {
		animationExplosion = new AnimationExplosion1();
		animation = new AnimationEnnemiDeBase();
		tpsAnimation = 0;
		tpsAnimationExplosion = 0;
	}

	@Override
	public void reset() {
		position.x = (float) (Math.random() * CSG.LARGEUR_ECRAN - DEMI_LARGEUR);
		position.y = CSG.HAUTEUR_ECRAN + HAUTEUR;
		mort = false;
		tpsAnimationExplosion = 0;
		pv = PVMAX;
	}


	@Override
	public void afficher(SpriteBatch batch) {
		if(mort){
			batch.draw(animationExplosion.getTexture(tpsAnimationExplosion), position.x, position.y, LARGEUR, HAUTEUR);
			tpsAnimationExplosion += Gdx.graphics.getDeltaTime();
		}
		else{
			batch.draw(animation.getTexture(tpsAnimation), position.x, position.y, LARGEUR, HAUTEUR);
			tpsAnimation += Gdx.graphics.getDeltaTime();
		}
	}

	@Override
	public boolean mouvementEtVerif() {
		if(mort & tpsAnimationExplosion > AnimationExplosion1.tpsTotalAnimationExplosion1 
				| Physique.mouvementDeBase(direction, position, VITESSE_MAX, HAUTEUR, LARGEUR) == false){
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
	public int getXp() {
		return TypesEnnemis.EnnemiDeBase.COUT;
	}

	@Override
	public int getHauteur() {
		return HAUTEUR;
	}

	@Override
	public int getLargeur() {
		return LARGEUR;
	}

	@Override
	public int getVitesse() {
		return VITESSE_MAX;
	}

	@Override
	public int getDemiHauteur() {
		return DEMI_HAUTEUR;
	}

	@Override
	public int getDemiLargeur() {
		return DEMI_LARGEUR;
	}
	
}
