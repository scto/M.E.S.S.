package elements.particular.bonuses;

import jeu.EndlessMode;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class BonusTimeMvt extends Bonus {
	
	protected float tps = 0;
	
	@Override
	void drawMeMoveMe(SpriteBatch batch) {
		tps += EndlessMode.delta;
		batch.draw(getTexture(), pos.x, pos.y, WIDTH, WIDTH);
		pos.y += getMvt() * EndlessMode.delta;
	}

	protected abstract float getMvt();
	public void reset() {
		tps = 0;
	}
}
