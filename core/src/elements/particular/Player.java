package elements.particular;

import jeu.CSG;
import jeu.Stats;
import jeu.mode.EndlessMode;
import jeu.mode.extensions.Transition;
import assets.AssetMan;
import behind.SoundMan;
import assets.sprites.AnimPlayer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import elements.generic.enemies.Enemy;
import elements.generic.weapons.Weapon;
import elements.generic.weapons.player.ArmeAdd;
import elements.generic.weapons.player.WeaponManager;
import elements.particular.particles.OvaleParticuleGenerator;
import elements.particular.particles.Particles;
import elements.particular.particles.individual.PrecalculatedParticles;
import elements.particular.particles.individual.explosions.Explosion;

public final class Player {

	public static final int WIDTH = (int) Stats.U4, HALF_WIDTH = WIDTH/2, WIDTH_ADD = (int) (WIDTH/1.5f), HALF_WIDTH_ADD = WIDTH_ADD/2, WIDTH_DIV_10 = WIDTH / 10,
		HEIGHT = (int) ((float)WIDTH * 1.5f), HALF_HEIGHT = HEIGHT / 2, HEIGHT_MAX_ADD = HEIGHT + HALF_HEIGHT, HALF_HEIGHT_ADD = HEIGHT / 8, HEIGHT_DIV4 = HEIGHT / 4, HEIGHT_DIV8 = HEIGHT/8,
		DECALAGE_ADD = WIDTH + HALF_WIDTH - WIDTH_ADD,
		DECALAGE_TIR_ADD_X_GAUCHE = (int) (-HALF_WIDTH - HALF_WIDTH_ADD + ArmeAdd.DIMENSIONS.halfWidth),
		DECALAGE_TIR_ADD_X_DROITE = (int) (DECALAGE_ADD - HALF_WIDTH_ADD + ArmeAdd.DIMENSIONS.halfWidth),
		LIMITE_X_GAUCHE = 0 - HALF_WIDTH, LIMITE_X_DROITE = CSG.screenWidth - HALF_WIDTH, LIMITE_Y_GAUCHE = 0 - HALF_HEIGHT, LIMITE_Y_DROITE = CSG.height - HALF_HEIGHT,
		LEFT_ADD1 = 0x0001, LEFT_ADD2 = 0x0002, RIGHT_ADD1 = 0x0004, RIGHT_ADD2 = 0x0008;
	public static float xCenter = 0, yCenter = 0, nextShot = 0, nextShotAdd = 0, prevX, prevY, destX, destY, addX, addY, centerLeft1AddX, centerAdd1Y, centerRight1AddX, centerLeft2AddX, centerAdd2Y,
			centerRight2AddX, vitesseFoisdelta = 0, tmpCalculDeplacement = 0, originalAccelX = 0, originalAccelY = 0, angleAdd = -90, angleAddDroite = -90, camXmoinsDemiEcran = CSG.halfWidth;
	public static WeaponManager weapon = CSG.profile.getArmeSelectionnee();
	private static final OvaleParticuleGenerator bouclierParticules = new OvaleParticuleGenerator(HEIGHT * 2);
	private static boolean shieldHS = false;
	public static final Vector2 POS = new Vector2();
	public static boolean leftDrone = false, rightDrone = false, leftDrone2 = false, rightDrone2 = false, alterner = true;
	public static int shield = 0;
	public static float tpsBouclierHs = 0;
	private float shotTime = 0;
	private int addShotNbr = 0;
	
	public Player() {
		super();
		initialiser();
	}

	/**
	 * positionne et initialise tout
	 */
	public void initialiser() {
		shield = 0;
		reInit();
		rightDrone2 = false;
	    rightDrone = false;
	    leftDrone2 = false;
	    leftDrone = false;
	    angleAdd = 80;
	    nextShotAdd = 0;
	}

	/**
	 * Positionne mais ne reset ni adds ni shield
	 */
	public void reInit() {
		POS.set(CSG.halfWidth - Player.HALF_WIDTH, Player.HEIGHT * 4);
		nextShot = 0;
		prevX = POS.x;
		prevY = POS.y;
		if (CSG.profile.controls == CSG.CONTROLE_ACCELEROMETRE) {
			originalAccelX = Gdx.input.getAccelerometerX();
			originalAccelY = Gdx.input.getAccelerometerY();
		}
		shieldHS = false;
		tpsBouclierHs = 0;
		xCenter = POS.x + HALF_WIDTH;
		yCenter = POS.y + HALF_HEIGHT;
	}
	
