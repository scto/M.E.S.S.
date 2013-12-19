package objets.ennemis.particuliers.boss;

import objets.armes.ennemi.ArmeEnnemi;
import objets.armes.ennemi.BouleFeu;
import objets.armes.typeTir.Tireur;
import objets.armes.typeTir.Tirs;
import objets.ennemis.CoutsEnnemis;
import objets.ennemis.Ennemis;
import jeu.CSG;
import jeu.EndlessMode;
import jeu.Stats;
import assets.SoundMan;
import assets.animation.AnimationBossQuad;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

public class BossQuad extends Ennemis implements Tireur {

	private static final int LARGEUR = Stats.LARGEUR_BOSS_QUAD, DEMI_LARGEUR = LARGEUR/2;
	private static final int HAUTEUR = Stats.HAUTEUR_BOSS_QUAD, DEMI_HAUTEUR = HAUTEUR / 2;
	private static final int DECALAGE_ARME_2 = (LARGEUR /6), DECALAGE_ARME_3 = (LARGEUR /3)*2, DECALAGE_ARME_EXTERIEUR_Y = HAUTEUR / 3;
	private static int PV_MAX_PHASE2, PV_MIN_PHASE2;
	private static Tirs tirPhase1, tirPhase2, tirPhase3;
	public static Pool<BossQuad> pool = Pools.get(BossQuad.class);
	
	private float prochainTir = .8f, dirY = 1, dirX = -2;
	private boolean explosionTourellesCentre = false; 
	private int phase = 1;
	
	public BossQuad() {
		super();
		init();
	}
	
	public void init() {
		position.x = CSG.DEMI_LARGEUR_ECRAN - DEMI_LARGEUR;
		position.y = CSG.HAUTEUR_ECRAN;
	}
	
	public static void setLevel(int level) {
		tirPhase1 = new Tirs(.9f - (0.09f * level));
		tirPhase2 = new Tirs(.5f - (0.05f * level));
		tirPhase3 = new Tirs(.3f - (0.03f * level));
		PV_MAX_PHASE2 = getPvBoss(Stats.PV_BOSS_QUAD) / 3 * 2;
		PV_MIN_PHASE2 = getPvBoss(Stats.PV_BOSS_QUAD) / 3;
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

	public boolean mouvementEtVerif() {
		if (position.y > CSG.HAUTEUR_ECRAN_PALLIER_2 | dirY < 1) 	position.y -= (dirY * Stats.V_ENN_BOSS_QUAD * EndlessMode.delta);
		if (dirY < 1) 												dirY += 30 * EndlessMode.delta;
		if (position.x + DEMI_LARGEUR > CSG.DEMI_LARGEUR_ZONE_JEU)	dirX -= 4 * EndlessMode.delta;
		else														dirX += 4 * EndlessMode.delta;
		position.x += dirX * Stats.V_ENN_BOSS_QUAD * EndlessMode.delta;
		return super.mouvementEtVerif();
	}

	public void afficher(SpriteBatch batch) {
		batch.draw(AnimationBossQuad.getTexture(phase), position.x, position.y, LARGEUR, HAUTEUR);
		maintenant += EndlessMode.delta;
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

	public Vector2 getPositionDuTir(int numeroTir) {
		switch (numeroTir) {
		// Attention on donne en premier les exterieurs 
		case 1:
			SoundMan.playBruitage(SoundMan.tirRocket);
			dirY = -1.2f;
			TMP_POS.x = position.x;
			TMP_POS.y = position.y - DECALAGE_ARME_EXTERIEUR_Y;
			break;
		case 2:
			TMP_POS.x = position.x + LARGEUR - BouleFeu.LARGEUR;
			TMP_POS.y = position.y - DECALAGE_ARME_EXTERIEUR_Y;
			break;
		case 3:
			TMP_POS.x = position.x + DECALAGE_ARME_3;
			TMP_POS.y = position.y - BouleFeu.DEMI_HAUTEUR;
			break;
		case 4:
			TMP_POS.x = position.x + DECALAGE_ARME_2;
			TMP_POS.y = position.y - BouleFeu.DEMI_HAUTEUR;
			break;
		default:
			TMP_POS.x = position.x + DEMI_LARGEUR - BouleFeu.DEMI_LARGEUR;
			TMP_POS.y = position.y - BouleFeu.DEMI_HAUTEUR;
			break;
		}
		return TMP_POS;
	}
	

	public void invoquer() {				LISTE.add(pool.obtain());	}
	@Override
	public float getDirectionY() {			return -dirY;	}
	@Override
	public float getDirectionX() {			return dirX;	}
	@Override
	public int getXp() {					return CoutsEnnemis.EnnemiBossQuad.COUT;	}
	protected void free() {					pool.free(this);	}
	public int getHauteur() {				return HAUTEUR;	}
	public int getLargeur() {				return LARGEUR;	}
	protected int getPvMax() {				return super.getPvBoss(Stats.PV_BOSS_QUAD);	}
	protected String getLabel() {			return getClass().toString();	}
	public int getDemiHauteur() {			return DEMI_HAUTEUR;	}
	public int getDemiLargeur() {			return DEMI_LARGEUR;	}
	public ArmeEnnemi getArme() {			return BouleFeu.pool.obtain();	}
	public float getModifVitesse() {		return 1;	}
	protected Sound getSonExplosion() {		return SoundMan.explosionGrosse;	}
	public void setProchainTir(float f) {	prochainTir = f;	}

}

