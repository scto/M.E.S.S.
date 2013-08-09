package vaisseaux.ennemis.particuliers.nv3;

import jeu.Endless;
import jeu.Physique;
import jeu.Stats;
import menu.CSG;
import vaisseaux.armes.ArmeBouleEnergie;
import vaisseaux.ennemis.Ennemis;
import vaisseaux.ennemis.TypesEnnemis;
import assets.AssetMan;
import assets.SoundMan;
import assets.animation.AnimationExplosion1;
import assets.particules.ParticulesExplosionPetite;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

/**
 * Ennemi de base, g�re son pool, va tout droit et ni tire pas.
 * @author Julien
 *
 */
public class EnnemiPorteRaisinNv3 extends Ennemis{
	
	// ** ** caracteristiques g�n�rales
	public static final int LARGEUR= CSG.LARGEUR_ECRAN / 7;
	public static final int DEMI_LARGEUR = LARGEUR/2;
	public static final int HAUTEUR = LARGEUR + DEMI_LARGEUR/2;
	private static final int DEMI_HAUTEUR = HAUTEUR / 2; 
	public static Pool<EnnemiPorteRaisinNv3> pool = Pools.get(EnnemiPorteRaisinNv3.class);
	// ** ** animations
	protected float tpsAnimation;
	protected float tpsAnimationExplosion;
	private float dernierTir = 3;
	private ParticulesExplosionPetite explosion;
	private boolean tir1PasEncoreFait = true, tir2PasEncoreFait = true, sens = true;
	private int numeroTir = 3;

	/** 
	 * Appel� par les sous classes
	 * @param posX
	 * @param posY
	 * @param dirX
	 * @param dirY
	 * @param pv
	 */
	public EnnemiPorteRaisinNv3(float posX, float posY, int pv) {
		super(posX, posY, pv);
	}

	/**
	 * Contructeur sans argument, appel� par le pool
	 */
	public EnnemiPorteRaisinNv3() {
		super(Physique.getEmplacementX(DEMI_LARGEUR),	CSG.HAUTEUR_ECRAN + HAUTEUR, Stats.PVMAX_PORTE_RAISIN);
	}
	
	@Override
	protected void mort() {
		SoundMan.playBruitage(SoundMan.explosionkinder);
		if (CSG.profil.particules) {
			explosion = ParticulesExplosionPetite.pool.obtain();
			explosion.setPosition(position.x + DEMI_LARGEUR, position.y + DEMI_HAUTEUR);
			explosion.start();
		} else {
			tpsAnimationExplosion = 0;
		}
	}
	
	/**
	 * Initialise l'ennemi
	 */
	public void init() {
		tpsAnimationExplosion = 0;
		if (CSG.profil.particules & explosion == null)	explosion = ParticulesExplosionPetite.pool.obtain();
	}

	@Override
	public void reset() {
		position.x = Physique.getEmplacementX(DEMI_LARGEUR);
		position.y = CSG.HAUTEUR_ECRAN + HAUTEUR;
		mort = false;
		pv = Stats.PVMAX_PORTE_RAISIN;
	}

	@Override
	public void afficher(SpriteBatch batch) {
		if (mort) {
			explosion.draw(batch, Endless.delta);
		} else {
			if (pv > Stats.PVMAX_PORTE_RAISIN_AMOCHE)	batch.draw(AssetMan.porteraisin, position.x, position.y, LARGEUR, HAUTEUR);
			else	batch.draw(AssetMan.porteraisinamoche, position.x, position.y, LARGEUR, HAUTEUR);
			tpsAnimation += Endless.delta;
		}
	}
	@Override
	public void afficherSansParticules(SpriteBatch batch) {
		if (mort) {
			batch.draw(AnimationExplosion1.getTexture(tpsAnimationExplosion), position.x, position.y, LARGEUR, HAUTEUR);
			tpsAnimationExplosion += Endless.delta;
		} else {
			if (pv > Stats.PVMAX_PORTE_RAISIN_AMOCHE)	batch.draw(AssetMan.porteraisin, position.x, position.y, LARGEUR, HAUTEUR);
			else	batch.draw(AssetMan.porteraisinamoche, position.x, position.y, LARGEUR, HAUTEUR);
			tpsAnimation += Endless.delta;
		}
	}

	@Override
	public boolean mouvementEtVerif() {
		if( (mort && explosion.isComplete()) || Physique.toujoursAfficher(position, HAUTEUR, LARGEUR) == false){
			pool.free(this);
			if (explosion != null) explosion.free();
			return false;
		}
		position.y -= (Stats.VITESSE_PORTE_RAISIN * Endless.delta);
		return true;
	}
	@Override
	public boolean mouvementEtVerifSansParticules() {
		if( (mort && tpsAnimationExplosion > AnimationExplosion1.tpsTotalAnimationExplosion1) || Physique.toujoursAfficher(position, HAUTEUR, LARGEUR) == false){
			pool.free(this);
			return false;
		}
		position.y -= (Stats.VITESSE_PORTE_RAISIN * Endless.delta);
		return true;
	}
	
	@Override
	protected void tir() {
		if (!mort && tpsAnimation > dernierTir + ArmeBouleEnergie.CADENCETIR
				|| (tpsAnimation > (dernierTir - .2f) + ArmeBouleEnergie.CADENCETIR && tir1PasEncoreFait)
				|| (tpsAnimation > (dernierTir - .4f) + ArmeBouleEnergie.CADENCETIR && tir2PasEncoreFait)) {
			
			ArmeBouleEnergie gauche = new ArmeBouleEnergie();
//			gauche.init(position.x + DEMI_LARGEUR - ArmeBouleEnergie.DEMI_LARGEUR, position.y, true, numeroTir);
			if (sens) numeroTir += 3;
			else numeroTir -= 3;
			ArmeBouleEnergie a = new ArmeBouleEnergie();
//			a.init(position.x + DEMI_LARGEUR - ArmeBouleEnergie.DEMI_LARGEUR, position.y, false, numeroTir);
			if (sens) numeroTir += 3;
			else numeroTir -= 3;
			if ((tpsAnimation > (dernierTir - .2f) + ArmeBouleEnergie.CADENCETIR))
				tir1PasEncoreFait = false;
			if ((tpsAnimation > (dernierTir - .4f) + ArmeBouleEnergie.CADENCETIR))
				tir2PasEncoreFait = false;
			if (tpsAnimation > dernierTir + ArmeBouleEnergie.CADENCETIR){
				dernierTir = tpsAnimation - .4f;
				tir1PasEncoreFait = true;
				tir2PasEncoreFait = true;
			}
			if (numeroTir > 64 || numeroTir < 5) sens = !sens;
		}
	}


	@Override
	public Rectangle getRectangleCollision() {
		collision.set(position.x, position.y, LARGEUR, HAUTEUR);
		return collision;
	}

	@Override
	public int getXp() {
		return TypesEnnemis.EnnemiPorteRaisin.COUT;
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