	public void draw(SpriteBatch batch) {
		Particles.addThrusterParticles(this);
		float x = Player.xCenter, y = Player.POS.y;
		
		batch.setColor(PrecalculatedParticles.colorsOverTimeCyanToGreen[CSG.R.nextInt(PrecalculatedParticles.colorsOverTimeCyanToGreen.length)]);
		batch.draw(AssetMan.dust, x - (Stats.U3 / 2), y - Stats.U3, Stats.U3, Stats.U8);
		batch.setColor(PrecalculatedParticles.colorsOverTimeCyanToGreen[CSG.R.nextInt(PrecalculatedParticles.colorsOverTimeCyanToGreen.length / 2)]);
		batch.draw(AssetMan.dust, x - Stats.U, y - Stats.U3, Stats.U2, Stats.U8);
		batch.setColor(PrecalculatedParticles.colorsOverTimeCyanToGreen[CSG.R.nextInt(PrecalculatedParticles.colorsOverTimeCyanToGreen.length / 3)]);
		batch.draw(AssetMan.dust, x - Stats.u, y - Stats.U3, Stats.U, Stats.U6);
		batch.setColor(PrecalculatedParticles.colorsOverTimeCyanToGreen[0]);
		batch.draw(AssetMan.dust, x - Stats.uDiv2, y - Stats.u * 3, Stats.u, Stats.U5);
		batch.setColor(PrecalculatedParticles.colorsOverTimeCyanToGreen[0]);
		batch.draw(AssetMan.dust, x - Stats.U, y - Stats.u * 3, Stats.U2, Stats.U6);
		
		batch.setColor(CSG.gm.palette().white);
		batch.draw(AnimPlayer.getTexture(), POS.x, POS.y, WIDTH, HEIGHT);
		shield();
		if (leftDrone) 	batch.draw(AssetMan.addShip, addX - HALF_WIDTH, 					addY - HALF_HEIGHT, 	HALF_WIDTH_ADD, HEIGHT_DIV8, WIDTH_ADD, HEIGHT_DIV4, 1, 1, angleAdd, 		false);
		if (rightDrone) 	batch.draw(AssetMan.addShip, addX + DECALAGE_ADD, 					addY - HALF_HEIGHT, 	HALF_WIDTH_ADD, HEIGHT_DIV8, WIDTH_ADD, HEIGHT_DIV4, 1, 1, angleAddDroite, false);
		if (leftDrone2) 	batch.draw(AssetMan.addShip, addX - WIDTH, 						addY - HEIGHT, 		HALF_WIDTH_ADD, HEIGHT_DIV8, WIDTH_ADD, HEIGHT_DIV4, 1, 1, angleAdd, 		false);
		if (rightDrone2) 	batch.draw(AssetMan.addShip, addX + DECALAGE_ADD + HALF_WIDTH, 	addY - HEIGHT, 		HALF_WIDTH_ADD, HEIGHT_DIV8, WIDTH_ADD, HEIGHT_DIV4, 1, 1, angleAddDroite, false);
		// ** ** A D D S
		if (shotTime > 0) {
			if (leftDrone)	batch.draw(AssetMan.addShipShot, addX - HALF_WIDTH, 					addY - HALF_HEIGHT, 	HALF_WIDTH_ADD, HEIGHT_DIV8, WIDTH_ADD, HEIGHT_DIV4, 1, 1, angleAdd, 		false);
			if (leftDrone2) 	batch.draw(AssetMan.addShipShot, addX - WIDTH, 						addY - HEIGHT, 		HALF_WIDTH_ADD, HEIGHT_DIV8, WIDTH_ADD, HEIGHT_DIV4, 1, 1, angleAdd, 		false);
			if (rightDrone)	batch.draw(AssetMan.addShipShot, addX + DECALAGE_ADD, 					addY - HALF_HEIGHT, 	HALF_WIDTH_ADD, HEIGHT_DIV8, WIDTH_ADD, HEIGHT_DIV4, 1, 1, angleAddDroite, false);
			if (rightDrone2)	batch.draw(AssetMan.addShipShot, addX + DECALAGE_ADD + HALF_WIDTH, 	addY - HEIGHT, 		HALF_WIDTH_ADD, HEIGHT_DIV8, WIDTH_ADD, HEIGHT_DIV4, 1, 1, angleAddDroite, false);
			shotTime -= EndlessMode.delta;
			if (shotTime < 0)
				addShotNbr = 0x0000;
		}
		elements.particular.particles.Smoke.draw(batch);
		batch.setColor(CSG.gm.palette().white);
	}

	private void shield() {
		if (shield > 0) {
			bouclierParticules.add(xCenter , yCenter - Stats.u);
		} else if (shieldHS) {
			tpsBouclierHs += EndlessMode.delta;
			if (tpsBouclierHs > 1f)
				shieldHS = false;
			bouclierParticules.grow(EndlessMode.unPlusDelta3);
			bouclierParticules.add(xCenter , yCenter - Stats.u);
		}
	}

	private int getTouchY() {
		return CSG.height - Gdx.input.getY();
	}

