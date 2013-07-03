 package vaisseaux.bonus;

import jeu.Endless;
import jeu.Stats;

import menu.CSG;
import assets.AssetMan;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;
import com.badlogic.gdx.utils.Pool.Poolable;

public class XP extends Bonus implements Poolable{
	public int valeur;
	private float tps = 0;
	private boolean gauche;
	public static Pool<XP> pool = Pools.get(XP.class);
	
	public void init(float x, float y, int xp){
		valeur = xp;
		posX = x;
		posY = y;
		if(posX < CSG.DEMI_LARGEUR_ZONE_JEU) gauche = false;
		else gauche = true;
		liste.add(this);
	}


	@Override
	boolean afficherEtMvt(SpriteBatch batch) {
		if (valeur < 49) batch.draw(AssetMan.XP, posX, posY,	LARGEUR, LARGEUR);
		else batch.draw(AssetMan.XP2, posX, posY,	LARGEUR, LARGEUR);
		tps += Endless.delta;
		// Le fait descendre
		posY -= Stats.VITESSE_BONUS * Endless.delta;
		// le fait aller � gauche ou � droite de plus en plus suivant le temps �coul�
		if (gauche)		posX -= ( (tps*tps) * Endless.delta);
		else			posX += ( (tps*tps) * Endless.delta);
		
		return true;
	}

	@Override
	public void prisEtFree() {
		Endless.score += valeur;
//		CSG.profil.addXp(valeur);
		pool.free(this);
	}


	@Override
	public void reset() {
		tps = 0;
	}


	@Override
	public void free() {
		pool.free(this);
	}
}
