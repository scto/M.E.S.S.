package objets.ennemis.particuliers.nv1;

import objets.PatternHorizontalPositionnable;
import objets.Positionnement;
import objets.ennemis.CoutsEnnemis;
import objets.ennemis.Ennemis;
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
	
	private static final int LARGEUR = Stats.LARGEUR_DE_BASE, DEMI_LARGEUR = LARGEUR / 2, HAUTEUR = Stats.HAUTEUR_DE_BASE, DEMI_HAUTEUR = HAUTEUR / 2;
	private static final int MAX_ENNEMIS_LIGNE = 4; 
	public static final Pool<DeBase> POOL = Pools.get(DeBase.class);
	private static int nbEnnemisAvant = 0;
	private static float posXInitiale;
	
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
	public void invoquer() {
		LISTE.add(POOL.obtain());
		if (EndlessMode.modeDifficulte > 1) {
			LISTE.add(POOL.obtain());
			LISTE.add(POOL.obtain());
			LISTE.add(POOL.obtain());
			LISTE.add(POOL.obtain());
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
	protected void free() { 				POOL.free(this);	}
	@Override
	public float getDirectionY() {			return -Stats.V_ENN_DE_BASE;	}
	@Override
	protected String getLabel() {			return getClass().toString();	}
	@Override
	protected float getVitesse() {			return Stats.V_ENN_DE_BASE;	}
	
}
