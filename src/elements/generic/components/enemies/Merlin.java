package elements.generic.components.enemies;

import jeu.CSG;
import elements.generic.enemies.Enemy;
import elements.generic.enemies.individual.bosses.BossMine;
import elements.generic.enemies.individual.bosses.BossQuad;
import elements.generic.enemies.individual.bosses.BossSat;
import elements.generic.enemies.individual.lvl1.Ball;
import elements.generic.enemies.individual.lvl1.Crusader;
import elements.generic.enemies.individual.lvl1.Cylon;
import elements.generic.enemies.individual.lvl1.DeBase;
import elements.generic.enemies.individual.lvl1.Group;
import elements.generic.enemies.individual.lvl1.Insect;
import elements.generic.enemies.individual.lvl1.Kinder;
import elements.generic.enemies.individual.lvl1.Laser;
import elements.generic.enemies.individual.lvl1.Plane;
import elements.generic.enemies.individual.lvl1.QuiTir;
import elements.generic.enemies.individual.lvl1.QuiTirTriangle;
import elements.generic.enemies.individual.lvl1.QuiTourne;
import elements.generic.enemies.individual.lvl1.RoundAndRound;
import elements.generic.enemies.individual.lvl1.Vicious;
import elements.generic.enemies.individual.lvl1.ZigZag;
import elements.generic.enemies.individual.lvl2.BouleTirCote;
import elements.generic.enemies.individual.lvl2.BouleTirCoteRotation;
import elements.generic.enemies.individual.lvl3.BouleNv3;
import elements.generic.enemies.individual.lvl3.CylonNv3;
import elements.generic.enemies.individual.lvl3.DeBaseNv3;
import elements.generic.enemies.individual.lvl3.Group3;
import elements.generic.enemies.individual.lvl3.InsecteNv3;
import elements.generic.enemies.individual.lvl3.KinderNv3;
import elements.generic.enemies.individual.lvl3.LaserNv3;
import elements.generic.enemies.individual.lvl3.Plane3;
import elements.generic.enemies.individual.lvl3.PorteRaisinNv3;
import elements.generic.enemies.individual.lvl3.QuiTirNv3;
import elements.generic.enemies.individual.lvl3.QuiTirTriangle3;
import elements.generic.enemies.individual.lvl3.QuiTourneNv3;
import elements.generic.enemies.individual.lvl3.RoundAndRound3;
import elements.generic.enemies.individual.lvl3.ZigZagNv3;
import elements.generic.enemies.individual.lvl4.BouleNv4;
import elements.generic.enemies.individual.lvl4.CylonNv4;
import elements.generic.enemies.individual.lvl4.DeBaseNv4;
import elements.generic.enemies.individual.lvl4.Group4;
import elements.generic.enemies.individual.lvl4.InsecteNv4;
import elements.generic.enemies.individual.lvl4.KinderNv4;
import elements.generic.enemies.individual.lvl4.LaserNv4;
import elements.generic.enemies.individual.lvl4.Plane4;
import elements.generic.enemies.individual.lvl4.PorteRaisinNv4;
import elements.generic.enemies.individual.lvl4.QuiTirNv4;
import elements.generic.enemies.individual.lvl4.QuiTirTriangle4;
import elements.generic.enemies.individual.lvl4.QuiTourneNv4;
import elements.generic.enemies.individual.lvl4.RoundAndRound4;
import elements.generic.enemies.individual.lvl4.ZigZagNv4;

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
	}),
	BALL_SIDE_SHOT(new Summoner() {
		@Override
		public Enemy invoke() {
			final BouleTirCote b = BouleTirCote.POOL.obtain();
			b.init();
			Enemy.LIST.add(b);
			return b;
		}
	}),
	BALL(new Summoner() {
		@Override
		public Enemy invoke() {
			final Ball b = Ball.POOL.obtain();
			b.init();
			Enemy.LIST.add(b);
			return b;
		}
	}),
	BALL3(new Summoner() {
		@Override
		public Enemy invoke() {
			final BouleNv3 b = BouleNv3.POOL.obtain();
			b.init();
			Enemy.LIST.add(b);
			return b;
		}
	}),
	BALL4(new Summoner() {
		@Override
		public Enemy invoke() {
			final BouleNv4 b = BouleNv4.POOL.obtain();
			b.init();
			Enemy.LIST.add(b);
			return b;
		}
	}),
	BOSS_MINE(new Summoner() {
		@Override
		public Enemy invoke() {
			final BossMine b = BossMine.POOL.obtain();
			b.init();
			Enemy.LIST.add(b);
			return b;
		}
	}),
	BOSS_QUAD(new Summoner() {
		@Override
		public Enemy invoke() {
			final BossQuad b = BossQuad.POOL.obtain();
			b.init();
			Enemy.LIST.add(b);
			return b;
		}
	}),
	BOSS_SAT(new Summoner() {
		@Override
		public Enemy invoke() {
			final BossSat b = BossSat.POOL.obtain();
			b.init();
			Enemy.LIST.add(b);
			return b;
		}
	}),
	CRUSADER(new Summoner() {
		@Override
		public Enemy invoke() {
			final Crusader b = Crusader.POOL.obtain();
			b.init();
			Enemy.LIST.add(b);
			return b;
		}
	}),
	CRUSADER3(new Summoner() {
		@Override
		public Enemy invoke() {
			final PorteRaisinNv3 b = PorteRaisinNv3.POOL.obtain();
			b.init();
			Enemy.LIST.add(b);
			return b;
		}
	}),
	CRUSADER4(new Summoner() {
		@Override
		public Enemy invoke() {
			final PorteRaisinNv4 b = PorteRaisinNv4.POOL.obtain();
			b.init();
			Enemy.LIST.add(b);
			return b;
		}
	}),
	ZIGZAG(new Summoner() {
		@Override
		public Enemy invoke() {
			final ZigZag b = ZigZag.POOL.obtain();
			b.init();
			Enemy.LIST.add(b);
			return b;
		}
	}),
	ZIGZAG3(new Summoner() {
		@Override
		public Enemy invoke() {
			final ZigZagNv3 b = ZigZagNv3.POOL.obtain();
			b.init();
			Enemy.LIST.add(b);
			return b;
		}
	}),
	ZIGZAG4(new Summoner() {
		@Override
		public Enemy invoke() {
			final ZigZagNv4 b = ZigZagNv4.POOL.obtain();
			b.init();
			Enemy.LIST.add(b);
			return b;
		}
	}),
	VICIOUS(new Summoner() {
		@Override
		public Enemy invoke() {
			final Vicious b = Vicious.POOL.obtain();
			b.init();
			Enemy.LIST.add(b);
			return b;
		}
	}),
	BOULE_TIR_COTE(new Summoner() {
		@Override
		public Enemy invoke() {
			final BouleTirCote b = BouleTirCote.POOL.obtain();
			b.init();
			Enemy.LIST.add(b);
			return b;
		}
	}),
	BOULE_TIR_COTE_ROTATION(new Summoner() {
		@Override
		public Enemy invoke() {
			final BouleTirCoteRotation b = BouleTirCoteRotation.POOL.obtain();
			b.init();
			Enemy.LIST.add(b);
			return b;
		}
	}),
	QUI_TIR(new Summoner() {
		@Override
		public Enemy invoke() {
			final QuiTir b = QuiTir.POOL.obtain();
			b.init();
			Enemy.LIST.add(b);
			return b;
		}
	}),
	QUI_TIR3(new Summoner() {
		@Override
		public Enemy invoke() {
			final QuiTirNv3 b = QuiTirNv3.POOL.obtain();
			b.init();
			Enemy.LIST.add(b);
			return b;
		}
	}),
	QUI_TIR4(new Summoner() {
		@Override
		public Enemy invoke() {
			final QuiTirNv4 b = QuiTirNv4.POOL.obtain();
			b.init();
			Enemy.LIST.add(b);
			return b;
		}
	}),
	QUI_TOURNE(new Summoner() {
		@Override
		public Enemy invoke() {
			final QuiTourne b = QuiTourne.POOL.obtain();
			b.init();
			Enemy.LIST.add(b);
			return b;
		}
	}),
	QUI_TOURNE3(new Summoner() {
		@Override
		public Enemy invoke() {
			final QuiTourneNv3 b = QuiTourneNv3.POOL.obtain();
			b.init();
			Enemy.LIST.add(b);
			return b;
		}
	}),
	QUI_TOURNE4(new Summoner() {
		@Override
		public Enemy invoke() {
			final QuiTourneNv4 b = QuiTourneNv4.POOL.obtain();
			b.init();
			Enemy.LIST.add(b);
			return b;
		}
	}),
	ROUND_N_ROUND(new Summoner() {
		@Override
		public Enemy invoke() {
			final RoundAndRound b = RoundAndRound.POOL.obtain();
			b.init();
			Enemy.LIST.add(b);
			return b;
		}
	}),
	ROUND_N_ROUND3(new Summoner() {
		@Override
		public Enemy invoke() {
			final RoundAndRound3 b = RoundAndRound3.POOL.obtain();
			b.init();
			Enemy.LIST.add(b);
			return b;
		}
	}),
	ROUND_N_ROUND4(new Summoner() {
		@Override
		public Enemy invoke() {
			final RoundAndRound4 b = RoundAndRound4.POOL.obtain();
			b.init();
			Enemy.LIST.add(b);
			return b;
		}
	}),
	QUI_TIR_TRIANGLE(new Summoner() {
		@Override
		public Enemy invoke() {
			final QuiTirTriangle b = QuiTirTriangle.POOL.obtain();
			b.init();
			Enemy.LIST.add(b);
			return b;
		}
	}),
	QUI_TIR_TRIANGLE3(new Summoner() {
		@Override
		public Enemy invoke() {
			final QuiTirTriangle3 b = QuiTirTriangle3.POOL.obtain();
			b.init();
			Enemy.LIST.add(b);
			return b;
		}
	}),
	QUI_TIR_TRIANGLE4(new Summoner() {
		@Override
		public Enemy invoke() {
			final QuiTirTriangle4 b = QuiTirTriangle4.POOL.obtain();
			b.init();
			Enemy.LIST.add(b);
			return b;
		}
	}),
	KINDER(new Summoner() {
		@Override
		public Enemy invoke() {
			final Kinder b = Kinder.POOL.obtain();
			b.init();
			Enemy.LIST.add(b);
			return b;
		}
	}),
	KINDER3(new Summoner() {
		@Override
		public Enemy invoke() {
			final KinderNv3 b = KinderNv3.POOL.obtain();
			b.init();
			Enemy.LIST.add(b);
			return b;
		}
	}),
	KINDER4(new Summoner() {
		@Override
		public Enemy invoke() {
			final KinderNv4 b = KinderNv4.POOL.obtain();
			b.init();
			Enemy.LIST.add(b);
			return b;
		}
	}),
	PLANE(new Summoner() {
		@Override
		public Enemy invoke() {
			final Plane b = Plane.POOL.obtain();
			b.init();
			Enemy.LIST.add(b);
			return b;
		}
	}),
	PLANE3(new Summoner() {
		@Override
		public Enemy invoke() {
			final Plane3 b = Plane3.POOL.obtain();
			b.init();
			Enemy.LIST.add(b);
			return b;
		}
	}),
	PLANE4(new Summoner() {
		@Override
		public Enemy invoke() {
			final Plane4 b = Plane4.POOL.obtain();
			b.init();
			Enemy.LIST.add(b);
			return b;
		}
	}),
	LASER(new Summoner() {
		@Override
		public Enemy invoke() {
			final Laser b = Laser.POOL.obtain();
			b.init();
			Enemy.LIST.add(b);
			return b;
		}
	}),
	LASER3(new Summoner() {
		@Override
		public Enemy invoke() {
			final LaserNv3 b = LaserNv3.POOL.obtain();
			b.init();
			Enemy.LIST.add(b);
			return b;
		}
	}),
	LASER4(new Summoner() {
		@Override
		public Enemy invoke() {
			final LaserNv4 b = LaserNv4.POOL.obtain();
			b.init();
			Enemy.LIST.add(b);
			return b;
		}
	}),
	CYLON(new Summoner() {
		@Override
		public Enemy invoke() {
			final Cylon b = Cylon.POOL.obtain();
			b.init();
			Enemy.LIST.add(b);
			return b;
		}
	}),
	CYLON3(new Summoner() {
		@Override
		public Enemy invoke() {
			final CylonNv3 b = CylonNv3.POOL.obtain();
			b.init();
			Enemy.LIST.add(b);
			return b;
		}
	}),
	CYLON4(new Summoner() {
		@Override
		public Enemy invoke() {
			final CylonNv4 b = CylonNv4.POOL.obtain();
			b.init();
			Enemy.LIST.add(b);
			return b;
		}
	}),
	INSECT(new Summoner() {
		@Override
		public Enemy invoke() {
			final Insect b = Insect.POOL.obtain();
			b.init();
			Enemy.LIST.add(b);
			return b;
		}
	}),
	INSECT3(new Summoner() {
		@Override
		public Enemy invoke() {
			final InsecteNv3 b = InsecteNv3.POOL.obtain();
			b.init();
			Enemy.LIST.add(b);
			return b;
		}
	}),
	INSECT4(new Summoner() {
		@Override
		public Enemy invoke() {
			final InsecteNv4 b = InsecteNv4.POOL.obtain();
			b.init();
			Enemy.LIST.add(b);
			return b;
		}
	}),
	GROUP(new Summoner() {
		@Override
		public Enemy invoke() {
			Group.initAll();
			return null;
		}
	}),
	GROUP3(new Summoner() {
		@Override
		public Enemy invoke() {
			Group3.initAll();
			return null;
		}
	}),
	GROUP4(new Summoner() {
		@Override
		public Enemy invoke() {
			Group4.initAll();
			return null;
		}
	}),
	DE_BASE(new Summoner() {
		@Override
		public Enemy invoke() {
			final DeBase db = DeBase.POOL.obtain();
			initEnemyDown(db);
			return db;
			}
		}),
	DE_BASE3(new Summoner() {		
		@Override		
		public Enemy invoke() {
			final DeBaseNv3 db = DeBaseNv3.POOL.obtain();
			initEnemyDown(db);
			return db;
		}
	}),
	DE_BASE4(new Summoner() {
		@Override
		public Enemy invoke() {
			final DeBaseNv4 db = DeBaseNv4.POOL.obtain();
			initEnemyDown(db);
			return db;
		}
	});
	
	
	
	private static void initEnemyDown(final Enemy db) {
		Enemy.LIST.add(db);
		db.getPosition().y = CSG.SCREEN_HEIGHT;
		db.dir.y = -db.getSpeed();
	}
	public Summoner incantation;

	private Merlin(Summoner incantation) {
		this.incantation = incantation;
	}

}
