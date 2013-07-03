package vaisseaux.ennemis.particuliers.nv3;

import jeu.Endless;
import jeu.Physique;
import jeu.Stats;
import menu.CSG;
import vaisseaux.armes.ArmeBalayageEnnemiToupie;
import vaisseaux.armes.Armes;
import vaisseaux.ennemis.Ennemis;
import vaisseaux.ennemis.TypesEnnemis;
import assets.SoundMan;
import assets.animation.AnimationEnnemiToupie;
import assets.animation.AnimationExplosion1;
import assets.particules.ParticulesExplosionPetite;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

public class EnnemiToupieNv3 extends Ennemis{
	
	// ** ** caracteristiques g�n�rales
	public static final int LARGEUR= CSG.LARGEUR_ECRAN / 13;
	public static final int DEMI_LARGEUR = LARGEUR/2;
	public static final int HAUTEUR = LARGEUR + DEMI_LARGEUR;
	private static final int DEMI_HAUTEUR = HAUTEUR / 2; 
	private Vector2 direction = new Vector2(0,-1);
	public static Pool<EnnemiToupieNv3> pool = Pools.get(EnnemiToupieNv3.class);
	// ******************************************** T I R ********************************************************************
	public float qdEnnemiATire = 0;
	public static final float CADENCE_TIR = .30f;
	private float angle = 0;
	// ** ** animations
	protected float tpsAnimation;
	protected float tpsAnimationExplosion;
	private ParticulesExplosionPetite explosion;
	// ** ** caracteristiques variables.
	private boolean versDroite = true;
	private boolean aGaucheEcran;
	private float angleAffichage = 270;

	public EnnemiToupieNv3(float posX, float posY, int pv) {
		super(posX, posY, pv);
	}

	/**
	 * Contructeur sans argument, appel� par le pool
	 */
	public EnnemiToupieNv3() {
		super(Physique.getEmplacementX(DEMI_LARGEUR),	CSG.HAUTEUR_ECRAN + HAUTEUR, Stats.PVMAX_TOUPIE3);
		if (position.x + DEMI_LARGEUR < CSG.DEMI_LARGEUR_ZONE_JEU) aGaucheEcran = true;
		else aGaucheEcran = false;
	}
	
	/**
	 * Initialise l'ennemi
	 */
	public void init() {
		if (CSG.profil.particules && explosion==null) explosion = ParticulesExplosionPetite.pool.obtain();
		else tpsAnimationExplosion = 0;
	}

	@Override
	public void reset() {
		angleAffichage = 270;
		direction.x = 0;
		direction.y = -1;
		position.x = Physique.getEmplacementX(DEMI_LARGEUR);
		position.y = CSG.HAUTEUR_ECRAN + HAUTEUR;
		tpsAnimation = 0;
		qdEnnemiATire = 0;
		mort = false;
		pv = Stats.PVMAX_TOUPIE3;
		angle = 30;
		if (position.x + DEMI_LARGEUR < CSG.DEMI_LARGEUR_ZONE_JEU) aGaucheEcran = true;
		else aGaucheEcran = false;
	}

	@Override
	protected void mort() {
		SoundMan.playBruitage(SoundMan.explosiontoupie);
		if(CSG.profil.particules){
			explosion = ParticulesExplosionPetite.pool.obtain();
			explosion.setPosition(position.x + DEMI_LARGEUR, position.y + DEMI_HAUTEUR);
			explosion.start();
		} else {
			tpsAnimationExplosion = 0;
		}
	}

	@Override
	public void afficher(SpriteBatch batch) {
		if (mort) {
			explosion.draw(batch, Endless.delta);
		} else {
			batch.draw(AnimationEnnemiToupie.getTexture(tpsAnimation), position.x, position.y,	DEMI_LARGEUR,DEMI_LARGEUR, LARGEUR, LARGEUR, 1.3f, 1, angleAffichage, false);
			tpsAnimation += Endless.delta;
		}
	}
	@Override
	public void afficherSansParticules(SpriteBatch batch) {
		if(mort){
			batch.draw(AnimationExplosion1.getTexture(tpsAnimationExplosion), position.x, position.y, LARGEUR, HAUTEUR);
			tpsAnimationExplosion += Endless.delta;
		}
		else{
			batch.draw(AnimationEnnemiToupie.getTexture(tpsAnimation), position.x, position.y,	DEMI_LARGEUR,DEMI_LARGEUR, LARGEUR, LARGEUR, 1.3f, 1, angleAffichage, false);
			tpsAnimation += Endless.delta;
		}
	}

