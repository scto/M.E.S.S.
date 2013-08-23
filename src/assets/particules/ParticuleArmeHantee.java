package assets.particules;

import jeu.Endless;
import menu.CSG;
import vaisseaux.armes.Armes;
import vaisseaux.armes.joueur.ArmeHantee;
import vaisseaux.bonus.Bonus;
import assets.AssetMan;
import assets.animation.Anim;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.badlogic.gdx.utils.Pools;

public class ParticuleArmeHantee extends Particule implements Poolable {
	
	public static final float LARGEUR = ArmeHantee.LARGEUR;
	public static Pool<ParticuleArmeHantee> pool = Pools.get(ParticuleArmeHantee.class);
	
	@Override
	protected TextureRegion getTexture() {
		return Anim.tirTrois.getTexture(1);
	}

	@Override
	public void afficher(SpriteBatch batch) {
		batch.draw(getTexture(), posX, posY, temps/2, temps/2, temps, temps, 1, 1, angle);
	}
	
	@Override
	public boolean mouvementEtVerif() {
		angle += ArmeHantee.VITESSE_ANGLE * Endless.delta;
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
		pool.free(this);
	}
}
