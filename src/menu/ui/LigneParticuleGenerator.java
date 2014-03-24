package menu.ui;

import com.badlogic.gdx.Gdx;

import elements.particular.particles.Particles;

public class LigneParticuleGenerator {

	private boolean effetVersDroite = true;
	private float x;
	private float min, max;
	private final boolean horizontal;
	
	public LigneParticuleGenerator(int x, int min, int max, boolean horizontal) {
		if (x > Gdx.graphics.getWidth()) {
			this.x = x % Gdx.graphics.getWidth();
			effetVersDroite = false;
		}
		this.x = x;
		this.min = min;
		this.max = max;
		this.horizontal = horizontal;
	}

	public void effet(float y) {
		if (horizontal)
			ligneHorizontale(y);
	}

	private void ligneHorizontale(float y) {
		if (effetVersDroite)
			x = ( x + (Gdx.graphics.getDeltaTime() * 150) );
		else
			x = ( x - (Gdx.graphics.getDeltaTime() * 150) );
		if (x > max)
			effetVersDroite = false;
		if (x < min)
			effetVersDroite = true;
		Particles.ajoutUiElement(x, y, false);
	}

	public void majDimensions(int widthScreen) {
		this.max = widthScreen;
	} 
}
