package elements.generic.weapons.player;

import jeu.CSG;
import jeu.Profil;
import jeu.mode.EndlessMode;
import elements.generic.weapons.Weapon;

public abstract class WeaponManager {
	
	public float init(float nextShot) {
		if (EndlessMode.now > nextShot) {
			if (++Weapon.color >= TWeapon.COLORS.length)
				Weapon.color = 0;
			init();
			return EndlessMode.now + getCadenceTir();
		}
		return nextShot;
	}
	
	public static WeaponManager changerArme(WeaponManager arme) {
		if (arme.getLabel() == BlueSweepWeapon.LABEL) 	return new FireballManager();
		if (arme.getLabel() == TWeapon.LABEL) 			return new BlueSweepWeaponManager();
		if (arme.getLabel() == PinkWeapon.LABEL) 		{
			if (CSG.profile.NvArmeBalayage >= Profil.NV_MIN_SUN && CSG.profile.NvArmeDeBase >= Profil.NV_MIN_SUN && CSG.profile.NvArmeHantee >= Profil.NV_MIN_SUN && CSG.profile.lvlPinkWeapon >= Profil.NV_MIN_SUN) {
				return new SunManager();
			} else {
				return new TWeaponManager();
			}
		}
		if (arme.getLabel() == SunWeapon.LABEL)				return new SpaceInvaderManager();
		if (arme.getLabel() == SpaceInvaderWeapon.LABEL)	return new TWeaponManager();
		return new PinkWeaponManager();
	}
	
	public static WeaponManager getWeaponManager(String label) {
		if (label == BlueSweepWeapon.LABEL) 	return new BlueSweepWeaponManager();
		if (label == TWeapon.LABEL) 			return new TWeaponManager();
		if (label == Fireball.LABEL) 			return new FireballManager();
		if (label == SpaceInvaderWeapon.LABEL) 	return new SpaceInvaderManager();
		if (label == SunWeapon.LABEL) 			return new SunManager();
		return new PinkWeaponManager();
	}

	protected abstract float getCadenceTir();
	public abstract float[] getColors();
	public abstract String getLabel();
	protected abstract void init();
	public abstract int nv();

}
