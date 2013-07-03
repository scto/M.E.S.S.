package vaisseaux;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class RestesEnnemis {
	
	public static final int MAX = 50;
	public static Array<Reste> restes = new Array<Reste>(false, MAX);
	
	public static void render(SpriteBatch batch) {
		for (Reste p : restes){
			p.afficher(batch);
			if (p.mouvementEtVerif() == false) {
				restes.removeValue(p, true);
			}
		}
	}
	
	public static void ajout(Vector2 position, Vector2 direction) {
		if (RestesEnnemis.restes.size < RestesEnnemis.MAX) {
			Reste r = Reste.pool.obtain();
			r.init(position, direction);
			restes.add(r);
		}
	}

}
