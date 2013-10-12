package assets.particules;

import objets.armes.Armes;
import objets.armes.joueur.ArmeHantee;
import jeu.EndlessMode;
import assets.animation.AnimationTirTrois;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.badlogic.gdx.utils.Pools;

public class ParticuleArmeHantee extends Particule implements Poolable {
	
	public static final float LARGEUR = ArmeHantee.LARGEUR;
	public static Pool<ParticuleArmeHantee> pool = Pools.get(ParticuleArmeHantee.class);
	private float angle;
	
	@Override
	protected TextureRegion getTexture() {
		return AnimationTirTrois.getTexture(1);
	}

	@Override
	public void display(SpriteBatch batch) {
		batch.draw(getTexture(), posX, posY, temps/2, temps/2, temps, temps, 1, 1, angle);
	}
	
	@Override
	public boolean mouvementEtVerif() {
		angle += ArmeHantee.VITESSE_ANGLE * EndlessMode.delta;
		return trainee();
	}

	public void init(Armes a) {
		posX = a.position.x;
		posY = a.position.y;
		
		temps = LARGEUR;
	}

	@Override
	public void reset() {
	}
	@Override
	public void free() {
		Particules.nbArmeJoueur--;
		pool.free(this);
	}
}
