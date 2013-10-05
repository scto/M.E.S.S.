package objets.ennemis.particuliers.nv1;

import objets.Positionnement;
import objets.armes.ennemi.ArmeEnnemi;
import objets.armes.ennemi.BouleBleu;
import objets.armes.typeTir.TireurPlusieurFois;
import objets.armes.typeTir.Tirs;
import objets.ennemis.CoutsEnnemis;
import objets.ennemis.Ennemis;
import jeu.EndlessMode;
import jeu.Stats;
import menu.CSG;
import assets.SoundMan;
import assets.animation.AnimationEnnemiTourne;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

public class QuiTourne extends Ennemis implements TireurPlusieurFois {
	
	// ** ** caracteristiques g�n�rales
	public static final int LARGEUR= CSG.LARGEUR_ECRAN / 13;
	public static final int DEMI_LARGEUR = LARGEUR/2;
	public static final float CADENCE_TIR = .1f;
	public static final Tirs TIR = new Tirs(CADENCE_TIR);
	protected Vector2 direction;
	public static Pool<QuiTourne> pool = Pools.get(QuiTourne.class);
	protected float prochainTir = 0;
	private int numeroTir = 0;

	/**
	 * Contructeur sans argument, appel� par le pool
	 */
	public QuiTourne() {
		super();
		Positionnement.hautLarge(position, getLargeur(), getHauteur());
		prochainTir = 2;
		direction = new Vector2(0,-getVitesse());
	}
	
	@Override
	protected int getPvMax() {
		return Stats.PV_QUI_TOURNE;
	}
	
	@Override
	protected void free() {
		pool.free(this);
	}
	
	@Override
	public void reset() {
		Positionnement.hautLarge(position, getLargeur(), getHauteur());
		direction.x = 0;
		direction.y = -getVitesse();
		prochainTir = 3;
		super.reset();
	}

	@Override
	protected Sound getSonExplosion() {		return SoundMan.explosionPetite;	}
	@Override
	protected TextureRegion getTexture() {	return AnimationEnnemiTourne.getTexture(maintenant);	}

	@Override
	public boolean mouvementEtVerif() {
		position.y += (CSG.DEMI_HAUTEUR_ECRAN - position.y) * (EndlessMode.delta/2);
		if (maintenant < 10)		direction.rotate(EndlessMode.delta * getVitesse());
		position.x += direction.x * EndlessMode.delta;
		position.y += direction.y * EndlessMode.delta;
		return super.mouvementEtVerif();
	}
	
	@Override
	protected float getVitesse() {
		return Stats.V_ENN_QUI_TOURNE;
	}
	protected float getDemiVitesse() {
		return Stats.DEMI_V_ENN_QUI_TOURNE;
	}

	@Override
	protected void tir() {		TIR.tirEnRafale(this, 4, mort, maintenant, prochainTir);	}

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
	public ArmeEnnemi getArme() {			return BouleBleu.pool.obtain();	}
	@Override
	public void setProchainTir(float f) {		prochainTir = f;	}
	@Override
	public float getModifVitesse() {	return 0.005f;	}
	@Override
	public float getAngleTir() {			return 0;	}
	@Override
	public Vector2 getDirectionTir() {		return direction;	}
	
	@Override
	public Vector2 getPositionDuTir(int numeroTir) {
		TMP_POS.x = (position.x + DEMI_LARGEUR - BouleBleu.DEMI_LARGEUR);
		TMP_POS.y = (position.y + DEMI_LARGEUR - BouleBleu.DEMI_LARGEUR);
		return TMP_POS;
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
