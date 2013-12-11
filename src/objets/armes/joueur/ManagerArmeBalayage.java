package objets.armes.joueur;

import jeu.CSG;
import objets.joueur.VaisseauJoueur;
import assets.SoundMan;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Vector2;

/**
 * Sert � cr�er les projectiles de l'arme bleu, s'adapte suivant le niveau et donne un effet de balayage
 * @author Julien
 */
public class ManagerArmeBalayage extends ManagerArme {
	
	private static boolean versDroite;
	private static int placeBalle = 0;
	private static float rotation = 0, demiRotation = 0, doubleRotation = 0;
	private static float BALLE4ROTA = 7, BALLE3ROTA = 5, BALLE2ROTA = 3, BALLE1ROTA = 1;
	private static Sound son = Gdx.audio.newSound(Gdx.files.internal("sons/164102__bmaczero__laser.wav"));
	private static Vector2 dir = new Vector2(0, 1);
	private static float posX, posY;
	
	public void init(){
		SoundMan.playTir(son);
		posX = VaisseauJoueur.centreX;
		posY = VaisseauJoueur.position.y + VaisseauJoueur.HAUTEUR;
		
		dir.x = 0;
		dir.y = 1;
		
		switch (placeBalle) {
		case (-4):			rotation = -BALLE4ROTA; 	break;
		case (-3):			rotation = -BALLE3ROTA;	 	break;
		case (-2):			rotation = -BALLE2ROTA;	 	break;
		case (-1):			rotation = -BALLE1ROTA;	  	break;
		case (1):			rotation = BALLE1ROTA;		break;
		case (2):			rotation = BALLE2ROTA;	  	break;
		case (3):			rotation = BALLE3ROTA;	  	break;
		case (4):			rotation = BALLE4ROTA;	  	break;
		default:			rotation = 0;				break;
		}
		doubleRotation = rotation + rotation;
		demiRotation = rotation / 2;
		
		changerAngle();
		posX -= ArmesBalayage.DEMI_LARGEUR;
		
		dir.rotate(rotation);
		ArmesBalayage.pool.obtain().init(posX, posY, dir.x, dir.y, dir.angle());
		
		if (CSG.profil.NvArmeBalayage < 2) return;
		dir.rotate(-doubleRotation);
		ArmesBalayage.pool.obtain().init(posX, posY, dir.x, dir.y, dir.angle());
		if (CSG.profil.NvArmeBalayage < 3) return;
		dir.rotate(-rotation);
		ArmesBalayage.pool.obtain().init(posX, posY, dir.x, dir.y, dir.angle());
		if (CSG.profil.NvArmeBalayage < 4) return;
		dir.rotate(doubleRotation + doubleRotation);
		ArmesBalayage.pool.obtain().init(posX, posY, dir.x, dir.y, dir.angle());
		if (CSG.profil.NvArmeBalayage < 5) return;
		dir.rotate(-demiRotation);
		ArmesBalayage.pool.obtain().init(posX, posY, dir.x, dir.y, dir.angle());
		if (CSG.profil.NvArmeBalayage < 6) return;
		dir.rotate(-(doubleRotation + rotation) );
		ArmesBalayage.pool.obtain().init(posX, posY, dir.x, dir.y, dir.angle());
	}

	/**
	 * Change la place suivant le sens.
	 * Appel�e apr�s chaque passage
	 */
	private static void changerAngle() {
		// si le rayon se deplace vers la droite
		if (versDroite){
			placeBalle++;
			if (placeBalle >= 4)		versDroite = false;
		} else {
			placeBalle--;
			if (placeBalle <= -4)	versDroite = true;
		}
	}

	@Override
	protected float getCadenceTir() {
		return ArmesBalayage.CADENCETIR;
	}

	@Override
	public String getLabel() {
		return ArmesBalayage.LABEL;
	}
}
