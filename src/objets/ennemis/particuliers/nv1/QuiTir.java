package objets.ennemis.particuliers.nv1;

import objets.Positionnement;
import objets.armes.ennemi.ArmeEnnemi;
import objets.armes.ennemi.BouleFeu;
import objets.armes.typeTir.Tireur;
import objets.armes.typeTir.Tirs;
import objets.ennemis.CoutsEnnemis;
import objets.ennemis.Ennemis;
import jeu.EndlessMode;
import jeu.Stats;
import menu.CSG;
import assets.SoundMan;
import assets.animation.AnimationQuiTir;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;


public class QuiTir extends Ennemis implements Tireur{
	
	public static final float LARGEUR= CSG.LARGEUR_ECRAN / 11, DEMI_LARGEUR = LARGEUR/2;
	public static final float HAUTEUR = LARGEUR + DEMI_LARGEUR, DEMI_HAUTEUR = HAUTEUR / 2;
	public static final float CADENCE = 1.2f;
	public static final Tirs tir = new Tirs(CADENCE);
	protected float prochainTir = 1.5f;
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
		prochainTir = 2f;
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
		position.y -= (getVitesse() * EndlessMode.delta);
		if (pv < getDemiPv()) position.x += (getDerive() * EndlessMode.delta);
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
