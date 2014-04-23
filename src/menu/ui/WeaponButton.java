package menu.ui;

import jeu.CSG;
import jeu.Physic;
import jeu.Stats;
import assets.AssetMan;
import assets.animation.AnimPlayer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

import elements.generic.Player;
import elements.generic.weapons.player.WeaponManager;

public class WeaponButton {
	
	public static final float width = CSG.screenWidth / 7, widthDiv10 = width / 10, offsetShip = (width - Player.LARGEUR)/2, height = width * 1.3f, padding = (CSG.screenWidth / 7) / 7;
	public static final float heightBackground = height * 1.1f;
	private static final float unselectedWidth = width * 0.8f, unselectedHeight = height * 0.8f;
	private final TextureRegion tr;
	private final int num;
	private boolean selected;
	private final String label;
	private static float xText = 10;
	private float y, x;
	private final Array<Barre> barres = new Array<Barre>();
	
	public WeaponButton(TextureRegion tr, int num, String label, float y) {
		this.tr = tr;
		this.num = num;
		this.label = label;
		setSelected(label);
		this.y = y;
		this.x = (num * width) + (padding * (num+1));
		x += Barre.HALF_HEIGHT;
		setBarres();
	}

	private void setBarres() {
		barres.clear();
		// top 
		horizontalBarre(x, y + height - Barre.HEIGHT);
		// bottom
		horizontalBarre(x, y);
		// left
		verticalBarre(x - Barre.HALF_HEIGHT, y);
		verticalBarre(x + width - Barre.HEIGHT, y);
		Barre.nbr = 0;
	}
	
	public void setSelected(String label) {
		if (CSG.profile.armeSelectionnee.equals(label))
			selected = true;
		else
			selected = false;
	}
	
	private void verticalBarre(float x, float y) {
		final float distanceACouvrir = height;
		final int nbrBarre = (int) (((distanceACouvrir * 0.8f) / Barre.HEIGHT));
		final float distanceCouverte = nbrBarre * Barre.HEIGHT;
		final float ecartTotal = distanceACouvrir - distanceCouverte;
		final float ecart = ecartTotal / (nbrBarre-1);
		float tmpX = 0;
		for (int i = 0; i < nbrBarre; i++) {
//		for (float tmpX = 0; tmpX < height - Barre.HEIGHT;) {
			barres.add(Barre.POOL.obtain().init(x, y + tmpX));
			tmpX += (Barre.HEIGHT + ecart);
//			tmpX += Stats.uSur4;
		}
	}

	private void horizontalBarre(float x, float y) {
		final float distanceACouvrir = width - Barre.HEIGHT;
		final int nbrBarre = (int) (((distanceACouvrir * 0.7f) / Barre.HEIGHT));
		final float distanceCouverte = nbrBarre * Barre.HEIGHT;
		final float ecartTotal = distanceACouvrir - distanceCouverte;
		final float ecart = ecartTotal / (nbrBarre-1);
		float tmpX = -Barre.HALF_HEIGHT;
		for (int i = 0; i < nbrBarre; i++) {
			barres.add(Barre.POOL.obtain().init(x + tmpX, y));
			tmpX += (Barre.HEIGHT + ecart);
		}
	}
	
	/**
	 * return true if a weapon has been selected
	 * @param batch
	 * @return 
	 */
	public void draw(SpriteBatch batch) {
		batch.setColor(AssetMan.BLACK);
		batch.draw(AssetMan.backgroundButton, x, y, width, heightBackground);
//		batch.draw(AssetMan.backgroundButton, num * width, y, width, heightBackground);
		for (Barre b : barres)
			b.draw(batch);
		
		if (selected) {
			batch.setColor(AssetMan.WHITE);
//			batch.draw(tr, num * width, y, width, width);
//			batch.draw(AnimPlayer.tr[2], (num * width) + offsetShip, y - Player.HAUTEUR, Player.LARGEUR, Player.HAUTEUR);
			batch.draw(tr, x, y, width, height);
			batch.draw(AnimPlayer.tr[2], x + offsetShip, y - Player.HEIGHT, Player.LARGEUR, Player.HEIGHT);
		} else {
			batch.setColor(AssetMan.ALPHA70);
//			batch.draw(tr, (num * width) + widthDiv10, y, unselectedWidth, unselectedWidth);
			batch.draw(tr, x + widthDiv10, y, unselectedWidth, unselectedHeight);
		}
		
		if ( (num == 5 || num == 4) && CSG.profile.areWeaponsUnlocked() == false) {
			CSG.menuFontPetite.draw(CSG.batch, "Unlock the 2 remaining weapons by getting the others at level 6 or higher ", xText, 4 + CSG.menuFontPetite.getBounds("W").height);
			xText -= 0.3f;
			batch.setColor(AssetMan.RED);
//			batch.draw(AssetMan.dust, (num * width), y, 0, 		0, width*1.2f, 	Stats.u, 1, 1, 40);
//			batch.draw(AssetMan.dust, (num * width), y, width, 	0, width, 		Stats.u, 1, 1, -35);
			batch.draw(AssetMan.dust, x, y, 0, 		0, width*1.2f, 	Stats.u, 1, 1, 40);
			batch.draw(AssetMan.dust, x, y, width, 	0, width, 		Stats.u, 1, 1, -35);
			if (-CSG.menuFontPetite.getBounds("Unlock the 2 remaining weapons by getting the others at level 6 or higher ").width > xText)
				xText = CSG.screenWidth;
		} else {
			if (Gdx.input.justTouched()) {
				if (Physic.isPointInSquare(Gdx.input.getX(), CSG.SCREEN_HEIGHT - Gdx.input.getY(), x, y, width)) {
					CSG.profile.setArmeSelectionnee(label);
					Player.weapon = WeaponManager.getWeaponManager(label);
				}
			}
		}
		batch.setColor(AssetMan.WHITE);
		
		setSelected(label);
	}
	
}
