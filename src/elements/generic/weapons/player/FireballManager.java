package elements.generic.weapons.player;
import jeu.CSG;
import jeu.EndlessMode;
import assets.SoundMan;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

import elements.generic.Player;

public class FireballManager extends WeaponManager {

	private static final Sound SOUND = Gdx.audio.newSound(Gdx.files.internal("sons/156895__halgrimm__shot-2-0.wav"));
	private static float posX, posY;

	public void init() {
		posX = Player.xCenter - Fireball.halfWidth;
		posY = Player.POS.y + Player.HAUTEUR;
		if (EndlessMode.alternate)
			SoundMan.playBulletSound(SOUND);
		switch (CSG.profile.NvArmeDeBase) {
		case 1:			nv1(posX, posY);			break;
		case 2:			nv2(posX, posY);			break;
		case 3:			nv3(posX, posY);			break;
		case 4:			nv4(posX, posY);			break;
		case 5:			nv5(posX, posY);			break;
		case 6:			nv6(posX, posY);			break;
		case 7:			nv7();			break;
		default:		nv8();			break;
		}
	}

	private void nv8() {
		Fireball.POOL.obtain().init(posX, posY);
		Fireball.POOL.obtain().init(posX, posY - Fireball.width05);
		Fireball.POOL.obtain().init(posX, posY - Fireball.width10);
		Fireball.POOL.obtain().init(posX, posY - Fireball.width15);
		Fireball.POOL.obtain().init(posX, posY - Fireball.width20);
		Fireball.POOL.obtain().init(posX, posY - Fireball.width25);
		Fireball.POOL.obtain().init(posX, posY - Fireball.width30);
		Fireball.POOL.obtain().init(posX, posY - Fireball.width35);
		Fireball.POOL.obtain().init(posX, posY - Fireball.width40);
	}

	private void nv7() {
		Fireball.POOL.obtain().init(posX - Fireball.width, posY - Fireball.width);
		Fireball.POOL.obtain().init(posX + Fireball.width, posY - Fireball.width);
		Fireball.POOL.obtain().init(posX - Fireball.width033, posY - Fireball.width033);
		Fireball.POOL.obtain().init(posX + Fireball.width033, posY - Fireball.width033);
		Fireball.POOL.obtain().init(posX - Fireball.width066, posY);
		Fireball.POOL.obtain().init(posX + Fireball.width066, posY);
		Fireball.POOL.obtain().init(posX, posY + Fireball.halfWidth);
	}

	private static void nv6(float posX, float posY) {
		Fireball.POOL.obtain().init(posX - Fireball.halfWidth, posY);
		Fireball.POOL.obtain().init(posX + Fireball.halfWidth, posY);
		Fireball.POOL.obtain().init(posX - Fireball.width, posY - Fireball.halfWidth);
		Fireball.POOL.obtain().init(posX + Fireball.width, posY - Fireball.halfWidth);
		Fireball.POOL.obtain().init(posX, posY + Fireball.halfWidth);
		Fireball.POOL.obtain().init(posX, posY - Fireball.halfWidth);
	}

	private static void nv5(float posX, float posY) {
		Fireball.POOL.obtain().init(posX - Fireball.halfWidth, posY);
		Fireball.POOL.obtain().init(posX + Fireball.halfWidth, posY);
		Fireball.POOL.obtain().init(posX - Fireball.width, posY - Fireball.halfWidth);
		Fireball.POOL.obtain().init(posX + Fireball.width, posY - Fireball.halfWidth);
		Fireball.POOL.obtain().init(posX, posY + Fireball.halfWidth);
	}

	private static void nv4(float posX, float posY) {
		Fireball.POOL.obtain().init(posX - Fireball.halfWidth, posY);
		Fireball.POOL.obtain().init(posX + Fireball.halfWidth, posY);
		Fireball.POOL.obtain().init(posX - Fireball.width, posY -Fireball.halfWidth);
		Fireball.POOL.obtain().init(posX + Fireball.width, posY -Fireball.halfWidth);
	}

	private static void nv3(float posX, float posY) {
		Fireball.POOL.obtain().init(posX - Fireball.width, posY);
		Fireball.POOL.obtain().init(posX + Fireball.width, posY);
		Fireball.POOL.obtain().init(posX, posY + Fireball.halfWidth);
	}

	private static void nv2(float posX, float posY) {
		Fireball.POOL.obtain().init(posX + Fireball.halfWidth, posY);
		Fireball.POOL.obtain().init(posX - Fireball.halfWidth, posY);
	}

	private static void nv1(float posX, float posY) {
		Fireball.POOL.obtain().init(posX, posY);
	}
	
	@Override
	public String getLabel() {					return Fireball.LABEL;			}
	@Override
	public float[] getColors() {				return Fireball.couleurs;		}
	@Override
	protected float getCadenceTir() {			return Fireball.CADENCETIR;		}
	@Override
	public int nv() {							return CSG.profile.NvArmeDeBase;	}
}