	private float getTouchX() {
		return(Gdx.input.getX() - HALF_WIDTH);
	}
	
	private float clicX = 0, clicY = 0, originalClicX = CSG.halfWidth, originalClicY = CSG.halfHeight, wantedMvtX, wantedMvtY, chronoDroit;

	public void mouvements() {
		xCenter = POS.x + HALF_WIDTH;
		yCenter = POS.y + HALF_HEIGHT;

		if (Gdx.input.justTouched()) {
			originalClicX = getTouchX();
			originalClicY = getTouchY();
		} else {
			clicX = getTouchX();
			clicY = getTouchY();

			wantedMvtX = (originalClicX - clicX);
			wantedMvtY = (originalClicY - clicY);

			POS.x -= wantedMvtX * CSG.profile.sensitivity;
			POS.y -= wantedMvtY * CSG.profile.sensitivity;

			if (wantedMvtX > 0) {
				chronoDroit = 0;
				AnimPlayer.toLeft();
			} else if (wantedMvtX < 0) {
				AnimPlayer.toRight();
				chronoDroit = 0;
			} else {
				chronoDroit += EndlessMode.delta;
				if (chronoDroit > 0.3f)
					AnimPlayer.straight();
			}
			originalClicX = getTouchX();
			originalClicY = getTouchY();
			routineAdds();
		}
		limitesEtCentre();
	}
	
	public void routineAdds() {
		addX = POS.x;
		if (addY > POS.y + HEIGHT_MAX_ADD) {
			addY = POS.y + HEIGHT_MAX_ADD;
			angleAdd = -80;
			angleAddDroite = -100; 
		} else if (addY < POS.y) {
			addY = POS.y;
			angleAdd = 80;
			angleAddDroite = 100;
		} else {		// L'angle a une amplitude de 160° qui varie à gauche entre -80 et 80 et à droite entre -100 et 100 car on tourne dans l'autre sens 
			angleAdd = addY - POS.y;
			angleAdd /= HEIGHT_MAX_ADD;
			angleAdd *= 160;
			angleAddDroite = -angleAdd - 100; // Lui tourne dans l'autre sens (d'où le -) et doit décaler de 110 et pas 80 (C'est logique avec le petit dessin devant soi :) ). Ca fait 5 lignes, une multiplication, une division et une soustraction en moins par rapport à l'original
			angleAdd -= 80;
			angleAdd = -angleAdd;	// Car il fait son flip autour de 0
			angleAddDroite = -angleAddDroite; // Bon il doit y avoir moyen de se passer de ses inversions !
		}
		centerAdd2Y = (addY - HEIGHT) + HALF_HEIGHT_ADD;
		centerRight2AddX = (addX + DECALAGE_ADD + HALF_WIDTH) + HALF_WIDTH_ADD;
		
		centerAdd2Y = (addY - HEIGHT) + HALF_HEIGHT_ADD;
		centerLeft2AddX = (addX - WIDTH) + HALF_WIDTH_ADD;
		
		centerRight1AddX = (addX + DECALAGE_ADD) + HALF_WIDTH_ADD;
		centerAdd1Y = (addY - HALF_HEIGHT) + HALF_HEIGHT_ADD;
		
		centerLeft1AddX = (addX - HALF_WIDTH) + HALF_WIDTH_ADD;
		centerAdd1Y = (addY - HALF_HEIGHT) + HALF_HEIGHT_ADD;
	}

	/**
	 * Le vaisseau va aux coordonn�es pass�es
	 * @param x
	 * @param y
	 */
	@SuppressWarnings("unused")
	private void mvtTeleport(int x, int y) {
		POS.x = x;
		POS.y = y;
	}
	
	/**
	 * oblige le vaisseau a rester dans les limites de l'�cran
	 */
	private void limitesEtCentre() {
		updateCenters();
		
		if (POS.x < LIMITE_X_GAUCHE) 		POS.x = LIMITE_X_GAUCHE;
		else if (POS.x > LIMITE_X_DROITE) 	POS.x = LIMITE_X_DROITE;
		if (POS.y < LIMITE_Y_GAUCHE) 		POS.y = LIMITE_Y_GAUCHE;
		else if (POS.y > LIMITE_Y_DROITE)	POS.y = LIMITE_Y_DROITE;
		
		if (EndlessMode.cam.position.x > CSG.screenWidth - CSG.halfWidth)
			EndlessMode.cam.position.x = CSG.screenWidth - CSG.halfWidth;
		if (EndlessMode.cam.position.x < CSG.halfWidth)
			EndlessMode.cam.position.x = CSG.halfWidth;
		
//		light.setPosition(xCenter, POS.y);
//		light.setDistance(1);
	}

