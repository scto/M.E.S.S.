package vaisseaux.ennemis.particuliers.boss;

import jeu.Endless;
import jeu.Stats;
import menu.CSG;
import vaisseaux.armes.ennemi.BouleFeu;
import vaisseaux.armes.ennemi.ArmeEnnemi;
import vaisseaux.armes.typeTir.Tireur;
import vaisseaux.armes.typeTir.Tirs;
import vaisseaux.ennemis.Ennemis;
import vaisseaux.ennemis.CoutsEnnemis;
import assets.SoundMan;
import assets.animation.AnimationBossQuad;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;


public class EnnemiBossQuad extends Ennemis implements Tireur {

	// ** ** caracteristiques g�n�rales
	public static final int LARGEUR = (int) (CSG.LARGEUR_ECRAN / 2.6);
	public static final int DEMI_LARGEUR = LARGEUR/2;
	public static final int HAUTEUR = LARGEUR / 2;
	private static final int DEMI_HAUTEUR = HAUTEUR / 2;
	private static final int DECALAGE_ARME_2 = (LARGEUR /6);
	private static final int DECALAGE_ARME_3 = (LARGEUR /3)*2;
	private static final int DECALAGE_ARME_EXTERIEUR_Y = HAUTEUR / 3;
	public static int PV_MAX_PHASE2;
	public static int PV_MIN_PHASE2;
	private static int PV_MAX;
	public static Tirs tirPhase1;
	public static Tirs tirPhase2;
	public static Tirs tirPhase3;
	// ** ** caracteristiques variables.
	private float prochainTir = .8f;
	public static Pool<EnnemiBossQuad> pool = Pools.get(EnnemiBossQuad.class);
	// direction
	private float dirY = 1;
	private float dirX = -2;
	// tourelles
	private boolean explosionTourellesCentre = false; 
	private int phase = 1;
	
	public static int divisionPv = 27;
	
	public static void setLevel(int level) {
		tirPhase1 = new Tirs(.9f - (0.09f * level));
		tirPhase2 = new Tirs(.5f - (0.05f * level));
		tirPhase3 = new Tirs(.3f - (0.03f * level));
		PV_MAX = Stats.PV_BOSS_QUAD * level;
		PV_MAX_PHASE2 = PV_MAX / 3 * 2;
		PV_MIN_PHASE2 = PV_MAX / 3;
	}
	

	protected void free() {
		pool.free(this);
	}
	
	public EnnemiBossQuad() {
		super();
		init();
	}
	
	@Override
	protected int getPvMax() {		return PV_MAX;	}

	protected Sound getSonExplosion() {		return SoundMan.explosionGrosse;	}
	
	/**
	 * Ajoute � la liste
	 */
	public void init() {
		position.x = CSG.DEMI_LARGEUR_ECRAN - DEMI_LARGEUR;
		position.y = CSG.HAUTEUR_ECRAN;
	}


	public void reset() {
		super.reset();
		phase = 1;
		prochainTir = .2f;
		init();
		dirY = 1;
		dirX = 2;
		explosionTourellesCentre = false;
	}
	
	/**
	 * Exactement la m�me que dans la super classe mais �a �vite de faire des getter largeur hauteur...
	 */

	public boolean mouvementEtVerif() {
		if (position.y > CSG.HAUTEUR_ECRAN_PALLIER_2 | dirY < 1) 	position.y -= (dirY * Stats.V_ENN_BOSS_QUAD * Endless.delta);
		if (dirY < 1) 												dirY += 30 * Endless.delta;
		if (position.x + DEMI_LARGEUR > CSG.DEMI_LARGEUR_ZONE_JEU)	dirX -= 4 * Endless.delta;
		else														dirX += 4 * Endless.delta;
		position.x += dirX * Stats.V_ENN_BOSS_QUAD * Endless.delta;
		return super.mouvementEtVerif();
	}

	/**
	 * Exactement la m�me que dans la super classe mais �a �vite de faire des getter largeur hauteur...
	 */

	public void afficher(SpriteBatch batch) {
		batch.draw(AnimationBossQuad.getTexture(phase), position.x, position.y, LARGEUR, HAUTEUR);
		maintenant += Endless.delta;
	}
	

	protected void tir() {
		switch(phase) {
		case 1:			tirPhase1.tirMultiplesVersBas(this, 4, mort, maintenant, prochainTir);			break;
		case 2:			tirPhase2.tirMultiplesVersBas(this, 2, mort, maintenant, prochainTir);			break;
		case 3:			tirPhase3.tirMultiplesVersBas(this, 2, mort, maintenant, prochainTir);			break;
		}
	}
	
	

	public boolean touche(int force) {
		// si mes pvs sont inf�rieurs � �a je suis en phase 2 ou 3
		if (pv < PV_MAX_PHASE2 ) { 
			if (pv > PV_MIN_PHASE2) {
				if (explosionTourellesCentre == false) {
					explosionTourellesCentre = true;
				}
				phase = 2;
			} else {
				phase = 3;
			}
		} 
		return super.touche(force);
	}


	public int getXp() {
		return CoutsEnnemis.EnnemiBossQuad.COUT;
	}
	

	public int getHauteur() {
		return HAUTEUR;
	}


	public int getLargeur() {
		return LARGEUR;
	}


	public int getDemiHauteur() {
		return DEMI_HAUTEUR;
	}


	public int getDemiLargeur() {
		return DEMI_LARGEUR;
	}

	public ArmeEnnemi getArme() {			return BouleFeu.pool.obtain();	}
	

	public void setProchainTir(float f) {		prochainTir = f;	}


	public float getModifVitesse() {	return 1;	}
	

	public Vector2 getPositionDuTir(int numeroTir) {
		switch (numeroTir) {
		// Attention on donne en premier les exterieurs 
		case 1:
			SoundMan.playBruitage(SoundMan.tirRocket);
			dirY = -1.2f;
			tmpPos.x = position.x;
			tmpPos.y = position.y - DECALAGE_ARME_EXTERIEUR_Y;
			break;
		case 2:
			tmpPos.x = position.x + LARGEUR - BouleFeu.LARGEUR;
			tmpPos.y = position.y - DECALAGE_ARME_EXTERIEUR_Y;
			break;
		case 3:
			tmpPos.x = position.x + DECALAGE_ARME_3;
			tmpPos.y = position.y - BouleFeu.DEMI_HAUTEUR;
			break;
		case 4:
			tmpPos.x = position.x + DECALAGE_ARME_2;
			tmpPos.y = position.y - BouleFeu.DEMI_HAUTEUR;
			break;
		default:
			tmpPos.x = position.x + DEMI_LARGEUR - BouleFeu.DEMI_LARGEUR;
			tmpPos.y = position.y - BouleFeu.DEMI_HAUTEUR;
			break;
		}
		return tmpPos;
	}
	

	public void invoquer() {
		liste.add(pool.obtain());
	}
	
	@Override
	public float getDirectionY() {		return -dirY;	}
	@Override
	public float getDirectionX() {
		return dirX;
	}
}

