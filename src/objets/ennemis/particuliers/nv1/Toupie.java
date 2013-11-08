package objets.ennemis.particuliers.nv1;

import objets.Positionnement;
import objets.armes.ennemi.ArmeEnnemi;
import objets.armes.ennemi.BouleBleueRapide;
import objets.armes.typeTir.TireurBalayage;
import objets.armes.typeTir.Tirs;
import objets.ennemis.CoutsEnnemis;
import objets.ennemis.Ennemis;
import jeu.EndlessMode;
import jeu.Physique;
import jeu.Stats;
import menu.CSG;
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
	public static final float VITESSE = Stats.V_ENN_TOUPIE;
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
				direction.rotate(EndlessMode.delta * Stats.DEMI_V_ENN_TOUPIE);
			} else {
				direction.rotate(EndlessMode.delta * -Stats.DEMI_V_ENN_TOUPIE);
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
	public ArmeEnnemi getArme() {			return BouleBleueRapide.pool.obtain();	}

	@Override
	public void setProchainTir(float f) {
		prochainTir = f;
	}

	@Override
	public float getModifVitesse() {	return 0.010f;	}

	@Override
	public float getAngleTir() {			return 0;	}
	
	@Override
	public Vector2 getDirectionTir() {
		return direction;
	}
	
	@Override
	public Vector2 getPositionDuTir(int numeroTir) {
		TMP_POS.x = (position.x + DEMI_LARGEUR - BouleBleueRapide.DEMI_LARGEUR);
		TMP_POS.y = (position.y + DEMI_LARGEUR - BouleBleueRapide.DEMI_LARGEUR);
		return TMP_POS;
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
		LISTE.add(pool.obtain());
	}

	@Override
	protected int getPvMax() {
		return Stats.PV_TOUPIE;
	}
	
	@Override
	public float getDirectionY() {
		return direction.y;
	}
	
	@Override
	public float getDirectionX() {
		return direction.x;
	}
	@Override
	protected String getLabel() {			return getClass().toString();	}
}
