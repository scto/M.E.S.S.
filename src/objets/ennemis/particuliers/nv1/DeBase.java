package objets.ennemis.particuliers.nv1;

import objets.PatternHorizontalPositionnable;
import objets.Positionnement;
import objets.ennemis.CoutsEnnemis;
import objets.ennemis.Ennemis;
import jeu.CSG;
import jeu.EndlessMode;
import jeu.Stats;
import assets.SoundMan;
import assets.animation.AnimationEnnemiDeBase;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

/**
 * Ennemi de base, va tout droit et ni tire pas.
 * @author Julien
 */
public class DeBase extends Ennemis implements PatternHorizontalPositionnable {
	
	public static final int LARGEUR= CSG.LARGEUR_ECRAN / 15, DEMI_LARGEUR = LARGEUR/2, HAUTEUR = LARGEUR + DEMI_LARGEUR, DEMI_HAUTEUR = HAUTEUR / 2;
	private static final int MAX_ENNEMIS_LIGNE = 4; 
	public static Pool<DeBase> pool = Pools.get(DeBase.class);
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
		position.y -= (getVitesse() * EndlessMode.delta);
		return super.mouvementEtVerif();
	}
	
	@Override
	protected float getVitesse() {			return Stats.V_ENN_DE_BASE;	}
	
	@Override
	public void invoquer() {
		LISTE.add(pool.obtain());
		if (EndlessMode.modeDifficulte == 2) {
			LISTE.add(pool.obtain());
			LISTE.add(pool.obtain());
			LISTE.add(pool.obtain());
			LISTE.add(pool.obtain());
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
	protected int getPvMax() {				return Stats.PV_DE_BASE;	}
	@Override
	public void reset() {
		Positionnement.hautLarge(position, getLargeur(), getHauteur());
		super.reset();
	}
	@Override
	protected TextureRegion getTexture() {	return AnimationEnnemiDeBase.getTexture(maintenant);	}
	@Override
	protected void free() { 				pool.free(this);	}
	@Override
	public float getDirectionY() {			return -Stats.V_ENN_DE_BASE;	}
	@Override
	protected String getLabel() {			return getClass().toString();	}
	
}
