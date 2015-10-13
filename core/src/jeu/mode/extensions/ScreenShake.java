package jeu.mode.extensions;

import java.util.Random;

import jeu.CSG;
import jeu.Stats;
import jeu.mode.EndlessMode;

public class ScreenShake {

	// screenshake
	private static boolean shake = false, bloomSet = false;
	private static float chronoShake = 0, shakeTotalMvtX, shakeTotalMvtY;
	public static final Random R = new Random();
	private static float tmp;
	private static final float MAX_BLOOM = 5;

	public static void init() {
		shake = false;
		chronoShake = 0;
	}

	private static final float SHAKE_MIN = 1.1f, SHAKE_MAX = 4;

	public static void screenShake(float valeur) {
		if (shake == false) {
			shakeTotalMvtX = 0;
			shakeTotalMvtY = 0;
		}
		shake = true;
		bloomSet = false;
		chronoShake += valeur / 50f;
		if (chronoShake < SHAKE_MIN)
			chronoShake += SHAKE_MIN;
		else if (chronoShake > SHAKE_MAX)
			chronoShake = SHAKE_MAX;
	}

	public static void act() {
		if (shake) {
			if (chronoShake <= SHAKE_MIN) {
				shake = false;
				EndlessMode.cam.position.x = CSG.halfWidth;
				EndlessMode.cam.position.y = CSG.halfHeight;
			} else {
				chronoShake /= 1.01f + EndlessMode.delta2;
				if (CSG.profile.screenshake == false)
					return;
				
				if (shakeTotalMvtX < 0)		camMvtX((R.nextFloat() / 2) * chronoShake * Stats.U);
				else						camMvtX(-(R.nextFloat() / 2) * chronoShake * Stats.U);
				
				if (shakeTotalMvtY < 0)		camMvtY((R.nextFloat() / 2) * chronoShake * Stats.U);
				else						camMvtY(-(R.nextFloat() / 2) * chronoShake * Stats.U);
			}
		}
	}
	
	public static void camMvtX(float x) {
		EndlessMode.cam.position.x += x;
		shakeTotalMvtX += x;
	}
	
	public static void camMvtY(float y) {
		EndlessMode.cam.position.y += y;
		shakeTotalMvtY += y;
	}

	public static void bloomEffect() {
		if (!shake) {
			if (!bloomSet) {
				EndlessMode.bloom.setBloomIntesity(CSG.profile.bloomIntensity);
				bloomSet = true;
			}
		} else {
			tmp = CSG.profile.bloomIntensity + (chronoShake);
			if (tmp > MAX_BLOOM)
				tmp = MAX_BLOOM;
			EndlessMode.bloom.setBloomIntesity(tmp);
		}
	}

}
