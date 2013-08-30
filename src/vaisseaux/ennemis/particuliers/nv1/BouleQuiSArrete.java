package vaisseaux.ennemis.particuliers.nv1;

import jeu.Physique;
import jeu.Stats;
import menu.CSG;
import vaisseaux.Positionnement;
import vaisseaux.armes.ennemi.ArmeEnnemi;
import vaisseaux.armes.ennemi.ArmesBouleBleu;
import vaisseaux.armes.ennemi.BouleBleueRapide;
import vaisseaux.armes.typeTir.Tireur;
import vaisseaux.armes.typeTir.Tirs;
import vaisseaux.ennemis.CoutsEnnemis;
import vaisseaux.ennemis.Ennemis;
import assets.SoundMan;
import assets.animation.AnimationBouleBleuRouge;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;


public class BouleQuiSArrete extends Ennemis implements Tireur {
	
	// ** ** caracteristiques g�n�rales
	public static final int LARGEUR= CSG.LARGEUR_ECRAN / 17;
	public static final int DEMI_LARGEUR = LARGEUR/2;
	public static final float CADENCETIR = 1.6f;
	public static final Tirs tir = new Tirs(CADENCETIR);
	// ** ** caracteristiques variables.
	private float prochainTir = .1f;
	public static Pool<BouleQuiSArrete> pool = Pools.get(BouleQuiSArrete.class);
	
	
	public BouleQuiSArrete() {
		super();
		Positionnement.hautLarge(position, getLargeur(), getHauteur());
	}
	
	@Override
	public void reset() {
		Positionnement.hautLarge(position, getLargeur(), getHauteur());
		super.reset();
		prochainTir = .2f;
	}
	
	@Override
	public boolean mouvementEtVerif() {
		Physique.mvtArretHauteur(position, Stats.V_ENN_BOULE_QUI_SARRETE, maintenant);
		return super.mouvementEtVerif();
	}

	@Override
	public Vector2 getPositionDuTir(int numeroTir) {
		tmpPos.x = (position.x + DEMI_LARGEUR - BouleBleueRapide.DEMI_LARGEUR);
		tmpPos.y = (position.y + DEMI_LARGEUR - BouleBleueRapide.DEMI_LARGEUR);
		return tmpPos;
	}
	
	@Override
	public void invoquer() {				liste.add(pool.obtain());	}
	@Override
	protected void free() {					pool.free(this);	}
	@Override
	protected TextureRegion getTexture() {	return AnimationBouleBleuRouge.getTexture(maintenant);	}
	@Override
	protected void tir() {					tir.tirVersJoueur(this, mort, maintenant, prochainTir);	}
	@Override
	protected Sound getSonExplosion() {		return SoundMan.explosionboule;	}
	@Override
	protected int getPvMax() {				return Stats.PV_BOULE_QUI_SARRETE;	}
	@Override
	public int getXp() {					return CoutsEnnemis.EnnemiBouleQuiSArrete.COUT;	}
	@Override
	public int getHauteur() {				return LARGEUR;	}
	@Override
	public int getLargeur() {				return LARGEUR;	}
	@Override
	public int getDemiHauteur() {			return DEMI_LARGEUR;	}
	@Override
	public int getDemiLargeur() {			return DEMI_LARGEUR;	}
	@Override
	public ArmeEnnemi getArme() {				return ArmesBouleBleu.pool.obtain();	}
	@Override
	public void setProchainTir(float f) {	prochainTir = f;	}
	@Override
	public float getModifVitesse() {		return 1;	}

	@Override
	public float getDirectionY() {
		if (position.y < CSG.HAUTEUR_ECRAN_PALLIER_2) {
			// On ralentit
			if (position.y > CSG.HAUTEUR_ECRAN_PALLIER_3)
				return -50;
		} else {
			return -Stats.V_ENN_BOULE_QUI_SARRETE;
		}
		return -Stats.V_ENN_BOULE_QUI_SARRETE;
	}
	
	@Override
	public float getDirectionX() {
		if (position.y < CSG.HAUTEUR_ECRAN_PALLIER_2) {
			return 0;
		} else {
			return -Stats.V_ENN_BOULE_QUI_SARRETE;
		}
	}
}
