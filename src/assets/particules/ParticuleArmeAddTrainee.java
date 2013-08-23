package assets.particules;

import jeu.Endless;
import menu.CSG;
import vaisseaux.armes.Armes;
import vaisseaux.armes.joueur.ArmeAdd;
import vaisseaux.armes.joueur.ArmeHantee;
import vaisseaux.bonus.Bonus;
import assets.AssetMan;
import assets.animation.Anim;
import assets.animation.AnimationTirAdd;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.badlogic.gdx.utils.Pools;

public class ParticuleArmeAddTrainee extends Particule implements Poolable {
	
	public static Pool<ParticuleArmeAddTrainee> pool = Pools.get(ParticuleArmeAddTrainee.class);
	private float hauteur;
	protected static float diminutionH;

	@Override
	public void afficher(SpriteBatch batch) {
		batch.draw(AnimationTirAdd.getTexture(), posX, posY, temps/2, hauteur/2, temps, hauteur, 1, 1, angle);
	}
	
	@Override
	public boolean mouvementEtVerif() {
		if (temps < 4) return false;
		
		diminution = temps * (Endless.delta15);
		diminutionH = hauteur * (Endless.delta15);
		temps -= diminution;
		hauteur -= diminutionH;
		diminution /= 2;
		diminutionH /= 2;

		posX += diminution;
		posY += diminutionH;
		
		return true;
	}

	public void init(ArmeAdd a) {
		angle = a.angle + 90;
		posX = a.position.x;
		posY = a.position.y;
		
		temps = ArmeAdd.LARGEUR;
		hauteur = ArmeAdd.HAUTEUR;
	}

	@Override
	public void reset() {
	}
	@Override
	public void free() {
		pool.free(this);
	}
}
