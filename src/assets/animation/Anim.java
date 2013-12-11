package assets.animation;

import jeu.TriggerUser;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public abstract class Anim {
	
	protected TextureRegion[] keyFrames;
	protected int mode = Animation.NORMAL;
	protected int frameNumber;
	
	protected int mode() {
		return mode;
	}
	
	public abstract TextureRegion frame(TriggerUser user);

	public void init(Array<TextureRegion> keyFrames) {
		this.keyFrames = new TextureRegion[keyFrames.size];
		for (int i = 0, n = keyFrames.size; i < n; i++)
			this.keyFrames[i] = keyFrames.get(i);
	}
	
	public void reset() {
		frameNumber = 0;
	}

	public void mode(int type) {
		this.mode = type;
	}

	public String nextMode() {
		switch(mode) {
		case Animation.NORMAL:		mode = Animation.LOOP;				return "LOOP";
		case Animation.LOOP:		mode = Animation.LOOP_PINGPONG;		return "PING PONG";
		default:					mode = Animation.NORMAL;			return "NORMAL";
		}
	}
}
