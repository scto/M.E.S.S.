package vaisseaux.ennemis.particuliers.nv1;

import jeu.Endless;
import jeu.Physique;
import jeu.Stats;
import menu.CSG;
import vaisseaux.Positionnement;
import vaisseaux.armes.ArmeFusee;
import vaisseaux.armes.Armes;
import vaisseaux.armes.typeTir.Tireur;
import vaisseaux.armes.typeTir.Tirs;
import vaisseaux.ennemis.CoutsEnnemis;
import vaisseaux.ennemis.Ennemis;
import assets.SoundMan;
import assets.animation.AnimationExplosion1;
import assets.particules.ParticulesExplosionPetite;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;


public class QuiTir2 extends Ennemis implements Tireur {
	
	// ** ** caracteristiques g�n�rales
	public static final int LARGEUR= CSG.LARGEUR_ECRAN / 9;
	public static final int DEMI_LARGEUR = LARGEUR/2;
	public static final int HAUTEUR = LARGEUR + DEMI_LARGEUR;
	private static final int DEMI_HAUTEUR = HAUTEUR / 2; 
	public static final Tirs TIR = new Tirs(0.7f);
	// ** ** caracteristiques variables.
	private float prochainTir = .1f;
	public static Pool<QuiTir2> pool = Pools.get(QuiTir2.class);
	// ** ** particules
	private ParticulesExplosionPetite explosion;
	
	// Animation
	public static AtlasRegion bonEtat;
	public static AtlasRegion mauvaisEtat;
	
	public static void initAnimation(){
		bonEtat = CSG.getAssetMan().getAtlas().findRegion("fusee");
		mauvaisEtat = CSG.getAssetMan().getAtlas().findRegion("fuseeamochee");
	}
	
	@Override
	public TextureRegion getTexture() {
		if (pv < Stats.DEMI_PV_BASE_QUI_TIR2)
			return mauvaisEtat;
		return bonEtat;
	}
	
	@Override
	protected void free() {
		pool.free(this);
	}
	
	@Override
	protected Sound getSonExplosion() {
		return SoundMan.explosionennemidebasequitir;
	}
	
	public void init() {
		tpsAnimationExplosion = 0;
		if(CSG.profil.particules & explosion == null) explosion = ParticulesExplosionPetite.pool.obtain();
	}
	
	@Override
	public void reset() {
		position.x = Positionnement.getEmplacementX(DEMI_LARGEUR);
		position.y = CSG.HAUTEUR_ECRAN + HAUTEUR;
		mort = false;
		pv = Stats.PVMAX_DE_BASE_QUI_TIR2;
		prochainTir = .2f;
	}

	public QuiTir2() {
		super(Positionnement.getEmplacementX(DEMI_LARGEUR), CSG.HAUTEUR_ECRAN + HAUTEUR, Stats.PVMAX_DE_BASE_QUI_TIR2);
	}

	/**
	 * Exactement la m�me que dans la super classe mais �a �vite de faire des getter largeur hauteur...
	 */
	@Override
	public boolean mouvementEtVerif() {
		if( (mort & tpsAnimationExplosion > AnimationExplosion1.tpsTotalAnimationExplosion1) | Physique.toujoursAfficher(position, HAUTEUR, LARGEUR) == false){
			pool.free(this);
			return false;
		}
		position.y += (Stats.VITESSE_MAX_DE_BASE_QUI_TIR2 * Endless.delta);
		if (pv < Stats.DEMI_PV_BASE_QUI_TIR2) position.x += (Stats.DERIVE_DE_BASE_QUI_TIR2 * Endless.delta);
		return true;
	}
	
	@Override
	protected void tir() {
		TIR.tirVersBas(this, mort, maintenant, prochainTir);
	}

	@Override
	public Rectangle getRectangleCollision() {
		collision.set(position.x, position.y, LARGEUR, HAUTEUR);
		return collision;
	}

	@Override
	public int getXp() {		return CoutsEnnemis.EnnemiQuiTir2.COUT;	}
	
	@Override
	public int getHauteur() {		return HAUTEUR;	}

	@Override
	public int getLargeur() {		return LARGEUR;	}

	@Override
	public int getDemiHauteur() {		return DEMI_HAUTEUR;	}

	@Override
	public int getDemiLargeur() {		return DEMI_LARGEUR;	}
	
	@Override
	public Armes getArme() {			return ArmeFusee.pool.obtain();	}
	
	@Override
	public void setProchainTir(float f) {		prochainTir = f;	}

	@Override
	public float getModifVitesse() {	return 1;	}

	
	@Override
	public Vector2 getPositionDuTir(int numeroTir) {
		tmpPos.x = position.x + DEMI_LARGEUR - ArmeFusee.DEMI_LARGEUR;
		tmpPos.y = position.y - ArmeFusee.LARGEUR;
		return tmpPos;
	}

	@Override
	public void invoquer() {
		liste.add(pool.obtain());
	}
}
