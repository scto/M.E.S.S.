package objets.ennemis.particuliers.nv1;

import objets.Positionnement;
import objets.armes.ennemi.ArmeBouleEnergie;
import objets.armes.ennemi.ArmeEnnemi;
import objets.armes.typeTir.TireurPlusieurFois;
import objets.armes.typeTir.Tirs;
import objets.ennemis.CoutsEnnemis;
import objets.ennemis.Ennemis;
import jeu.EndlessMode;
import jeu.Stats;
import assets.AssetMan;
import assets.SoundMan;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

public class PorteRaisin extends Ennemis implements TireurPlusieurFois {
	
	private static final int LARGEUR = Stats.LARGEUR_PORTE_RAISIN, DEMI_LARGEUR = LARGEUR/2, HAUTEUR = Stats.HAUTEUR_PORTE_RAISIN, DEMI_HAUTEUR = HAUTEUR / 2; 
	public static final Pool<PorteRaisin> POOL = Pools.get(PorteRaisin.class);
	private static final Tirs TIR = new Tirs(.3f);
	private float prochainTir = 3, angleTir = 0;
	private int numeroTir = 3;

	public PorteRaisin() {
		super();
		Positionnement.hautLarge(position, getLargeur(), getHauteur());
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
		position.y -= (Stats.V_ENN_PORTE_RAISIN * EndlessMode.delta);
		return super.mouvementEtVerif();
	}
	
	@Override
	protected TextureRegion getTexture() {
		if (pv > Stats.PV_PORTE_RAISIN_AMOCHE)	return AssetMan.porteraisin;
		else										return AssetMan.porteraisinamoche;
	}
	
	@Override
	public Vector2 getDirectionTir() {
		TMP_DIR.x = 0;
		TMP_DIR.y = -1;
		TMP_DIR.rotate(angleTir++);
		return TMP_DIR;
	}
	
	@Override
	public Vector2 getPositionDuTir(int numeroTir) {
		TMP_POS.x = (position.x + DEMI_LARGEUR - ArmeBouleEnergie.DEMI_LARGEUR);
		TMP_POS.y = (position.y + DEMI_LARGEUR - ArmeBouleEnergie.DEMI_LARGEUR);
		return TMP_POS;
	}
	
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
	public int getNumeroTir() {		return numeroTir;	}
	@Override
	public void addNombresTirs(int i) {		numeroTir += i;	}
	@Override
	public void invoquer() {		LISTE.add(POOL.obtain());	}
	@Override
	public float getDirectionY() {		return -Stats.V_ENN_PORTE_RAISIN;	}
	@Override
	protected String getLabel() {			return getClass().toString();	}
	@Override
	protected int getPvMax() {		return Stats.PV_PORTE_RAISIN;	}
	@Override
	protected Sound getSonExplosion() {		return SoundMan.explosionkinder;	}
	@Override
	protected void free() {		POOL.free(this);	}
	@Override
	protected void tir() {		TIR.tirEnRafaleGaucheEtDroite(this, 4, mort, maintenant, prochainTir);	}
	@Override
	public int getXp() {		return CoutsEnnemis.PORTE_RAISIN.COUT;	}
	@Override
	public int getHauteur() {		return HAUTEUR;	}
	@Override
	public int getLargeur() {		return LARGEUR;	}
	@Override
	public int getDemiHauteur() {		return DEMI_HAUTEUR;	}
	@Override
	public int getDemiLargeur() {		return DEMI_LARGEUR;	}
	@Override
	public ArmeEnnemi getArme() {			return ArmeBouleEnergie.pool.obtain();	}
}
