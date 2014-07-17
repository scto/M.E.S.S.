package elements.particular.particles;

import jeu.CSG;
import jeu.Stats;
import jeu.mode.EndlessMode;
import assets.AssetMan;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import elements.generic.enemies.Enemy;
import elements.generic.weapons.enemies.EnemyWeapon;
import elements.generic.weapons.player.ArmeAdd;
import elements.generic.weapons.player.Fireball;
import elements.generic.weapons.player.PinkWeapon;
import elements.generic.weapons.player.PlayerWeapon;
import elements.generic.weapons.player.SunWeapon;
import elements.generic.weapons.player.TWeapon;
import elements.particular.Player;
import elements.particular.bonuses.Bomb;
import elements.particular.particles.individual.Ghost;
import elements.particular.particles.individual.PrecalculatedParticles;
import elements.particular.particles.individual.SparklesColorOverTimeWide;
import elements.particular.particles.individual.TimeParticle;
import elements.particular.particles.individual.background.Dust;
import elements.particular.particles.individual.background.Star;
import elements.particular.particles.individual.explosions.DebrisExplosion;
import elements.particular.particles.individual.explosions.Explosion;
import elements.particular.particles.individual.explosions.ColorOverTime;
import elements.particular.particles.individual.explosions.ExplosionImpactBullet;
import elements.particular.particles.individual.explosions.SparklesColorOverTime;
import elements.particular.particles.individual.smoke.BlueSmoke;
import elements.particular.particles.individual.smoke.MovingSmoke;
import elements.particular.particles.individual.smoke.Smoke;
import elements.particular.particles.individual.trhuster.ThrusterParticle;
import elements.particular.particles.individual.weapon.BlueSweepParticle;
import elements.particular.particles.individual.weapon.FireballParticle;
import elements.particular.particles.individual.weapon.GreenAddParticle;
import elements.particular.particles.individual.weapon.PinkParticle;
import elements.particular.particles.individual.weapon.SpaceInvaderParticle;
import elements.particular.particles.individual.weapon.SunParticle;
import elements.particular.particles.individual.weapon.TWeaponParticles;

public class Particles {
	 	
	public static final int MAX_THRUSTER = 600, MAX_BACKGROUND = 400, MAX_DUST = 5;
	public static int nbFlammes = 0;
	
	private static final Array<Dust> DUST = new Array<Dust>(false, MAX_DUST);
	private static final Array<Star> STAR = new Array<Star>(false, 300);
	
	public static final Array<Explosion> EXPLOSIONS = new Array<Explosion>();
	public static final Array<ColorOverTime> EXPLOSION_COLOR_OVER_TIME = new Array<ColorOverTime>();
	
	public static final Array<DebrisExplosion> WHITE_SPARKLES_NOT_MOVING = new Array<DebrisExplosion>();
	public static final Array<Explosion> EXPLOSIONS_IMPACT = new Array<Explosion>();
	public static final Array<ExplosionImpactBullet> EXPLOSION_IMPACT_BULLET = new Array<ExplosionImpactBullet>();
	
	public static final Array<SparklesColorOverTime> COLOR_OVER_TIME = new Array<SparklesColorOverTime>();
	public static final Array<SparklesColorOverTimeWide> COLOR_OVER_TIME_FOREGROUND = new Array<SparklesColorOverTimeWide>();
	
	private static final Array<GreenAddParticle> ADD = new Array<GreenAddParticle>();
	private static final Array<FireballParticle> FIREBALL = new Array<FireballParticle>();
	private static final Array<TWeaponParticles> T_WEAPON = new Array<TWeaponParticles>();
	private static final Array<ThrusterParticle> THRUSTER = new Array<ThrusterParticle>(false, MAX_THRUSTER);
	private static final Array<Ghost> GHOSTS = new Array<Ghost>(false, 20);
	private static final Array<Smoke> SMOKE = new Array<Smoke>(false, 20);
	private static final Array<MovingSmoke> MOVING_SMOKE = new Array<MovingSmoke>(false, 20);
	private static final Array<BlueSmoke> BLUESMOKE = new Array<BlueSmoke>(false, 20);
	public static final Array<SunParticle> SUN_WEAPON = new Array<SunParticle>(false, MAX_THRUSTER);
	public static final Array<PinkParticle> PINK_WEAPON = new Array<PinkParticle>(false, MAX_THRUSTER);
	public static final Array<BlueSweepParticle> BLUE_SWEEP_WEAPON = new Array<BlueSweepParticle>();
	public static final Array<SpaceInvaderParticle> SPACE_INVADER = new Array<SpaceInvaderParticle>(300);
	public static final Array<TimeParticle> TIME = new Array<TimeParticle>(40);
	private static float yShip = 0;
	
