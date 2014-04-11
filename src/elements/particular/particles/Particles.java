package elements.particular.particles;

import jeu.CSG;
import jeu.EndlessMode;
import assets.AssetMan;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import elements.generic.Player;
import elements.generic.enemies.Enemy;
import elements.generic.weapons.Weapons;
import elements.generic.weapons.enemies.EnemyWeapon;
import elements.generic.weapons.player.ArmeAdd;
import elements.generic.weapons.player.BlueSweepWeapon;
import elements.generic.weapons.player.Fireball;
import elements.generic.weapons.player.PinkWeapon;
import elements.generic.weapons.player.PlayerWeapon;
import elements.generic.weapons.player.SunWeapon;
import elements.generic.weapons.player.TWeapon;
import elements.particular.bonuses.BonusBombe;
import elements.particular.particles.individual.BlueSmoke;
import elements.particular.particles.individual.BlueSparkles;
import elements.particular.particles.individual.Debris;
import elements.particular.particles.individual.Ghost;
import elements.particular.particles.individual.MovingSmoke;
import elements.particular.particles.individual.ShieldParticle;
import elements.particular.particles.individual.Smoke;
import elements.particular.particles.individual.ThrusterParticle;
import elements.particular.particles.individual.ThrusterSideParticle;
import elements.particular.particles.individual.TimeParticle;
import elements.particular.particles.individual.background.Dust;
import elements.particular.particles.individual.background.Star;
import elements.particular.particles.individual.explosions.BlueExplosion;
import elements.particular.particles.individual.explosions.DebrisExplosion;
import elements.particular.particles.individual.explosions.Explosion;
import elements.particular.particles.individual.explosions.ExplosionImpactBullet;
import elements.particular.particles.individual.explosions.GreenExplosion;
import elements.particular.particles.individual.explosions.Spark;
import elements.particular.particles.individual.explosions.SparkBlue;
import elements.particular.particles.individual.explosions.SparkGreen;
import elements.particular.particles.individual.weapon.BlueSweepParticle;
import elements.particular.particles.individual.weapon.FireballParticle;
import elements.particular.particles.individual.weapon.GreenAddParticle;
import elements.particular.particles.individual.weapon.PinkParticle;
import elements.particular.particles.individual.weapon.SpaceInvaderParticle;
import elements.particular.particles.individual.weapon.SunParticle;
import elements.particular.particles.individual.weapon.TWeaponParticles;

public class Particles {
	 	
	public static final int MAX_THRUSTER = 240, MAX_BACKGROUND = 300, MAX_DUST = 5;
	public static int nbFlammes = 0;
	
	private static final Array<Dust> DUST = new Array<Dust>(false, MAX_DUST);
	private static final Array<Star> STAR = new Array<Star>(false, 300);
	
	public static final Array<Spark> SPARKS = new Array<Spark>();
	public static final Array<SparkBlue> SPARKS_BLUE = new Array<SparkBlue>();
	public static final Array<SparkGreen> SPARKS_GREEN = new Array<SparkGreen>();
	
	public static final Array<Explosion> EXPLOSIONS = new Array<Explosion>();
	public static final Array<BlueExplosion> BLUE_EXPLOSION = new Array<BlueExplosion>();
	public static final Array<GreenExplosion> EXPLOSIONS_GREENS = new Array<GreenExplosion>();
	
	public static final Array<DebrisExplosion> DEBRIS_EXPLOSIONS = new Array<DebrisExplosion>();
	public static final Array<Explosion> EXPLOSIONS_IMPACT = new Array<Explosion>();
	public static final Array<ExplosionImpactBullet> EXPLOSION_IMPACT_BULLET = new Array<ExplosionImpactBullet>();
	
