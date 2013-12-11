package menu.ui;

import java.util.ArrayList;

import jeu.EndlessMode;
import assets.particules.Particules;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class UI {
	public static final float BOTTOM_BAR = 10, TOP_BAR = 10;
	public static final float PADDING = 5;
	// pourcentage de la largeur
	public static final int PORTION_VIGNETTE = 10;
	public static int facteurParticules = 10;
	private ArrayList<UiBean> elements = new ArrayList<UiBean>();

	public void add(UiBean element) {
		elements.add(element);
	}

	public void draw(SpriteBatch batch) {
		EndlessMode.delta = Gdx.graphics.getDeltaTime();
		EndlessMode.delta15 = EndlessMode.delta * 15;
		Particules.drawUi(batch);
		for (UiBean e : elements)
			e.draw(batch);
	}

	public void majDimensions(int width, int height) {
		for (UiBean bean : elements) {
			bean.updateDimensions(width, height);
		}
	}

	public void numberPressed(int i) {
		for (UiBean bean : elements)
			bean.numberPressed(i);
	}
}
