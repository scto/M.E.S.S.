package objets.ennemis.particuliers.nv1;

import objets.Positionnement;
import objets.armes.ennemi.ArmeEnnemi;
import objets.armes.ennemi.ArmeKinder;
import objets.armes.typeTir.TireurAngle;
import objets.armes.typeTir.Tirs;
import objets.ennemis.CoutsEnnemis;
import objets.ennemis.Ennemis;
import jeu.EndlessMode;
import jeu.Physique;
import jeu.Stats;
import menu.CSG;
import assets.SoundMan;
import assets.animation.AnimationKinder;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;


public class Kinder extends Ennemis implements TireurAngle {

	// ** ** caracteristiques g�n�rales
	public static final int LARGEUR= CSG.LARGEUR_ECRAN / 8;
	public static final int DEMI_LARGEUR = LARGEUR/2;
	public static final int HAUTEUR = LARGEUR + DEMI_LARGEUR;
	private static final int DEMI_HAUTEUR = HAUTEUR / 2; 
	private static final float VITESSE = Stats.V_ENN_KINDER;
	protected static final Tirs tir = new Tirs(.45f);
	// ** ** caracteristiques variables.
	protected float prochainTir = 1f;
	public static Pool<Kinder> pool = Pools.get(Kinder.class);
	// direction
	private Vector2 direction = new Vector2();
	protected float angle = 0;
	protected boolean gauche = true;
	
	public Kinder() {
		super();
		randPositionEtDirection();
	}

	@Override
	protected Sound getSonExplosion() {		return SoundMan.explosionkinder;	}
	
	
	@Override
	protected void free() {
		pool.free(this);
	}
	@Override
	protected int getPvMax() {
		return Stats.PV_KINDER;
	}

	
	private void randPositionEtDirection() {
		Positionnement.coteVersInterieurKinder(this, VITESSE, direction);
		angle = direction.angle();
	}

	@Override
	public void reset() {
		prochainTir = .2f;
		randPositionEtDirection();
		super.reset();
	}
	
	/**
	 * Exactement la m�me que dans la super classe mais �a �vite de faire des getter largeur hauteur...
	 */
	@Override
	public boolean mouvementEtVerif() {
		if (maintenant < AnimationKinder.TPS_ANIM_OUVERT || maintenant > 12) {
			Physique.mvtSansVerif(position, direction);
		} else {
			angle += Stats.V_ENN_KINDER * EndlessMode.delta;
		}
		return super.mouvementEtVerif();
	}
	
	@Override
	protected TextureRegion getTexture() {		return AnimationKinder.getTexture(maintenant);	}
	@Override
	protected float getAngle() {				return angle + 90;	}
	@Override
	protected void tir() {						tir.tirToutDroit(this, mort, maintenant, prochainTir);	}
	@Override
	public int getXp() {						return CoutsEnnemis.EnnemiKinder.COUT;	}
	@Override
	public int getHauteur() {					return HAUTEUR;	}
	@Override
	public int getLargeur() {					return LARGEUR;	}
	@Override
	public int getDemiHauteur() {				return DEMI_HAUTEUR;	}
	@Override
	public int getDemiLargeur() {				return DEMI_LARGEUR;	}

	@Override
	public ArmeEnnemi getArme() {			return ArmeKinder.pool.obtain();	}

	@Override
	public void setProchainTir(float f) {
		if (maintenant > 11.6 && maintenant < 12.4) {// On se pr�pare � bouger
			angle = direction.angle();
		}
		prochainTir = f;
	}

	@Override
	public float getModifVitesse() {	return 0.8f;	}

	@Override
	public float getAngleTir() {			return angle;	}
	
	@Override
	public Vector2 getDirectionTir() {
		TMP_DIR.x = -1;
		TMP_DIR.y = 0;
		TMP_DIR.rotate(angle);
		return TMP_DIR;
	}
	
	@Override
	public Vector2 getPositionDuTir(int numeroTir) {
		TMP_POS.x = (position.x + DEMI_LARGEUR - ArmeKinder.DEMI_LARGEUR);// + (direction.x * 16);
		TMP_POS.y = (position.y + DEMI_HAUTEUR - ArmeKinder.DEMI_LARGEUR);//+ (direction.y * 16);
		return TMP_POS;
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
		if (maintenant < AnimationKinder.TPS_ANIM_OUVERT || maintenant > 12) 
			return direction.x;
		return 0;
	}
}

