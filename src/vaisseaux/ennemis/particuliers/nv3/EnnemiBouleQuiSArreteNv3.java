package vaisseaux.ennemis.particuliers.nv3;

import jeu.Endless;
import jeu.Physique;
import jeu.Stats;
import menu.CSG;
import vaisseaux.armes.ArmesBouleBleu;
import vaisseaux.ennemis.Ennemis;
import vaisseaux.ennemis.CoutsEnnemis;
import vaisseaux.joueur.VaisseauType1;
import assets.SoundMan;
import assets.animation.AnimationBouleBleuRouge;
import assets.animation.AnimationExplosion1;
import assets.particules.ParticulesExplosionPetite;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;


public class EnnemiBouleQuiSArreteNv3 extends Ennemis{
	
	// ** ** caracteristiques g�n�rales
	public static final int LARGEUR= CSG.LARGEUR_ECRAN / 17;
	public static final int DEMI_LARGEUR = LARGEUR/2;
	public static final float CADENCETIR = 1.6f;
	// ** ** caracteristiques variables.
	private float dernierTir = .1f;
	public static Pool<EnnemiBouleQuiSArreteNv3> pool = Pools.get(EnnemiBouleQuiSArreteNv3.class);
	// ** ** animations 
	protected float tpsAnimationExplosion = 0;
	private float tpsAnim = 0;
	private Vector2 tmpVecteur = new Vector2();
	private ParticulesExplosionPetite explosion;
	private boolean reset = false;
	
	@Override
	public void reset() {
		position.x = Physique.getEmplacementX(DEMI_LARGEUR);
		position.y = CSG.HAUTEUR_ECRAN + LARGEUR;
		mort = false;
		pv = Stats.PVMAX_BOULE_QUI_SARRETE3;
		dernierTir = .2f;
		reset = true;
		tpsAnim = 0;
	}
	
	@Override
	protected void mort() {
		SoundMan.playBruitage(SoundMan.explosionboule);
		if (CSG.profil.particules){
			explosion = ParticulesExplosionPetite.pool.obtain();
			explosion.start();
			explosion.setPosition(position.x + DEMI_LARGEUR, position.y + DEMI_LARGEUR);
		} else {
			tpsAnimationExplosion = 0;
		}
	}

	public EnnemiBouleQuiSArreteNv3() {
		super(Physique.getEmplacementX(DEMI_LARGEUR), CSG.HAUTEUR_ECRAN + LARGEUR, Stats.PVMAX_BOULE_QUI_SARRETE3);
	}

	public void init() {
		if(CSG.profil.particules & explosion == null)	explosion = ParticulesExplosionPetite.pool.obtain();
	}
	
	/**
	 * Exactement la m�me que dans la super classe mais �a �vite de faire des getter largeur hauteur...
	 */
	@Override
	public boolean mouvementEtVerif() {
		// Gros bout de code moche
		if( (mort && explosion.isComplete()) | Physique.toujoursAfficher(position, LARGEUR) == false){
			pool.free(this);
			if (explosion != null) explosion.free();
			return false;
		} else { // Si on a pass� le deuxi�me pallier les ailes sont repli�es
			if (position.y < CSG.HAUTEUR_ECRAN_PALLIER_2) {
				// On ralentit
				if (position.y > CSG.HAUTEUR_ECRAN_PALLIER_3)	position.y += (-50 * Endless.delta);
			} else {
				position.y += (Stats.VITESSE_MAX_BOULE_QUI_SARRETE * Endless.delta);
			}
			if (tpsAnim > 10) {
				if(reset){
					position.y -= Stats.VITESSE_MAX_BOULE_QUI_SARRETE * Endless.delta;
					position.x -= Stats.VITESSE_MAX_BOULE_QUI_SARRETE * Endless.delta;
				} else {
					position.y -= Stats.VITESSE_MAX_BOULE_QUI_SARRETE * Endless.delta;
					position.x += Stats.VITESSE_MAX_BOULE_QUI_SARRETE * Endless.delta;
				}
			}
		}
		return true;
	}
	@Override
	public boolean mouvementEtVerifSansParticules() {
		if( (mort & tpsAnimationExplosion > AnimationExplosion1.tpsTotalAnimationExplosion1) | Physique.toujoursAfficher(position, LARGEUR) == false){
			pool.free(this);
			return false;
		} else { // Si on a pass� le deuxi�me pallier les ailes sont repli�es
			if (position.y < CSG.HAUTEUR_ECRAN_PALLIER_2) {
				// On ralentit
				if (position.y > CSG.HAUTEUR_ECRAN_PALLIER_3)	position.y += (-50 * Endless.delta);
			} else {
				position.y += (Stats.VITESSE_MAX_BOULE_QUI_SARRETE * Endless.delta);
			}
			if(tpsAnim > 10) {
				if(reset){
					position.y -= Stats.VITESSE_MAX_BOULE_QUI_SARRETE * Endless.delta;
					position.x -= Stats.VITESSE_MAX_BOULE_QUI_SARRETE * Endless.delta;
				} else {
					position.y -= Stats.VITESSE_MAX_BOULE_QUI_SARRETE * Endless.delta;
					position.x += Stats.VITESSE_MAX_BOULE_QUI_SARRETE * Endless.delta;
				}
			}
		}
		return true;
	}

