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

public class BouleTirCote extends Ennemis implements TireurAngle {
	
	private static final int LARGEUR = Stats.LARGEUR_BOULE_TIR_COTE, DEMI_LARGEUR = LARGEUR/2;
	private static final float CADENCETIR = .12f;
	public static final Pool<BouleTirCote> POOL = Pools.get(BouleTirCote.class);
	protected final static Tirs TIR = new Tirs(.12f); 
	protected float angle = 0, prochainTir;
	protected Vector2 direction = new Vector2();
	protected int numeroTir;
	protected boolean gauche = false;
	
	public BouleTirCote() {
		super();
		placement();
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
	
	public void setProchainTir(float f) {
		if (++numeroTir > 7) numeroTir = 1;
		prochainTir = f + CADENCETIR * numeroTir;
	}

	public Vector2 getPositionDuTir(int numeroTir) {
		TMP_POS.x = position.x + DEMI_LARGEUR - ArmeBouleTir.DEMI_LARGEUR; 
		TMP_POS.y = position.y + DEMI_LARGEUR - ArmeBouleTir.DEMI_LARGEUR;
		return TMP_POS;
	}
	
	public Vector2 getDirectionTir() {
		TMP_DIR.x = direction.x;
		TMP_DIR.y = direction.y;
		return TMP_DIR;
	}

	public void reset() {
		placement();
		super.reset();
	}

	@Override
	public boolean mouvementEtVerif() {
		Physique.mvtSansVerif(position, direction);
		return super.mouvementEtVerif();
	}

	@Override
	protected float getAngle() {		return angle;	}
	protected float getVitesse() {		return Stats.V_BOULE_TIR_COTE;	}
	@Override
	protected TextureRegion getTexture() {		return AnimationBouleBleuRouge.getTexture(maintenant);	}
	protected void tir() {		TIR.tirSurCote(this, mort, maintenant, prochainTir);	}
	public int getXp() {			return CoutsEnnemis.EnnemiLaserCoteNv2.COUT;	}
	public int getHauteur() {		return LARGEUR;	}
	public int getLargeur() {		return LARGEUR;	}
	public int getDemiHauteur() {	return DEMI_LARGEUR;	}
	public int getDemiLargeur() {	return DEMI_LARGEUR;	}
	public void invoquer() {		LISTE.add(POOL.obtain());	}
	public ArmeEnnemi getArme() {		return ArmeBouleTir.pool.obtain();	}
	public float getModifVitesse() {return 1;	}
	@Override
	protected int getPvMax() {		return Stats.PV_BOULE_COTE_PETIT;	}
	protected Sound getSonExplosion() {		return SoundMan.explosiontoupie;	}
	protected void free() {		POOL.free(this);	}
	public float getAngleTir() {		return angle;	}
	@Override
	public float getDirectionY() {		return direction.y;	}
	@Override
	public float getDirectionX() {		return direction.x;	}
	@Override
	protected String getLabel() {			return getClass().toString();	}
}