	public static void initBackground() {
		Star.initBackground(STAR);
	}
	
	/**
	 * Display the background and add a star if required
	 * @param batch
	 */
	public static void background(SpriteBatch batch) {
		batch.draw(AssetMan.background, -CSG.widthDiv10 *2, -CSG.heightDiv10, CSG.width + CSG.widthDiv10*3, CSG.height + CSG.heightDiv10);
		Star.act(batch, STAR);
		SparklesColorOverTime.act(COLOR_OVER_TIME, batch);
		Explosion.act(EXPLOSIONS, batch);
		ColorOverTime.act(EXPLOSION_COLOR_OVER_TIME, batch);
	}

	public static int debris = 0, add = 0,  explosionsGreen = 0, explosionBleu = 0, explosion = 0, explosionsImpact = 0, shield = 0, pinkWeapon = 0, thruster = 0,
			tWeapon = 0, spaceInv = 0, fireball = 0, blueSweep = 0, sunWeapon = 0, ghost = 0, smoke = 0, movingSmoke = 0, blueSmoke = 0, sparkles = 0,
			explosionImpactBullet = 0;
	public static void draw(SpriteBatch batch) {
		ThrusterParticle.act(THRUSTER, batch);
		PinkParticle.act(PINK_WEAPON, batch);
		SpaceInvaderParticle.act(SPACE_INVADER, batch);
		TWeaponParticles.act(T_WEAPON, batch);
		GreenAddParticle.act(ADD, batch);
		BlueSweepParticle.act(BLUE_SWEEP_WEAPON, batch);
		FireballParticle.act(FIREBALL, batch);
		SunParticle.act(SUN_WEAPON, batch);
		Ghost.act(GHOSTS, batch);
		Smoke.act(SMOKE, batch);
		MovingSmoke.act(MOVING_SMOKE, batch);
		BlueSmoke.act(BLUESMOKE, batch);
		Dust.act(batch, DUST);
		DebrisExplosion.act(WHITE_SPARKLES_NOT_MOVING, batch);
		TimeParticle.act(TIME, batch);
	}

	public static void drawImpacts(SpriteBatch batch) {
		ExplosionImpactBullet.act(EXPLOSION_IMPACT_BULLET, batch);
	}
	
	public static void addPartEnemyTouched(PlayerWeapon a, Enemy e) {
			SparklesColorOverTime.bulletImpact(COLOR_OVER_TIME, a, a.getColors());
			ExplosionImpactBullet.add(EXPLOSION_IMPACT_BULLET, a);
	}

	public static void addThrusterParticles(Player v) {
		if (THRUSTER.size > 1000)
			return;
		if (yShip-1 < Player.POS.y) {
			for (int i = 0; i < 10; i++)
				THRUSTER.add(ThrusterParticle.POOL.obtain().init(v));

			if (yShip+1 < Player.POS.y)
				for (int i = 0; i < EndlessMode.fps; i++)
					THRUSTER.add(ThrusterParticle.POOL.obtain().init(v));
		}
		yShip = Player.POS.y;
	}

	public static void clear() {
		GreenAddParticle.clear(ADD);
		Explosion.clear(EXPLOSIONS);
		Explosion.clear(EXPLOSIONS_IMPACT);
		DebrisExplosion.clear(WHITE_SPARKLES_NOT_MOVING);
		PinkParticle.clear(PINK_WEAPON);
		ThrusterParticle.clear(THRUSTER);
		TWeaponParticles.clear(T_WEAPON);
		SpaceInvaderParticle.clear(SPACE_INVADER);
		FireballParticle.clear(FIREBALL);
		BlueSweepParticle.clear(BLUE_SWEEP_WEAPON);
		SunParticle.clear(SUN_WEAPON);
		Ghost.clear(GHOSTS);
		Smoke.clear(SMOKE);
		MovingSmoke.clear(MOVING_SMOKE);
		BlueSmoke.clear(BLUESMOKE);
		ExplosionImpactBullet.clear(EXPLOSION_IMPACT_BULLET);
		TimeParticle.clear(TIME);
		SparklesColorOverTime.clear(COLOR_OVER_TIME);
		SparklesColorOverTimeWide.clear(COLOR_OVER_TIME_FOREGROUND);
		ColorOverTime.clear(EXPLOSION_COLOR_OVER_TIME);
		nbFlammes = 0;
		Dust.next = 0;
	}
	
