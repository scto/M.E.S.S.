package vaisseaux.ennemis.particuliers.nv1;

import jeu.Endless;
import jeu.Stats;
import menu.CSG;
import vaisseaux.Positionnement;
import vaisseaux.armes.ArmeBossQuad;
import vaisseaux.armes.Armes;
import vaisseaux.armes.typeTir.Tireur;
import vaisseaux.armes.typeTir.Tirs;
import vaisseaux.ennemis.CoutsEnnemis;
import vaisseaux.ennemis.Ennemis;
import assets.SoundMan;
import assets.animation.AnimationQuiTir;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;


public class QuiTir extends Ennemis implements Tireur{
	
	// ** ** caracteristiques g�n�rales
	public static final float LARGEUR= CSG.LARGEUR_ECRAN / 15;
	public static final float DEMI_LARGEUR = LARGEUR/2;
	public static final float HAUTEUR = LARGEUR + DEMI_LARGEUR;
	public static final float DEMI_HAUTEUR = HAUTEUR / 2;
	public static final float CADENCE = 1.2f;
	public static final Tirs tir = new Tirs(CADENCE);
	// ** ** caracteristiques variables.
	protected float prochainTir = .1f;
	public static Pool<QuiTir> pool = Pools.get(QuiTir.class);
	
	@Override
	public TextureRegion getTexture() {
		return AnimationQuiTir.getTexture(pv);
	}
	
	@Override
	protected void free() {
		pool.free(this);
	}
	
	@Override
	protected Sound getSonExplosion() {
		return SoundMan.explosionennemidebasequitir;
	}
	
	@Override
	public void reset() {
		Positionnement.hautLarge(position, getLargeur(), getHauteur());
		prochainTir = .2f;
		super.reset();
	}
	
	@Override
	protected int getPvMax() {
		return Stats.PVMAX_DE_BASE_QUI_TIR;
	}
	/**
	 * Exactement la m�me que dans la super classe mais �a �vite de faire des getter largeur hauteur...
	 */
	@Override
	public boolean mouvementEtVerif() {
		position.y += (getVitesse() * Endless.delta);
		if (pv < getDemiPv()) position.x += (getDerive() * Endless.delta);
		return super.mouvementEtVerif();
	}
	
	protected float getVitesse() {
		return Stats.VITESSE_MAX_DE_BASE_QUI_TIR;
	}
	
	protected float getDerive() {
		return Stats.DERIVE_DE_BASE_QUI_TIR;
	}
	
	protected int getDemiPv() {
		return Stats.DEMI_PV_BASE_QUI_TIR;
	}

	@Override
	protected void tir() {
		tir.tirVersBas(this, mort, maintenant, prochainTir);
	}

	@Override
	public int getXp() {
		return CoutsEnnemis.EnnemiQuiTir.COUT;
	}
	
	@Override
	public int getHauteur() {			return (int)HAUTEUR;	}

	@Override
	public int getLargeur() {			return (int)LARGEUR;	}

	@Override
	public int getDemiHauteur() {		return (int)DEMI_HAUTEUR;	}

	@Override
	public int getDemiLargeur() {		return (int)DEMI_LARGEUR;	}

	@Override
	public Armes getArme() {			return ArmeBossQuad.pool.obtain();	}
	
	@Override
	public void setProchainTir(float f) {		prochainTir = f;	}

	@Override
	public float getModifVitesse() {	return 1;	}

	@Override
	public Vector2 getPositionDuTir(int numeroTir) {
		tmpPos.x = position.x + DEMI_LARGEUR - ArmeBossQuad.DEMI_LARGEUR;
		tmpPos.y = position.y - ArmeBossQuad.HAUTEUR;
		return tmpPos;
	}

	@Override
	public void invoquer() {
		liste.add(pool.obtain());
	}

}
