package vaisseaux.ennemis.particuliers.nv1;

import jeu.Endless;
import jeu.Physique;
import jeu.Stats;
import menu.CSG;
import vaisseaux.Positionnement;
import vaisseaux.armes.Armes;
import vaisseaux.armes.ArmesBouleBleu;
import vaisseaux.armes.ArmesBouleVerte;
import vaisseaux.armes.typeTir.Tireur;
import vaisseaux.armes.typeTir.Tirs;
import vaisseaux.ennemis.CoutsEnnemis;
import vaisseaux.ennemis.Ennemis;
import assets.SoundMan;
import assets.animation.AnimationBouleBleuRouge;
import assets.animation.AnimationExplosion1;
import assets.particules.ParticulesExplosionPetite;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;


public class BouleQuiSArrete extends Ennemis implements Tireur {
	
	// ** ** caracteristiques g�n�rales
	public static final int LARGEUR= CSG.LARGEUR_ECRAN / 17;
	public static final int DEMI_LARGEUR = LARGEUR/2;
	public static final float CADENCETIR = 1.6f;
	public static final Tirs tir = new Tirs(CADENCETIR);
	// ** ** caracteristiques variables.
	private float prochainTir = .1f;
	public static Pool<BouleQuiSArrete> pool = Pools.get(BouleQuiSArrete.class);
	// ** ** animations 
	private ParticulesExplosionPetite explosion;
	private boolean reset = false;
	
	@Override
	public void reset() {
		position.x = Positionnement.getEmplacementX(DEMI_LARGEUR);
		position.y = CSG.HAUTEUR_ECRAN + LARGEUR;
		mort = false;
		pv = Stats.PVMAX_BOULE_QUI_SARRETE;
		prochainTir = .2f;
		reset = true;
		maintenant = 0;
	}
	
	@Override
	protected Sound getSonExplosion() {
		return SoundMan.explosionboule;
	}

	public BouleQuiSArrete() {
		super(Positionnement.getEmplacementX(DEMI_LARGEUR), CSG.HAUTEUR_ECRAN + LARGEUR, Stats.PVMAX_BOULE_QUI_SARRETE);
	}

	public void init() {
		if(CSG.profil.particules & explosion == null)	explosion = ParticulesExplosionPetite.pool.obtain();
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

	@Override
	protected TextureRegion getTexture() {
		return AnimationBouleBleuRouge.getTexture(maintenant);
	}
	
	@Override
	protected void tir() {
		tir.tirVersJoueur(this, mort, maintenant, prochainTir);
	}

	@Override
	public int getXp() {		return CoutsEnnemis.EnnemiBouleQuiSArrete.COUT;	}
	
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
	
	@Override
	public void invoquer() {
		liste.add(pool.obtain());
	}

}
