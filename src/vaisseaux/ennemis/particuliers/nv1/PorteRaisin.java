package vaisseaux.ennemis.particuliers.nv1;

import jeu.Endless;
import jeu.Stats;
import menu.CSG;
import vaisseaux.Positionnement;
import vaisseaux.armes.ennemi.ArmeBouleEnergie;
import vaisseaux.armes.ennemi.ArmeEnnemi;
import vaisseaux.armes.typeTir.TireurPlusieurFois;
import vaisseaux.armes.typeTir.Tirs;
import vaisseaux.ennemis.CoutsEnnemis;
import vaisseaux.ennemis.Ennemis;
import assets.AssetMan;
import assets.SoundMan;

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
public class PorteRaisin extends Ennemis implements TireurPlusieurFois {
	
	// ** ** caracteristiques g�n�rales
	public static final int LARGEUR= CSG.LARGEUR_ECRAN / 7;
	public static final int DEMI_LARGEUR = LARGEUR/2;
	public static final int HAUTEUR = LARGEUR + DEMI_LARGEUR/2;
	private static final int DEMI_HAUTEUR = HAUTEUR / 2; 
	public static Pool<PorteRaisin> pool = Pools.get(PorteRaisin.class);
	public static final Tirs TIR = new Tirs(.3f);
	// ** ** animations
	private float prochainTir = 3, angleTir = 0;
	private int numeroTir = 3;

	/**
	 * Contructeur sans argument, appel� par le pool
	 */
	public PorteRaisin() {
		super();
		Positionnement.hautLarge(position, getLargeur(), getHauteur());
	}

	
	@Override
	protected int getPvMax() {
		return Stats.PV_PORTE_RAISIN;
	}
	@Override
	protected Sound getSonExplosion() {
		return SoundMan.explosionkinder;
	}

	@Override
	protected void free() {
		pool.free(this);
	}

	@Override
	public void reset() {
		Positionnement.hautLarge(position, getLargeur(), getHauteur());
		prochainTir = 3;
		angleTir = 0;
		super.reset();
	}

	@Override
	public boolean mouvementEtVerif() {
		position.y -= (Stats.V_ENN_PORTE_RAISIN * Endless.delta);
		return super.mouvementEtVerif();
	}
	
	@Override
	protected TextureRegion getTexture() {
		if (pv > Stats.PV_PORTE_RAISIN_AMOCHE)	return AssetMan.porteraisin;
		else										return AssetMan.porteraisinamoche;
	}
	
	@Override
	protected void tir() {
		TIR.tirEnRafaleGaucheEtDroite(this, 4, mort, maintenant, prochainTir);
	}

	@Override
	public int getXp() {
		return CoutsEnnemis.EnnemiPorteRaisin.COUT;
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
	public ArmeEnnemi getArme() {			return ArmeBouleEnergie.pool.obtain();	}

	@Override
	public void setProchainTir(float f) {
		angleTir++;
		prochainTir = f;
	}

	@Override
	public float getModifVitesse() {	return 1;	}

	@Override
	public float getAngleTir() {			return angleTir;	}
	
	@Override
	public Vector2 getDirectionTir() {
		tmpDir.x = 0;
		tmpDir.y = -1;
		tmpDir.rotate(angleTir++);
		return tmpDir;
	}
	
	@Override
	public Vector2 getPositionDuTir(int numeroTir) {
		tmpPos.x = (position.x + DEMI_LARGEUR - ArmeBouleEnergie.DEMI_LARGEUR);// + (direction.x * 16);
		tmpPos.y = (position.y + DEMI_LARGEUR - ArmeBouleEnergie.DEMI_LARGEUR);//+ (direction.y * 16);
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
	
	@Override
	public float getDirectionY() {
		return -Stats.V_ENN_PORTE_RAISIN;
	}

}
