package menu.screens;

import jeu.CSG;
import jeu.Strings;
import menu.tuto.OnClick;
import menu.ui.Button;
import assets.SoundMan;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class MenuOptions extends AbstractScreen {

	private static final int LINE2 = 4;
	private static final int LINE3 = 6;
	private static final int LINE4 = 8;
	private static final int LINE5 = 14;
	private static final int LINE6 = 12;
	private static final int LINE7 = 10;
//	private static final Credits BOTTOM_TXT = new Credits("    SENSITIVITY WILL APPLY TO ANY CONTROL YOU CHOOSE, 1 IS NEUTRAL, ABOVE 1 IS MORE SENSITIVE   ");

	public MenuOptions(final Game game) {
		super(game);
		
		Gdx.input.setCatchBackKey(true);
		ajout(buttonBack);
		// ************************ A R M E S ****************************************************************

		// ************************ B R U I T A G E S ********************************************************
		final Button bruit = new Button(BRUITAGE_VOL + (int) (CSG.profile.effectsVolume * 10), true, CSG.menuFontSmall, SMALL_BUTTON_WIDTH, SMALL_BUTTON_HEIGHT, (CSG.screenWidth / 2) - Menu.SMALL_BUTTON_WIDTH / 2, -Menu.yOffset + CSG.screenHeight - Menu.BUTTON_HEIGHT * LINE2, this, new OnClick() {
			public void onClick() {}
		}, false);
		ajout(bruit);
		ajout(new Button(MOINS, false, CSG.menuFont, MINI_BOUTON_WIDTH, MINI_BOUTON_HEIGHT, CSG.screenWidth / Menu.PADDING, -Menu.yOffset + CSG.screenHeight - Menu.BUTTON_HEIGHT * LINE2 + Menu.MINI_BOUTON_HEIGHT / 2, this, new OnClick() {
			public void onClick() {
				CSG.profile.diminuerVolumeBruitage();
				bruit.setTexte(BRUITAGE_VOL + (int) (CSG.profile.effectsVolume * 10));
				SoundMan.playBruitage(SoundMan.shotRocket);
			}
		}, false));
		ajout(new Button(PLUS, false, CSG.menuFont, MINI_BOUTON_WIDTH, MINI_BOUTON_HEIGHT, CSG.screenWidth - (CSG.screenWidth / Menu.PADDING) - Menu.MINI_BOUTON_WIDTH, -Menu.yOffset + CSG.screenHeight - Menu.BUTTON_HEIGHT * LINE2 + Menu.MINI_BOUTON_HEIGHT / 2, this, new OnClick() {
			public void onClick() {
				CSG.profile.augmenterVolumeBruitage();
				bruit.setTexte(BRUITAGE_VOL + (int) (CSG.profile.effectsVolume * 10));
				SoundMan.playBruitage(SoundMan.shotRocket);
			}
		}, false));
		// ************************ M U S I Q U E S ************************************************************
		final Button musique = new Button(MUSIQUE_VOL + (int) (CSG.profile.musicVolume * 10), true, CSG.menuFontSmall, SMALL_BUTTON_WIDTH, SMALL_BUTTON_HEIGHT, (CSG.screenWidth / 2) - Menu.SMALL_BUTTON_WIDTH / 2, -Menu.yOffset + CSG.screenHeight - Menu.BUTTON_HEIGHT * LINE3, this, new OnClick() {
			public void onClick() {
			}
		}, false);
		ajout(musique);
		ajout(new Button(MOINS, false, CSG.menuFont, MINI_BOUTON_WIDTH, MINI_BOUTON_HEIGHT, CSG.screenWidth / Menu.PADDING, -Menu.yOffset + CSG.screenHeight - Menu.BUTTON_HEIGHT * LINE3 + Menu.MINI_BOUTON_HEIGHT / 2, this, new OnClick() {
			public void onClick() {
				CSG.profile.diminuerVolumeMusique();
				musique.setTexte(MUSIQUE_VOL + (int) (CSG.profile.musicVolume * 10));
				SoundMan.playMusic();
				if (CSG.profile.musicVolume < 0.1f) {
					SoundMan.stopMusic();
				}
			}
		}, false));
		ajout(new Button(PLUS, false, CSG.menuFont, MINI_BOUTON_WIDTH, MINI_BOUTON_HEIGHT, CSG.screenWidth - (CSG.screenWidth / Menu.PADDING) - Menu.MINI_BOUTON_WIDTH, -Menu.yOffset + CSG.screenHeight - Menu.BUTTON_HEIGHT * LINE3 + Menu.MINI_BOUTON_HEIGHT / 2, this, new OnClick() {
			public void onClick() {
				CSG.profile.augmenterVolumeMusique();
				musique.setTexte(MUSIQUE_VOL + (int) (CSG.profile.musicVolume * 10));
				SoundMan.playMusic();
			}
		}, false));

		// ****************************** B L O O M ************************************************************
		String bloomTxt = "BLOOM OFF";
		if (CSG.profile.bloom)
			bloomTxt = "BLOOM ON";
		final Button bloom = new Button(bloomTxt, true, CSG.menuFontSmall, SMALL_BUTTON_WIDTH, SMALL_BUTTON_HEIGHT, (CSG.screenWidth / 2) - Menu.SMALL_BUTTON_WIDTH / 2, -Menu.yOffset + CSG.screenHeight - Menu.BUTTON_HEIGHT * LINE4, this);
		ajout(bloom);

		ajout(new Button(MOINS, false, CSG.menuFont, MINI_BOUTON_WIDTH, MINI_BOUTON_HEIGHT, CSG.screenWidth / Menu.PADDING, -Menu.yOffset + CSG.screenHeight - Menu.BUTTON_HEIGHT * LINE4 + Menu.MINI_BOUTON_HEIGHT / 2, this, new OnClick() {
			public void onClick() {
				CSG.profile.downBloom();
				majBloom();
				bloom.setTexte(INTENSITY + CSG.profile.getBloomString());
			}
		}, false));
		ajout(new Button(PLUS, false, CSG.menuFont, MINI_BOUTON_WIDTH, MINI_BOUTON_HEIGHT, CSG.screenWidth - (CSG.screenWidth / Menu.PADDING) - Menu.MINI_BOUTON_WIDTH, -Menu.yOffset + CSG.screenHeight - Menu.BUTTON_HEIGHT * LINE4 + Menu.MINI_BOUTON_HEIGHT / 2, this, new OnClick() {
			public void onClick() {
				CSG.profile.bloom = true;
				CSG.initBloom();
				CSG.profile.upBloom();
				majBloom();
				bloom.setTexte(INTENSITY + CSG.profile.getBloomString());
			}
		}, false));
		// ****************************** B O N U S ************************************************************
		String bonusTxt = "Automatic bonus";
		if (CSG.profile.manualBonus)
			bonusTxt = "Manual bonus";
		final Button bonus = new Button(bonusTxt, false, CSG.menuFont, BUTTON_WIDTH, BUTTON_HEIGHT, (CSG.screenWidth / 2) - Menu.BUTTON_WIDTH / 2, -Menu.yOffset + CSG.screenHeight - Menu.BUTTON_HEIGHT * LINE5, this);
		bonus.setClick(new OnClick() {
			@Override
			public void onClick() {
				CSG.profile.manualBonus = !CSG.profile.manualBonus;
				if (CSG.profile.manualBonus)
					bonus.setTexte("Manual bonus");
				else 
					bonus.setTexte("Automatic bonus");
			}
		});
		ajout(bonus);
		String screenshaketxt = "Screenshake : on";
		if (CSG.profile.screenshake == false)
			screenshaketxt = "Screenshake : off";
		final Button screenshake = new Button(screenshaketxt, false, CSG.menuFont, BUTTON_WIDTH, BUTTON_HEIGHT, (CSG.screenWidth / 2) - Menu.BUTTON_WIDTH / 2, -Menu.yOffset + CSG.screenHeight - Menu.BUTTON_HEIGHT * LINE6, this);
		screenshake.setClick(new OnClick() {
			@Override
			public void onClick() {
				CSG.profile.screenshake = !CSG.profile.screenshake;
				if (CSG.profile.screenshake)
					screenshake.setTexte("Screenshake : on");
				else 
					screenshake.setTexte("Screenshake : off");
			}
		});
		ajout(screenshake);
		
		// sensitivity
		
		final Button sensitivity = new Button(Strings.SENSITIVITY + CSG.profile.getSensitivityString(), true, CSG.menuFontSmall, SMALL_BUTTON_WIDTH, SMALL_BUTTON_HEIGHT, (CSG.screenWidth / 2) - Menu.SMALL_BUTTON_WIDTH / 2, -Menu.yOffset + CSG.screenHeight - Menu.BUTTON_HEIGHT * LINE7, this, new OnClick() {
			public void onClick() {
			}
		}, false);
		ajout(sensitivity);
		ajout(new Button(MOINS, false, CSG.menuFont, MINI_BOUTON_WIDTH, MINI_BOUTON_HEIGHT, CSG.screenWidth / Menu.PADDING, -Menu.yOffset + CSG.screenHeight - Menu.BUTTON_HEIGHT * LINE7 + Menu.MINI_BOUTON_HEIGHT / 2, this, new OnClick() {
			public void onClick() {
				CSG.profile.downSensitivity();
				sensitivity.setTexte(Strings.SENSITIVITY + CSG.profile.getSensitivityString());
			}
		}, false));
		ajout(new Button(PLUS, false, CSG.menuFont, MINI_BOUTON_WIDTH, MINI_BOUTON_HEIGHT, CSG.screenWidth - (CSG.screenWidth / Menu.PADDING) - Menu.MINI_BOUTON_WIDTH, -Menu.yOffset + CSG.screenHeight - Menu.BUTTON_HEIGHT * LINE7 + Menu.MINI_BOUTON_HEIGHT / 2, this, new OnClick() {
			public void onClick() {
				CSG.profile.upSensitivity();
				sensitivity.setTexte(Strings.SENSITIVITY + CSG.profile.getSensitivityString());
			}
		}, false));
	}

	private void majBloom() {
		if (CSG.profile.bloom) {
			CSG.bloom.setBloomIntesity(CSG.profile.intensiteBloom);
			CSG.profilManager.persist();
		}
	}
}
