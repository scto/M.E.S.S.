package elements.particular.particles.individual;

import assets.AssetMan;

import com.badlogic.gdx.utils.Array;

import elements.generic.weapons.player.Fireball;
import jeu.CSG;
import jeu.Stats;

public class PrecalculatedParticles {

	public static final float INITIAL_WIDTH = ((float)Stats.LARGEUR_DE_BASE / 4), INITIAL_HALF_WIDTH = INITIAL_WIDTH / 2;
	private static final float stepSparkles = 0.075f;
	public static final float[] colorsRed = initAlphasRed(stepSparkles, 0), colorsBlue = initAlphasBlue(stepSparkles, 0), colorsGreen = initAlphasGreen(stepSparkles, 0);
	public static final float[] widths = initWidths(Stats.uSur4, colorsRed.length);
	public final static float[] widthsFireballParticules = initWidths(Stats.u, 0.75f, Fireball.WIDTH);
	public final static float[] halfWidthsFireballParticules = CSG.getHalf(widthsFireballParticules);
	public final static float[] colorsFireball = initColors(widthsFireballParticules.length, 1, 0.9f, 0);
	public final static float[] colorsPinkWeapon = initColors(7, 4, 1, 1);
	public static final float[] halfWidths = CSG.getHalf(widths);
	public static final float[] dirY = initDirY(widths);
	
	
	private static final float stepColorsOverTime = 0.065f;
	public static final float[] colorsOverTimeRed = initAlphasRed(stepColorsOverTime, 0.15f), colorsOverTimeBlue = initAlphasBlue(stepColorsOverTime, 0.15f), colorsOverTimeGreen = initAlphasGreen(stepColorsOverTime, 0.15f),
			colorsOverTimeBlueLong = initAlphasBlue(stepColorsOverTime / 2, 0.1f), colorsOverTimeGreenLong = initAlphasGreen(stepColorsOverTime / 2, 0.10f);
	public static final float[] widthsColorOverTime = initWidths((1 / colorsOverTimeRed.length) / 2, colorsOverTimeRed.length), widthsColorOVerTime2 = CSG.getDouble(widthsColorOverTime);
	public static final float[] halfWidthsColorOverTime = CSG.getHalf(widthsColorOverTime);
	
	private static float[] initWidths(float max, float mult, float initial) {
		Array<Float> tmp = new Array<Float>();
		float w = initial;
		while (w >= max) {
			tmp.add(w);
			w *= mult;
		}
		return CSG.convert(tmp);
	}
	
	private static float[] initAlphasPink(float stepcolorsovertime2, float f) {
		return null;
	}

	private static float[] initWidths(float step, int max) {
		float f = INITIAL_WIDTH;
		Array<Float> tmp = new Array<Float>();
		for (int i = 0; i < max; i++) {
			tmp.add(f);
			f += step;
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
	
	private static float[] initColors(int nbFrames, int div, float g, float b) {
		int cpt = 0;
		final float step = 0.95f / nbFrames;
		Array<Float> tmp = new Array<Float>();
		while (cpt < nbFrames) {
			tmp.add(AssetMan.convertARGB(1, 1, g / div, b));
			g -= step;
			cpt++;
		}
		return CSG.convert(tmp);
	}
	
	private static float[] initAlphasGreen(float step, float min) {
		float alpha = 1;
		Array<Float> tmp = new Array<Float>();
		while (alpha > min) {
			tmp.add(AssetMan.convertARGB(alpha, 0.05f, 1, alpha));
			alpha -= step;
		}
		return CSG.convert(tmp);
	}
	
	private static float[] initAlphasBlue(float step, float min) {
		float alpha = 1;
		Array<Float> tmp = new Array<Float>();
		while (alpha > min) {
			tmp.add(AssetMan.convertARGB(alpha, 0.05f, alpha, 1));
			alpha -= step;
		}
		return CSG.convert(tmp);
	}

	private static float[] initAlphasRed(float step, float min) {
		float alpha = 1;
		Array<Float> tmp = new Array<Float>();
		while (alpha > min) {
			tmp.add(AssetMan.convertARGB(alpha, 1, alpha, 0.05f));
			alpha -= step;
		}
		return CSG.convert(tmp);
	}
	
	
}
