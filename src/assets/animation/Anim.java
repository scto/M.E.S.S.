package assets.animation;

import menu.CSG;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Anim {
	
	protected Animation anime;
	
	public static final AnimationEnnemiDeBase animEnnemiDeBase = new AnimationEnnemiDeBase();
	public static final AnimationBouleBleu animBouleBleu = new AnimationBouleBleu();
	public static final AnimationBouleVerte bouleVerte = new AnimationBouleVerte();
	public static final AnimationBouleBleuRouge bouleBleuRouge = new AnimationBouleBleuRouge();
	public static final AnimationCylon cylon = new AnimationCylon();
	public static final AnimationCylonCasse cylonCasse = new AnimationCylonCasse();
	public static final AnimationEnnemiAileDeployee aileDeployee = new AnimationEnnemiAileDeployee();
	public static final AnimationEnnemiToupie toupie = new AnimationEnnemiToupie();
	public static final AnimationEnnemiTourne tourne = new AnimationEnnemiTourne();
	public static final AnimationKinder kinder = new AnimationKinder();
	public static final AnimationMeteorite meteor = new AnimationMeteorite();
	public static final AnimationMine mine = new AnimationMine();
	public static final AnimationTirBleu tirBleu = new AnimationTirBleu();
	public static final AnimationTirFeu tirFeu = new AnimationTirFeu();
	public static final AnimationTirTrois tirTrois = new AnimationTirTrois();
	
	public Animation initAnim(String[] frames, float tps, int type) {
		TextureRegion[] tr = new TextureRegion[frames.length];
		for (int i = 0; i < frames.length; i++)  {
			tr[i] = CSG.getAssetMan().getAtlas().findRegion(frames[i]);
		} 
		anime = new Animation(tps, tr);
		anime.setPlayMode(type);
		return anime;
	}

	public TextureRegion getKeyFrame(float tps, boolean b) {
		return anime.getKeyFrame(tps, b);
	}
	
	public TextureRegion getTexture(float tps) {
		return anime.getKeyFrame(tps, false);
	}
}