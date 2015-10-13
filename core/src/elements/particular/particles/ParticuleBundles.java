package elements.particular.particles;

import elements.particular.particles.individual.PrecalculatedParticles;

public enum ParticuleBundles {
	
	RED_LONG(PrecalculatedParticles.colorsOverTimeRedLong, PrecalculatedParticles.widthsColorOverTimeLong, PrecalculatedParticles.widthsColorOverTimeLongDifferences),
	YELLOW_TO_GREEN_LONG(PrecalculatedParticles.colorsOverTimeYellowToGreenLong, PrecalculatedParticles.widthsColorOverTimeLong, PrecalculatedParticles.widthsColorOverTimeLongDifferences),
	CYAN_TO_GREEN_LONG(PrecalculatedParticles.colorsOverTimeCyanToGreenLong, PrecalculatedParticles.widthsColorOverTimeLong, PrecalculatedParticles.widthsColorOverTimeLongDifferences),
	BLUE_LONG(PrecalculatedParticles.colorsOverTimeBlueLong, PrecalculatedParticles.widthsColorOverTimeLong, PrecalculatedParticles.widthsColorOverTimeLongDifferences),
	SMOKE(PrecalculatedParticles.colorsRed, PrecalculatedParticles.widths, PrecalculatedParticles.halfWidths);
	
	public float[] colors, widths, differences;

	ParticuleBundles(float[] colors, float[] widths, float[] differences) {
		this.colors = colors;
		this.widths = widths;
		this.differences = differences;
		check(colors, widths);
		check(colors, differences);
	}

	private void check(float[] color, float[] other) {
		if (other.length < color.length) {
			try {
				throw new Exception("BAD SIZE " + other.length + " < " + color.length);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
