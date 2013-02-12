package vaisseaux.ennemis.particuliers;

import jeu.Endless;
import menu.CSG;
import physique.Physique;
import vaisseaux.armes.ArmesBouleVerte;
import vaisseaux.ennemis.Ennemis;
import vaisseaux.ennemis.TypesEnnemis;
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
	public static final float CADENCETIR = 1.0f;
	public static final float CADENCETIR_SUR_3 = CADENCETIR/3;
	public static final int PVMAX = 20;
	public static final int VITESSE_ANGULAIRE = 100;
	// ** ** caracteristiques variables.
	private boolean doubleTir = false;
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
		lance = false;
		trigger = CADENCETIR;
		tpsAnim = 0;
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

	@Override
	public boolean mouvementEtVerif() {
		if (mort) {
			if (tpsAnimationExplosion > AnimationExplosion1.tpsTotalAnimationExplosion1	| Physique.toujoursAfficher(position, HAUTEUR, LARGEUR) == false) {
				pool.free(this);
				return false;
			}
		} else {
			if (lance) angle = Physique.mouvementTeteChercheuse(direction, position, VITESSE_MAX, HAUTEUR, LARGEUR, VITESSE_ANGULAIRE);
		}
		return true;
	}

	/**
	 * Exactement la même que dans la super classe mais ça évite de faire des getter largeur hauteur...
	 */
	@Override
	public void afficher(SpriteBatch batch) {
		maintenant += Endless.delta;
		if(mort){
			batch.draw(animationExplosion.getTexture(tpsAnimationExplosion), position.x, position.y, LARGEUR, HAUTEUR);
			tpsAnimationExplosion += Endless.delta;
		}
		else {
			batch.draw(animation.getTexture(tpsAnim), position.x, position.y,
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
			tpsAnim += Endless.delta;
		}
	}
	
	private float trigger = CADENCETIR;
	@Override
	protected void tir() {
//		if (!mort &
//				((maintenant > dernierTir + CADENCETIR))&
//				lance) {
//			ArmesBouleVerte milieu = ArmesBouleVerte.pool.obtain();
//			milieu.init(position.x + DEMI_LARGEUR - ArmesBouleVerte.DEMI_LARGEUR, 0, position.y + DEMI_HAUTEUR - ArmesBouleVerte.DEMI_LARGEUR, -(ArmesBouleVerte.HAUTEUR+DEMI_HAUTEUR), angle);
//			dernierTir = maintenant;
//		}
		if (!mort & lance) {
			if (trigger < maintenant) {
				ArmesBouleVerte milieu = ArmesBouleVerte.pool.obtain();
				milieu.init(position.x + DEMI_LARGEUR - ArmesBouleVerte.DEMI_LARGEUR, 0, position.y + DEMI_HAUTEUR - ArmesBouleVerte.DEMI_LARGEUR, -(ArmesBouleVerte.HAUTEUR+DEMI_HAUTEUR), angle);
				if (!doubleTir)
					trigger += CADENCETIR_SUR_3;
				else
					trigger += CADENCETIR;
				doubleTir = !doubleTir;
			}
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
	}
}
