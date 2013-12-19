package objets.ennemis.particuliers.boss;

import objets.ennemis.CoutsEnnemis;
import objets.ennemis.Ennemis;
import jeu.CSG;
import jeu.EndlessMode;
import jeu.Stats;
import assets.SoundMan;
import assets.animation.AnimationPorteNef;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;


public class BossSat extends Ennemis {
	
	public static final int LARGEUR = Stats.LARGEUR_BOSS_SAT, DEMI_LARGEUR = LARGEUR/2;
	private static final int Y_1 = (LARGEUR / 40) * 17, Y_2 = (LARGEUR / 40) * 29, Y_3 = (LARGEUR) - (int)AddBossStat.LARGEUR, Y_4 = Y_2, Y_5 = Y_1;
	private static final int X_2 = (LARGEUR / 13), X_3 = (LARGEUR / 2) - (int)AddBossStat.DEMI_LARGEUR, X_4 = (int) (LARGEUR - X_2 - AddBossStat.LARGEUR), X_5 = (int) (LARGEUR - AddBossStat.LARGEUR);
	private static final int ANGLE_2 = 140, ANGLE_3 = 90, ANGLE_4 = 40, ANGLE_5 = 0;
	public static Pool<BossSat> pool = Pools.get(BossSat.class);
	private int nbLance = 0;
	private int dirX = -1;


	public BossSat() {
		position.x = CSG.LARGEUR_ZONE_JEU;
		position.y = CSG.HAUTEUR_ECRAN_PALLIER_3 - DEMI_LARGEUR;
	}

	
	public void reset() {
		position.x = CSG.LARGEUR_ZONE_JEU;
		position.y = CSG.HAUTEUR_ECRAN_PALLIER_3 - DEMI_LARGEUR;
		dirX = -1;
		nbLance = 0;
		super.reset();
	}
	
	public boolean mouvementEtVerif() {
		position.x += dirX * EndlessMode.delta * Stats.V_ENN_PORTE_NEF;
		if (!mort)			lancerEnnemi();
		return super.mouvementEtVerif();
	}
	
	private void lancerEnnemi() {
		switch (nbLance) {
		case 0:
			if (position.x < CSG.LARGEUR_ZONE_MOINS_LARGEUR_BORD - DEMI_LARGEUR) {
				AddBossStat.pool.obtain().lancer(-1, 0, position.x, position.y + Y_1, 180);
				AddBossStat.pool.obtain().lancer(-0.76604444f, 0.6427876f, position.x + X_2, position.y + Y_2, ANGLE_2);
				if (EndlessMode.modeDifficulte > 1) {
					AddBossStat.pool.obtain().lancer(0, 1, position.x + X_3, position.y + Y_3, ANGLE_3);
				}
				nbLance++;
			}
			break;
		case 1:
			if (position.x < CSG.LARGEUR_ZONE_MOINS_LARGEUR_BORD - LARGEUR) {
				AddBossStat.pool.obtain().lancer(-1, 0, position.x, position.y + Y_1, 180);
				AddBossStat.pool.obtain().lancer(-0.76604444f, 0.6427876f, position.x + X_2, position.y + Y_2, ANGLE_2);
				if (EndlessMode.modeDifficulte > 1) {
					AddBossStat.pool.obtain().lancer(0, 1, position.x + X_3, position.y + Y_3, ANGLE_3);
					AddBossStat.pool.obtain().lancer(0.76604444f, 0.6427876f, position.x + X_4, position.y + Y_4, ANGLE_4);
				}
				nbLance++;
			}
			break;
		case 2:
			if (position.x < CSG.LARGEUR_ZONE_MOINS_LARGEUR_BORD - (LARGEUR + DEMI_LARGEUR)) {
				AddBossStat.pool.obtain().lancer(0, 1, position.x + X_3, position.y + Y_3, ANGLE_3);
				if (EndlessMode.modeDifficulte > 1) {
					AddBossStat.pool.obtain().lancer(0.76604444f, 0.6427876f, position.x + X_4, position.y + Y_4, ANGLE_4);
					AddBossStat.pool.obtain().lancer(-1, 0, position.x, position.y + Y_1, 180);
					AddBossStat.pool.obtain().lancer(-0.76604444f, 0.6427876f, position.x + X_2, position.y + Y_2, ANGLE_2);
				}
				nbLance++;
			}
			break;
		case 3:
			if (position.x < CSG.LARGEUR_ZONE_MOINS_LARGEUR_BORD - DEMI_LARGEUR) {
				AddBossStat.pool.obtain().lancer(0.76604444f, 0.6427876f, position.x + X_4, position.y + Y_4, ANGLE_4);
				if (EndlessMode.modeDifficulte > 1) {
					AddBossStat.pool.obtain().lancer(0, 1, position.x + X_3, position.y + Y_3, ANGLE_3);
					AddBossStat.pool.obtain().lancer(-1, 0, position.x, position.y + Y_1, 180);
					AddBossStat.pool.obtain().lancer(-0.76604444f, 0.6427876f, position.x + X_2, position.y + Y_2, ANGLE_2);
				}
				nbLance++;
			}
		case 4:
			if (position.x < CSG.LARGEUR_ZONE_MOINS_LARGEUR_BORD - LARGEUR) {
				AddBossStat.pool.obtain().lancer(1, 0, position.x + X_5, position.y + Y_5, ANGLE_5);
				if (EndlessMode.modeDifficulte > 1) {
					AddBossStat.pool.obtain().lancer(0, 1, position.x + X_3, position.y + Y_3, ANGLE_3);
					AddBossStat.pool.obtain().lancer(0.76604444f, 0.6427876f, position.x + X_4, position.y + Y_4, ANGLE_4);
				}
				nbLance++;
			}
			break;
		}
	}

	@Override
	public float getDirectionY() {			return 0;	}
	@Override
	public float getDirectionX() {			return -dirX;	}
	@Override
	protected String getLabel() {			return getClass().toString();	}
	@Override
	protected TextureRegion getTexture() {	return AnimationPorteNef.getTexture(pv);	}
	@Override
	protected int getPvMax() {				return super.getPvBoss(Stats.PV_BOSS_SAT);	}
	public int getXp() {					return CoutsEnnemis.EnnemiPorteNef.COUT;	}
	protected Sound getSonExplosion() {		return SoundMan.explosionGrosse;	}
	public void invoquer() {				LISTE.add(pool.obtain());	}
	public int getDemiHauteur() {			return DEMI_LARGEUR;	}
	public int getDemiLargeur() {			return DEMI_LARGEUR;	}
	protected void free() {					pool.free(this);	}
	public int getHauteur() {				return LARGEUR;	}
	public int getLargeur() {				return LARGEUR;	}
}
