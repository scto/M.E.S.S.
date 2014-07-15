package menu.ui;

import jeu.CSG;
import jeu.Physic;
import jeu.Profile;
import jeu.Stats;
import assets.AssetMan;
import assets.sprites.AnimPlayer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import elements.generic.weapons.player.WeaponManager;
import elements.particular.Player;

public class WeaponButton extends AbstractButton {
	
	public static final float width = CSG.width / 7, widthDiv10 = width / 10, offsetShip = (width - Player.WIDTH)/2, height = width * 1.3f, padding = (CSG.width / 7) / 7;
	public static final float heightBackground = height * 1.1f;
	private static final float unselectedWidth = width * 0.8f, unselectedHeight = height * 0.8f;
	private final TextureRegion tr;
	private final int num;
	private boolean selected;
	private final String label;
	private static float xText = 10;
	
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
		horizontalBarre(x, y + height - Barre.HEIGHT, width - Barre.HEIGHT);
		// bottom
		horizontalBarre(x, y, width - Barre.HEIGHT);
		// left
		verticalBarre(x - Barre.HALF_HEIGHT, y, height);
		verticalBarre(x + width - Barre.HEIGHT, y, height);
		Barre.nbr = 0;
	}
	
	public void setSelected(String label) {
		if (CSG.profile.selectedWeapon.equals(label))
			selected = true;
		else
			selected = false;
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
//			batch.draw(AnimPlayer.tr[2], (num * width) + offsetShip, y - Player.HEIGHT, Player.WIDTH, Player.HEIGHT);
			batch.draw(tr, x, y, width, height);
			batch.draw(AnimPlayer.TEXTURES[2], x + offsetShip, y - Player.HEIGHT, Player.WIDTH, Player.HEIGHT);
		} else {
			batch.setColor(AssetMan.ALPHA70);
//			batch.draw(tr, (num * width) + widthDiv10, y, unselectedWidth, unselectedWidth);
			batch.draw(tr, x + widthDiv10, y, unselectedWidth, unselectedHeight);
		}
		
		if ( (num == 5 || num == 4) && CSG.profile.isAllWeaponsLvlOk(Profile.LVL_UNLOCK) == false) {
			CSG.menuFontSmall.draw(CSG.batch, "Unlock the 2 remaining weapons by getting the others at level 6 or higher ", xText, 4 + CSG.menuFontSmall.getBounds("W").height);
			xText -= 0.3f;
			batch.setColor(AssetMan.RED);
//			batch.draw(AssetMan.dust, (num * width), y, 0, 		0, width*1.2f, 	Stats.u, 1, 1, 40);
//			batch.draw(AssetMan.dust, (num * width), y, width, 	0, width, 		Stats.u, 1, 1, -35);
			batch.draw(AssetMan.dust, x, y, 0, 		0, width*1.2f, 	Stats.u, 1, 1, 40);
			batch.draw(AssetMan.dust, x, y, width, 	0, width, 		Stats.u, 1, 1, -35);
			if (-CSG.menuFontSmall.getBounds("Unlock the 2 remaining weapons by getting the others at level 6 or higher ").width > xText)
				xText = CSG.width;
		} else {
			if (Gdx.input.justTouched()) {
				if (Physic.isPointInSquare(Gdx.input.getX(), CSG.height - Gdx.input.getY(), x, y, width)) {
					CSG.profile.setArmeSelectionnee(label);
					Player.weapon = WeaponManager.getWeaponManager(label);
				}
			}
		}
		batch.setColor(AssetMan.WHITE);
		
		setSelected(label);
	}
	
}