	private static final Array<GreenAddParticle> ADD = new Array<GreenAddParticle>();
	private static final Array<ParticleUiElement> UI = new Array<ParticleUiElement>();
	private static final Array<Debris> DEBRIS = new Array<Debris>(false, MAX_THRUSTER/4);
	private static final Array<FireballParticle> FIREBALL = new Array<FireballParticle>();
	private static final Array<TWeaponParticles> T_WEAPON = new Array<TWeaponParticles>();
	public static final Array<ParticlePanneau> BOUTONS = new Array<ParticlePanneau>(false, 300);
	private static final Array<ThrusterParticle> THRUSTER = new Array<ThrusterParticle>(false, MAX_THRUSTER);
	private static final Array<ThrusterSideParticle> THRUSTER_S = new Array<ThrusterSideParticle>(30);
	private static final Array<Ghost> GHOSTS = new Array<Ghost>(false, 20);
	private static final Array<Smoke> SMOKE = new Array<Smoke>(false, 20);
	private static final Array<MovingSmoke> MOVING_SMOKE = new Array<MovingSmoke>(false, 20);
	private static final Array<BlueSmoke> BLUESMOKE = new Array<BlueSmoke>(false, 20);
	public static final Array<SunParticle> SUN_WEAPON = new Array<SunParticle>(false, MAX_THRUSTER);
	public static final Array<PinkParticle> PINK_WEAPON = new Array<PinkParticle>(false, MAX_THRUSTER);
	public static final Array<BlueSweepParticle> BLUE_SWEEP_WEAPON = new Array<BlueSweepParticle>();
	public static final Array<ShieldParticle> SHIELD = new Array<ShieldParticle>(43);
	public static final Array<BlueSparkles> SPARKLES = new Array<BlueSparkles>(20);
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
//		batch.setColor( 0.5f + (CSG.R.nextFloat() / 2), 1, 1, 1);
		batch.draw(AssetMan.background, -CSG.DIXIEME_LARGEUR, -CSG.HEIGHT_DIV10, CSG.gameZoneWidth + CSG.DIXIEME_LARGEUR, CSG.SCREEN_HEIGHT + CSG.HEIGHT_DIV10);
//		batch.setColor(AssetMan.WHITE);
		Star.act(batch, STAR);
		Explosion.act(EXPLOSIONS, batch);
		BlueExplosion.act(BLUE_EXPLOSION, batch);
		GreenExplosion.act(EXPLOSIONS_GREENS, batch);
	}

	public static void drawUi(SpriteBatch batch) {
		for (final ParticlePanneau p : BOUTONS) {
			p.display(batch);
//			if (p.mouvementEtVerif() == false) {
			BOUTONS.removeValue(p, true);
			ParticlePanneau.pool.free(p);
//			}
		}
		for (final ParticleUiElement p : UI){
			p.display(batch);
//			if (p.mouvementEtVerif() == false) {
			UI.removeValue(p, true);
			ParticleUiElement.pool.free(p);
//			}
		}
	}
	

	public static int debris = 0, add = 0,  explosionsGreen = 0, explosionBleu = 0, explosion = 0, explosionsImpact = 0, shield = 0, pinkWeapon = 0, thruster = 0,
			tWeapon = 0, spaceInv = 0, fireball = 0, blueSweep = 0, sunWeapon = 0, ghost = 0, smoke = 0, movingSmoke = 0, blueSmoke = 0, sparkles = 0,
			explosionImpactBullet = 0;
	public static void draw(SpriteBatch batch) {
		Debris.act(DEBRIS, batch);
		ThrusterParticle.act(THRUSTER, batch);
		ThrusterSideParticle.act(THRUSTER_S, batch);
		PinkParticle.act(PINK_WEAPON, batch);
		SpaceInvaderParticle.act(SPACE_INVADER, batch);
		TWeaponParticles.act(T_WEAPON, batch);
		GreenAddParticle.act(ADD, batch);
		BlueSweepParticle.act(BLUE_SWEEP_WEAPON, batch);
		FireballParticle.act(FIREBALL, batch);
		ShieldParticle.act(batch, SHIELD);
		SunParticle.act(SUN_WEAPON, batch);
		Ghost.act(GHOSTS, batch);
		Smoke.act(SMOKE, batch);
		MovingSmoke.act(MOVING_SMOKE, batch);
		BlueSmoke.act(BLUESMOKE, batch);
		BlueSparkles.act(SPARKLES, batch);
		Dust.act(batch, DUST);
		DebrisExplosion.act(DEBRIS_EXPLOSIONS, batch);
		TimeParticle.act(TIME, batch);
		Spark.act(SPARKS, batch);
		SparkBlue.act(SPARKS_BLUE, batch);
		SparkGreen.act(SPARKS_GREEN, batch);
//		tests();
	}

	private static void tests() {
		debris = compare(DEBRIS, debris);
		thruster = compare(THRUSTER, thruster);
		pinkWeapon = compare(PINK_WEAPON, pinkWeapon);
		explosionsGreen = compare(EXPLOSIONS_GREENS, explosionsGreen);
		add = compare(ADD, add);
		explosionBleu = compare(BLUE_EXPLOSION, explosionBleu);
		explosion = compare(EXPLOSIONS, explosion);
		explosionsImpact = compare(EXPLOSION_IMPACT_BULLET, explosionsImpact);
		shield = compare(SHIELD, shield);
		tWeapon = compare(T_WEAPON, tWeapon);
		spaceInv = compare(SPACE_INVADER, spaceInv);
		fireball = compare(FIREBALL, fireball);
		blueSweep = compare(BLUE_EXPLOSION, blueSweep);
		sunWeapon = compare(SUN_WEAPON, sunWeapon);
		ghost = compare(GHOSTS, ghost);
		smoke = compare(SMOKE, smoke);
		movingSmoke = compare(MOVING_SMOKE, movingSmoke);
		blueSmoke = compare(BLUESMOKE, blueSmoke);
		sparkles = compare(SPARKLES, sparkles);
		explosionImpactBullet = compare(EXPLOSION_IMPACT_BULLET, explosionImpactBullet);
		System.out.println("------------------------------");
		System.out.println("- debris                " + debris);
		System.out.println("- thruster              " + thruster);
		System.out.println("- pinkWeapon            " + pinkWeapon);
		System.out.println("- explosionsGreen       " + explosionsGreen);
		System.out.println("- add                   " + add);
		System.out.println("- explosionBleu         " + explosionBleu);
		System.out.println("- explosion             " + explosion);
		System.out.println("- explosionsImpact      " + explosionsImpact);
		System.out.println("- shield                " + shield);
		System.out.println("- tWeapon               " + tWeapon);
		System.out.println("- spaceInv              " + spaceInv);
		System.out.println("- fireball              " + fireball);
		System.out.println("- blueSweep             " + blueSweep);
		System.out.println("- sunWeapon             " + sunWeapon);
		System.out.println("- ghost                 " + ghost);
		System.out.println("- smoke                 " + smoke);
		System.out.println("- movingSmoke           " + movingSmoke);
		System.out.println("- blueSmoke             " + blueSmoke);
		System.out.println("- sparkles              " + sparkles);
		System.out.println("- explosionImpactBullet " + explosionImpactBullet);
	}
	
	private static int compare(Array<?> array, int i) {
		if (array.size > i)
			return array.size;
		return i;
	}

	public static void drawImpacts(SpriteBatch batch) {
//		Explosion.act(EXPLOSIONS_IMPACT, batch);
		ExplosionImpactBullet.act(EXPLOSION_IMPACT_BULLET, batch);
	}
	
	public static void addPartEnemyTouched(PlayerWeapon a, Enemy e) {
//		Debris.add(a, DEBRIS);
//		DebrisExplosion.addEnemyTouched(DEBRIS_EXPLOSIONS, a);
		ExplosionImpactBullet.add(EXPLOSION_IMPACT_BULLET, a);
	}

	public static void ajoutFlammes(Player v) {
		if (nbFlammes < Particles.MAX_THRUSTER) {
			addThrusterParticle(v);
			if (yShip-1 < Player.POS.y) {
				addThrusterParticle(v);
				addThrusterParticle(v);
				if (yShip+1 < Player.POS.y) {
					addThrusterParticle(v);
				}
			}
		}
		for (int i = 0; i < EndlessMode.explosions * 10; i++) {
			addThrusterParticle(v);
		}
		yShip = Player.POS.y;
	}

	private static void addThrusterParticle(Player v) {
		final ThrusterParticle r = ThrusterParticle.POOL.obtain();
		r.init(v);
		THRUSTER.add(r);
	}
	
	public static void addThrusterParticle(float posX, float posY, boolean toLeft) {
		final ThrusterSideParticle r = ThrusterSideParticle.POOL.obtain();
		r.init(posX, posY, toLeft);
		THRUSTER_S.add(r);
	}

	public static void clear() {
		Debris.clear(DEBRIS);
		GreenAddParticle.clear(ADD);
		Spark.clear(SPARKS);
		SparkBlue.clear(SPARKS_BLUE);
		SparkGreen.clear(SPARKS_GREEN);
		GreenExplosion.clear(EXPLOSIONS_GREENS);
		BlueExplosion.clear(BLUE_EXPLOSION);
		Explosion.clear(EXPLOSIONS);
		Explosion.clear(EXPLOSIONS_IMPACT);
		DebrisExplosion.clear(DEBRIS_EXPLOSIONS);
		ShieldParticle.clear(SHIELD);
		ParticlePanneau.clear(BOUTONS);
		PinkParticle.clear(PINK_WEAPON);
		ThrusterParticle.clear(THRUSTER);
		ThrusterSideParticle.clear(THRUSTER_S);
		TWeaponParticles.clear(T_WEAPON);
		SpaceInvaderParticle.clear(SPACE_INVADER);
		FireballParticle.clear(FIREBALL);
		BlueSweepParticle.clear(BLUE_SWEEP_WEAPON);
		SunParticle.clear(SUN_WEAPON);
		Ghost.clear(GHOSTS);
		Smoke.clear(SMOKE);
		MovingSmoke.clear(MOVING_SMOKE);
		BlueSmoke.clear(BLUESMOKE);
		BlueSparkles.clear(SPARKLES);
		ExplosionImpactBullet.clear(EXPLOSION_IMPACT_BULLET);
		TimeParticle.clear(TIME);
		nbFlammes = 0;
	}
	
	public static void explosionGreen(float posX, float posY, int max) {
		GreenExplosion.add(max, EXPLOSIONS_GREENS, posX, posY);
	}
	
	public static void explosion(Enemy ennemis) {
		Explosion.add(EXPLOSIONS, ennemis);
		DebrisExplosion.add(DEBRIS_EXPLOSIONS, ennemis);
		Spark.add(SPARKS, ennemis);
	}

	public static void explosionBlue(Enemy e) {
		DebrisExplosion.add(DEBRIS_EXPLOSIONS, e);
		BlueExplosion.add(BLUE_EXPLOSION, e);
		SparkBlue.add(SPARKS_BLUE, e);
	}

	public static void explosionGreen(Enemy e) {
		DebrisExplosion.add(DEBRIS_EXPLOSIONS, e);
		GreenExplosion.add(EXPLOSIONS_GREENS, e);
		SparkGreen.add(SPARKS_GREEN, e);
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

	public static void ajoutArmeBalayage(BlueSweepWeapon a) {
		BlueSweepParticle.add(a, BLUE_SWEEP_WEAPON);
	}

	public static void pinkParticle(PinkWeapon a) {
		final PinkParticle p = PinkParticle.POOL.obtain();
		p.init(a);
		PINK_WEAPON.add(p);
	}

	public static void ajoutPanneau(Vector2 posParticule, Vector2 baseDirection) {
		final ParticlePanneau p = ParticlePanneau.pool.obtain();
		p.init(posParticule, baseDirection);
		BOUTONS.add(p);
	}
	
	public static void ajoutUiElement(float x, float y, boolean selected) {
		final ParticleUiElement p = ParticleUiElement.pool.obtain();
		p.init(x, y, selected);
		UI.add(p);
		
		final ParticleUiElement p2 = ParticleUiElement.pool.obtain();
		p2.init(x, y, selected);
		UI.add(p2);
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
	
	public static void smokeMoving(float x, float y, boolean rnd) {
		final MovingSmoke s = MovingSmoke.POOL.obtain();
		s.init(x, y, rnd);
		MOVING_SMOKE.add(s);
	}

	public static void smoke(float x, float y, boolean rnd) {
		final Smoke s = Smoke.POOL.obtain();
		s.init(x, y, rnd);
		SMOKE.add(s);
	}
	
	public static void blueSmoke(float x, float y) {
		final BlueSmoke s = BlueSmoke.POOL.obtain();
		s.init(x, y);
		BLUESMOKE.add(s);
	}

	public static void popOutWeapon(EnemyWeapon w) {
		for (int i = 0; i < 10; i++)
			ShieldParticle.addLong(w.pos.x + w.getWidth() * CSG.R.nextFloat(), w.pos.y + w.getHeight() * CSG.R.nextFloat());
	}

	public static void addSparkle(Weapons e) {
		BlueSparkles.add(e, SPARKLES);
	}

	public static void bomb(BonusBombe bonusBombe) {
		DebrisExplosion.bomb(bonusBombe, DEBRIS_EXPLOSIONS);
	}

	public static void addChronoGenerator() {
		for (Enemy e : Enemy.LIST)
			TimeParticle.generate(e, TIME);
	}

	public static void bombExplosion(PlayerWeapon a) {
		Explosion.blow(a, EXPLOSIONS);
		GreenExplosion.blow(a, EXPLOSIONS_GREENS);
		BlueExplosion.blow(a, BLUE_EXPLOSION);
		ExplosionImpactBullet.blow(a, EXPLOSION_IMPACT_BULLET);
	}
	
}
