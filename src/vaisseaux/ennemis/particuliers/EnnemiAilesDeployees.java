package vaisseaux.ennemis.particuliers;

import menu.CSG;
import physique.Physique;
import vaisseaux.armes.ArmesBouleBleu;
import vaisseaux.ennemis.Ennemis;
import vaisseaux.ennemis.TypesEnnemis;
import vaisseaux.joueur.VaisseauType1;
import affichage.animation.AnimationBouleBleuRouge;
import affichage.animation.AnimationEnnemiAileDeployee;
import affichage.animation.AnimationExplosion1;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;


public class EnnemiAilesDeployees extends Ennemis{
	
	// ** ** caracteristiques générales
	public static final int LARGEUR= CSG.LARGEUR_ECRAN / 16;
	public static final int DEMI_LARGEUR = LARGEUR/2;
	public static final int HAUTEUR = LARGEUR;
	private static final int DEMI_HAUTEUR = HAUTEUR / 2; 
	private static final int VITESSE_MAX = 100;
	private static final int ROTATION = 100;
	public static final float CADENCETIR = 1.0f;
	public static final int PVMAX = 20;
	// ** ** caracteristiques variables.
	private float dernierTir = .1f;
	private float maintenant = 0;
	public static Pool<EnnemiAilesDeployees> pool = Pools.get(EnnemiAilesDeployees.class);
	private boolean lance = false;
	// ** ** animations
	protected static AnimationEnnemiAileDeployee animation; 
	protected static AnimationExplosion1 animationExplosion;
	protected float tpsAnimationExplosion;
	private float tpsAnim;
	// ** ** autre
	private float angle;
	private Vector2 direction = new Vector2(-1,0);
	

	@Override
	public void reset() {
		mort = false;
		tpsAnimationExplosion = 0;
		pv = PVMAX;
		dernierTir = .2f;
		lance = false;
	}

	
	/**
	 * Initialise l'ennemi
	 */
	private void init() {
		animationExplosion = new AnimationExplosion1();
		animation = new AnimationEnnemiAileDeployee();
		tpsAnimationExplosion = 0;
	}

	public EnnemiAilesDeployees() {
		super((float) (Math.random() * CSG.LARGEUR_ECRAN - DEMI_LARGEUR),	CSG.HAUTEUR_ECRAN + HAUTEUR, PVMAX);
		init();
	}

	/**
	 * Exactement la même que dans la super classe mais ça évite de faire des getter largeur hauteur...
	 */
	@Override
	public boolean mouvementEtVerif(float delta) {
		// Gros bout de code moche
		if(mort & tpsAnimationExplosion > AnimationExplosion1.tpsTotalAnimationExplosion1 | Physique.toujoursAfficher(position, HAUTEUR, LARGEUR) == false){
			pool.free(this);
			return false;
		} else { // Si on a passé le deuxième pallier les ailes sont repliées
			if(lance){
				Physique.mouvementTeteChercheuse(direction, position, VITESSE_MAX, HAUTEUR, LARGEUR, delta);
			}
		}
		return true;
	}

	/**
	 * Exactement la même que dans la super classe mais ça évite de faire des getter largeur hauteur...
	 */
	@Override
	public void afficher(SpriteBatch batch, float delta) {
		maintenant += delta;
		if(mort){
			batch.draw(animationExplosion.getTexture(tpsAnimationExplosion), position.x, position.y, LARGEUR, HAUTEUR);
			tpsAnimationExplosion += delta;
		}
		else {
			batch.draw(animation.getTexture(0), position.x, position.y,
					// CENTRE DE LA ROTATION EN X													// CENTRE DE LA ROTATION EN Y
					DEMI_LARGEUR,DEMI_HAUTEUR,
					// LARGEUR DU RECTANGLE AFFICHE		HAUTEUR DU RECTANGLE
					LARGEUR, HAUTEUR,
					//scaleX the scale of the rectangle around originX/originY in x ET Y
					1,1,
					// L'ANGLE DE ROTATION
					angle,
					//FLIP OU PAS
					false);
			tpsAnim += delta;
		}
	}
	
	
	@Override
	protected void tir() {
		if (!mort & maintenant > dernierTir	+ ArmesBouleBleu.CADENCETIR + CADENCETIR & lance) {
			ArmesBouleBleu milieu = ArmesBouleBleu.pool.obtain();
			milieu.init(position.x + DEMI_LARGEUR - ArmesBouleBleu.DEMI_LARGEUR, 0, position.y + DEMI_HAUTEUR - ArmesBouleBleu.DEMI_LARGEUR, -(ArmesBouleBleu.HAUTEUR+DEMI_HAUTEUR), angle);
			dernierTir = maintenant;
		}
	}


	@Override
	public int getXp() {
		return TypesEnnemis.EnnemiDeBaseQuiTir.COUT;
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

	@Override
	public Rectangle getRectangleCollision() {
		collision.set(position.x, position.y, LARGEUR, HAUTEUR);
		return collision;
	}


	public void setPosition(float x, float y) {
		position.x = x;
		position.y = y;
	}


	public void setAngle(int i) {
		this.angle = i;
	}


	public void lancer(float f, float g) {
		lance = true;
		direction.x = f;
		direction.y = g;
		System.out.println("Angle direction : " + direction.angle());
	}
}
