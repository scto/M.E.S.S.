package elements.generic.enemies.individual.bosses;

import java.util.Random;

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

import elements.generic.Invocable;
import elements.generic.behavior.Behavior;
import elements.generic.behavior.MineBehavior;
import elements.generic.enemies.Enemy;
import elements.generic.enemies.Progression;
import elements.generic.weapons.enemies.ArmeBossMine;
import elements.generic.weapons.enemies.Mine;
import elements.generic.weapons.enemies.EnemyWeapon;
import elements.generic.weapons.patterns.TireurAngle;
import elements.generic.weapons.patterns.Tirs;
import elements.generic.weapons.player.PlayerWeapon;
import elements.particular.particles.Particles;

public class BossMine extends Enemy implements TireurAngle {

	private static final int LARGEUR = Stats.LARGEUR_BOOS_MINE, DEMI_LARGEUR = LARGEUR / 2, HAUTEUR = Stats.HAUTEUR_BOOS_MINE, DEMI_HAUTEUR = HAUTEUR / 2;
	private static final Tirs tirPhase1 = new Tirs(.1f), tirPhase2 = new Tirs(.75f);
	private static final Vector2 tmpDirectionTir = new Vector2();
	private float tpsP2 = 0, prochainTir = 3f;
	public static final Pool<BossMine> POOL = Pools.get(BossMine.class);
	public static final Invocable ref = new BossMine();
	private static int pvPhase2;
	// direction
	private boolean versDroite = false;
	private int phase = 1, nbMinesEtDisspersion = 5;
	private static Random r = new Random();
	private static final Behavior behavior = new MineBehavior();


	public BossMine() {
		angle = 0;
		pos.x = CSG.gameZoneHalfWidth - DEMI_LARGEUR;
		pos.y = CSG.SCREEN_HEIGHT;
	}
	
	public void reset() {
		angle = 0;
		versDroite = false;
		phase = 1;
		prochainTir = 3f;
		tpsP2 = 0;
		pos.x = CSG.gameZoneHalfWidth - DEMI_LARGEUR;
		pos.y = CSG.SCREEN_HEIGHT;
		super.reset();
	}

	public void afficher(SpriteBatch batch) {
		if (pv > pvPhase2)
			batch.draw(AnimationBossMine.getTexture(pv, pvPhase2), pos.x, pos.y, LARGEUR, HAUTEUR);
		else
			batch.draw(AnimationBossMine.getTexture(pv, pvPhase2), pos.x, pos.y,
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
	public boolean isOnPlayer() {
		// pendant deux secondes au début de la phase deux on ne check pas la collision avec le joueur, c'était la façon la plus simple de ne pas s'embeter avec le rectangle de collision qui ne coincide plus avec le sprite qui lui fait une rotation 
		if (phase == 1 || tpsP2 > 2)
			return super.isOnPlayer();
		return false;
	}

	protected void tir() {
		switch (phase) {
		case 1:
			tirPhase1.tirEventail(this, 5, 5, now, prochainTir, false);
			if (versDroite) {
				angle += EndlessMode.delta * 40;
				if (angle > 17) {
					versDroite = false;
					setProchainTir(now + tirPhase1.cadence * 4);
				}
			} else {
				angle -= EndlessMode.delta * 40;
				if (angle < -17) {
					setProchainTir(now + tirPhase1.cadence * 4);
					versDroite = true;
				}
			}
			break;
		case 2:
			nbMinesEtDisspersion = r.nextInt(5) + 3;
			tirPhase2.tirEventail(this, nbMinesEtDisspersion, 10 + nbMinesEtDisspersion * 4, now, prochainTir, false);
			break;
		}
	}

	@Override
	public boolean stillAlive(PlayerWeapon p) {
		if (pv >= pvPhase2) {
			phase = 1;
		} else if (phase != 2) {
			// pour lui laisser le temps de se retourner
			prochainTir += 2f;
			phase = 2;
		}
		return super.stillAlive(p);
	}

	@Override
	public EnemyWeapon getArme() {
		if (phase == 1)		return ArmeBossMine.POOL.obtain();
		else				return Mine.POOL.obtain();
	}

	@Override
	public void setProchainTir(float f) {
		SoundMan.playBruitage(SoundMan.tirRocket);
		prochainTir = f;
	}

	@Override
	public Vector2 getDirectionTir() {
		tmpDirectionTir.x = 0;
		if (phase == 1)
			tmpDirectionTir.y = -1;
		else
			tmpDirectionTir.y = 1;
		return tmpDirectionTir;
	}

	@Override
	public Vector2 getPositionDuTir(int numeroTir) {
		if (phase == 1)
			TMP_POS.x = (pos.x + DEMI_LARGEUR - ArmeBossMine.HALF_WIDTH);
		else
			TMP_POS.x = (pos.x + DEMI_LARGEUR - Mine.HALF_WIDTH);
		TMP_POS.y = pos.y;
		return TMP_POS;
	}

	@Override	protected void explode() {				Particles.explosionBlue(this);	}
	@Override	public int getXp() {					return 200;	}
	@Override	public int getHeight() {				return HAUTEUR;	}
	@Override	public int getWidth() {					return LARGEUR;	}
	@Override	public int getHalfHeight() {			return DEMI_HAUTEUR;	}
	@Override	public int getHalfWidth() {				return DEMI_LARGEUR;	}
	@Override	public float getModifVitesse() {		return 1;	}
	@Override	public float getAngleTir() {			return angle;	}
	@Override	public float getDirectionY() {			return -Stats.V_ENN_BOSS_MINE;	}
	@Override	protected String getLabel() {			return getClass().toString();	}
	@Override	protected Sound getSonExplosion() {		return SoundMan.bigExplosion;	}
	@Override	public int getValeurBonus() {			return 200;	}
	@Override	public int getExplosionCount() {		return 180;	}
	@Override	public Behavior getBehavior() {			return behavior;	}
	@Override	public int getPhase() {					return phase;	}
	@Override
	public Invocable invoquer() {
		BossMine l = POOL.obtain();
		LIST.add(l);
		return l;
	}
	@Override
	protected int getPvMax() {
		pvPhase2 = getPvBoss(Stats.PV_BOSS_MINE) / 2;
		return super.getPvBoss(Stats.PV_BOSS_MINE);
	}
	@Override
	public void free() {					
		POOL.free(this);
	}
	
	@Override
	public void die() {
		Progression.nextNormalWavesCheck = EndlessMode.now;
		Progression.bossJustPoped = false;
		super.die();
	}

}
