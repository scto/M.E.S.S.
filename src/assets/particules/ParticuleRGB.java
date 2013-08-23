package assets.particules;

import assets.AssetMan;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class ParticuleRGB extends Particule {
	
	protected float color;
	
	protected void flammes() {
		
		color = AssetMan.convertARGB(1, 1f, (r.nextFloat() + .8f) / 1.6f, 0f);
		
		angle = r.nextFloat() * 360f;
		vitesseAngle = angle * 100000f;
	}
	
	public void afficher(SpriteBatch batch) {
		batch.setColor(color);
		batch.draw(getTexture(), posX, posY, 0, 0, getLargeur(), getHauteur(), 1, 1, angle);
		batch.setColor(AssetMan.WHITE);
	}

}
