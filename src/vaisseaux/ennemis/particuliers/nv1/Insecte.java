package vaisseaux.ennemis.particuliers.nv1;

import jeu.Physique;
import jeu.Stats;
import menu.CSG;
import vaisseaux.Positionnement;
import vaisseaux.armes.ennemi.ArmeEnnemi;
import vaisseaux.armes.ennemi.ArmeInsecte;
import vaisseaux.armes.typeTir.TireurAngle;
import vaisseaux.armes.typeTir.Tirs;
import vaisseaux.ennemis.CoutsEnnemis;
import vaisseaux.ennemis.Ennemis;
import assets.SoundMan;
import assets.animation.AnimationInsecte;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;


public class Insecte extends Ennemis implements TireurAngle {
	
	// ** ** caracteristiques g�n�rales
	public static final int LARGEUR = CSG.LARGEUR_ECRAN / 4;
	public static final int DEMI_LARGEUR = LARGEUR/2;
	public static final int HAUTEUR = (LARGEUR / 2) + DEMI_LARGEUR;
	private static final int DEMI_HAUTEUR = HAUTEUR / 2;
	private static final float VITESSE = Stats.V_ENN_INSECTE;
	public static final Tirs tir = new Tirs(.7f);
	private float prochainTir = 1f;
	public static Pool<Insecte> pool = Pools.get(Insecte.class);
	private boolean tirGauche = true;
	private float impulsion;
	private float angle = 0;
	protected Vector2 direction = new Vector2();
	

	public Insecte() {
		super();
		initPlacement();
	}
	
	
	@Override
	protected Sound getSonExplosion() {		return SoundMan.explosionennemidebasequitir;	}
	@Override
	protected void free() {					pool.free(this);	}
	
	@Override
	public void reset() {
		prochainTir = .2f;
		initPlacement();
		super.reset();
	}

	@Override
	protected int getPvMax() {
		return  Stats.PV_INSECTE;
	}

	private void initPlacement() {
		angle = 90;
		impulsion = -10;
		Positionnement.coteVersInterieur(position, VITESSE, direction, getLargeur());
	}

	/**
	 * Exactement la m�me que dans la super classe mais �a �vite de faire des getter largeur hauteur...
	 */
	@Override
	public boolean mouvementEtVerif() {
		Physique.mvtSansVerif(position, direction);
		direction.rotate(impulsion);
		angle += impulsion;
		impulsion /= 1.2f;
		return super.mouvementEtVerif();
	}

	@Override
	public float getAngle() {
		return angle+90;
	}
	
	@Override
	protected TextureRegion getTexture() {
		return AnimationInsecte.getTexture(pv);
	}
	
	@Override
	protected void tir() {
		tir.tirToutDroit(this, mort, maintenant, prochainTir);
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
	public ArmeEnnemi getArme() {			return ArmeInsecte.pool.obtain();	}

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
		return tmpDir;
	}
	
	@Override
	public Vector2 getPositionDuTir(int numeroTir) {
		majVecteurTir();
		if (!tirGauche) {
			tmpPos.x = (position.x) + (tmpDir.x * 16);
			tmpPos.y = (position.y + ArmeInsecte.DEMI_HAUTEUR)+ (tmpDir.y * 16);
		} else {
			tmpPos.x = (position.x + DEMI_LARGEUR) + (tmpDir.x * 16);
			tmpPos.y = (position.y - ArmeInsecte.DEMI_HAUTEUR) + (tmpDir.y * 16);
		}
		return tmpPos;
	}

	private void majVecteurTir() {
		tmpDir.x = direction.x / VITESSE;
		tmpDir.y = direction.y / VITESSE;
		if (direction.x < 0) tmpDir.rotate(90);
		else tmpDir.rotate(-90);
	}
	
	@Override
	public void invoquer() {
		liste.add(pool.obtain());
	}
	
	@Override
	public float getDirectionY() {
		return direction.y;
	}
	
	@Override
	public float getDirectionX() {
		return direction.x;
	}
}
