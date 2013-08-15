package vaisseaux.ennemis.particuliers.nv2;

import jeu.Endless;
import jeu.Physique;
import jeu.Stats;
import menu.CSG;
import vaisseaux.Positionnement;
import vaisseaux.armes.ArmeBouleTir;
import vaisseaux.armes.Armes;
import vaisseaux.armes.typeTir.TireurAngle;
import vaisseaux.armes.typeTir.Tirs;
import vaisseaux.ennemis.CoutsEnnemis;
import vaisseaux.ennemis.Ennemis;
import assets.SoundMan;
import assets.animation.AnimationBouleBleuRouge;
import assets.animation.AnimationExplosion1;
import assets.particules.ParticulesExplosionPetite;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

/**
 * Ennemi de base, g�re son pool, va tout droit et ni tire pas.
 * @author Julien
 *
 */
public class BouleTirCote extends Ennemis implements TireurAngle {
	
	// ** ** caracteristiques g�n�rales
	public static final int LARGEUR = CSG.LARGEUR_ECRAN / 15;
	public static final int DEMI_LARGEUR = LARGEUR/2;
	public static final float CADENCETIR = .12f;
	public static Pool<BouleTirCote> pool = Pools.get(BouleTirCote.class);
	// ** ** animations
	protected float maintenant;
	protected float tpsAnimationExplosion;
	protected ParticulesExplosionPetite explosion;
	// ** ** moins aleatoire
	protected float angle = 0;
	protected Vector2 direction = new Vector2();
	protected float prochainTir;
	protected int numeroTir;
	protected boolean gauche = false;
	protected final static Tirs TIR = new Tirs(.12f); 
	
	@Override
	protected Sound getSonExplosion() {
		return SoundMan.explosiontoupie;
	}
	
	@Override
	protected void free() {
		pool.free(this);
	}
	
	/**
	 * Contructeur sans argument, appel� par le pool
	 */
	public BouleTirCote() {
		super(CSG.LARGEUR_BORD,	CSG.HAUTEUR_ECRAN + LARGEUR, Stats.PVMAX_LASER_COTE);
		placement();
	}

	private void placement() {
		direction.x = 0;
		direction.y = -1;
		prochainTir = 1;
		numeroTir = 1;
		maintenant = 0;
		position.y = CSG.HAUTEUR_ECRAN + LARGEUR;
		position.x = Positionnement.getEmplacementXVersMilieu(LARGEUR);
		angle = CSG.DEMI_LARGEUR_ZONE_JEU - (position.x + LARGEUR*2);
		angle /= 4;
		direction.rotate(angle);
		angle += -90;
	}

	@Override
	public void reset() {
		mort = false;
		if (!CSG.profil.particules)		tpsAnimationExplosion = 0;
		pv = Stats.PVMAX_LASER_COTE;
		placement();
	}

	@Override
	public void afficher(SpriteBatch batch) {
		if (mort) {
			batch.draw(AnimationExplosion1.getTexture(tpsAnimationExplosion), position.x, position.y, LARGEUR, LARGEUR);
			tpsAnimationExplosion += Endless.delta;
		} else {
			batch.draw(AnimationBouleBleuRouge.getTexture(maintenant) , position.x, position.y, DEMI_LARGEUR, DEMI_LARGEUR, LARGEUR, LARGEUR, 1, 1, angle, false);
			maintenant += Endless.delta;
		}
	}

	@Override
	public boolean mouvementEtVerif() {
		if( (mort && tpsAnimationExplosion > AnimationExplosion1.tpsTotalAnimationExplosion1)
				|| Physique.mouvementDeBase(direction, position, Stats.VITESSE_BOULE_TIR_COTE, LARGEUR) == false){
			pool.free(this);
			return false;
		}
		return true;
	}

	@Override
	protected void tir() {
		TIR.tirSurCote(this, mort, maintenant, prochainTir);
	}

	@Override
	public Rectangle getRectangleCollision() {
		collision.set(position.x, position.y, LARGEUR, LARGEUR);
		return collision;
	}

	@Override
	public int getXp() {			return CoutsEnnemis.EnnemiLaserCoteNv2.COUT;	}
	@Override
	public int getHauteur() {		return LARGEUR;	}
	@Override
	public int getLargeur() {		return LARGEUR;	}
	@Override
	public int getDemiHauteur() {	return DEMI_LARGEUR;	}
	@Override
	public int getDemiLargeur() {	return DEMI_LARGEUR;	}
	@Override
	public void invoquer() {		liste.add(pool.obtain());	}
	@Override
	public Armes getArme() {		return ArmeBouleTir.pool.obtain();	}

	@Override
	public Vector2 getPositionDuTir(int numeroTir) {
		tmpPos.x = position.x + DEMI_LARGEUR - ArmeBouleTir.DEMI_LARGEUR; 
		tmpPos.y = position.y + DEMI_LARGEUR - ArmeBouleTir.DEMI_LARGEUR;
		return tmpPos;
	}

	@Override
	public float getModifVitesse() {return 1;	}

	@Override
	public void setProchainTir(float f) {
		if (++numeroTir > 7) numeroTir = 1;
		prochainTir = f + CADENCETIR * numeroTir;
	}

	@Override
	public Vector2 getDirectionTir() {
		tmpDir.x = direction.x;
		tmpDir.y = direction.y;
		return tmpDir;
	}

	@Override
	public float getAngleTir() {	return angle;	}
}