	@Override
	public boolean mouvementEtVerif() {
		if ((mort && explosion.isComplete()) | Physique.toujoursAfficher(position, HAUTEUR, LARGEUR) == false){
			pool.free(this);
			if (explosion != null) explosion.free();
			return false;
		}
		if (position.y < CSG.HAUTEUR_ECRAN_PALLIER_7) { // tout droit
			if (aGaucheEcran) {
				direction.rotate(Endless.delta * Stats.DEMI_VITESSE_TOUPIE);
			} else {
				direction.rotate(Endless.delta * -Stats.DEMI_VITESSE_TOUPIE);
			}
			angleAffichage = direction.angle();
		}
		position.x += direction.x * Stats.VITESSE_TOUPIE * Endless.delta;
		position.y += direction.y * Stats.VITESSE_TOUPIE * Endless.delta;
		return true;
	}
	@Override
	public boolean mouvementEtVerifSansParticules() {
		if (mort & tpsAnimationExplosion > AnimationExplosion1.tpsTotalAnimationExplosion1 | Physique.toujoursAfficher(position, HAUTEUR, LARGEUR) == false){
			pool.free(this);
			return false;
		}
		if (position.y < CSG.HAUTEUR_ECRAN_PALLIER_7) { // tout droit
			if (aGaucheEcran) {
				direction.rotate(Endless.delta * Stats.DEMI_VITESSE_TOUPIE);
			} else {
				direction.rotate(Endless.delta * -Stats.DEMI_VITESSE_TOUPIE);
			}
			angleAffichage = direction.angle();
		}
		position.x += direction.x * Stats.VITESSE_TOUPIE * Endless.delta;
		position.y += direction.y * Stats.VITESSE_TOUPIE * Endless.delta;
		return true;
	}

	

	@Override
	protected void tir() {
		if (tpsAnimation > qdEnnemiATire + CADENCE_TIR){
			if (versDroite){
				angle += Endless.delta * 500;
				if (angle > 60) versDroite = false;
			} else {
				angle -= Endless.delta * 500;
				if (angle < -60) versDroite = true;
			}
			ArmeBalayageEnnemiToupie a = ArmeBalayageEnnemiToupie.pool.obtain();
			a.init(position.x + DEMI_LARGEUR - ArmeBalayageEnnemiToupie.DEMI_LARGEUR, position.y + DEMI_HAUTEUR - ArmeBalayageEnnemiToupie.DEMI_HAUTEUR,
					direction.x, direction.y, angle);
			Armes.listeTirsDesEnnemis.add(a);
			
			ArmeBalayageEnnemiToupie b = ArmeBalayageEnnemiToupie.pool.obtain();
			b.init(position.x + DEMI_LARGEUR - ArmeBalayageEnnemiToupie.DEMI_LARGEUR, position.y + DEMI_HAUTEUR - ArmeBalayageEnnemiToupie.DEMI_HAUTEUR,
					direction.x, direction.y, -angle);
			Armes.listeTirsDesEnnemis.add(b);
			if (tpsAnimation > qdEnnemiATire + CADENCE_TIR)				qdEnnemiATire = tpsAnimation;
		}
	}

	@Override
	public Rectangle getRectangleCollision() {
		collision.set(position.x, position.y, LARGEUR, HAUTEUR);
		return collision;
	}

	@Override
	public int getXp() {
		return TypesEnnemis.EnnemiToupieNv3.COUT;
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
	public int getDemiHauteur() {
		return DEMI_HAUTEUR;
	}

	@Override
	public int getDemiLargeur() {
		return DEMI_LARGEUR;
	}
	
}
