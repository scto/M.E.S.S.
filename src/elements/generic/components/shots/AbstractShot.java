package elements.generic.components.shots;

import jeu.CSG;
import jeu.Physic;

import com.badlogic.gdx.math.Vector2;

import elements.generic.enemies.Enemy;
import elements.generic.weapons.enemies.EnemyWeapon;

public abstract class AbstractShot {
	
	private static float tmpFloat;
	
	public static int interval(Enemy e, int numberOfMaxShot, float nextShotIncrement, int shotNumber) {
		if (++shotNumber > numberOfMaxShot) {
			e.nextShot += nextShotIncrement;
			return 0;
		}
		return shotNumber;
	}
		
	public static void shootDown(Gatling gatling, Vector2 shotPosition, float speedMod) {
		gatling.canon.invoke().init(shotPosition, speedMod, false);
	}
	
	public static void shootDownRandom(Gatling gatling, Vector2 shotPosition, float speedMod, float rnd) {
		CSG.vecteurCible.x = 0;
		CSG.vecteurCible.y = -1;
		CSG.vecteurCible.rotate((CSG.R.nextFloat() - 0.5f) * rnd);
		gatling.canon.invoke().init(shotPosition, speedMod, CSG.vecteurCible, false);
	}
	
	public static void shootOnPlayer(Gatling gatling, Vector2 shotPosition, float offset, float speedMod) {
		final EnemyWeapon a = gatling.canon.invoke();
		CSG.vecteurCible.x = 1;
		CSG.vecteurCible.y = 0;
		CSG.vecteurCible.rotate(Physic.getAngleWithPlayer(shotPosition, a.getDimensions().width / 2, a.getDimensions().height / 2));
		shotPosition.x += CSG.vecteurCible.x * offset;
		shotPosition.y += CSG.vecteurCible.y * offset;
		a.init(shotPosition, speedMod, CSG.vecteurCible, false);
	}
	
	public static void shootOnPlayer(Gatling gatling, Vector2 shotPosition, float offset, float speedMod, int bullets, float dispersion) {
		final EnemyWeapon a = gatling.canon.invoke();
		tmpFloat = Physic.getAngleWithPlayer(shotPosition, a.getDimensions().width / 2, a.getDimensions().height / 2);
		a.free();
		// 0 - 2 => 	i-1
		// 0 - 4 =>		i-2		
		for (int i = 0; i < bullets; i++) {
			final EnemyWeapon b = gatling.canon.invoke();
			CSG.vecteurCible.x = 1;
			CSG.vecteurCible.y = 0;
			CSG.vecteurCible.rotate(tmpFloat - ((i - bullets/2) * dispersion));
			shotPosition.x += CSG.vecteurCible.x * offset;
			shotPosition.y += CSG.vecteurCible.y * offset;
			b.init(shotPosition, speedMod, CSG.vecteurCible, false);
		}
	}
	
	public static void leftRight(Gatling gatling, Vector2 shotPosition, Vector2 shotDirection, float speedMod) {
		CSG.vecteurCible = shotDirection;
		gatling.canon.invoke().init(shotPosition, speedMod, CSG.vecteurCible, false);
		CSG.vecteurCible.x = -CSG.vecteurCible.x;
		CSG.vecteurCible.y = -CSG.vecteurCible.y; 
		gatling.canon.invoke().init(shotPosition, speedMod, CSG.vecteurCible, false);
	}
	
	public static void straight(Gatling gatling, Vector2 shotPosition, Vector2 shotDirection, float speedMod) {
		gatling.canon.invoke().init(shotPosition, speedMod, shotDirection, false);
	}
	
	public static void rafale(Enemy t) {
		if (t.getShotNumber() >= t.getNumberOfShots()) {
			t.addShots(
					//-t.getShotNumber()
					1
					);
			t.setNextShot(t.getNow() + (t.getFirerate() * t.getNumberOfShots()));
		} else {
			t.setNextShot(t.getNow() + t.getFirerate());
		}
		t.addShots(1);
	}

	public static void shotgun(Gatling gatling, Vector2 shotDirection, Vector2 shotPosition, int min, int rnd, float dirRnd, float dirScaleRnd, float offset) {
		CSG.tmpInt = min + CSG.R.nextInt(rnd);
		for (int i = 0; i < CSG.tmpInt; i++) {
			EnemyWeapon b = gatling.canon.invoke();
			
			CSG.tmpDir.x = shotDirection.x;
			CSG.tmpDir.y = shotDirection.y;
			CSG.tmpDir.scl(1 + ((CSG.R.nextFloat() - 0.5f) * dirScaleRnd) );
			CSG.tmpDir.rotate((CSG.R.nextFloat() - 0.5f) * dirRnd);
			
			CSG.tmpPos.x = shotPosition.x;
			CSG.tmpPos.y = shotPosition.y;
			CSG.tmp2.x = 0;
			CSG.tmp2.y = -offset;
			CSG.tmp2.rotate(CSG.tmpDir.angle() + 90);
			CSG.tmpPos.add(CSG.tmp2);
			b.init(CSG.tmpPos, CSG.tmpDir);
		}
	}

	public static void doubleShotDown(Gatling gatling, Vector2 pos1, Vector2 pos2, float bulletSpeed) {
		gatling.canon.invoke().init(pos1, bulletSpeed, false);
		gatling.canon.invoke().init(pos2, bulletSpeed, false);
	}

	public static boolean sweep(Gatling gatling, Vector2 shotDirection, Vector2 shotPosition, float bulletSpeed, Enemy t, boolean shootDir, int numberOfShotBeforeDirChange, float shootingSpreadAngle, float shotGap, int shotNumber) {
		CSG.vecteurCible.y = shotDirection.y;
		CSG.vecteurCible.x = shotDirection.x;
		CSG.vecteurCible.rotate(shootingSpreadAngle + (shotGap * shotNumber));
		System.out.println(shotNumber);
		gatling.canon.invoke().init(shotPosition, bulletSpeed, CSG.vecteurCible, false);
		if (shootDir) {
			t.addShots(1);
			if (shotNumber > numberOfShotBeforeDirChange)
				return false;
		} else {
			t.addShots(-1);
			if (shotNumber < -numberOfShotBeforeDirChange)
				return true;
		}

		return shootDir;
	}


}