	/**
	 * Exactement la m�me que dans la super classe mais �a �vite de faire des getter largeur hauteur...
	 */
	@Override
	public void afficher(SpriteBatch batch) {
		if(mort){
			explosion.draw(batch, Endless.delta);
		} else {
			batch.draw(AnimationBouleBleuRouge.getTexture(tpsAnim), position.x, position.y, LARGEUR, LARGEUR);
			tpsAnim += Endless.delta;
		}
	}

	@Override
	public void afficherSansParticules(SpriteBatch batch) {
		if(mort){
			batch.draw(AnimationExplosion1.getTexture(tpsAnimationExplosion), position.x, position.y, LARGEUR, LARGEUR);
			tpsAnimationExplosion += Endless.delta;
		} else {
			batch.draw(AnimationBouleBleuRouge.getTexture(tpsAnim), position.x, position.y, LARGEUR, LARGEUR);
			tpsAnim += Endless.delta;
		}
	}
	
	float tmpAngle = 0;
	@Override
	protected void tir() {
		if (!mort && tpsAnim > dernierTir + ArmesBouleBleu.CADENCETIR + CADENCETIR && position.y < CSG.HAUTEUR_ECRAN_PALLIER_3) {
			tmpVecteur.x = (VaisseauType1.position.x + VaisseauType1.DEMI_LARGEUR) - ArmesBouleBleu.DEMI_LARGEUR;
			tmpVecteur.y = (VaisseauType1.position.y + VaisseauType1.DEMI_LARGEUR) - ArmesBouleBleu.DEMI_LARGEUR;
			ArmesBouleBleu milieu = ArmesBouleBleu.pool.obtain();
			tmpAngle = tmpVecteur.sub(position).angle();
//			milieu.init(position.x + DEMI_LARGEUR - ArmesBouleBleu.DEMI_LARGEUR, position.y + DEMI_LARGEUR - ArmesBouleBleu.DEMI_LARGEUR, tmpAngle);
			dernierTir = tpsAnim;
			ArmesBouleBleu moinsDix = ArmesBouleBleu.pool.obtain();
//			moinsDix.init(position.x + DEMI_LARGEUR - ArmesBouleBleu.DEMI_LARGEUR, position.y + DEMI_LARGEUR - ArmesBouleBleu.DEMI_LARGEUR, tmpAngle - 10);
			ArmesBouleBleu plusDix = ArmesBouleBleu.pool.obtain();
//			plusDix.init(position.x + DEMI_LARGEUR - ArmesBouleBleu.DEMI_LARGEUR, position.y + DEMI_LARGEUR - ArmesBouleBleu.DEMI_LARGEUR, tmpAngle + 10);
		}
	}

	@Override
	public int getXp() {		return CoutsEnnemis.EnnemiBouleQuiSArreteNv3.COUT;	}
	
	@Override
	public int getHauteur() {		return LARGEUR;	}

	@Override
	public int getLargeur() {		return LARGEUR;	}

	@Override
	public int getDemiHauteur() {		return DEMI_LARGEUR;	}

	@Override
	public int getDemiLargeur() {		return DEMI_LARGEUR;	}

	@Override
	public Rectangle getRectangleCollision() {
		collision.set(position.x, position.y, LARGEUR, LARGEUR);
		return collision;
	}

	@Override
	public void invoquer() {
		liste.add(pool.obtain());
	}
}
