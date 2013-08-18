package vaisseaux.ennemis.particuliers;

import jeu.Endless;
import jeu.Physique;
import jeu.Stats;
import menu.CSG;
import vaisseaux.ennemis.Ennemis;
import vaisseaux.ennemis.CoutsEnnemis;
import assets.SoundMan;
import assets.animation.AnimationExplosion1;
import assets.animation.AnimationPorteNef;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;


public class EnnemiPorteNef extends Ennemis{
	
	// ** ** caracteristiques g�n�rales
	public static final int LARGEUR= (int) (CSG.LARGEUR_ECRAN / 2.5f);
	public static final int DEMI_LARGEUR = LARGEUR/2;
	private static final int Y_PREMIER = (LARGEUR / 40) * 17;
	private static final int Y_DEUXIEME = (LARGEUR / 40) * 29;
	private static final int X_DEUXIEME = (LARGEUR / 13);
	private static final int ANGLE_DEUXIEME = 140;
	private static final int Y_TROISIEME = (LARGEUR) - (int)EnnemiAilesDeployees.LARGEUR;
	private static final int X_TROISIEME = (LARGEUR / 2) - (int)EnnemiAilesDeployees.DEMI_LARGEUR;
	private static final int ANGLE_TROISIEME = 90;
	private static final int Y_QUATRIEME = Y_DEUXIEME;
	private static final int X_QUATRIEME = (int) (LARGEUR - X_DEUXIEME - EnnemiAilesDeployees.LARGEUR);
	private static final int ANGLE_QUATRIEME = 40;
	private static final int Y_CINQUIEME = Y_PREMIER;
	private static final int X_CINQUIEME = (int) (LARGEUR - EnnemiAilesDeployees.LARGEUR);
	private static final int ANGLE_CINQUIEME = 0;
	// ** ** caracteristiques variables.
	public static Pool<EnnemiPorteNef> pool = Pools.get(EnnemiPorteNef.class);
	private int nbLance = 0;
	private int dirX = -1;


	public EnnemiPorteNef() {
		position.x = CSG.LARGEUR_ZONE_JEU;
		position.y = CSG.HAUTEUR_ECRAN_PALLIER_3 - DEMI_LARGEUR;
	}

	protected Sound getSonExplosion() {
		return SoundMan.explosionGrosse;
	}
	
	/**
	 * Le reset sert � faire le random du c�t� depuis le quel il apparait, � voir � l'usage mais pourquoi pas.
	 */

	public void reset() {
		position.x = CSG.LARGEUR_ZONE_JEU;
		position.y = CSG.HAUTEUR_ECRAN_PALLIER_3 - DEMI_LARGEUR;
		dirX = -1;
		nbLance = 0;
		super.reset();
	}
	
	@Override
	protected int getPvMax() {
		return Stats.PVMAX_PORTE_NEF + (CSG.profil.getCoutUpArme() / 35);
	}

	protected void free() {
		pool.free(this);
	}
	
	/**
	 * Exactement la m�me que dans la super classe mais �a �vite de faire des getter largeur hauteur...
	 */

