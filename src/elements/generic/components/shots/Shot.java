package elements.generic.components.shots;

import jeu.CSG;
import jeu.Physic;
import elements.generic.components.PhaseUser;
import elements.generic.weapons.enemies.EnemyWeapon;

public enum Shot {

	DOUBLE_SHOT_DOWN(new ShotImplementation() {
		public void shoot(PhaseUser t) {
			t.getPhase().bullet.canon.invoke().init(t.getShotPosition(1), t.getBulletSpeedMod(), false);
			t.getPhase().bullet.canon.invoke().init(t.getShotPosition(2), t.getBulletSpeedMod(), false);
		}
	}), DOUBLE_SHOT_DOWN_RAND(new ShotImplementation() {
		public void shoot(PhaseUser t) {
			CSG.tmpDir.x = 0;
			CSG.tmpDir.y = -1;
			t.getPhase().bullet.canon.invoke().init(t.getShotPosition(1), t.getBulletSpeedMod(), CSG.tmpDir.rotate((CSG.R.nextFloat() - 0.5f) * t.getFloatFactor()), false);
			CSG.tmpDir.x = 0;
			CSG.tmpDir.y = -1;
			t.getPhase().bullet.canon.invoke().init(t.getShotPosition(2), t.getBulletSpeedMod(), CSG.tmpDir.rotate((CSG.R.nextFloat() - 0.5f) * t.getFloatFactor()), false);
		}
	}), DOUBLE_SHOT_DOWN_RAND_RAFALE(new ShotImplementation() {
		public void shoot(PhaseUser t) {
			CSG.tmpDir.x = 0;
			CSG.tmpDir.y = -1;
			t.getPhase().bullet.canon.invoke().init(t.getShotPosition(1), t.getBulletSpeedMod(), CSG.tmpDir.rotate((CSG.R.nextFloat() - 0.5f) * t.getFloatFactor()), false);
			CSG.tmpDir.x = 0;
			CSG.tmpDir.y = -1;
			t.getPhase().bullet.canon.invoke().init(t.getShotPosition(2), t.getBulletSpeedMod(), CSG.tmpDir.rotate((CSG.R.nextFloat() - 0.5f) * t.getFloatFactor()), false);
			Shot.rafale(t);
		}
	}), SHOT_DOWN(new ShotImplementation() {
		public void shoot(PhaseUser u) {
			u.getPhase().bullet.canon.invoke().init(u.getShotPosition(1), u.getBulletSpeedMod(), false);
		}
	}), SHOT_DOWN_MULTIPLE(new ShotImplementation() {
		public void shoot(PhaseUser u) {
			Shot.multiple(u);
		}
	}), SHOT_DOWN_MULTIPLE_WITH_BREAK(new ShotImplementation() {
		public void shoot(PhaseUser t) {
			if (Shot.multiple(t))
				t.setNextShot(t.getPhaseTime() + t.getFirerate() * t.getNumberOfShots());
		}
	}), SHOT_DOWN_RAND_RAFALE(new ShotImplementation() {
		public void shoot(PhaseUser t) {
			CSG.tmpDir.x = 0;
			CSG.tmpDir.y = -1;
			t.getPhase().bullet.canon.invoke().init(t.getShotPosition(1), t.getBulletSpeedMod(), CSG.tmpDir.rotate((CSG.R.nextFloat() - 0.5f) * t.getFloatFactor()), false);
			Shot.rafale(t);
		}
	}), SHOT_EN_RAFALE(new ShotImplementation() {
		public void shoot(PhaseUser t) {
			t.getPhase().bullet.canon.invoke().init(t.getShotPosition(1), t.getBulletSpeedMod(), t.getShootingDir(), false);
			Shot.rafale(t);
		}
	}), SHOT_EN_RAFALE_LEFT_RIGHT(new ShotImplementation() {
		public void shoot(PhaseUser t) {
			CSG.vecteurCible = t.getShootingDir();
			t.getPhase().bullet.canon.invoke().init(t.getShotPosition(1), t.getBulletSpeedMod(), CSG.vecteurCible, false);
			CSG.vecteurCible.x = -CSG.vecteurCible.x;
			CSG.vecteurCible.y = -CSG.vecteurCible.y; 
			t.getPhase().bullet.canon.invoke().init(t.getShotPosition(1), t.getBulletSpeedMod(), CSG.vecteurCible, false);
			Shot.rafale(t);
		}
	}), SHOT_EVENTAIL(new ShotImplementation() {
		public void shoot(PhaseUser t) {
			final float shotGap = t.getShotTotalAngle() / t.getNumberOfShots();
			for (int i = -(t.getNumberOfShots() / 2); i <= t.getNumberOfShots() / 2; i++) {
				t.getPhase().bullet.canon.invoke().init(t.getShotPosition(1), t.getBulletSpeedMod(), t.getShootingDir().rotate(i * shotGap), t.isBoss());
			}
		}
	}), SHOT_SWEEP_WITH_BREAK(new ShotImplementation() {
		public void shoot(PhaseUser t) {
			if (Shot.eventail(t))
				t.setNextShot(t.getPhaseTime() + t.getFirerate() * 4);
		}
	}), SHOTGUN(new ShotImplementation() {
		public void shoot(PhaseUser u) {
			CSG.tmpInt = u.getNumberOfShots() + CSG.R.nextInt(u.getIntFactor());
			for (int i = 0; i < CSG.tmpInt; i++) {
				EnemyWeapon b = u.getPhase().bullet.canon.invoke();
				CSG.tmpDir.x = 0;//(float) (CSG.R.nextGaussian() * (Stats.UU * u.getDispersionFactor()));
				CSG.tmpDir.y = -u.getSpeed() + ((CSG.R.nextFloat() - 0.5f) * u.getFloatFactor());//((float) -Math.abs(CSG.R.nextGaussian() * Stats.UUU)) - Stats.UUU;
				CSG.tmpDir.rotate(u.getShootingAngle() + ((CSG.R.nextFloat() - 0.5f) * u.getFloatFactor()));
				CSG.vecteurCible = u.getShotPosition(0);
				CSG.tmp2.x = 0;
				CSG.tmp2.y = -u.getHalfHeight();
				CSG.tmp2.rotate(CSG.tmpDir.angle() + 90);
				CSG.vecteurCible.add(CSG.tmp2);
				b.init(CSG.vecteurCible, CSG.tmpDir);
			}
		}
	}), SHOT_VERS_JOUEUR(new ShotImplementation() {
		public void shoot(PhaseUser t) {
			final EnemyWeapon a = t.getPhase().bullet.canon.invoke();
			CSG.vecteurPosition.x = t.getShotPosition(0).x;
			CSG.vecteurPosition.y = t.getShotPosition(0).y;
			CSG.vecteurCible.x = 1;
			CSG.vecteurCible.y = 0;
			CSG.vecteurCible.rotate(Physic.getAngleWithPlayer(CSG.vecteurPosition, a.getWidth() / 2, a.getHeight() / 2));
			a.init(CSG.vecteurPosition, t.getBulletSpeedMod(), CSG.vecteurCible, false);
		}
	}), SHOT_VERS_JOUEUR_RAND(new ShotImplementation() {
		public void shoot(PhaseUser t) {
			final EnemyWeapon a = t.getPhase().bullet.canon.invoke();
			CSG.vecteurPosition.x = t.getShotPosition(0).x;
			CSG.vecteurPosition.y = t.getShotPosition(0).y;
			CSG.vecteurCible.x = 1;
			CSG.vecteurCible.y = 0;
			CSG.vecteurCible.rotate(Physic.getAngleWithPlayer(CSG.vecteurPosition, a.getWidth() / 2, a.getHeight() / 2));
			for (int i = 0; i < t.getNumberOfShots(); i++) {
				CSG.tmpDir.x = CSG.vecteurCible.x;
				CSG.tmpDir.y = CSG.vecteurCible.y;
				CSG.tmpDir.rotate((float) (CSG.R.nextGaussian() * t.getFloatFactor()));
				t.getPhase().bullet.canon.invoke().init(CSG.vecteurPosition, t.getBulletSpeedMod(), CSG.tmpDir, false);
			}
		}
	}), SWEEP(new ShotImplementation() {
		public void shoot(PhaseUser t) {
			if (t.getNumberOfShotBeforeDirChange() < t.getShotNumber()) {
				t.setShotDir(false);
			} else if (-t.getNumberOfShotBeforeDirChange() > t.getShotNumber()) {
				t.setShotDir(true);
			}
			CSG.vecteurCible.y = t.getShootingDir().y;
			CSG.vecteurCible.x = t.getShootingDir().x;
			CSG.vecteurCible.rotate(t.getShootingAngle() + (t.getShotsGap() * t.getShotNumber()));

			if (t.getShotDir())
				t.addShots(1);
			else
				t.addShots(-1);

			t.getPhase().bullet.canon.invoke().init(t.getShotPosition(1), t.getBulletSpeedMod(), CSG.vecteurCible, false);
		}
	}), TIR_SUR_COTES(new ShotImplementation() {
		public void shoot(PhaseUser t) {
			t.getPhase().bullet.canon.invoke().init(t.getShotPosition(2), t.getShootingDir().rotate(-90), t.getBulletSpeedMod());
			t.getPhase().bullet.canon.invoke().init(t.getShotPosition(2), t.getShootingDir().rotate(90), t.getBulletSpeedMod());
		}
	}), TIR_TOUT_DROIT(new ShotImplementation() {
		public void shoot(PhaseUser t) {
			t.getPhase().bullet.canon.invoke().init(t.getShotPosition(1), t.getBulletSpeedMod(), t.getShootingDir(), false);
		}
	}), V(new ShotImplementation() {
		public void shoot(PhaseUser t) {
			t.getPhase().bullet.canon.invoke().init(t.getShotPosition(2), t.getShootingDir().rotate(-5), t.getBulletSpeedMod());
			t.getPhase().bullet.canon.invoke().init(t.getShotPosition(2), t.getShootingDir().rotate(5), t.getBulletSpeedMod());
		}
	}), TIR_TOUT_DROIT_DOUBLE_RAND(new ShotImplementation() {
		public void shoot(PhaseUser t) {
			t.getPhase().bullet.canon.invoke().init(t.getShotPosition(1), t.getBulletSpeedMod(), t.getShootingDir().rotate((float) (CSG.R.nextGaussian() * t.getFloatFactor())), false);
			t.getPhase().bullet.canon.invoke().init(t.getShotPosition(1), t.getBulletSpeedMod(), t.getShootingDir().rotate((float) (CSG.R.nextGaussian() * t.getFloatFactor())), false);
		}
	}), TIRS_VERS_BAS_RAND(new ShotImplementation() {
		public void shoot(PhaseUser t) {
			t.getPhase().bullet.canon.invoke().init(t.getShotPosition(1), t.getBulletSpeedMod(), t.getShootingDir().rotate((float) (CSG.R.nextGaussian() * t.getFloatFactor())), false);
			t.getPhase().bullet.canon.invoke().init(t.getShotPosition(1), t.getBulletSpeedMod(), t.getShootingDir().rotate((float) (CSG.R.nextGaussian() * t.getFloatFactor())), false);
		}
	}), NOTHING(new ShotImplementation() {
		public void shoot(PhaseUser t) {
		}
	});

