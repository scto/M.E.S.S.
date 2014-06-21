package elements.particular.particles;

import jeu.CSG;
import assets.AssetMan;

public enum Painters {

	RED(new ColorGenerator() {
		public float getColor() {
			return AssetMan.convertARGB(1, 1f, (CSG.R.nextFloat() / 2) + 0.49f, CSG.R.nextFloat() / 8);
		}
	}), BLUE(new ColorGenerator() {
		public float getColor() {
			return AssetMan.convertARGB(1, CSG.R.nextFloat() / 8, (CSG.R.nextFloat() / 2) + 0.49f, .85f);
		}
	}), GREEN(new ColorGenerator() {
		public float getColor() {
			return AssetMan.convertARGB(1,		(CSG.R.nextFloat() / 2) + 0.49f, 	1, 		CSG.R.nextFloat()/8);
		}
	}), GREEN_CYAN(new ColorGenerator() {
		public float getColor() {
			if (CSG.R.nextBoolean())
				// 0 - 0.125		1		0.5 - 1
				return AssetMan.convertARGB(1, 	CSG.R.nextFloat()/8, 				1, 		(CSG.R.nextFloat() / 2) + 0.49f);
				// 0.5 - 1			1		0 - 0.125
			return AssetMan.convertARGB(1,		(CSG.R.nextFloat() / 2) + 0.49f, 	1, 		CSG.R.nextFloat()/8);
		}
	});

	public ColorGenerator colorGenerator;

	private Painters(ColorGenerator colorGenerator) {
		this.colorGenerator = colorGenerator;
	}

}
