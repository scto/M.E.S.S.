package objets.ennemis;

import javax.sound.midi.Sequence;

import objets.armes.Armes;
import objets.armes.typeTir.Tirs;
import assets.animation.AnimationEnnemiDeBase;

import com.badlogic.gdx.graphics.g2d.Animation;

public class Phases  {
	
	public Phases(Animation anim) {
		// TODO Auto-generated constructor stub
	}

	Animation anim;
	Sequence sequence;
	Armes arme;
	Tirs tir;
	Triggers[] triggers;
	Evenements[] evenements;

	float temps;
}
