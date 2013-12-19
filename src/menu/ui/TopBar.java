package menu.ui;

import java.util.ArrayList;

import assets.particules.Particules;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TopBar extends UiComponent {

	private ArrayList<UiComponent> elements = new ArrayList<UiComponent>();
	private ArrayList<LigneParticuleGenerator> generators = new ArrayList<LigneParticuleGenerator>();
	private int decalage = 0, max = 40;
	
	public TopBar() {
		for (int i = 0; i < UI.facteurParticules; i++) {
			generators.add(new LigneParticuleGenerator( (Gdx.graphics.getWidth() / UI.facteurParticules) * i, 0, Gdx.graphics.getWidth(), true) );
		}
	}
	
	public void add(UiComponent element) {
		elements.add(element);
	}

	@Override
	public void draw(SpriteBatch batch) {
		for (UiComponent e : elements)
			e.draw(batch);
//		for (LigneParticuleGenerator generator : generators)
//			generator.effet(y());
		decalage++;
		for (int i = decalage; i < width(); i = i + max) 
			Particules.ajoutUiElement(i, y(), false);
		if (decalage > max)
			decalage = 0;
	}

	@Override
	public void updateDimensions(int widthScreen, int heightScreen) {
		max = widthScreen / 10;
		width((float)widthScreen);
		height(heightScreen / UI.TOP_BAR);
		y(heightScreen - height());
		float original = UI.PADDING;
		for (UiComponent e : elements) {
			e.y( y() + UI.PADDING);
			e.height( (heightScreen / UI.TOP_BAR) - UI.PADDING * 2 );
			// On met les �l�ments les un � c�t� des autres
			e.x(original);
			original += e.width() + UI.PADDING * 2;
		}
		for (UiComponent e : elements)
			e.updateDimensions(widthScreen, heightScreen);
		for (LigneParticuleGenerator generator : generators) {
			generator.majDimensions(widthScreen);
		}
	}
	
	@Override
	public boolean testClick(int x, int y) {
		if (super.testClick(x, y)) {
			setSelected(false);
			for (UiComponent e : new ArrayList<UiComponent>(elements))
				e.testClick(x, y);
			return true;
		}
		return false;
	}

	public void removeAll() {
		elements.clear();
	}

	@Override
	public void numberPressed(int i) {
		for (UiComponent bean : elements)
			bean.numberPressed(i);
	}

	public ArrayList<UiComponent> getAllComponents() {
		return elements;
	}
}
