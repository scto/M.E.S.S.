package elements.generic.enemies.individual.bosses;

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

import elements.generic.Invocable;
import elements.generic.behavior.Behavior;
import elements.generic.behavior.OnTheTop;
import elements.generic.enemies.Enemy;
import elements.generic.enemies.Progression;
import elements.generic.weapons.enemies.Fireball;
import elements.generic.weapons.enemies.EnemyWeapon;
import elements.generic.weapons.patterns.Tireur;
import elements.generic.weapons.patterns.Tirs;
import elements.generic.weapons.player.PlayerWeapon;
import elements.particular.particles.Particles;

public class BossQuad extends Enemy implements Tireur {

	private static final int LARGEUR = Stats.LARGEUR_BOSS_QUAD, DEMI_LARGEUR = LARGEUR/2;
	private static final int HAUTEUR = Stats.HAUTEUR_BOSS_QUAD, DEMI_HAUTEUR = HAUTEUR / 2;
	private static final int DECALAGE_ARME_2 = (LARGEUR /6), DECALAGE_ARME_3 = (LARGEUR /3)*2, DECALAGE_ARME_EXTERIEUR_Y = HAUTEUR / 3;
	public static final Invocable ref = new BossQuad();
	private static int pvMaxPhase2, pvMinPhase2;
	private static Tirs tirPhase1, tirPhase2, tirPhase3;
	public static Pool<BossQuad> pool = Pools.get(BossQuad.class);
	private float prochainTir = .8f;
	private int phase = 1;
	private static final Behavior behavior = new OnTheTop();
	
	public BossQuad() {
		super();
		init();
	}
	
	public void init() {
		pos.x = CSG.screenHalfWidth - DEMI_LARGEUR;
		pos.y = CSG.SCREEN_HEIGHT;
		dir.y = -getVitesse();
		dir.x = -getVitesse()*2;
	}
	
	public void reset() {
		super.reset();
		phase = 1;
		prochainTir = .2f;
		init();
	}

	public void afficher(SpriteBatch batch) {
		batch.draw(AnimationBossQuad.getTexture(phase), pos.x, pos.y, LARGEUR, HAUTEUR);
	}

	protected void tir() {
		switch(phase) {
		case 1:			tirPhase1.tirMultiplesVersBas(this, 4, now, prochainTir, false);			break;
		case 2:			tirPhase2.tirMultiplesVersBas(this, 2, now, prochainTir, false);			break;
		case 3:			tirPhase3.tirMultiplesVersBas(this, 2, now, prochainTir, false);			break;
		}
	}

	private static boolean tmp;
	@Override
	public boolean stillAlive(PlayerWeapon p) {
		tmp = super.stillAlive(p);
		
		switch(phase) {
		case 1:		
			if (pv < pvMaxPhase2)
				incrementPhase();
			break;
		case 2:
			if (pv < pvMinPhase2)
				incrementPhase();
			break;
		}
		return tmp;
	}

	private void incrementPhase() {
		phase++;
		for (int i = 0; i < 15 * phase; i++)
			Particles.smoke(pos.x + (CSG.R.nextFloat() * LARGEUR), pos.y + (CSG.R.nextFloat() * HAUTEUR), false);
	}

	public Vector2 getPositionDuTir(int numeroTir) {
		switch (numeroTir) {
		// Attention on donne en premier les exterieurs 
		case 1:
			SoundMan.playBruitage(SoundMan.tirRocket);
			dir.y += getVitesse();
			pos.y++;
			pos.y++;
			TMP_POS.x = pos.x;
			TMP_POS.y = pos.y - DECALAGE_ARME_EXTERIEUR_Y;
			break;
		case 2:
			TMP_POS.x = pos.x + LARGEUR - Fireball.WIDTH;
			TMP_POS.y = pos.y - DECALAGE_ARME_EXTERIEUR_Y;
			break;
		case 3:
			TMP_POS.x = pos.x + DECALAGE_ARME_3;
			TMP_POS.y = pos.y - Fireball.HALF_HEIGHT;
			break;
		case 4:
			TMP_POS.x = pos.x + DECALAGE_ARME_2;
			TMP_POS.y = pos.y - Fireball.HALF_HEIGHT;
			break;
		default:
			TMP_POS.x = pos.x + DEMI_LARGEUR - Fireball.HALF_WIDTH;
			TMP_POS.y = pos.y - Fireball.HALF_HEIGHT;
			break;
		}
		return TMP_POS;
	}
	

	@Override
	public Invocable invoquer() {
		BossQuad l = pool.obtain();
		LIST.add(l);
		return l;
	}
	@Override
	public float getDirectionY() {			return -dir.y;	}
	@Override
	public float getDirectionX() {			return dir.x;	}
	@Override
	public int getXp() {					return 200;	}
	@Override
	public void free() {
		pool.free(this);
		Progression.nextNormalWavesCheck = EndlessMode.now;
	}
	@Override
	public int getHeight() {				return HAUTEUR;	}
	@Override
	public int getWidth() {					return LARGEUR;	}
	@Override
	protected int getPvMax() {			
		tirPhase1 = new Tirs(.9f - (0.09f * EndlessMode.modeDifficulte));
		tirPhase2 = new Tirs(.5f - (0.05f * EndlessMode.modeDifficulte));
		tirPhase3 = new Tirs(.3f - (0.03f * EndlessMode.modeDifficulte));
		pvMaxPhase2 = getPvBoss(Stats.PV_BOSS_QUAD) / 3 * 2;
		pvMinPhase2 = getPvBoss(Stats.PV_BOSS_QUAD) / 3;
		return super.getPvBoss(Stats.PV_BOSS_QUAD);
	}
	@Override
	protected String getLabel() {			return getClass().toString();	}
	@Override
	public int getHalfHeight() {			return DEMI_HAUTEUR;			}
	@Override
	public int getHalfWidth() {				return DEMI_LARGEUR;			}
	@Override
	public EnemyWeapon getArme() {			return Fireball.POOL.obtain();	}
	@Override
	public float getModifVitesse() {		return 1;						}
	@Override
	protected Sound getSonExplosion() {		return SoundMan.bigExplosion;	}
	@Override
	public void setProchainTir(float f) {	prochainTir = f;				}
	@Override
	public int getValeurBonus() {			return 200;	}
	public void die() {
		Progression.nextNormalWavesCheck = EndlessMode.now;
		Progression.bossJustPoped = false;
		super.die();
	}

	@Override
	public int getExplosionCount() {
		return 180;
	}

	@Override
	public Behavior getBehavior() {
		return behavior;
	}
	@Override
	public float getVitesse() {
		return Stats.V_ENN_BOSS_QUAD;
	}
}

