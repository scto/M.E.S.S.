package vaisseaux.ennemis.particuliers.nv1;

import jeu.Stats;
import menu.CSG;
import vaisseaux.Positionnement;
import vaisseaux.armes.ArmeBossQuad;
import vaisseaux.armes.Armes;
import vaisseaux.armes.typeTir.DoubleTireur;
import vaisseaux.armes.typeTir.Tirs;
import vaisseaux.ennemis.CoutsEnnemis;
import assets.SoundMan;
import assets.animation.AnimationAvion;
import assets.particules.ParticulesExplosionPetite;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;


public class Avion extends DeBase implements DoubleTireur {
	
	// ** ** caracteristiques g�n�rales
	public static final int LARGEUR= CSG.LARGEUR_ECRAN / 5;
	public static final int DEMI_LARGEUR = LARGEUR/2;
	public static final int HAUTEUR = LARGEUR + DEMI_LARGEUR/2;
	private static final int DEMI_HAUTEUR = HAUTEUR / 2; 
	private static final int offsetArmeGauche = DEMI_LARGEUR / 4;
	private static final int offsetArmeDroite = LARGEUR - (DEMI_LARGEUR / 3);
	public static final float CADENCE = .8f;
	public static final Tirs tir = new Tirs(CADENCE);
	// ** ** caracteristiques variables.
	protected float prochainTir = 2f;
	public static Pool<Avion> pool = Pools.get(Avion.class);
	// ** ** particules
	protected ParticulesExplosionPetite explosion;
	
	@Override
	public void reset() {
		position.x = Positionnement.getEmplacementX(DEMI_LARGEUR);
		position.y = CSG.HAUTEUR_ECRAN + HAUTEUR;
		reset(Stats.PVMAX_AVION);
		prochainTir = 2.5f;
	}

	@Override
	protected Sound getSonExplosion() {	return SoundMan.explosionennemidebasequitir;	}
	@Override
	public int getHauteur() {			return HAUTEUR;	}
	@Override
	public int getLargeur() {			return LARGEUR;	}
	@Override
	public int getDemiHauteur() {		return DEMI_HAUTEUR;	}
	@Override
	public int getDemiLargeur() {		return DEMI_LARGEUR;	}
	@Override
	public Armes getArme() {			return ArmeBossQuad.pool.obtain();	}
	@Override
	public void setProchainTir(float f) {		prochainTir = f;	}
	@Override
	public float getModifVitesse() {	return 1;	}
	@Override
	public float getXtir1() {			return position.x - offsetArmeGauche;	}
	@Override
	public float getXtir2() {			return position.x + offsetArmeDroite;	}
	@Override
	public float getYtirs() {			return position.y;	}
	@Override
	protected TextureRegion getTexture() {		return AnimationAvion.getTexture(pv);	}
	@Override
	protected void tir() {				tir.doubleTirVersBas(this, mort, maintenant, prochainTir);	}
	@Override
	public int getXp() {				return CoutsEnnemis.EnnemiAvion.COUT;	}
	
	@Override
	public Vector2 getPositionDuTir(int numeroTir) {
		switch (numeroTir) {
		case 1: 
			tmpPos.x = position.x - ArmeBossQuad.DEMI_LARGEUR;
			tmpPos.y = position.y;
			break;
		case 2:
			tmpPos.x = position.x + LARGEUR - ArmeBossQuad.DEMI_LARGEUR;
			tmpPos.y = position.y;
			break;
		}
		return tmpPos;
	}

	@Override
	public void invoquer() {
		liste.add(pool.obtain());
	}
}
