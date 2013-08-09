package vaisseaux.ennemis.particuliers.nv3;

import jeu.Endless;
import jeu.Physique;
import jeu.Stats;
import menu.CSG;
import vaisseaux.armes.ArmeInsecte;
import vaisseaux.armes.Armes;
import vaisseaux.ennemis.Ennemis;
import vaisseaux.ennemis.TypesEnnemis;
import vaisseaux.ennemis.particuliers.EnnemiInsecte;
import assets.SoundMan;
import assets.animation.AnimationExplosion1;
import assets.particules.ParticulesExplosionPetite;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;


public class EnnemiInsecteNv3 extends Ennemis{
	
	// ** ** caracteristiques g�n�rales
	public static final int LARGEUR = CSG.LARGEUR_ECRAN / 4;
	public static final int DEMI_LARGEUR = LARGEUR/2;
	public static final int HAUTEUR = (LARGEUR / 2) + DEMI_LARGEUR;
	private static final int DEMI_HAUTEUR = HAUTEUR / 2; 
	private float dernierTir = .1f;
	private float maintenant = 0;
	public static Pool<EnnemiInsecteNv3> pool = Pools.get(EnnemiInsecteNv3.class);
	private Vector2 direction = new Vector2();
	private boolean tirGauche = true;
	private float angle = 0;
	private float impulsion;
	// ** ** particules
	private ParticulesExplosionPetite explosion;
	protected float tpsAnimationExplosion;

	public static TextureRegion getTexture(int pv) {
		if (pv < Stats.PVMAX_INSECTE_DEMI3)	return EnnemiInsecte.mauvaisEtat;
		return EnnemiInsecte.bonEtat;
	}
	
	@Override
	protected void mort() {
		SoundMan.playBruitage(SoundMan.explosionennemidebasequitir);
		if (CSG.profil.particules){
			explosion = ParticulesExplosionPetite.pool.obtain();
			explosion.setPosition(position.x + DEMI_LARGEUR, position.y + DEMI_HAUTEUR);
			explosion.start();
		} else {
			tpsAnimationExplosion = 0;
		}
	}
	
	public void init() {
		if (CSG.profil.particules & explosion == null) explosion = ParticulesExplosionPetite.pool.obtain();
		else tpsAnimationExplosion = 0;
	}
	
	@Override
	public void reset() {
		mort = false;
		pv = Stats.PVMAX_INSECTE3;
		dernierTir = .2f;
		initPlacement();
	}

	public EnnemiInsecteNv3() {
		super(Physique.getEmplacementX(DEMI_LARGEUR), CSG.HAUTEUR_ECRAN + HAUTEUR, Stats.PVMAX_INSECTE3);
		initPlacement();
	}

	private void initPlacement() {
		angle = 90;
		impulsion = -10;
		position.y = CSG.HAUTEUR_ECRAN_PALLIER_3;
		if (Math.random() > .5) {
			direction.x = -1;
			position.x = CSG.LARGEUR_ZONE_JEU;
		} else {
			direction.x = 1;
			position.x = -LARGEUR;
		}
		direction.y = 0;
	}

	/**
	 * Exactement la m�me que dans la super classe mais �a �vite de faire des getter largeur hauteur...
	 */
	@Override
	public boolean mouvementEtVerif() {
		if ( (mort && explosion.isComplete()) || Physique.mouvementDeBase(direction, position, Stats.VITESSE_ENNEMI_INSECTE3, HAUTEUR, LARGEUR) == false){
			pool.free(this);
			if (explosion != null) explosion.free();
			return false;
		}
		direction.rotate(impulsion);
		angle += impulsion;
		if (pv > Stats.PVMAX_INSECTE_DEMI3) impulsion /= 1.2f;
		else impulsion /= 1.1f;
		return true;
	}
	
	/**
	 * Exactement la m�me que dans la super classe mais �a �vite de faire des getter largeur hauteur...
	 */
	@Override
	public boolean mouvementEtVerifSansParticules() {
		if( (mort && tpsAnimationExplosion > AnimationExplosion1.tpsTotalAnimationExplosion1) || Physique.mouvementDeBase(direction, position, Stats.VITESSE_ENNEMI_INSECTE3, HAUTEUR, LARGEUR) == false){
			pool.free(this);
			return false;
		}
		direction.rotate(impulsion);
		angle += impulsion;
		if (pv > Stats.PVMAX_INSECTE_DEMI3) impulsion /= 1.2f;
		else impulsion /= 1.1f;
		return true;
	}

	/**
	 * Exactement la m�me que dans la super classe mais �a �vite de faire des getter largeur hauteur...
	 */
	@Override
	public void afficher(SpriteBatch batch) {
		maintenant += Endless.delta;
		if (mort) {
			explosion.draw(batch, Endless.delta);
			explosion.setPosition(position.x + DEMI_LARGEUR, position.y + DEMI_HAUTEUR);
		} else	batch.draw(getTexture(pv), position.x, position.y,	DEMI_LARGEUR,DEMI_LARGEUR, LARGEUR, LARGEUR, 1,1, angle, false);
	}
	/**
	 * Exactement la m�me que dans la super classe mais �a �vite de faire des getter largeur hauteur...
	 */
	@Override
	public void afficherSansParticules(SpriteBatch batch) {
		maintenant += Endless.delta;
		if (mort) {
			batch.draw(AnimationExplosion1.getTexture(tpsAnimationExplosion), position.x, position.y, LARGEUR, HAUTEUR);
			tpsAnimationExplosion += Endless.delta;
		}
		else	batch.draw(getTexture(pv), position.x, position.y,	DEMI_LARGEUR,DEMI_LARGEUR, LARGEUR, LARGEUR, 1,1, angle, false);
	}
	
	@Override
	protected void tir() {
		if (!mort && maintenant > dernierTir	+ ArmeInsecte.CADENCETIR) {
			if (tirGauche) {
				impulsion = 10;
				ArmeInsecte e = ArmeInsecte.pool.obtain();
//				e.init(position.x + (DEMI_LARGEUR-ArmeInsecte.DEMI_LARGEUR) + ArmeInsecte.LARGEUR, position.y - ArmeInsecte.DEMI_HAUTEUR, angle, direction);
				dernierTir = maintenant + ArmeInsecte.CADENCETIR + ArmeInsecte.CADENCETIR;
				Armes.listeTirsDesEnnemis.add(e);
			} else {
				impulsion = -10;
				ArmeInsecte e = ArmeInsecte.pool.obtain();
//				e.init(position.x + (DEMI_LARGEUR-ArmeInsecte.DEMI_LARGEUR) - ArmeInsecte.LARGEUR, position.y - ArmeInsecte.DEMI_HAUTEUR, angle, direction);
				dernierTir = maintenant + ArmeInsecte.CADENCETIR + ArmeInsecte.CADENCETIR;
				Armes.listeTirsDesEnnemis.add(e);
			}
			tirGauche = !tirGauche;
		}
	}

	@Override
	public Rectangle getRectangleCollision() {
		collision.set(position.x, position.y, LARGEUR, HAUTEUR);
		return collision;
	}

	@Override
	public int getXp() {
		return TypesEnnemis.EnnemiInsecteNv3.COUT;
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
}