	public ShotImplementation implementation;

	private Shot(ShotImplementation shot) {
		this.implementation = shot;
	}

	protected static void rafale(PhaseUser t) {
		if (t.getShotNumber() >= t.getNumberOfShots()) {
			t.addShots(-t.getShotNumber());
			t.setNextShot(t.getPhaseTime() + (t.getFirerate() * t.getNumberOfShots()));
		} else {
			t.setNextShot(t.getPhaseTime() + t.getFirerate());
		}
		t.addShots(1);
	}
	protected static boolean multiple(PhaseUser u) {
		for (int i = 0; i < u.getNumberOfShots(); i++)
			u.getPhase().bullet.canon.invoke().init(u.getShotPosition(i), u.getBulletSpeedMod(), false);
		u.addShots(1);
		if (u.getNumberOfShots() < u.getShotNumber()) {
			u.addShots(-u.getNumberOfShots());
			return true;
		}
		return false;
	}
	
	protected static boolean eventail(PhaseUser t) {
		for (int i = -(t.getNumberOfShots() / 2); i <= t.getNumberOfShots() / 2; i++) {
			t.getPhase().bullet.canon.invoke().init(t.getShotPosition(1), t.getBulletSpeedMod(), t.getShootingDir().rotate(t.getShootingAngle() + (i * t.getFloatFactor())), t.isBoss());
		}
		if (t.getShotWay())
			t.setShootingAngle(t.getShootingAngle() + t.getShotsGap());
		else
			t.setShootingAngle(t.getShootingAngle() - t.getShotsGap());
		
		if (t.getShootingAngle() > t.getMaxShotAngle()) {
			t.setShotWay(false);
			return true;
		}
		if (t.getShootingAngle() < -t.getMaxShotAngle()) {
			t.setShotWay(true);
			return true;
		}
		return false;
	}

