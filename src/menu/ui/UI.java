package menu.ui;

import java.util.ArrayList;

import jeu.EndlessMode;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import elements.particular.particles.Particles;

public class UI {
	public static final float BOTTOM_BAR = 10, TOP_BAR = 10;
	public static final float PADDING = 5;
	// pourcentage de la largeur
	public static final int PORTION_VIGNETTE = 10;
	public static int facteurParticules = 10;
	private ArrayList<UiComponent> elements = new ArrayList<UiComponent>();

	public void add(UiComponent element) {
		elements.add(element);
	}

	public void draw(SpriteBatch batch) {
		EndlessMode.delta = Gdx.graphics.getDeltaTime();
		EndlessMode.delta15 = EndlessMode.delta * 15;
		Particles.drawUi(batch);
		for (UiComponent e : elements)
			e.draw(batch);
	}

	public void majDimensions(int width, int height) {
		for (UiComponent bean : elements) {
			bean.updateDimensions(width, height);
		}
	}

	public void numberPressed(int i) {
		for (UiComponent bean : elements)
			bean.numberPressed(i);
	}
}
