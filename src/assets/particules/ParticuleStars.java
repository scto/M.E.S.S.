package assets.particules;

import jeu.EndlessMode;
import menu.CSG;
import assets.AssetMan;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.badlogic.gdx.utils.Pools;

public class ParticuleStars extends Particule implements Poolable{
	
	public static int LARGEUR = CSG.LARGEUR_ECRAN/120;
	public static final int VITESSE = CSG.HAUTEUR_ECRAN / 180;
	public static Pool<ParticuleStars> pool = Pools.get(ParticuleStars.class);
	public float posX, posY, largeur;
	static float nextEtoile = 0;

	public void init() {
		largeur = (r.nextFloat() * LARGEUR) + 1;
		posX = (r.nextFloat() * CSG.LARGEUR_ZONE_JEU + largeur) - largeur/2;
		posY = CSG.HAUTEUR_ECRAN + largeur;
	}

	@Override
	public void reset() {	}

	public void display(SpriteBatch batch) {
//		batch.draw(AssetMan.poussiere, posX, posY, largeur/2, largeur/2, largeur, largeur, 1, 1, posY);
		batch.draw(AssetMan.poussiere, posX, posY, largeur, largeur);
	}

	@Override
	public boolean mouvementEtVerif() {
		posY -= (VITESSE * largeur*largeur * EndlessMode.delta);
		if (posY < 0) {
			pool.free(this);
			return false;
		}
		return true;
	}

	@Override
	public void free() {
		pool.free(this);
	}

	public static void mightAdd() {
		if (EndlessMode.maintenant > nextEtoile) {
			ParticuleStars p = ParticuleStars.pool.obtain();
			p.init();
			nextEtoile = EndlessMode.maintenant + Particules.background.size/300;
			Particules.background.add(p);
		}
	}

	public static void initBackground() {
		for (int i = 0; i < 200; i++) {
			ParticuleStars p = ParticuleStars.pool.obtain();
			p.init();
			p.posY = r.nextFloat() * CSG.HAUTEUR_ECRAN;
			Particules.background.add(p);
		}
	}
}

