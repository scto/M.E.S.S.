package vaisseaux.ennemis.particuliers.nv1;

import jeu.Endless;
import jeu.Stats;
import menu.CSG;
import vaisseaux.Positionnement;
import vaisseaux.armes.ennemi.BouleFeu;
import vaisseaux.armes.ennemi.ArmeEnnemi;
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
	
	public static final float LARGEUR= CSG.LARGEUR_ECRAN / 15, DEMI_LARGEUR = LARGEUR/2;
	public static final float HAUTEUR = LARGEUR + DEMI_LARGEUR, DEMI_HAUTEUR = HAUTEUR / 2;
	public static final float CADENCE = 1.2f;
	public static final Tirs tir = new Tirs(CADENCE);
	protected float prochainTir = .1f;
	public static Pool<QuiTir> pool = Pools.get(QuiTir.class);
	
	
	public QuiTir() {		Positionnement.hautLarge(position, getLargeur(), getHauteur());	}
	
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
		prochainTir = .1f;
		super.reset();
	}
	
	@Override
	protected int getPvMax() {
		return Stats.PV_QUI_TIR;
	}
	/**
	 * Exactement la m�me que dans la super classe mais �a �vite de faire des getter largeur hauteur...
	 */
	@Override
	public boolean mouvementEtVerif() {
		position.y -= (getVitesse() * Endless.delta);
		if (pv < getDemiPv()) position.x += (getDerive() * Endless.delta);
		return super.mouvementEtVerif();
	}
	
	protected float getVitesse() {
		return Stats.V_ENN_QUI_TIR;
	}
	
	protected float getDerive() {
		return Stats.DERIVE_QUI_TIR;
	}
	
	protected int getDemiPv() {
		return Stats.DEMI_PV_QUI_TIR;
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
	public ArmeEnnemi getArme() {			return BouleFeu.pool.obtain();	}
	
	@Override
	public void setProchainTir(float f) {		prochainTir = f;	}

	@Override
	public float getModifVitesse() {	return 1;	}

	@Override
	public Vector2 getPositionDuTir(int numeroTir) {
		TMP_POS.x = position.x + DEMI_LARGEUR - BouleFeu.DEMI_LARGEUR;
		TMP_POS.y = position.y - BouleFeu.HAUTEUR;
		return TMP_POS;
	}

	@Override
	public void invoquer() {
		LISTE.add(pool.obtain());
	}
	
	@Override
	public float getDirectionY() {
		return getVitesse();
	}

	@Override
	public float getDirectionX() {
		if (pv < getDemiPv()) 
		return getDerive();
		else return 0;
	}
}
