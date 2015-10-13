package elements.generic.components.enemies;

import elements.generic.enemies.Enemy;
import elements.generic.enemies.individual.bosses.BossMine;
import elements.generic.enemies.individual.bosses.Meteor;
import elements.generic.enemies.individual.bosses.Quad;
import elements.generic.enemies.individual.bosses.Sat;
import elements.generic.enemies.individual.lvl1.Ball;
import elements.generic.enemies.individual.lvl1.Basic;
import elements.generic.enemies.individual.lvl1.Crusader;
import elements.generic.enemies.individual.lvl1.Cylon;
import elements.generic.enemies.individual.lvl1.Diabolo;
import elements.generic.enemies.individual.lvl1.Group;
import elements.generic.enemies.individual.lvl1.Insect;
import elements.generic.enemies.individual.lvl1.Kinder;
import elements.generic.enemies.individual.lvl1.Laser;
import elements.generic.enemies.individual.lvl1.Plane;
import elements.generic.enemies.individual.lvl1.RoundAndRound;
import elements.generic.enemies.individual.lvl1.Shooter;
import elements.generic.enemies.individual.lvl1.ShooterFrag;
import elements.generic.enemies.individual.lvl1.Vicious;
import elements.generic.enemies.individual.lvl1.ZigZag;
import elements.generic.enemies.individual.lvl2.BouleTirCote;
import elements.generic.enemies.individual.lvl2.BouleTirCoteRotation;
import elements.generic.enemies.individual.lvl3.Ball3;
import elements.generic.enemies.individual.lvl3.Basic3;
import elements.generic.enemies.individual.lvl3.Crusader3;
import elements.generic.enemies.individual.lvl3.Cylon3;
import elements.generic.enemies.individual.lvl3.Diabolo3;
import elements.generic.enemies.individual.lvl3.Group3;
import elements.generic.enemies.individual.lvl3.Insect3;
import elements.generic.enemies.individual.lvl3.Kinder3;
import elements.generic.enemies.individual.lvl3.Laser3;
import elements.generic.enemies.individual.lvl3.Plane3;
import elements.generic.enemies.individual.lvl3.RoundAndRound3;
import elements.generic.enemies.individual.lvl3.Shooter3;
import elements.generic.enemies.individual.lvl3.ShooterFrag3;
import elements.generic.enemies.individual.lvl3.ZigZag3;
import elements.generic.enemies.individual.lvl4.Ball4;
import elements.generic.enemies.individual.lvl4.Basic4;
import elements.generic.enemies.individual.lvl4.Crusader4;
import elements.generic.enemies.individual.lvl4.Cylon4;
import elements.generic.enemies.individual.lvl4.Diabolo4;
import elements.generic.enemies.individual.lvl4.Group4;
import elements.generic.enemies.individual.lvl4.Insect4;
import elements.generic.enemies.individual.lvl4.Kinder4;
import elements.generic.enemies.individual.lvl4.Laser4;
import elements.generic.enemies.individual.lvl4.Plane4;
import elements.generic.enemies.individual.lvl4.RoundAndRound4;
import elements.generic.enemies.individual.lvl4.Shooter4;
import elements.generic.enemies.individual.lvl4.ShooterFrag4;
import elements.generic.enemies.individual.lvl4.ZigZag4;

public enum Merlin {

