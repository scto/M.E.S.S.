package affichage;
import jeu.Endless;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class ParallaxBackground {
	   
	   private ParallaxLayer[] layers;
	   private Camera camera;
	   private static Vector2 speed = new Vector2();
	   
	   /**
	    * @param layers  The  background layers
	    * @param width   The screenWith
	    * @param height The screenHeight
	    * @param speed A Vector2 attribute to point out the x and y speed
	    */
	   public ParallaxBackground(ParallaxLayer[] layers,float width,float height,Vector2 speedX){
		  super();
	      this.layers = layers;
	      speed.set(speedX);
	      camera = new OrthographicCamera(width, height);
	   }
	   
	   public static void changerOrientation(float i){
		   speed.x = i;
	   }
	   
//	   public void render(SpriteBatch batch){
//		   for (ParallaxLayer layer : layers) {
//			   batch.draw(layer.region, layer.region.getRegionWidth() + layer.padding.x, 0);
//		   }
//	   }
	   public void render(SpriteBatch batch){
		this.camera.position.add(speed.x * Endless.delta, speed.y * Endless.delta, 0);
		for (ParallaxLayer layer : layers) {
			float currentX = -camera.position.x * layer.parallaxRatio.x	% (layer.region.getRegionWidth() + layer.padding.x);

			if (speed.x < 0)
				currentX += -(layer.region.getRegionWidth() + layer.padding.x);
			do {
				float currentY = -camera.position.y * layer.parallaxRatio.y	% (layer.region.getRegionHeight() + layer.padding.y);
				if (speed.y < 0)
					currentY += -(layer.region.getRegionHeight() + layer.padding.y);
				do {
					batch.draw(layer.region, currentX,//-this.camera.viewportWidth / 2	+ currentX + layer.startPosition.x,
							currentY);//-this.camera.viewportHeight / 2 + currentY + layer.startPosition.y);
					currentY += (layer.region.getRegionHeight() + layer.padding.y);
				} 
				while (currentY < camera.viewportHeight);
				currentX += (layer.region.getRegionWidth() + layer.padding.x);
			} while (currentX < camera.viewportWidth);

		}
	   }
	}