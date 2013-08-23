package vaisseaux.ennemis.particuliers.nv1;

import jeu.Endless;
import jeu.Physique;
import jeu.Stats;
import menu.CSG;
import vaisseaux.Positionnement;
import vaisseaux.armes.Armes;
import vaisseaux.armes.ArmesBouleVerte;
import vaisseaux.armes.typeTir.TireurBalayage;
import vaisseaux.armes.typeTir.Tirs;
import vaisseaux.ennemis.CoutsEnnemis;
import vaisseaux.ennemis.Ennemis;
import assets.SoundMan;
import assets.animation.AnimationEnnemiToupie;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

public class Toupie extends Ennemis implements TireurBalayage {
	
	// ** ** caracteristiques g�n�rales
	public static final int LARGEUR= CSG.LARGEUR_ECRAN / 13;
	public static final int DEMI_LARGEUR = LARGEUR/2;
	public static final int HAUTEUR = LARGEUR + DEMI_LARGEUR;
	private static final int DEMI_HAUTEUR = HAUTEUR / 2;
	public static final float VITESSE = Stats.VITESSE_TOUPIE;
	public static final float CADENCE_TIR = .33f; 
	public static final Tirs TIR = new Tirs(CADENCE_TIR);
	private static int ecartTirs = 10;
	private Vector2 direction = new Vector2(0,-VITESSE);
	public static Pool<Toupie> pool = Pools.get(Toupie.class);
	// ******************************************** T I R ********************************************************************
	public float prochainTir = 0;
	// ** ** caracteristiques variables.
	private boolean versDroite = true;
	private boolean aGaucheEcran;
	private float angleAffichage = 270;
	private int numeroTir = 0;

	@Override
	protected void free() {
		pool.free(this);
	}
	
	/**
	 * Contructeur sans argument, appel� par le pool
	 */
	public Toupie() {
		super();
		Positionnement.hautLarge(position, getLargeur(), getHauteur());
		if (position.x + DEMI_LARGEUR < CSG.DEMI_LARGEUR_ZONE_JEU) aGaucheEcran = true;
		else aGaucheEcran = false;
	}

	@Override
	public void reset() {
		angleAffichage = 270;
		direction.x = 0;
		direction.y = -VITESSE;
		Positionnement.hautLarge(position, getLargeur(), getHauteur());
		prochainTir = 0;
		if (position.x + DEMI_LARGEUR < CSG.DEMI_LARGEUR_ZONE_JEU) aGaucheEcran = true;
		else aGaucheEcran = false;
		super.reset();
	}

	@Override
	protected Sound getSonExplosion() {		return SoundMan.explosiontoupie;	}

	@Override
	protected TextureRegion getTexture() {
		return AnimationEnnemiToupie.getTexture(maintenant);
	}
	
	@Override
	public float getAngle() {
		return angleAffichage + 90;
	}

	@Override
	public boolean mouvementEtVerif() {
		if (position.y < CSG.HAUTEUR_ECRAN_PALLIER_7) { // tout droit
			if (aGaucheEcran) {
				direction.rotate(Endless.delta * Stats.DEMI_VITESSE_TOUPIE);
			} else {
				direction.rotate(Endless.delta * -Stats.DEMI_VITESSE_TOUPIE);
			}
			angleAffichage = direction.angle();
		}
		Physique.mvtSansVerif(position, direction);
		return super.mouvementEtVerif();
	}

	

	@Override
	protected void tir() {
		TIR.tirBalayage(this, mort, maintenant, prochainTir);
	}

	@Override
	public int getXp() {
		return CoutsEnnemis.EnnemiToupie.COUT;
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
	public Armes getArme() {			return ArmesBouleVerte.pool.obtain();	}

	@Override
	public void setProchainTir(float f) {
		prochainTir = f;
	}

	@Override
	public float getModifVitesse() {	return 0.011f;	}

	@Override
	public float getAngleTir() {			return 0;	}
	
	@Override
	public Vector2 getDirectionTir() {
		return direction;
	}
	
	@Override
	public Vector2 getPositionDuTir(int numeroTir) {
		tmpPos.x = (position.x + DEMI_LARGEUR - ArmesBouleVerte.DEMI_LARGEUR);
		tmpPos.y = (position.y + DEMI_LARGEUR - ArmesBouleVerte.DEMI_LARGEUR);
		return tmpPos;
	}

	@Override
	public int getNumeroTir() {
		return numeroTir;
	}

	@Override
	public int getNombreTirsAvantChangement() {
		return 5;
	}

	@Override
	public float getEcartTirs() {
		return ecartTirs;
	}

	@Override
	public void addNombresTirs(int i) {
		numeroTir += i;
	}

	@Override
	public boolean getSens() {
		return versDroite;
	}

	@Override
	public void setSens(boolean b) {
		versDroite = b;
	}
	
	@Override
	public void invoquer() {
		liste.add(pool.obtain());
	}

	@Override
	protected int getPvMax() {
		return Stats.PVMAX_TOUPIE;
	}
	
	@Override
	public float getDirectionY() {
		return direction.y;
	}
	
	@Override
	public float getDirectionX() {
		return direction.x;
	}
}