	/**
	 * ENEMIES
	 */
	BALL_SIDE_SHOT_ROTATION(new Summoner() {
		@Override
		public Enemy invoke() {
			final BouleTirCoteRotation b = BouleTirCoteRotation.POOL.obtain();
			b.init();
			Enemy.LIST.add(b);
			return b;
		}
	}), BALL_SIDE_SHOT(new Summoner() {
		@Override
		public Enemy invoke() {
			final BouleTirCote b = BouleTirCote.POOL.obtain();
			b.init();
			Enemy.LIST.add(b);
			return b;
		}
	}), BALL(new Summoner() {
		@Override
		public Enemy invoke() {
			final Ball b = Ball.POOL.obtain();
			b.init();
			Enemy.LIST.add(b);
			return b;
		}
	}), BALL3(new Summoner() {
		@Override
		public Enemy invoke() {
			final Ball3 b = Ball3.POOL.obtain();
			b.init();
			Enemy.LIST.add(b);
			return b;
		}
	}), BALL4(new Summoner() {
		@Override
		public Enemy invoke() {
			final Ball4 b = Ball4.POOL.obtain();
			b.init();
			Enemy.LIST.add(b);
			return b;
		}
	}), BOSS_MINE(new Summoner() {
		@Override
		public Enemy invoke() {
			final BossMine b = BossMine.POOL.obtain();
			b.init();
			Enemy.LIST.add(b);
			return b;
		}
	}), BOSS_QUAD(new Summoner() {
		@Override
		public Enemy invoke() {
			final Quad b = Quad.POOL.obtain();
			b.init();
			Enemy.LIST.add(b);
			return b;
		}
	}), BOSS_SAT(new Summoner() {
		@Override
		public Enemy invoke() {
			final Sat b = Sat.POOL.obtain();
			b.init();
			Enemy.LIST.add(b);
			return b;
		}
	}), CRUSADER(new Summoner() {
		@Override
		public Enemy invoke() {
			final Crusader b = Crusader.POOL.obtain();
			b.init();
			Enemy.LIST.add(b);
			return b;
		}
	}), CRUSADER3(new Summoner() {
		@Override
		public Enemy invoke() {
			final Crusader3 b = Crusader3.POOL.obtain();
			b.init();
			Enemy.LIST.add(b);
			return b;
		}
	}), CRUSADER4(new Summoner() {
		@Override
		public Enemy invoke() {
			final Crusader4 b = Crusader4.POOL.obtain();
			b.init();
			Enemy.LIST.add(b);
			return b;
		}
	}), ZIGZAG(new Summoner() {
		@Override
		public Enemy invoke() {
			final ZigZag b = ZigZag.POOL.obtain();
			b.init();
			Enemy.LIST.add(b);
			return b;
		}
	}), ZIGZAG3(new Summoner() {
		@Override
		public Enemy invoke() {
			final ZigZag3 b = ZigZag3.POOL.obtain();
			b.init();
			Enemy.LIST.add(b);
			return b;
		}
	}), ZIGZAG4(new Summoner() {
		@Override
		public Enemy invoke() {
			final ZigZag4 b = ZigZag4.POOL.obtain();
			b.init();
			Enemy.LIST.add(b);
			return b;
		}
	}), VICIOUS(new Summoner() {
		@Override
		public Enemy invoke() {
			final Vicious b = Vicious.POOL.obtain();
			b.init();
			Enemy.LIST.add(b);
			return b;
		}
	}), BOULE_TIR_COTE(new Summoner() {
		@Override
		public Enemy invoke() {
			final BouleTirCote b = BouleTirCote.POOL.obtain();
			b.init();
			Enemy.LIST.add(b);
			return b;
		}
	}), BOULE_TIR_COTE_ROTATION(new Summoner() {
		@Override
		public Enemy invoke() {
			final BouleTirCoteRotation b = BouleTirCoteRotation.POOL.obtain();
			b.init();
			Enemy.LIST.add(b);
			return b;
		}
	}), SHOOTER(new Summoner() {
		@Override
		public Enemy invoke() {
			final Shooter b = Shooter.POOL.obtain();
			b.init();
			Enemy.LIST.add(b);
			return b;
		}
	}), SHOOTER3(new Summoner() {
		@Override
		public Enemy invoke() {
			final Shooter3 b = Shooter3.POOL.obtain();
			b.init();
			Enemy.LIST.add(b);
			return b;
		}
	}), SHOOTER4(new Summoner() {
		@Override
		public Enemy invoke() {
			final Shooter4 b = Shooter4.POOL.obtain();
			b.init();
			Enemy.LIST.add(b);
			return b;
		}
	}), DIABOLO(new Summoner() {
		@Override
		public Enemy invoke() {
			final Diabolo b = Diabolo.POOL.obtain();
			b.init();
			Enemy.LIST.add(b);
			return b;
		}
	}), DIABOLO3(new Summoner() {
		@Override
		public Enemy invoke() {
			final Diabolo3 b = Diabolo3.POOL.obtain();
			b.init();
			Enemy.LIST.add(b);
			return b;
		}
	}), DIABOLO4(new Summoner() {
		@Override
		public Enemy invoke() {
			final Diabolo4 b = Diabolo4.POOL.obtain();
			b.init();
			Enemy.LIST.add(b);
			return b;
		}
	}), ROUND_N_ROUND(new Summoner() {
		@Override
		public Enemy invoke() {
			final RoundAndRound b = RoundAndRound.POOL.obtain();
			b.init();
			Enemy.LIST.add(b);
			return b;
		}
	}), ROUND_N_ROUND3(new Summoner() {
		@Override
		public Enemy invoke() {
			final RoundAndRound3 b = RoundAndRound3.POOL.obtain();
			b.init();
			Enemy.LIST.add(b);
			return b;
		}
	}), ROUND_N_ROUND4(new Summoner() {
		@Override
		public Enemy invoke() {
			final RoundAndRound4 b = RoundAndRound4.POOL.obtain();
			b.init();
			Enemy.LIST.add(b);
			return b;
		}
	}), SHOOTER_FRAG(new Summoner() {
		@Override
		public Enemy invoke() {
			final ShooterFrag b = ShooterFrag.POOL.obtain();
			b.init();
			Enemy.LIST.add(b);
			return b;
		}
	}), SHOOTER_FRAG3(new Summoner() {
		@Override
		public Enemy invoke() {
			final ShooterFrag3 b = ShooterFrag3.POOL.obtain();
			b.init();
			Enemy.LIST.add(b);
			return b;
		}
	}), SHOOTER_FRAG4(new Summoner() {
		@Override
		public Enemy invoke() {
			final ShooterFrag4 b = ShooterFrag4.POOL.obtain();
			b.init();
			Enemy.LIST.add(b);
			return b;
		}
	}), KINDER(new Summoner() {
		@Override
		public Enemy invoke() {
			final Kinder b = Kinder.POOL.obtain();
			b.init();
			Enemy.LIST.add(b);
			return b;
		}
	}), KINDER3(new Summoner() {
		@Override
		public Enemy invoke() {
			final Kinder3 b = Kinder3.POOL.obtain();
			b.init();
			Enemy.LIST.add(b);
			return b;
		}
	}), KINDER4(new Summoner() {
		@Override
		public Enemy invoke() {
			final Kinder4 b = Kinder4.POOL.obtain();
			b.init();
			Enemy.LIST.add(b);
			return b;
		}
	}), PLANE(new Summoner() {
		@Override
		public Enemy invoke() {
			final Plane b = Plane.POOL.obtain();
			b.init();
			Enemy.LIST.add(b);
			return b;
		}
	}), PLANE3(new Summoner() {
		@Override
		public Enemy invoke() {
			final Plane3 b = Plane3.POOL.obtain();
			b.init();
			Enemy.LIST.add(b);
			return b;
		}
	}), PLANE4(new Summoner() {
		@Override
		public Enemy invoke() {
			final Plane4 b = Plane4.POOL.obtain();
			b.init();
			Enemy.LIST.add(b);
			return b;
		}
	}), LASER(new Summoner() {
		@Override
		public Enemy invoke() {
			final Laser b = Laser.POOL.obtain();
			b.init();
			Enemy.LIST.add(b);
			return b;
		}
	}), LASER3(new Summoner() {
		@Override
		public Enemy invoke() {
			final Laser3 b = Laser3.POOL.obtain();
			b.init();
			Enemy.LIST.add(b);
			return b;
		}
	}), LASER4(new Summoner() {
		@Override
		public Enemy invoke() {
			final Laser4 b = Laser4.POOL.obtain();
			b.init();
			Enemy.LIST.add(b);
			return b;
		}
	}), CYLON(new Summoner() {
		@Override
		public Enemy invoke() {
			final Cylon b = Cylon.POOL.obtain();
			b.init();
			Enemy.LIST.add(b);
			return b;
		}
	}), CYLON3(new Summoner() {
		@Override
		public Enemy invoke() {
			final Cylon3 b = Cylon3.POOL.obtain();
			b.init();
			Enemy.LIST.add(b);
			return b;
		}
	}), CYLON4(new Summoner() {
		@Override
		public Enemy invoke() {
			final Cylon4 b = Cylon4.POOL.obtain();
			b.init();
			Enemy.LIST.add(b);
			return b;
		}
	}), INSECT(new Summoner() {
		@Override
		public Enemy invoke() {
			final Insect b = Insect.POOL.obtain();
			b.init();
			Enemy.LIST.add(b);
			return b;
		}
	}), INSECT3(new Summoner() {
		@Override
		public Enemy invoke() {
			final Insect3 b = Insect3.POOL.obtain();
			b.init();
			Enemy.LIST.add(b);
			return b;
		}
	}), INSECT4(new Summoner() {
		@Override
		public Enemy invoke() {
			final Insect4 b = Insect4.POOL.obtain();
			b.init();
			Enemy.LIST.add(b);
			return b;
		}
	}), METEOR(new Summoner() {
		@Override
		public Enemy invoke() {
			final Meteor m = Meteor.POOL.obtain();
			m.init();
			Enemy.LIST.add(m);
			return m;
		}
	}),	GROUP(new Summoner() {
		@Override
		public Enemy invoke() {
			Group.initAll();
			return null;
		}
	}), GROUP3(new Summoner() {
		@Override
		public Enemy invoke() {
			Group3.initAll();
			return null;
		}
	}), GROUP4(new Summoner() {
		@Override
		public Enemy invoke() {
			Group4.initAll();
			return null;
		}
	}), DE_BASE(new Summoner() {
		@Override
		public Enemy invoke() {
			final Basic db = Basic.POOL.obtain();
			initEnemy(db);
			db.init();
			return db;
		}
	}), DE_BASE3(new Summoner() {
		@Override
		public Enemy invoke() {
			final Basic3 db = Basic3.POOL.obtain();
			initEnemy(db);
			return db;
		}
	}), DE_BASE4(new Summoner() {
		@Override
		public Enemy invoke() {
			final Basic4 db = Basic4.POOL.obtain();
			initEnemy(db);
			return db;
		}
	});

	private static void initEnemy(final Enemy db) {
		Enemy.LIST.add(db);
		db.init();
	}

	public Summoner incantation;

	private Merlin(Summoner incantation) {
		this.incantation = incantation;
	}

}
