package elements.particular.particles.individual;

import assets.AssetMan;

import com.badlogic.gdx.utils.Array;

import jeu.CSG;
import jeu.Stats;

public class PrecalculatedParticlesLong {

	public static final float INITIAL_WIDTH = ((float)Stats.LARGEUR_DE_BASE / 4), INITIAL_HALF_WIDTH = INITIAL_WIDTH / 2;
	public static final float[] colorsRed = initAlphasRed(), colorsBlue = initAlphasBlue(), colorsGreen = initAlphasGreen();
	public static final float[] widths = initWidths(), widths2 = CSG.getDouble(widths);
	public static final float[] halfWidths = CSG.getHalf(widths);
	private static final float step = 0.010f, min = 0.15f;
	
	private static float[] initWidths() {
		float f = INITIAL_WIDTH;
		final float step = (1 / colorsRed.length) / 2;
		Array<Float> tmp = new Array<Float>();
		for (int i = 0; i < colorsRed.length; i++) {
			tmp.add(f);
			f -= step;
		}
		return CSG.convert(tmp);
	}

	private static float[] initAlphasGreen() {
		float alpha = 1;
		Array<Float> tmp = new Array<Float>();
		while (alpha > min) {
			tmp.add(AssetMan.convertARGB(alpha, 0.05f, 1, alpha));
			alpha -= step;
		}
		return CSG.convert(tmp);
	}
	
	private static float[] initAlphasBlue() {
		float alpha = 1;
		Array<Float> tmp = new Array<Float>();
		while (alpha > min) {
			tmp.add(AssetMan.convertARGB(alpha, 0.05f, alpha, 1));
			alpha -= step;
		}
		return CSG.convert(tmp);
	}

	private static float[] initAlphasRed() {
		float alpha = 1;
		Array<Float> tmp = new Array<Float>();
		while (alpha > min) {
			tmp.add(AssetMan.convertARGB(alpha, 1, alpha, 0.05f));
			alpha -= step;
		}
		return CSG.convert(tmp);
	}
}
