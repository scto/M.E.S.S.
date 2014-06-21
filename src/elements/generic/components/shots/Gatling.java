package elements.generic.components.shots;

import elements.generic.weapons.enemies.Raindow;
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
import elements.generic.weapons.enemies.ViciousBullet;

public enum Gatling {

	/**
	 * BULLETS
	 */
	BLUE_BULLET_SLOW(8, new Canon() {			public EnemyWeapon invoke() {			return BlueBulletSlow.POOL.obtain();			}	}),
	METEORITE(11, new Canon() {					public EnemyWeapon invoke() {			return Meteorite.POOL.obtain();					}	}),
	TOURNANTE(14, new Canon() {					public EnemyWeapon invoke() {			return Tournante.POOL.obtain();					}	}),
	MINE(13, new Canon() {						public EnemyWeapon invoke() {			return Mine.POOL.obtain();						}	}),
	INSECT(4, new Canon() {						public EnemyWeapon invoke() {			return InsectWeapon.POOL.obtain();				}	}),
	VICIOUS(6, new Canon() {						public EnemyWeapon invoke() {			return ViciousBullet.POOL.obtain();				}	}),
	SMALL_FIREBALL(15, new Canon() {			public EnemyWeapon invoke() {			return SmallFireball.POOL.obtain();				}	}),
	BLUE_BULLET(2, new Canon() {				public EnemyWeapon invoke() {			return BlueBullet.POOL.obtain();				}	}),
	BLUE_BULLET_FAST(7, new Canon() {			public EnemyWeapon invoke() {			return BlueBulletFast.POOL.obtain();			}	}),
	BOSS_MINE(1, new Canon() {					public EnemyWeapon invoke() {			return Raindow.POOL.obtain();				}	}),
	FIREBALL(9, new Canon() {					public EnemyWeapon invoke() {			return Fireball.POOL.obtain();					}	}),
	FRAG(3, new Canon() {						public EnemyWeapon invoke() {			return FragWeapon.POOL.obtain();				}	}),
	LASER(5, new Canon() {						public EnemyWeapon invoke() {			return LaserWeapon.POOL.obtain();				}	}),
	KINDER_WEAPON(12, new Canon() {				public EnemyWeapon invoke() {			return KinderWeapon.POOL.obtain();				}	});
	
	public Canon canon;
	public int pk;

	private Gatling(Canon canon) {
		this.canon = canon;
	}
	private Gatling(int pk, Canon canon) {
		this(canon);
		this.pk = pk;
	}

}
