package elements.generic.weapons.player;

import assets.SoundMan;
import elements.generic.Player;
import elements.generic.weapons.Weapons;
import jeu.CSG;

public class SpaceInvaderManager extends WeaponManager {
	
	@Override
	protected void init() {
		if (Weapons.PLAYER_LIST.size != 0) {
			return;
		}
		SoundMan.playBulletSound(SoundMan.tirRocket);
		SpaceInvaderWeapon.POOL.obtain().init(Player.xCenter - SpaceInvaderWeapon.halfWidth, Player.POS.y + Player.HAUTEUR);
	}

	@Override	public String getLabel() {				return SpaceInvaderWeapon.LABEL;			}
	@Override	public float[] getColors() {			return SpaceInvaderWeapon.COLORS;			}
	@Override	protected float getCadenceTir() {		return SpaceInvaderWeapon.CADENCETIR;		}
	@Override	public int nv() {						return CSG.profile.NvSpaceInvadersWeapon;	}
}
