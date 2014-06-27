package elements.generic.components.shots;

import elements.generic.weapons.enemies.CyanBullet;
import elements.generic.weapons.enemies.EnemyWeapon;
import elements.generic.weapons.enemies.Fireball;
import elements.generic.weapons.enemies.FragWeapon;
import elements.generic.weapons.enemies.InsectWeapon;
import elements.generic.weapons.enemies.KinderWeapon;
import elements.generic.weapons.enemies.LaserWeapon;
import elements.generic.weapons.enemies.Meteorite;
import elements.generic.weapons.enemies.Mine;
import elements.generic.weapons.enemies.OrangeBullet;
import elements.generic.weapons.enemies.PinkBullet;
import elements.generic.weapons.enemies.Rainbow;
import elements.generic.weapons.enemies.SmallFireball;
import elements.generic.weapons.enemies.Tournante;
import elements.generic.weapons.enemies.ViciousBullet;

public enum Gatling {

	/**
	 * BULLETS
	 */
	PINK_BULLET(new Canon() {			public EnemyWeapon invoke() {			return PinkBullet.POOL.obtain();			}	}),
	CYAN_BULLET(new Canon() {			public EnemyWeapon invoke() {			return CyanBullet.POOL.obtain();			}	}),
	METEORITE(new Canon() {					public EnemyWeapon invoke() {			return Meteorite.POOL.obtain();					}	}),
	MINE(new Canon() {						public EnemyWeapon invoke() {			return Mine.POOL.obtain();						}	}),
	INSECT(new Canon() {					public EnemyWeapon invoke() {			return InsectWeapon.POOL.obtain();				}	}),
	VICIOUS(new Canon() {					public EnemyWeapon invoke() {			return ViciousBullet.POOL.obtain();				}	}),
	SMALL_FIREBALL(new Canon() {			public EnemyWeapon invoke() {			return SmallFireball.POOL.obtain();				}	}),
	ORANGE_BULLET(new Canon() {				public EnemyWeapon invoke() {			return OrangeBullet.POOL.obtain();				}	}),
	RAINBOW(new Canon() {					public EnemyWeapon invoke() {			return Rainbow.POOL.obtain();				}	}),
	FIREBALL(new Canon() {					public EnemyWeapon invoke() {			return Fireball.POOL.obtain();					}	}),
	FRAG(new Canon() {						public EnemyWeapon invoke() {			return FragWeapon.POOL.obtain();				}	}),
	TOURNANTE(new Canon() {						public EnemyWeapon invoke() {			return Tournante.POOL.obtain();				}	}),
	LASER(new Canon() {						public EnemyWeapon invoke() {			return LaserWeapon.POOL.obtain();				}	}),
	KINDER_WEAPON(new Canon() {				public EnemyWeapon invoke() {			return KinderWeapon.POOL.obtain();				}	});
	
	public Canon canon;

	private Gatling(Canon canon) {
		this.canon = canon;
	}

}
