package assets.animation;

import java.text.DecimalFormat;

import jeu.TriggerUser;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimTemps extends Anim {

	private boolean inc = true;
	private float step = 1;
	private float trigger;

	public AnimTemps(TriggerUser user) {
		trigger = user.temps() + step;
	}

	@Override
	public TextureRegion frame(TriggerUser user) {
		if (next(user)) {
			switch (mode) {
			case Animation.NORMAL:
				if (frameIsntLast())
					frameNumber++;
				break;
			case Animation.LOOP_PINGPONG:
				if (inc) {// si on incremente et qu'on est pas encore au bout
					if (frameIsntLast()) {
						frameNumber++;
					} else { // sinon on decremente
						inc = false;
						frameNumber--;
						if (frameNumber < 0)
							frameNumber = 0;
					}
				} else {
					frameNumber--;
					if (frameNumber < 0) {
						frameNumber = 1;
						if (frameNumber >= keyFrames.length)
							frameNumber = 0;
						inc = true;
					}
				}
				break;
			case Animation.LOOP:
				if (frameIsntLast()) {
					frameNumber++;
				} else { // sinon on revient à 0
					frameNumber = 0;
				}
				break;
			}
			System.out.println("Frame number : " + frameNumber + " keyframes lenght : " + keyFrames.length + " anim type : " + mode  + " user temps : " + user.temps() + " trigger : " + trigger);
		}
		return keyFrames[frameNumber];
	}

	private boolean frameIsntLast() {
		return frameNumber + 1 < keyFrames.length;
	}

	private boolean next(TriggerUser user) {
		if (user.temps() > trigger) {
			trigger += step;
			return true;
		}
		return false;
	}
	
	public void step(float step) {
		this.step = step;
		if (this.step < 0)
			this.step = 0;
		DecimalFormat twoDForm = new DecimalFormat("#,##");
		step = Float.valueOf(twoDForm.format(step));
	}

	public float step() {
		return step;
	}

	public void reset() {
		super.reset();
		trigger = step;
		if (keyFrames.length >= 0)
			frameNumber = 0;
		inc = true;
	}

	public String stepAsString() {
		DecimalFormat twoDForm = new DecimalFormat("0.00");
		return twoDForm.format(step);
	}
}
