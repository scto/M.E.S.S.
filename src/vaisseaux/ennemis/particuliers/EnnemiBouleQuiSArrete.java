package vaisseaux.ennemis.particuliers;

import jeu.Endless;
import menu.CSG;
import physique.Physique;
import vaisseaux.armes.ArmesBouleBleu;
import vaisseaux.ennemis.Ennemis;
import vaisseaux.ennemis.TypesEnnemis;
import vaisseaux.joueur.VaisseauType1;
import affichage.animation.AnimationBouleBleuRouge;
import affichage.animation.AnimationExplosion1;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;


public class EnnemiBouleQuiSArrete extends Ennemis{
	
	// ** ** caracteristiques générales
	public static final int LARGEUR= CSG.LARGEUR_ECRAN / 17;
	public static final int DEMI_LARGEUR = LARGEUR/2;
	public static final int HAUTEUR = LARGEUR;
	private static final int DEMI_HAUTEUR = HAUTEUR / 2; 
	private static final int VITESSE_MAX = -100;
	public static final float CADENCETIR = 1.0f;
	public static final int PVMAX = 20;
	// ** ** caracteristiques variables.
	private float dernierTir = .1f;
	private float maintenant = 0;
	public static Pool<EnnemiBouleQuiSArrete> pool = Pools.get(EnnemiBouleQuiSArrete.class);
	// ** ** animations
	protected static AnimationBouleBleuRouge animation; 
	protected static AnimationExplosion1 animationExplosion;
	protected float tpsAnimationExplosion;
	private float tpsArret = 0;
	private float angle = 270;
	private float tpsAnim = 0;
	private Vector2 tmpVecteur = new Vector2();
	
	@Override
	public void reset() {
		position.x = getRandX();
		position.y = CSG.HAUTEUR_ECRAN + HAUTEUR;
		mort = false;
		tpsAnimationExplosion = 0;
		pv = PVMAX;
		dernierTir = .2f;
		angle = 270;
	}

	
	/**
	 * Initialise l'ennemi
	 */
	private void init() {
		animationExplosion = new AnimationExplosion1();
		animation = new AnimationBouleBleuRouge();
		tpsAnimationExplosion = 0;
	}

	public EnnemiBouleQuiSArrete() {
		super((float) (Math.random() * CSG.LARGEUR_ECRAN - DEMI_LARGEUR),	CSG.HAUTEUR_ECRAN + HAUTEUR, PVMAX);
		init();
	}

	private static float getRandX() {
		return (float) (Math.random() * CSG.LARGEUR_ECRAN - DEMI_LARGEUR);
	}
	
	/**
	 * Exactement la même que dans la super classe mais ça évite de faire des getter largeur hauteur...
	 */
	@Override
	public boolean mouvementEtVerif() {
		// Gros bout de code moche
		if(mort & tpsAnimationExplosion > AnimationExplosion1.tpsTotalAnimationExplosion1 | Physique.toujoursAfficher(position, HAUTEUR, LARGEUR) == false){
			pool.free(this);
			return false;
		} else { // Si on a passé le deuxième pallier les ailes sont repliées
			{
				if (position.y < CSG.HAUTEUR_ECRAN_PALLIER_2) {
					// On ralentit
					if (position.y > CSG.HAUTEUR_ECRAN_PALLIER_3)
						position.y += (-50 * Endless.delta);
					else
						tpsArret += Endless.delta;
				} else {
					position.y += (VITESSE_MAX * Endless.delta);
				}
			}
			tmpVecteur.x = VaisseauType1.position.x;
			tmpVecteur.y = VaisseauType1.position.y;
			angle = tmpVecteur.sub(position).angle();
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
			batch.draw(animation.getTexture(tpsAnim), position.x, position.y, LARGEUR, HAUTEUR);
			tpsAnim += Endless.delta;
		}
	}
	
	
	@Override
	protected void tir() {
		if (!mort & maintenant > dernierTir	+ ArmesBouleBleu.CADENCETIR + CADENCETIR & tpsArret > 0) {
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
}
