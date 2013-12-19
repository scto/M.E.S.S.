package objets.ennemis.particuliers.nv1;

import objets.armes.ennemi.ArmeEnnemi;
import objets.armes.ennemi.BouleFeu;
import objets.armes.typeTir.DoubleTireur;
import objets.armes.typeTir.Tirs;
import objets.ennemis.CoutsEnnemis;
import jeu.Stats;
import assets.SoundMan;
import assets.animation.AnimationAvion;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;


public class Avion extends DeBase implements DoubleTireur {
	
	private static final int LARGEUR = Stats.LARGEUR_AVION, DEMI_LARGEUR = LARGEUR/2, HAUTEUR = Stats.HAUTEUR_AVION, DEMI_HAUTEUR = HAUTEUR / 2; 
	private static final int offsetArmeGauche = DEMI_LARGEUR / 4, offsetArmeDroite = LARGEUR - (DEMI_LARGEUR / 3);
	private static final float CADENCE = .8f;
	private static final Tirs tir = new Tirs(CADENCE);
	// ** ** caracteristiques variables.
	protected float prochainTir = 2f;
	public static final Pool<Avion> POOL = Pools.get(Avion.class);
	
	@Override
	protected void free() {		POOL.free(this);	}
	@Override
	public void reset() {
		super.reset();
		prochainTir = 2.5f;
	}
	
	@Override
	public Vector2 getPositionDuTir(int numeroTir) {
		switch (numeroTir) {
		case 1: 
			TMP_POS.x = position.x - BouleFeu.DEMI_LARGEUR;
			TMP_POS.y = position.y;
			break;
		case 2:
			TMP_POS.x = position.x + LARGEUR - BouleFeu.DEMI_LARGEUR;
			TMP_POS.y = position.y;
			break;
		}
		return TMP_POS;
	}
	
	@Override
	protected float getVitesse() {			return Stats.V_ENN_AVION;	}
	@Override
	protected int getPvMax() {				return Stats.PV_AVION;	}
	@Override
	public void invoquer() {				LISTE.add(POOL.obtain());	}
	@Override
	protected Sound getSonExplosion() {		return SoundMan.explosionennemidebasequitir;	}
	@Override
	public int getHauteur() {				return HAUTEUR;	}
	@Override
	public int getLargeur() {				return LARGEUR;	}
	@Override
	public int getDemiHauteur() {			return DEMI_HAUTEUR;	}
	@Override
	public int getDemiLargeur() {			return DEMI_LARGEUR;	}
	@Override
	public ArmeEnnemi getArme() {			return BouleFeu.pool.obtain();	}
	@Override
	public void setProchainTir(float f) {	prochainTir = f;	}
	@Override
	public float getModifVitesse() {		return 1;	}
	@Override
	public float getXtir1() {				return position.x - offsetArmeGauche;	}
	@Override
	public float getXtir2() {				return position.x + offsetArmeDroite;	}
	@Override
	public float getYtirs() {				return position.y;	}
	@Override
	protected TextureRegion getTexture() {	return AnimationAvion.getTexture(pv);	}
	@Override
	protected void tir() {					tir.doubleTirVersBas(this, mort, maintenant, prochainTir);	}
	@Override
	public int getXp() {					return CoutsEnnemis.EnnemiAvion.COUT;	}
	@Override
	protected String getLabel() {			return getClass().toString();	}
}
