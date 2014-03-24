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
import elements.particular.particles.individual.BlueSmoke;
import elements.particular.particles.individual.BlueSparkles;
import elements.particular.particles.individual.Debris;
import elements.particular.particles.individual.Explosion;
import elements.particular.particles.individual.Ghost;
import elements.particular.particles.individual.GreenExplosion;
import elements.particular.particles.individual.MovingSmoke;
import elements.particular.particles.individual.ShieldParticle;
import elements.particular.particles.individual.Smoke;
import elements.particular.particles.individual.Star;
import elements.particular.particles.individual.ThrusterParticle;
import elements.particular.particles.individual.ThrusterSideParticle;
import elements.particular.particles.individual.weapon.BlueSweepParticle;
import elements.particular.particles.individual.weapon.FireballParticle;
import elements.particular.particles.individual.weapon.GreenAddParticle;
import elements.particular.particles.individual.weapon.PinkParticle;
import elements.particular.particles.individual.weapon.SpaceInvaderParticle;
import elements.particular.particles.individual.weapon.SunParticle;
import elements.particular.particles.individual.weapon.TWeaponParticles;

public class Particles {
	 	
	public static final int MAX_THRUSTER = 240, MAX_BACKGROUND = 300;
	public static int nbFlammes = 0;
	
	private static final Array<Star> STAR = new Array<Star>(false, 300);
	public static final Array<Explosion> EXPLOSIONS = new Array<Explosion>();
	public static final Array<Explosion> EXPLOSIONS_IMPACT = new Array<Explosion>();
	public static final Array<GreenExplosion> EXPLOSIONS_GREENS = new Array<GreenExplosion>();
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
	private static float yShip = 0;
	
	public static void initBackground() {
		Star.initBackground(STAR);
	}
	
	/**
	 * Display the background and add a star if required
	 * @param batch
	 */
	public static void background(SpriteBatch batch) {
		batch.draw(AssetMan.background, -CSG.DIXIEME_LARGEUR, -CSG.HEIGHT_DIV10, CSG.gameZoneWidth + CSG.DIXIEME_LARGEUR, CSG.SCREEN_HEIGHT + CSG.HEIGHT_DIV10);
		Star.act(batch, STAR);
		Explosion.act(EXPLOSIONS, batch);
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
	}
	public static void impacts(SpriteBatch batch) {
		Explosion.act(EXPLOSIONS_IMPACT, batch);
	}
	
	public static void addFragment(PlayerWeapon a, Enemy e) {
		Debris.add(a, DEBRIS);
		Explosion.addSmall(EXPLOSIONS_IMPACT, a, e);
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
		Explosion.clear(EXPLOSIONS);
		Explosion.clear(EXPLOSIONS_IMPACT);
		GreenExplosion.clear(EXPLOSIONS_GREENS);
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
		nbFlammes = 0;
	}

	public static void explosion(Enemy ennemis) {
		Explosion.add(EXPLOSIONS, ennemis);
	}
	
	public static void explosionGreen(float posX, float posY, int max) {
		GreenExplosion.add(max, EXPLOSIONS_GREENS, posX, posY);
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
	
}
