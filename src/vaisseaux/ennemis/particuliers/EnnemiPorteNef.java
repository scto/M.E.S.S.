package vaisseaux.ennemis.particuliers;

import menu.CSG;
import physique.Physique;
import vaisseaux.armes.ArmesDeBase;
import vaisseaux.ennemis.Ennemis;
import vaisseaux.ennemis.TypesEnnemis;
import affichage.animation.AnimationEnnemiDeBase;
import affichage.animation.AnimationExplosion1;
import affichage.animation.AnimationPorteNef;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;


public class EnnemiPorteNef extends Ennemis{
	
	// ** ** caracteristiques générales
	public static final int LARGEUR= CSG.LARGEUR_ECRAN / 3;
	public static final int DEMI_LARGEUR = LARGEUR/2;
	public static final int HAUTEUR = LARGEUR;
	private static final int DEMI_HAUTEUR = HAUTEUR / 2;
	private static final int Y_PREMIER = (HAUTEUR / 40) * 17;
	private static final int Y_DEUXIEME = (HAUTEUR / 40) * 29;
	private static final int X_DEUXIEME = (LARGEUR / 13);
	private static final int ANGLE_DEUXIEME = 140;
	private static final int Y_TROISIEME = (HAUTEUR) - EnnemiAilesDeployees.HAUTEUR;
	private static final int X_TROISIEME = (LARGEUR / 2) - EnnemiAilesDeployees.DEMI_LARGEUR;
	private static final int ANGLE_TROISIEME = 90;
	private static final int Y_QUATRIEME = Y_DEUXIEME;
	private static final int X_QUATRIEME = LARGEUR - X_DEUXIEME - EnnemiAilesDeployees.LARGEUR;
	private static final int ANGLE_QUATRIEME = 40;
	private static final int Y_CINQUIEME = Y_PREMIER;
	private static final int X_CINQUIEME = LARGEUR - EnnemiAilesDeployees.LARGEUR;
	private static final int ANGLE_CINQUIEME = 0;
	private static final int VITESSE_MAX = 30;
	public static final int PVMAX = 70;
	public static final int DEMI_PVMAX = PVMAX/2;
	// ** ** caracteristiques variables.
	private float maintenant = 0;
	private Rectangle collision = new Rectangle(position.x, position.y, LARGEUR, HAUTEUR);
	public static Pool<EnnemiPorteNef> pool = Pools.get(EnnemiPorteNef.class);
	protected static AnimationExplosion1 animationExplosion;
	private static AnimationPorteNef animation;
	protected float tpsAnimationExplosion;
	private float angle = -180;
	private int nbLance = 0;
	private int dirX = -1;
	
	/**
	 * Initialise l'ennemi
	 */
	public void init() {
		animationExplosion = new AnimationExplosion1();
		animation = new AnimationPorteNef();
		tpsAnimationExplosion = 0;
	}

	/**
	 * Le reset sert à faire le random du côté depuis le quel il apparait, à voir à l'usage mais pourquoi pas.
	 */
	@Override
	public void reset() {
		position.x = -LARGEUR;
		position.y = CSG.HAUTEUR_ECRAN_PALLIER_3 - DEMI_HAUTEUR;
		mort = false;
		tpsAnimationExplosion = 0;
		pv = PVMAX;
		dirX = 1;
		angle = 0;
	}

	public EnnemiPorteNef() {
		super(CSG.LARGEUR_ECRAN, CSG.HAUTEUR_ECRAN_PALLIER_2 - HAUTEUR, PVMAX);
		init();
	}
	
	/**
	 * Exactement la même que dans la super classe mais ça évite de faire des getter largeur hauteur...
	 */
	@Override
	public boolean mouvementEtVerif(float delta) {
		if(mort & tpsAnimationExplosion > AnimationExplosion1.tpsTotalAnimationExplosion1 
				| Physique.toujoursAfficher(position, HAUTEUR, LARGEUR) == false){
			pool.free(this);
			return false;
		}
		position.x += dirX * delta * VITESSE_MAX;
		// == == Partie ennemis à lancer
		switch(nbLance){
		case 0:
			if(maintenant > 4){
				EnnemiAilesDeployees premier = EnnemiAilesDeployees.pool.obtain();
				premier.setPosition(position.x, position.y + Y_PREMIER);
				premier.setAngle(180);
				premier.lancer(-1, 0);
				liste.add(premier);
				nbLance++;
			}
			break;
//		case 1:
//			if(maintenant > 6){
//				EnnemiAilesDeployees deuxieme = EnnemiAilesDeployees.pool.obtain();
//				deuxieme.setPosition(position.x + X_DEUXIEME, position.y + Y_DEUXIEME);
//				deuxieme.setAngle(ANGLE_DEUXIEME);
//				deuxieme.lancer(-0.76604444f, 0.6427876f);
//				liste.add(deuxieme);
//				nbLance++;
//			}
//			break;
//		case 2:
//			if(maintenant > 8){
//				EnnemiAilesDeployees troisieme = EnnemiAilesDeployees.pool.obtain();
//				troisieme.setPosition(position.x + X_TROISIEME, position.y + Y_TROISIEME);
//				troisieme.setAngle(ANGLE_TROISIEME);
//				troisieme.lancer(0, 1);
//				liste.add(troisieme);
//				nbLance++;
//			}
//			break;
//		case 3:
//			if(maintenant > 10){
//				EnnemiAilesDeployees quatrieme = EnnemiAilesDeployees.pool.obtain();
//				quatrieme.setPosition(position.x + X_QUATRIEME, position.y + Y_QUATRIEME);
//				quatrieme.setAngle(ANGLE_QUATRIEME);
//				quatrieme.lancer(0.76604444f, 0.6427876f);
//				liste.add(quatrieme);
//				nbLance++;
//			}
//			break;
//		case 4:
//			if(maintenant > 12){
//				EnnemiAilesDeployees cinquieme = EnnemiAilesDeployees.pool.obtain();
//				cinquieme.setPosition(position.x + X_CINQUIEME, position.y + Y_CINQUIEME);
//				cinquieme.setAngle(ANGLE_CINQUIEME);
//				cinquieme.lancer(1, 0);
//				liste.add(cinquieme);
//				nbLance++;
//			}
//			break;
		}
		return true;
	}

	/**
	 * Exactement la même que dans la super classe mais ça évite de faire des getter largeur hauteur...
	 * Il rotationne du double du delta
	 */
	@Override
	public void afficher(SpriteBatch batch, float delta) {
		maintenant += delta;
		if(mort){
			batch.draw(animationExplosion.getTexture(tpsAnimationExplosion), position.x, position.y, LARGEUR, HAUTEUR);
			tpsAnimationExplosion += delta;
		}
		else{
			batch.draw(animation.getTexture(pv), position.x, position.y,
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
		}
	}

	@Override
	public int getXp() {
		return TypesEnnemis.EnnemiPorteNef.COUT;
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
		collision.x = position.x;
		collision.y = position.y;
		return collision;
	}
}
