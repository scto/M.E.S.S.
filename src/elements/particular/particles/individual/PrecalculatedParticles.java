package elements.particular.particles.individual;

import assets.AssetMan;

import com.badlogic.gdx.utils.Array;

import jeu.CSG;
import jeu.Stats;

public class PrecalculatedParticles {

	public static final float INITIAL_WIDTH = ((float)Stats.LARGEUR_DE_BASE / 4), INITIAL_HALF_WIDTH = INITIAL_WIDTH / 2;
	public static final float[] colorsRed = initAlphasRed(), colorsBlue = initAlphasBlue(), colorsGreen = initAlphasGreen();
	public static final float[] widths = initWidths();
	public static final float[] halfWidths = CSG.getHalf(widths);
	public static final float[] dirY = initDirY(widths);
	
	private static float[] initWidths() {
		float f = INITIAL_WIDTH;
		Array<Float> tmp = new Array<Float>();
		for (int i = 0; i < colorsRed.length; i++) {
			tmp.add(f);
			f += Stats.uSur4;
		}
		return CSG.convert(tmp);
	}

	private static float[] initDirY(float[] widths2) {
		float alpha = CSG.SCREEN_HEIGHT / 50;
		Array<Float> tmp = new Array<Float>();
		while (alpha > 0) {
			tmp.add(alpha);
			alpha -= 0.075f;
		}
		return CSG.convert(tmp);
	}

	
	private static float[] initAlphasGreen() {
		float alpha = 1;
		Array<Float> tmp = new Array<Float>();
		while (alpha > 0) {
			tmp.add(AssetMan.convertARGB(alpha, 0.05f, 1, alpha));
			alpha -= 0.075f;
		}
		return CSG.convert(tmp);
	}
	
	private static float[] initAlphasBlue() {
		float alpha = 1;
		Array<Float> tmp = new Array<Float>();
		while (alpha > 0) {
			tmp.add(AssetMan.convertARGB(alpha, 0.05f, alpha, 1));
			alpha -= 0.075f;
		}
		return CSG.convert(tmp);
	}

	private static float[] initAlphasRed() {
		float alpha = 1;
		Array<Float> tmp = new Array<Float>();
		while (alpha > 0) {
			tmp.add(AssetMan.convertARGB(alpha, 1, alpha, 0.05f));
			alpha -= 0.075f;
		}
		return CSG.convert(tmp);
	}
}
