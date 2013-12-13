package objets.ennemis.particuliers.boss;

import objets.Positionnement;
import objets.armes.ennemi.ArmeEnnemi;
import objets.armes.ennemi.BouleFeu;
import objets.armes.ennemi.BouleFeuPetite;
import objets.armes.typeTir.TireurAngle;
import objets.armes.typeTir.Tirs;
import objets.ennemis.Ennemis;
import jeu.CSG;
import jeu.Physique;
import jeu.Stats;
import assets.SoundMan;
import assets.animation.AnimationOmbrelle;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

public class Ombrelle extends Ennemis implements TireurAngle {
	
	public static final int LARGEUR = (int) (CSG.LARGEUR_ECRAN / 2.1f), DEMI_LARGEUR = LARGEUR/2;
	public static final int HAUTEUR = (int) (LARGEUR - (DEMI_LARGEUR/1.5f)), DEMI_HAUTEUR = HAUTEUR / 2;
	public static final float CADENCE = 0.5f;
	public static final Tirs tir = new Tirs(CADENCE);
	private Vector2 direction = new Vector2();
	protected float prochainTir = .1f;
	public static Pool<Ombrelle> pool = Pools.get(Ombrelle.class);
	private float angle = 0;
	
	public Ombrelle() {
		super();
		init();
	}
	
	public void init() {
		direction.x = 0;
		direction.y = -Stats.V_ENN_OMBRELLE;
		prochainTir = .1f;
		Positionnement.milieu(this);
	}
	
	@Override
	public void reset() {
		super.reset();
		init();
	}
	
	@Override
	public boolean mouvementEtVerif() {
		angle = Physique.mvtOmbrelle(this, direction);
		return super.mouvementEtVerif();
	}
	
	@Override
	protected void tir() {
		tir.tirOmbrelle(this, 11, 10.8f, mort, maintenant, prochainTir, 7);
	}
	@Override
	protected float getAngle() {		return angle + 90;	}
	@Override
	protected TextureRegion getTexture() {		return AnimationOmbrelle.getTexture(pv);	}
	@Override
	protected int getPvMax() {					return Stats.PV_OMBRELLE;	}
	@Override
	public int getXp() {						return 0;	}
	@Override
	protected Sound getSonExplosion() {		return SoundMan.explosionGrosse;	}
	@Override
	public int getHauteur() {			return HAUTEUR;	}
	@Override
	public int getLargeur() {			return LARGEUR;	}
	@Override
	public int getDemiHauteur() {		return DEMI_HAUTEUR;	}
	@Override
	public int getDemiLargeur() {		return DEMI_LARGEUR;	}
	@Override
	public void invoquer() { LISTE.add(pool.obtain());	}
	@Override
	protected void free() { pool.free(this);	}
	@Override
	public float getDirectionY() {		return direction.y;	}
	@Override
	public ArmeEnnemi getArme() {		return BouleFeuPetite.pool.obtain();	}
	@Override
	public Vector2 getPositionDuTir(int numeroTir) {		
		if (numeroTir != 6) {
			TMP_POS.x = (position.x + DEMI_LARGEUR) - BouleFeuPetite.DEMI_LARGEUR;
			TMP_POS.y = (position.y + DEMI_HAUTEUR) - BouleFeuPetite.DEMI_LARGEUR;
			TMP_DIR.y = DEMI_HAUTEUR - BouleFeuPetite.DEMI_LARGEUR;
		} else {
			TMP_POS.x = (position.x + DEMI_LARGEUR) - BouleFeu.DEMI_LARGEUR;
			TMP_POS.y = (position.y + DEMI_HAUTEUR) - BouleFeu.DEMI_HAUTEUR;
			TMP_DIR.y = DEMI_HAUTEUR ;
		}
		TMP_DIR.x = 0;
		// On additionne pour partir du haut de l'ennemi
		TMP_DIR.rotate(angle+90);
		TMP_POS.x += TMP_DIR.x;
		TMP_POS.y += TMP_DIR.y;
		return TMP_POS;
	}
	@Override
	public float getModifVitesse() {		return 200f;	}
	@Override
	public void setProchainTir(float f) { prochainTir = f;	}
	@Override
	public float getAngleTir() {		return angle - 90;	}
	@Override
	public Vector2 getDirectionTir() {		
		TMP_DIR.x = direction.x;
		TMP_DIR.y = direction.y;
		TMP_DIR.nor();
		return TMP_DIR;
	}
	
	@Override
	protected String getLabel() {
		return getClass().toString();
	}
}
