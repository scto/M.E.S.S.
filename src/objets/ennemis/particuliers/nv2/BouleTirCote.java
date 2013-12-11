package objets.ennemis.particuliers.nv2;

import objets.Positionnement;
import objets.armes.ennemi.ArmeBouleTir;
import objets.armes.ennemi.ArmeEnnemi;
import objets.armes.typeTir.TireurAngle;
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
		direction.y = -getVitesse();
		prochainTir = 1;
		numeroTir = 1;
		Positionnement.hautMoyen(this);
		angle = CSG.DEMI_LARGEUR_ZONE_JEU - (position.x + LARGEUR*2);
		angle /= 4;
		direction.rotate(angle);
		angle += 180;
	}

	@Override
	protected float getAngle() {
		return angle;
	}
	
	protected float getVitesse() {
		return Stats.V_BOULE_TIR_COTE;
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
		System.out.println("BouleTirCote.tir()");
		TIR.tirSurCote(this, mort, maintenant, prochainTir);
	}

	public int getXp() {			return CoutsEnnemis.EnnemiLaserCoteNv2.COUT;	}

	public int getHauteur() {		return LARGEUR;	}

	public int getLargeur() {		return LARGEUR;	}

	public int getDemiHauteur() {	return DEMI_LARGEUR;	}

	public int getDemiLargeur() {	return DEMI_LARGEUR;	}

	public void invoquer() {		LISTE.add(pool.obtain());	}

	public ArmeEnnemi getArme() {		return ArmeBouleTir.pool.obtain();	}

	public Vector2 getPositionDuTir(int numeroTir) {
		TMP_POS.x = position.x + DEMI_LARGEUR - ArmeBouleTir.DEMI_LARGEUR; 
		TMP_POS.y = position.y + DEMI_LARGEUR - ArmeBouleTir.DEMI_LARGEUR;
		return TMP_POS;
	}

	public float getModifVitesse() {return 1;	}

	public void setProchainTir(float f) {
		if (++numeroTir > 7) numeroTir = 1;
		prochainTir = f + CADENCETIR * numeroTir;
	}

	public Vector2 getDirectionTir() {
		TMP_DIR.x = direction.x;
		TMP_DIR.y = direction.y;
		return TMP_DIR;
	}

	public float getAngleTir() {		return angle;	}
	@Override
	public float getDirectionY() {		return direction.y;	}
	@Override
	public float getDirectionX() {		return direction.x;	}
	@Override
	protected String getLabel() {			return getClass().toString();	}
}