	public static void explosion(Enemy e) {
		switch (e.getColor()) {
		case Enemy.BLUE:
			Explosion.add(EXPLOSIONS, e, PrecalculatedParticles.RANDDOM_BLUES);
			SparklesColorOverTime.add(COLOR_OVER_TIME, e, PrecalculatedParticles.colorsOverTimeBlue);
			ColorOverTime.add(EXPLOSION_COLOR_OVER_TIME, e, ParticuleBundles.BLUE_LONG);
			break;
		case Enemy.GREEN:
			Explosion.add(EXPLOSIONS, e, PrecalculatedParticles.RANDDOM_GREENS);
			SparklesColorOverTime.add(COLOR_OVER_TIME, e, PrecalculatedParticles.colorsOverTimeYellowToGreen, PrecalculatedParticles.colorsOverTimeCyanToGreen);
			ColorOverTime.add(EXPLOSION_COLOR_OVER_TIME, e, ParticuleBundles.YELLOW_TO_GREEN_LONG, ParticuleBundles.CYAN_TO_GREEN_LONG);
			break;
		default:
			Explosion.add(EXPLOSIONS, e, PrecalculatedParticles.RANDDOM_REDS);
			SparklesColorOverTime.add(COLOR_OVER_TIME, e, PrecalculatedParticles.colorsOverTimeRed);
			ColorOverTime.add(EXPLOSION_COLOR_OVER_TIME, e, ParticuleBundles.RED_LONG);
			break;
		}
	}

	public static void armeHantee(TWeapon a) {
		TWeaponParticles.add(T_WEAPON, a);
	}

	public static void ajoutAdd(ArmeAdd a) {
		GreenAddParticle.add(a, ADD);
	}

	public static void ajoutArmeDeBase(Fireball a) {
		FireballParticle.add(a, FIREBALL);
	}

	public static void pinkParticle(PinkWeapon a) {
		final PinkParticle p = PinkParticle.POOL.obtain();
		p.init(a);
		PINK_WEAPON.add(p);
	}

	public static void ajoutSun(SunWeapon sunWeapon, Vector2 vector) {
		final SunParticle p = SunParticle.POOL.obtain();
		p.init(sunWeapon, vector);
		SUN_WEAPON.add(p);
	}

	public static void addGhost(int etat) {
		final Ghost g = Ghost.POOL.obtain();
		g.init(etat);
		GHOSTS.add(g);
	}
	
	public static void smokeMoving(float x, float y, Vector2 dir, int color) {
		
		addSmokeMoving(x, y, dir, color, MovingSmoke.POOL.obtain());
	}

	private static void addSmokeMoving(float x, float y, Vector2 dir, int color, final MovingSmoke s) {
		s.init(x, y, convertColor(color), dir);
		MOVING_SMOKE.add(s);
	}

	public static float[] convertColor(int color) {
		switch (color) {
		case Enemy.BLUE:	return PrecalculatedParticles.colorsBlue;
		case Enemy.GREEN:	return PrecalculatedParticles.colorsYellowToGreen;
		default:			return PrecalculatedParticles.colorsRed;
		}
	}
	
	public static void smokeMoving(float x, float y, boolean rnd, int color) {
		addSmokeMoving(x, y, rnd, color, MovingSmoke.POOL.obtain());
	}

	private static void addSmokeMoving(float x, float y, boolean rnd, int color, final MovingSmoke s) {
		s.init(x, y, rnd, convertColor(color));
		MOVING_SMOKE.add(s);
	}
	
	public static void smokeMoving(float x, float y, boolean rnd, int color, Vector2 dir) {
		addMovingSmoke(x, y, rnd, color, dir, MovingSmoke.POOL.obtain());
	}