	public boolean mouvementEtVerif() {
		position.x += dirX * Endless.delta * Stats.VITESSE_MAX_PORTE_NEF;
		if (!mort)			lancerEnnemi();
		return super.mouvementEtVerif();
	}
	private void lancerEnnemi() {
		if (position.x < CSG.LARGEUR_ZONE_MOINS_LARGEUR_BORD - DEMI_LARGEUR && (nbLance == 0)){
			EnnemiAilesDeployees.pool.obtain().lancer(-1, 0, position.x, position.y + Y_PREMIER, 180);
			EnnemiAilesDeployees.pool.obtain().lancer(-0.76604444f, 0.6427876f, position.x + X_DEUXIEME, position.y + Y_DEUXIEME, ANGLE_DEUXIEME);
			if (Endless.level > 1) {
				EnnemiAilesDeployees.pool.obtain().lancer(0, 1, position.x + X_TROISIEME, position.y + Y_TROISIEME, ANGLE_TROISIEME);
			}
			nbLance++;
		}
		if (position.x < CSG.LARGEUR_ZONE_MOINS_LARGEUR_BORD - LARGEUR && (nbLance == 1)){
			EnnemiAilesDeployees.pool.obtain().lancer(-1, 0, position.x, position.y + Y_PREMIER, 180);
			EnnemiAilesDeployees.pool.obtain().lancer(-0.76604444f, 0.6427876f, position.x + X_DEUXIEME, position.y + Y_DEUXIEME, ANGLE_DEUXIEME);
			if (Endless.level > 1) {
				EnnemiAilesDeployees.pool.obtain().lancer(0, 1, position.x + X_TROISIEME, position.y + Y_TROISIEME, ANGLE_TROISIEME);	
				EnnemiAilesDeployees.pool.obtain().lancer(0.76604444f, 0.6427876f, position.x + X_QUATRIEME, position.y + Y_QUATRIEME, ANGLE_QUATRIEME);
			}
			nbLance++;
		}
		if(position.x < CSG.LARGEUR_ZONE_MOINS_LARGEUR_BORD - (LARGEUR + DEMI_LARGEUR) && (nbLance == 2)){
			EnnemiAilesDeployees.pool.obtain().lancer(0, 1, position.x + X_TROISIEME, position.y + Y_TROISIEME, ANGLE_TROISIEME);
			
			if (Endless.level > 1) {				
				EnnemiAilesDeployees.pool.obtain().lancer(0.76604444f, 0.6427876f, position.x + X_QUATRIEME, position.y + Y_QUATRIEME, ANGLE_QUATRIEME);
				EnnemiAilesDeployees.pool.obtain().lancer(-1, 0, position.x, position.y + Y_PREMIER, 180);
				EnnemiAilesDeployees.pool.obtain().lancer(-0.76604444f, 0.6427876f, position.x + X_DEUXIEME, position.y + Y_DEUXIEME, ANGLE_DEUXIEME);
			}
			
			nbLance++;
		}
		if(position.x < CSG.LARGEUR_ZONE_MOINS_LARGEUR_BORD - DEMI_LARGEUR && (nbLance == 3)){
			EnnemiAilesDeployees.pool.obtain().lancer(0.76604444f, 0.6427876f, position.x + X_QUATRIEME, position.y + Y_QUATRIEME, ANGLE_QUATRIEME);
			
			if (Endless.level > 1) {
				EnnemiAilesDeployees.pool.obtain().lancer(0, 1, position.x + X_TROISIEME, position.y + Y_TROISIEME, ANGLE_TROISIEME);
				EnnemiAilesDeployees.pool.obtain().lancer(-1, 0, position.x, position.y + Y_PREMIER, 180);
				EnnemiAilesDeployees.pool.obtain().lancer(-0.76604444f, 0.6427876f, position.x + X_DEUXIEME, position.y + Y_DEUXIEME, ANGLE_DEUXIEME);
			}
			nbLance++;
		}
		if (position.x < CSG.LARGEUR_ZONE_MOINS_LARGEUR_BORD - LARGEUR && (nbLance == 4)){
			EnnemiAilesDeployees.pool.obtain().lancer(1, 0, position.x + X_CINQUIEME, position.y + Y_CINQUIEME, ANGLE_CINQUIEME);
			
			if (Endless.level > 1) {
				EnnemiAilesDeployees.pool.obtain().lancer(0, 1, position.x + X_TROISIEME, position.y + Y_TROISIEME, ANGLE_TROISIEME);
				EnnemiAilesDeployees.pool.obtain().lancer(0.76604444f, 0.6427876f, position.x + X_QUATRIEME, position.y + Y_QUATRIEME, ANGLE_QUATRIEME);
			}
			nbLance++;
		}
	}

	@Override
	protected TextureRegion getTexture() {
		return AnimationPorteNef.getTexture(pv);
	}


	public int getXp() {		return CoutsEnnemis.EnnemiPorteNef.COUT;	}
	

	public int getHauteur() {		return LARGEUR;	}


	public int getLargeur() {		return LARGEUR;	}
	

	public int getDemiHauteur() {		return DEMI_LARGEUR;	}


	public int getDemiLargeur() {		return DEMI_LARGEUR;	}


	public void invoquer() {
		liste.add(pool.obtain());
	}
	
	@Override
	public float getDirectionY() {		return 0;	}
	@Override
	public float getDirectionX() {		return -dirX;	}
}