	/**
	 * Semblable au shot en eventail mais laisse un angle plus important au
	 * centre et ne tient compte que de l'angle pour le shot
	 * 
	 * @param ombrelle
	 * @param i
	 * @param j
	 * @param mort
	 * @param maintenant
	 * @param nextShot
	 * @param k
	 */
	// public void shotOmbrelle(TireurAngle t, int nbTirs, float dispersion,
	// float maintenant, float nextShot, float ecartCentre) {
	// if (maintenant > nextShot) {
	// vecteurCible.x = 0;
	// vecteurCible.y = -1;
	// vecteurCible.rotate(t.getShootingAngle());
	// final int numeroTrou = nbTirs/2;
	// float angleDepart = t.getShootingAngle() - (( (dispersion * (nbTirs-1)) +
	// ecartCentre * 2) / 2f);
	// for (int i = 0; i < nbTirs; i++) {
	// if (i == numeroTrou) {
	// angleDepart += ecartCentre;
	// Fireball.POOL.obtain().init(t.getShotPosition(6), t.getBulletSpeedMod(),
	// angleDepart + (i * dispersion));
	// } else {
	// if (i == numeroTrou+1)
	// angleDepart += ecartCentre;
	// t.getWeapon().init(t.getShotPosition(1), t.getBulletSpeedMod(),
	// angleDepart + (i * dispersion));
	// }
	// }
	// t.setNextShot(maintenant + firerate);
	// }
	// }

}
