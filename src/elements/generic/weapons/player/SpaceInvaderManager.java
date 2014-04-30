package elements.generic.weapons.player;

import assets.SoundMan;
import elements.generic.Player;
import elements.generic.weapons.Weapons;
import jeu.CSG;
import jeu.mode.EndlessMode;

public class SpaceInvaderManager extends WeaponManager {
	
	@Override
	public float init(float prochainTir) {
		if (EndlessMode.triggerStop && prochainTir < EndlessMode.now) {
			if (++Weapons.color >= TWeapon.COLORS.length)
				Weapons.color = 0;
			SoundMan.playBulletSound(SoundMan.tirRocket);
			SpaceInvaderWeapon.POOL.obtain().init(Player.xCenter - SpaceInvaderWeapon.halfWidth, Player.POS.y + Player.HEIGHT);
			return EndlessMode.now + 2f;
		} 
		return super.init(prochainTir);
	}
	
	@Override
	protected void init() {
		if (Weapons.PLAYER_LIST.size != 0) {
			return;
		}
		SoundMan.playBulletSound(SoundMan.tirRocket);
		SpaceInvaderWeapon.POOL.obtain().init(Player.xCenter - SpaceInvaderWeapon.halfWidth, Player.POS.y + Player.HEIGHT);
	}

	@Override	public String getLabel() {				return SpaceInvaderWeapon.LABEL;			}
	@Override	public float[] getColors() {			return SpaceInvaderWeapon.COLORS;			}
	@Override	protected float getCadenceTir() {		return SpaceInvaderWeapon.CADENCETIR;		}
	@Override	public int nv() {						return CSG.profile.NvSpaceInvadersWeapon;	}
}
