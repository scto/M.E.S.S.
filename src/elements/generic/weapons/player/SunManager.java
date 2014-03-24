package elements.generic.weapons.player;

import assets.SoundMan;

import com.badlogic.gdx.math.Vector2;

import jeu.CSG;
import jeu.EndlessMode;

public class SunManager extends WeaponManager {
	
	public static final Vector2 vector = new Vector2(0,1);
	public static final float 	lvl8 = 360 / 9, 
								lvl7 = 360 / 7,
								lvl6 = 360 / 6,
								lvl5 = 360 / 5,
								lvl4 = 360 / 4,
								lvl3 = 360 / 3;
	
	@Override
	protected void init() {
		if (EndlessMode.alternate)
			SoundMan.playBulletSound(SoundMan.tirRocket);
		vector.rotate(8);
		switch (CSG.profile.NvArmeSun) {
		case 1:			nv1();		break;
		case 2:			nv2();		break;
		case 3:			nv3();		break;
		case 4:			nv4();		break;
		case 5:			nv5();		break;
		case 6:			nv6();		break;
		case 7:			nv7();		break;
		default:		nv8();		break;
		}
	}

	private void nv8() {
		SunWeapon.init(vector);
		SunWeapon.init(vector.rotate(lvl8));
		SunWeapon.init(vector.rotate(lvl8));
		SunWeapon.init(vector.rotate(lvl8));
		SunWeapon.init(vector.rotate(lvl8));
		SunWeapon.init(vector.rotate(lvl8));
		SunWeapon.init(vector.rotate(lvl8));
		SunWeapon.init(vector.rotate(lvl8));
		SunWeapon.init(vector.rotate(lvl8));
	}

	private void nv7() {
		SunWeapon.init(vector);
		SunWeapon.init(vector.rotate(lvl7));
		SunWeapon.init(vector.rotate(lvl7));
		SunWeapon.init(vector.rotate(lvl7));
		SunWeapon.init(vector.rotate(lvl7));
		SunWeapon.init(vector.rotate(lvl7));
		SunWeapon.init(vector.rotate(lvl7));
	}

	private void nv6() {
		SunWeapon.init(vector);
		SunWeapon.init(vector.rotate(lvl6));
		SunWeapon.init(vector.rotate(lvl6));
		SunWeapon.init(vector.rotate(lvl6));
		SunWeapon.init(vector.rotate(lvl6));
		SunWeapon.init(vector.rotate(lvl6));
	}

	private void nv5() {
		SunWeapon.init(vector);
		SunWeapon.init(vector.rotate(lvl5));
		SunWeapon.init(vector.rotate(lvl5));
		SunWeapon.init(vector.rotate(lvl5));
		SunWeapon.init(vector.rotate(lvl5));
	}

	private void nv4() {
		SunWeapon.init(vector);
		SunWeapon.init(vector.rotate(lvl4));
		SunWeapon.init(vector.rotate(lvl4));
		SunWeapon.init(vector.rotate(lvl4));
	}

	private void nv3() {
		SunWeapon.init(vector);
		SunWeapon.init(vector.rotate(lvl3));
		SunWeapon.init(vector.rotate(lvl3));
	}

	private void nv2() {
		SunWeapon.init(vector);
		SunWeapon.init(vector.rotate(180));
	}

	private void nv1() {
		SunWeapon.init(vector);
	}

	@Override
	public String getLabel() {				return SunWeapon.LABEL;			}
	@Override
	public float[] getColors() {			return SunWeapon.COLORS;			}
	@Override
	protected float getCadenceTir() {		return SunWeapon.CADENCETIR;		}
	@Override
	public int nv() {						return CSG.profile.NvArmeSun;	}
}
