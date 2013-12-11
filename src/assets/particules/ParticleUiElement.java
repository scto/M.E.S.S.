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

public class ParticleUiElement {
	
	private float posX, posY, largeur, color;
	public static final float LARGEUR = CSG.LARGEUR_ECRAN / 250;
	private static final Random rand = new Random();
	public static Pool<ParticleUiElement> pool = Pools.get(ParticleUiElement.class);
	private static float deplacement = 0, demiDeplacement = 0;
	
	public ParticleUiElement() {
		color = AssetMan.convertARGB(1, rand.nextFloat(), rand.nextFloat(), 1);
	}
	
	public void init(float x, float y, boolean selected) {
		posX = x;
		posY = y;
		largeur = LARGEUR;
		if (selected)
			color = AssetMan.convertARGB(1, 1, rand.nextFloat(), 1);
		else
			color = AssetMan.convertARGB(1, rand.nextFloat(), rand.nextFloat(), 1);
	}

	public void display(SpriteBatch batch) {
		batch.setColor(color);
		batch.draw(AssetMan.poussiere, posX, posY, largeur, largeur);
		batch.setColor(Color.WHITE);
	}

	public boolean mouvementEtVerif() {
		deplacement = (largeur - largeur/1.2f);
		demiDeplacement = deplacement/2;
		posX += demiDeplacement;
		posY += demiDeplacement;
		largeur -= deplacement;
		if (largeur < .5f)
			return false;
		// Je pense qu'on peut se permettre de ne pas verifier si il est tjrs � l'�cran vu son court temps de vie
		return true;
	}

}
