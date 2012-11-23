package menu.splash;

import physique.Physique;
import menu.CSG;
import vaisseaux.ennemis.particuliers.EnnemiDeBase;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class EnnemiSplash {
	private static final int COLONNES = 5;
	private static final float TPS_ANIM = .1f;
	private static Animation anim;
	private float angle = 90;
	private float angleVirage = 40;
	private float tps = 0;
	private Vector2 direction;
	private Vector2 position;
	
	
	public EnnemiSplash() {
		Texture tmp = new Texture(Gdx.files.internal("jeu/vaisseaux24sur28.png"));
		TextureRegion[][] sheet = TextureRegion.split(tmp, 24, 28);
		TextureRegion[] tr = new TextureRegion[COLONNES];
		for (int i = 0; i < COLONNES; i++)
			tr[i] = sheet[6][i];
		anim = new Animation(TPS_ANIM, tr);
		anim.setPlayMode(Animation.LOOP_PINGPONG);
		// -- -- random
		double rand = Math.random();
		if(rand < .25)
			direction = new Vector2((float)Math.random(), (float)Math.random());
		else if(rand < .5)
			direction = new Vector2((float)-Math.random(), (float)Math.random());
		else if(rand < .75)
			direction = new Vector2((float)Math.random(), (float)-Math.random());
		else 
			direction = new Vector2((float)-Math.random(), (float)-Math.random());
		
		position = new Vector2( (float)(Math.random() * CSG.LARGEUR_ECRAN)
				, (float)(Math.random() * CSG.HAUTEUR_ECRAN));
		angle = direction.angle();
	}
	
	public boolean afficher(SpriteBatch batch, float delta){
		tps += delta;
		batch.draw(anim.getKeyFrame(tps), position.x, position.y,
				// CENTRE DE LA ROTATION EN X													// CENTRE DE LA ROTATION EN Y
				EnnemiDeBase.LARGEUR/2, EnnemiDeBase.HAUTEUR/2,
				// LARGEUR DU RECTANGLE AFFICHE		HAUTEUR DU RECTANGLE
				EnnemiDeBase.LARGEUR, EnnemiDeBase.HAUTEUR,
				//scaleX the scale of the rectangle around originX/originY in x ET Y
				1,1,
				// L'ANGLE DE ROTATION
				angle,
				//FLIP OU PAS
				false);
		position.add(direction);
		return Physique.toujoursAfficher(position, EnnemiDeBase.LARGEUR, EnnemiDeBase.HAUTEUR);
	}
	
	
}
