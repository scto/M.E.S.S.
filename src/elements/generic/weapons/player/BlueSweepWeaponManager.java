package elements.generic.weapons.player;

import jeu.CSG;
import jeu.EndlessMode;
import jeu.Stats;
import assets.SoundMan;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Vector2;

import elements.generic.Player;

/**
 * Sert � cr�er les projectiles de l'arme bleu, s'adapte suivant le niveau et donne un effet de balayage
 * @author Julien
 */
public class BlueSweepWeaponManager extends WeaponManager {
	
	private static boolean versDroite;
	private static int placeBalle = 0;
	private static float rotation10 = 0, rotation05 = 0, rotation20 = 0, rotation40, rotation30;
	private static final float BALLE4ROTA = 7, BALLE3ROTA = 5, BALLE2ROTA = 3, BALLE1ROTA = 1;
	private static final Sound SOUND = Gdx.audio.newSound(Gdx.files.internal("sons/164102__bmaczero__laser.wav"));
	private static final Vector2 DIR = new Vector2(0, 1);
	private static float posX, posY;
	
	public void init(){
		if (EndlessMode.alternate)
			SoundMan.playBulletSound(SOUND);
		posX = Player.xCenter;
		posY = Player.POS.y + Player.HAUTEUR;
		
		DIR.x = 0;
		DIR.y = 1;
		
		switch (placeBalle) {
		case (-4):			rotation10 = -BALLE4ROTA; 	break;
		case (-3):			rotation10 = -BALLE3ROTA;	 	break;
		case (-2):			rotation10 = -BALLE2ROTA;	 	break;
		case (-1):			rotation10 = -BALLE1ROTA;	  	break;
		case (1):			rotation10 = BALLE1ROTA;		break;
		case (2):			rotation10 = BALLE2ROTA;	  	break;
		case (3):			rotation10 = BALLE3ROTA;	  	break;
		case (4):			rotation10 = BALLE4ROTA;	  	break;
		default:			rotation10 = 0;				break;
		}
		rotation20 = rotation10 + rotation10;
		rotation05 = rotation10 / 2;
		rotation40 = rotation20 + rotation20;
		rotation30 = rotation10 + rotation20;
		
		changerAngle();
		posX -= BlueSweepWeapon.halfWidth;
		
//		DIR.rotate(rotation10);
		fire();	
		
		if (CSG.profile.NvArmeBalayage < 2) return;
//		DIR.rotate(-rotation20);
		fire();
		
		if (CSG.profile.NvArmeBalayage < 3) return;
//		DIR.rotate(-rotation10);
		fire();
		
		if (CSG.profile.NvArmeBalayage < 4) return;
//		DIR.rotate(rotation40);
		fire();
		
		if (CSG.profile.NvArmeBalayage < 5) return;
//		DIR.rotate(-rotation05);
		fire();
		
		if (CSG.profile.NvArmeBalayage < 6) return;
//		DIR.rotate(-rotation30);
		fire();
		
		if (CSG.profile.NvArmeBalayage < 7) return;
//		DIR.rotate(rotation20);
		fire();
		
		if (CSG.profile.NvArmeBalayage < 8) return;
//		DIR.rotate(-rotation10);
		fire();
	}

	private void fire() {
		boolean rotate = true;
		for (int i = 0; i < 10; i++) {
			if (rotate) {
				DIR.x = 0;
				DIR.y = 1;
				DIR.rotate((float) (CSG.R.nextGaussian()*6f));
				DIR.scl(
						(float) (Stats.V_ARME_BALAYAGE + (Stats.U * CSG.R.nextGaussian()))
						);
			}
			rotate = !rotate;
			BlueSweepWeapon.POOL.obtain().init(posX, posY, DIR.x, DIR.y);
		}
	}

	private static void changerAngle() {
		// si le rayon se deplace vers la droite
		if (versDroite){
			placeBalle++;
			if (placeBalle >= 4)	versDroite = false;
		} else {
			placeBalle--;
			if (placeBalle <= -4)	versDroite = true;
		}
	}

	@Override
	public String getLabel() {				return BlueSweepWeapon.LABEL;		}
	@Override
	public float[] getColors() {			return BlueSweepWeapon.COLORS;		}
	@Override
	protected float getCadenceTir() {		return BlueSweepWeapon.CADENCETIR;	}
	@Override
	public int nv() {						return CSG.profile.NvArmeBalayage;	}
}
