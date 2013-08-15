package vaisseaux.ennemis.particuliers;

import jeu.Endless;
import jeu.Physique;
import jeu.Stats;
import menu.CSG;
import vaisseaux.armes.Armes;
import vaisseaux.armes.ArmesBouleVerte;
import vaisseaux.armes.typeTir.TireurAngle;
import vaisseaux.armes.typeTir.Tirs;
import vaisseaux.ennemis.Ennemis;
import vaisseaux.ennemis.CoutsEnnemis;
import assets.SoundMan;
import assets.animation.AnimationEnnemiAileDeployee;
import assets.animation.AnimationExplosion1;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;


public class EnnemiAilesDeployees extends Ennemis implements TireurAngle {
	
	// ** ** caracteristiques g�n�rales
	public static final float LARGEUR= CSG.LARGEUR_ECRAN / 16;
	public static final float DEMI_LARGEUR = LARGEUR/2;
	public static final float CADENCETIR = 3.0f;
	public static final float VITESSE_ANGULAIRE = 100;
	public static final float CADENCE = 1.2f;
	public static final Tirs tir = new Tirs(CADENCE);
	// ** ** caracteristiques variables.
	private float maintenant = 0;
	private float prochainTir = 0;
	public static Pool<EnnemiAilesDeployees> pool = Pools.get(EnnemiAilesDeployees.class);
	// ** ** animations 
	protected float tpsAnimationExplosion = 0;
	private float tpsAnim;
	// ** ** autre
	private float angle;
	private Vector2 direction = new Vector2(-1,0);

	@Override
	protected Sound getSonExplosion() {
		return SoundMan.explosionpetittetechercheuse;
	}
	
	@Override
	public void reset() {
		mort = false;
		pv = Stats.PVMAX_AILES_DEPLOYEE;
		tpsAnim = 0;
	}

	public EnnemiAilesDeployees() {
		super((float) (Math.random() * CSG.LARGEUR_ECRAN - DEMI_LARGEUR), CSG.HAUTEUR_ECRAN + LARGEUR, Stats.PVMAX_AILES_DEPLOYEE);
	}

	@Override
	public boolean mouvementEtVerifSansParticules() {
		if ( (mort && tpsAnimationExplosion > AnimationExplosion1.tpsTotalAnimationExplosion1) | Physique.toujoursAfficher(position, (int)LARGEUR, (int)LARGEUR) == false) {
			pool.free(this);
			return false;
		} else {
			angle = Physique.mouvementTeteChercheuse(direction, position, Stats.VITESSE_MAX_AILES_DEPLOYEE, (int)LARGEUR, VITESSE_ANGULAIRE, (int)DEMI_LARGEUR);
		}
		return true;
	}

	/**
	 * Exactement la meme que dans la super classe mais ca evite de faire des getter largeur hauteur...
	 */
	@Override
	public void afficherSansParticules(SpriteBatch batch) {
		maintenant += Endless.delta;
		if (mort) {
			batch.draw(AnimationExplosion1.getTexture(tpsAnimationExplosion), position.x, position.y, LARGEUR, LARGEUR);
			tpsAnimationExplosion += Endless.delta;
		} else {
			batch.draw(AnimationEnnemiAileDeployee.getTexture(tpsAnim), position.x, position.y,	DEMI_LARGEUR,DEMI_LARGEUR,	LARGEUR, LARGEUR, 1,1, angle, false);
			tpsAnim += Endless.delta;
		}
	}
	
	@Override
	protected void tir() {
		tir.tirToutDroit(this, mort, maintenant, prochainTir);
	}

	@Override
	public int getXp() {		return CoutsEnnemis.EnnemiAilesDeployee.COUT;	}
	
	@Override
	public Rectangle getRectangleCollision() {
		collision.set(position.x, position.y, LARGEUR, LARGEUR);
		return collision;
	}


	public void setPosition(float x, float y) {
		position.x = x;
		position.y = y;
	}


	public void setAngle(int i) {		this.angle = i;	}


	public void lancer(float f, float g) {
		direction.x = f;
		direction.y = g;
	}
	
	@Override
	public int getHauteur() {			return (int)LARGEUR;	}
	@Override
	public int getLargeur() {			return (int)LARGEUR;	}
	@Override
	public int getDemiHauteur() {		return (int)DEMI_LARGEUR;	}
	@Override
	public int getDemiLargeur() {		return (int)DEMI_LARGEUR;	}
	@Override
	public Armes getArme() {			return ArmesBouleVerte.pool.obtain();	}
	@Override
	public void setProchainTir(float f) {		prochainTir = f;	}
	@Override
	public float getModifVitesse() {	return 1;	}
	@Override
	public float getAngleTir() {		return angle;	}
	@Override
	public Vector2 getDirectionTir() {	return direction;	}
	
	@Override
	public Vector2 getPositionDuTir(int numeroTir) {
		tmpPos.x = (position.x + DEMI_LARGEUR - ArmesBouleVerte.DEMI_LARGEUR) + (direction.x * 16);
		tmpPos.y = (position.y + DEMI_LARGEUR - ArmesBouleVerte.DEMI_LARGEUR)+ (direction.y * 16);
		return tmpPos;
	}
	
	@Override
	public void invoquer() {			liste.add(pool.obtain());	}
}
