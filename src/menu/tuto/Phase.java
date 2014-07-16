package menu.tuto;

import com.badlogic.gdx.utils.Array;

public class Phase {
	
	private final Array<OnClick> actions = new Array<OnClick>();

	public Phase(OnClick... actions) {
		super();
		for (OnClick a : actions)
			this.actions.add(a);
	}
	
	public void act() {
		for (OnClick a : actions)
			a.onClick();
	}
	
}
