package menu.ui;

import java.util.Random;

import jeu.CSG;
import jeu.Physique;
import menu.OnClick;
import assets.particules.Particules;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class UiBean {

	private float x, y, width, height;
	private String text;
	private static final Vector2 tmpPos = new Vector2();
	private int cptPosition = 0;
	private Random rand = new Random();
	private float yText = y;
	private OnClick click = null;
	private boolean selected = false;
	private int number = 0;
	private int alreadyEnteredNumber = 0;
	private long inactive = 0;
	private boolean numericButton = false;


	public void draw(SpriteBatch batch) {
		CSG.menuFont.draw(batch, text, x + UI.PADDING, yText);
		for (int i = 0; i < UI.facteurParticules; i++) {
			initVectorsParticule();
			Particules.ajoutUiElement(tmpPos.x, tmpPos.y, selected);
		}
	}

	public UiBean text(String text) {
		height = CSG.menuFont.getBounds(text).height;
		width = CSG.menuFont.getBounds(text).width;
		this.text = text;
		return this;
	}

	public void updateDimensions(int width, int height) {
		float milieu = y + this.height / 2;
		milieu += CSG.menuFont.getBounds(text).height / 2;
		yText = milieu;
	}

	private void initVectorsParticule() {
		switch (cptPosition) {
		case 1:
		case 2:
		case 3:
			// ligne du haut
			tmpPos.y = y + height;
			tmpPos.x = x + (rand.nextFloat() * width);
			break;
		case 4:
			// ligne de droite
			tmpPos.y = y + (rand.nextFloat() * height);
			tmpPos.x = x + width + UI.PADDING;
			break;
		case 5:
		case 6:
		case 7:
			// ligne du bas
			tmpPos.y = y;
			tmpPos.x = x + (rand.nextFloat() * width);
			break;
		case 8:
			// ligne de gauche
			tmpPos.y = y + (rand.nextFloat() * height);
			tmpPos.x = x;
			break;
		}
		if (++cptPosition > 8)
			cptPosition = 1;
	}

	public void onClick(OnClick onClick) {
		click = onClick;
	}

	public boolean testClick(int x, int y) {
		if (Physique.pointDansRectangle(x, y, this.x, this.y, width, height)) {
			selected = true;
			if (click != null)
				click.onClick();
			return true;
		}
		selected = false;
		return false;
	}

	public void numberPressed(int i) {
		System.out.println(this + " " + selected);
		if (selected && inactive < System.currentTimeMillis()) {
			switch (alreadyEnteredNumber++) {
			case 1:
				number *= 10;
			case 0:
				number += i;
				break;
			case 2:
				alreadyEnteredNumber = 1;
				number = i;
				break;
			}
			inactive = System.currentTimeMillis() + 200;
			System.out.println(number + " " + numericButton + " " + this);
			if (numericButton) {
				float f = height;
				text(String.valueOf(number));
				height = f;
			}
		}
	}

	public int number() {
		return number;
	}
	
	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public float x() {
		return x;
	}

	public float y() {
		return y;
	}

	public float width() {
		return width;
	}

	public float height() {
		return height;
	}

	public String text() {
		return text;
	}

	public UiBean height(float height) {
		this.height = height;
		return this;
	}

	public UiBean width(float width) {
		this.width = width;
		return this;
	}

	public UiBean x(float x) {
		this.x = x;
		return this;
	}

	public UiBean y(float y) {
		this.y = y;
		return this;
	}

	public void numericButton(boolean b) {
		System.out.println("on met : " + b + " dans " + this);
		numericButton = b;
	}
}
