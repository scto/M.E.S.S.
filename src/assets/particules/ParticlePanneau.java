package assets.particules;

import java.util.Random;

import jeu.CSG;
import jeu.EndlessMode;
import assets.AssetMan;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

public class ParticlePanneau {
	
	private float posX, posY, largeur, color;
	public static final float LARGEUR = CSG.LARGEUR_ECRAN / 40;
	private static final Random rand = new Random();
	public static Pool<ParticlePanneau> pool = Pools.get(ParticlePanneau.class);
	private final Vector2 direction = new Vector2();
	private static float deplacement = 0, demiDeplacement = 0;
	private static Random r = new Random();
	
	public ParticlePanneau() {
		color = AssetMan.convertARGB(1, rand.nextFloat(), rand.nextFloat(), 1);
	}
	
	public void init(Vector2 posParticule, Vector2 baseDirection) {
		direction.x = baseDirection.x;
		direction.y = baseDirection.y;
		direction.rotate( -90 + (180 * rand.nextFloat()));
		posX = posParticule.x;
		posY = posParticule.y;
		largeur = LARGEUR;
	}

	public void display(SpriteBatch batch) {
		batch.setColor(color);
		batch.draw(AssetMan.poussiere, posX, posY, largeur, largeur);
		batch.setColor(Color.WHITE);
	}

	public boolean mouvementEtVerif() {
		deplacement = (r.nextFloat() - 0.36f) * (largeur / 2);
//		deplacement = (largeur - largeur/1.05f);
		demiDeplacement = deplacement/2;
		posX += (direction.x * EndlessMode.delta15) + demiDeplacement;
		posY += (direction.y * EndlessMode.delta15) + demiDeplacement;
		largeur -= deplacement;
		if (largeur < 1)
			return false;
		// Je pense qu'on peut se permettre de ne pas verifier si il est tjrs � l'�cran vu son court temps de vie
		return true;
	}
}
