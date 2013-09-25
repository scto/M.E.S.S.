package menu.tuto;

import com.badlogic.gdx.utils.Array;

public class Phase {
	
	private final Array<Action> actions = new Array<Action>();

	public Phase(Action... actions) {
		super();
		for (Action a : actions)
			this.actions.add(a);
	}
	
	public void act() {
		for (Action a : actions)
			a.action();
	}
	
}