	/**
	 * verifie si le vaisseau peut shoter ou pas. Tir au cas ou.
	 * Vu qu'on appele shot une frame sur deux adapter la cadence en cons�quence
	 * @param listeTir
	 */
	public void shot(){
		nextShot = weapon.init(nextShot);
		// ** ** A D D S
		if (EndlessMode.now > nextShotAdd) {
			shotTime += ArmeAdd.FIRERATETIR / 10f;
			if (leftDrone) {
				addShotNbr = addShotNbr | LEFT_ADD1;
				ArmeAdd.add(addX - HALF_WIDTH, addY - HALF_HEIGHT, angleAdd, 0);
				if (CSG.profile.dronesFirerate > 3)
					ArmeAdd.add(addX - HALF_WIDTH, addY - HALF_HEIGHT, angleAdd, 10);
			}
			if (rightDrone) {
				addShotNbr = addShotNbr | RIGHT_ADD1;
				ArmeAdd.add(addX + DECALAGE_ADD, addY - HALF_HEIGHT, angleAddDroite, 0);
				if (CSG.profile.dronesFirerate > 3)
					ArmeAdd.add(addX + DECALAGE_ADD, addY - HALF_HEIGHT, angleAddDroite, -10);
			}
			if (leftDrone2) {
				addShotNbr = addShotNbr | LEFT_ADD2;
				ArmeAdd.add(addX - WIDTH, addY - HEIGHT, angleAdd, 0);
				if (CSG.profile.dronesFirerate > 6)
					ArmeAdd.add(addX - WIDTH, addY - HEIGHT, angleAdd, 10);
			}
			if (rightDrone2) {
				addShotNbr = addShotNbr | RIGHT_ADD2;
				ArmeAdd.add(addX + DECALAGE_ADD + HALF_WIDTH, addY - HEIGHT, angleAddDroite, 0);
				if (CSG.profile.dronesFirerate > 6)
					ArmeAdd.add(addX + DECALAGE_ADD + HALF_WIDTH, addY - HEIGHT, angleAddDroite, -10);
			}
			nextShotAdd = EndlessMode.now + ArmeAdd.FIRERATETIR;
		}
	}
	
	public static void addDrone() {
		if (!Player.leftDrone)				leftDrone = true;
		else if (!Player.rightDrone)		rightDrone = true;
		else if (!Player.leftDrone2)		leftDrone2 = true;
		else if (!Player.rightDrone2)		rightDrone2 = true;
	}
	
	public static void changeWeapon(){
		weapon = WeaponManager.changerArme(weapon);
		CSG.profile.setArmeSelectionnee(weapon.getLabel());
	}

	public static void touched() {
		if (EndlessMode.konamiCode)
			return;
		if (shield == 0) {
			if (!shieldHS)
				EndlessMode.lost();
		} else {
			popOutShield();
			Weapon.shieldHs();
		}
	}

	public static void removeLeftAdd1() {
		Explosion.add(Particles.EXPLOSIONS, addX - HALF_WIDTH, addY - HALF_HEIGHT, 10, PrecalculatedParticles.RANDDOM_GREENS);
		leftDrone = false;
	}
	public static void enleverAddDroite1() {	
		Explosion.add(Particles.EXPLOSIONS, addX + DECALAGE_ADD, addY - HALF_HEIGHT, 10, PrecalculatedParticles.RANDDOM_GREENS);
		rightDrone = false;	
	}
	public static void removeLeftAdd2() {	
		Explosion.add(Particles.EXPLOSIONS, addX - WIDTH, addY - HEIGHT, 10, PrecalculatedParticles.RANDDOM_GREENS);
		leftDrone2 = false;	
	}
	public static void enleverAddDroite2() {	
		Explosion.add(Particles.EXPLOSIONS, addX + DECALAGE_ADD + HALF_WIDTH, addY - HEIGHT, 10, PrecalculatedParticles.RANDDOM_GREENS);
		rightDrone2 = false;
	}

	public static void activateShield() {
		bouclierParticules.init(HEIGHT);
		if (shield < 3) {
			shield++;
		}
		bouclierParticules.lvlChanged(shield);
	}

	public static void touchedEnnemy(Enemy enemy) {
		if (shield == 0) {
			if (!shieldHS)
				EndlessMode.lost();
		} else {
			popOutShield();
			enemy.stillAlive(Enemy.superBomb);
		}
	}
	
	public static void updateCenters() {
		xCenter = POS.x + HALF_WIDTH;
		yCenter = POS.y + HALF_HEIGHT;
	}
	
	private static void popOutShield() {
		EndlessMode.transition.activate(10, Transition.POP_OUT_SHIELD);
		shieldHS = true;
		tpsBouclierHs = 0;
		SoundMan.playBruitage(SoundMan.bigExplosion);
		shield--;
		bouclierParticules.lvlChanged(shield);
	}

}
