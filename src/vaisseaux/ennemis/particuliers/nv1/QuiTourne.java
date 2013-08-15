package vaisseaux.ennemis.particuliers.nv1;

import jeu.Endless;
import jeu.Physique;
import jeu.Stats;
import menu.CSG;
import vaisseaux.Positionnement;
import vaisseaux.armes.ArmeBalayageEnnemiQuiTourne;
import vaisseaux.armes.Armes;
import vaisseaux.armes.typeTir.TireurPlusieurFois;
import vaisseaux.armes.typeTir.Tirs;
import vaisseaux.ennemis.CoutsEnnemis;
import vaisseaux.ennemis.Ennemis;
import assets.SoundMan;
import assets.animation.AnimationEnnemiTourne;
import assets.animation.AnimationExplosion1;
import assets.particules.ParticulesExplosionPetite;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

public class QuiTourne extends Ennemis implements TireurPlusieurFois {
	
	// ** ** caracteristiques g�n�rales
	public static final int LARGEUR= CSG.LARGEUR_ECRAN / 13;
	public static final int DEMI_LARGEUR = LARGEUR/2;
	public static final float CADENCE_TIR = .2f;
	public static final Tirs TIR = new Tirs(CADENCE_TIR);
	protected Vector2 direction = new Vector2(0,-1);
	public static Pool<QuiTourne> pool = Pools.get(QuiTourne.class);
	// ******************************************** T I R ********************************************************************
	private float prochainTir = 0;
	private int numeroTir = 0;
	// ** ** animations
	protected ParticulesExplosionPetite explosion;
	// ** ** caracteristiques variables.

	/**
	 * Contructeur sans argument, appel� par le pool
	 */
	public QuiTourne() {
		super(Positionnement.getEmplacementXVersMilieu(DEMI_LARGEUR),	CSG.HAUTEUR_ECRAN + LARGEUR, Stats.PVMAX_QUI_TOURNE);
		prochainTir = 2;
	}
	
	@Override
	public void reset() {
		position.x = Positionnement.getEmplacementXVersMilieu(DEMI_LARGEUR);
		position.y = CSG.HAUTEUR_ECRAN + LARGEUR;
		direction.x = 0;
		direction.y = 1;
		maintenant = 0;
		prochainTir = 0;
		mort = false;
		pv = Stats.PVMAX_QUI_TOURNE;
		prochainTir = 3;
	}

	@Override
	protected Sound getSonExplosion() {		return SoundMan.explosionPetite;	}
	@Override
	protected TextureRegion getTexture() {	return AnimationEnnemiTourne.getTexture(maintenant);	}

	@Override
	public boolean mouvementEtVerifSansParticules() {
		if (mort && tpsAnimationExplosion > AnimationExplosion1.tpsTotalAnimationExplosion1 || Physique.toujoursAfficher(position, LARGEUR) == false){
			pool.free(this);
			return false;
		}
		position.y += (CSG.DEMI_HAUTEUR_ECRAN - position.y) * (Endless.delta/2);
		if(maintenant < 10)		direction.rotate(Endless.delta * Stats.VITESSE_QUI_TOURNE);
		position.x += direction.x * Stats.VITESSE_QUI_TOURNE * Endless.delta;
		position.y += direction.y * Stats.DEMI_VITESSE_QUI_TOURNE * Endless.delta;
		return true;
	}

	

	@Override
	protected void tir() {
		TIR.tirEnRafale(this, 3, mort, maintenant, prochainTir);
	}

	@Override
	public Rectangle getRectangleCollision() {
		collision.set(position.x, position.y, LARGEUR, LARGEUR);
		return collision;
	}

	@Override
	public int getXp() {
		return CoutsEnnemis.EnnemiQuiTourne.COUT;
	}

	@Override
	public int getHauteur() {
		return LARGEUR;
	}

	@Override
	public int getLargeur() {
		return LARGEUR;
	}

	@Override
	public int getDemiHauteur() {
		return DEMI_LARGEUR;
	}

	@Override
	public int getDemiLargeur() {
		return DEMI_LARGEUR;
	}
	
	@Override
	public Armes getArme() {			return ArmeBalayageEnnemiQuiTourne.pool.obtain();	}

	@Override
	public void setProchainTir(float f) {
		prochainTir = f;
	}

	@Override
	public float getModifVitesse() {	return 1;	}

	@Override
	public float getAngleTir() {			return 0;	}
	
	@Override
	public Vector2 getDirectionTir() {
		return direction;
	}
	
	@Override
	public Vector2 getPositionDuTir(int numeroTir) {
		tmpPos.x = (position.x + DEMI_LARGEUR - ArmeBalayageEnnemiQuiTourne.DEMI_LARGEUR) + (direction.x * 32);
		tmpPos.y = (position.y + DEMI_LARGEUR - ArmeBalayageEnnemiQuiTourne.DEMI_LARGEUR) + (direction.y * 32);
		return tmpPos;
	}

	@Override
	public int getNumeroTir() {
		return numeroTir;
	}

	@Override
	public void addNombresTirs(int i) {
		numeroTir += i;
	}
	
	@Override
	public void invoquer() {
		liste.add(pool.obtain());
	}
}
