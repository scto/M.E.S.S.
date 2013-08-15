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
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
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
//	private float maintenant = 0;
	private Rectangle collision = new Rectangle(position.x, position.y, LARGEUR, LARGEUR);
	public static Pool<EnnemiPorteNef> pool = Pools.get(EnnemiPorteNef.class);
	protected float tpsAnimationExplosion = 0;
	private int nbLance = 0;
	private int dirX = -1;


	@Override
	protected Sound getSonExplosion() {
		return SoundMan.explosionGrosse;
	}
	
	/**
	 * Le reset sert � faire le random du c�t� depuis le quel il apparait, � voir � l'usage mais pourquoi pas.
	 */
	@Override
	public void reset() {
		position.x = CSG.LARGEUR_ZONE_JEU;
		position.y = CSG.HAUTEUR_ECRAN_PALLIER_3 - DEMI_LARGEUR;
		mort = false;
		tpsAnimationExplosion = 0;
		pv = Stats.PVMAX_PORTE_NEF + (CSG.profil.getCoutUpArme() / 35);
		dirX = -1;
		nbLance = 0;
	}

	public EnnemiPorteNef() {
		super(CSG.LARGEUR_ZONE_JEU, CSG.HAUTEUR_ECRAN_PALLIER_2 - LARGEUR, Stats.PVMAX_PORTE_NEF + (CSG.profil.getCoutUpArme() / 35));
	}
	
	/**
	 * Exactement la m�me que dans la super classe mais �a �vite de faire des getter largeur hauteur...
	 */
	@Override
	public boolean mouvementEtVerifSansParticules() {
		if( (mort & tpsAnimationExplosion > AnimationExplosion1.tpsTotalAnimationExplosion1) | Physique.toujoursAfficher(position, LARGEUR) == false){
			pool.free(this);
			return false;
		}
		position.x += dirX * Endless.delta * Stats.VITESSE_MAX_PORTE_NEF;
		if(!mort)			lancerEnnemi();
		return true;
	}
	private void lancerEnnemi() {
//		maintenant += Endless.delta;
		if (position.x < CSG.LARGEUR_ZONE_MOINS_LARGEUR_BORD - DEMI_LARGEUR && (nbLance == 0)){
			EnnemiAilesDeployees premier = EnnemiAilesDeployees.pool.obtain();
			premier.setPosition(position.x, position.y + Y_PREMIER);
			premier.setAngle(180);
			premier.lancer(-1, 0);
			liste.add(premier);
			EnnemiAilesDeployees deuxieme = EnnemiAilesDeployees.pool.obtain();
			deuxieme.setPosition(position.x + X_DEUXIEME, position.y + Y_DEUXIEME);
			deuxieme.setAngle(ANGLE_DEUXIEME);
			deuxieme.lancer(-0.76604444f, 0.6427876f);
			liste.add(deuxieme);
			if (Endless.level > 1) {
				EnnemiAilesDeployees troisieme = EnnemiAilesDeployees.pool.obtain();
				troisieme.setPosition(position.x + X_TROISIEME, position.y + Y_TROISIEME);
				troisieme.setAngle(ANGLE_TROISIEME);
				troisieme.lancer(0, 1);
				liste.add(troisieme);
			}
			nbLance++;
		}
		if (position.x < CSG.LARGEUR_ZONE_MOINS_LARGEUR_BORD - LARGEUR && (nbLance == 1)){
			EnnemiAilesDeployees premier = EnnemiAilesDeployees.pool.obtain();
			premier.setPosition(position.x, position.y + Y_PREMIER);
			premier.setAngle(180);
			premier.lancer(-1, 0);
			liste.add(premier);
			EnnemiAilesDeployees deuxieme = EnnemiAilesDeployees.pool.obtain();
			deuxieme.setPosition(position.x + X_DEUXIEME, position.y + Y_DEUXIEME);
			deuxieme.setAngle(ANGLE_DEUXIEME);
			deuxieme.lancer(-0.76604444f, 0.6427876f);
			liste.add(deuxieme);
			if (Endless.level > 1) {
				EnnemiAilesDeployees troisieme = EnnemiAilesDeployees.pool.obtain();
				troisieme.setPosition(position.x + X_TROISIEME, position.y + Y_TROISIEME);
				troisieme.setAngle(ANGLE_TROISIEME);
				troisieme.lancer(0, 1);
				liste.add(troisieme);
				
				EnnemiAilesDeployees quatrieme = EnnemiAilesDeployees.pool.obtain();
				quatrieme.setPosition(position.x + X_QUATRIEME, position.y + Y_QUATRIEME);
				quatrieme.setAngle(ANGLE_QUATRIEME);
				quatrieme.lancer(0.76604444f, 0.6427876f);
				liste.add(quatrieme);
			}
			nbLance++;
		}
		if(position.x < CSG.LARGEUR_ZONE_MOINS_LARGEUR_BORD - (LARGEUR + DEMI_LARGEUR) && (nbLance == 2)){
			EnnemiAilesDeployees troisieme = EnnemiAilesDeployees.pool.obtain();
			troisieme.setPosition(position.x + X_TROISIEME, position.y + Y_TROISIEME);
			troisieme.setAngle(ANGLE_TROISIEME);
			troisieme.lancer(0, 1);
			liste.add(troisieme);
			
			if (Endless.level > 1) {				
				EnnemiAilesDeployees quatrieme = EnnemiAilesDeployees.pool.obtain();
				quatrieme.setPosition(position.x + X_QUATRIEME, position.y + Y_QUATRIEME);
				quatrieme.setAngle(ANGLE_QUATRIEME);
				quatrieme.lancer(0.76604444f, 0.6427876f);
				liste.add(quatrieme);
				
				EnnemiAilesDeployees premier = EnnemiAilesDeployees.pool.obtain();
				premier.setPosition(position.x, position.y + Y_PREMIER);
				premier.setAngle(180);
				premier.lancer(-1, 0);
				liste.add(premier);
				
				EnnemiAilesDeployees deuxieme = EnnemiAilesDeployees.pool.obtain();
				deuxieme.setPosition(position.x + X_DEUXIEME, position.y + Y_DEUXIEME);
				deuxieme.setAngle(ANGLE_DEUXIEME);
				deuxieme.lancer(-0.76604444f, 0.6427876f);
				liste.add(deuxieme);
			}
			
			nbLance++;
		}
		if(position.x < CSG.LARGEUR_ZONE_MOINS_LARGEUR_BORD - DEMI_LARGEUR && (nbLance == 3)){
			EnnemiAilesDeployees quatrieme = EnnemiAilesDeployees.pool.obtain();
			quatrieme.setPosition(position.x + X_QUATRIEME, position.y + Y_QUATRIEME);
			quatrieme.setAngle(ANGLE_QUATRIEME);
			quatrieme.lancer(0.76604444f, 0.6427876f);
			liste.add(quatrieme);
			
			if (Endless.level > 1) {
				EnnemiAilesDeployees troisieme = EnnemiAilesDeployees.pool.obtain();
				troisieme.setPosition(position.x + X_TROISIEME, position.y + Y_TROISIEME);
				troisieme.setAngle(ANGLE_TROISIEME);
				troisieme.lancer(0, 1);
				liste.add(troisieme);
				
				EnnemiAilesDeployees premier = EnnemiAilesDeployees.pool.obtain();
				premier.setPosition(position.x, position.y + Y_PREMIER);
				premier.setAngle(180);
				premier.lancer(-1, 0);
				liste.add(premier);
				
				EnnemiAilesDeployees deuxieme = EnnemiAilesDeployees.pool.obtain();
				deuxieme.setPosition(position.x + X_DEUXIEME, position.y + Y_DEUXIEME);
				deuxieme.setAngle(ANGLE_DEUXIEME);
				deuxieme.lancer(-0.76604444f, 0.6427876f);
				liste.add(deuxieme);
			}
			nbLance++;
		}
		if (position.x < CSG.LARGEUR_ZONE_MOINS_LARGEUR_BORD - LARGEUR && (nbLance == 4)){
			EnnemiAilesDeployees cinquieme = EnnemiAilesDeployees.pool.obtain();
			cinquieme.setPosition(position.x + X_CINQUIEME, position.y + Y_CINQUIEME);
			cinquieme.setAngle(ANGLE_CINQUIEME);
			cinquieme.lancer(1, 0);
			liste.add(cinquieme);
			
			if (Endless.level > 1) {
				EnnemiAilesDeployees troisieme = EnnemiAilesDeployees.pool.obtain();
				troisieme.setPosition(position.x + X_TROISIEME, position.y + Y_TROISIEME);
				troisieme.setAngle(ANGLE_TROISIEME);
				troisieme.lancer(0, 1);
				liste.add(troisieme);
				
				EnnemiAilesDeployees quatrieme = EnnemiAilesDeployees.pool.obtain();
				quatrieme.setPosition(position.x + X_QUATRIEME, position.y + Y_QUATRIEME);
				quatrieme.setAngle(ANGLE_QUATRIEME);
				quatrieme.lancer(0.76604444f, 0.6427876f);
				liste.add(quatrieme);
			}
			nbLance++;
		}
	}

	@Override
	public void afficherSansParticules(SpriteBatch batch) {
		if (mort){
			batch.draw(AnimationExplosion1.getTexture(tpsAnimationExplosion), position.x, position.y, LARGEUR, LARGEUR);
			tpsAnimationExplosion += Endless.delta;
		} else {
			batch.draw(AnimationPorteNef.getTexture(pv), position.x, position.y, LARGEUR, LARGEUR);
		}
	}


	@Override
	public int getXp() {		return CoutsEnnemis.EnnemiPorteNef.COUT;	}
	
	@Override
	public int getHauteur() {		return LARGEUR;	}

	@Override
	public int getLargeur() {		return LARGEUR;	}
	
	@Override
	public int getDemiHauteur() {		return DEMI_LARGEUR;	}

	@Override
	public int getDemiLargeur() {		return DEMI_LARGEUR;	}

	@Override
	public Rectangle getRectangleCollision() {
		collision.x = position.x;
		collision.y = position.y;
		return collision;
	}
	@Override
	public void invoquer() {
		liste.add(pool.obtain());
	}
}
