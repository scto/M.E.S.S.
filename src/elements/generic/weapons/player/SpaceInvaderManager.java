package elements.generic.weapons.player;

import assets.SoundMan;
import elements.generic.weapons.Weapon;
import elements.particular.Player;
import jeu.CSG;
import jeu.mode.EndlessMode;

public class SpaceInvaderManager extends WeaponManager {
	
	@Override
	public float init(float nextShot) {
		if (EndlessMode.triggerStop && nextShot < EndlessMode.now) {
			if (++Weapon.color >= TWeapon.COLORS.length)
				Weapon.color = 0;
			SoundMan.playBulletSound(SoundMan.shotRocket);
			SpaceInvaderWeapon.POOL.obtain().init(Player.xCenter - SpaceInvaderWeapon.DIMENSIONS.halfWidth, Player.POS.y + Player.HEIGHT);
			return EndlessMode.now + 2f;
		} 
		return super.init(nextShot);
	}
	
	@Override
	protected void init() {
		if (Weapon.PLAYER_LIST.size != 0) {
			return;
		}
		SoundMan.playBulletSound(SoundMan.shotRocket);
		SpaceInvaderWeapon.POOL.obtain().init(Player.xCenter - SpaceInvaderWeapon.DIMENSIONS.halfWidth, Player.POS.y + Player.HEIGHT);
	}

	@Override	public String getLabel() {				return SpaceInvaderWeapon.LABEL;			}
	@Override	public float[] getColors() {			return SpaceInvaderWeapon.COLORS;			}
	@Override	protected float getCadenceTir() {		return SpaceInvaderWeapon.FIRERATETIR;		}
	@Override	public int nv() {						return CSG.profile.NvSpaceInvadersWeapon;	}
}
