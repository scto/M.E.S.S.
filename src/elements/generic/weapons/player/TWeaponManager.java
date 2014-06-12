package elements.generic.weapons.player;

import jeu.CSG;
import jeu.mode.EndlessMode;
import assets.SoundMan;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

import elements.generic.Player;

public class TWeaponManager extends WeaponManager {
	
	private static final Sound SOUND = Gdx.audio.newSound(Gdx.files.internal("sons/armehantee.wav"));
	private static float posX, posY;
	
	public void init(){
		posX = Player.xCenter - TWeapon.halfWidth;
		posY = Player.POS.y + Player.HEIGHT;
		if (EndlessMode.alternate)
			SoundMan.playBulletSound(SOUND);
		switch (CSG.profile.NvArmeHantee) {
		case 1:			nv1();			break;
		case 2:			nv2();			break;
		case 4:			nv4();			break;
		case 5:			nv5();			break;
		case 6:			nv6();			break;
		case 7:			nv7();			break;
		default:		nv3();			break;
		}
	}

	private void nv7() {
		TWeapon.POOL.obtain().init(posX - TWeapon.halfWidth, posY - TWeapon.halfWidth);
		TWeapon.POOL.obtain().init(posX + TWeapon.halfWidth, posY - TWeapon.halfWidth);
		TWeapon.POOL.obtain().init(posX - TWeapon.halfWidth, posY);
		TWeapon.POOL.obtain().init(posX + TWeapon.halfWidth, posY);
		TWeapon.POOL.obtain().init(posX - TWeapon.width, posY - TWeapon.halfWidth);
		TWeapon.POOL.obtain().init(posX + TWeapon.width, posY - TWeapon.halfWidth);
		TWeapon.POOL.obtain().init(posX, posY + TWeapon.halfWidth);
	}

	private static void nv6() {
		TWeapon.POOL.obtain().init(posX - TWeapon.halfWidth, posY);
		TWeapon.POOL.obtain().init(posX + TWeapon.halfWidth, posY);
		TWeapon.POOL.obtain().init(posX - TWeapon.width, posY - TWeapon.halfWidth);
		TWeapon.POOL.obtain().init(posX + TWeapon.width, posY - TWeapon.halfWidth);
		TWeapon.POOL.obtain().init(posX, posY + TWeapon.halfWidth);
		TWeapon.POOL.obtain().init(posX, posY - TWeapon.halfWidth);
	}

	private static void nv5() {
		TWeapon.POOL.obtain().init(posX - TWeapon.halfWidth, posY);
		TWeapon.POOL.obtain().init(posX + TWeapon.halfWidth, posY);
		TWeapon.POOL.obtain().init(posX - TWeapon.width, posY - TWeapon.halfWidth);
		TWeapon.POOL.obtain().init(posX + TWeapon.width, posY - TWeapon.halfWidth);
		TWeapon.POOL.obtain().init(posX, posY + TWeapon.halfWidth);
	}

	private static void nv4() {
		TWeapon.POOL.obtain().init(posX - TWeapon.halfWidth, posY);
		TWeapon.POOL.obtain().init(posX + TWeapon.halfWidth, posY);
		TWeapon.POOL.obtain().init(posX - TWeapon.width, posY - TWeapon.halfWidth);
		TWeapon.POOL.obtain().init(posX + TWeapon.width, posY - TWeapon.halfWidth);
	}

	private static void nv3() {
		TWeapon.POOL.obtain().init(posX - TWeapon.width, posY);
		TWeapon.POOL.obtain().init(posX + TWeapon.width, posY);
		TWeapon.POOL.obtain().init(posX, posY + TWeapon.halfWidth);
	}

	private static void nv2() {
		TWeapon.POOL.obtain().init(posX - TWeapon.halfWidth, posY);
		TWeapon.POOL.obtain().init(posX + TWeapon.halfWidth, posY);
	}

	private static void nv1() {
		TWeapon.POOL.obtain().init(posX, posY);
	}

	@Override	public String getLabel() {				return TWeapon.LABEL;				}
	@Override	public float[] getColors() {			return TWeapon.COLORS;				}
	@Override	public int nv() {						return CSG.profile.NvArmeHantee;	}
	@Override
	protected float getCadenceTir() {
		if (CSG.profile.NvArmeHantee == 8)
			return TWeapon.FIRERATETIRLVL8;
		return TWeapon.FIRERATETIR;		
	}
}
