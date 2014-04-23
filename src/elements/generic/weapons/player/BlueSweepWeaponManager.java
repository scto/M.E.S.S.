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
	
	private static final Sound SOUND = Gdx.audio.newSound(Gdx.files.internal("sons/164102__bmaczero__laser.wav"));
	private static final Vector2 DIR = new Vector2(0, 1);
	private static float posX, posY;
	
	public void init(){
		if (EndlessMode.alternate)
			SoundMan.playBulletSound(SOUND);
		posX = Player.xCenter - BlueSweepWeapon.halfWidth;
		posY = Player.POS.y + Player.HEIGHT;
		
		for (int i = 0; i < CSG.profile.NvArmeBalayage; i++) {
			fire();	
		}
	}

	private void fire() {
//		boolean rotate = true;
		for (int i = 0; i < 9; i++) {
//			if (rotate) {
				DIR.x = 0;
				DIR.y = 1;
				DIR.rotate((float) (CSG.R.nextGaussian()*6f));
				DIR.scl(
						(float) (Stats.V_ARME_BALAYAGE + (Stats.U * CSG.R.nextGaussian()))
						);
//			}
//			rotate = !rotate;
			BlueSweepWeapon.POOL.obtain().init(posX, posY, DIR.x, DIR.y);
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
