package vaisseaux;

import vaisseaux.armes.joueur.ArmeJoueur;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class Particules {
	
	public static final int MAX = 100;
	public static Array<Debris> restes = new Array<Debris>(false, MAX);
	
	public static void render(SpriteBatch batch) {
		for (Debris p : restes){
			p.afficher(batch);
			if (p.mouvementEtVerif() == false) {
				restes.removeValue(p, true);
			}
		}
	}
	
	public static void ajoutDebris(ArmeJoueur a) {
		if (Particules.restes.size < Particules.MAX) {
			ArmeJoueur.roueCouleurs();
			Debris r = Debris.pool.obtain();
			r.init(a);
			restes.add(r);
			
			Debris r2 = Debris.pool.obtain();
			r2.init(a);
			restes.add(r2);
			
			Debris r3 = Debris.pool.obtain();
			r3.init(a);
			restes.add(r3);
		}
	}

}
