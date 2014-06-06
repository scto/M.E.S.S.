package elements.generic.components.shots;

import elements.generic.weapons.enemies.ArmeBossMine;
import elements.generic.weapons.enemies.BlueBullet;
import elements.generic.weapons.enemies.BlueBulletFast;
import elements.generic.weapons.enemies.BlueBulletSlow;
import elements.generic.weapons.enemies.EnemyWeapon;
import elements.generic.weapons.enemies.Fireball;
import elements.generic.weapons.enemies.FragWeapon;
import elements.generic.weapons.enemies.InsectWeapon;
import elements.generic.weapons.enemies.KinderWeapon;
import elements.generic.weapons.enemies.LaserWeapon;
import elements.generic.weapons.enemies.Meteorite;
import elements.generic.weapons.enemies.Mine;
import elements.generic.weapons.enemies.SmallFireball;
import elements.generic.weapons.enemies.Tournante;
import elements.generic.weapons.enemies.VicousBullet;

public enum Gatling {

	/**
	 * BULLETS
	 */
	BLUE_BULLET_SLOW(new Canon() {			public EnemyWeapon invoke() {			return BlueBulletSlow.POOL.obtain();			}	}),
	METEORITE(new Canon() {					public EnemyWeapon invoke() {			return Meteorite.POOL.obtain();					}	}),
	TOURNANTE(new Canon() {					public EnemyWeapon invoke() {			return Tournante.POOL.obtain();					}	}),
	MINE(new Canon() {						public EnemyWeapon invoke() {			return Mine.POOL.obtain();						}	}),
	INSECT(new Canon() {					public EnemyWeapon invoke() {			return InsectWeapon.POOL.obtain();				}	}),
	VICOUS(new Canon() {					public EnemyWeapon invoke() {			return VicousBullet.POOL.obtain();				}	}),
	SMALL_FIREBALL(new Canon() {			public EnemyWeapon invoke() {			return SmallFireball.POOL.obtain();				}	}),
	BLUE_BULLET(new Canon() {				public EnemyWeapon invoke() {			return BlueBullet.POOL.obtain();				}	}),
	BLUE_BULLET_FAST(new Canon() {			public EnemyWeapon invoke() {			return BlueBulletFast.POOL.obtain();			}	}),
	BOSS_MINE(new Canon() {					public EnemyWeapon invoke() {			return ArmeBossMine.POOL.obtain();				}	}),
	FIREBALL(new Canon() {					public EnemyWeapon invoke() {			return Fireball.POOL.obtain();					}	}),
	FRAG(new Canon() {						public EnemyWeapon invoke() {			return FragWeapon.POOL.obtain();				}	}),
	LASER(new Canon() {						public EnemyWeapon invoke() {			return LaserWeapon.POOL.obtain();				}	}),
	KINDER_WEAPON(new Canon() {				public EnemyWeapon invoke() {			return KinderWeapon.POOL.obtain();				}	});
	
	public Canon canon;

	private Gatling(Canon canon) {
		this.canon = canon;
	}

}
