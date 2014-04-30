package elements.generic.enemies.individual.bosses;

import jeu.CSG;
import jeu.Stats;
import jeu.mode.EndlessMode;
import assets.SoundMan;
import assets.animation.AnimationPorteNef;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

import elements.generic.Invocable;
import elements.generic.behavior.Behavior;
import elements.generic.enemies.Enemy;


public class BossSat extends Enemy {
	
	public static final int LARGEUR = Stats.LARGEUR_BOSS_SAT, DEMI_LARGEUR = LARGEUR/2;
	private static final int Y_1 = (LARGEUR / 40) * 17, Y_2 = (LARGEUR / 40) * 29, Y_3 = (LARGEUR) - (int)AddBossStat.LARGEUR, Y_4 = Y_2, Y_5 = Y_1;
	private static final int X_2 = (LARGEUR / 13), X_3 = (LARGEUR / 2) - (int)AddBossStat.DEMI_LARGEUR, X_4 = (int) (LARGEUR - X_2 - AddBossStat.LARGEUR), X_5 = (int) (LARGEUR - AddBossStat.LARGEUR);
	private static final int ANGLE_2 = 140, ANGLE_3 = 90, ANGLE_4 = 40, ANGLE_5 = 0;
	public static final Invocable ref = new BossSat();
	public static Pool<BossSat> pool = Pools.get(BossSat.class);
	private int nbLance = 0;
	
	private static final int PK = 17, LVL = 1;
	protected static final float SPEED = initSpeed(8, PK);
	private static final int PV = initPv(195, PK);
	private static final int EXPLOSION = initExplosion(60, PK);
	protected static final int BASE_XP = Enemy.initXp(75, PK);
	private static final int XP = getXp(BASE_XP, LVL);
	private static final Behavior behavior = initBehavior(PK, Behavior.STRAIGHT);

	public BossSat() {
		pos.x = CSG.gameZoneWidth - 1;
		pos.y = CSG.HAUTEUR_ECRAN_PALLIER_3 - DEMI_LARGEUR;
		dir.x = -getVitesse();
		angle = -90;
	}

	
	public void reset() {
		pos.x = CSG.gameZoneWidth - 1;
		pos.y = CSG.HAUTEUR_ECRAN_PALLIER_3 - DEMI_LARGEUR;
		dir.x = -getVitesse();
		nbLance = 0;
		angle = -90;
		super.reset();
	}
	
	public void mouvementEtVerif() {
		lancerEnnemi();
		super.mouvementEtVerif();
	}
	
	private void lancerEnnemi() {
		switch (nbLance) {
		case 0:
			if (pos.x < CSG.LARGEUR_ZONE_MOINS_LARGEUR_BORD - DEMI_LARGEUR) {
				AddBossStat.pool.obtain().lancer(-1, 0, pos.x, pos.y + Y_1, 180);
				AddBossStat.pool.obtain().lancer(-0.76604444f, 0.6427876f, pos.x + X_2, pos.y + Y_2, ANGLE_2);
				if (EndlessMode.difficulty > 1) {
					AddBossStat.pool.obtain().lancer(0, 1, pos.x + X_3, pos.y + Y_3, ANGLE_3);
				}
				nbLance++;
			}
			break;
		case 1:
			if (pos.x < CSG.LARGEUR_ZONE_MOINS_LARGEUR_BORD - LARGEUR) {
				AddBossStat.pool.obtain().lancer(-1, 0, pos.x, pos.y + Y_1, 180);
				AddBossStat.pool.obtain().lancer(-0.76604444f, 0.6427876f, pos.x + X_2, pos.y + Y_2, ANGLE_2);
				if (EndlessMode.difficulty > 1) {
					AddBossStat.pool.obtain().lancer(0, 1, pos.x + X_3, pos.y + Y_3, ANGLE_3);
					AddBossStat.pool.obtain().lancer(0.76604444f, 0.6427876f, pos.x + X_4, pos.y + Y_4, ANGLE_4);
				}
				nbLance++;
			}
			break;
		case 2:
			if (pos.x < CSG.LARGEUR_ZONE_MOINS_LARGEUR_BORD - (LARGEUR + DEMI_LARGEUR)) {
				AddBossStat.pool.obtain().lancer(0, 1, pos.x + X_3, pos.y + Y_3, ANGLE_3);
				AddBossStat.pool.obtain().lancer(0.76604444f, 0.6427876f, pos.x + X_4, pos.y + Y_4, ANGLE_4);
				if (EndlessMode.difficulty > 1) {
					AddBossStat.pool.obtain().lancer(-1, 0, pos.x, pos.y + Y_1, 180);
					AddBossStat.pool.obtain().lancer(-0.76604444f, 0.6427876f, pos.x + X_2, pos.y + Y_2, ANGLE_2);
				}
				nbLance++;
			}
			break;
		case 3:
			if (pos.x < CSG.LARGEUR_ZONE_MOINS_LARGEUR_BORD - DEMI_LARGEUR) {
				AddBossStat.pool.obtain().lancer(0.76604444f, 0.6427876f, pos.x + X_4, pos.y + Y_4, ANGLE_4);
				AddBossStat.pool.obtain().lancer(0, 1, pos.x + X_3, pos.y + Y_3, ANGLE_3);
				if (EndlessMode.difficulty > 1) {
					AddBossStat.pool.obtain().lancer(-1, 0, pos.x, pos.y + Y_1, 180);
					AddBossStat.pool.obtain().lancer(-0.76604444f, 0.6427876f, pos.x + X_2, pos.y + Y_2, ANGLE_2);
				}
				nbLance++;
			}
		case 4:
			if (pos.x < CSG.LARGEUR_ZONE_MOINS_LARGEUR_BORD - LARGEUR) {
				AddBossStat.pool.obtain().lancer(1, 0, pos.x + X_5, pos.y + Y_5, ANGLE_5);
				if (EndlessMode.difficulty > 1) {
					AddBossStat.pool.obtain().lancer(0, 1, pos.x + X_3, pos.y + Y_3, ANGLE_3);
					AddBossStat.pool.obtain().lancer(0.76604444f, 0.6427876f, pos.x + X_4, pos.y + Y_4, ANGLE_4);
				}
				nbLance++;
			}
			break;
		}
	}

	@Override
	public Invocable invoquer() {
		BossSat l = pool.obtain();
		LIST.add(l);
		return l;
	}
	@Override	public float getDirectionY() {			return 0;										}
	@Override	public float getDirectionX() {			return dir.x;									}
	@Override	protected String getLabel() {			return getClass().toString();					}
	@Override	protected TextureRegion getTexture() {	return AnimationPorteNef.getTexture(pv);		}
	@Override	protected int getPvMax() {				return super.getPvBoss(PV);		}
	@Override	public int getXp() {					return XP;			}
	@Override	public int getValeurBonus() {			return BASE_XP;	}
	@Override	protected Sound getSonExplosion() {		return SoundMan.bigExplosion;					}
	@Override	public int getHalfHeight() {			return DEMI_LARGEUR;							}
	@Override	public int getHalfWidth() {				return DEMI_LARGEUR;							}
	@Override	public void free() {					pool.free(this);								}
	@Override	public int getHeight() {				return LARGEUR;									}
	@Override	public int getWidth() {					return LARGEUR;									}
	@Override	public int getExplosionCount() {		return EXPLOSION;	}
	@Override	public Behavior getBehavior() {			return behavior;	}
	@Override	public float getVitesse() {				return SPEED;	}

}