	private static void addMovingSmoke(float x, float y, boolean rnd, int color, Vector2 dir, final MovingSmoke s) {
		s.init(x, y, rnd, convertColor(color), dir);
		MOVING_SMOKE.add(s);
	}

	public static void smoke(float x, float y, boolean rnd, float[] colors) {
		final Smoke s = Smoke.POOL.obtain();
		s.init(x, y, rnd, colors);
		SMOKE.add(s);
	}
	
	public static void smoke(float x, float y, boolean rnd, float[] colors, float dirX, float dirY) {
		final MovingSmoke s = MovingSmoke.POOL.obtain();
		s.init(x, y, rnd, colors, dirX, dirY);
		MOVING_SMOKE.add(s);
	}
	
	public static void blueSmoke(float x, float y) {
		final BlueSmoke s = BlueSmoke.POOL.obtain();
		s.init(x, y);
		BLUESMOKE.add(s);
	}

	private static float angle = 0, tmpX, tmpY;
	public static void popOutWeapon(EnemyWeapon w) {
		angle = 0;
		tmpX = w.pos.x + w.getDimensions().halfWidth;
		tmpY = w.pos.y + w.getDimensions().halfHeight;
		for (int i = 0; i < 10; i++) {
			SparklesColorOverTime.add(tmpX, tmpY, angle, PrecalculatedParticles.colorsOverTimeYellowToGreenLong, Stats.U12);
			SparklesColorOverTime.add(tmpX, tmpY, angle + 18, PrecalculatedParticles.colorsOverTimeBlueLong, Stats.U5);
			angle += 36;
		}
	}
	
	public static void bomb(Bomb bonusBombe) {
		DebrisExplosion.bomb(bonusBombe, WHITE_SPARKLES_NOT_MOVING);
	}

	public static void addChronoGenerator() {
		for (Enemy e : Enemy.LIST)
			TimeParticle.generate(e, TIME);
	}

	public static void bombExplosion(PlayerWeapon a) {
		Explosion.blow(a, EXPLOSIONS);
		ExplosionImpactBullet.blow(a, EXPLOSION_IMPACT_BULLET);
		SparklesColorOverTime.blow(a, COLOR_OVER_TIME);
	}

	public static void foreground(SpriteBatch batch) {
		SparklesColorOverTimeWide.act(COLOR_OVER_TIME_FOREGROUND, batch);
	}

	public static int getNombreParticles() {
		return DUST.size + STAR.size + EXPLOSIONS.size + EXPLOSION_COLOR_OVER_TIME.size + WHITE_SPARKLES_NOT_MOVING.size + EXPLOSIONS_IMPACT.size +
				EXPLOSION_IMPACT_BULLET.size + COLOR_OVER_TIME.size + COLOR_OVER_TIME_FOREGROUND.size  + ADD.size +	FIREBALL.size + T_WEAPON.size + THRUSTER.size +
				GHOSTS.size + SMOKE.size + MOVING_SMOKE.size + BLUESMOKE.size + SUN_WEAPON.size + PINK_WEAPON.size + BLUE_SWEEP_WEAPON.size + SPACE_INVADER.size + TIME.size;
	}

	public static void shot(float x, float y, float angle) {
		for (int i = 0; i < 2; i++)
			SparklesColorOverTime.add(x, y, angle + (float)CSG.R.nextGaussian() * 25, PrecalculatedParticles.colorsOverTimeMuzzle, (Stats.U50 + (Stats.U270 * CSG.R.nextFloat())) * 0.75f );
	}

	private static float tmpOffsetX, tmpOffsetY;
	public static void enemyHit(Enemy e, float[] colors) {
		for (int i = 0; i < 5; i++) {
			do {
				tmpOffsetX = (float) (CSG.R.nextGaussian() * e.getDimensions().quartWidth);
			} while (Math.abs(tmpOffsetX) > e.getDimensions().halfWidth);
			do {
				tmpOffsetY = (float) (CSG.R.nextGaussian() * e.getDimensions().quartHeight);
			} while (Math.abs(tmpOffsetY) > e.getDimensions().halfHeight);
			Particles.smoke(e.pos.x + e.getDimensions().halfWidth + tmpOffsetX, e.pos.y + e.getDimensions().halfHeight + tmpOffsetY, false, colors);
		}
	}
	
}
