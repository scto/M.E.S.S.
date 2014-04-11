package menu.screens;

import jeu.CSG;
import jeu.EndlessMode;
import jeu.Strings;
import menu.tuto.OnClick;
import menu.ui.Bouton;
import menu.ui.WeaponButton;
import assets.AssetMan;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.utils.Array;

import elements.generic.weapons.player.BlueSweepWeapon;
import elements.generic.weapons.player.PinkWeapon;
import elements.generic.weapons.player.SpaceInvaderWeapon;
import elements.generic.weapons.player.SunWeapon;
import elements.generic.weapons.player.TWeapon;
import elements.particular.particles.Particles;

public class ChoixDifficulte extends AbstractScreen {

	private final Game game;
	private final Array<WeaponButton> weaponButtons = new Array<WeaponButton>(6);
	private static final float Y_WEAPON = (CSG.SCREEN_HEIGHT - HAUTEUR_BOUTON * 15) - (WeaponButton.width * 2);

	public ChoixDifficulte(Game game) {
		super(game);
		this.game = game;
		setUpScreenElements();
		Gdx.input.setCatchBackKey(true);
	}

	public void setUpScreenElements() {
		ajout(boutonBack);
		// ** ** ** PIECE OF CAKE ** ** **
		final Bouton lvl1 = new Bouton(Strings.LVL1, false, CSG.menuFont, LARGEUR_BOUTON, HAUTEUR_BOUTON, CSG.screenWidth / PADDING, CSG.SCREEN_HEIGHT - HAUTEUR_BOUTON * 5, this, new OnClick() {
			public void onClick() {
				changeMenu(new EndlessMode(game, CSG.batch, 1));
			}
		}, true);
		ajout(lvl1);
		// ** ** ** LET'S ROCK ** ** **
		final Bouton lvl2 = new Bouton(Strings.LVL2, false, CSG.menuFont, LARGEUR_BOUTON, HAUTEUR_BOUTON, CSG.screenWidth / PADDING, CSG.SCREEN_HEIGHT - HAUTEUR_BOUTON * 7.5f, this, new OnClick() {
			public void onClick() {
				changeMenu(new EndlessMode(game, CSG.batch, 2));
			}
		}, true);
		ajout(lvl2);
		// ** ** ** COME GET SOME ** ** **
		final Bouton lvl3 = new Bouton(Strings.LVL3, false, CSG.menuFont, LARGEUR_BOUTON, HAUTEUR_BOUTON, CSG.screenWidth / PADDING, CSG.SCREEN_HEIGHT - HAUTEUR_BOUTON * 10, this, new OnClick() {
			public void onClick() {
				changeMenu(new EndlessMode(game, CSG.batch, 3));
			}
		}, true);
		ajout(lvl3);
		if (Gdx.app.getVersion() != 0) {
			CSG.myRequestHandler.showAds(true);
		}
		final Bouton lvl4 = new Bouton(Strings.LVL4, false, CSG.menuFont, LARGEUR_BOUTON, HAUTEUR_BOUTON, CSG.screenWidth / PADDING, CSG.SCREEN_HEIGHT - HAUTEUR_BOUTON * 12.5f, this, new OnClick() {
			public void onClick() {
				changeMenu(new EndlessMode(game, CSG.batch, 4));
			}
		}, true);
		ajout(lvl4);
		final Bouton chooseWeapon = new Bouton(Strings.CHOOSE_WEAPON, false, CSG.menuFont, LARGEUR_BOUTON, HAUTEUR_BOUTON, CSG.screenWidth / PADDING, CSG.SCREEN_HEIGHT - HAUTEUR_BOUTON * 15.5f, this, new OnClick() {
			public void onClick() {
			}
		}, true);
		ajout(chooseWeapon);
		weaponButtons.add(new WeaponButton(AssetMan.iconDefaultW, 0, PinkWeapon.LABEL, Y_WEAPON));
		weaponButtons.add(new WeaponButton(AssetMan.iconFireballW, 1, elements.generic.weapons.player.Fireball.LABEL, Y_WEAPON));
		weaponButtons.add(new WeaponButton(AssetMan.iconSpreadW, 2, BlueSweepWeapon.LABEL, Y_WEAPON));
		weaponButtons.add(new WeaponButton(AssetMan.iconTW, 3, TWeapon.LABEL, Y_WEAPON));
		weaponButtons.add(new WeaponButton(AssetMan.iconSpaceInvW, 4, SpaceInvaderWeapon.LABEL, Y_WEAPON));
		weaponButtons.add(new WeaponButton(AssetMan.iconSunW, 5, SunWeapon.LABEL, Y_WEAPON));
	}

	@Override
	public void render(float delta) {
		CSG.begin(delta);
		if (Gdx.input.isKeyPressed(Keys.BACK)) {
			keyBackPressed();
		}
		CSG.batch.begin();
		Particles.background(CSG.batch);
		for (int i = 0; i < boutons.size; i++) {
			if (boutons.get(i) != null) boutons.get(i).draw(CSG.batch);
		}
		Particles.drawUi(CSG.batch);
		for (WeaponButton wb : weaponButtons) {
			wb.draw(CSG.batch);
		}
		CSG.end();
		EndlessMode.majDeltas();
		EndlessMode.fps = Gdx.graphics.getFramesPerSecond();
		EndlessMode.perf = EndlessMode.fps / 6;
		if (Gdx.input.isKeyPressed(Keys.BACK) || Gdx.input.isKeyPressed(Keys.ESCAPE)) {
			keyBackPressed();
		}
	}

	@Override
	public void keyBackPressed() {
		changeMenu(new Menu(game));
	}
}