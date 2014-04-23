package elements.generic.weapons.player;

import jeu.CSG;
import jeu.EndlessMode;
import assets.SoundMan;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

import elements.generic.Player;

public class PinkWeaponManager extends WeaponManager {
	// rotation 85 : [-0.9961947:0.087155804]
	// rotation 15 : [-0.25881904:0.9659258]
	// rotation 10 : -0.17364818:0.9848077
	// rotation 5 : [-0.08715574:0.9961947]
	private static final Sound SOUND = Gdx.audio.newSound(Gdx.files.internal("sons/156895__halgrimm__shot-2-0.wav"));
	private static float posX, posY;
	private static boolean alternate = true;
	
	public void init(){
		posX = Player.xCenter - PinkWeapon.halfWidth / 3;
		posY = Player.POS.y + Player.HEIGHT;
		alternate = !alternate;
		if (EndlessMode.alternate)
			SoundMan.playBulletSound(SOUND);
		// 5°
		if (alternate)			PinkWeapon.POOL.obtain().init(posX, posY, -0.08715574f, 0.9961947f);
		else					PinkWeapon.POOL.obtain().init(posX, posY, 0.08715574f, 0.9961947f);
		switch (CSG.profile.lvlPinkWeapon) {
		case 8:
			if (!alternate)		PinkWeapon.POOL.obtain().init(posX, posY, -0.70710677f, 0.70710677f);
			else				PinkWeapon.POOL.obtain().init(posX, posY, 0.70710677f, 0.70710677f);
		case 7:
			if (alternate)		PinkWeapon.POOL.obtain().init(posX, posY, -0.70710677f, -0.70710677f);
			else				PinkWeapon.POOL.obtain().init(posX, posY, 0.70710677f, -0.70710677f);
		case 6:
			// 80°
			if (!alternate)		PinkWeapon.POOL.obtain().init(posX, posY, -0.9848077f, 0.17364822f);
			else				PinkWeapon.POOL.obtain().init(posX, posY, 0.9848077f, 0.17364822f);
		case 5:
			// 85°
			if (alternate)		PinkWeapon.POOL.obtain().init(posX, posY, -0.9961947f, 0.087155804f);
			else				PinkWeapon.POOL.obtain().init(posX, posY, 0.9961947f, 0.087155804f);
		case 4:
			// 90°
			if (!alternate)		PinkWeapon.POOL.obtain().init(posX, posY, -1, 0);
			else				PinkWeapon.POOL.obtain().init(posX, posY, 1, 0);
		case 3:
			// 15°
			if (alternate)		PinkWeapon.POOL.obtain().init(posX, posY, -0.25881904f, 0.9659258f);
			else				PinkWeapon.POOL.obtain().init(posX, posY, 0.25881904f, 0.9659258f);
		case 2:
			// 10°
			if (!alternate)		PinkWeapon.POOL.obtain().init(posX, posY, -0.17364818f, 0.9848077f);
			else				PinkWeapon.POOL.obtain().init(posX, posY, 0.17364818f, 0.9848077f);
		}
	}

	@Override
	public String getLabel() {				return PinkWeapon.LABEL;			}
	@Override
	public float[] getColors() {			return PinkWeapon.COLORS;			}
	@Override
	protected float getCadenceTir() {		return PinkWeapon.CADENCETIR;		}
	@Override
	public int nv() {						return CSG.profile.lvlPinkWeapon;	}
	
}
