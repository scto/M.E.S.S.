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
import assets.SoundMan;
import assets.animation.AnimationKinder;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;


public class Kinder extends Ennemis implements TireurAngle {

	private static final int LARGEUR = Stats.LARGEUR_KINDER, DEMI_LARGEUR = LARGEUR/2; 
	private static final float VITESSE = Stats.V_ENN_KINDER;
	protected static final Tirs tir = new Tirs(.45f);
	public static Pool<Kinder> pool = Pools.get(Kinder.class);
	private Vector2 direction = new Vector2();
	protected float prochainTir = 1f;
	protected boolean gauche = true;
	protected float angle = 0;
	
	public Kinder() {
		super();
		randPositionEtDirection();
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
	public void setProchainTir(float f) {
		if (maintenant > 11.6 && maintenant < 12.4) {// On se prepare a bouger
			angle = direction.angle();
		}
		prochainTir = f;
	}

	@Override
	public Vector2 getDirectionTir() {
		TMP_DIR.x = -1;
		TMP_DIR.y = 0;
		TMP_DIR.rotate(angle);
		return TMP_DIR;
	}
	
	@Override
	public Vector2 getPositionDuTir(int numeroTir) {
		TMP_POS.x = (position.x + DEMI_LARGEUR - ArmeKinder.DEMI_LARGEUR);
		TMP_POS.y = (position.y + DEMI_LARGEUR - ArmeKinder.DEMI_LARGEUR);
		return TMP_POS;
	}
	
	@Override
	public float getDirectionX() {
		if (maintenant < AnimationKinder.TPS_ANIM_OUVERT || maintenant > 12) 
			return direction.x;
		return 0;
	}
	
	@Override
	protected void tir() {						tir.tirToutDroit(this, mort, maintenant, prochainTir);	}
	@Override
	protected TextureRegion getTexture() {		return AnimationKinder.getTexture(maintenant);	}
	@Override
	public int getXp() {						return CoutsEnnemis.EnnemiKinder.COUT;	}
	@Override
	protected Sound getSonExplosion() {			return SoundMan.explosionkinder;	}
	@Override
	public ArmeEnnemi getArme() {				return ArmeKinder.pool.obtain();	}
	@Override
	protected String getLabel() {				return getClass().toString();	}
	@Override
	public void invoquer() {					LISTE.add(pool.obtain());	}
	@Override
	protected int getPvMax() {					return Stats.PV_KINDER;	}
	@Override
	public int getDemiHauteur() {				return DEMI_LARGEUR;	}
	@Override
	public int getDemiLargeur() {				return DEMI_LARGEUR;	}
	@Override
	public float getDirectionY() {				return direction.y;	}
	@Override
	protected float getAngle() {				return angle + 90;	}
	@Override
	protected void free() {						pool.free(this);	}
	@Override
	public int getHauteur() {					return LARGEUR;	}
	@Override
	public int getLargeur() {					return LARGEUR;	}
	@Override
	public float getAngleTir() {				return angle;	}
	@Override
	public float getModifVitesse() {			return 0.8f;	}
}

