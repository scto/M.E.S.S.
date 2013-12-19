package objets.ennemis.particuliers.nv1;

import objets.Positionnement;
import objets.armes.ennemi.ArmeEnnemi;
import objets.armes.ennemi.ArmesBouleBleu;
import objets.armes.ennemi.BouleBleueRapide;
import objets.armes.typeTir.Tireur;
import objets.armes.typeTir.Tirs;
import objets.ennemis.CoutsEnnemis;
import objets.ennemis.Ennemis;
import jeu.CSG;
import jeu.Physique;
import jeu.Stats;
import assets.SoundMan;
import assets.animation.AnimationBouleBleuRouge;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

public class Boule extends Ennemis implements Tireur {
	
	private static final int LARGEUR = Stats.LARGEUR_BOULE, DEMI_LARGEUR = LARGEUR/2;
	private static final float CADENCETIR = 1.6f;
	private static final Tirs tir = new Tirs(CADENCETIR);
	private float prochainTir = .1f;
	public static Pool<Boule> pool = Pools.get(Boule.class);
	
	
	public Boule() {
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
		Physique.mvtArretHauteur(position, -Stats.V_ENN_BOULE, maintenant);
		return super.mouvementEtVerif();
	}

	@Override
	public Vector2 getPositionDuTir(int numeroTir) {
		TMP_POS.x = (position.x + DEMI_LARGEUR - BouleBleueRapide.DEMI_LARGEUR);
		TMP_POS.y = (position.y + DEMI_LARGEUR - BouleBleueRapide.DEMI_LARGEUR);
		return TMP_POS;
	}
	
	@Override
	public float getDirectionY() {
		if (position.y < CSG.HAUTEUR_ECRAN_PALLIER_2) {
			// On ralentit
			if (position.y > CSG.HAUTEUR_ECRAN_PALLIER_3)
				return -50;
		} else {
			return -Stats.V_ENN_BOULE;
		}
		return -Stats.V_ENN_BOULE;
	}
	
	@Override
	public float getDirectionX() {
		if (position.y < CSG.HAUTEUR_ECRAN_PALLIER_2) {
			return 0;
		} else {
			return -Stats.V_ENN_BOULE;
		}
	}
	
	@Override
	public void invoquer() {				LISTE.add(pool.obtain());	}
	@Override
	protected void free() {					pool.free(this);	}
	@Override
	protected TextureRegion getTexture() {	return AnimationBouleBleuRouge.getTexture(maintenant);	}
	@Override
	protected void tir() {					tir.tirVersJoueur(this, mort, maintenant, prochainTir);	}
	@Override
	protected Sound getSonExplosion() {		return SoundMan.explosionboule;	}
	@Override
	protected int getPvMax() {				return Stats.PV_BOULE;	}
	@Override
	public int getXp() {					return CoutsEnnemis.BOULE.COUT;	}
	@Override
	public int getHauteur() {				return LARGEUR;	}
	@Override
	public int getLargeur() {				return LARGEUR;	}
	@Override
	public int getDemiHauteur() {			return DEMI_LARGEUR;	}
	@Override
	public int getDemiLargeur() {			return DEMI_LARGEUR;	}
	@Override
	public ArmeEnnemi getArme() {			return ArmesBouleBleu.pool.obtain();	}
	@Override
	public void setProchainTir(float f) {	prochainTir = f;	}
	@Override
	public float getModifVitesse() {		return 1;	}
	@Override
	protected String getLabel() {			return getClass().toString();	}
}
