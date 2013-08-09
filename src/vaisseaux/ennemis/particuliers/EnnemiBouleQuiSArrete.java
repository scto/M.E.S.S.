package vaisseaux.ennemis.particuliers;

import jeu.Endless;
import jeu.Physique;
import jeu.Stats;
import menu.CSG;
import vaisseaux.armes.Armes;
import vaisseaux.armes.ArmesBouleBleu;
import vaisseaux.armes.ArmesBouleVerte;
import vaisseaux.armes.typeTir.Tirs;
import vaisseaux.ennemis.Ennemis;
import vaisseaux.ennemis.TypesEnnemis;
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


public class EnnemiBouleQuiSArrete extends Ennemis implements Tireur {
	
	// ** ** caracteristiques g�n�rales
	public static final int LARGEUR= CSG.LARGEUR_ECRAN / 17;
	public static final int DEMI_LARGEUR = LARGEUR/2;
	public static final float CADENCETIR = 1.6f;
	public static final Tirs tir = new Tirs(CADENCETIR);
	// ** ** caracteristiques variables.
	private float prochainTir = .1f;
	public static Pool<EnnemiBouleQuiSArrete> pool = Pools.get(EnnemiBouleQuiSArrete.class);
	// ** ** animations 
	protected float tpsAnimationExplosion = 0;
	private float maintenant = 0;
	private Vector2 tmpVecteur = new Vector2();
	private ParticulesExplosionPetite explosion;
	private boolean reset = false;
	
	@Override
	public void reset() {
		position.x = Physique.getEmplacementX(DEMI_LARGEUR);
		position.y = CSG.HAUTEUR_ECRAN + LARGEUR;
		mort = false;
		pv = Stats.PVMAX_BOULE_QUI_SARRETE;
		prochainTir = .2f;
		reset = true;
		maintenant = 0;
	}
	
	@Override
	protected void mort() {
		SoundMan.playBruitage(SoundMan.explosionboule);
		if(CSG.profil.particules){
			explosion = ParticulesExplosionPetite.pool.obtain();
			explosion.start();
			explosion.setPosition(position.x + DEMI_LARGEUR, position.y + DEMI_LARGEUR);
		} else {
			tpsAnimationExplosion = 0;
		}
	}

	public EnnemiBouleQuiSArrete() {
		super(Physique.getEmplacementX(DEMI_LARGEUR), CSG.HAUTEUR_ECRAN + LARGEUR, Stats.PVMAX_BOULE_QUI_SARRETE);
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
			if(maintenant > 10) {
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
		if( (mort && tpsAnimationExplosion > AnimationExplosion1.tpsTotalAnimationExplosion1) | Physique.toujoursAfficher(position, LARGEUR) == false){
			pool.free(this);
			return false;
		} else { // Si on a pass� le deuxi�me pallier les ailes sont repli�es
			if (position.y < CSG.HAUTEUR_ECRAN_PALLIER_2) {
				// On ralentit
				if (position.y > CSG.HAUTEUR_ECRAN_PALLIER_3)	position.y += (-50 * Endless.delta);
			} else {
				position.y += (Stats.VITESSE_MAX_BOULE_QUI_SARRETE * Endless.delta);
			}
			if(maintenant > 10) {
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
			batch.draw(AnimationBouleBleuRouge.getTexture(maintenant), position.x, position.y, LARGEUR, LARGEUR);
			maintenant += Endless.delta;
		}
	}

	@Override
	public void afficherSansParticules(SpriteBatch batch) {
		if(mort){
			batch.draw(AnimationExplosion1.getTexture(tpsAnimationExplosion), position.x, position.y, LARGEUR, LARGEUR);
			tpsAnimationExplosion += Endless.delta;
		} else {
			batch.draw(AnimationBouleBleuRouge.getTexture(maintenant), position.x, position.y, LARGEUR, LARGEUR);
			maintenant += Endless.delta;
		}
	}
	
	@Override
	protected void tir() {
		tir.tirVersJoueur(this, mort, maintenant, prochainTir);
	}

	@Override
	public int getXp() {		return TypesEnnemis.EnnemiBouleQuiSArrete.COUT;	}
	
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
	public Armes getArme() {			return ArmesBouleBleu.pool.obtain();	}

	@Override
	public void setProchainTir(float f) {		prochainTir = f;	}

	@Override
	public float getModifVitesse() {	return 1;	}

	@Override
	public Vector2 getPositionDuTir(int numeroTir) {
		tmpPos.x = (position.x + DEMI_LARGEUR - ArmesBouleVerte.DEMI_LARGEUR);
		tmpPos.y = (position.y + DEMI_LARGEUR - ArmesBouleVerte.DEMI_LARGEUR);
		return tmpPos;
	}

}
