package objets.ennemis.particuliers.boss;

import java.util.Random;

import objets.armes.ennemi.ArmeBossMine;
import objets.armes.ennemi.ArmeEnnemi;
import objets.armes.ennemi.ArmeMine;
import objets.armes.typeTir.TireurAngle;
import objets.armes.typeTir.Tirs;
import objets.ennemis.CoutsEnnemis;
import objets.ennemis.Ennemis;
import jeu.CSG;
import jeu.EndlessMode;
import jeu.Stats;
import assets.SoundMan;
import assets.animation.AnimationBossMine;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

public class BossMine extends Ennemis implements TireurAngle {

	private static final int LARGEUR = Stats.LARGEUR_BOOS_MINE, DEMI_LARGEUR = LARGEUR / 2;
	private static final int HAUTEUR = Stats.HAUTEUR_BOOS_MINE, DEMI_HAUTEUR = HAUTEUR / 2;
	private static final Tirs tirPhase1 = new Tirs(.1f);
	private static final Tirs tirPhase2 = new Tirs(ArmeMine.CADENCETIR);
	private static final Vector2 tmpDirectionTir = new Vector2();
	private float tpsP2 = 0;
	private float prochainTir = 3f;
	public static Pool<BossMine> pool = Pools.get(BossMine.class);
	// direction
	private float dirY = 1;
	private float angle;
	private boolean versDroite = false;
	private int phase = 1;
	private static Random r = new Random();
	private int nbMinesEtDisspersion = 5;

	public BossMine() {
		angle = 0;
		position.x = CSG.DEMI_LARGEUR_ZONE_JEU - DEMI_LARGEUR;
		position.y = CSG.HAUTEUR_ECRAN;
	}
	
	public void reset() {
		angle = 0;
		versDroite = false;
		phase = 1;
		prochainTir = 3f;
		dirY = 1;
		tpsP2 = 0;
		position.x = CSG.DEMI_LARGEUR_ZONE_JEU - DEMI_LARGEUR;
		position.y = CSG.HAUTEUR_ECRAN;
		super.reset();
	}

	public boolean mouvementEtVerif() {
		if (phase == 1) {
			if (position.y > CSG.HAUTEUR_ECRAN_PALLIER_3 | dirY < 1)
				position.y -= (dirY * Stats.V_ENN_KINDER * EndlessMode.delta);
			if (dirY < 1)
				dirY += 30 * EndlessMode.delta;
		} else {
			if (angle < 180)
				angle += EndlessMode.delta * 100;
		}
		return super.mouvementEtVerif();
	}

	public void afficher(SpriteBatch batch) {
		maintenant += EndlessMode.delta;
		if (pv > Stats.DEUXTIERS_PV_BOSS_MINE)
			batch.draw(AnimationBossMine.getTexture(pv), position.x, position.y, LARGEUR, HAUTEUR);
		else
			batch.draw(AnimationBossMine.getTexture(pv), position.x, position.y,
			// CENTRE DE LA ROTATION EN X // CENTRE DE LA ROTATION EN Y
					DEMI_LARGEUR, DEMI_HAUTEUR,
					// LARGEUR DU RECTANGLE AFFICHE HAUTEUR DU RECTANGLE
					LARGEUR, HAUTEUR,
					// scaleX the scale of the rectangle around originX/originY in x ET Y
					4, 0.25f,
					// L'ANGLE DE ROTATION
					angle - 90,
					// FLIP OU PAS
					false);
		if (phase == 2)
			tpsP2 += EndlessMode.delta;
	}
	
	@Override
	public boolean checkJoueur() {
		// pendant deux secondes au début de la phase deux on ne check pas la collision avec le joueur, c'était la façon la plus simple de ne pas s'embeter avec le rectangle de collision qui ne coincide plus avec le sprite qui lui fait une rotation 
		if (phase == 1 || tpsP2 > 2)
			return super.checkJoueur();
		return false;
	}

	protected void tir() {
		switch (phase) {
		case 1:
			tirPhase1.tirEventail(this, 5, 10, mort, maintenant, prochainTir);
			if (versDroite) {
				angle += EndlessMode.delta * 40;
				if (angle > 20)
					versDroite = false;
			} else {
				angle -= EndlessMode.delta * 40;
				if (angle < -20)
					versDroite = true;
			}
			break;
		case 2:
			nbMinesEtDisspersion = r.nextInt(5) + 3;
			tirPhase2.tirEventail(this, nbMinesEtDisspersion, 10 + nbMinesEtDisspersion * 4, mort, maintenant, prochainTir);
			break;
		}
	}

	public boolean touche(int force) {
		if (pv > Stats.DEUXTIERS_PV_BOSS_MINE) {
			phase = 1;
		} else if (phase != 2) {
			// pour lui laisser le temps de se retourner
			prochainTir += 2f;
			phase = 2;
		}
		return super.touche(force);
	}

	public ArmeEnnemi getArme() {
		if (phase == 1)		return ArmeBossMine.pool.obtain();
		else				return ArmeMine.pool.obtain();
	}

	public void setProchainTir(float f) {
		SoundMan.playBruitage(SoundMan.tirRocket);
		prochainTir = f;
	}

	public Vector2 getDirectionTir() {
		tmpDirectionTir.x = 0;
		if (phase == 1)
			tmpDirectionTir.y = -1;
		else
			tmpDirectionTir.y = 1;
		return tmpDirectionTir;
	}

	public Vector2 getPositionDuTir(int numeroTir) {
		if (phase == 1)
			TMP_POS.x = (position.x + DEMI_LARGEUR - ArmeBossMine.DEMI_LARGEUR);
		else
			TMP_POS.x = (position.x + DEMI_LARGEUR - ArmeMine.DEMI_LARGEUR);
		TMP_POS.y = position.y;
		return TMP_POS;
	}

	public int getXp() {					return CoutsEnnemis.EnnemiBossMine.COUT;	}
	public int getHauteur() {				return HAUTEUR;	}
	public int getLargeur() {				return LARGEUR;	}
	public int getDemiHauteur() {			return DEMI_HAUTEUR;	}
	public int getDemiLargeur() {			return DEMI_LARGEUR;	}
	public float getModifVitesse() {		return 1;	}
	public float getAngleTir() {			return angle;	}
	public void invoquer() {				LISTE.add(pool.obtain());	}
	@Override
	public float getDirectionY() {			return -dirY * Stats.V_ENN_KINDER;	}
	@Override
	protected String getLabel() {			return getClass().toString();	}
	@Override
	protected int getPvMax() {				return super.getPvBoss(Stats.PV_BOSS_MINE);	}
	protected void free() {					pool.free(this);	}
	protected Sound getSonExplosion() {		return SoundMan.explosionGrosse;	}
}
