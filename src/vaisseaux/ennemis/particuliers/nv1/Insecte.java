package vaisseaux.ennemis.particuliers.nv1;

import jeu.Physique;
import jeu.Stats;
import menu.CSG;
import vaisseaux.Positionnement;
import vaisseaux.armes.ArmeInsecte;
import vaisseaux.armes.Armes;
import vaisseaux.armes.typeTir.TireurAngle;
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


public class Insecte extends Ennemis implements TireurAngle {
	
	// ** ** caracteristiques g�n�rales
	public static final int LARGEUR = CSG.LARGEUR_ECRAN / 4;
	public static final int DEMI_LARGEUR = LARGEUR/2;
	public static final int HAUTEUR = (LARGEUR / 2) + DEMI_LARGEUR;
	private static final int DEMI_HAUTEUR = HAUTEUR / 2; 
	public static final Tirs tir = new Tirs(.7f);
	private static final Vector2 tmpVector = new Vector2();
	private float prochainTir = 1f;
	public static Pool<Insecte> pool = Pools.get(Insecte.class);
	private boolean tirGauche = true;
	private float impulsion;
	private float angle = 0;
	// ** ** particules
	private ParticulesExplosionPetite explosion;
	protected Vector2 direction = new Vector2();
	
	// Animation
	public static AtlasRegion bonEtat;
	public static AtlasRegion mauvaisEtat;
	
	
	@Override
	protected void free() {
		pool.free(this);
	}
	
	public static void initAnimation(){
		bonEtat = CSG.getAssetMan().getAtlas().findRegion("insecte");
		mauvaisEtat = CSG.getAssetMan().getAtlas().findRegion("insectecasse");
	}
	
	@Override
	protected Sound getSonExplosion() {
		return SoundMan.explosionennemidebasequitir;
	}
	
	public void init() {
		if (CSG.profil.particules & explosion == null) explosion = ParticulesExplosionPetite.pool.obtain();
		else tpsAnimationExplosion = 0;
	}
	
	@Override
	public void reset() {
		mort = false;
		pv = Stats.PVMAX_INSECTE;
		prochainTir = .2f;
		initPlacement();
	}

	public Insecte() {
		super(Positionnement.getEmplacementX(DEMI_LARGEUR), CSG.HAUTEUR_ECRAN + HAUTEUR, Stats.PVMAX_INSECTE);
		initPlacement();
	}

	private void initPlacement() {
		angle = 90;
		impulsion = -10;
		position.y = CSG.HAUTEUR_ECRAN_PALLIER_3;
		if (Math.random() > .5) {
			direction.x = -1;
			position.x = CSG.LARGEUR_ZONE_JEU;
		} else {
			direction.x = 1;
			position.x = -LARGEUR;
		}
		direction.y = 0;
	}

	/**
	 * Exactement la m�me que dans la super classe mais �a �vite de faire des getter largeur hauteur...
	 */
	@Override
	public boolean mouvementEtVerif() {
		if( (mort && tpsAnimationExplosion > AnimationExplosion1.tpsTotalAnimationExplosion1) || Physique.mouvementDeBase(direction, position, Stats.VITESSE_ENNEMI_INSECTE, HAUTEUR, LARGEUR) == false){
			pool.free(this);
			return false;
		}
		direction.rotate(impulsion);
		angle += impulsion;
		impulsion /= 1.2f;
		return true;
	}

	@Override
	public float getAngle() {
		return angle+90;
	}
	
	@Override
	protected TextureRegion getTexture() {
		if (pv < Stats.PVMAX_INSECTE_DEMI)	return mauvaisEtat;
		return bonEtat;
	}
	
	@Override
	protected void tir() {
		tir.tirToutDroit(this, mort, maintenant, prochainTir);
	}

	@Override
	public Rectangle getRectangleCollision() {
		collision.set(position.x, position.y, LARGEUR, HAUTEUR);
		return collision;
	}

	@Override
	public int getXp() {
		return CoutsEnnemis.EnnemiInsecte.COUT;
	}
	
	@Override
	public int getHauteur() {
		return HAUTEUR;
	}

	@Override
	public int getLargeur() {
		return LARGEUR;
	}

	@Override
	public int getDemiHauteur() {
		return DEMI_HAUTEUR;
	}

	@Override
	public int getDemiLargeur() {
		return DEMI_LARGEUR;
	}


	@Override
	public Armes getArme() {			return ArmeInsecte.pool.obtain();	}

	@Override
	public void setProchainTir(float f) {
		if (tirGauche) impulsion = 10;
		else impulsion = -10;
		tirGauche = !tirGauche;
		prochainTir = f;
	}

	@Override
	public float getModifVitesse() {	return 1;	}

	@Override
	public float getAngleTir() {			return angle;	}
	
	@Override
	public Vector2 getDirectionTir() {
		return tmpVector;
	}
	
	@Override
	public Vector2 getPositionDuTir(int numeroTir) {
		majVecteurTir();
		if (!tirGauche) {
			tmpPos.x = (position.x) + (tmpVector.x * 16);
			tmpPos.y = (position.y + ArmeInsecte.DEMI_HAUTEUR)+ (tmpVector.y * 16);
		} else {
			tmpPos.x = (position.x + DEMI_LARGEUR) + (tmpVector.x * 16);
			tmpPos.y = (position.y - ArmeInsecte.DEMI_HAUTEUR) + (tmpVector.y * 16);
		}
		return tmpPos;
	}

	private void majVecteurTir() {
		tmpVector.x = direction.x;
		tmpVector.y = direction.y;
		if (direction.x < 0) tmpVector.rotate(90);
		else tmpVector.rotate(-90);
	}
	
	@Override
	public void invoquer() {
		liste.add(pool.obtain());
	}
}
