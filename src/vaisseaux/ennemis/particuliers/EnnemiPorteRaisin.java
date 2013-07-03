package vaisseaux.ennemis.particuliers;

import jeu.Endless;
import jeu.Physique;
import jeu.Stats;
import menu.CSG;
import vaisseaux.armes.ArmeBouleEnergie;
import vaisseaux.armes.Armes;
import vaisseaux.armes.ArmesBouleVerte;
import vaisseaux.ennemis.Ennemis;
import vaisseaux.ennemis.TypesEnnemis;
import vaisseaux.tirs.Tirs;
import assets.AssetMan;
import assets.SoundMan;
import assets.animation.AnimationExplosion1;
import assets.particules.ParticulesExplosionPetite;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

/**
 * Ennemi de base, g�re son pool, va tout droit et ni tire pas.
 * @author Julien
 *
 */
public class EnnemiPorteRaisin extends Ennemis implements TireurPlusieurFois {
	
	// ** ** caracteristiques g�n�rales
	public static final int LARGEUR= CSG.LARGEUR_ECRAN / 7;
	public static final int DEMI_LARGEUR = LARGEUR/2;
	public static final int HAUTEUR = LARGEUR + DEMI_LARGEUR/2;
	private static final int DEMI_HAUTEUR = HAUTEUR / 2; 
	public static Pool<EnnemiPorteRaisin> pool = Pools.get(EnnemiPorteRaisin.class);
	public static final Tirs TIR = new Tirs(.3f);
	// ** ** animations
	protected float maintenant;
	protected float tpsAnimationExplosion;
	private float prochainTir = 3, angleTir = 0;
	private ParticulesExplosionPetite explosion;
	private int numeroTir = 3;

	/** 
	 * Appel� par les sous classes
	 * @param posX
	 * @param posY
	 * @param dirX
	 * @param dirY
	 * @param pv
	 */
	public EnnemiPorteRaisin(float posX, float posY, int pv) {
		super(posX, posY, pv);
	}

	/**
	 * Contructeur sans argument, appel� par le pool
	 */
	public EnnemiPorteRaisin() {
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
		if(CSG.profil.particules & explosion == null)	explosion = ParticulesExplosionPetite.pool.obtain();
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
			maintenant += Endless.delta;
		}
	}
	@Override
	public void afficherSansParticules(SpriteBatch batch) {
		if (mort) {
			batch.draw(AnimationExplosion1.getTexture(tpsAnimationExplosion), position.x, position.y, LARGEUR, HAUTEUR);
			tpsAnimationExplosion += Endless.delta;
		} else {
			if(pv > Stats.PVMAX_PORTE_RAISIN_AMOCHE)	batch.draw(AssetMan.porteraisin, position.x, position.y, LARGEUR, HAUTEUR);
			else	batch.draw(AssetMan.porteraisinamoche, position.x, position.y, LARGEUR, HAUTEUR);
			maintenant += Endless.delta;
		}
	}

	@Override
	public boolean mouvementEtVerif() {
		if( (mort && explosion.isComplete()) | Physique.toujoursAfficher(position, HAUTEUR, LARGEUR) == false){
			pool.free(this);
			if (explosion != null) explosion.free();
			return false;
		}
		position.y -= (Stats.VITESSE_PORTE_RAISIN * Endless.delta);
		return true;
	}
	@Override
	public boolean mouvementEtVerifSansParticules() {
		if( (mort & tpsAnimationExplosion > AnimationExplosion1.tpsTotalAnimationExplosion1) | Physique.toujoursAfficher(position, HAUTEUR, LARGEUR) == false){
			pool.free(this);
			return false;
		}
		position.y -= (Stats.VITESSE_PORTE_RAISIN * Endless.delta);
		return true;
	}
	
	@Override
	protected void tir() {
		TIR.tirEnRafaleGaucheEtDroite(this, 4, mort, maintenant, prochainTir);
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

	@Override
	public Armes getArme() {			return ArmeBouleEnergie.pool.obtain();	}

	@Override
	public void setProchainTir(float f) {
		angleTir++;
		prochainTir = f;
	}

	@Override
	public float getModifVitesse() {	return 1;	}

	@Override
	public float getAngleTir() {			return angleTir;	}
	
	@Override
	public Vector2 getDirectionTir() {
		tmpDir.x = 0;
		tmpDir.y = -1;
		tmpDir.rotate(angleTir++);
		return tmpDir;
	}
	
	@Override
	public Vector2 getPositionDuTir(int numeroTir) {
		tmpPos.x = (position.x + DEMI_LARGEUR - ArmeBouleEnergie.DEMI_LARGEUR);// + (direction.x * 16);
		tmpPos.y = (position.y + DEMI_LARGEUR - ArmeBouleEnergie.DEMI_LARGEUR);//+ (direction.y * 16);
		return tmpPos;
	}

	@Override
	public int getNumeroTir() {
		return numeroTir;
	}

	@Override
	public void addNombresTirs(int i) {
		numeroTir += i;
	}
	
}
