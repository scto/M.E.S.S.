package elements.generic.weapons.player;

import jeu.CSG;
import jeu.EndlessMode;
import jeu.Profil;
import elements.generic.weapons.Weapons;

public abstract class WeaponManager {
	
	public float init(float prochainTir) {
		if (EndlessMode.now > prochainTir) {
			if (++Weapons.color >= TWeapon.COLORS.length)
				Weapons.color = 0;
			init();
			return EndlessMode.now + getCadenceTir();
		}
		return prochainTir;
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

	protected abstract float getCadenceTir();
	public abstract float[] getColors();
	public abstract String getLabel();
	protected abstract void init();
	public abstract int nv();

}
