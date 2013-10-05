package objets.ennemis.particuliers.nv1;

import objets.Positionnement;
import objets.armes.ennemi.ArmeEnnemi;
import objets.armes.ennemi.ArmeInsecte;
import objets.armes.typeTir.TireurAngle;
import objets.armes.typeTir.Tirs;
import objets.ennemis.CoutsEnnemis;
import objets.ennemis.Ennemis;
import jeu.Physique;
import jeu.Stats;
import menu.CSG;
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
		return angle;
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
		return TMP_DIR;
	}
	
	@Override
	public Vector2 getPositionDuTir(int numeroTir) {
		majVecteurTir();
		if (!tirGauche) {
			TMP_POS.x = (position.x) + (TMP_DIR.x * 16);
			TMP_POS.y = (position.y + ArmeInsecte.DEMI_HAUTEUR)+ (TMP_DIR.y * 16);
		} else {
			TMP_POS.x = (position.x + DEMI_LARGEUR) + (TMP_DIR.x * 16);
			TMP_POS.y = (position.y - ArmeInsecte.DEMI_HAUTEUR) + (TMP_DIR.y * 16);
		}
		return TMP_POS;
	}

	private void majVecteurTir() {
		TMP_DIR.x = direction.x / VITESSE;
		TMP_DIR.y = direction.y / VITESSE;
		if (direction.x < 0) TMP_DIR.rotate(90);
		else TMP_DIR.rotate(-90);
	}
	
	@Override
	public void invoquer() {
		LISTE.add(pool.obtain());
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
