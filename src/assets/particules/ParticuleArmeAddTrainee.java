package assets.particules;

import objets.armes.joueur.ArmeAdd;
import jeu.CSG;
import jeu.EndlessMode;
import assets.AssetMan;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.badlogic.gdx.utils.Pools;

public class ParticuleArmeAddTrainee extends Particule implements Poolable {
	
	public static Pool<ParticuleArmeAddTrainee> pool = Pools.get(ParticuleArmeAddTrainee.class);
	public static float LARGEUR = CSG.LARGEUR_ECRAN / 100;
	private float color;

	@Override
	public void display(SpriteBatch batch) {
		batch.setColor(color);
		batch.draw(AssetMan.poussiere, posX, posY, temps, temps);
		batch.setColor(AssetMan.WHITE);
	}
	
	@Override
	public boolean mouvementEtVerif() {
		if (temps < 4) return false;
		
		diminution = temps * (EndlessMode.delta15);
		temps -= diminution;
		diminution /= 2;

		posX += diminution;
		posY += diminution;
		
		return true;
	}

	public void init(ArmeAdd a) {
		posX = a.position.x;
		posY = a.position.y;
		
		temps = ArmeAdd.LARGEUR + (r.nextFloat() * LARGEUR);
		
		color = a.getColor();
	}

	@Override
	public void reset() {
	}
	@Override
	public void free() {
		pool.free(this);
		Particules.nbArmeJoueur--;
	}
}
