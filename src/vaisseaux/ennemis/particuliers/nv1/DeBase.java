package vaisseaux.ennemis.particuliers.nv1;

import jeu.Endless;
import jeu.Stats;
import menu.CSG;
import vaisseaux.PatternHorizontalPositionnable;
import vaisseaux.Positionnement;
import vaisseaux.ennemis.CoutsEnnemis;
import vaisseaux.ennemis.Ennemis;
import assets.SoundMan;
import assets.animation.Anim;
import assets.particules.ParticulesExplosionPetite;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

/**
 * Ennemi de base, gere son pool, va tout droit et ni tire pas.
 * @author Julien
 */
public class DeBase extends Ennemis implements PatternHorizontalPositionnable {
	
	// ** ** caracteristiques generales
	public static final int LARGEUR= CSG.LARGEUR_ECRAN / 15;
	public static final int DEMI_LARGEUR = LARGEUR/2;
	public static final int HAUTEUR = LARGEUR + DEMI_LARGEUR;
	private static final int DEMI_HAUTEUR = HAUTEUR / 2;
	private static final int MAX_ENNEMIS_LIGNE = 4; 
	public static Pool<DeBase> pool = Pools.get(DeBase.class);
	protected ParticulesExplosionPetite explosion;
	// ** ** moins al�atoire
	private static int nbEnnemisAvant = 0;
	private static float posXInitiale;
	
	/**
	 * Contructeur sans argument, appel� par le pool
	 */
	public DeBase() {
		super();
		Positionnement.hautLarge(position, getLargeur(), getHauteur());
	}

	@Override
	public boolean mouvementEtVerif() {
		position.y -= (getVitesse() * Endless.delta);
		return super.mouvementEtVerif();
	}
	
	@Override
	protected float getVitesse() {
		return Stats.VITESSE_MAX_DE_BASE;
	}
	
	@Override
	public void invoquer() {
		liste.add(pool.obtain());
		if (Endless.level == 2) {
			liste.add(pool.obtain());
			liste.add(pool.obtain());
			liste.add(pool.obtain());
			liste.add(pool.obtain());
		}
	}

	@Override
	public void incNbEnnemisAvant() {
		nbEnnemisAvant++;
		if (nbEnnemisAvant > MAX_ENNEMIS_LIGNE)
			nbEnnemisAvant = 0;
	}

	@Override
	protected Sound getSonExplosion() {		return SoundMan.explosiontoupie;	}
	@Override
	public int getXp() {					return CoutsEnnemis.DeBase.COUT;	}
	@Override
	public int getHauteur() {				return HAUTEUR;	}
	@Override
	public int getLargeur() {				return LARGEUR;	}
	@Override
	public int getDemiHauteur() {			return DEMI_HAUTEUR;	}
	@Override
	public int getDemiLargeur() {			return DEMI_LARGEUR;	}
	@Override
	public int getNbEnnemisAvant() {		return nbEnnemisAvant;	}
	@Override
	public void setPosXInitiale(float emplacementX) {		posXInitiale = emplacementX;	}
	@Override
	public Vector2 getPosition() {			return position;	}
	@Override
	public float getPosXInitiale() {		return posXInitiale;	}
	@Override
	protected int getPvMax() {		return Stats.PVMAX_DE_BASE;	}
	@Override
	public void reset() {
		Positionnement.hautLarge(position, getLargeur(), getHauteur());
		super.reset();
	}
	@Override
	protected TextureRegion getTexture() {		return Anim.animEnnemiDeBase.getTexture(maintenant);	}
	@Override
	protected void free() { pool.free(this);	}
	
	@Override
	public float getDirectionY() {
		return -Stats.VITESSE_MAX_DE_BASE;
	}
	
}
