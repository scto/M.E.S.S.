package objets.armes.ennemi;

import jeu.Stats;
import menu.CSG;
import assets.AssetMan;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pool.Poolable;
import com.badlogic.gdx.utils.Pools;

public class ArmeLaser extends ArmeEnnemi implements Poolable{
	
	public static final int LARGEUR = CSG.LARGEUR_ECRAN / 23;
	public static final int DEMI_LARGEUR = LARGEUR/2;
	public static Pool<ArmeLaser> pool = Pools.get(ArmeLaser.class);

	@Override
	public TextureRegion getTexture() {		return AssetMan.laserVert;	}
	@Override
	protected float getVitesse() {			return Stats.V_ARME_LASER;	}
	@Override
	public int getLargeur() {				return LARGEUR;	}
	@Override
	public int getHauteur() {				return LARGEUR;	}
	@Override
	public float getDemiHauteur() {			return DEMI_LARGEUR;	}
	@Override
	public float getDemiLargeur() {			return DEMI_LARGEUR;	}
	@Override
	public void free() {					pool.free(this);	}
}
