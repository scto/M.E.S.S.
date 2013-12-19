package objets.ennemis.particuliers.nv1;

import objets.Positionnement;
import objets.armes.ennemi.ArmeEnnemi;
import objets.armes.ennemi.ArmesFragmentation;
import objets.armes.typeTir.TireurAngle;
import objets.armes.typeTir.Tirs;
import objets.ennemis.CoutsEnnemis;
import objets.ennemis.Ennemis;
import jeu.CSG;
import jeu.Physique;
import jeu.Stats;
import assets.SoundMan;
import assets.animation.AnimationCylon;
import assets.animation.AnimationCylonCasse;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

public class Cylon extends Ennemis implements TireurAngle {
	
	private static final int LARGEUR = Stats.LARGEUR_CYLON, DEMI_LARGEUR = LARGEUR/2;
	private Vector2 direction = new Vector2(0,-1);
	public static Pool<Cylon> pool = Pools.get(Cylon.class);
	private static final float CADENCE = 3f, VITESSE = Stats.V_ENN_CYLON;
	private static final Tirs tir = new Tirs(CADENCE);
	private float prochainTir = 3f;
	private float angle = 0;

	public Cylon() {
		super();
		Positionnement.hautLarge(position, getLargeur(), getHauteur());
		if (position.x + DEMI_LARGEUR < CSG.DEMI_LARGEUR_ECRAN)			direction.x = 0.26f * VITESSE;
		else 			direction.x = -0.26f * VITESSE;
		direction.y = -0.83f * VITESSE;
	}
	
	public void reset() {
		Positionnement.hautLarge(position, getLargeur(), getHauteur());
		prochainTir = 2.6f;
		angle = 30;
		if (position.x + DEMI_LARGEUR < CSG.DEMI_LARGEUR_ECRAN){
			direction.x = 0.26f * VITESSE;
		} else {
			direction.x = -0.26f * VITESSE;
		}
		direction.y = -0.83f * VITESSE;
		super.reset();
	}

	protected TextureRegion getTexture() {
		if (pv > Stats.DEMI_PV_CYLON) return AnimationCylon.getTexture(maintenant);
		else return AnimationCylonCasse.getTexture(maintenant);
	}

	public boolean mouvementEtVerif() {
		Physique.mvtSansVerif(position, direction);
		return super.mouvementEtVerif();
	}
	
	public Vector2 getPositionDuTir(int numeroTir) {
		TMP_POS.x = position.x + (direction.x / 2);
		TMP_POS.y = position.y + (direction.y / 2);
		return TMP_POS;
	}

	@Override
	public float getDirectionY() {			return direction.y;	}
	@Override
	public float getDirectionX() {			return direction.x;	}
	@Override
	protected String getLabel() {			return getClass().toString();	}
	protected void tir() {					tir.tirToutDroit(this, mort, maintenant, prochainTir);	}
	public ArmeEnnemi getArme() {			return ArmesFragmentation.pool.obtain();	}
	protected Sound getSonExplosion() {		return SoundMan.explosioncylon;	}
	public int getXp() {					return CoutsEnnemis.CYLON.COUT;	}
	public void invoquer() {				LISTE.add(pool.obtain());	}
	protected int getPvMax() {				return Stats.PV_CYLON;	}
	public int getDemiHauteur() {			return DEMI_LARGEUR;	}
	public int getDemiLargeur() {			return DEMI_LARGEUR;	}
	public Vector2 getDirectionTir() {		return direction;	}
	public void setProchainTir(float f) {	prochainTir = f;	}
	protected void free() {					pool.free(this);	}
	public int getHauteur() {				return LARGEUR;	}
	public int getLargeur() {				return LARGEUR;	}
	public float getModifVitesse() {		return 0.01f;	}
	public float getAngleTir() {			return angle;	}
}
