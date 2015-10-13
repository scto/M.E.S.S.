package elements.particular.particles;

import java.util.Random;

import jeu.CSG;
import assets.AssetMan;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

public class ParticleUiElement {
	
	private float posX, posY, largeur, color;
	public static final float LARGEUR = CSG.screenWidth / 250;
	private static final Random rand = new Random();
	public static Pool<ParticleUiElement> pool = Pools.get(ParticleUiElement.class);
	private static float deplacement = 0, demiDeplacement = 0;
	private static final Random r = new Random();
	
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
		batch.draw(AssetMan.dust, posX, posY, largeur, largeur);
		batch.setColor(Color.WHITE);
	}

	public boolean mouvementEtVerif() {
		deplacement = (r.nextFloat() - 0.36f) * largeur;
//		deplacement = (largeur - largeur/1.2f);
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
