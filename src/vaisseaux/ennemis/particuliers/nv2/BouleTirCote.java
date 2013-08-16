package vaisseaux.ennemis.particuliers.nv2;

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
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
	protected float angle = 0;
	protected Vector2 direction = new Vector2();
	protected float prochainTir;
	protected int numeroTir;
	protected boolean gauche = false;
	protected final static Tirs TIR = new Tirs(.12f); 
	

	protected Sound getSonExplosion() {
		return SoundMan.explosiontoupie;
	}

	protected void free() {
		pool.free(this);
	}
	
	/**
	 * Contructeur sans argument, appel� par le pool
	 */
	public BouleTirCote() {
		super();
		placement();
	}
	@Override
	protected int getPvMax() {
		return Stats.PV_BOULE_COTE_PETIT;
	}

	private void placement() {
		direction.x = 0;
		direction.y = getVitesse();
		prochainTir = 1;
		numeroTir = 1;
		Positionnement.hautMoyen(this);
		angle = CSG.DEMI_LARGEUR_ZONE_JEU - (position.x + LARGEUR*2);
		angle /= 4;
		direction.rotate(angle);
		angle += -90;
	}

	protected float getVitesse() {
		return Stats.VITESSE_BOULE_TIR_COTE;
	}

	public void reset() {
		placement();
		super.reset();
	}

	@Override
	protected TextureRegion getTexture() {
		return AnimationBouleBleuRouge.getTexture(maintenant);
	}

	@Override
	public boolean mouvementEtVerif() {
		Physique.mvtSansVerif(position, direction);
		return super.mouvementEtVerif();
	}


	protected void tir() {
		TIR.tirSurCote(this, mort, maintenant, prochainTir);
	}

	public int getXp() {			return CoutsEnnemis.EnnemiLaserCoteNv2.COUT;	}

	public int getHauteur() {		return LARGEUR;	}

	public int getLargeur() {		return LARGEUR;	}

	public int getDemiHauteur() {	return DEMI_LARGEUR;	}

	public int getDemiLargeur() {	return DEMI_LARGEUR;	}

	public void invoquer() {		liste.add(pool.obtain());	}

	public Armes getArme() {		return ArmeBouleTir.pool.obtain();	}


	public Vector2 getPositionDuTir(int numeroTir) {
		tmpPos.x = position.x + DEMI_LARGEUR - ArmeBouleTir.DEMI_LARGEUR; 
		tmpPos.y = position.y + DEMI_LARGEUR - ArmeBouleTir.DEMI_LARGEUR;
		return tmpPos;
	}


	public float getModifVitesse() {return 1;	}


	public void setProchainTir(float f) {
		if (++numeroTir > 7) numeroTir = 1;
		prochainTir = f + CADENCETIR * numeroTir;
	}


	public Vector2 getDirectionTir() {
		tmpDir.x = direction.x;
		tmpDir.y = direction.y;
		return tmpDir;
	}


	public float getAngleTir() {	return angle;	}
}
