package menu.screens;

import jeu.CSG;
import jeu.Strings;
import menu.tuto.OnClick;
import menu.ui.Bouton;
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
		ajout(boutonBack);
		// ************************ A R M E S ****************************************************************

		// ************************ B R U I T A G E S ********************************************************
		final Bouton bruit = new Bouton(BRUITAGE_VOL + (int) (CSG.profile.effectsVolume * 10), true, CSG.menuFontSmall, LARGEUR_PETITBOUTON, HAUTEUR_PETITBOUTON, (CSG.screenWidth / 2) - Menu.LARGEUR_PETITBOUTON / 2, -Menu.decalageY + CSG.SCREEN_HEIGHT - Menu.HAUTEUR_BOUTON * LINE2, this, new OnClick() {
			public void onClick() {}
		}, false);
		ajout(bruit);
		ajout(new Bouton(MOINS, false, CSG.menuFont, LARGEUR_MINIBOUTON, HAUTEUR_MINIBOUTON, CSG.screenWidth / Menu.PADDING, -Menu.decalageY + CSG.SCREEN_HEIGHT - Menu.HAUTEUR_BOUTON * LINE2 + Menu.HAUTEUR_MINIBOUTON / 2, this, new OnClick() {
			public void onClick() {
				CSG.profile.diminuerVolumeBruitage();
				bruit.setTexte(BRUITAGE_VOL + (int) (CSG.profile.effectsVolume * 10));
				SoundMan.playBruitage(SoundMan.tirRocket);
			}
		}, false));
		ajout(new Bouton(PLUS, false, CSG.menuFont, LARGEUR_MINIBOUTON, HAUTEUR_MINIBOUTON, CSG.screenWidth - (CSG.screenWidth / Menu.PADDING) - Menu.LARGEUR_MINIBOUTON, -Menu.decalageY + CSG.SCREEN_HEIGHT - Menu.HAUTEUR_BOUTON * LINE2 + Menu.HAUTEUR_MINIBOUTON / 2, this, new OnClick() {
			public void onClick() {
				CSG.profile.augmenterVolumeBruitage();
				bruit.setTexte(BRUITAGE_VOL + (int) (CSG.profile.effectsVolume * 10));
				SoundMan.playBruitage(SoundMan.tirRocket);
			}
		}, false));
		// ************************ M U S I Q U E S ************************************************************
		final Bouton musique = new Bouton(MUSIQUE_VOL + (int) (CSG.profile.musicVolume * 10), true, CSG.menuFontSmall, LARGEUR_PETITBOUTON, HAUTEUR_PETITBOUTON, (CSG.screenWidth / 2) - Menu.LARGEUR_PETITBOUTON / 2, -Menu.decalageY + CSG.SCREEN_HEIGHT - Menu.HAUTEUR_BOUTON * LINE3, this, new OnClick() {
			public void onClick() {
			}
		}, false);
		ajout(musique);
		ajout(new Bouton(MOINS, false, CSG.menuFont, LARGEUR_MINIBOUTON, HAUTEUR_MINIBOUTON, CSG.screenWidth / Menu.PADDING, -Menu.decalageY + CSG.SCREEN_HEIGHT - Menu.HAUTEUR_BOUTON * LINE3 + Menu.HAUTEUR_MINIBOUTON / 2, this, new OnClick() {
			public void onClick() {
				CSG.profile.diminuerVolumeMusique();
				musique.setTexte(MUSIQUE_VOL + (int) (CSG.profile.musicVolume * 10));
				SoundMan.playMusic();
				if (CSG.profile.musicVolume < 0.1f) {
					SoundMan.stopMusic();
				}
			}
		}, false));
		ajout(new Bouton(PLUS, false, CSG.menuFont, LARGEUR_MINIBOUTON, HAUTEUR_MINIBOUTON, CSG.screenWidth - (CSG.screenWidth / Menu.PADDING) - Menu.LARGEUR_MINIBOUTON, -Menu.decalageY + CSG.SCREEN_HEIGHT - Menu.HAUTEUR_BOUTON * LINE3 + Menu.HAUTEUR_MINIBOUTON / 2, this, new OnClick() {
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
		final Bouton bloom = new Bouton(bloomTxt, true, CSG.menuFontSmall, LARGEUR_PETITBOUTON, HAUTEUR_PETITBOUTON, (CSG.screenWidth / 2) - Menu.LARGEUR_PETITBOUTON / 2, -Menu.decalageY + CSG.SCREEN_HEIGHT - Menu.HAUTEUR_BOUTON * LINE4, this);
		ajout(bloom);

		ajout(new Bouton(MOINS, false, CSG.menuFont, LARGEUR_MINIBOUTON, HAUTEUR_MINIBOUTON, CSG.screenWidth / Menu.PADDING, -Menu.decalageY + CSG.SCREEN_HEIGHT - Menu.HAUTEUR_BOUTON * LINE4 + Menu.HAUTEUR_MINIBOUTON / 2, this, new OnClick() {
			public void onClick() {
				CSG.profile.downBloom();
				majBloom();
				bloom.setTexte(INTENSITY + CSG.profile.getBloomString());
			}
		}, false));
		ajout(new Bouton(PLUS, false, CSG.menuFont, LARGEUR_MINIBOUTON, HAUTEUR_MINIBOUTON, CSG.screenWidth - (CSG.screenWidth / Menu.PADDING) - Menu.LARGEUR_MINIBOUTON, -Menu.decalageY + CSG.SCREEN_HEIGHT - Menu.HAUTEUR_BOUTON * LINE4 + Menu.HAUTEUR_MINIBOUTON / 2, this, new OnClick() {
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
		final Bouton bonus = new Bouton(bonusTxt, false, CSG.menuFont, LARGEUR_BOUTON, HAUTEUR_BOUTON, (CSG.screenWidth / 2) - Menu.LARGEUR_BOUTON / 2, -Menu.decalageY + CSG.SCREEN_HEIGHT - Menu.HAUTEUR_BOUTON * LINE5, this);
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
		final Bouton screenshake = new Bouton(screenshaketxt, false, CSG.menuFont, LARGEUR_BOUTON, HAUTEUR_BOUTON, (CSG.screenWidth / 2) - Menu.LARGEUR_BOUTON / 2, -Menu.decalageY + CSG.SCREEN_HEIGHT - Menu.HAUTEUR_BOUTON * LINE6, this);
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
		// ************************ C O N T R O L E S ********************************************************
//		final Bouton control = new Bouton(CSG.profile.getNomControle(), false, CSG.menuFont, LARGEUR_BOUTON, HAUTEUR_BOUTON, CSG.screenWidth / 2 - Menu.LARGEUR_BOUTON / 2, (int) (-Menu.decalageY + -2 * Menu.HAUTEUR_MINIBOUTON + CSG.SCREEN_HEIGHT - Menu.HAUTEUR_BOUTON * LINE6), this);
//		control.setClick(new OnClick() {
//			public void onClick() {
//				CSG.profile.chgControle();
//				control.setTexte(CSG.profile.getNomControle());
//			}
//		});
//		ajout(control);
		
		// sensitivity
		
		final Bouton sensitivity = new Bouton(Strings.SENSITIVITY + CSG.profile.getSensitivityString(), true, CSG.menuFontSmall, LARGEUR_PETITBOUTON, HAUTEUR_PETITBOUTON, (CSG.screenWidth / 2) - Menu.LARGEUR_PETITBOUTON / 2, -Menu.decalageY + CSG.SCREEN_HEIGHT - Menu.HAUTEUR_BOUTON * LINE7, this, new OnClick() {
			public void onClick() {
			}
		}, false);
		ajout(sensitivity);
		ajout(new Bouton(MOINS, false, CSG.menuFont, LARGEUR_MINIBOUTON, HAUTEUR_MINIBOUTON, CSG.screenWidth / Menu.PADDING, -Menu.decalageY + CSG.SCREEN_HEIGHT - Menu.HAUTEUR_BOUTON * LINE7 + Menu.HAUTEUR_MINIBOUTON / 2, this, new OnClick() {
			public void onClick() {
				CSG.profile.downSensitivity();
				sensitivity.setTexte(Strings.SENSITIVITY + CSG.profile.getSensitivityString());
			}
		}, false));
		ajout(new Bouton(PLUS, false, CSG.menuFont, LARGEUR_MINIBOUTON, HAUTEUR_MINIBOUTON, CSG.screenWidth - (CSG.screenWidth / Menu.PADDING) - Menu.LARGEUR_MINIBOUTON, -Menu.decalageY + CSG.SCREEN_HEIGHT - Menu.HAUTEUR_BOUTON * LINE7 + Menu.HAUTEUR_MINIBOUTON / 2, this, new OnClick() {
			public void onClick() {
				CSG.profile.upSensitivity();
				sensitivity.setTexte(Strings.SENSITIVITY + CSG.profile.getSensitivityString());
			}
		}, false));
	}
	
	@Override
	public void render(float delta) {
		super.render(delta);
//		CSG.batch.begin();
//		BOTTOM_TXT.render(CSG.batch, delta);
//		CSG.batch.end();
	}

	private void majBloom() {
		if (CSG.profile.bloom) {
			CSG.bloom.setBloomIntesity(CSG.profile.intensiteBloom);
			CSG.profilManager.persist();
		}
	}
}
