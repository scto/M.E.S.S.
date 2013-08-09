package assets.background;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Ceci represente une couche de background
 * @author Julien
 */
public class ParallaxLayer{
	
   public TextureRegion region ;
   public float originalX, ratioVitesseY, deplacementY, largeurRegion, hauteurRegion;
   
   public ParallaxLayer(TextureRegion region, float ratioVitesseY){
      this.region  = region;
      this.deplacementY = ratioVitesseY;
      largeurRegion = region.getRegionWidth();
      hauteurRegion = region.getRegionHeight();
      this.ratioVitesseY = ratioVitesseY % hauteurRegion;
   }
}

