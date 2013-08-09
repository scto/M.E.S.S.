package vaisseaux.ennemis.particuliers.nv3;

import jeu.Endless;
import jeu.Physique;
import jeu.Stats;
import menu.CSG;
import vaisseaux.armes.ArmeBalayageEnnemiQuiTourne;
import vaisseaux.armes.Armes;
import vaisseaux.ennemis.Ennemis;
import vaisseaux.ennemis.CoutsEnnemis;
import assets.SoundMan;
import assets.animation.AnimationEnnemiTourne;
import assets.animation.AnimationExplosion1;
import assets.particules.ParticulesExplosionPetite;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

public class EnnemiQuiTourneNv3 extends Ennemis{
	
	// ** ** caracteristiques g�n�rales
	public static final int LARGEUR= CSG.LARGEUR_ECRAN / 15;
	public static final int DEMI_LARGEUR = LARGEUR / 2;
	private Vector2 direction = new Vector2(0,-1);
	public static Pool<EnnemiQuiTourneNv3> pool = Pools.get(EnnemiQuiTourneNv3.class);
	// ******************************************** T I R ********************************************************************
	private boolean tir1PasEncoreFait = true;
	private boolean tir2PasEncoreFait = true;
	public float qdEnnemiATire = 0;
	public static final float CADENCE_TIR = .6f;
	// ** ** animations
	protected float tpsAnimation;
	protected float tpsAnimationExplosion;
	private ParticulesExplosionPetite explosion;
	// ** ** caracteristiques variables.

	public EnnemiQuiTourneNv3(float posX, float posY, int pv) {
		super(posX, posY, pv);
	}

	/**
	 * Contructeur sans argument, appel� par le pool
	 */
	public EnnemiQuiTourneNv3() {
		super(Physique.getEmplacementX(DEMI_LARGEUR)- CSG.LARGEUR_BORD/2,	CSG.HAUTEUR_ECRAN + LARGEUR, Stats.PVMAX_QUI_TOURNE3);
		qdEnnemiATire = 2;
	}
	
	/**
	 * Initialise l'ennemi
	 */
	public void init() {
		if (CSG.profil.particules & explosion==null) explosion = ParticulesExplosionPetite.pool.obtain();
		else tpsAnimationExplosion = 0;
	}

	@Override
	public void reset() {
		position.x = Physique.getEmplacementX(DEMI_LARGEUR) - CSG.LARGEUR_BORD/2;
		position.y = CSG.HAUTEUR_ECRAN + LARGEUR;
		direction.x = 0;
		direction.y = 1;
		tpsAnimation = 0;
		tir1PasEncoreFait = true;
		tir2PasEncoreFait = true;
		qdEnnemiATire = 0;
		mort = false;
		pv = Stats.PVMAX_QUI_TOURNE3;
		qdEnnemiATire = 3;
	}

	@Override
	protected void mort() {
		SoundMan.playBruitage(SoundMan.explosionPetite);
		if (CSG.profil.particules){
			explosion = ParticulesExplosionPetite.pool.obtain();
			explosion.setPosition(position.x + DEMI_LARGEUR, position.y + DEMI_LARGEUR);
			explosion.start();
		} else {
			tpsAnimationExplosion = 0;
		}
	}

	@Override
	public void afficher(SpriteBatch batch) {
		if (mort){
			explosion.draw(batch, Endless.delta);
		} else {
			batch.draw(AnimationEnnemiTourne.getTexture(tpsAnimation), position.x, position.y, LARGEUR, LARGEUR);
			tpsAnimation += Endless.delta;
		}
	}
	@Override
	public void afficherSansParticules(SpriteBatch batch) {
		if (mort){
			batch.draw(AnimationExplosion1.getTexture(tpsAnimationExplosion), position.x, position.y, LARGEUR, LARGEUR);
			tpsAnimationExplosion += Endless.delta;
		} else {
			batch.draw(AnimationEnnemiTourne.getTexture(tpsAnimation), position.x, position.y, LARGEUR, LARGEUR);
			tpsAnimation += Endless.delta;
		}
	}

	@Override
	public boolean mouvementEtVerif() {
		if ((mort && explosion.isComplete()) || Physique.toujoursAfficher(position, LARGEUR) == false){
			pool.free(this);
			if (explosion != null) explosion.free();
			return false;
		}
		position.y += (CSG.DEMI_HAUTEUR_ECRAN - position.y) * (Endless.delta);
		if (tpsAnimation < 10) direction.rotate(Endless.delta * Stats.VITESSE_QUI_TOURNE3);
		position.x += direction.x * Stats.VITESSE_QUI_TOURNE3 * Endless.delta;
		position.y += direction.y * Stats.DEMI_VITESSE_QUI_TOURNE3 * Endless.delta;
		return true;
	}
	@Override
	public boolean mouvementEtVerifSansParticules() {
		if (mort && tpsAnimationExplosion > AnimationExplosion1.tpsTotalAnimationExplosion1 || Physique.toujoursAfficher(position, LARGEUR) == false){
			pool.free(this);
			return false;
		}
		position.y += (CSG.DEMI_HAUTEUR_ECRAN - position.y) * (Endless.delta/2);
		if (tpsAnimation < 10) direction.rotate(Endless.delta * Stats.VITESSE_QUI_TOURNE3);
		position.x += direction.x * Stats.VITESSE_QUI_TOURNE3 * Endless.delta;
		position.y += direction.y * Stats.DEMI_VITESSE_QUI_TOURNE3 * Endless.delta;
		return true;
	}

	

	@Override
	protected void tir() {
		if (tpsAnimation > qdEnnemiATire + CADENCE_TIR || (tpsAnimation > (qdEnnemiATire - .1f) + CADENCE_TIR && tir1PasEncoreFait) ||
				(tpsAnimation > (qdEnnemiATire - .2f) + CADENCE_TIR && tir2PasEncoreFait)) { 
			ArmeBalayageEnnemiQuiTourne a = ArmeBalayageEnnemiQuiTourne.pool.obtain();
//			a.init(position.x, position.y, direction.x, direction.y, true, direction.angle());
			Armes.listeTirsDesEnnemis.add(a);
			if((tpsAnimation > (qdEnnemiATire - .1f) + CADENCE_TIR))
				tir1PasEncoreFait = false;
			if((tpsAnimation > (qdEnnemiATire - .2f) + CADENCE_TIR))
				tir2PasEncoreFait = false;
			if(tpsAnimation > qdEnnemiATire + CADENCE_TIR){
				qdEnnemiATire = tpsAnimation - .05f;
				tir1PasEncoreFait = true;
				tir2PasEncoreFait = true;
			}
		}
	}

	@Override
	public Rectangle getRectangleCollision() {
		collision.set(position.x, position.y, LARGEUR, LARGEUR);
		return collision;
	}

	@Override
	public int getXp() {
		return CoutsEnnemis.EnnemiQuiTourne.COUT;
	}

	@Override
	public int getHauteur() {
		return LARGEUR;
	}

	@Override
	public int getLargeur() {
		return LARGEUR;
	}

	@Override
	public int getDemiHauteur() {
		return DEMI_LARGEUR;
	}

	@Override
	public int getDemiLargeur() {
		return DEMI_LARGEUR;
	}
	
	@Override
	public void invoquer() {		liste.add(pool.obtain());	}
}
